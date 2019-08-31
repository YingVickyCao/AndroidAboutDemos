package com.hades.example.android.widget._surfaceview;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.hades.example.android.R;

import java.io.IOException;

public class TestSurfaceViewPlayVideoFragment extends Fragment implements SurfaceHolder.Callback, MediaPlayer.OnPreparedListener {

    private SurfaceView surfaceView;
    private View mView;

    private MediaPlayer mPlayer;
    private int currentPosition;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.media_video_surface_view, container, false);

        view.findViewById(R.id.play).setOnClickListener(v -> onClickPlay());
        view.findViewById(R.id.pause).setOnClickListener(v -> onClickPause());
        view.findViewById(R.id.stopRecord).setOnClickListener(v -> onClickStop());
        surfaceView = view.findViewById(R.id.surfaceView);
        mView = view;
        enableBtns(false);

        mPlayer = new MediaPlayer();
        mPlayer.setOnPreparedListener(this);
        onClickPlay();
        return view;
    }

    private void onClickPlay() {
        try {
            play();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void onClickPause() {
        if (mPlayer.isPlaying()) {
            mPlayer.pause();
        } else {
            mPlayer.start();
        }
    }

    private void onClickStop() {
        if (mPlayer.isPlaying()) {
            mPlayer.stop();
        }
    }

    private void play() throws IOException {
        mPlayer.reset();
        mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        // 设置需要播放的视频
        mPlayer.setDataSource(getActivity(), Uri.parse("android.resource://" + getContext().getPackageName() + "/" + R.raw.mp4_1));
        mPlayer.prepareAsync();
    }

    private void adjustSurfaceViewSize() {
        // 获取窗口管理器
        WindowManager wManager = getActivity().getWindowManager();
        DisplayMetrics metrics = new DisplayMetrics();
        // 获取屏幕大小
        wManager.getDefaultDisplay().getMetrics(metrics);
        // 设置视频保持纵横比缩放到占满整个屏幕
        surfaceView.setLayoutParams(new RelativeLayout.LayoutParams(metrics.widthPixels, mPlayer.getVideoHeight() * metrics.widthPixels / mPlayer.getVideoWidth()));
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        mPlayer.start();
        enableBtns(true);
        adjustSurfaceViewSize();
        // 把视频画面输出到SurfaceView
        mPlayer.setDisplay(surfaceView.getHolder());  // ①
    }

    private void enableBtns(boolean enabled) {
        mView.findViewById(R.id.play).setEnabled(enabled);
        mView.findViewById(R.id.pause).setEnabled(enabled);
        mView.findViewById(R.id.stopRecord).setEnabled(enabled);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if (null == holder) {
            return;
        }
        holder.setKeepScreenOn(true);
        holder.addCallback(this);

        if (currentPosition > 0) {
            try {
                // 开始播放
                // 并直接从指定位置开始播放
                mPlayer.seekTo(currentPosition);
                currentPosition = 0;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        if (null == holder) {
            return;
        }
        holder.setKeepScreenOn(false);
        holder.addCallback(null);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        if (mPlayer.isPlaying()) {
            currentPosition = mPlayer.getCurrentPosition();
            mPlayer.pause();
        }
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        if (mPlayer.isPlaying()) {
            mPlayer.stop();
        }
        mPlayer.release();
        super.onDestroyView();
    }
}