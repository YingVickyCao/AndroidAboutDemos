package com.hades.android.example.android_about_demos.resource.material;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.os.Bundle;

import com.hades.android.example.android_about_demos.R;

import java.io.IOException;

public class MaterialActivity extends Activity {
    MediaPlayer mediaPlayer1 = null;
    MediaPlayer mediaPlayer2 = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.res_material);

        mediaPlayer1 = MediaPlayer.create(this, R.raw.msg);

        try {
            AssetManager am = getAssets();
            // 获取指定文件对应的AssetFileDescriptor
            AssetFileDescriptor afd = am.openFd("shot.mp3");
            mediaPlayer2 = new MediaPlayer();
            // 使用MediaPlayer加载指定的声音文件
            mediaPlayer2.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getStartOffset());
            mediaPlayer2.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

        findViewById(R.id.playRaw).setOnClickListener(v -> playRaw());
        findViewById(R.id.playAsserts).setOnClickListener(arg0 -> playAsserts());
    }

    private void playRaw() {
        mediaPlayer1.start();
    }

    private void playAsserts() {
        // TODO: 03/07/2018 没有声音
        mediaPlayer2.start();
    }
}