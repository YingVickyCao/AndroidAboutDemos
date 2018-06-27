package com.hades.android.example.android_about_demos.msg_handler;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.widget.TextView;

import com.hades.android.example.android_about_demos.R;
import com.hades.android.example.android_about_demos.mock.LogHelper;
import com.hades.android.example.android_about_demos.mock.MockHeavyWork;

/**
 * main -> thread -> main
 * <p>
 * log
 * HandlerThreadActivity: onCreate,send msg,msg=what=1000000,thread =1,main
 * HandlerThreadActivity: ChildCallback,handleMessage,msg=what=1000000,thread =5063,heavyWork
 * HandlerThreadActivity: ChildCallback,sendMessage,msg=what=1000000,obj=499999500000,thread =5063,heavyWork
 * HandlerThreadActivity: mUIHandler,handleMessage,msg=what=1000000,obj=499999500000,thread =1,main
 */

/**
 * log
 * HandlerThreadActivity: onCreate,send msg,msg=what=1000000,thread =1,main
 * HandlerThreadActivity: ChildCallback,handleMessage,msg=what=1000000,thread =5063,heavyWork
 * HandlerThreadActivity: ChildCallback,sendMessage,msg=what=1000000,obj=499999500000,thread =5063,heavyWork
 * HandlerThreadActivity: mUIHandler,handleMessage,msg=what=1000000,obj=499999500000,thread =1,main
 */

/**
 * https://blog.csdn.net/u011240877/article/details/72905631
 * https://blog.csdn.net/javazejian/article/details/52426353
 */
public class HandlerThreadActivity extends Activity {
    private static final String TAG = HandlerThreadActivity.class.getSimpleName();

    private int uppers = 1000000;

    private TextView test;
    private Handler mUIHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            LogHelper.logThreadInfo(TAG, "mUIHandler,handleMessage", "what=" + msg.what + ",obj=" + msg.obj);
            String sum = (String) msg.obj;
            test.setText(sum);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_thread);
        test = findViewById(R.id.text);

        //创建异步HandlerThread
        HandlerThread handlerThread = new HandlerThread("heavyWork");
        //必须先开启线程
        handlerThread.start();

        //子线程Handler
        Handler childHandler = new Handler(handlerThread.getLooper(), new ChildCallback());
        childHandler.sendEmptyMessageDelayed(uppers, 1000);
        LogHelper.logThreadInfo(TAG, "onCreate,send msg", "what=" + uppers);
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