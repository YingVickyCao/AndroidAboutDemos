package com.hades.example.android.other_ui.manager_phone_desktop._app_widget.base;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.hades.example.android.R;

import static com.hades.example.android.other_ui.manager_phone_desktop._app_widget.base.AppWidgetProvider4Base.convertColor;
import static com.hades.example.android.other_ui.manager_phone_desktop._app_widget.base.TestAppWidgetConfigureActivity.PREFS_KEY_COLOR;

public class LaunchedAfterClickAppWidgetBtnActivity extends Activity {
    private static final String TAG = LaunchedAfterClickAppWidgetBtnActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_widget_launched_layout_after_click_app_widget_btn);

        int appWidgetId = getIntent().getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
        Log.d(TAG, "onCreate:appWidgetId= " + appWidgetId);
        String color = getIntent().getStringExtra(PREFS_KEY_COLOR);
        Log.d(TAG, "onCreate:color= " + color);
        TextView textView = findViewById(R.id.info);
        textView.setBackgroundColor(convertColor(color));
    }
}