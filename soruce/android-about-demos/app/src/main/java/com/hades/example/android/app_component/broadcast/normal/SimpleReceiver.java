package com.hades.example.android.app_component.broadcast.normal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.hades.example.android.lib.utils.LogHelper;

/**
 *
 */
public class SimpleReceiver extends BroadcastReceiver {
    private static final String TAG = SimpleReceiver.class.getSimpleName();

    public static final String ACTION_ONE = "com.hades.example.android.app_component.broadcast.normal.SimpleReceiver.ONE";
    public static final String KEY_ONE = "one";

    @Override
    public void onReceive(Context context, Intent intent) {
        /**
         * SimpleReceiver: onReceive,[thread =2,main],hashCode()=77016919
         */
        LogHelper.printThread(TAG, "onReceive", "------->");
        LogHelper.printThread(TAG, "onReceive", "hashCode=" + String.valueOf(System.identityHashCode(this)));
        if (null == intent || null == intent.getAction()) {
            return;
        }
        switch (intent.getAction()) {
            case ACTION_ONE:
                /*
                 onReceive: action=com.hades.example.android.app_component.broadcast.normal.SimpleReceiver.ONE,info=Explicit broadcast
                 onReceive: action=com.hades.example.android.app_component.broadcast.normal.SimpleReceiver.ONE,info=implicit Broadcast
                 */
                Log.d(TAG, "onReceive: " + "action=" + intent.getAction() + ",info=" + intent.getStringExtra(KEY_ONE));
                Toast.makeText(context, "接收到的Intent的Action为：" + intent.getAction() + "\n消息内容是：" + intent.getStringExtra(KEY_ONE), Toast.LENGTH_LONG).show();

//                startLongRunningBackgroundThreads();
//                doLongRunningWorkInMain();
                testGoAsync(intent);
                break;
        }
        LogHelper.printThread(TAG, "onReceive", "<-------");
    }

    /*
    // activity is top = 120s(activity is top)
    // 虽然 ANR（Android Not Responsing） = 10s，实际上只运行120s(activity is top)就AAR。
    07-24 18:10:24.664 13683-13683/app D/SimpleReceiver: onReceive,[thread =2,main],------->
    07-24 18:10:24.664 13683-13683/app D/SimpleReceiver: onReceive,[thread =2,main],hashCode=180628956
    07-24 18:10:24.664 13683-13683/app D/SimpleReceiver: onReceive: action=com.hades.example.android.app_component.broadcast.normal.SimpleReceiver.ONE,info=implicit Broadcast
    07-24 18:10:24.679 13683-13683/app D/SimpleReceiver: doLongRunningWorkInMain,[thread =2,main],------->
    07-24 18:10:25.682 13683-13683/app D/SimpleReceiver: doLongRunningWorkInMain,[thread =2,main],count=1
    ...
    07-24 18:12:30.027 13683-13683/app D/SimpleReceiver: doLongRunningWorkInMain,[thread =2,main],count=125
    07-24 18:12:30.055 13683-13689/app I/zygote64: Thread[3,tid=13689,WaitingInMainSignalCatcherLoop,Thread*=0x7139ec4400,peer=0x137c0020,"Signal Catcher"]: reacting to signal 3
    07-24 18:12:30.161 13683-13689/app I/zygote64: Wrote stack traces to '/data/anr/traces.txt'
    07-24 18:12:31.028 13683-13683/app D/SimpleReceiver: doLongRunningWorkInMain,[thread =2,main],count=126
    07-24 18:12:32.029 13683-13683/app D/SimpleReceiver: doLongRunningWorkInMain,[thread =2,main],count=127
    ...
     */
    private void doLongRunningWorkInMain() {
        LogHelper.printThread(TAG, "doLongRunningWorkInMain", "------->");
        int count = 0;
        for (; ; ) {
            count++;
            try {
                Thread.sleep(1000);
                LogHelper.printThread(TAG, "doLongRunningWorkInMain", "count=" + count);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /*
    07-24 18:22:53.475 15540-15540/app D/SimpleReceiver: onReceive,[thread =2,main],------->
    07-24 18:22:53.475 15540-15540/app D/SimpleReceiver: onReceive,[thread =2,main],hashCode=180628956
    07-24 18:22:53.475 15540-15540/app D/SimpleReceiver: onReceive: action=com.hades.example.android.app_component.broadcast.normal.SimpleReceiver.ONE,info=implicit Broadcast
    07-24 18:22:53.485 15540-15540/app D/SimpleReceiver: startLongRunningBackgroundThreads,[thread =2,main],------->
    07-24 18:22:53.486 15540-15540/app D/SimpleReceiver: startLongRunningBackgroundThreads,[thread =2,main],<-------
    07-24 18:22:53.475 15540-15540/app D/SimpleReceiver: onReceive,[thread =2,main],<-------
    07-24 18:22:54.488 15540-15966/app D/SimpleReceiver: startLongRunningBackgroundThreads,[thread =34165,Thread-7],count=1
    07-24 18:22:55.489 15540-15966/app D/SimpleReceiver: startLongRunningBackgroundThreads,[thread =34165,Thread-7],count=2
    ...
     */
    private void startLongRunningBackgroundThreads() {
        LogHelper.printThread(TAG, "startLongRunningBackgroundThreads", "------->");
        new Thread(new Runnable() {
            @Override
            public void run() {
                int count = 0;
                for (; ; ) {
                    count++;
                    try {
                        Thread.sleep(1000);
                        LogHelper.printThread(TAG, "startLongRunningBackgroundThreads", "count=" + count);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        LogHelper.printThread(TAG, "startLongRunningBackgroundThreads", "<-------");
    }

    private void testGoAsync(final Intent intent) {
        LogHelper.printThread(TAG, "testGoAsync", "------->");
        final PendingResult pendingResult = goAsync();
        AsyncTask<String, Integer, String> asyncTask = new AsyncTask<String, Integer, String>() {
            @Override
            protected String doInBackground(String... params) {
                int count = 0;
                for (int i = 0; i < 10000; i++) {
                    count++;
                    try {
                        Thread.sleep(1000);
                        LogHelper.printThread(TAG, "testGoAsync", "count=" + count);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                // Must call finish() so the BroadcastReceiver can be recycled.
                pendingResult.finish();
                return String.valueOf(count);
            }
        };
        asyncTask.execute();
        LogHelper.printThread(TAG, "testGoAsync", "<-------");
    }
}