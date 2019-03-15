package com.hades.example.android.app_component.service.system.audio_manager;

import android.app.Activity;
import android.app.Service;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.ToggleButton;

import com.hades.example.android.R;


public class AudioServiceActivity extends Activity {
    private AudioManager aManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_system_audio_service);

        aManager = (AudioManager) getSystemService(Service.AUDIO_SERVICE);

        findViewById(R.id.play).setOnClickListener(source -> play());
        findViewById(R.id.up).setOnClickListener(source -> up());
        findViewById(R.id.down).setOnClickListener(source -> down());
        ((ToggleButton) findViewById(R.id.mute)).setOnCheckedChangeListener((source, isChecked) -> mute(isChecked));
    }

    private void play() {
        MediaPlayer mPlayer = MediaPlayer.create(AudioServiceActivity.this, R.raw.msg);
        mPlayer.setLooping(false);
        mPlayer.start();
    }

    private void up() {
        aManager.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_RAISE, AudioManager.FLAG_SHOW_UI);
    }

    private void down() {
        aManager.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_LOWER, AudioManager.FLAG_SHOW_UI);
    }

    private void mute(boolean isChecked) {
        aManager.setStreamMute(AudioManager.STREAM_MUSIC, isChecked);
    }
}