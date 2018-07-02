package com.hades.android.example.android_about_demos.resource.anim;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.hades.android.example.android_about_demos.R;

public class AnimationDrawableActivity extends Activity {
    private ImageView image;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        image = (ImageView) findViewById(R.id.image);

        Button bn = (Button) findViewById(R.id.bn);
        bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // 加载动画资源
                final Animation anim = AnimationUtils.loadAnimation(AnimationDrawableActivity.this, R.anim.my_anim);
                // 设置动画结束后保留结束状态
                anim.setFillAfter(true);  // ①

                // 开始动画
                image.startAnimation(anim);
            }
        });

        findViewById(R.id.scale).setOnClickListener(v -> scale());
        findViewById(R.id.translate).setOnClickListener(v -> translate());
        findViewById(R.id.rotate).setOnClickListener(v -> rotate());
    }

    private void scale() {
        final Animation anim = AnimationUtils.loadAnimation(AnimationDrawableActivity.this, R.anim.anim_scale);
        // 设置动画结束后保留结束状态
        anim.setFillAfter(false);
        image.startAnimation(anim);
    }

    private void translate() {
        final Animation anim = AnimationUtils.loadAnimation(AnimationDrawableActivity.this, R.anim.anim_translate);
        anim.setFillAfter(false);
        image.startAnimation(anim);
    }

    private void rotate() {
        final Animation anim = AnimationUtils.loadAnimation(AnimationDrawableActivity.this, R.anim.anim_rotate);
        anim.setFillAfter(false);
        image.startAnimation(anim);
    }
}