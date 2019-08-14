package com.hades.example.android.other_ui.manager_phone_desktop._app_widget.list;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.hades.example.android.R;

public class ExampleAppWidgetProvider4Collections extends AppWidgetProvider {
    private static final String TAG = ExampleAppWidgetProvider4Collections.class.getSimpleName();

    public static final String TOAST_ACTION = ExampleAppWidgetProvider4Collections.class.getCanonicalName() + ".TOAST_ACTION";
    public static final String EXTRA_ITEM = ExampleAppWidgetProvider4Collections.class.getCanonicalName() + ".EXTRA_ITEM";

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        Log.d(TAG, "onUpdate: ");
        // update each of the app widgets with the remote adapter
        for (int i = 0; i < appWidgetIds.length; ++i) {
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.app_widget_list_layout);
            setRemoteAdapter(context, remoteViews, appWidgetIds[i]);
            addClick4Toast(context, remoteViews, appWidgetIds[i]);
            appWidgetManager.updateAppWidget(appWidgetIds[i], remoteViews);
        }
        super.onUpdate(context, appWidgetManager, appWidgetIds);
    }

    private void setRemoteAdapter(Context context, RemoteViews remoteViews, int appWidgetId) {
        // Set up the intent that starts the RemoteViewsService, which wil provide the views for this collection.
        Intent intent = new Intent(context, ExampleRemoteViewsService4Collections.class);
        // Add the app widget ID to the intent extras.
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        intent.setData(Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME)));

        // Set up the RemoteViews object to use a RemoteViews adapter.This adapter connects to a RemoteViewsService through the specified intent. This is how you populate the data.
        remoteViews.setRemoteAdapter(R.id.list_view, intent);

        // The empty view is displayed when the collection has no items.It should be in the same layout used to instantiate the RemoteViews object above.
        remoteViews.setEmptyView(R.id.list_view, R.id.empty_view);
    }

    private void addClick4Toast(Context context, RemoteViews remoteViews, int appWidgetId) {
        Intent toastIntent = new Intent(context, ExampleAppWidgetProvider4Collections.class);
        toastIntent.setAction(ExampleAppWidgetProvider4Collections.TOAST_ACTION);
        toastIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        // 将Intent包装成PendingIntent
        PendingIntent toastPendingIntent = PendingIntent.getBroadcast(context, 0, toastIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        remoteViews.setPendingIntentTemplate(R.id.list_view, toastPendingIntent);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (TOAST_ACTION.equals(intent.getAction())) {
            int viewIndex = intent.getIntExtra(EXTRA_ITEM, 0);
            Toast.makeText(context, "Click item " + (viewIndex + 1), Toast.LENGTH_SHORT).show();
        }
        super.onReceive(context, intent);
    }
}

