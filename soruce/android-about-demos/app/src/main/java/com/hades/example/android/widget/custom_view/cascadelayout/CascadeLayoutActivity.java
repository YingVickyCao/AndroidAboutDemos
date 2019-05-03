package com.hades.example.android.widget.custom_view.cascadelayout;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.hades.example.android.R;
import com.hades.example.android.lib.base.NoNeedPermissionActivity;

/**
 * Created by hades on 17/03/2018.
 * 扑克牌游戏中的玩家手牌
 */
public class CascadeLayoutActivity extends NoNeedPermissionActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.widget_customview_cascadelayout);

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
