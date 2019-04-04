package com.example.hades.androidpo._2_memory_op.memory_leak.monitor;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.example.hades.androidpo.BaseActivity;
import com.example.hades.androidpo.BuildConfig;
import com.example.hades.androidpo.DebugExampleApplication;
import com.example.hades.androidpo.R;

/**
 * Create by Vicky on 29/08/2018.
 **/
public class MockLeakMemoryActivity extends BaseActivity {
    private static final String TAG = MockLeakMemoryActivity.class.getSimpleName();
    TextView mNum;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_mock_memory_leak_layout);

        mNum = findViewById(R.id.num);

//        runThread();
        runThread2();
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


    private void runThread2() {
        LeakThread leakThread = new LeakThread();
        leakThread.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (BuildConfig.DEBUG) {
            DebugExampleApplication.getRefWatcher().watch(this);
        }
    }

    protected class LeakThread extends Thread {
        @Override
        public void run() {
            try {
                Thread.sleep(10 * 60 * 1000);
                mNum.setText(System.currentTimeMillis() + "");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}