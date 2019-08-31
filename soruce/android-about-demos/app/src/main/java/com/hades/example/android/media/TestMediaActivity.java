package com.hades.example.android.media;

import android.Manifest;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.hades.example.android.R;
import com.hades.example.android.lib.base.PermissionActivity;
import com.hades.example.android.media.audio._audio_effect.TestAudioEffectActivity;
import com.hades.example.android.media.audio._media_player.TestMediaPlayer4AudioFragment;
import com.hades.example.android.media.audio._sound_pool.TestSoundPoolFragment;

public class TestMediaActivity extends PermissionActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.media_layout);
        initViews();

        findViewById(R.id.pageMediaPlayerPlayAudio).setOnClickListener(v -> pageMediaPlayerPlayAudio());
        findViewById(R.id.pageAudioEffect).setOnClickListener(v -> pageAudioEffect());
        findViewById(R.id.pageSoundPool4Audio).setOnClickListener(v -> pageSoundPool4Audio());
    }

    @Override
    protected void requestPermission() {
        checkPermission("Request permission for operate storage", Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE);
    }

    @Override
    protected void showCurrentTest() {
        pageSoundPool4Audio();
    }

    private void pageMediaPlayerPlayAudio() {
        showFragment(new TestMediaPlayer4AudioFragment());
    }

    private void pageAudioEffect() {
        showActivity(TestAudioEffectActivity.class);
    }

    private void pageSoundPool4Audio() {
        showFragment(new TestSoundPoolFragment());
    }
}