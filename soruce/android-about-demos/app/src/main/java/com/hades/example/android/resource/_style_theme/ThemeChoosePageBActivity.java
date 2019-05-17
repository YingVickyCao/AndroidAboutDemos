package com.hades.example.android.resource._style_theme;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.hades.example.android.R;

public class ThemeChoosePageBActivity extends ThemeChoosePageAActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        findViewById(R.id.jumpB).setVisibility(View.GONE);
        ((TextView) findViewById(R.id.id)).setText(R.string.page_b);
    }
}