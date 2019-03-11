package com.hades.android.example.android_about_demos.msg_handler.thread_2_main;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.hades.android.example.android_about_demos.R;
import com.hades.android.example.android_about_demos.utils.LogHelper;
import com.hades.android.example.android_about_demos.mock.MockHeavyWork;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Thread -> main
 */
public class MsgThread2MainFragment extends Fragment {
    private static final String TAG = MsgThread2MainFragment.class.getSimpleName();

    String[] imageIds = new String[]{"java", "Android", "ajax", "swift"};
    int currentImageId = 0;
    private final int HANDLER_MSG_KEY_1 = 1;
    private final int HANDLER_MSG_KEY_2 = 2;
    private final int HANDLER_MSG_KEY_3 = 3;
    private Handler uiHandler;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.msg_handler_thread_2_main_update_text, container, false);

        final TextView show = view.findViewById(R.id.tableContentList);
        view.findViewById(R.id.startRecycleUpdateText).setOnClickListener(v -> startRecycleUpdateText());
        view.findViewById(R.id.missLooperWhenCreateHandlerInThread).setOnClickListener(v -> missLooperWhenCreateHandlerInThread());
        view.findViewById(R.id.thread2Main_createHandler_with_Looper_getMainLooper).setOnClickListener(v -> thread2Main_createHandler_with_Looper_getMainLooper());

        uiHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                /**
                 * 在主线程中执行
                 */

                if (msg.what == HANDLER_MSG_KEY_1) {
                    LogHelper.printThreadInfo(TAG, "uiHandler -> handleMessage()", String.valueOf(currentImageId));
                    show.setText(imageIds[currentImageId++ % imageIds.length]);
                }

                if (msg.what == HANDLER_MSG_KEY_3) {
                    LogHelper.printThreadInfo(TAG, "uiHandler,handleMessage", "what=" + msg.what + ",obj=" + msg.obj);
                    Toast.makeText(getActivity(), (String) msg.obj, Toast.LENGTH_SHORT).show();
                }
            }
        };
        return view;
    }

    private void startRecycleUpdateText() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                /***
                 * 子线程中执行
                 */
                LogHelper.printThreadInfo(TAG, "Timer -> run()", String.valueOf(currentImageId));

                uiHandler.sendEmptyMessage(HANDLER_MSG_KEY_1);
            }
        }, 0, 1200);
    }

    private void missLooperWhenCreateHandlerInThread() {
        /**
         * QA：Can't create handler inside thread that has not called Looper.prepare()
         * ERROR:
         * E/AndroidRuntime: FATAL EXCEPTION: Thread-5
         java.lang.RuntimeException: Can't create handler inside thread that has not called Looper.prepare()

         A：在 thread 创建时，默认不带Looper。
         */
        new Thread(new Runnable() {

            @Override
            public void run() {
                MockHeavyWork.sum();
                Handler lab2Handler = new Handler();
                lab2Handler.sendMessage(defineNewMessage(HANDLER_MSG_KEY_2, "Lab2"));
            }

        }).start();
    }

    public Message defineNewMessage(int key, String messageContent) {
        Message message = new Message();
        message.what = key;
        message.obj = messageContent;
        return message;
    }

    /**
     * 这种方式实际开发时，用不上
     */
    private void thread2Main_createHandler_with_Looper_getMainLooper() {
        new Thread(new Runnable() {

            @Override
            public void run() {
                Handler lab3Handler;
                long result = new MockHeavyWork().sum();
                LogHelper.printThreadInfo(TAG, "thread2Main_createHandler_with_Looper_getMainLooper()->run()", String.valueOf(result));

                lab3Handler = new Handler(Looper.getMainLooper()) {

                    @Override
                    public void handleMessage(Message msg) {
                        super.handleMessage(msg);
                        /**
                         * main
                         */
                        LogHelper.printThreadInfo(TAG, "thread2Main_createHandler_with_Looper_getMainLooper()->run()->handleMessage()", "what=" + msg.what + ",obj=" + msg.obj);
                    }
                };
                /**
                 * thread -> main
                 */
                lab3Handler.sendMessage(defineNewMessage(HANDLER_MSG_KEY_3, "getMainLooper"));
            }

        }).start();
    }

}



