package com.hades.example.android.widget.splash_notify_icon;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.Nullable;

import com.hades.example.android.R;


/**
 * Animation
 * https://developer.android.google.cn/guide/topics/resources/animation-resource#Interpolators
 * https://www.cnblogs.com/whoislcj/p/5738478.html
 * https://blog.csdn.net/liaoyi_/article/details/53542568
 * https://blog.csdn.net/zenmela2011/article/details/42495263
 * https://blog.csdn.net/love_xsq/article/details/50126947
 * https://www.cnblogs.com/pzyoung/p/4268702.html
 * https://www.cnblogs.com/shuiyin/p/6560010.html
 * <p>
 * <p>
 * Drawable
 * https://blog.csdn.net/mkosto/article/details/52395996
 * https://blog.csdn.net/linghu_java/article/details/46403817
 * https://www.jianshu.com/p/ff634a3ce107
 * https://blog.csdn.net/shibin1990_/article/details/51602498
 */

/**
 * Splash 3 times
 * Type 1 : = > Animation, alpha .
 * 这种方式在有些手机上有问题，多次点击后，再点击就没有反映了。
 */
public class SplashNotifyIconActivity extends Activity implements View.OnClickListener {
    private static final String TAG = SplashNotifyIconActivity.class.getSimpleName();

    //    ImageView image;
    View notifyContainer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash_notify_icon_layout);

        findViewById(R.id.startSplashNotifyIcon).setOnClickListener(this);
        findViewById(R.id.changeViewVisibility).setOnClickListener(this);
        findViewById(R.id.stopSplashNotifyIcon).setOnClickListener(this);
//        image = findViewById(R.id.image);
        notifyContainer = findViewById(R.id.notifyContainer);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.startSplashNotifyIcon:
                startSplashNotifyIcon();
                break;

            case R.id.changeViewVisibility:
                changeViewVisibility();
                break;

            case R.id.stopSplashNotifyIcon:
                stopSplashNotifyIcon();
                break;
        }
    }

    private void startSplashNotifyIcon() {
        Animation animation = getAnimation();
        /**
         * ERROR：many times setAnimation， but only first time is work.
         * Solution:
         * setAnimation(animation)
         * =>
         * startAnimation(animation)
         *
         */
        //
        notifyContainer.startAnimation(animation);
//        image.startAnimation(animation);
    }

    private void stopSplashNotifyIcon() {
        notifyContainer.clearAnimation();
//        image.clearAnimation();
    }

    private int getAnimationId() {
        return R.anim.splash_animation_set;
    }

    private Animation getAnimation() {
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), getAnimationId());
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                Log.d(TAG, "onAnimationStart: ");

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Log.d(TAG, "onAnimationEnd: ");
                stopSplashNotifyIcon();

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                Log.d(TAG, "onAnimationRepeat: ");
            }
        });
        return animation;
    }

    /**
     * ERROR:setAnimation 后，setVisibility 没有效果
     * 解决：
     * 方案1：android:fillAfter="true" -> false
     * 方案2：在setVisibility之前，设置View的mCurrentAnimation为null => onAnimationEnd(), invoke clearAnimation().
     * <p>
     * https://blog.csdn.net/liuhanhan512/article/details/42928669
     * https://blog.csdn.net/u011060103/article/details/52876683
     */
    private void changeViewVisibility() {
        if (View.GONE == notifyContainer.getVisibility()) {
            notifyContainer.setVisibility(View.VISIBLE);
        } else {
            notifyContainer.setVisibility(View.GONE);
        }
    }
}
