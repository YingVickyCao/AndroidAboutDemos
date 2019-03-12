package com.hades.example.android.resource.drawable.clip;

import android.app.Activity;
import android.graphics.drawable.ClipDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

import com.hades.example.android.android_about_demos.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 从中间开始截取拖图片，截取方向为水平方向。
 * 实现图片徐徐张开的效果。
 */
public class ClipDrawableActivity extends Activity {
    final int KEY_LEVEL = 1000;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.res_drawable_clip);

        ImageView imageview = findViewById(R.id.imageView);

        // 获取图片所显示的ClipDrawable对象
        final ClipDrawable drawable = (ClipDrawable) imageview.getDrawable();

        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                // 如果该消息是本程序所发送的z
                if (msg.what == KEY_LEVEL) {
                    // 修改ClipDrawable的level值
                    drawable.setLevel(drawable.getLevel() + 200);
                }
            }
        };

        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Message msg = new Message();
                msg.what = KEY_LEVEL;

                // 发送消息，通知应用修改ClipDrawable对象的level值。
                handler.sendMessage(msg);
                // 取消定时器
                if (drawable.getLevel() >= 10000) {
                    timer.cancel();
                }
            }
        }, 0, 300);
    }
}