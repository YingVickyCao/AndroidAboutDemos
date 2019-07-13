package com.hades.example.android.resource.material;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.hades.example.android.R;
import com.hades.example.android.lib.base.BaseFragment;

import java.io.IOException;
import java.io.InputStream;

public class TestMaterialFragment extends BaseFragment {
    MediaPlayer mediaPlayer1 = null;
    MediaPlayer mediaPlayer2 = null;
    private ImageView mImageView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.res_material, container, false);

        mImageView = view.findViewById(R.id.imageView);

        view.findViewById(R.id.playRaw).setOnClickListener(v -> playRaw());
        view.findViewById(R.id.playAsserts).setOnClickListener(arg0 -> playAsserts());
        view.findViewById(R.id.loadAssertsImg).setOnClickListener(arg0 -> loadAssertsImg());

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
        mediaPlayer2.start();
    }

    private void loadAssertsImg() {
        InputStream is = null;
        try {
            is = getActivity().getAssets().open("play_fire.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Bitmap bitmap = BitmapFactory.decodeStream(is);
        mImageView.setImageBitmap(bitmap);

    }
}