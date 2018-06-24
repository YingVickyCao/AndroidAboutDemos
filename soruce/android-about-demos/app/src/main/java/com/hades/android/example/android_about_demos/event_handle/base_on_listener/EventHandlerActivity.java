package com.hades.android.example.android_about_demos.event_handle.base_on_listener;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ScrollView;

import com.hades.android.example.android_about_demos.R;
import com.hades.android.example.android_about_demos.event_handle.base_on_listener.plane.PlaneViewActivity;

public class EventHandlerActivity extends Activity {
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

        hideBtns();
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
        testEventHandlerBaseOnListener4Plane();
    }

    private void showFragment(Fragment fragment) {
        hideBtns();
        getFragmentManager().beginTransaction().replace(R.id.fragmentRoot, fragment, fragment.getClass().getSimpleName()).commit();
    }

    private void showActivity(Class<?> dest) {
        startActivity(new Intent(this, dest));
    }

    private void testEventHandlerBaseOnListener() {
        showFragment(new EventHandlerBaseOnListenFragment());
    }

    private void testEventHandlerBaseOnListener4Plane() {
        showActivity(PlaneViewActivity.class);
    }

}
