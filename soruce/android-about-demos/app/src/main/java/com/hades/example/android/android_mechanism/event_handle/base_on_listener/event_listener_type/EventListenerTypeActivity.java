package com.hades.example.android.android_mechanism.event_handle.base_on_listener.event_listener_type;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.hades.example.android.R;
import com.hades.example.android.android_mechanism.event_handle.base_on_listener.event_listener_type.activity_itself.ActivityItselfListenerActivity;
import com.hades.example.android.android_mechanism.event_handle.base_on_listener.event_listener_type.anonymous_inner_class.AnonymousInnerClassListenFragment;
import com.hades.example.android.android_mechanism.event_handle.base_on_listener.event_listener_type.inner_class.InnerClassListenFragment;
import com.hades.example.android.android_mechanism.event_handle.base_on_listener.event_listener_type.outer_class.OuterClassListenFragment;
import com.hades.example.android.android_mechanism.event_handle.base_on_listener.event_listener_type.xml.XmlListenerActivity;
import com.hades.example.android.lib.base.BaseActivity;

public class EventListenerTypeActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_listener_type);

        findViewById(R.id.test_listener_4_inner_class).setOnClickListener(v -> test_listener_4_inner_class());
        findViewById(R.id.test_listener_4_outer_class).setOnClickListener(v -> test_listener_4_outer_class());
        findViewById(R.id.test_listener_4_activity_itself).setOnClickListener(v -> test_listener_4_activity_itself());
        findViewById(R.id.test_listener_4_anonymous_inner_class).setOnClickListener(v -> test_listener_4_anonymous_inner_class());
        findViewById(R.id.test_listener_4_xml).setOnClickListener(v -> test_listener_4_xml());

//        hideBtns();
        showBtns();
//        showCurrentTest();
    }

    @Override
    protected boolean isNeedCheckPermission() {
        return false;
    }


    private void test_listener_4_inner_class() {
        showFragment(new InnerClassListenFragment());
    }

    private void test_listener_4_outer_class() {
        showFragment(new OuterClassListenFragment());
    }

    private void test_listener_4_activity_itself() {
        showActivity(ActivityItselfListenerActivity.class);
    }

    private void test_listener_4_anonymous_inner_class() {
        showFragment(new AnonymousInnerClassListenFragment());
    }

    private void test_listener_4_xml() {
        showActivity(XmlListenerActivity.class);
    }
}
