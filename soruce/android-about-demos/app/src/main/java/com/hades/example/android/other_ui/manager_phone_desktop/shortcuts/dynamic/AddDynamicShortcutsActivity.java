package com.hades.example.android.other_ui.manager_phone_desktop.shortcuts.dynamic;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.hades.example.android.R;

import java.util.Arrays;

public class AddDynamicShortcutsActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.add_dynamic_shorts_layout);

        findViewById(R.id.createDynamicShortcuts).setOnClickListener(v -> createDynamicShortcuts());
    }

    private void createDynamicShortcuts() {
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.N_MR1) {
            return;
        }
        ShortcutManager shortcutManager = getSystemService(ShortcutManager.class);
//        ShortcutInfo shortcut = new ShortcutInfo.Builder(getApplicationContext(), "id1" + System.currentTimeMillis()) // add = ++i
        ShortcutInfo shortcut = new ShortcutInfo.Builder(getApplicationContext(), "id1") // add = 1;
                .setShortLabel("Website")
                .setLongLabel("Open the website")
                .setIcon(Icon.createWithResource(getApplicationContext(), R.drawable.ic_adjust_black_18dp))
                .setIntent(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.baidu.com")))
                .build();
        shortcutManager.setDynamicShortcuts(Arrays.asList(shortcut)); // 1,已经添加的各种类型的shortcut，均被删除。
//        shortcutManager.addDynamicShortcuts(Arrays.asList(shortcut)); // ++i
    }
}
