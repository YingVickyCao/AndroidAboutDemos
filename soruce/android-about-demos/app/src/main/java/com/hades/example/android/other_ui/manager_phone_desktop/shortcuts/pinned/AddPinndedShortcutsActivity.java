package com.hades.example.android.other_ui.manager_phone_desktop.shortcuts.pinned;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.hades.example.android.R;
import com.hades.example.android.lib.base.PermissionActivity;

public class AddPinndedShortcutsActivity extends PermissionActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initViews();

        setContentView(R.layout.add_dynamic_shorts_layout);

        findViewById(R.id.createDynamicShortcuts).setOnClickListener(v -> createPinnedShortcuts());
    }

    @Override
    protected void requestPermission() {
//        checkPermission("Request permission for operate storage", Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE);
    }

    private void createPinnedShortcuts() {
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.O) {
            return;
        }

        ShortcutManager shortcutManager = this.getSystemService(ShortcutManager.class);
        //  Android >=26. But Support library APIs, Android >=7.1 (API level 25).
        if (shortcutManager.isRequestPinShortcutSupported()) {

            ShortcutInfo pinShortcutInfo = new ShortcutInfo.Builder(this, "my-pinned-shortcut")
                    .setShortLabel("Pinned ABC - Website")
                    .setLongLabel("Pinned - Open the website")
                    .setIcon(Icon.createWithResource(getApplicationContext(), R.drawable.placeholder))
                    .setIntent(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.baidu.com")))
                    .build(); // id is stable

            // Create the PendingIntent object only if your app needs to be notified that the user allowed the shortcut to be pinned. Note that, if the pinning operation fails, your app isn't notified.
            // We assume here that the app has implemented a method called createShortcutResultIntent() that returns a broadcast intent.
            Intent pinnedShortcutCallbackIntent = shortcutManager.createShortcutResultIntent(pinShortcutInfo);

            // Configure the intent so that your app's broadcast receiver gets the callback successfully.For details, see PendingIntent.getBroadcast().
            PendingIntent successCallback = PendingIntent.getBroadcast(this, 0, pinnedShortcutCallbackIntent, /* flags */ 0);
            shortcutManager.requestPinShortcut(pinShortcutInfo, successCallback.getIntentSender());
        }

    }
}
