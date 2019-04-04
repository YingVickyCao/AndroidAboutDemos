package com.example.hades.androidpo._2_memory_op.memory_leak.newed_anonymous_class.bad;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hades.androidpo.BuildConfig;
import com.example.hades.androidpo.DebugExampleApplication;
import com.example.hades.androidpo.R;

/**
 * Create by Vicky on 29/08/2018.
 **/
public class LeakNewedAnonymousClassFragment extends Fragment {
    private static final String TAG = LeakNewedAnonymousClassFragment.class.getSimpleName();
    TextView mNum;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mock_memory_leak_layout, container, false);
        mNum = view.findViewById(R.id.num);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        runThread();
    }

    private void runThread() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(60 * 1000);
                    mNum.setText(System.currentTimeMillis() + "");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (BuildConfig.DEBUG) {
            DebugExampleApplication.getRefWatcher().watch(this);
        }
    }

}