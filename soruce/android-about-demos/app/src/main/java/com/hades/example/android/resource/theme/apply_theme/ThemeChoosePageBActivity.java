package com.hades.example.android.resource.theme.apply_theme;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.hades.example.android.R;

public class ThemeChoosePageBActivity extends ThemeChoosePageAActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        findViewById(R.id.jumpB).setVisibility(View.GONE);
        ((TextView) findViewById(R.id.id)).setText(R.string.page_b);
    }
}