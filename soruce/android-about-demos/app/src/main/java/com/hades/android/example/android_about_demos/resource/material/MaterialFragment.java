package com.hades.android.example.android_about_demos.resource.material;

import android.app.Fragment;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hades.android.example.android_about_demos.R;

import java.io.IOException;

public class MaterialFragment extends Fragment {
    MediaPlayer mediaPlayer1 = null;
    MediaPlayer mediaPlayer2 = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.res_material, container, false);

        view.findViewById(R.id.playRaw).setOnClickListener(v -> playRaw());
        view.findViewById(R.id.playAsserts).setOnClickListener(arg0 -> playAsserts());

        mediaPlayer1 = MediaPlayer.create(getActivity(), R.raw.msg);

        try {
            AssetManager am = getActivity().getAssets();
            // 获取指定文件对应的AssetFileDescriptor
            AssetFileDescriptor afd = am.openFd("shot.mp3");
            mediaPlayer2 = new MediaPlayer();
            // 使用MediaPlayer加载指定的声音文件
            mediaPlayer2.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getStartOffset());
            mediaPlayer2.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return view;
    }

    private void playRaw() {
        mediaPlayer1.start();
    }

    private void playAsserts() {
        // TODO: 03/07/2018 没有声音
        mediaPlayer2.start();
    }
}