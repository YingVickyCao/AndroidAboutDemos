package com.hades.example.android.manager_phone_desktop._app_widget.base;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.hades.example.android.R;

public class LaunchedAfterClickAppWidgetBtnActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_widget_launched_layout_after_click_app_widget_btn);
    }
}