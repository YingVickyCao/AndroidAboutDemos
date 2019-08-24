package com.hades.example.android.media;

import android.media.MediaPlayer;

public interface IMediaPlayer extends MediaPlayer.OnCompletionListener
        , MediaPlayer.OnErrorListener
        , MediaPlayer.OnPreparedListener
        , MediaPlayer.OnBufferingUpdateListener
        , MediaPlayer.OnVideoSizeChangedListener
        , MediaPlayer.OnSeekCompleteListener
        , MediaPlayer.OnTimedTextListener
        , MediaPlayer.OnInfoListener {
}
