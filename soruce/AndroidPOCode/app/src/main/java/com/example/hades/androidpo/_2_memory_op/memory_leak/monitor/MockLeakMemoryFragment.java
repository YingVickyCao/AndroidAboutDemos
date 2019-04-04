package com.example.hades.androidpo._2_memory_op.memory_leak.monitor;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
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
public class MockLeakMemoryFragment extends Fragment {
    private static final String TAG = MockLeakMemoryFragment.class.getSimpleName();
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
//        runThread2();
    }

    private void runThread() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mockHeavyWork();
            }
        }).start();
    }


    private void runThread2() {
        LeakThread leakThread = new LeakThread();
        leakThread.start();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (BuildConfig.DEBUG) {
            DebugExampleApplication.getRefWatcher().watch(this);
        }
    }

    protected class LeakThread extends Thread {
        @Override
        public void run() {
            mockHeavyWork();
        }
    }

    private void mockHeavyWork() {
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int finalI = i;
            Log.d(TAG, "run: " + String.valueOf(finalI + 1));
//                    mNum.post(new Runnable() {
//                        @Override
//                        public void run() {
//                            Log.d(TAG, "run: " + String.valueOf(finalI + 1));
//                            mNum.setText(String.valueOf(finalI + 1));
//                        }
//                    });
        }
    }
}