package com.hades.example.android.widget.layout.linearlayout;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.hades.example.android.R;
import com.hades.example.android.base.ver1.HasPermissionActivity;

public class LinearLayoutActivityActivity extends HasPermissionActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linearlayout_home_layout);
        initViews();
    }
}
