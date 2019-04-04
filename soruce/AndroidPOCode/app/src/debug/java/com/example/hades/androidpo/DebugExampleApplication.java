package com.example.hades.androidpo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.StrictMode;

import com.example.hades.androidpo._1_render_op.startup.WelcomeActivity;
import com.example.hades.androidpo.monitor.memory.leak.MemoryLeakUploadService;
import com.example.hades.androidpo.monitor.time.TimeMonitorConfig;
import com.example.hades.androidpo.monitor.time.TimeMonitorManager;
import com.squareup.leakcanary.AndroidExcludedRefs;
import com.squareup.leakcanary.AndroidHeapDumper;
import com.squareup.leakcanary.ExcludedRefs;
import com.squareup.leakcanary.HeapDumper;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.LeakDirectoryProvider;
import com.squareup.leakcanary.RefWatcher;
import com.squareup.leakcanary.internal.LeakCanaryInternals;

import java.io.File;
import java.util.concurrent.TimeUnit;
/*

Logcat Filter = Displayed
I/ActivityManager: Displayed com.example.hades.androidpo/._1_render_op.DrawLayoutOPActivity: +385ms

APPLoginActivity: +2s154ms
APPMainActivity: +1s607m

 */

/*
 adb shell am start -S -W com.example.hades.androidpo/._1_render_op.DrawLayoutOPActivity
 ThisTime: 385
 TotalTime: 385
 WaitTime: 430
 Complete

APPLoginActivity:
    ThisTime: 2141
TotalTime: 2141
WaitTime: 2165
Complete
 */
public class DebugExampleApplication extends ExampleApplication {
    private static final String TAG = DebugExampleApplication.class.getSimpleName();

    private static RefWatcher mRefWatcher = null;
    private TogglingHeapDumper heapDumper;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

        TimeMonitorManager.getInstance().resetTimeMonitor(TimeMonitorConfig.TIME_MONITOR_ID_APPLICATION_START);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        TimeMonitorManager.getInstance().getTimeMonitor(TimeMonitorConfig.TIME_MONITOR_ID_APPLICATION_START).recodingTimeTag(TAG + "_" + TimeMonitorConfig.ON_START);
    }

    protected void setStrictMode() {
        StrictMode.setThreadPolicy(
                new StrictMode.ThreadPolicy.Builder()
                        .detectDiskReads()
                        .detectDiskWrites()
                        .detectNetwork()
                        //.detectAll() for all detectable problems
                        .penaltyLog()
//                        .penaltyDeath()
                        .penaltyDialog()
//                        .penaltyFlashScreen()
//                        .penaltyDeathOnNetwork()
//                        .penaltyDropBox()
                        .build());
    }

    private ExcludedRefs getExcludedRefs() {
        /**
         * You can create your own version of ExcludedRefs to ignore specific references that you know are causing leaks but you still want to ignore:
         */
        return AndroidExcludedRefs.createAppDefaults()
                .instanceField(WelcomeActivity.class.getCanonicalName(), "mLogo")
                .build();
    }

    @Override
    protected void installLeakCanary() {
        LeakDirectoryProvider leakDirectoryProvider = LeakCanaryInternals.getLeakDirectoryProvider(this);

        AndroidHeapDumper defaultDumper = new AndroidHeapDumper(this, leakDirectoryProvider);
        heapDumper = new TogglingHeapDumper(defaultDumper);
        heapDumper.toggle();

        // Build a customized RefWatcher
        mRefWatcher = LeakCanary.refWatcher(this)
                .watchDelay(10, TimeUnit.SECONDS)
                .watchActivities(true) // By default, true,LeakCanary watches all activities.
                .watchFragments(true)
                .maxStoredHeapDumps(42)
                .excludedRefs(getExcludedRefs()) // Ignoring specific references
                .heapDumper(heapDumper) // Toggling LeakCanary on and off at runtime
                .listenerServiceClass(MemoryLeakUploadService.class)
                .buildAndInstall();

        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
                mRefWatcher.watch(activity);
            }

            public void onActivityDestroyed(Activity activity) {
                // By default, LeakCanary watches all activities.
                // Custom to ignore leaks of a certain type of activity.
                if (activity instanceof WelcomeActivity || activity instanceof MainActivity) {
                    return;
                }
                mRefWatcher.watch(activity);
            }
        });
    }

    public static RefWatcher getRefWatcher() {
        return mRefWatcher;
    }

    public static class TogglingHeapDumper implements HeapDumper {
        private final HeapDumper defaultDumper;
        private boolean enabled = false;

        TogglingHeapDumper(HeapDumper defaultDumper) {
            this.defaultDumper = defaultDumper;
        }

        public void toggle() {
            enabled = !enabled;
        }

        @Override
        public File dumpHeap() {
            return enabled ? defaultDumper.dumpHeap() : HeapDumper.RETRY_LATER;
        }
    }
}
