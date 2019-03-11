package com.hades.android.example.android_about_demos._process_and_thread._asynctask;

import android.os.AsyncTask;
import android.util.Log;

class SumAsyncTask extends AsyncTask<Integer, Integer, Long> {
    private static final String TAG = SumAsyncTask.class.getSimpleName();

    private TestAsyncTaskTestFragment mTestAsyncTaskTestFragment;

    public SumAsyncTask(TestAsyncTaskTestFragment testAsyncTaskTestFragment) {
        mTestAsyncTaskTestFragment = testAsyncTaskTestFragment;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {// UI Thread
        // TODO Auto-generated method stub
        //super.onProgressUpdate(values);
        mTestAsyncTaskTestFragment.progressBar.setProgress(values[0]);
        Log.d(TAG, "onProgressUpdate: progress=" + values[0] + ",thread id=" + Thread.currentThread().getId() + ",thread name=" + Thread.currentThread().getName());
    }


    @Override
    protected Long doInBackground(Integer... params) {//work thread
        int max = params[0];
        long result = 0;
        for (int i = 1; i <= max; i++) {
            try {
                Thread.currentThread().sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int progress = (int) ((i / (float) max) * 100);
            Log.d(TAG, "doInBackground: progress=" + progress + ",thread id=" + Thread.currentThread().getId() + ",thread name=" + Thread.currentThread().getName());
            publishProgress(progress);
            result += i;
        }
        return result;
    }

    @Override
    protected void onPostExecute(Long result) {// UI Thread
        // TODO Auto-generated method stub
        super.onPostExecute(result);
//        mTestAsyncTaskTestFragment.mResult.setText(String.valueOf(result));
        Log.d(TAG, "onPostExecute: result=" + result + ",thread id=" + Thread.currentThread().getId() + ",thread name=" + Thread.currentThread().getName());
    }
}
