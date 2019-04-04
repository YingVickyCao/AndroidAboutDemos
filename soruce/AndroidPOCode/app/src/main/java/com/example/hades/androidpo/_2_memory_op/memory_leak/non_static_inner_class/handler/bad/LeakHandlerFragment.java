package com.example.hades.androidpo._2_memory_op.memory_leak.non_static_inner_class.handler.bad;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;

public class LeakHandlerFragment extends Fragment {
    private Handler mHandler;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                // what?
                showProgressBar();
            }
        };
    }

    private void showProgressBar() {
    }

    private void sendMsg() {
        Message message = new Message();
        mHandler.sendMessage(message);
    }
}