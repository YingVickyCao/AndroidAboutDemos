package com.hades.example.android.resource.drawable.clip;

import android.graphics.drawable.ClipDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.hades.example.android.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 从中间开始截取拖图片，截取方向为水平方向。
 * 实现图片徐徐张开的效果。
 */
public class TestClipDrawableFragment extends Fragment {
    private static final String TAG = TestClipDrawableFragment.class.getSimpleName();

    final int KEY_LEVEL = 1000;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.res_drawable_clip, container, false);
        ImageView imageview = view.findViewById(R.id.imageView);

        // 获取图片所显示的ClipDrawable对象
        final ClipDrawable drawable = (ClipDrawable) imageview.getDrawable();

        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == KEY_LEVEL) {
                    Log.d(TAG, "handleMessage: drawable level=" + drawable.getLevel());
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

                handler.sendMessage(msg);
                // 取消定时器
                if (drawable.getLevel() >= 10000) {
                    timer.cancel();
                }
            }
        }, 0, 300);

        return view;
    }
}