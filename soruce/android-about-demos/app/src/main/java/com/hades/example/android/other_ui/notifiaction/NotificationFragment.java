package com.hades.example.android.other_ui.notifiaction;

import android.app.Fragment;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hades.example.android.R;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * https://blog.csdn.net/w804518214/article/details/51231946
 */
public class NotificationFragment extends Fragment {
    private static final String TAG = NotificationFragment.class.getSimpleName();

    NotificationManager mNotificationManager;

    private int mProgress = 0;
    private final int NORMAL_NOTIFICATION_ID = 5;
    private final int PROGRESS_NOTIFICATION_ID = 10;

    private final String PROGRESS_NOTIFICATION_CHANNEL_ID = "PROGRESS_NOTIFICATION";
    private boolean isOnProgressNotification;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.other_ui_notification, container, false);

        mNotificationManager = (NotificationManager) getUsedContext().getSystemService(NOTIFICATION_SERVICE);

        view.findViewById(R.id.simpleNotify).setOnClickListener(this::simpleNotify);
        view.findViewById(R.id.progressNotification).setOnClickListener(this::sendProgressNotification);

        view.findViewById(R.id.del).setOnClickListener(this::delete);
        return view;
    }

    private Context getUsedContext() {
        return getActivity();
    }

    public void simpleNotify(View source) {
        /**
         * QA:Android中getResources().getDrawable() Deprecated?
         * https://blog.csdn.net/zheng0203/article/details/62909177
         */
        Notification.Builder builder = new Notification.Builder(getUsedContext())
                /**
                 * 点击通知后，通知是否自动消失。 若设置否，点击一次通知后，下次单击动作仍然生效。故，一般设置为 false
                 */
                .setAutoCancel(false)
                /**
                 * QA:java.lang.IllegalArgumentException: Invalid notification (no valid small icon)
                 * Small Icon 必须有。高版本（如Android7.0）中仅仅显示Small Icon，不显示Ticker。
                 */
                .setSmallIcon(R.drawable.notify)
                .setTicker("提示信息")

                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher))
                .setContentTitle("标题")
                .setContentText("通知内容" + "-" + System.currentTimeMillis())
                .setSubText("内容摘要")

                // setSound 和setDefaults：只能生效一种
                // 设置通知的自定义声音
//                .setSound(Uri.parse("android.resource://" + BuildConfig.APPLICATION_ID + "/" + R.raw.msg))
                // 设置使用系统默认的声音、振动、默认LED灯
                .setDefaults(Notification.DEFAULT_ALL)

                .setWhen(System.currentTimeMillis())
                /**
                 * 点击时的intent
                 */
                .setContentIntent(getPendingIntent4Click())

                //ContentInfo 在通知的右侧 时间的下面 用来展示一些其他信息
                //.setContentInfo("10")
                //number设计用来显示同种通知的数量和ContentInfo的位置一样，如果设置了ContentInfo,则number会被隐藏
                .setNumber(10); // ①;

        /**
         * 通过NotificationManager发送通知。
         *
         * QA: notify 只显示一条？
         * notify(int id, Notification notification),若 id 一样，则只显示一条 = 自动替换新的内容。
         */
        mNotificationManager.notify(NORMAL_NOTIFICATION_ID, builder.build());
//        mNotificationManager.notify((int) System.currentTimeMillis(), builder.build());
    }

    private void progressNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getUsedContext(), PROGRESS_NOTIFICATION_CHANNEL_ID);
        builder.setSmallIcon(R.drawable.notify);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher));

        builder.setAutoCancel(true);

        //禁止滑动删除
        builder.setOngoing(false);

        //取消右上角的时间显示
        builder.setShowWhen(false);

        builder.setContentTitle("下载中..." + mProgress + "%");
        builder.setProgress(100, mProgress, false);
        //builder.setContentInfo(mProgress+"%");

        builder.setShowWhen(false);

        mNotificationManager.notify(PROGRESS_NOTIFICATION_ID, builder.build());
    }

    private void sendProgressNotification(View source) {
        if (isOnProgressNotification) {
            return;
        }
        mockDoHeavyWork();
    }

    private void mockDoHeavyWork() {
        isOnProgressNotification = true;
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 10; i++) {
                    Log.d(TAG, "mockDoHeavyWork,run: i=" + i);
                    mProgress = i * 10;
                    try {
                        Thread.sleep(500);
                        progressNotification();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                isOnProgressNotification = false;
            }
        }).start();
    }

    private PendingIntent getPendingIntent4Click() {
        Intent intent = new Intent(getUsedContext(), NotificationDescActivity.class);
        return PendingIntent.getActivity(getActivity(), 0, intent, 0);
    }

    public void delete(View v) {
        mNotificationManager.cancel(NORMAL_NOTIFICATION_ID);
        mNotificationManager.cancel(PROGRESS_NOTIFICATION_ID);
    }
}
