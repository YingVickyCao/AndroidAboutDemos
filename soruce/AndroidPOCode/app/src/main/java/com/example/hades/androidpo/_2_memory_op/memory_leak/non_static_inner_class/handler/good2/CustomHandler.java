package com.example.hades.androidpo._2_memory_op.memory_leak.non_static_inner_class.handler.good2;

import android.os.Handler;
import android.os.Message;

import java.lang.ref.WeakReference;

public class CustomHandler extends Handler {
    private WeakReference<NotLeakHandler2Fragment> mReference;

    public CustomHandler(NotLeakHandler2Fragment reference) {
        mReference = new WeakReference<>(reference);
    }

    @Override
    public void handleMessage(Message msg) {
        if (null == mReference) {
            return;
        }
        // what?
        mReference.get().showProgressBar();
    }

    public void clear() {
        if (null != mReference) {
            mReference.clear();
            mReference = null;
        }
    }
}