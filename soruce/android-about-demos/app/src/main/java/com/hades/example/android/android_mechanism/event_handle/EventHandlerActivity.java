package com.hades.example.android.android_mechanism.event_handle;

import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;

import androidx.annotation.Nullable;

import com.hades.example.android.R;
import com.hades.example.android.android_mechanism.event_handle.base_on_callback.EventHandlerBaseOnCallbackActivity;
import com.hades.example.android.android_mechanism.event_handle.base_on_listener.EventHandlerBaseOnListenFragment;
import com.hades.example.android.android_mechanism.event_handle.base_on_listener.event_listener_type.EventListenerTypeActivity;
import com.hades.example.android.android_mechanism.event_handle.base_on_listener.plane.PlaneViewActivity;
import com.hades.example.android.lib.base.BaseActivity;

public class EventHandlerActivity extends BaseActivity {
    private ScrollView mScrollView;
    private View mFragmentRoot;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_handler);

        mScrollView = findViewById(R.id.scrollView);
        mFragmentRoot = findViewById(R.id.fragmentRoot);

        findViewById(R.id.testEventHandlerBaseOnListener).setOnClickListener(v -> testEventHandlerBaseOnListener());
        findViewById(R.id.testEventHandlerBaseOnListener4Plane).setOnClickListener(v -> testEventHandlerBaseOnListener4Plane());
        findViewById(R.id.test_event_handler_base_on_listener_types).setOnClickListener(v -> test_event_handler_base_on_listener_types());
        findViewById(R.id.test_event_handler_base_on_callback).setOnClickListener(v -> test_event_handler_base_on_callback());

//        hideBtns();
        showBtns();
//        showCurrentTest();
    }

    @Override
    protected boolean isNeedCheckPermission() {
        return false;
    }

    private void testEventHandlerBaseOnListener() {
        showFragment(new EventHandlerBaseOnListenFragment());
    }

    private void testEventHandlerBaseOnListener4Plane() {
        showActivity(PlaneViewActivity.class);
    }

    private void test_event_handler_base_on_listener_types() {
        showActivity(EventListenerTypeActivity.class);
    }

    private void test_event_handler_base_on_callback() {
//        showFragment(new EventHandlerBaseOnCallbackFragment());
        showActivity(EventHandlerBaseOnCallbackActivity.class);

    }
}
