package com.example.hades.androidpo._1_render_op._not_block_ui.UnboundServce;

import android.app.Fragment;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.hades.androidpo.R;

import static com.example.hades.androidpo._1_render_op._not_block_ui.UnboundServce.PLayMusicService.PLAY_MUSIC_ACTION_COUNTER;
import static com.example.hades.androidpo._1_render_op._not_block_ui.UnboundServce.PLayMusicService.PLAY_MUSIC_ACTION_PROGRESS;
import static com.example.hades.androidpo._1_render_op._not_block_ui.UnboundServce.PLayMusicService.PLAY_MUSIC_ACTION_VISIBLE;
import static com.example.hades.androidpo._1_render_op._not_block_ui.UnboundServce.PLayMusicService.PLAY_MUSIC_KEY;
import static com.example.hades.androidpo._1_render_op._not_block_ui.UnboundServce.PLayMusicService.PLAY_MUSIC_KEY_IS_VISIBLE;

public class PlayMusic4ServiceFragment extends Fragment implements IPlayMusic {
    private ProgressBar progressBarValue;
    private PlayMusicBroadcastReceiver mMusicBroadcastReceiver;
    private IntentFilter mIntentFilter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.play_music_layout, container, false);
        view.findViewById(R.id.playMusic).setOnClickListener(v -> playMusic());
        progressBarValue = view.findViewById(R.id.progressBarValue);
        mMusicBroadcastReceiver = new PlayMusicBroadcastReceiver();

        mIntentFilter = new IntentFilter(PLAY_MUSIC_ACTION_PROGRESS);
        return view;
    }

    private void playMusic() {
        // ERROR:java.lang.IllegalArgumentException: Service Intent must be explicit: Intent { act=COUNTER (has extras) }
//        Intent intent = new Intent();
        Intent intent = new Intent(getActivity().getApplicationContext(), PLayMusicService.class);
        intent.setAction(PLAY_MUSIC_ACTION_COUNTER);
        intent.putExtra(PLAY_MUSIC_KEY, 100);
        // SESSION:
        // >= Android 8.0(API 26)
//        getActivity().startForegroundService(intent);
        getActivity().startService(intent);
    }

    @Override
    public void onResume() {
        super.onResume();

        mMusicBroadcastReceiver.setPlayMusic(this);
        getActivity().registerReceiver(mMusicBroadcastReceiver, mIntentFilter);
        sendViewVisible(true);
    }

    private void sendViewVisible(boolean isVisible) {
        Intent intent = new Intent(getActivity().getApplicationContext(), PLayMusicService.class);
        intent.setAction(PLAY_MUSIC_ACTION_VISIBLE);
        intent.putExtra(PLAY_MUSIC_KEY_IS_VISIBLE, isVisible);
        getActivity().startService(intent);
    }

    @Override
    public void onPause() {
        super.onPause();

        mMusicBroadcastReceiver.setPlayMusic(null);
        getActivity().unregisterReceiver(mMusicBroadcastReceiver);
        sendViewVisible(false);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mMusicBroadcastReceiver = null;
        getActivity().startService(new Intent(getActivity().getApplicationContext(), PLayMusicService.class));
    }

    @Override
    public void updateProgress(int progress) {
        progressBarValue.setProgress(progress);
    }
}
