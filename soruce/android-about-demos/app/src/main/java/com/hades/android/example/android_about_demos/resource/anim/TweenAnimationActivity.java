package com.hades.android.example.android_about_demos.resource.anim;

import android.app.Activity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.hades.android.example.android_about_demos.R;

public class TweenAnimationActivity extends Activity {
    private ImageView image;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.res_tween_animation);
        image = findViewById(R.id.imageView);

        findViewById(R.id.alpha).setOnClickListener(v -> alpha());
        findViewById(R.id.scale).setOnClickListener(v -> scale());
        findViewById(R.id.translate).setOnClickListener(v -> translate());
        findViewById(R.id.rotate).setOnClickListener(v -> rotate());
        findViewById(R.id.combineAnim).setOnClickListener(v -> combineAnim());
    }

    /**
     * https://developer.android.google.cn/guide/topics/resources/animation-resource
     * <p>
     * https://www.cnblogs.com/dongweiq/p/6863521.html
     * https://www.cnblogs.com/carbs/p/5302908.html
     * <p>
     * LayerAnimation
     * https://blog.csdn.net/witernie/article/details/55050965
     * <p>
     * AlphaAnimation
     * https://www.cnblogs.com/sishuiliuyun/p/3167581.html
     */
    private void alpha() {
        final Animation anim = AnimationUtils.loadAnimation(TweenAnimationActivity.this, R.anim.anim_alpha);
        // 设置动画结束后保留结束状态
        anim.setFillAfter(false);
        image.startAnimation(anim);
    }

    private void scale() {
        final Animation anim = AnimationUtils.loadAnimation(TweenAnimationActivity.this, R.anim.anim_scale);
        anim.setFillAfter(false);
        image.startAnimation(anim);
    }

    private void translate() {
        final Animation anim = AnimationUtils.loadAnimation(TweenAnimationActivity.this, R.anim.anim_translate);
        anim.setFillAfter(false);
        image.startAnimation(anim);
    }

    private void rotate() {
        final Animation anim = AnimationUtils.loadAnimation(TweenAnimationActivity.this, R.anim.anim_rotate);
        anim.setFillAfter(false);
        image.startAnimation(anim);
    }

    private void combineAnim() {
        final Animation anim = AnimationUtils.loadAnimation(TweenAnimationActivity.this, R.anim.anim_rotate);
        anim.setFillAfter(false);
        image.startAnimation(anim);
    }
}