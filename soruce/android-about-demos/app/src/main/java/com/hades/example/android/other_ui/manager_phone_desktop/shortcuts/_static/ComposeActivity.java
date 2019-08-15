package com.hades.example.android.other_ui.manager_phone_desktop.shortcuts._static;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.hades.example.android.R;

import java.util.Timer;
import java.util.TimerTask;

public class ComposeActivity extends Activity {
    ImageView flower;
    // 定义两份动画资源
    Animation anim, reverse;

    final Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0x123) {
                flower.startAnimation(reverse);
            }
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_shorts_layout);
        flower = (ImageView) findViewById(R.id.flower);
        // 加载第一份动画资源
        anim = AnimationUtils.loadAnimation(this, R.anim.anim);
        // 设置动画结束后保留结束状态
        anim.setFillAfter(true);
        // 加载第二份动画资源
        reverse = AnimationUtils.loadAnimation(this, R.anim.reverse);
        // 设置动画结束后保留结束状态
        reverse.setFillAfter(true);
        Button bn = (Button) findViewById(R.id.bn);
        // 为按钮的单击事件添加监听器
        bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View source) {
                // 创建添加快捷方式的Intent
                Intent addIntent = new Intent(
                        "com.android.launcher.action.INSTALL_SHORTCUT"); // ①
                String title = getResources().getString(R.string.title);
                // 加载快捷方式的图标
                Parcelable icon = Intent.ShortcutIconResource.fromContext(ComposeActivity.this, R.drawable.ic_launcher);
                // 创建点击快捷方式后对应的Intent，该处指定当点击创建的快捷方式后
                // 将再次启动该程序
                Intent myIntent = new Intent(ComposeActivity.this, ComposeActivity.class);
                // 设置快捷方式的标题
                addIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, title); // ②
                // 设置快捷方式的图标
                addIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE
                        , icon); // ②
                // 设置快捷方式对应的Intent
                addIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT
                        , myIntent); // ②
                // 发送广播添加快捷方式
                sendBroadcast(addIntent); // ③
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        // 开始执行动画
        flower.startAnimation(anim);
        // 设置3.5秒后启动第二个动画
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(0x123);
            }
        }, 3500);
    }
}

