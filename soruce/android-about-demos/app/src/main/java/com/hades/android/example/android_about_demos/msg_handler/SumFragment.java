package com.hades.android.example.android_about_demos.msg_handler;

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

import com.hades.android.example.android_about_demos.R;
import com.hades.android.example.android_about_demos.mock.LogHelper;

/**
 * main -> thread -> main
 */
public class SumFragment extends Fragment {
    private static final String TAG = SumFragment.class.getSimpleName();

    static final String UPPER_NUM = "upper";
    private final int num = 1000;

    CalThread calThread;
    private TextView result;

    private final int HANDLER_MSG_KEY_1 = 1;

    /**
     * log:
     * SumFragment: sum(),msg=1000,thread =1,main
     * SumFragment: CalThread -> handleMessage(),msg=1000,thread =4487,Thread-7
     * SumFragment: updateResult(),msg=499500,thread =1,main
     */

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.msg_handler_main_2_thread_sum, container, false);

        view.findViewById(R.id.sum).setOnClickListener(this::sum);
        result = view.findViewById(R.id.result);

        calThread = new CalThread();
        calThread.start();
        return view;
    }

    public void sum(View source) {
        LogHelper.logThreadInfo(TAG, "sum()", String.valueOf(num));
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

    class CalThread extends Thread {
        public Handler mHandler;

        public void run() {
            Looper.prepare();

            mHandler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    if (msg.what == HANDLER_MSG_KEY_1) {
                        int upper = msg.getData().getInt(UPPER_NUM);
                        LogHelper.logThreadInfo(TAG, "CalThread -> handleMessage()", String.valueOf(upper));

                        long sum = 0;
                        for (int i = 0; i < upper; i++) {
                            sum += i;
                        }
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
                LogHelper.logThreadInfo(TAG, "updateResult()", String.valueOf(sum));
                result.setText(String.valueOf(sum));
            }
        });
    }

}

