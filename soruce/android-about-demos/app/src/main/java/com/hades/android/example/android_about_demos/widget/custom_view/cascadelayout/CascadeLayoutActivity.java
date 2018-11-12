package com.hades.android.example.android_about_demos.widget.custom_view.cascadelayout;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.hades.android.example.android_about_demos.R;
import com.hades.android.example.android_about_demos.base.ver1.BaseActivity;

/**
 * Created by hades on 17/03/2018.
 * 扑克牌游戏中的玩家手牌
 */
public class CascadeLayoutActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_cascadelayout_layout);

        initViews();

        findViewById(R.id.cascadeLayout1).setOnClickListener(v -> cascadeLayout1());
        findViewById(R.id.cascadeLayout2).setOnClickListener(v -> cascadeLayout2());
    }

    private void cascadeLayout1() {
        showFragment(new CascadeLayout1Fragment());
    }

    private void cascadeLayout2() {
        showFragment(new CascadeLayout2Fragment());
    }
}
