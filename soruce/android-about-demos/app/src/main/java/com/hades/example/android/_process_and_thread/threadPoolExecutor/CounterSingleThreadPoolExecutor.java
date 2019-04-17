package com.hades.example.android._process_and_thread.threadPoolExecutor;

import android.util.Log;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class CounterSingleThreadPoolExecutor {
    private static final String TAG = CounterSingleThreadPoolExecutor.class.getSimpleName();
    private ScheduledExecutorService mExecutorService;
    private ScheduledFuture mFuture;
    private Runnable mRunnable;

    private int mMax;
    private int mCount;
    private IProgressListener mProgressListener;

    public CounterSingleThreadPoolExecutor(int max, IProgressListener progressListener) {
        this.mMax = max;
        this.mProgressListener = progressListener;
    }

    public void setProgressListener(IProgressListener progressListener) {
        mProgressListener = progressListener;
    }

    public void start() {
        mCount = 0;
        if (null == mProgressListener) {
            return;
        }
        if (null == mExecutorService) {
            mExecutorService = Executors.newSingleThreadScheduledExecutor();
            Log.d(TAG, "start: ExecutorService@" + mExecutorService.hashCode());
        }

        initRunnable();

        if (null == mFuture || mFuture.isCancelled()) {
            mFuture = mExecutorService.scheduleAtFixedRate(mRunnable, 0, 1, TimeUnit.SECONDS);
        }
    }

    private void initRunnable() {
        if (null != mRunnable) {
            return;
        }

        mRunnable = new Runnable() {
            @Override
            public void run() {
                for (int i = (0 == mCount ? 0 : mCount - 1); i < mMax; i++) {
                    if (mCount >= mMax) {
                        if (null != mProgressListener) {
                            mProgressListener.update(String.valueOf(mCount));
                        }
                        break;
                    }
                    if (null == mProgressListener) {
                        mCount = 0;
                        break;
                    }
                    mCount++;
                    if (null != mRunnable) {
                        Log.d(TAG, "initRunnable: count=" + mCount + ",Runnable@" + mRunnable.hashCode());
                    }

                    if (mCount % 9 == 0) {
                        mProgressListener.update(String.valueOf(mCount));
                    }
                    if (null == mProgressListener) {
                        return;
                    }
//                try {
//                    Thread.currentThread().sleep(1000);
//                } catch (InterruptedException e) {
//                    // ERROR:run: java.lang.InterruptedException
//                    Log.e(TAG, "run: " + e);
//                }
                }
            }
        };
    }

    public void cancel() {
        if (null != mFuture && !mFuture.isCancelled()) {
            try {
                mFuture.cancel(true);
            } catch (Exception ex) {
                Log.d(TAG, "cancel: " + ex);
            }
        }

        mCount = 0;
        if (null != mProgressListener) {
            mProgressListener.update("cancel");
        }
        mProgressListener = null;
    }

    public void pause() {
        if (null != mFuture && !mFuture.isCancelled()) {
            mFuture.cancel(true);
        }

        if (null != mProgressListener) {
            mProgressListener.update("pause");
        }

        mProgressListener = null;
    }

    public void end() {
//        mCount = 0;
        if (null != mRunnable) {
            Log.d(TAG, "end: Runnable-[1] set null");
        } else {
            Log.d(TAG, "end: Runnable-[2] is already null");
        }


        if (null != mFuture) {
            if (!mFuture.isCancelled()) {
                mFuture.cancel(true);
                Log.d(TAG, "end: Future-[1] canceled && null");
            } else {
                Log.d(TAG, "end: Future-[2] set null");
            }
            mFuture = null;

        } else {
            Log.d(TAG, "end: Future [3] is already null");
        }

        if (null != mExecutorService) {
            // TODO: 2019/3/7 Not work: not shutdown, I don not know why.
            mExecutorService.shutdownNow();
//            if (!mExecutorService.isShutdown()) {
//                mExecutorService.shutdownNow();
//                Log.d(TAG, "end: ExecutorService-[1] shutdownNow && null");
//            } else {
//                Log.d(TAG, "end: ExecutorService-[2] set null");
//            }
            mExecutorService = null;
        } else {
            Log.d(TAG, "end: ExecutorService-[3] is already null");
        }

        if (null != mProgressListener) {
            mProgressListener.update("");
//            mProgressListener = null;
        }
    }
}