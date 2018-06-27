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
import com.hades.android.example.android_about_demos.event_handle.base_on_listener.event_listener_type.EventListenerTypeActivity;
import com.hades.android.example.android_about_demos.msg_handler.main_2_thread.SumFragment;
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

        findViewById(R.id.thread_2_main_update_text).setOnClickListener(v -> thread_2_main_update_text());
        findViewById(R.id.main_2_thread_sum).setOnClickListener(v -> main_2_thread_sum());
        findViewById(R.id.test_event_handler_base_on_listener_types).setOnClickListener(v -> test_event_handler_base_on_listener_types());
        findViewById(R.id.test_event_handler_base_on_callback).setOnClickListener(v -> test_event_handler_base_on_callback());

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
        main_2_thread_sum();
    }

    private void showFragment(Fragment fragment) {
        hideBtns();
        getFragmentManager().beginTransaction().replace(R.id.fragmentRoot, fragment, fragment.getClass().getSimpleName()).commit();
    }

    private void showActivity(Class<?> dest) {
        startActivity(new Intent(this, dest));
    }

    private void thread_2_main_update_text() {
        showFragment(new RecycleUpdateTextFragment());
    }

    private void main_2_thread_sum() {
        showFragment(new SumFragment());
    }

    private void test_event_handler_base_on_listener_types() {
        showActivity(EventListenerTypeActivity.class);
    }

    private void test_event_handler_base_on_callback() {
//        showFragment(new EventHandlerBaseOnCallbackFragment());
        showActivity(EventHandlerBaseOnCallbackActivity.class);

    }
}