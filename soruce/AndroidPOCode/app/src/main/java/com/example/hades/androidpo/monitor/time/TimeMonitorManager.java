package com.example.hades.androidpo.monitor.time;

import java.util.HashMap;

public class TimeMonitorManager {
    private static TimeMonitorManager mTimeMonitorManager = null;
    //    private static Context mContext = null;
    private HashMap<Integer, TimeMonitor> timeMonitorMap;

    public synchronized static TimeMonitorManager getInstance() {
        if (mTimeMonitorManager == null) {
            mTimeMonitorManager = new TimeMonitorManager();
        }
        return mTimeMonitorManager;
    }

    public TimeMonitorManager() {
        timeMonitorMap = new HashMap<>();
    }

    public void resetTimeMonitor(int id) {
        if (timeMonitorMap.get(id) != null) {
            timeMonitorMap.remove(id);
        }
        getTimeMonitor(id);
    }

    public TimeMonitor getTimeMonitor(int id) {
        TimeMonitor monitor = timeMonitorMap.get(id);
        checkTimeMonitor(monitor, id);
        return monitor;
    }

    private void checkTimeMonitor(TimeMonitor monitor, int id) {
        if (monitor == null) {
            monitor = new TimeMonitor(id);
            timeMonitorMap.put(id, monitor);
        }
    }
}
