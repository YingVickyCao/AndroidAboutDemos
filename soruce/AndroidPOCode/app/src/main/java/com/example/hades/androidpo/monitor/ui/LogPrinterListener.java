package com.example.hades.androidpo.monitor.ui;

public interface LogPrinterListener {
    void onStartLoop();
    void onEndLoop(long startTime,long endTime,String logInfo,int level);
}
