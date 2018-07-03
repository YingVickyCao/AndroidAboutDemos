package com.hades.android.example.android_about_demos.resource.theme.apply_theme;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.hades.android.example.android_about_demos.R;

public class ThemeChoosePageBActivity extends ThemeChoosePageAActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        findViewById(R.id.jumpB).setVisibility(View.GONE);
        ((TextView) findViewById(R.id.title)).setText(R.string.page_b);
    }
}