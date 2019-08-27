package com.hades.example.android.widget._progressbar;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import androidx.annotation.Nullable;

import com.hades.example.android.R;
import com.hades.example.android.lib.base.BaseFragment;

public class TestSeekBarFragment extends BaseFragment {
    private static final String TAG = TestSeekBarFragment.class.getName();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.widget_progressbar_seekbar, container, false);

        /**
         <pre>
         Drag:
         onStartTrackingTouch: progress=40
         onProgressChanged: progress=39,fromUser=true
         onProgressChanged: progress=35,fromUser=true
         onProgressChanged: progress=31,fromUser=true
         onProgressChanged: progress=27,fromUser=true
         onProgressChanged: progress=26,fromUser=true
         onStopTrackingTouch: progress=26
         <pre/>
         */
        ((SeekBar) view.findViewById(R.id.seekBar)).setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.d(TAG, "onProgressChanged: progress=" + progress + ",fromUser=" + fromUser);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Log.d(TAG, "onStartTrackingTouch: progress=" + seekBar.getProgress());
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.d(TAG, "onStopTrackingTouch: progress=" + seekBar.getProgress());
            }
        });
        return view;
    }
}