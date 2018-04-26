package com.hades.android.example.android_about_demos.app_component.splash_notify_icon;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.hades.android.example.android_about_demos.R;


/**
 * https://blog.csdn.net/liaoyi_/article/details/53542568
 */
public class SplashNotifyIconActivity extends Activity implements View.OnClickListener {
    ImageView image;
    View notifyContainer;
    Button startSplashNotifyIcon;
    Button stopSplashNotifyIcon;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash_notify_icon_layout);

        startSplashNotifyIcon = findViewById(R.id.startSplashNotifyIcon);
        stopSplashNotifyIcon = findViewById(R.id.stopSplashNotifyIcon);
        image = findViewById(R.id.image);
        notifyContainer = findViewById(R.id.notifyContainer);

        startSplashNotifyIcon.setOnClickListener(this);
        stopSplashNotifyIcon.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.startSplashNotifyIcon:
                startSplashNotifyIcon();
                break;

            case R.id.stopSplashNotifyIcon:
                stopSplashNotifyIcon();
                break;
        }
    }

    private void startSplashNotifyIcon() {
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), getAnimationId());
        notifyContainer.setAnimation(animation);
        image.startAnimation(animation);
//        startSplashNotifyIcon.setAnimation(animation);
        animation.start();
    }

    private void stopSplashNotifyIcon() {
        notifyContainer.clearAnimation();
        image.clearAnimation();
//        startSplashNotifyIcon.clearAnimation();
    }

    private int getAnimationId() {
        return R.anim.splash_animation_set;
    }
}
