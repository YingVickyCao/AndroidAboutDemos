/**
 * Analyse Handler of Android
 */
package com.hades.android.example.android_about_demos.temp.sundy.async;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

import com.hades.android.example.android_about_demos.R;
import com.hades.android.example.android_about_demos.mock.LogHelper;
import com.hades.android.example.android_about_demos.mock.MockHeavyWork;
import com.hades.android.example.android_about_demos.temp.sundy.async.handller.DelayHandler;
import com.hades.android.example.android_about_demos.temp.sundy.async.handller.SubHandlerThread;

/**
 * @author sundy
 */
public class HandlerConceptActivity extends Activity {
    private static final String TAG = HandlerConceptActivity.class.getSimpleName();

    public final String HANDLER_KEY = "HANDLER_KEY";
    Handler mainHandler1;
    Handler mainHandler2;

    public long sum() {
        return new MockHeavyWork().sum();
    }

    //Define a common Message , I will invoke it in different labs .
    public Message defineNewMessage(String messageContent) {
        Message returnMsg = new Message();
        Bundle data = new Bundle();
        data.putString(HANDLER_KEY, messageContent);
        returnMsg.setData(data);
        return returnMsg;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_handlerconcept);
        initMainHandler1();
        initMainHandler2();

        //Handler Lab1
        findViewById(R.id.buttonHandlerLab1).setOnClickListener(v -> buttonHandlerLab1());
        //Handler Lab2.1 , in new thread create handler send message .
        findViewById(R.id.missLooperWhenCreateHandlerInThread).setOnClickListener(v -> missLooperWhenCreateHandlerInThread());
        //Handler Lab2.2
        findViewById(R.id.buttonHandlerLab2_2).setOnClickListener(v -> handlerThread2itself());
        //Handler Lab3.1 child thread get main thread's looper and send message .
        findViewById(R.id.thread2Main_createHandler_with_Looper_getMainLooper).setOnClickListener(v -> thread2Main_createHandler_with_Looper_getMainLooper());
        //Handler Lab3.2 using looper.prepare() and looper.loop()
        findViewById(R.id.thread2Itself_Handler_with_Looper_prepare_and_loop).setOnClickListener(v -> thread2Itself_Handler_with_Looper_prepare_and_loop());
        findViewById(R.id.buttonHandlerLab4).setOnClickListener(v -> buttonHandlerLab4());

        HandlerThread lab5Thread = new HandlerThread("lab5_thread");
        lab5Thread.start();
        final Handler lab5Handler = new DelayHandler(this, lab5Thread.getLooper());
        findViewById(R.id.buttonHandlerLab5_1).setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
//                buttonHandlerLab5_1();
                Message message = defineNewMessage("Lab5");
                message.what = 1;
                lab5Handler.sendMessage(message);
            }
        });

        findViewById(R.id.buttonHandlerLab5_2).setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                lab5Handler.removeMessages(1);
            }
        });

    }

    private void initMainHandler1() {
        mainHandler1 = new Handler() {

            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                logThreadInfo("mainHandler1 -> handleMessage: ", msg.getData().getString(HANDLER_KEY));
            }
        };
    }

    public void logThreadInfo(String funcInfo, String msg) {
        Log.d(TAG, funcInfo + ",msg=" + msg + ",thread =" + Thread.currentThread().getId() + "," + Thread.currentThread().getName());
    }

    private void initMainHandler2() {
        mainHandler2 = new Handler() {

            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                logThreadInfo("mainHandler2 -> handleMessage: ", msg.getData().getString(HANDLER_KEY));
            }

        };
    }

    private void buttonHandlerLab1() {
        // TODO Auto-generated method stub
        new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                sum();
                mainHandler1.sendMessage(defineNewMessage("Lab1"));
                mainHandler2.sendMessage(defineNewMessage("Lab1"));

            }

        }).start();
    }

    private void missLooperWhenCreateHandlerInThread() {
        /**
         * QA：Can't create handler inside thread that has not called Looper.prepare()
         * ERROR:
         * E/AndroidRuntime: FATAL EXCEPTION: Thread-5
         *Process: com.hades.android.example.android_about_demos, PID: 2668
         java.lang.RuntimeException: Can't create handler inside thread that has not called Looper.prepare()

         A：在 thread 创建时，默认不带Looper。
         */
        new Thread(new Runnable() {

            @Override
            public void run() {
                sum();
                Handler lab2Handler = new Handler();
                lab2Handler.sendMessage(defineNewMessage("Lab2"));
            }

        }).start();
    }

    private void handlerThread2itself() {
        //using HandlerThread to make handler (message queue) ,send message
        // TODO: 27/06/2018 SumThread
        SubHandlerThread subHandlerThread = new SubHandlerThread(HandlerConceptActivity.this, "SumThread");
        subHandlerThread.start();
    }

    private void thread2Main_createHandler_with_Looper_getMainLooper() {
        new Thread(new Runnable() {

            @Override
            public void run() {
                Handler lab3Handler;
                long result = new MockHeavyWork().sum();
                LogHelper.logThreadInfo(TAG, "thread2Main_createHandler_with_Looper_getMainLooper()->run()", String.valueOf(result));
                lab3Handler = new Handler(Looper.getMainLooper()) {

                    @Override
                    public void handleMessage(Message msg) {
                        super.handleMessage(msg);
                        LogHelper.logThreadInfo(TAG, "thread2Main_createHandler_with_Looper_getMainLooper()->run()->handleMessage()", String.valueOf(result));
                    }
                };
                lab3Handler.sendMessage(defineNewMessage("Lab3"));
            }

        }).start();
    }

    private void thread2Itself_Handler_with_Looper_prepare_and_loop() {
        new Thread(new Runnable() {

            @Override
            public void run() {
                long result = sum();
                LogHelper.logThreadInfo(TAG, "thread2Itself_Handler_with_Looper_prepare_and_loop()->run()", String.valueOf(result));

                Looper.prepare();

                Handler lab3Handler;
                lab3Handler = new Handler() {

                    @Override
                    public void handleMessage(Message msg) {
                        super.handleMessage(msg);
                        LogHelper.logThreadInfo(TAG, "thread2Itself_Handler_with_Looper_prepare_and_loop()->run()->handleMessage()", String.valueOf(result));
                    }
                };

                lab3Handler.sendMessage(defineNewMessage("Lab3"));

                Looper.loop();
            }

        }).start();
    }

    private void buttonHandlerLab4() {
        HandlerThread lab4Thread = new HandlerThread("lab4_thread");
        lab4Thread.start();
        Handler lab4Handler = new DelayHandler(HandlerConceptActivity.this, lab4Thread.getLooper());
        lab4Handler.sendMessage(defineNewMessage("Lab4  A"));
        lab4Handler.sendMessage(defineNewMessage("Lab4  B"));
    }


}