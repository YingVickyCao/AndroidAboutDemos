package com.hades.example.android.app_component._fragment;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.hades.example.android.R;
import com.hades.example.android.lib.base.BaseActivity;

public class TestFragmentActivity extends BaseActivity {

    View fragmentContainer1;
    View fragmentContainer2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fragment_container);

        fragmentContainer1 = findViewById(R.id.fragmentContainer1);
        fragmentContainer2 = findViewById(R.id.fragmentContainer2);

        showFragment(new Fragment1(), R.id.fragmentContainer1);
        showFragment(new Fragment2(), R.id.fragmentContainer2);

        findViewById(R.id.showFragment1).setOnClickListener(v -> {
            fragmentContainer1.setVisibility(View.VISIBLE);
            fragmentContainer2.setVisibility(View.GONE);
        });

        findViewById(R.id.showFragment2).setOnClickListener(v -> {
            fragmentContainer1.setVisibility(View.GONE);
            fragmentContainer2.setVisibility(View.VISIBLE);
        });
    }
}