package com.hades.example.android.android_mechanism.msg_handler._main_2_thread_2_main.handler_thread.refactor;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;

import com.hades.example.android.lib.mock.MockHeavyWork;
import com.hades.example.android.lib.utils.LogHelper;

import java.util.Arrays;
import java.util.List;

public class SumThread extends HandlerThread implements Handler.Callback {
    private static final String TAG = SumThread.class.getSimpleName();

    // 子线程 Handler
    private Handler mWorkerHandler;
    private Handler mUIHandler;

    private List<Integer> mNumList;
    private int sumedNum = 0;
    public static final int KEY_NUM = 1;
    public static final int KEY_SUM = 2;
    public static final int KEY_FINISH = 3;

    public SumThread(String name) {
        super(name);
    }

    // /执行初始化任务
    @Override
    protected void onLooperPrepared() {
        super.onLooperPrepared();
        //使用子线程中的 Looper
        mWorkerHandler = new Handler(getLooper(), this);

        //将接收到的任务消息挨个添加到消息队列中
        for (Integer num : mNumList) {
            mWorkerHandler.sendMessage(createMessage4Num(num));
        }
    }

    private Message createMessage4Num(Integer num) {
        Message message = mWorkerHandler.obtainMessage();
        message.what = KEY_NUM;
        message.arg1 = num;
        return message;
    }

    private Message createMessage4Sum(Long sum) {
        Message message = mWorkerHandler.obtainMessage();
        message.what = KEY_SUM;
        message.obj = String.valueOf(sum);
        return message;
    }

    private Message createMessage4Sum(int num, long sum) {
        Message message = mWorkerHandler.obtainMessage();
        message.what = KEY_SUM;
        message.arg1 = num;
        message.obj = String.valueOf(sum);
        return message;
    }

    private Message createMessage4Finish() {
        Message message = mWorkerHandler.obtainMessage();
        message.what = KEY_FINISH;
        return message;
    }

    public void setDownloadUrls(Integer... urls) {
        mNumList = Arrays.asList(urls);
    }

    public Handler getUIHandler() {
        return mUIHandler;
    }

    //注入主线程 Handler
    public SumThread setUIHandler(final Handler UIHandler) {
        mUIHandler = UIHandler;
        return this;
    }

    /**
     * 子线程中执行任务，完成后发送消息到主线程
     */
    @Override
    public boolean handleMessage(Message msg) {
        if (msg == null || msg.getData() == null || KEY_NUM != msg.what) {
            return false;
        }

        int num = msg.arg1;
        LogHelper.printThread(TAG, "ChildCallback,handleMessage()", "num=" + num);

        long sum = MockHeavyWork.sum(num);
        LogHelper.printThread(TAG, "ChildCallback,handleMessage() -> sendMessage", "num=" + num + ",sum=" + sum);

        /**
         * 子线程计算完成，通知主线程
         */
        mUIHandler.sendMessage(createMessage4Sum(num,sum));

        sumedNum++;

        /**
         * 子线程计算所有完成，通知主线程
         */
        if (sumedNum >= mNumList.size()) {
            mUIHandler.sendMessage(createMessage4Finish());
        }

        return false;
    }

    @Override
    public boolean quitSafely() {
        mUIHandler = null;
        return super.quitSafely();
    }
}