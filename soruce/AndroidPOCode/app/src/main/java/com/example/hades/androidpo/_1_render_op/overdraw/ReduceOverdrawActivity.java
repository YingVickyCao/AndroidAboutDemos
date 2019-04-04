package com.example.hades.androidpo._1_render_op.overdraw;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.hades.androidpo.BaseActivity;
import com.example.hades.androidpo.R;
import com.example.hades.androidpo._1_render_op.overdraw.custom_view.ReduceOverDrawInCustomViewFragment;
import com.example.hades.androidpo._1_render_op.overdraw.reduce_overdraw_layout.ReduceOverviewFragment;
import com.example.hades.androidpo._1_render_op.overdraw.reduce_transparency.ReduceTransparentAnimationsFragment;
import com.example.hades.androidpo._1_render_op.overdraw.reduce_transparency.ReduceTransparentImageViewFragment;

public class ReduceOverdrawActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reduce_overview);

        initViews();

        findViewById(R.id.reduceUnneededBackgrounds).setOnClickListener(v -> reduceUnneededBackgrounds());
        findViewById(R.id.reduceOverdrawInCustomView).setOnClickListener(v -> reduceOverdrawInCustomView());
        findViewById(R.id.reduceTransparentAnimations).setOnClickListener(v -> reduceTransparentAnimations());
        findViewById(R.id.reduceTransparentImageView).setOnClickListener(v -> reduceTransparentImageView());
    }

    private void reduceUnneededBackgrounds() {
        showFragment(new ReduceOverviewFragment());
    }

    private void reduceOverdrawInCustomView() {
        showFragment(new ReduceOverDrawInCustomViewFragment());
    }

    private void reduceTransparentAnimations() {
        showFragment(new ReduceTransparentAnimationsFragment());
    }

    private void reduceTransparentImageView() {
        showFragment(new ReduceTransparentImageViewFragment());
    }
}
