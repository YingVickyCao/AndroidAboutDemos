package com.hades.example.android.other_ui.manager_phone_desktop;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.hades.example.android.R;
import com.hades.example.android.lib.base.BaseActivity;
import com.hades.example.android.other_ui.manager_phone_desktop.wallpaper.static_wallpaper.TestStaticWallpaperFragment;

public class TestManagerPhoneDesktopActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manager_phone_desktop);

        initViews();

        findViewById(R.id.page_SetStaticWallpaper).setOnClickListener(v -> page_SetStaticWallpaper());
    }

    private void page_SetStaticWallpaper() {
        showFragment(new TestStaticWallpaperFragment());
    }
}