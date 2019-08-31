package com.hades.example.android.media;

import android.Manifest;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.hades.example.android.R;
import com.hades.example.android.lib.base.PermissionActivity;
import com.hades.example.android.media.audio.audio_effect.TestAudioEffectActivity;
import com.hades.example.android.media.audio.media_player.TestMediaPlayer4AudioFragment;
import com.hades.example.android.media.audio.sound_pool.TestSoundPoolFragment;
import com.hades.example.android.media.camera.TestCameraActivity;
import com.hades.example.android.media.record.audio.TestRecordAudioFragment;
import com.hades.example.android.widget._surfaceview.TestSurfaceViewPlayVideoFragment;
import com.hades.example.android.widget._videoview.VideoViewRotateScreenTipActivity;

public class TestMediaActivity extends PermissionActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.media_layout);
        initViews();

        findViewById(R.id.pageMediaPlayerPlayAudio).setOnClickListener(v -> pageMediaPlayerPlayAudio());
        findViewById(R.id.pageAudioEffect).setOnClickListener(v -> pageAudioEffect());
        findViewById(R.id.pageSoundPoolPlayAudio).setOnClickListener(v -> pageSoundPool4Audio());

        findViewById(R.id.pageVideoViewPlayVideo).setOnClickListener(v -> pageVideoViewPlayVideo());
        findViewById(R.id.pageSurfaceViewPlayVideo).setOnClickListener(v -> pageSurfaceViewPlayVideo());

        findViewById(R.id.pageRecordAudio).setOnClickListener(v -> pageRecordAudio());

        findViewById(R.id.pageCamera).setOnClickListener(v -> pageCamera());
    }

    @Override
    protected void requestPermission() {
        checkPermission("Request permission for operate storage", Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE);
    }

    @Override
    protected void showCurrentTest() {
        pageRecordAudio();
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

    private void pageVideoViewPlayVideo() {
        showActivity(VideoViewRotateScreenTipActivity.class);
    }

    private void pageSurfaceViewPlayVideo() {
        showFragment(new TestSurfaceViewPlayVideoFragment());
    }

    private void pageRecordAudio() {
        showFragment(new TestRecordAudioFragment());
    }

    private void pageCamera() {
        showActivity(TestCameraActivity.class);
    }
}