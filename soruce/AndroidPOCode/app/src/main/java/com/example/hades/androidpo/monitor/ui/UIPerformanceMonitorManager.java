package com.example.hades.androidpo.monitor.ui;

import android.os.Looper;

import com.example.hades.androidpo.monitor.ui.sampling.CpuInfo;
import com.example.hades.androidpo.monitor.ui.sampling.CpuInfoSampler;

import java.io.File;

/**
 * UI Performance Monitor Manager
 */
public class UIPerformanceMonitorManager implements UiPerfMonitorConfig, LogPrinterListener {
    private static UIPerformanceMonitorManager mInstance = null;
    private final String TAG = "UIPerformanceMonitorManager";
    private LogPrinter mLogPrinter;
    private LogWriteThread mLogWriteThread;
    private int monitorState = UI_PERF_MONITER_STOP;
    private CpuInfoSampler mCpuInfoSampler = null;

    public synchronized static UIPerformanceMonitorManager getmInstance() {
        if (null == mInstance) {
            mInstance = new UIPerformanceMonitorManager();
        }
        return mInstance;
    }

    //
    public UIPerformanceMonitorManager() {
        mCpuInfoSampler = new CpuInfoSampler();
        mLogPrinter = new LogPrinter(this);
        mLogWriteThread = new LogWriteThread();
        initLogpath();
    }

    public void startMonitor() {
        Looper.getMainLooper().setMessageLogging(mLogPrinter);
        monitorState = UI_PERF_MONITER_START;
    }

    public void stopMonitor() {
        Looper.getMainLooper().setMessageLogging(null);
        mCpuInfoSampler.stop();
        monitorState = UI_PERF_MONITER_STOP;
    }

    public boolean isMonitoring() {
        return monitorState == UI_PERF_MONITER_START;
    }

    //Init Log file dir
    private void initLogpath() {
        File logpath = new File(LOG_PATH);
        if (!logpath.exists()) {
            boolean mkdir = logpath.mkdir();
            GLog.d(TAG, "mkdir:" + mkdir + ":" + LOG_PATH);
        }
    }


    @Override
    public void onStartLoop() {
        mCpuInfoSampler.start();
    }

    @Override
    public void onEndLoop(long starttime, long endtime, String loginfo, @PER_LEVEL int level) {
        mCpuInfoSampler.stop();
        switch (level) {
            case UI_PERF_LEVEL_1:
                GLog.d(TAG, "onEndLoop TIME_WARNING_LEVEL_1 & cpusize:" + mCpuInfoSampler.getStatCpuInfoList().size());
                if (mCpuInfoSampler.getStatCpuInfoList().size() > 0) {
                    StringBuffer sb = new StringBuffer("startTime:");
                    sb.append(starttime);
                    sb.append(" endTime:");
                    sb.append(endtime);
                    sb.append(" handleTime:");
                    sb.append(endtime - starttime);
                    for (CpuInfo info : mCpuInfoSampler.getStatCpuInfoList()) {
                        sb.append("\r\n");
                        sb.append(info.toString());
                    }
                    mLogWriteThread.saveLog(sb.toString());
                }
                break;
            case UI_PERF_LEVEL_2:
                break;
            default:
        }
    }
}
