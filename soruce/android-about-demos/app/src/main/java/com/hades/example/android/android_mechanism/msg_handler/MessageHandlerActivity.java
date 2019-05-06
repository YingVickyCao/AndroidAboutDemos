package com.hades.example.android.android_mechanism.msg_handler;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.hades.example.android.R;
import com.hades.example.android.lib.base.BaseActivity;
import com.hades.example.android.android_mechanism.msg_handler.main_2_thread_2_main.SumFragment;
import com.hades.example.android.android_mechanism.msg_handler.main_2_thread_2_main.handler_thread.HandlerThreadFragment;
import com.hades.example.android.android_mechanism.msg_handler.main_2_thread_2_main.handler_thread.refactor.HandlerThreadFragment4Refactor;
import com.hades.example.android.android_mechanism.msg_handler.thread_2_main.MsgThread2MainFragment;

public class MessageHandlerActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_handler);

        findViewById(R.id.test_thread_2_main_update_text).setOnClickListener(v -> test_thread_2_main_update_text());
        findViewById(R.id.test_main_2_thread_sum).setOnClickListener(v -> test_main_2_thread_sum());
        findViewById(R.id.test_main_2_thread_2_main_Sum).setOnClickListener(v -> test_main_2_thread_2_main_Sum());
        findViewById(R.id.test_main_2_thread_2_main_Sum2).setOnClickListener(v -> test_main_2_thread_2_main_Sum2());

        hideBtns();
//        showBtns();
        showCurrentTest();
    }

    @Override
    protected boolean isNeedCheckPermission() {
        return false;
    }

    private void test_thread_2_main_update_text() {
        showFragment(new MsgThread2MainFragment());
    }

    private void test_main_2_thread_sum() {
        showFragment(new SumFragment());
    }

    private void test_main_2_thread_2_main_Sum() {
        showFragment(new HandlerThreadFragment());
    }

    private void test_main_2_thread_2_main_Sum2() {
        showFragment(new HandlerThreadFragment4Refactor());
    }
}