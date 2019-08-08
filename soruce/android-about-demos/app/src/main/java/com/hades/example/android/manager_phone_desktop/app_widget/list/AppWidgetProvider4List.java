package com.hades.example.android.manager_phone_desktop.app_widget.list;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.hades.example.android.R;

public class AppWidgetProvider4List extends AppWidgetProvider {
    private static final String TAG = AppWidgetProvider4List.class.getSimpleName();

    public static final String TOAST_ACTION = AppWidgetProvider4List.class.getCanonicalName() + ".TOAST_ACTION";
    public static final String EXTRA_ITEM = AppWidgetProvider4List.class.getCanonicalName() + ".EXTRA_ITEM";

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.app_widget_list_layout);
        Intent intent = new Intent(context, RemoteViewsService4List.class);

        // 使用intent更新rv中的stack_view组件（StackView）
        rv.setRemoteAdapter(R.id.list_view, intent);  // ①

        // 设置当StackWidgetService提供的列表项为空时，直接显示empty_view组件
        rv.setEmptyView(R.id.list_view, R.id.empty_view);
        // 创建启动StackWidgetProvider组件（作为BroadcastReceiver）的Intent

        Intent toastIntent = new Intent(context, AppWidgetProvider4List.class);
        // 为该Intent设置Action属性
        toastIntent.setAction(AppWidgetProvider4List.TOAST_ACTION);
        // 将Intent包装成PendingIntent
        PendingIntent toastPendingIntent = PendingIntent.getBroadcast(context, 0, toastIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        // 将PendingIntent与stack_view进行关联
        rv.setPendingIntentTemplate(R.id.list_view, toastPendingIntent);
        // 使用AppWidgetManager通过RemteViews更新AppWidgetProvider
        appWidgetManager.updateAppWidget(new ComponentName(context, AppWidgetProvider4List.class), rv); // ②
        super.onUpdate(context, appWidgetManager, appWidgetIds);
    }

    // 重写该方法，将该组件当成BroadcastReceiver使用
    @Override
    public void onReceive(Context context, Intent intent) {
        if (TOAST_ACTION.equals(intent.getAction())) {
            // 获取Intent中的数据
            int viewIndex = intent.getIntExtra(EXTRA_ITEM, 0);
            // 显示Toast提示
            Toast.makeText(context, "点击第【" + viewIndex + "】个列表项", Toast.LENGTH_SHORT).show();
        }
        super.onReceive(context, intent);
    }
}

