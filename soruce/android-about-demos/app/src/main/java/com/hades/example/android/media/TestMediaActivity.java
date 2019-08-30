package com.hades.example.android.media;

import android.Manifest;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.hades.example.android.R;
import com.hades.example.android.lib.base.PermissionActivity;
import com.hades.example.android.media.audio.TestMediaPlayer4AudioFragment;

public class TestMediaActivity extends PermissionActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.media_layout);
        initViews();

        findViewById(R.id.pageMediaPlayerPlayAudio).setOnClickListener(v -> pageMediaPlayerPlayAudio());
    }

    @Override
    protected void requestPermission() {
        checkPermission("Request permission for operate storage", Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE);
    }

    @Override
    protected void showCurrentTest() {
        pageMediaPlayerPlayAudio();
    }

    private void pageMediaPlayerPlayAudio(){
        showFragment(new TestMediaPlayer4AudioFragment());
    }
}