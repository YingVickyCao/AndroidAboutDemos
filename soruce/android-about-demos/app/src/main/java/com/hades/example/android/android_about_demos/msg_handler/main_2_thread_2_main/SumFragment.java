package com.hades.example.android.android_about_demos.msg_handler.main_2_thread_2_main;

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

import com.hades.example.android.android_about_demos.R;
import com.hades.example.android.android_about_demos.utils.LogHelper;
import com.hades.example.android.android_about_demos.mock.MockHeavyWork;

/**
 * main -> thread -> main
 */

/*
 log:
 SumFragment: sum(),msg=1000,thread =1,main
 SumFragment: SumThread -> handleMessage(),msg=1000,thread =4487,Thread-7
 SumFragment: updateResult(),msg=499500,thread =1,main
 */

public class SumFragment extends Fragment {
    private static final String TAG = SumFragment.class.getSimpleName();

    static final String UPPER_NUM = "upper";
    private final int num = 1000;

    SumThread calThread;
    private TextView result;

    private final int HANDLER_MSG_KEY_1 = 1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.msg_handler_main_2_thread_sum, container, false);

        view.findViewById(R.id.sum).setOnClickListener(this::sum);
        result = view.findViewById(R.id.result);

        calThread = new SumThread();
        calThread.start();
        return view;
    }

    public void sum(View source) {
        LogHelper.printThreadInfo(TAG, "sum()", String.valueOf(num));
        /**
         * main -> thread
         */
        calThread.mHandler.sendMessage(createMessage());
    }

    private Message createMessage() {
        Message msg = new Message();
        msg.what = HANDLER_MSG_KEY_1;
        Bundle bundle = new Bundle();
        bundle.putInt(UPPER_NUM, num);
        msg.setData(bundle);
        return msg;
    }

    class SumThread extends Thread {
        public Handler mHandler;

        public void run() {
            Looper.prepare();

            mHandler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    /**
                     * 在子线程中执行
                     */
                    if (msg.what == HANDLER_MSG_KEY_1) {
                        int upper = msg.getData().getInt(UPPER_NUM);
                        LogHelper.printThreadInfo(TAG, "SumThread -> handleMessage()", String.valueOf(upper));
                        long sum = MockHeavyWork.sum(upper);

                        /**
                         * thread -> main
                         */
                        updateResult(sum);
                    }
                }
            };

            Looper.loop();
        }
    }


    private void updateResult(long sum) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                LogHelper.printThreadInfo(TAG, "updateResult()", String.valueOf(sum));
                result.setText(String.valueOf(sum));
            }
        });
    }

}

