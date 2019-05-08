package com.hades.example.android._process_and_thread._asynctask;

import android.os.AsyncTask;
import android.util.Log;

public class SumAsyncTask extends AsyncTask<Integer, Integer, Long> {
    private static final String TAG = SumAsyncTask.class.getSimpleName();

    private ISum mISum;

    SumAsyncTask() {
    }

    void setISum(ISum sum) {
        mISum = sum;
    }

    @Override
    protected void onPreExecute() {// UI Thread
        if (null != mISum) {
            mISum.onPreExecute("Start work");
        }
        Log.d(TAG, "onPreExecute: thread id=" + Thread.currentThread().getId() + ",thread name=" + Thread.currentThread().getName());
    }

    @Override
    protected Long doInBackground(Integer... params) {//work thread
        int max = params[0];
        long result = 0;
        for (int i = 1; i <= max; i++) {
            if (isCancelled()) {
                Log.d(TAG, "doInBackground: isCancelled");
                return result;
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int progress = (int) ((i / (float) max) * 100);
            Log.d(TAG, "doInBackground,SumAsyncTask@" + hashCode() + ",progress=" + progress + ",thread id=" + Thread.currentThread().getId() + ",thread name=" + Thread.currentThread().getName() + ",result=" + result);
            publishProgress(progress);
            result += i;
        }
        return result;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {// UI Thread
        if (null != mISum) {
            mISum.setProgress(values[0]);
        }
        Log.d(TAG, "onProgressUpdate: progress=" + values[0] + ",thread id=" + Thread.currentThread().getId() + ",thread name=" + Thread.currentThread().getName());
    }

    @Override
    protected void onPostExecute(Long result) {// UI Thread
        if (null != mISum) {
            mISum.setResult(result);
        }
        Log.d(TAG, "onPostExecute: result=" + result + ",thread id=" + Thread.currentThread().getId() + ",thread name=" + Thread.currentThread().getName());
    }

    @Override
    protected void onCancelled() {// UI Thread
        super.onCancelled();
        Log.d(TAG, "onCancelled: thread id=" + Thread.currentThread().getId() + ",thread name=" + Thread.currentThread().getName());
    }

    @Override
    protected void onCancelled(Long aLong) {// UI Thread
        super.onCancelled(aLong);
        Log.d(TAG, "onCancelled: aLong=" + aLong + ",thread id=" + Thread.currentThread().getId() + ",thread name=" + Thread.currentThread().getName());
    }
}
