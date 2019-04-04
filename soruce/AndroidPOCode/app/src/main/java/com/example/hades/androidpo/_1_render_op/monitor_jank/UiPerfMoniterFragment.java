package com.example.hades.androidpo._1_render_op.monitor_jank;

import android.app.ActivityManager;
import android.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Debug;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.hades.androidpo.R;

public class UiPerfMoniterFragment extends Fragment {
    private static final String TAG = UiPerfMoniterFragment.class.getSimpleName();
    RelativeLayout mtest = null;
    Button mJump = null;
    int mColor = Color.WHITE;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmet_test, container, false);
        mtest = view.findViewById(R.id.test_bg);
        mtest.setBackgroundColor(mColor);
        mJump = (Button) view.findViewById(R.id.btn_start_perfmon);
        mJump.setOnClickListener(new View.OnClickListener() {
                                     @Override
                                     public void onClick(View v) {
                                         changeMonitorPerf();
                                     }
                                 }
        );
        return view;
    }

    public void setBg(int color) {
        //mColor = color;
    }

    private void changeMonitorPerf() {
//        if (UIPerformanceMonitorManager.getmInstance().isMonitoring()) {
//            UIPerformanceMonitorManager.getmInstance().stopMonitor();
//            mJump.setText(this.getResources().getText(R.string.monitor_jank_start));
//        } else {
//            UIPerformanceMonitorManager.getmInstance().startMonitor();
//            mJump.setText(this.getResources().getText(R.string.monitor_jank_stop));
//        }

        getSystemMemoryInfo();
        getCurrentMemoryInfo();
    }

    private void getSystemMemoryInfo() {
        ActivityManager activityManager = (ActivityManager) getActivity().getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo(memoryInfo);
        Log.d(TAG, "getSystemMemoryInfo: totalMem=" + memoryInfo.totalMem);
        Log.d(TAG, "getSystemMemoryInfo: availMem=" + memoryInfo.availMem);
        Log.d(TAG, "getSystemMemoryInfo: threshold=" + memoryInfo.threshold);
        Log.d(TAG, "getSystemMemoryInfo: lowMemory=" + memoryInfo.lowMemory);
    }

    private void getCurrentMemoryInfo() {
        Debug.MemoryInfo memoryInfo = new Debug.MemoryInfo();
        Debug.getMemoryInfo(memoryInfo);
        Log.d(TAG, "getSystemMemoryInfo: totalMem=" + memoryInfo.getTotalPrivateClean());
    }
}