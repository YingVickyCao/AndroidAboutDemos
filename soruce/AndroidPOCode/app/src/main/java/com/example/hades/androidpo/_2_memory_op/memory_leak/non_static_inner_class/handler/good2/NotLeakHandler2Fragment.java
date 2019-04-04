package com.example.hades.androidpo._2_memory_op.memory_leak.non_static_inner_class.handler.good2;

import android.app.Fragment;
import android.os.Message;

public class NotLeakHandler2Fragment extends Fragment {

    // OuterClass +  WeakReference/ StrongReference
    private CustomHandler mCustomHandler = new CustomHandler(this);

    public void showProgressBar() {
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