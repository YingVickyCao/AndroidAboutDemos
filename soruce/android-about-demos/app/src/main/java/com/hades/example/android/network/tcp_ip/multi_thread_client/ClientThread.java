package com.hades.example.android.network.tcp_ip.multi_thread_client;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import com.hades.example.android.Constant;
import com.hades.example.android.network.tcp_ip.one_thread_client.SimpleClientActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.nio.charset.StandardCharsets;


public class ClientThread implements Runnable {
    private static final String TAG = ClientThread.class.getSimpleName();

    private Socket socket;
    private Handler ui_handler;    // 定义向UI线定义向UI线程发送消息的Handler对象程发送消息的Handler对象
    public Handler send_Handler;  // 定义接收UI线程的消息的Handler对象

    BufferedReader receive_server_2_memory = null;
    OutputStream send_memory_2_server = null;

    public ClientThread(Handler handler) {
        this.ui_handler = handler;
    }

    @SuppressLint("HandlerLeak")
    public void run() {
        try {
//            socket = new Socket(SimpleClientActivity.SERVER_ADDRESS, SimpleClientActivity.SERVER_PORT);
//            socket.setSoTimeout(10000); // 10s, 设置连接后，读写超时时间

            socket = new Socket();
            socket.connect(new InetSocketAddress(SimpleClientActivity.SERVER_ADDRESS, SimpleClientActivity.SERVER_PORT), 1000); // 10s,设置连接超时时间

            receive_server_2_memory = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            send_memory_2_server = socket.getOutputStream();

            new Thread() {  // 启动一条子线程来读取服务器响应的数据
                @Override
                public void run() {
                    String content = null;
                    try {
                        while ((content = receive_server_2_memory.readLine()) != null) {// 不断读取Socket输入流中的内容
                            // 每当读到来自服务器的数据之后，发送消息通知程序界面显示该数据
                            Log.d(TAG, "receive,run,thread[" + Thread.currentThread().getId() + "," + Thread.currentThread().getName() + "]"); // thread[4427,Thread-9]
                            Message msg = new Message();
                            msg.what = Constant.KEY_UPDATE_UI;
                            msg.obj = content;
                            ui_handler.sendMessage(msg);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }.start();

            // 为当前线程初始化Looper
            Looper.prepare();
            // 创建revHandler对象
            send_Handler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    if (msg.what == Constant.KEY_SEND) {// 接收到UI线程中用户输入的数据
                        Log.d(TAG, "send,handleMessage,thread[" + Thread.currentThread().getId() + "," + Thread.currentThread().getName() + "]"); //thread[4423,Thread-8]
                        // 将用户在文本框内输入的内容写入网络
                        try {
                            send_memory_2_server.write((msg.obj.toString() + "\r\n").getBytes(StandardCharsets.UTF_8));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            };
            // 启动Looper
            Looper.loop();
        } catch (SocketTimeoutException e1) {
            System.out.println("网络连接超时！！");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

