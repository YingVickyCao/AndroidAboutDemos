package com.example.hades.androidpo.monitor.ui.sampling;

import android.os.Handler;
import android.os.HandlerThread;

import com.example.hades.androidpo.monitor.ui.GLog;

import java.util.concurrent.atomic.AtomicBoolean;

public abstract class BaseSampler {
    private final String TAG = "BaseSampler";
    private Handler mControlHandler = null;
    private int intervalTime = 50; //ms�������
    private AtomicBoolean mIsSampling = new AtomicBoolean(false);

    public BaseSampler() {
        GLog.d(TAG, "Init BaseSampler");
    }

    public void start() {
        if (!mIsSampling.get()) {
            GLog.d(TAG, "start Sampler");
            getmControlHandler().removeCallbacks(mRunnable);
            getmControlHandler().post(mRunnable);
            mIsSampling.set(true);
        }
    }

    public void stop() {
        if (mIsSampling.get()) {
            GLog.d(TAG, "stop Sampler");
            getmControlHandler().removeCallbacks(mRunnable);
            mIsSampling.set(false);
        }
    }

    private Handler getmControlHandler() {
        if (null == mControlHandler) {
            HandlerThread mHT = new HandlerThread("SamplerThread");
            mHT.start();
            mControlHandler = new Handler(mHT.getLooper());
        }
        return mControlHandler;
    }

    abstract void doSample();

    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            doSample();
            if (mIsSampling.get()) {
                getmControlHandler().postDelayed(mRunnable, intervalTime);
            }
        }
    };
}
