package com.hades.example.android.media;

import android.os.Handler;
import android.os.Message;

// 使用handler实现简单的定时器，主线程每秒刷新UI
public class MediaPlayerHandler extends Handler {
    private IMediaPlayView mIMediaPlayView;
    private static final int UPDATE_MEDIA_PLAY_PROGRESS = 1;

    void setIMediaPlayView(IMediaPlayView mIMediaPlayView) {
        this.mIMediaPlayView = mIMediaPlayView;
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);

        if (msg.what == UPDATE_MEDIA_PLAY_PROGRESS) {
            handleMessage4UpdateMediaPlayProgress();
        }
    }

    private void handleMessage4UpdateMediaPlayProgress() {
        removeMessages(UPDATE_MEDIA_PLAY_PROGRESS);

        if (null != mIMediaPlayView) {
            mIMediaPlayView.updateProgress();
            sendMessage4UpdateMediaPlayProgress();
        }
    }

    void sendMessage4UpdateMediaPlayProgress(){
        sendEmptyMessageDelayed(UPDATE_MEDIA_PLAY_PROGRESS, 1000);
    }
}
