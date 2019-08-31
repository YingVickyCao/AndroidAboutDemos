package com.hades.example.android.media.audio.media_player;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.TimedText;
import android.net.Uri;
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

import com.hades.example.android.Constant;
import com.hades.example.android.R;
import com.hades.example.android.lib.base.BaseFragment;
import com.hades.example.android.lib.timer.ITimerView;
import com.hades.example.android.lib.timer.TimerHandler;
import com.hades.example.android.lib.utils.FileUtil;

import java.io.IOException;

public class TestMediaPlayer4AudioFragment extends BaseFragment implements IMediaPlayer, ITimerView {
    private static final String TAG = TestMediaPlayer4AudioFragment.class.getName();

    MediaPlayer mPlayer = null;
    SeekBar mProgress;
    TextView mCurrentTime;
    TextView mEndTime;

    private TimerHandler mHandler;
    private MediaController mMediaController;
    private int mPosition4MediaFrom;
    private int mCurrentBufferPercentage;
    private boolean mDragging = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.media_audio_mediaplayer, container, false);

        mProgress = view.findViewById(R.id.playProgress);
        mCurrentTime = view.findViewById(R.id.currentTime);
        mEndTime = view.findViewById(R.id.totalTime);

        ((Spinner) view.findViewById(R.id.mediaFrom)).setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mPosition4MediaFrom = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        mProgress.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //  FIXED_ERROR:TBD: seekbar -> MediaPlayer.seekTo(int)没有效果,直接回调old position
                if (!fromUser) {
                    return;
                }
                long duration = mPlayer.getDuration();
                long newposition = (duration * progress) / 100L;
                seekTo((int) newposition);
                if (mCurrentTime != null) {
                    mCurrentTime.setText(mMediaController.stringForTime((int) newposition));
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                mDragging = true;
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.d(TAG, "onStopTrackingTouch: ");
                mDragging = false;
            }
        });

        view.findViewById(R.id.start).setOnClickListener(v -> start());
        view.findViewById(R.id.pause).setOnClickListener(v -> pause());
        view.findViewById(R.id.stopRecord).setOnClickListener(v -> stop());

        mMediaController = new MediaController();

        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        if (null == mHandler) {
            mHandler = new TimerHandler();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (null != mHandler) {
            mHandler.clear();
            mHandler.setITimerView(null);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mMediaController = null;
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

    private void setMediaPlayerListener() {
        if (null == mPlayer) {
            return;
        }
        mPlayer.setOnBufferingUpdateListener(this);
        mPlayer.setOnCompletionListener(this);
        mPlayer.setOnErrorListener(this);
        mPlayer.setOnInfoListener(this);
        mPlayer.setOnSeekCompleteListener(this);
        mPlayer.setOnPreparedListener(this);
        mPlayer.setOnTimedTextListener(this);
        // Depressed
//        mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        AudioAttributes.Builder builder = new AudioAttributes.Builder();
        builder.setLegacyStreamType(AudioManager.STREAM_MUSIC);
        mPlayer.setAudioAttributes(builder.build());
    }

    private void loadResourceRaw() {
        mPlayer = MediaPlayer.create(getActivity(), R.raw.mp3_2);
        setMediaPlayerListener();
    }

    private void loadAsserts() {
        try {
            AssetFileDescriptor afd = assert2AssetFileDescriptor();
            if (null != afd) {
                mPlayer = new MediaPlayer();
                mPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getStartOffset());
                setMediaPlayerListener();
                prepare();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private AssetFileDescriptor assert2AssetFileDescriptor() {
        if (null == getActivity()) {
            return null;
        }
        AssetManager am = getActivity().getAssets();
        AssetFileDescriptor afd = null;
        try {
            afd = am.openFd("mp3_1.mp3");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return afd;
    }

    private void loadSD() {
        mPlayer = new MediaPlayer();
        try {
            mPlayer.setDataSource(FileUtil.buildFileNameInSD(Constant.MP3_NAME));
//            mPlayer.setDataSource("/sdcard/mp3_1.mp3");
            setMediaPlayerListener();
            prepare();
            mPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadNetwork() {
        if (null == getActivity() || null == getActivity().getApplicationContext()) {
            return;
        }
        mPlayer = new MediaPlayer();
        try {
            mPlayer.setDataSource(getActivity().getApplicationContext(), Uri.parse(Constant.MP3_NAME_2));
            setMediaPlayerListener();
            prepare();
        } catch (IOException e) {
            e.printStackTrace();
            if (null != mPlayer) {
                mPlayer.release();
            }
            mPlayer = null;
        }
    }


    private void startUpdateProgress() {
        if (null != mHandler) {
            mHandler.sendMessage4UpdateView();
        }
    }

    private void stopUpdateProgress() {
        if (null != mHandler) {
            mHandler.setITimerView(null);
        }
    }

    private void prepare() {
        if (null == mPlayer) {
            return;
        }
        mPlayer.prepareAsync();
    }

    private void start() {
        loadMediaSource();
        if (null == mPlayer) {
            return;
        }
        setMediaPlayerListener();
        startUpdateProgress();
    }

    private void pause() {
        if (null == mPlayer) {
            return;
        }
        mPlayer.pause();
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
        if (null == mPlayer) {
            return;
        }
        stopUpdateProgress();
        mPlayer.release();
        mPlayer = null;
    }

    private void seekTo(int seekToProgress) {
        if (null == mPlayer) {
            return;
        }
        mPlayer.seekTo(seekToProgress);
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
        if (null == mPlayer) {
            return;
        }

        mPlayer.start();
        if (null != mHandler) {
            mHandler.setITimerView(this);
            mHandler.sendMessage4UpdateView();
        }
        setEndTime(mPlayer.getDuration());
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
    public void onBufferingUpdate(MediaPlayer mp, int percent) {
        mCurrentBufferPercentage = percent;
    }

    @Override
    public void updateView() {
        /**
         * ERROR:
         *  E/MediaPlayerNative: Attempt to call getDuration in wrong state: mPlayer=0xd3ee1d40, mCurrentState=0
         *  E/MediaPlayerNative: error (-38, 0)
         */
        if (null == mPlayer || mDragging || getActivity() == null) {
            return;
        }

        getActivity().runOnUiThread(() -> {
            int position = mPlayer.getCurrentPosition();
            int duration = mPlayer.getDuration();
            if (mProgress != null) {
                if (duration > 0) {
                    // use long to avoid overflow
                    long pos = 100L * position / duration;
                    mProgress.setProgress((int) pos);
                    Log.d(TAG, "updateView: ,[" + mPlayer.getCurrentPosition() + "," + mCurrentBufferPercentage + "," + mPlayer.getDuration() + "]," + "progress=" + pos + ",bufferPercentage=" + mCurrentBufferPercentage);
                }
                mProgress.setSecondaryProgress(mCurrentBufferPercentage);
            }

            if (mEndTime != null)
                mEndTime.setText(mMediaController.stringForTime(duration));
            if (mCurrentTime != null)
                mCurrentTime.setText(mMediaController.stringForTime(position));
        });
    }

    private void setEndTime(int duration) {
        if (null == getActivity()) {
            return;
        }
        getActivity().runOnUiThread(() -> {
            mProgress.setMax(100);
            mEndTime.setText(mMediaController.stringForTime(duration));
        });
    }
}