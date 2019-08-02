package com.hades.example.android.android_mechanism.msg_handler._main_2_thread_2_main.handler_thread;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
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
  TestMsgMain2Thread2Main_HandlerThread_Fragment: onCreate,send msg,msg=what=1000000,thread =1,main
  TestMsgMain2Thread2Main_HandlerThread_Fragment: ChildCallback,handleMessage,msg=what=1000000,thread =5063,heavyWork
  TestMsgMain2Thread2Main_HandlerThread_Fragment: ChildCallback,sendMessage,msg=what=1000000,obj=499999500000,thread =5063,heavyWork
  TestMsgMain2Thread2Main_HandlerThread_Fragment: mUIHandler,handleMessage,msg=what=1000000,obj=499999500000,thread =1,main
 */

/**
 * https://blog.csdn.net/u011240877/article/details/72905631
 * https://blog.csdn.net/javazejian/article/details/52426353
 */
public class TestMsgMain2Thread2Main_HandlerThread_Fragment extends BaseFragment {
    private static final String TAG = TestMsgMain2Thread2Main_HandlerThread_Fragment.class.getSimpleName();

    private int uppers = 1000000;

    private TextView test;
    private Handler mUIHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            /**
             * main
             */
            LogHelper.printThread(TAG, "mUIHandler,handleMessage", "what=" + msg.what + ",obj=" + msg.obj);
            String sum = (String) msg.obj;
            test.setText(sum);
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.msg_handler_main_2_thread_2_main_4_handlerthread, container, false);

        test = view.findViewById(R.id.text);
        //创建异步HandlerThread
        HandlerThread handlerThread = new HandlerThread("heavyWork");
        //必须先开启线程
        handlerThread.start();

        LogHelper.printThread(TAG, "onCreateView,send msg", "what=" + uppers);

        //子线程Handler
//        Handler threadHandler = new Handler(handlerThread.getLooper(), new ChildCallback());
        Handler threadHandler = new ThreadHandler(handlerThread.getLooper());
        /**
         * main -> thread
         */
        threadHandler.sendEmptyMessageDelayed(uppers, 1000);
        return view;
    }

    class ThreadHandler extends Handler {
        public ThreadHandler(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            /**
             * thread
             */
            LogHelper.printThread(TAG, "ChildCallback,handleMessage", "what=" + msg.what);

            long sum = MockHeavyWork.sum(msg.what);
            Message msg1 = new Message();
            msg1.what = msg.what;
            msg1.obj = String.valueOf(sum);

            LogHelper.printThread(TAG, "ChildCallback,sendMessage", "what=" + msg.what + ",obj=" + sum);

            /**
             * thread -> main
             */
            mUIHandler.sendMessage(msg1);
        }
    }

    /**
     * 该callback运行于子线程
     */
    class ChildCallback implements Handler.Callback {
        @Override
        public boolean handleMessage(Message msg) {
            LogHelper.printThread(TAG, "ChildCallback,handleMessage", "what=" + msg.what);

            long sum = MockHeavyWork.sum(msg.what);
            Message msg1 = new Message();
            msg1.what = msg.what;
            msg1.obj = String.valueOf(sum);

            LogHelper.printThread(TAG, "ChildCallback,sendMessage", "what=" + msg.what + ",obj=" + sum);
            mUIHandler.sendMessage(msg1);
            return false;
        }
    }
}