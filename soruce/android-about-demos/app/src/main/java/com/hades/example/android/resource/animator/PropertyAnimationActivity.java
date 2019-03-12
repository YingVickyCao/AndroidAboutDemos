package com.hades.example.android.resource.animator;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.hades.example.android.android_about_demos.R;

/**
 * https://developer.android.google.cn/guide/topics/resources/animation-resource
 * https://developer.android.google.cn/guide/topics/graphics/prop-animation#choreography
 * https://developer.android.google.cn/reference/android/animation/ValueAnimator
 */
public class PropertyAnimationActivity extends Activity {
    private Button btn;
    private ProgressBar mProgressBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.res_property_animation);
        btn = findViewById(R.id.btn);
        mProgressBar = findViewById(R.id.progressBar);

        findViewById(R.id.objectAnimation).setOnClickListener(v -> objectAnimation_NoEndlessChangeBg());
        findViewById(R.id.animator).setOnClickListener(v -> animator());
//        findViewById(R.id.animator).setOnClickListener(v -> animator2());
//        findViewById(R.id.animator).setOnClickListener(v -> animator3());
    }

    private void objectAnimation_NoEndlessChangeBg() {
        ObjectAnimator colorAnim = (ObjectAnimator) AnimatorInflater.loadAnimator(this, R.animator.animation_property_4_bg_color);
        colorAnim.setEvaluator(new ArgbEvaluator());
        colorAnim.setTarget(btn);
        colorAnim.start();
    }

    /**
     * ValueAnimator
     * https://www.jianshu.com/p/2966227ea0b4
     */
    private void animator() {
        ValueAnimator valueAnimator = (ValueAnimator) AnimatorInflater.loadAnimator(this, R.animator.animation_animator);
        valueAnimator.addUpdateListener(animation ->
                mProgressBar.setProgress((Integer) animation.getAnimatedValue()));
        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                Toast.makeText(PropertyAnimationActivity.this, "动画开始", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                Toast.makeText(PropertyAnimationActivity.this, "动画结束", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                Toast.makeText(PropertyAnimationActivity.this, "动画取消", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                Toast.makeText(PropertyAnimationActivity.this, "动画重复", Toast.LENGTH_SHORT).show();
            }
        });
        valueAnimator.start();
    }

    private void animator2() {
        ValueAnimator valueAnimator = ValueAnimator.ofArgb(0xffffffff, 0xffff0000, 0xff0000ff);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                btn.setBackgroundColor((Integer) animation.getAnimatedValue());
            }
        });
        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                Toast.makeText(PropertyAnimationActivity.this, "动画开始", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                Toast.makeText(PropertyAnimationActivity.this, "动画结束", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                Toast.makeText(PropertyAnimationActivity.this, "动画取消", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                Toast.makeText(PropertyAnimationActivity.this, "动画重复", Toast.LENGTH_SHORT).show();
            }
        });
        valueAnimator.start();
    }

    private void animator3() {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0, 100);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mProgressBar.setProgress((Integer) animation.getAnimatedValue());
            }
        });
        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                Toast.makeText(PropertyAnimationActivity.this, "动画开始", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                Toast.makeText(PropertyAnimationActivity.this, "动画结束", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                Toast.makeText(PropertyAnimationActivity.this, "动画取消", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                Toast.makeText(PropertyAnimationActivity.this, "动画重复", Toast.LENGTH_SHORT).show();
            }
        });
        valueAnimator.start();
    }
}