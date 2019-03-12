package com.hades.example.android.event_handle.base_on_listener.event_listener_type;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ScrollView;

import com.hades.example.android.android_about_demos.R;
import com.hades.example.android.event_handle.base_on_listener.event_listener_type.activity_itself.ActivityItselfListenerActivity;
import com.hades.example.android.event_handle.base_on_listener.event_listener_type.anonymous_inner_class.AnonymousInnerClassListenFragment;
import com.hades.example.android.event_handle.base_on_listener.event_listener_type.inner_class.InnerClassListenFragment;
import com.hades.example.android.event_handle.base_on_listener.event_listener_type.outer_class.OuterClassListenFragment;
import com.hades.example.android.event_handle.base_on_listener.event_listener_type.xml.XmlListenerActivity;

public class EventListenerTypeActivity extends Activity {
    private ScrollView mScrollView;
    private View mFragmentRoot;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_listener_type);

        mScrollView = findViewById(R.id.scrollView);
        mFragmentRoot = findViewById(R.id.fragmentRoot);

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
    public void onBackPressed() {
        if (isShowDetail()) {
            showBtns();
            removeDetailFragment();
        } else {
            super.onBackPressed();
        }
    }

    private boolean isShowDetail() {
        return mFragmentRoot.getVisibility() == View.VISIBLE;
    }

    private void removeDetailFragment() {
        Fragment fragment = getFragmentManager().findFragmentById(R.id.fragmentRoot);
        getFragmentManager().beginTransaction().remove(fragment).commit();
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
        test_listener_4_activity_itself();
    }

    private void showFragment(Fragment fragment) {
        hideBtns();
        getFragmentManager().beginTransaction().replace(R.id.fragmentRoot, fragment, fragment.getClass().getSimpleName()).commit();
    }

    private void showActivity(Class<?> dest) {
        startActivity(new Intent(this, dest));
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
