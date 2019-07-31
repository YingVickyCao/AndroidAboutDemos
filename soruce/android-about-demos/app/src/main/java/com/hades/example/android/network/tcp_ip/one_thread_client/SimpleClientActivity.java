package com.hades.example.android.network.tcp_ip.one_thread_client;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.hades.example.android.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class SimpleClientActivity extends Activity {
    TextView info;

    public final static String SERVER_ADDRESS = "192.168.9.114";
    public final static int SERVER_PORT = 30000;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.network_simple_client_layout);
        info = findViewById(R.id.info);

        new Thread() {
            @Override
            public void run() {
                try {
                    Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);                                // 建立连接到远程服务器的Socket
                    BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream())); // 将Socket对应的输入流包装成BufferedReader
                    String line = br.readLine();
                    showReceivedInfoFromRemoteServer(line);
                    br.close();
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private void showReceivedInfoFromRemoteServer(String s) {
        runOnUiThread(() -> info.setText(s));
    }
}