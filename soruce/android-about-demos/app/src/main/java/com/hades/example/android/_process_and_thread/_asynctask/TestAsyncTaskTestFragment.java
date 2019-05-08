package com.hades.example.android._process_and_thread._asynctask;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.hades.example.android.R;
import com.hades.example.android.lib.base.BaseFragment;

public class TestAsyncTaskTestFragment extends BaseFragment implements ISum {
    private static final String TAG = TestAsyncTaskTestFragment.class.getSimpleName();

    ProgressBar progressBar;
    public TextView mResult;
    private SumAsyncTask mSumAsyncTask;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bt_asynctask, container, false);
        progressBar = view.findViewById(R.id.progressbar);
        mResult = view.findViewById(R.id.result);

        view.findViewById(R.id.sum).setOnClickListener(v -> sum());
        view.findViewById(R.id.cancel).setOnClickListener(v -> cancel());
        return view;
    }

    private void sum() {
//        new SumAsyncTask().execute(5);
        /**
         * ERROR:
         * java.lang.IllegalStateException: Cannot execute task: the task has already been executed (a task can be executed only once)
         */
        mSumAsyncTask = new SumAsyncTask();
        mSumAsyncTask.setISum(this);
        // 串行
        mSumAsyncTask.execute(10);
        /*
            D/SumAsyncTask: doInBackground,SumAsyncTask@105483046,progress=10,thread id=3223,thread name=AsyncTask #1,result=0
            D/SumAsyncTask: doInBackground,SumAsyncTask@105483046,progress=20,thread id=3223,thread name=AsyncTask #1,result=1
            D/SumAsyncTask: doInBackground,SumAsyncTask@105483046,progress=30,thread id=3223,thread name=AsyncTask #1,result=3
            D/SumAsyncTask: doInBackground,SumAsyncTask@105483046,progress=40,thread id=3223,thread name=AsyncTask #1,result=6
            D/SumAsyncTask: doInBackground,SumAsyncTask@105483046,progress=50,thread id=3223,thread name=AsyncTask #1,result=10
            D/SumAsyncTask: doInBackground,SumAsyncTask@105483046,progress=60,thread id=3223,thread name=AsyncTask #1,result=15
            D/SumAsyncTask: doInBackground,SumAsyncTask@105483046,progress=70,thread id=3223,thread name=AsyncTask #1,result=21
         */


        // 并行
//        mSumAsyncTask.executeOnExecutor(Executors.newSingleThreadExecutor(), 10);
        /*
            D/SumAsyncTask: doInBackground,SumAsyncTask@105483046,progress=10,thread id=3203,thread name=pool-1-thread-1,result=0
            D/SumAsyncTask: doInBackground,SumAsyncTask@105483046,progress=20,thread id=3203,thread name=pool-1-thread-1,result=1
            D/SumAsyncTask: doInBackground,SumAsyncTask@110942462,progress=10,thread id=3204,thread name=pool-2-thread-1,result=0
            D/SumAsyncTask: doInBackground,SumAsyncTask@105483046,progress=30,thread id=3203,thread name=pool-1-thread-1,result=3
         */

        // 并行
        //无限制的Executor
//        mSumAsyncTask.executeOnExecutor(Executors.newCachedThreadPool(), 10);
        /*
            D/SumAsyncTask: doInBackground,SumAsyncTask@237993390,progress=10,thread id=2572,thread name=pool-4-thread-1,result=0
            D/SumAsyncTask: doInBackground,SumAsyncTask@85440335,progress=10,thread id=2573,thread name=pool-5-thread-1,result=0
            D/SumAsyncTask: doInBackground,SumAsyncTask@237993390,progress=20,thread id=2572,thread name=pool-4-thread-1,result=1
            D/SumAsyncTask: doInBackground,SumAsyncTask@85440335,progress=20,thread id=2573,thread name=pool-5-thread-1,result=1
         */


        // 并行
        //同时执行数目为10的Executor
//        mSumAsyncTask.executeOnExecutor(Executors.newFixedThreadPool(10), 10);
        /*
            D/SumAsyncTask: doInBackground,SumAsyncTask@226398877,progress=10,thread id=2983,thread name=pool-1-thread-1,result=0
            D/SumAsyncTask: doInBackground,SumAsyncTask@33452818,progress=10,thread id=2984,thread name=pool-2-thread-1,result=0
            D/SumAsyncTask: doInBackground,SumAsyncTask@38972131,progress=10,thread id=2985,thread name=pool-3-thread-1,result=0
            D/SumAsyncTask: doInBackground,SumAsyncTask@198581472,progress=10,thread id=2986,thread name=pool-4-thread-1,result=0
            D/SumAsyncTask: doInBackground,SumAsyncTask@204913561,progress=10,thread id=2987,thread name=pool-5-thread-1,result=0
         */
    }

    private void cancel() {
        if (mSumAsyncTask != null) {
            mSumAsyncTask.cancel(true);
        }
    }

    /**
     * PO:
     * This AsyncTask class should be static or leaks might occur (TestAsyncTaskTestFragment.SumAsyncTask) less... (⌘F1)
     * Inspection info:A static field will leak contexts.  Non-static inner classes have an implicit reference to their outer class. If that outer class is for example a Fragment or Activity,
     * then this reference means that the long-running handler/loader/task will hold a reference to the activity which prevents it from getting garbage collected.
     * Similarly, direct field references to activities and fragments from these longer running instances can cause leaks.  ViewModel classes should never point to Views or non-application Contexts.  Issue id: StaticFieldLeak
     */
//    class SumAsyncTask extends AsyncTask<Integer, Integer, Long> {
//
//        @Override
//        protected void onProgressUpdate(Integer... values) {// UI Thread
//            // TODO Auto-generated method stub
//            //super.onProgressUpdate(values);
//            progressBar.setProgress(values[0]);
//            Log.d(TAG, "onProgressUpdate: progress=" + values[0] + ",thread id=" + Thread.currentThread().getId() + ",thread name=" + Thread.currentThread().getName());
//        }
//
//        @Override
//        protected Long doInBackground(Integer... params) {//work thread
//            int max = params[0];
//            long result = 0;
//            for (int i = 1; i <= max; i++) {
//                try {
//                    Thread.currentThread().sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                int progress = (int) ((i / (float) max) * 100);
//                Log.d(TAG, "doInBackground: progress=" + progress + ",thread id=" + Thread.currentThread().getId() + ",thread name=" + Thread.currentThread().getName());
//                publishProgress(progress);
//                result += i;
//            }
//            return result;
//        }
//
//        @Override
//        protected void onPostExecute(Long result) {// UI Thread
//            // TODO Auto-generated method stub
//            super.onPostExecute(result);
//            mResult.setText(String.valueOf(result));
//            Log.d(TAG, "onPostExecute: result=" + result + ",thread id=" + Thread.currentThread().getId() + ",thread name=" + Thread.currentThread().getName());
//        }
//    }
    @Override
    public void onPause() {
        super.onPause();
        if (null != mSumAsyncTask) {
            mSumAsyncTask.setISum(null);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (null != mSumAsyncTask) {
            mSumAsyncTask.setISum(this);
        }
    }

    @Override
    public void onPreExecute(String msg) {
        showToast(msg);
        mResult.setText("");
        progressBar.setVisibility(View.VISIBLE);
        progressBar.setProgress(0);
    }

    @Override
    public void setProgress(int progress) {
        progressBar.setProgress(progress);
    }

    @Override
    public void setResult(long result) {
        mResult.setText(String.valueOf(result));
        progressBar.setVisibility(View.GONE);
    }
}