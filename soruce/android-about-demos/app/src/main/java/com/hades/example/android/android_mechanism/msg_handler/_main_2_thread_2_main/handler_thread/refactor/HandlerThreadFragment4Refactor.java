package com.hades.example.android.android_mechanism.msg_handler._main_2_thread_2_main.handler_thread.refactor;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.hades.example.android.R;
import com.hades.example.android.lib.base.BaseFragment;
import com.hades.example.android.lib.utils.LogHelper;

import static com.hades.example.android.android_mechanism.msg_handler._main_2_thread_2_main.handler_thread.refactor.SumThread.KEY_FINISH;
import static com.hades.example.android.android_mechanism.msg_handler._main_2_thread_2_main.handler_thread.refactor.SumThread.KEY_SUM;

/**
 * main -> thread -> main
 */

/*
log:
SumThread:
    ChildCallback,handleMessage(),msg=num=10,thread =6923,heavyWork
    ChildCallback,handleMessage() -> sendMessage,msg=num=1,sum=45,thread =6923,heavyWork

    ChildCallback,handleMessage(),msg=num=1000,thread =6923,heavyWork
    ChildCallback,handleMessage() -> sendMessage,msg=num=1,sum=499500,thread =6923,heavyWork

    ChildCallback,handleMessage(),msg=num=10000,thread =6923,heavyWork

SumThread:
    ChildCallback,handleMessage() -> sendMessage,msg=num=1,sum=49995000,thread =6923,heavyWork

HandlerThreadFragment4Refactor: mUIHandler,handleMessage,msg=num=10,sum=45,thread =1,main
HandlerThreadFragment4Refactor: mUIHandler,handleMessage,msg=num=1000,sum=499500,thread =1,main
                                mUIHandler,handleMessage,msg=num=10000,sum=49995000,thread =1,main
                                mUIHandler,handleMessage,msg=num=0,sum=null,thread =1,main
 */

/**
 * https://blog.csdn.net/u011240877/article/details/72905631
 * https://blog.csdn.net/javazejian/article/details/52426353
 */
public class HandlerThreadFragment4Refactor extends BaseFragment implements Handler.Callback {
    private static final String TAG = HandlerThreadFragment4Refactor.class.getSimpleName();

    private SumThread mSumThread;
    private Handler mUIHandler;

    private TextView result;
    private Button sumBtn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.msg_handler_main_2_thread_2_main_4_handlerthread_4_refactor, container, false);

        result = view.findViewById(R.id.result);
        sumBtn = view.findViewById(R.id.sum);
        sumBtn.setOnClickListener(v -> sum());

        mUIHandler = new Handler(this);

        mSumThread = new SumThread("heavyWork");
        mSumThread.setUIHandler(mUIHandler);
        mSumThread.setDownloadUrls(10, 1000, 10000);
        return view;
    }

    private void sum() {
        mSumThread.start();
        sumBtn.setEnabled(false);
        sumBtn.setText("Sum start");
    }

    private void showResult(String sum) {
        result.setText(result.getText().toString() + "\n " + sum);
    }

    private void resetSumBtnText() {
        sumBtn.setText("Sum");
    }

    @Override
    public boolean handleMessage(Message msg) {
        LogHelper.printThread(TAG, "mUIHandler,handleMessage", "num=" + msg.arg1 + ",sum=" + msg.obj);
        switch (msg.what) {
            case KEY_SUM:
                showResult((String) msg.obj);
                return true;

            case KEY_FINISH:
                resetSumBtnText();
                return true;

            default:
                return false;

        }
    }

}