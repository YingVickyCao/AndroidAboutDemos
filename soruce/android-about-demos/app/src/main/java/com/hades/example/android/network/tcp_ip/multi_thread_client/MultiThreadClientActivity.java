package com.hades.example.android.network.tcp_ip.multi_thread_client;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.hades.example.android.Constant;
import com.hades.example.android.R;

public class MultiThreadClientActivity extends Activity {
    private static final String TAG = MultiThreadClientActivity.class.getSimpleName();

    EditText input;
    TextView show;

    Handler handler;
    ClientThread clientThread;

    @SuppressLint("HandlerLeak")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.network_multi_thread_client_layout);

        show = findViewById(R.id.receiveMessage);
        input = findViewById(R.id.input);

        findViewById(R.id.send).setOnClickListener(v -> send());

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == Constant.KEY_UPDATE_UI) {
                    Log.d(TAG, "receive,handleMessage,thread[ " + Thread.currentThread().getId() + "," + Thread.currentThread().getName() + "]"); // thread[2,main]
                    show.append("\n" + msg.obj.toString());
                }
            }
        };


        clientThread = new ClientThread(handler);
        new Thread(clientThread).start();   // 客户端启动ClientThread线程创建网络连接、读取来自服务器的数据
    }

    private void send() {
        Log.d(TAG, "send,thread[ " + Thread.currentThread().getId() + "," + Thread.currentThread().getName() + "]"); // thread[2,main]
        try {
            // 当用户按下发送按钮后，将用户输入的数据封装成Message, 然后发送给子线程的Handler
            Message msg = new Message();
            msg.what = Constant.KEY_SEND;
            msg.obj = input.getText().toString();

            clientThread.send_Handler.sendMessage(msg);

            input.setText("");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

