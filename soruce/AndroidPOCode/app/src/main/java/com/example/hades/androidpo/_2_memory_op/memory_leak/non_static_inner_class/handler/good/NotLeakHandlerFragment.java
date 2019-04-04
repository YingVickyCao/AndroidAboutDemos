package com.example.hades.androidpo._2_memory_op.memory_leak.non_static_inner_class.handler.good;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;

import java.lang.ref.WeakReference;

public class NotLeakHandlerFragment extends Fragment {
    private CustomHandler mCustomHandler;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mCustomHandler = new CustomHandler(this);
    }

    // static InnerClass +  WeakReference/ StrongReference
    static class CustomHandler extends Handler {
        private WeakReference<NotLeakHandlerFragment> mReference;

        CustomHandler(NotLeakHandlerFragment reference) {
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

        void clear() {
            if (null != mReference) {
                mReference.clear();
                mReference = null;
            }
        }
    }

    private void showProgressBar() {
    }

    private void sendMsg() {
        if (null == mCustomHandler) {
            return;
        }

        Message message = Message.obtain();
        // fill message
        mCustomHandler.sendMessage(message);
    }

    private void sendMsg2() {
        if (null == mCustomHandler) {
            return;
        }
        mCustomHandler.sendEmptyMessage(100);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (null != mCustomHandler) {
            mCustomHandler.removeCallbacksAndMessages(null);
            mCustomHandler.clear();
            mCustomHandler = null;
        }
    }
}