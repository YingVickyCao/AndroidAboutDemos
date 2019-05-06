package com.hades.example.android.android_mechanism.msg_handler;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.hades.example.android.R;
import com.hades.example.android.android_mechanism.msg_handler._main_2_thread.TestMsgMain2ThreadFragment;
import com.hades.example.android.android_mechanism.msg_handler._main_2_thread_2_main.TestMsgMain2Thread2MainFragment;
import com.hades.example.android.android_mechanism.msg_handler._main_2_thread_2_main.handler_thread.TestMsgMain2Thread2Main_HandlerThread_Fragment;
import com.hades.example.android.android_mechanism.msg_handler._thread_2_main.TestMsgThread2MainFragment;
import com.hades.example.android.lib.base.BaseActivity;

public class MessageHandlerActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_handler);

        initViews();

        findViewById(R.id.pageHandler_thread_2_main).setOnClickListener(v -> pageHandler_thread_2_main());
        findViewById(R.id.pageHandler_main_2_thread_2_main).setOnClickListener(v -> pageHandler_main_2_thread_2_main());
        findViewById(R.id.pageHandler_main_2_thread_2_main_HandlerThread).setOnClickListener(v -> pageHandler_main_2_thread_2_main_HandlerThread());
        findViewById(R.id.pageHandler_main_2_thread).setOnClickListener(v -> pageHandler_main_2_thread());
    }

    @Override
    protected void showCurrentTest() {
        pageHandler_thread_2_main();
    }

    private void pageHandler_thread_2_main() {
        showFragment(new TestMsgThread2MainFragment());
    }

    private void pageHandler_main_2_thread_2_main() {
        showFragment(new TestMsgMain2Thread2MainFragment());
    }

    private void pageHandler_main_2_thread_2_main_HandlerThread() {
        // showFragment(new HandlerThreadFragment4Refactor());
        showFragment(new TestMsgMain2Thread2Main_HandlerThread_Fragment());
    }

    private void pageHandler_main_2_thread() {
        showFragment(new TestMsgMain2ThreadFragment());
    }
}