package com.hades.android.example.android_about_demos.resource.i18n;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.hades.android.example.android_about_demos.R;

public class InternationalizationActivity extends Activity {
    TextView tvShow;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.res_internationalization);
        tvShow = (TextView) findViewById(R.id.show);
        // 设置文本框所显示的文本
        tvShow.setText(R.string.msg);
    }
}