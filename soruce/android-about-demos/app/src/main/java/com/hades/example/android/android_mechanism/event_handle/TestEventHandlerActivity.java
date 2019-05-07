package com.hades.example.android.android_mechanism.event_handle;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.hades.example.android.R;
import com.hades.example.android.android_mechanism.event_handle.base_on_callback.EventHandlerBaseOnCallbackActivity;
import com.hades.example.android.android_mechanism.event_handle.base_on_listener.TestEventHandlerBaseOnListenFragment;
import com.hades.example.android.android_mechanism.event_handle.base_on_listener.event_listener_type.EventListenerTypeActivity;
import com.hades.example.android.android_mechanism.event_handle.base_on_listener.plane.TestCircleViewActivity;
import com.hades.example.android.lib.base.BaseActivity;

public class TestEventHandlerActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_handler);
        initViews();

        findViewById(R.id.page_event_handler_base_on_listener).setOnClickListener(v -> page_event_handler_base_on_listener());
        findViewById(R.id.page_event_handler_base_on_listener_4_plane).setOnClickListener(v -> page_event_handler_base_on_listener_4_plane());
        findViewById(R.id.page_event_handler_base_on_listener_types).setOnClickListener(v -> page_event_handler_base_on_listener_types());
        findViewById(R.id.page_event_handler_base_on_callback).setOnClickListener(v -> page_event_handler_base_on_callback());
    }

    @Override
    protected boolean isNeedCheckPermission() {
        return false;
    }

    private void page_event_handler_base_on_listener() {
        showFragment(new TestEventHandlerBaseOnListenFragment());
    }

    private void page_event_handler_base_on_listener_4_plane() {
        showActivity(TestCircleViewActivity.class);
    }

    private void page_event_handler_base_on_listener_types() {
        showActivity(EventListenerTypeActivity.class);
    }

    private void page_event_handler_base_on_callback() {
//        showFragment(new EventHandlerBaseOnCallbackFragment());
        showActivity(EventHandlerBaseOnCallbackActivity.class);

    }
}
