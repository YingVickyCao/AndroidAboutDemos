package com.hades.android.example.android_about_demos.widget.imageview;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.hades.android.example.android_about_demos.R;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.view.animation.AnimatorProxy;

import java.util.Random;

public class KenBurnsImageActivity extends Activity implements Animator.AnimatorListener {

    private static final int ANIM_COUNT = 4;
    private static final int[] PHOTOS = new int[]{R.drawable.photo1, R.drawable.photo2, R.drawable.photo3, R.drawable.photo4, R.drawable.photo5, R.drawable.photo6};
    private FrameLayout mContainer;
    private ImageView mView;
    private Random mRandom = new Random();
    private int mIndex = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mView = createNewView();

        // 创建布局容器
        mContainer = new FrameLayout(this);
        mContainer.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        mContainer.addView(mView);

        setContentView(mContainer);
    }

    private ImageView createNewView() {
//        ImageView ret = new ImageView(this);
        ImageView ret = (null == mView) ? new ImageView(this) : mView;
        ret.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        ret.setScaleType(ImageView.ScaleType.FIT_XY);
        // 设置要显示的图片，并设置下一个要显示的图片的索引
        ret.setImageResource(PHOTOS[mIndex]);
        mIndex = (mIndex + 1 < PHOTOS.length) ? mIndex + 1 : 0;
        return ret;
    }

    @Override
    protected void onResume() {
        super.onResume();
        nextAnimation();
    }

    private void nextAnimation() {
        AnimatorSet anim = new com.nineoldandroids.animation.AnimatorSet();
        final int index = mRandom.nextInt(ANIM_COUNT);

        switch (index) {
            // 缩放动画
            case 0:
                anim.playTogether(
                        com.nineoldandroids.animation.ObjectAnimator.ofFloat(mView, "scaleX", 1.5f, 1f),
                        com.nineoldandroids.animation.ObjectAnimator.ofFloat(mView, "scaleY", 1.5f, 1f));
                break;

            case 1:
                anim.playTogether(
                        com.nineoldandroids.animation.ObjectAnimator.ofFloat(mView, "scaleX", 1, 1.5f),
                        com.nineoldandroids.animation.ObjectAnimator.ofFloat(mView, "scaleY", 1, 1.5f));
                break;

            case 2:
                AnimatorProxy.wrap(mView).setScaleX(1.5f);
                AnimatorProxy.wrap(mView).setScaleY(1.5f);
                anim.playTogether(com.nineoldandroids.animation.ObjectAnimator.ofFloat(mView,
                        "translationY", 80f, 0f));
                break;

            // 位移动画
            case 3:
            default:
                AnimatorProxy.wrap(mView).setScaleX(1.5f);
                AnimatorProxy.wrap(mView).setScaleY(1.5f);
                anim.playTogether(com.nineoldandroids.animation.ObjectAnimator.ofFloat(mView,
                        "translationX", 0f, 40f));
                break;
        }

        // 设置动画持续时间
        anim.setDuration(3000);
        anim.addListener(this);
        anim.start();
    }

    @Override
    public void onAnimationCancel(com.nineoldandroids.animation.Animator arg0) {
    }

    @Override
    public void onAnimationEnd(com.nineoldandroids.animation.Animator animator) {
        mContainer.removeView(mView);
        mView = createNewView();
        mContainer.addView(mView);
        nextAnimation();
    }

    @Override
    public void onAnimationRepeat(com.nineoldandroids.animation.Animator arg0) {
    }

    @Override
    public void onAnimationStart(com.nineoldandroids.animation.Animator arg0) {
    }
}
