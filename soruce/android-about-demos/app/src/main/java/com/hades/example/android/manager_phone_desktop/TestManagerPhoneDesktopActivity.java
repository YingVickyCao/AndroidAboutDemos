package com.hades.example.android.manager_phone_desktop;

import android.Manifest;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.hades.example.android.R;
import com.hades.example.android.lib.base.RxPermissionsActivity;
import com.hades.example.android.manager_phone_desktop.static_wallpaper.TestStaticWallpaperFragment;

public class TestManagerPhoneDesktopActivity extends RxPermissionsActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manager_phone_desktop);

        initViews();

        findViewById(R.id.page_SetStaticWallpaper).setOnClickListener(v -> page_SetStaticWallpaper());
    }

    @Override
    protected void requestPermission() {
        checkPermission("Request permission for Set Wallpaper", Manifest.permission.SET_WALLPAPER);
    }

    private void page_SetStaticWallpaper() {
        showFragment(new TestStaticWallpaperFragment());
    }
}