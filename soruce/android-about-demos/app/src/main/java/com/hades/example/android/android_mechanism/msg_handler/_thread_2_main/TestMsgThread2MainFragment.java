package com.hades.example.android.android_mechanism.msg_handler._thread_2_main;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.hades.example.android.R;
import com.hades.example.android.lib.base.BaseFragment;
import com.hades.example.android.lib.mock.MockHeavyWork;
import com.hades.example.android.lib.utils.LogHelper;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Thread -> main
 */
public class TestMsgThread2MainFragment extends BaseFragment {
    private static final String TAG = TestMsgThread2MainFragment.class.getSimpleName();

    String[] imageIds = new String[]{"java", "Android", "ajax", "swift"};
    int currentImageId = 0;
    private final int HANDLER_MSG_KEY_1 = 1;
    private final int HANDLER_MSG_KEY_2 = 2;
    private final int HANDLER_MSG_KEY_3 = 3;
    private Handler uiHandler;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.msg_handler_thread_2_main, container, false);

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
                    LogHelper.printThread(TAG, "uiHandler -> handleMessage()", String.valueOf(currentImageId));
                    show.setText(imageIds[currentImageId++ % imageIds.length]);
                }

                if (msg.what == HANDLER_MSG_KEY_3) {
                    LogHelper.printThread(TAG, "uiHandler,handleMessage", "what=" + msg.what + ",obj=" + msg.obj);
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
                LogHelper.printThread(TAG, "Timer -> run()", String.valueOf(currentImageId));

                /**
                 * Thread -> UI
                 */
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
                Handler uiHandler;
                long result = MockHeavyWork.sum(10);
                LogHelper.printThread(TAG, "thread2Main_createHandler_with_Looper_getMainLooper()->run()", String.valueOf(result));

                uiHandler = new Handler(Looper.getMainLooper()) {

                    @Override
                    public void handleMessage(Message msg) {
                        super.handleMessage(msg);
                        /**
                         * main
                         */
                        LogHelper.printThread(TAG, "uiHandler", "what=" + msg.what + ",obj=" + msg.obj);
                        showToast(String.valueOf(msg.what));
                    }
                };
                /**
                 * thread -> main
                 */
                uiHandler.sendMessage(defineNewMessage(HANDLER_MSG_KEY_3, "getMainLooper"));
            }

        }).start();
    }

}



