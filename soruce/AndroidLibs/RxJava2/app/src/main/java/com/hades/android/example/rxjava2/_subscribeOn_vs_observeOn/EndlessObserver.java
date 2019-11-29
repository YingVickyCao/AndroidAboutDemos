package com.hades.android.example.rxjava2._subscribeOn_vs_observeOn;

import android.util.Log;

import io.reactivex.observers.DisposableObserver;
import timber.log.Timber;

public class EndlessObserver<T> extends DisposableObserver<T> {
    private static final String TAG = "EndlessObserver";

    @Override
    public void onNext(T t) {

    }

    @Override
    public void onError(Throwable e) {
        Log.d(TAG, "onError: ");
        Timber.e(e);
    }

    @Override
    public void onComplete() {

    }
}
