package com.hades.example.android.media;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.media.TimedText;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.hades.example.android.R;
import com.hades.example.android.lib.base.BaseFragment;

import java.io.IOException;

public class TestMediaPlayerPlayAudioFragment extends BaseFragment implements IMediaPlayer, IMediaPlayView {
    private static final String TAG = TestMediaPlayerPlayAudioFragment.class.getName();

    MediaPlayer mMediaPlayer = null;
    SeekBar mProgress;
    TextView mCurrentTime;
    TextView mTotalTime;

    private MediaPlayerHandler mHandler;
    private MediaController mMediaController;
    private int mPosition4MediaFrom;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.media_mediaplayer_audio, container, false);

        mProgress = view.findViewById(R.id.playProgress);
        mCurrentTime = view.findViewById(R.id.currentTime);
        mTotalTime = view.findViewById(R.id.totalTime);

        ((Spinner) view.findViewById(R.id.mediaFrom)).setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mPosition4MediaFrom = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        view.findViewById(R.id.start).setOnClickListener(v -> start());
        view.findViewById(R.id.pause).setOnClickListener(v -> pause());
        view.findViewById(R.id.stop).setOnClickListener(v -> stop());

        mMediaController = new MediaController();

        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        if (null == mHandler) {
            mHandler = new MediaPlayerHandler();
            mHandler.setIMediaPlayView(this);
        }
        if (null != mHandler) {
            mHandler.setIMediaPlayView(this);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (null != mHandler) {
            mHandler.setIMediaPlayView(null);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mMediaController = null;
    }

    private void loadResourceRaw() {
        mMediaPlayer = MediaPlayer.create(getActivity(), R.raw.mp3_2);
        mMediaPlayer.setOnBufferingUpdateListener(this);
        mMediaPlayer.setOnCompletionListener(this);
        mMediaPlayer.setOnErrorListener(this);
        mMediaPlayer.setOnInfoListener(this);
        mMediaPlayer.setOnSeekCompleteListener(this);
        mMediaPlayer.setOnPreparedListener(this);
        mMediaPlayer.setOnTimedTextListener(this);
    }

    void loadMediaSource() {
        switch (mPosition4MediaFrom) {
            case 0:
                loadResourceRaw();
                break;

            case 1:
                loadAsserts();
                break;

            case 2:
                loadSD();
                break;

            case 3:
                loadNetwork();
                break;

            default:
                break;
        }
    }

    private AssetFileDescriptor assert2AssetFileDescriptor() {
        if (null == getActivity()) {
            return null;
        }
        AssetManager am = getActivity().getAssets();
        AssetFileDescriptor afd = null;
        try {
            afd = am.openFd("mp3.mp3");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return afd;
    }

    private void loadAsserts() {
        try {
            AssetFileDescriptor afd = assert2AssetFileDescriptor();
            if (null != afd) {
                mMediaPlayer = new MediaPlayer();
                mMediaPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getStartOffset());
                mMediaPlayer.prepare();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadSD() {

    }

    private void loadNetwork() {

    }

    private void startUpdateProgress() {
        if (null != mHandler) {
            mHandler.setIMediaPlayView(this);
            mHandler.sendMessage4UpdateMediaPlayProgress();
        }
    }

    private void stopUpdateProgress() {
        if (null != mHandler) {
            mHandler.setIMediaPlayView(null);
        }
    }

    private void start() {
        loadMediaSource();
        if (null == mMediaPlayer) {
            return;
        }
        mMediaPlayer.start();
        startUpdateProgress();
    }

    private void pause() {
        if (null == mMediaPlayer) {
            return;
        }
        mMediaPlayer.pause();
        stopUpdateProgress();
    }

    /**
     * ERROR:
     * stop() -> start()
     * E/MediaPlayerNative: start called in state 0, mPlayer(0x7be0799700)
     * E/MediaPlayerNative: error (-38, 0)
     * E/MediaPlayer: Error (-38,0)
     */
    private void stop() {
        if (null == mMediaPlayer){
            return;
        }
        stopUpdateProgress();
        mMediaPlayer.release();
        mMediaPlayer = null;
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        Log.d(TAG, "onCompletion: ");

    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        Log.d(TAG, "onError: " + what + ",extra=" + extra);
        return false;
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        Log.d(TAG, "onPrepared: ");
        if (null != mHandler) {
            mHandler.sendMessage4UpdateMediaPlayProgress();
        }
    }

    @Override
    public void onBufferingUpdate(MediaPlayer mp, int percent) {
        Log.d(TAG, "onBufferingUpdate: percent+" + percent);
    }

    @Override
    public boolean onInfo(MediaPlayer mp, int what, int extra) {
        Log.d(TAG, "onInfo: what=" + what + ",extra=" + extra);
        return false;
    }

    @Override
    public void onSeekComplete(MediaPlayer mp) {
        Log.d(TAG, "onSeekComplete: ");
    }

    @Override
    public void onTimedText(MediaPlayer mp, TimedText text) {
        Log.d(TAG, "onTimedText: ");
    }

    @Override
    public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
        Log.d(TAG, "onVideoSizeChanged: width=" + width + ",height=" + height);
    }

    @Override
    public void updateProgress() {
        int currentPosition = mMediaPlayer.getCurrentPosition();
        int duration = mMediaPlayer.getDuration();
        Log.d(TAG, "updateProgress: total=" + duration + ",current=" + currentPosition);

        if (null == getActivity()) {
            return;
        }
        getActivity().runOnUiThread(() -> {
            mProgress.setProgress(currentPosition);
            mProgress.setMax(duration);
            mCurrentTime.setText(mMediaController.stringForTime(currentPosition));
            mTotalTime.setText(mMediaController.stringForTime(duration));
        });
    }
}