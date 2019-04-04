package com.example.hades.androidpo._1_render_op.layout._reduce_hierachy;

import android.os.Bundle;

import com.example.hades.androidpo.BaseActivity;
import com.example.hades.androidpo.R;

/**
 * Remove unneeded Window default background
 */
public class WindowNoTitleActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.use_window_no_title_to_reduce_hierarchy_layout);
        initViews();
    }
}