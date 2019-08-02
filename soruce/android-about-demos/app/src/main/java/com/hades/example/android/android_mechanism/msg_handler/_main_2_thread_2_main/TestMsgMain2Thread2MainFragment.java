package com.hades.example.android.android_mechanism.msg_handler._main_2_thread_2_main;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.hades.example.android.R;
import com.hades.example.android.lib.base.BaseFragment;
import com.hades.example.android.lib.mock.MockHeavyWork;
import com.hades.example.android.lib.utils.LogHelper;

/**
 * main -> thread -> main
 */

/*
 log:
 TestMsgMain2Thread2MainFragment: sum(),msg=1000,thread =1,main
 TestMsgMain2Thread2MainFragment: SumThread -> handleMessage(),msg=1000,thread =4487,Thread-7
 TestMsgMain2Thread2MainFragment: updateResult(),msg=499500,thread =1,main
 */

public class TestMsgMain2Thread2MainFragment extends BaseFragment {
    private static final String TAG = TestMsgMain2Thread2MainFragment.class.getSimpleName();

    static final String UPPER_NUM = "upper";
    private final int num = 1000;

    SumThread calThread;
    private TextView result;

    private final int HANDLER_MSG_KEY_1 = 1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.msg_handler_main_2_thread_2_main, container, false);

        view.findViewById(R.id.sum).setOnClickListener(this::sum);
        result = view.findViewById(R.id.result);

        calThread = new SumThread();
        calThread.start();
        return view;
    }

    public void sum(View source) {
        LogHelper.printThread(TAG, "sum()", String.valueOf(num));
        /**
         * main -> thread
         */
        calThread.mHandlerOfThread.sendMessage(createMessage());
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
        public Handler mHandlerOfThread;

        public void run() {
            Looper.prepare();

            mHandlerOfThread = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    /**
                     * 在子线程中执行
                     */
                    if (msg.what == HANDLER_MSG_KEY_1) {
                        int upper = msg.getData().getInt(UPPER_NUM);
                        LogHelper.printThread(TAG, "SumThread -> handleMessage()", String.valueOf(upper));
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
                LogHelper.printThread(TAG, "updateResult()", String.valueOf(sum));
                result.setText(String.valueOf(sum));
            }
        });
    }

}

