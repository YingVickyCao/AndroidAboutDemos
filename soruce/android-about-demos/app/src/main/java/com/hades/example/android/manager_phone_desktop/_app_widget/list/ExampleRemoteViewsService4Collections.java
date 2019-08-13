package com.hades.example.android.manager_phone_desktop._app_widget.list;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.hades.example.android.R;

import java.util.ArrayList;
import java.util.List;

public class ExampleRemoteViewsService4Collections extends RemoteViewsService {
    // 重写该方法，该方法返回一个RemoteViewsFactory对象
    // RemoteViewsFactory对象的作用类似于Adapter
    // 它负责为RemoteView中的指定组件提供多个列表项
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new StackRemoteViewsFactory(this.getApplicationContext(), intent);  // ①
    }

    class StackRemoteViewsFactory implements RemoteViewsFactory {
        // 定义一个数组来保存该组件生成的多个列表项
        private List<WidgetItem> widgetItems = new ArrayList<>();
        private Context mContext;
        private int appWidgetId;

        public StackRemoteViewsFactory(Context context, Intent intent) {
            mContext = context;
            appWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
        }

        @Override
        public void onCreate() {// must < =20 seconds, or ANR
            widgetItems.add(new WidgetItem(android.R.color.holo_red_dark));
            widgetItems.add(new WidgetItem(android.R.color.holo_green_dark));
            widgetItems.add(new WidgetItem(android.R.color.holo_orange_dark));
        }

        @Override
        public void onDestroy() {
            widgetItems = null;
        }

        @Override
        public int getCount() {
            return widgetItems.size();
        }

        @Override
        public RemoteViews getViewAt(int position) {// perform processing-intensive operations synchronously
            RemoteViews rv = new RemoteViews(mContext.getPackageName(), R.layout.app_widget_list_item);

            rv.setImageViewResource(R.id.img, widgetItems.get(position).getColor());
            rv.setTextViewText(R.id.tv, String.valueOf(position + 1));
            addClick4EachItem(rv, position);

            // 此处让线程暂停0.5秒来模拟加载该组件
            try {
                System.out.println("加载【" + position + "】位置的组件");
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return rv;
        }

        private void addClick4EachItem(RemoteViews rv, int position) {
            // set a fill-intent, which will be used to fill in the pending intent template that is set on the collection view.
            // Make it possible to distinguish the individual on-click action of a given item
            Intent fillInIntent = new Intent();
            fillInIntent.putExtra(ExampleAppWidgetProvider4Collections.EXTRA_ITEM, position);
            rv.setOnClickFillInIntent(R.id.img, fillInIntent);
        }

        @Override
        public RemoteViews getLoadingView() {
            return new RemoteViews(mContext.getPackageName(), R.layout.app_widget_list_progress_view);
        }

        @Override
        public int getViewTypeCount() {
            return 1;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public void onDataSetChanged() { // perform processing-intensive operations synchronously
        }
    }
}