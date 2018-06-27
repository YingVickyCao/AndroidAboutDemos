package com.hades.android.example.android_about_demos.msg_handler.main_2_thread_2_main.handler_thread;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hades.android.example.android_about_demos.R;
import com.hades.android.example.android_about_demos.mock.LogHelper;
import com.hades.android.example.android_about_demos.mock.MockHeavyWork;

/**
 * main -> thread -> main
 */

/*
log:
  HandlerThreadFragment: onCreate,send msg,msg=what=1000000,thread =1,main
  HandlerThreadFragment: ChildCallback,handleMessage,msg=what=1000000,thread =5063,heavyWork
  HandlerThreadFragment: ChildCallback,sendMessage,msg=what=1000000,obj=499999500000,thread =5063,heavyWork
  HandlerThreadFragment: mUIHandler,handleMessage,msg=what=1000000,obj=499999500000,thread =1,main
 */

/**
 * https://blog.csdn.net/u011240877/article/details/72905631
 * https://blog.csdn.net/javazejian/article/details/52426353
 */
public class HandlerThreadFragment extends Fragment {
    private static final String TAG = HandlerThreadFragment.class.getSimpleName();

    private int uppers = 1000000;

    private TextView test;
    private Handler mUIHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            /**
             * main
             */
            LogHelper.logThreadInfo(TAG, "mUIHandler,handleMessage", "what=" + msg.what + ",obj=" + msg.obj);
            String sum = (String) msg.obj;
            test.setText(sum);
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_handler_thread, container, false);

        test = view.findViewById(R.id.text);
        //创建异步HandlerThread
        HandlerThread handlerThread = new HandlerThread("heavyWork");
        //必须先开启线程
        handlerThread.start();

        LogHelper.logThreadInfo(TAG, "onCreateView,send msg", "what=" + uppers);

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
            LogHelper.logThreadInfo(TAG, "ChildCallback,handleMessage", "what=" + msg.what);

            long sum = MockHeavyWork.sum(msg.what);
            Message msg1 = new Message();
            msg1.what = msg.what;
            msg1.obj = String.valueOf(sum);

            LogHelper.logThreadInfo(TAG, "ChildCallback,sendMessage", "what=" + msg.what + ",obj=" + sum);

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
            LogHelper.logThreadInfo(TAG, "ChildCallback,handleMessage", "what=" + msg.what);

            long sum = MockHeavyWork.sum(msg.what);
            Message msg1 = new Message();
            msg1.what = msg.what;
            msg1.obj = String.valueOf(sum);

            LogHelper.logThreadInfo(TAG, "ChildCallback,sendMessage", "what=" + msg.what + ",obj=" + sum);
            mUIHandler.sendMessage(msg1);
            return false;
        }
    }
}