package com.hades.example.android.media.audio._sound_pool;

import android.app.Activity;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.hades.example.android.R;

import java.util.HashMap;


public class TestSoundPoolActivity extends Activity implements View.OnClickListener {

    Button bomb, shot, arrow;
    // 定义一个SoundPool
    SoundPool soundPool;
    HashMap<Integer, Integer> soundMap = new HashMap<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.media_audio_soundpool);
        bomb = (Button) findViewById(R.id.bomb);
        shot = (Button) findViewById(R.id.shot);
        arrow = (Button) findViewById(R.id.arrow);
        AudioAttributes attr = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME) // 设置音效使用场景
                // 设置音效的类型
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .build();
        soundPool = new SoundPool.Builder()
                .setAudioAttributes(attr) // 设置音效池的属性
                .setMaxStreams(10) // 设置最多可容纳10个音频流，
                .build();  // ①
        // load方法加载指定音频文件，并返回所加载的音频ID
        // 此处使用HashMap来管理这些音频流
        soundMap.put(1, soundPool.load(this, R.raw.mp3_1, 1));  // ②
        soundMap.put(2, soundPool.load(this, R.raw.mp3_2, 1));
        soundMap.put(3, soundPool.load(this, R.raw.mp3_3, 1));
        bomb.setOnClickListener(this);
        shot.setOnClickListener(this);
        arrow.setOnClickListener(this);
    }

    // 重写OnClickListener监听器接口的方法
    @Override
    public void onClick(View source) {
        // 判断哪个按钮被单击
        switch (source.getId()) {
            case R.id.bomb:
                soundPool.play(soundMap.get(1), 1, 1, 0, 0, 1);  // ③
                break;
            case R.id.shot:
                soundPool.play(soundMap.get(2), 1, 1, 0, 0, 1);
                break;
            case R.id.arrow:
                soundPool.play(soundMap.get(3), 1, 1, 0, 0, 1);
                break;
        }
    }
}