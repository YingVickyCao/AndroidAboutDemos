package com.hades.example.android.other_ui.manager_phone_desktop._app_widget.base;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.hades.example.android.R;

public class TestAppWidgetConfigureActivity extends Activity {
    private static final String TAG = TestAppWidgetConfigureActivity.class.getSimpleName();
    int mAppWidgetId;

    private static final String PREFS_NAME = "com.hades.example.android.other_ui.manager_phone_desktop._app_widget.base.COLOR";
    public static final String PREFS_KEY_COLOR = "COLOR";

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        Log.d(TAG, "onCreate: ");


        // Set the result to CANCELED.  This will cause the widget host to cancel out of the widget placement if they press the back button.
        setResult(RESULT_CANCELED);

        setContentView(R.layout.app_widget_config_4_base);


        // Bind the action for the save button.
        findViewById(R.id.red).setTag("RED");
        findViewById(R.id.red).setOnClickListener(this::setConfig);

        findViewById(R.id.green).setTag("GREEN");
        findViewById(R.id.green).setOnClickListener(this::setConfig);

        findViewById(R.id.blue).setTag("BLUE");
        findViewById(R.id.blue).setOnClickListener(this::setConfig);


        mAppWidgetId = getAppWidgetIdFromIntent();
        if (mAppWidgetId == AppWidgetManager.INVALID_APPWIDGET_ID) {
            finish();
        }
    }

    private int getAppWidgetIdFromIntent() {
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            return mAppWidgetId = extras.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
        }
        return AppWidgetManager.INVALID_APPWIDGET_ID;
    }

    private void setConfig(View view) {
        save(this, mAppWidgetId, (String) view.getTag());
//        updateAppWidget4Configuration();
        setResult();
    }

    private void updateAppWidget4Configuration() {
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        Log.d(TAG, "updateAppWidget4Configuration:appWidgetId=" + mAppWidgetId);
        AppWidgetProvider4Base.updateAppWidget(this, appWidgetManager, mAppWidgetId);
    }

    private void setResult() {
        Intent resultValue = new Intent();
        resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, mAppWidgetId);
        setResult(RESULT_OK, resultValue);
        finish();
    }

    static void save(Context context, int appWidgetId, String color) {
        SharedPreferences.Editor prefs = context.getSharedPreferences(PREFS_NAME, 0).edit();
        prefs.putString(PREFS_KEY_COLOR + appWidgetId, color);
        prefs.commit();
    }

    static String load(Context context, int appWidgetId) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
        return prefs.getString(PREFS_KEY_COLOR + appWidgetId, "GRAY");
    }
}