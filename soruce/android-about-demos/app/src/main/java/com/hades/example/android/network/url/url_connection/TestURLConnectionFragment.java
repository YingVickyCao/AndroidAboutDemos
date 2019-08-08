package com.hades.example.android.network.url.url_connection;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.hades.example.android.Constant;
import com.hades.example.android.R;

public class TestURLConnectionFragment extends Fragment {
    TextView show;

    String response;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == Constant.KEY_UPDATE_UI) {
                // 设置show组件显示服务器响应
                show.setText(response);
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.network_url_connection, container, false);
        show = view.findViewById(R.id.show);

        view.findViewById(R.id.get).setOnClickListener(v -> get());
        view.findViewById(R.id.post).setOnClickListener(v -> post());
        return view;
    }

    /*
        Android 9.0 SM-G9730

         1) nodeJs 不支持https，only support http
         2) Set app use clear text Traffic(明文)
         https://blog.csdn.net/gengkui9897/article/details/82863966

        D/NetworkSecurityConfig: Using Network Security Config from resource network_security_config debugBuild: true
        I/System.out: (HTTPLog)-Static: isSBSettingEnabled false
        I/System.out: (HTTPLog)-Static: isSBSettingEnabled false
        D/TcpOptimizer: TcpOptimizer-ON
        I/System.out: null--->[HTTP/1.1 200 OK]
        I/System.out: Connection--->[keep-alive]
        I/System.out: Content-Type--->[text/plain]
        I/System.out: Date--->[Fri, 02 Aug 2019 12:07:57 GMT]
        I/System.out: Transfer-Encoding--->[chunked]
        I/System.out: X-Android-Received-Millis--->[1564747681806]
        I/System.out: X-Android-Response-Source--->[NETWORK 200]
        I/System.out: X-Android-Selected-Protocol--->[http/1.1]
        I/System.out: X-Android-Sent-Millis--->[1564747681797]
     */
    private void get() {
        new Thread() {
            @Override
            public void run() {
                // [Get]http://localhost:8888/getSum?num1=5&num2=15
                response = GetPostUtil.sendGet("http://192.168.8.104:7000/getSum", "num1=5&num2=15");
                // 发送消息通知UI线程更新UI组件
                handler.sendEmptyMessage(Constant.KEY_UPDATE_UI);
            }
        }.start();
    }

    /*
        I/System.out: (HTTPLog)-Static: isSBSettingEnabled false
        I/System.out: (HTTPLog)-Static: isSBSettingEnabled false
        D/TcpOptimizer: TcpOptimizer-ON
        I/System.out: ContentEncoding:--->null
        I/System.out: ContentLength:--->-1
        I/System.out: ContentType:--->application/json; charset=utf8
        I/System.out: Date:--->1564750256000
        I/System.out: Expiration:--->0
        I/System.out: LastModified:--->0
        I/System.out: DoInput:--->true
        I/System.out: DoOutput:--->true
        I/System.out: URL:--->http://192.168.8.104:7777/login
        I/System.out: Permission:--->java.net.SocketPermission@ceed424
        I/System.out: null--->[HTTP/1.1 200 OK]
        I/System.out: Connection--->[keep-alive]
        I/System.out: Content-Type--->[application/json; charset=utf8]
        I/System.out: Date--->[Fri, 02 Aug 2019 12:50:56 GMT]
        I/System.out: Transfer-Encoding--->[chunked]
        I/System.out: X-Android-Received-Millis--->[1564750261422]
        I/System.out: X-Android-Response-Source--->[NETWORK 200]
        I/System.out: X-Android-Selected-Protocol--->[http/1.1]
        I/System.out: X-Android-Sent-Millis--->[1564750261412]
     */
    private void post() {
        new Thread() {
            @Override
            public void run() {
                // http://localhost:7777/login
                response = GetPostUtil.sendPost("http://192.168.8.104:7777/login", "name=ga&pwd=2"); // {"msg":"sucess","name":"ga","status":1}
                // 发送消息通知UI线程更新UI组件
                handler.sendEmptyMessage(Constant.KEY_UPDATE_UI);
            }
        }.start();
    }
}

