package com.hades.example.android.manager_phone_desktop.app_widget.list;

import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.hades.example.android.R;

public class RemoteViewsService4List extends RemoteViewsService {
    // 重写该方法，该方法返回一个RemoteViewsFactory对象
    // RemoteViewsFactory对象的作用类似于Adapter
    // 它负责为RemoteView中的指定组件提供多个列表项
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new StackRemoteViewsFactory(this.getApplicationContext(), intent);  // ①
    }

    class StackRemoteViewsFactory implements RemoteViewsFactory {
        // 定义一个数组来保存该组件生成的多个列表项
        private int[] items = null;
        private Context mContext;

        public StackRemoteViewsFactory(Context context, Intent intent) {
            mContext = context;
        }

        @Override
        public void onCreate() {
            items = new int[]{R.drawable.digital_num_02, R.drawable.digital_num_03, R.drawable.digital_num_04};
        }

        @Override
        public void onDestroy() {
            items = null;
        }

        // 该方法的返回值控制该对象包含多少个列表项
        @Override
        public int getCount() {
            return items.length;
        }

        // 该方法的返回值控制各位置所显示的RemoteViews
        @Override
        public RemoteViews getViewAt(int position) {
            RemoteViews rv = new RemoteViews(mContext.getPackageName(), R.layout.app_widget_list_item);
            rv.setImageViewResource(R.id.img, items[position]);
            rv.setTextViewText(R.id.tv, String.valueOf(position + 1));

            // 创建Intent、用于传递数据
            Intent fillInIntent = new Intent();
            fillInIntent.putExtra(AppWidgetProvider4List.EXTRA_ITEM, position);
            // 设置当单击该RemoteViews时传递fillInIntent包含的数据
            rv.setOnClickFillInIntent(R.id.img, fillInIntent);
            // 此处让线程暂停0.5秒来模拟加载该组件
            try {
                System.out.println("加载【" + position + "】位置的组件");
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return rv;
        }

        @Override
        public RemoteViews getLoadingView() {
            return null;
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
        public void onDataSetChanged() {
        }
    }
}