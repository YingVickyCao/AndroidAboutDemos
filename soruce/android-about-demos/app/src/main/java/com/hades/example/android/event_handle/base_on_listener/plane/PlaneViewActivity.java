package com.hades.example.android.event_handle.base_on_listener.plane;


import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.hades.example.android.android_about_demos.R;

public class PlaneViewActivity extends Activity {
    private static final String TAG = PlaneViewActivity.class.getSimpleName();

    // 定义飞机的移动速度
    private int speed = 10;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 去掉窗口标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        // 全屏显示
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // 创建PlaneView组件
        final PlaneView planeView = new PlaneView(this);
        planeView.setBackgroundResource(R.drawable.bg);

        // 获取窗口管理器
        WindowManager windowManager = getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        // 获得屏幕宽和高
        display.getMetrics(metrics);
        // 设置飞机的初始位置
        planeView.currentX = metrics.widthPixels / 2;
        planeView.currentY = metrics.heightPixels - 100;

        // 为planeView的Touch事件绑定监听器
        planeView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {

                    return true;
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    planeView.currentX = event.getX();
                    planeView.currentY = event.getY();
                }

                // 通知planeView组件重绘
                planeView.invalidate();
                return true;
            }
        });

        setContentView(planeView);
    }
}

