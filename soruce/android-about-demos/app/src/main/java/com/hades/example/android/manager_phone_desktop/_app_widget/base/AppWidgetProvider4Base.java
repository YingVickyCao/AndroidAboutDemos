package com.hades.example.android.manager_phone_desktop._app_widget.base;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;

import com.hades.example.android.R;
import com.hades.example.android.lib.utils.LogHelper;

/*
    // Add
    onEnabled: ,[thread =2,main]
    onReceive: ,[thread =2,main]
    onUpdate: ,[thread =2,main]
    onReceive: ,[thread =2,main]
    onAppWidgetOptionsChanged: ,[thread =2,main]
    onReceive: ,[thread =2,main]`

    // Delete
    onDeleted: ,[thread =2,main]
    onReceive: ,[thread =2,main]
    onDisabled: ,[thread =2,main]
    onReceive: ,[thread =2,main]
 */
public class AppWidgetProvider4Base extends AppWidgetProvider {
    private static final String TAG = AppWidgetProvider4Base.class.getSimpleName();

    /*
     Add widget:
     AppWidgetProvider4Base: onUpdate:appWidgetId=14,[thread =2,main]
     AppWidgetProvider4Base: updateAppWidget: appWidgetId=14
     AppWidgetProvider4Base: onReceive: ,[thread =2,main]

     TestAppWidgetConfigureActivity: onCreate:
     //TestAppWidgetConfigureActivity: updateAppWidget4Configuration:appWidgetId=14
     //AppWidgetProvider4Base: updateAppWidget: appWidgetId=14

     AppWidgetProvider4Base: onAppWidgetOptionsChanged: ,[thread =2,main]
     AppWidgetProvider4Base: updateAppWidget: appWidgetId=14
     AppWidgetProvider4Base: onReceive: ,[thread =2,main]
     */
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) { // [thread =2,main].
        Log.d(TAG, "onUpdate: " + LogHelper.getThreadInfo());
        for (int appWidgetId : appWidgetIds) {
            Log.d(TAG, "onUpdate:appWidgetId=" + appWidgetId + LogHelper.getThreadInfo());
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onReceive(Context context, Intent intent) { // [thread =2,main]
        super.onReceive(context, intent);
        Log.d(TAG, "onReceive: " + LogHelper.getThreadInfo());
    }

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {
        Log.d(TAG, "updateAppWidget: appWidgetId=" + appWidgetId);
        int color = TestAppWidgetConfigureActivity.load(context, appWidgetId);
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.app_widget_base);
        remoteViews.setTextColor(R.id.img, color);

        // Notify remote desktop app, update AppWidgetProvider4Base view.
//            ComponentName componentName = new ComponentName(context, AppWidgetProvider4Base.class);  // 将AppWidgetProvider子类实例包装成ComponentName对象
//            appWidgetManager.updateAppWidget(componentName, remoteViews);  // 调用AppWidgetManager将remoteViews添加到ComponentName中
        appWidgetManager.updateAppWidget(appWidgetId, remoteViews);  // 调用AppWidgetManager将remoteViews添加到ComponentName中
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) { // [thread =2,main].  delete cache data
        super.onDeleted(context, appWidgetIds);
        Log.d(TAG, "onDeleted: " + LogHelper.getThreadInfo());
    }

    // Below often not override
    @Override
    public void onDisabled(Context context) { // [thread =2,main]
        super.onDisabled(context);
        Log.d(TAG, "onDisabled: " + LogHelper.getThreadInfo());
    }

    @Override
    public void onEnabled(Context context) { // [thread =2,main]
        super.onEnabled(context);
        Log.d(TAG, "onEnabled: " + LogHelper.getThreadInfo());
    }

    @Override
    public void onAppWidgetOptionsChanged(Context context, AppWidgetManager appWidgetManager, int appWidgetId, Bundle newOptions) { // [thread =2,main]
        super.onAppWidgetOptionsChanged(context, appWidgetManager, appWidgetId, newOptions);
        Log.d(TAG, "onAppWidgetOptionsChanged: " + LogHelper.getThreadInfo());
        updateAppWidget(context, appWidgetManager, appWidgetId);
    }

    @Override
    public void onRestored(Context context, int[] oldWidgetIds, int[] newWidgetIds) { // [thread =2,main]
        super.onRestored(context, oldWidgetIds, newWidgetIds);
        Log.d(TAG, "onRestored: " + LogHelper.getThreadInfo());
    }
}