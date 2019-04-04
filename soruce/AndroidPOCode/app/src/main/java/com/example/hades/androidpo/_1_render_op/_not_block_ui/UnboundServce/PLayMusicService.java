package com.example.hades.androidpo._1_render_op._not_block_ui.UnboundServce;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.hades.androidpo.R;

public class PLayMusicService extends Service {
    private static final String TAG = PLayMusicService.class.getSimpleName();

    public final static String PLAY_MUSIC_ACTION_COUNTER = "COUNTER";
    public final static String PLAY_MUSIC_ACTION_VISIBLE = "INVISIBLE";
    public final static String PLAY_MUSIC_ACTION_PROGRESS = "PROGRESS";
    public final static String PLAY_MUSIC_KEY = "MAX_NUM";
    public final static String PLAY_MUSIC_KEY_IS_VISIBLE = "IS_VISIBLE";
    public final static String PLAY_MUSIC_VALUE_PROGRESS = "PROGRESS";

    private boolean mOn = false;
    private boolean mVisible = true;
    private boolean mNotify = false;
    private boolean isNotifyShowProgress = true;

    public PLayMusicService() {
        super();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // SESSION:
        // PLayMusicService: onStartCommand: thread name=main,thread id=2
        Log.d(TAG, "onStartCommand: thread name=" + Thread.currentThread().getName() + ",thread id=" + Thread.currentThread().getId());
        if (null == intent || null == intent.getAction() || (!PLAY_MUSIC_ACTION_COUNTER.equalsIgnoreCase(intent.getAction())
                && !PLAY_MUSIC_ACTION_VISIBLE.equalsIgnoreCase(intent.getAction()))) {
            return super.onStartCommand(intent, flags, startId);
        }

        switch (intent.getAction()) {
            case PLAY_MUSIC_ACTION_COUNTER:
                startPlayMusic(intent);
                break;

            case PLAY_MUSIC_ACTION_VISIBLE:
                mVisible = intent.getBooleanExtra(PLAY_MUSIC_KEY_IS_VISIBLE, true);
                break;
        }
        return super.onStartCommand(intent, flags, startId);
    }

    private void startPlayMusic(@Nullable Intent intent) {
        if (mOn) {
            return;
        }
        mOn = true;
        new Thread(new Runnable() {
            @Override
            public void run() {
                int max = intent.getIntExtra(PLAY_MUSIC_KEY, 0);
                for (int i = 0; i < max; i++) {
                    try {
                        Thread.sleep(1000);
                        displayPlayProgress(i);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                stopSelf();
                mOn = false;
            }
        }).start();
    }

    private void displayPlayProgress(int progress) {
        if (!mOn) {
            return;
        }

        broadcastPlayingMusicProgress(progress);
        if (!mVisible) {
            if (isNotifyShowProgress) {
                notifyPlayingMusicProgress(progress);
            } else {
                if (!mNotify) {
                    notifyPlayingMusicProgress(progress);
                }
                mNotify = true;
            }
        }
    }

    private void broadcastPlayingMusicProgress(int progress) {
        Intent intent = new Intent();
        intent.setAction(PLAY_MUSIC_ACTION_PROGRESS);
        intent.putExtra(PLAY_MUSIC_VALUE_PROGRESS, progress);
        sendBroadcast(intent);
    }

    private void notifyPlayingMusicProgress(int progress) {
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if (isNotSmallAndroid8()) {
            // SESSION:
            notificationManager.createNotificationChannel(new NotificationChannel("Mock play music", "Mock Media", NotificationManager.IMPORTANCE_LOW));
        }

        // SESSION:
        Notification.Builder builder = isNotSmallAndroid8()
                ? new Notification.Builder(getApplicationContext(), "Mock play music")
                : new Notification.Builder(getApplicationContext());

        builder.setSmallIcon(R.drawable.ic_launcher);
        if (isNotifyShowProgress) {
            builder.setContentTitle("Playing Music");
            builder.setProgress(100, progress, false);
        }

        Notification notification = builder.build();
        // SESSION:
        if (isNotSmallAndroid8()) {
            startForeground(1, notification);

            /**
             *  five seconds > startForeground() , or ANR
             */
            /**
             * W/zygote64: Current dex file has more than one class in it. Calling RetransformClasses on this class might fail if no transformations are applied to it!
             */
//            notificationManager.notify(1, notification);
        } else {
            notificationManager.notify(1, notification);
        }
    }

    private boolean isNotSmallAndroid8() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.O;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
