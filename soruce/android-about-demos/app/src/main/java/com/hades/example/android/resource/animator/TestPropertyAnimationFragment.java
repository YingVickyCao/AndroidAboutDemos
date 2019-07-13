package com.hades.example.android.resource.animator;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.hades.example.android.R;

/**
 * https://developer.android.google.cn/guide/topics/resources/animation-resource
 * https://developer.android.google.cn/guide/topics/graphics/prop-animation#choreography
 * https://developer.android.google.cn/reference/android/animation/ValueAnimator
 */
public class TestPropertyAnimationFragment extends Fragment {
    private static final String TAG = TestPropertyAnimationFragment.class.getSimpleName();

    private Button btn;

    private Button btn2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.res_property_animation, container, false);

        btn = view.findViewById(R.id.btn);
        view.findViewById(R.id.bg_color_1_objectAnimation_xml).setOnClickListener(v -> bg_color_1_objectAnimation_xml());
        view.findViewById(R.id.bg_color_2_objectAnimation_java).setOnClickListener(v -> bg_color_2_objectAnimation_java());
        view.findViewById(R.id.bg_color_3_valueAnimation_java).setOnClickListener(v -> bg_color_3_valueAnimation_java());

        btn2 = view.findViewById(R.id.btn2);
        btn2.setOnClickListener(v -> Toast.makeText(getContext(), "Btn 2 clicked", Toast.LENGTH_SHORT).show());
        view.findViewById(R.id.xy_1_translation_xml).setOnClickListener(v -> xy_1_translation_xml());
        view.findViewById(R.id.xy_2_translation_AnimatorSet).setOnClickListener(v -> xy_2_translation_AnimatorSet());
        view.findViewById(R.id.xy_3_translation_PropertyValuesHolder).setOnClickListener(v -> xy_2_translation_PropertyValuesHolder());
        view.findViewById(R.id.moveOver).setOnClickListener(v -> moveOver());
        view.findViewById(R.id.moveBack).setOnClickListener(v -> moveBack());

        return view;
    }

    private void bg_color_1_objectAnimation_xml() {
        ObjectAnimator objectAnimator4Bg = (ObjectAnimator) AnimatorInflater.loadAnimator(getContext(), R.animator.property_animator_4_object_animation_4_bg_color);
        objectAnimator4Bg.setEvaluator(new ArgbEvaluator()); // 渐变，否则 一闪一闪
        objectAnimator4Bg.setTarget(btn);                       // 目标View
        setAnimatorListener(objectAnimator4Bg);
        objectAnimator4Bg.start();
    }

    private void bg_color_2_objectAnimation_java() {
        ObjectAnimator objectAnimator4Bg = ObjectAnimator.ofInt(btn, "backgroundColor", 0xffff8800, 0xffcc0000); // 目标View，View set 属性名称，开始值（int），结束值（int）
        objectAnimator4Bg.setDuration(3000);                    // 动画持续时间
        objectAnimator4Bg.setRepeatCount(0);                    // 动画重复的次数
        objectAnimator4Bg.setRepeatMode(ValueAnimator.REVERSE); // 播放模式
        objectAnimator4Bg.setEvaluator(new ArgbEvaluator());    // 渐变，否则 一闪一闪
//        objectAnimator4Bg.setStartDelay(1000);                // 动画延迟播放
        setAnimatorListener(objectAnimator4Bg);
        objectAnimator4Bg.start();                              // 开始运行动画
    }

    private void bg_color_3_valueAnimation_java() {
        ValueAnimator animator = ValueAnimator.ofInt(0xffff8800, 0xffcc0000); // 产生一个从0到100变化的整数的动画
        animator.setDuration(3000);
        animator.setRepeatCount(0);
        animator.setRepeatMode(ValueAnimator.REVERSE);
        animator.setEvaluator(new ArgbEvaluator());             // 渐变，否则 一闪一闪
        setAnimatorListener(animator);
        animator.addUpdateListener(animation -> {
            Integer value = (Integer) animation.getAnimatedValue(); // 动态的获取当前运行到的属性值
            btn.setBackgroundColor(value);
        });
        animator.start(); // 开始播放动画
    }

    private void xy_1_translation_xml() {
        Animator animator = AnimatorInflater.loadAnimator(getContext(), R.animator.property_animator_4_set_4_translation); // AnimatorSet
        animator.setTarget(btn2);
        setAnimatorListener(animator);
        animator.start();
    }

    private void xy_2_translation_AnimatorSet() {
        ObjectAnimator animatorX = ObjectAnimator.ofFloat(btn2, "translationX", 0f, 500f).setDuration(5000);
        ObjectAnimator animatorY = ObjectAnimator.ofFloat(btn2, "translationY", 0f, 200f).setDuration(2000);

        AnimatorSet set = new AnimatorSet(); // 相对于父容器， X、Y轴 先后移动
//        set.playSequentially(animatorX, animatorY);                                            // play x, play y
//        set.play(animator3).before(animator2).after(animator1).with(animator4);
//        set.play(animatorY).after(animatorX);                                                   // play x, play y
        set.playTogether(animatorX,animatorY);                                                    // play (x,y)
        setAnimatorListener(set);
//        set.setDuration(500);
//        set.setInterpolator(new BounceInterpolator());
        set.start();
    }

    private void xy_2_translation_PropertyValuesHolder() {
        PropertyValuesHolder holder1 = PropertyValuesHolder.ofFloat("translationX", 0f, 500f);
        PropertyValuesHolder holder2 = PropertyValuesHolder.ofFloat("translationY", 0f, 200f);
        ObjectAnimator.ofPropertyValuesHolder(btn2, holder1, holder2).setDuration(5000).start(); // play (x,y)
    }

    private void moveOver() {
        btn2.animate().x(500f).y(200f); // // play (x,y)
    }

    private void moveBack(){
        btn2.animate().x(0).y(0); // 按钮放回到它在容器中原来的位置 (0, 0)
    }

    private void setAnimatorListener(Animator animator) {
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                Log.d(TAG, " 动画开始");
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                Log.d(TAG, " 动画结束");
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                Log.d(TAG, " 动画取消");
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                Log.d(TAG, " 动画重复");
            }
        });

        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                Log.d(TAG, " 动画开始");
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                Log.d(TAG, " 动画结束");
            }

            // 其他两个事件可以选择不实现
        });

    }
}