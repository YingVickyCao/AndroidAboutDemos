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
            if (msg.what == Constant.KEY_RECEIVE) {
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

    private void get() {
        new Thread() {
            @Override
            public void run() {
                // [Get]http://localhost:8888/getSum?num1=5&num2=15
                response = GetPostUtil.sendGet("https://192.168.8.104:8888/getSum", "num1=5&num2=15");
                // 发送消息通知UI线程更新UI组件
                handler.sendEmptyMessage(Constant.KEY_RECEIVE);
            }
        }.start();
    }

    private void post() {
        new Thread() {
            @Override
            public void run() {
                response = GetPostUtil.sendPost(
                        "http://192.168.8.104:8888/abc/login.jsp"
                        , "name=crazyit.org&pass=leegang");
            }
        }.start();
        // 发送消息通知UI线程更新UI组件
        handler.sendEmptyMessage(Constant.KEY_RECEIVE);
    }
}

