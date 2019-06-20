package com.hades.example.android.widget._progressbar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;

import com.hades.example.android.R;
import com.hades.example.android.lib.base.BaseFragment;

// progress_medium
public class TestProgressBarFragment extends BaseFragment {
    ProgressBar mProgressBar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.widget_progressbar, container, false);

        mProgressBar = view.findViewById(R.id.progressBar);

        view.findViewById(R.id.add).setOnClickListener(v -> add());
        view.findViewById(R.id.reduce).setOnClickListener(v -> reduce());

        return view;
    }

    private void add() {
        if (mProgressBar.getProgress() <= 90) {
//            mProgressBar.setProgress((int) (mProgressBar.getProgress() * 1.2));
            mProgressBar.incrementProgressBy(10);
        }
        if (mProgressBar.getSecondaryProgress() < 90) {
//            mProgressBar.setSecondaryProgress((int) (mProgressBar.getSecondaryProgress() * 1.2));
            mProgressBar.incrementSecondaryProgressBy(10);
        }
    }

    private void reduce() {
        if (mProgressBar.getProgress() > 10) {
            mProgressBar.incrementProgressBy(-10);
        }
        if (mProgressBar.getSecondaryProgress() > 20) {
            mProgressBar.incrementSecondaryProgressBy(-10);
        }
    }
}