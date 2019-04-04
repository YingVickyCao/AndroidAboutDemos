package com.example.hades.androidpo.monitor.time;

import com.example.hades.androidpo.monitor.ui.GLog;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class TimeMonitor {
    private final String TAG = TimeMonitor.class.getSimpleName();

    private int mMonitorId;
    private long mStartTime = 0;
    private LinkedHashMap<String, Long> mTimeTag = new LinkedHashMap<>();

    public TimeMonitor(int id) {
        GLog.d(TAG, "TimeMonitor id:" + id);
        mMonitorId = id;
        startMonitor();
    }

    public int getMonitorId() {
        return mMonitorId;
    }

    public void startMonitor() {
        if (mTimeTag.size() > 0) {
            mTimeTag.clear();
        }
        mStartTime = System.currentTimeMillis();
    }

    private void removeOldTag(String tag) {
        if (mTimeTag.get(tag) != null) {
            mTimeTag.remove(tag);
        }
    }

    private void cacheTag(String tag) {
        long time = System.currentTimeMillis() - mStartTime;
        GLog.d(TAG, tag + ":" + time + " ms");
        mTimeTag.put(tag, time);
    }

    public void recodingTimeTag(String tag) {
        removeOldTag(tag);
        cacheTag(tag);
    }

    public void end(String tag, boolean writeLog) {
        recodingTimeTag(tag);
        end(writeLog);
    }

    public void end(boolean writeLog) {
        if (writeLog) {
            writeLog();
        }
        printLog();
    }

    private void writeLog() {
        // TODO: 23/08/2018  write Log to SD card/ db
        sendLog2Server();
    }

    private void sendLog2Server() {

    }

    private void printLog() {
        if (mTimeTag.size() <= 0) {
            GLog.e(TAG, "mTimeTag is empty!");
            return;
        }

        Long lastTimeStamp = null;
        for (String key : mTimeTag.keySet()) {
            Long value = mTimeTag.get(key);
            if (null == lastTimeStamp) {
                GLog.d(TAG, key + ":" + value);
            } else {
                GLog.d(TAG, key + ":" + value + ",interval=" + (value - lastTimeStamp));
            }
            lastTimeStamp = value;
        }
    }

    public HashMap<String, Long> getTimeTags() {
        return mTimeTag;
    }
}
