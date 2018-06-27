package com.hades.android.example.android_about_demos.msg_handler;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ScrollView;

import com.hades.android.example.android_about_demos.R;
import com.hades.android.example.android_about_demos.event_handle.base_on_callback.EventHandlerBaseOnCallbackActivity;
import com.hades.android.example.android_about_demos.event_handle.base_on_callback.EventHandlerBaseOnCallbackFragment;
import com.hades.android.example.android_about_demos.msg_handler.main_2_thread_2_main.HandlerThreadFragment;
import com.hades.android.example.android_about_demos.msg_handler.main_2_thread_2_main.HandlerThreadFragment4Refactor;
import com.hades.android.example.android_about_demos.msg_handler.main_2_thread_2_main.SumFragment;
import com.hades.android.example.android_about_demos.msg_handler.thread_2_main.RecycleUpdateTextFragment;

public class MessageHandlerActivity extends Activity {
    private ScrollView mScrollView;
    private View mFragmentRoot;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_handler);

        mScrollView = findViewById(R.id.scrollView);
        mFragmentRoot = findViewById(R.id.fragmentRoot);

        findViewById(R.id.test_thread_2_main_update_text).setOnClickListener(v -> test_thread_2_main_update_text());
        findViewById(R.id.test_main_2_thread_sum).setOnClickListener(v -> test_main_2_thread_sum());
        findViewById(R.id.test_main_2_thread_2_main_Sum).setOnClickListener(v -> test_main_2_thread_2_main_Sum());
        findViewById(R.id.test_main_2_thread_2_main_Sum2).setOnClickListener(v -> test_main_2_thread_2_main_Sum2());

        hideBtns();
//        showBtns();
        showCurrentTest();
    }

    private void showBtns() {
        mScrollView.setVisibility(View.VISIBLE);
        mFragmentRoot.setVisibility(View.GONE);
    }

    private void hideBtns() {
        mScrollView.setVisibility(View.GONE);
        mFragmentRoot.setVisibility(View.VISIBLE);
    }

    private void showCurrentTest() {
        test_main_2_thread_2_main_Sum2();
    }

    private void showFragment(Fragment fragment) {
        hideBtns();
        getFragmentManager().beginTransaction().replace(R.id.fragmentRoot, fragment, fragment.getClass().getSimpleName()).commit();
    }

    private void showActivity(Class<?> dest) {
        startActivity(new Intent(this, dest));
    }

    private void test_thread_2_main_update_text() {
        showFragment(new RecycleUpdateTextFragment());
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