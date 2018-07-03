package com.hades.android.example.android_about_demos.resource.animator;

import android.animation.AnimatorInflater;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

import com.hades.android.example.android_about_demos.R;

public class PropertyAnimationActivity extends Activity {
    private Button btn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.res_property_animation);
        btn = findViewById(R.id.btn);

        findViewById(R.id.objectAnimation).setOnClickListener(v -> objectAnimation_NoEndlessChangeBg());
    }

    /**
     * https://developer.android.google.cn/guide/topics/resources/animation-resource
     */
    private void objectAnimation_NoEndlessChangeBg() {
        ObjectAnimator colorAnim = (ObjectAnimator) AnimatorInflater.loadAnimator(this, R.animator.animation_property_4_bg_color);
        colorAnim.setEvaluator(new ArgbEvaluator());
        colorAnim.setTarget(btn);
        colorAnim.start();
    }
}