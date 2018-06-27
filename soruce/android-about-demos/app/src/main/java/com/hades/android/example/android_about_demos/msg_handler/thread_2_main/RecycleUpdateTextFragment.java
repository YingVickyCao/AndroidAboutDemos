package com.hades.android.example.android_about_demos.msg_handler.thread_2_main;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hades.android.example.android_about_demos.R;
import com.hades.android.example.android_about_demos.mock.LogHelper;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Thread -> main
 */
public class RecycleUpdateTextFragment extends Fragment {
    private static final String TAG = RecycleUpdateTextFragment.class.getSimpleName();

    String[] imageIds = new String[]{"java", "Android", "ajax", "swift"};
    int currentImageId = 0;
    private final int HANDLER_MSG_KEY_1 = 1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.msg_handler_thread_2_main_update_text, container, false);

        final TextView show = view.findViewById(R.id.show);

        final Handler myHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                /**
                 * 在主线程中执行
                 */
                LogHelper.logThreadInfo(TAG, "Handler -> handleMessage()", String.valueOf(currentImageId));

                if (msg.what == HANDLER_MSG_KEY_1) {
                    show.setText(imageIds[currentImageId++ % imageIds.length]);
                }
            }
        };

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                LogHelper.logThreadInfo(TAG, "Timer -> run()", String.valueOf(currentImageId));

                myHandler.sendEmptyMessage(HANDLER_MSG_KEY_1);
            }
        }, 0, 1200);
        return view;
    }
}



