package com.hades.example.android._process_and_thread.threadPoolExecutor;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.hades.example.android.R;
import com.hades.example.android.lib.base.BaseFragment;

import java.util.Locale;

public class ThreadPoolExecutorFragment extends BaseFragment implements IProgressListener {
    private final int START_NUM = 0;
    private final int END_NUM = Integer.MAX_VALUE;
    private TextView mProgressInfo;
    CounterSingleThreadPoolExecutor mExecutor;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bt_threadpoolexecutor, container, false);

        mExecutor = new CounterSingleThreadPoolExecutor(END_NUM, this);
        ((TextView) view.findViewById(R.id.numInfo)).setText(String.format(Locale.getDefault(), "%d:%d", START_NUM, END_NUM));
        mProgressInfo = view.findViewById(R.id.progressInfo);

        view.findViewById(R.id.start).setOnClickListener(v -> start());
        view.findViewById(R.id.pause).setOnClickListener(v -> pause());
        view.findViewById(R.id.cancel).setOnClickListener(v -> cancel());
        view.findViewById(R.id.end).setOnClickListener(v -> end());
        
        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        mExecutor.setProgressListener(null);
    }

    @Override
    public void onResume() {
        super.onResume();
        mExecutor.setProgressListener(this);
    }

    private void start() {
        mExecutor.setProgressListener(this);
        mExecutor.start();
    }

    private void pause() {
        mExecutor.pause();
    }

    private void cancel() {
        mExecutor.cancel();
    }

    private void end() {
        mExecutor.end();
    }

    @Override
    public void update(String counter) {
        mProgressInfo.post(new Runnable() {
            @Override
            public void run() {
                mProgressInfo.setText(counter);
            }
        });
    }
}
