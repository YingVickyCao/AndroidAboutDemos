package com.hades.example.android.other_ui.manager_phone_desktop._app_widget.clock;

import android.annotation.SuppressLint;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.RemoteViews;

import com.hades.example.android.Constant;
import com.hades.example.android.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AppWidgetProvider4LedClock extends AppWidgetProvider {
    private static final String TAG = AppWidgetProvider4LedClock.class.getSimpleName();

    private AppWidgetManager appWidgetManager;
    private Context context;

    private int[] digitsNumImages = new int[]{
            R.drawable.digital_num_01,
            R.drawable.digital_num_02,
            R.drawable.digital_num_03,
            R.drawable.digital_num_04,
            R.drawable.digital_num_05,
            R.drawable.digital_num_06,
            R.drawable.digital_num_07,
            R.drawable.digital_num_08,
            R.drawable.digital_num_09,
            R.drawable.digital_num_10,
    };

    private int[] digitViews = new int[]{ // HH:MM:SS
            R.id.digital_view_h_1,
            R.id.digital_view_h_2,
            R.id.digital_view_m_1,
            R.id.digital_view_m_2,
            R.id.digital_view_s_1,
            R.id.digital_view_s_2
    };

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        Log.d(TAG, "onUpdate: ");
        this.appWidgetManager = appWidgetManager;
        this.context = context;

        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(Constant.PERIOD_1_MINUTE);
                    handler.sendEmptyMessage(Constant.KEY_UPDATE_UI);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == Constant.KEY_UPDATE_UI) {
                RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.app_widget_clock);
                String time = getTimeStr_HHmmss(); // 200123 = 20:01:23
                Log.d(TAG, "handleMessage: " + time);
                for (int i = 0; i < time.length(); i++) {
                    int num = convertChart2Int(time.charAt(i));
                    views.setImageViewResource(digitViews[i], digitsNumImages[num]);
                }

                ComponentName componentName = new ComponentName(context, AppWidgetProvider4LedClock.class);
                appWidgetManager.updateAppWidget(componentName, views);
            }
            super.handleMessage(msg);
        }
    };

    private String getTimeStr_HHmmss() {
        SimpleDateFormat df = new SimpleDateFormat("HHmmss", Locale.getDefault());
        return df.format(new Date());
    }

    private int convertChart2Int(char time) {
        return Integer.valueOf(time + "");
    }
}