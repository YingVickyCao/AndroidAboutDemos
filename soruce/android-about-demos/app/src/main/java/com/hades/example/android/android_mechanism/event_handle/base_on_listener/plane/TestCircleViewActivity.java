package com.hades.example.android.android_mechanism.event_handle.base_on_listener.plane;


import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.hades.example.android.R;
import com.hades.example.android.lib.base.BaseActivity;

public class TestCircleViewActivity extends BaseActivity {
    private static final String TAG = TestCircleViewActivity.class.getSimpleName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 去掉窗口标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 全屏显示
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // 获取窗口管理器
        WindowManager windowManager = getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        // 获得屏幕宽和高
        display.getMetrics(metrics);


        setContentView(R.layout.event_handle_customview);
        CircleView1 planeView = findViewById(R.id.circleView);
        findViewById(R.id.circleView).setOnTouchListener((v, event) -> onTouchPlaneView(v, event, planeView));
    }

    private boolean onTouchPlaneView(View v, MotionEvent event, CircleView1 planeView) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            planeView.currentX = event.getX();
            planeView.currentY = event.getY();
            Log.d(TAG, "onTouchPlaneView: ACTION_UP,x=" + planeView.currentX + ",y=" + planeView.currentY);
        }
        // 通知planeView组件重绘
        // View.invalidate() => View.onDraw()
        planeView.invalidate();
        return true;
    }
}