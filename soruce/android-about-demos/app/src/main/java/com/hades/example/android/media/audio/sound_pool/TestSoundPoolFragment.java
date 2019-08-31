package com.hades.example.android.media.audio.sound_pool;

import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.Bundle;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.hades.example.android.R;


public class TestSoundPoolFragment extends Fragment {
    SoundPool soundPool;
    SparseIntArray soundMap = new SparseIntArray(); // HashMap<Integer, Integer>  -> SparseIntArray

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.media_audio_soundpool, container, false);

        view.findViewById(R.id.audio_1).setOnClickListener(v -> audio_1());
        view.findViewById(R.id.audio_2).setOnClickListener(v -> audio_2());
        view.findViewById(R.id.audio_3).setOnClickListener(v -> audio_3());

        soundPool = new SoundPool.Builder()
                .setAudioAttributes(buildAudioAttributes()) // 设置音效池的属性
                .setMaxStreams(10) // 设置最多可容纳10个音频流，
                .build();  // ①

        // load方法加载指定音频文件，并返回所加载的音频ID
        // 此处使用HashMap来管理这些音频流
        soundMap.put(1, soundPool.load(getActivity(), R.raw.mp3_1, 1));  // ②
        soundMap.put(2, soundPool.load(getActivity(), R.raw.mp3_2, 1));
        soundMap.put(3, soundPool.load(getActivity(), R.raw.mp3_3, 1));
        return view;
    }

    private AudioAttributes buildAudioAttributes() {
        return new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_MEDIA) // 设置音效使用场景
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC) // 设置音效的类型
                .build();
    }

    private void audio_1() {
        switchAudio(1);
    }

    private void audio_2() {
        switchAudio(2);
    }

    private void audio_3() {
        switchAudio(3);
    }

    private void switchAudio(int key) {
        // rate = playback rate
        soundPool.play(soundMap.get(key), 1, 1, 0, 0, 1);  // ③
    }
}