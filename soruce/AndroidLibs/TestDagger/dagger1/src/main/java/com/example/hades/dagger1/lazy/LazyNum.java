package com.example.hades.dagger1.lazy;

import android.util.Log;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @injects 构造方法 + @Inject 对象名 + No module,  not added any module
 * New instance every each time.
 * @Singleton 无用
 */
@Singleton
public class LazyNum {
    private static final String TAG = LazyNum.class.getSimpleName();

    @Inject
    public LazyNum() {
        Log.d(TAG, "LazyNum: ");
    }

    public void num() {
        Log.d(TAG, "num: " + String.valueOf(System.currentTimeMillis()) + ",LazyNum hashCode=" + hashCode());
    }
}