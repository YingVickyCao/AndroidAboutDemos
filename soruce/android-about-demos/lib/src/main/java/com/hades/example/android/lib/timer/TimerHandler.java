package com.hades.example.android.lib.timer;

import android.os.Handler;
import android.os.Message;

// 使用handler实现简单的定时器，主线程每秒刷新UI
public class TimerHandler extends Handler {
    private ITimerView mITimerView;
    private static final int UPDATE_VIEW_EVERY_SECONDS = 1;

    public void setITimerView(ITimerView mITimerView) {
        this.mITimerView = mITimerView;
    }

    public void clear(){
        removeCallbacks(null);
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);

        if (msg.what == UPDATE_VIEW_EVERY_SECONDS) {
            handleMessage4UpdateView();
        }
    }

    private void handleMessage4UpdateView() {
        removeMessages(UPDATE_VIEW_EVERY_SECONDS);

        if (null != mITimerView) {
            mITimerView.updateView();
            sendMessage4UpdateView();
        }
    }

    public void sendMessage4UpdateView(){
        sendEmptyMessageDelayed(UPDATE_VIEW_EVERY_SECONDS, 1000);
    }
}
