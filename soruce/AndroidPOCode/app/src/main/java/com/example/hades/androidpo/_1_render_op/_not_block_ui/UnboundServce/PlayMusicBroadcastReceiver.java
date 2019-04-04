package com.example.hades.androidpo._1_render_op._not_block_ui.UnboundServce;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import static com.example.hades.androidpo._1_render_op._not_block_ui.UnboundServce.PLayMusicService.PLAY_MUSIC_ACTION_PROGRESS;
import static com.example.hades.androidpo._1_render_op._not_block_ui.UnboundServce.PLayMusicService.PLAY_MUSIC_VALUE_PROGRESS;

public class PlayMusicBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = PlayMusicBroadcastReceiver.class.getSimpleName();

    private IPlayMusic mPlayMusic;

    public PlayMusicBroadcastReceiver() {
    }

    public void setPlayMusic(IPlayMusic playMusic) {
        mPlayMusic = playMusic;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive: ");
        if (null == context || null == intent || null == intent.getAction()) {
            return;
        }
        switch (intent.getAction()) {
            case PLAY_MUSIC_ACTION_PROGRESS:
                updateProgress(intent);
                break;
        }
    }

    private void updateProgress(Intent intent) {
        int progress = intent.getIntExtra(PLAY_MUSIC_VALUE_PROGRESS, 0);
        if (null != mPlayMusic) {
            mPlayMusic.updateProgress(progress);
        }
    }
}