package com.hades.example.android.widget.splash_notify_icon;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import com.hades.example.android.R;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Splash 3 times
 * Type 2 : = > 发射数据[1,6]， 延迟0s 执行，每隔1秒执行1次 => 调整时间频率
 */
public class SplashNotifyIconActivity2 extends Activity implements View.OnClickListener {
    private static final String TAG = SplashNotifyIconActivity2.class.getSimpleName();

    View notifyContainer;
    private Disposable mDisposable;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash_notify_icon_layout);

        findViewById(R.id.startSplashNotifyIcon).setOnClickListener(this);
        findViewById(R.id.changeViewVisibility).setOnClickListener(this);
        findViewById(R.id.stopSplashNotifyIcon).setOnClickListener(this);

        notifyContainer = findViewById(R.id.notifyContainer);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.startSplashNotifyIcon:
                startSplashNotiyIcon();
                break;

            case R.id.changeViewVisibility:
                changeViewVisibility();
                break;

            case R.id.stopSplashNotifyIcon:
                stopSplashNotifyIcon();
                break;
        }
    }

    private void startSplashNotiyIcon() {
        // 发射数据[1,6]， 延迟0s 执行，每隔1秒执行1次
//        Observable.intervalRange(1, 6, 1, 0, TimeUnit.SECONDS)
        // 调整时间频率
        Observable.intervalRange(1, 6, 0, 200, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mDisposable = d;
                    }

                    @Override
                    public void onNext(Long num) {
                        Log.d(TAG, "onNext: num=" + num);
                        if (notifyContainer.getVisibility() == View.VISIBLE) {
                            notifyContainer.setVisibility(View.GONE);
                        } else {
                            notifyContainer.setVisibility(View.VISIBLE);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        notifyContainer.setVisibility(View.GONE);
                    }
                });
    }

    private void stopSplashNotifyIcon() {
        if (null != mDisposable && !mDisposable.isDisposed()) {
            mDisposable.dispose();
            mDisposable = null;
        }
    }

    private void changeViewVisibility() {
        if (View.GONE == notifyContainer.getVisibility()) {
            notifyContainer.setVisibility(View.VISIBLE);
        } else {
            notifyContainer.setVisibility(View.GONE);
        }
    }
}
