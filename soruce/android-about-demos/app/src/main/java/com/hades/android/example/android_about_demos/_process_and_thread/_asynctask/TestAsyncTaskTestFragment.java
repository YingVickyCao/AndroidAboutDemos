package com.hades.android.example.android_about_demos._process_and_thread._asynctask;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.hades.android.example.android_about_demos.R;
import com.hades.android.example.android_about_demos.base.BaseFragment;

public class TestAsyncTaskTestFragment extends BaseFragment {
    private static final String TAG = TestAsyncTaskTestFragment.class.getSimpleName();

    ProgressBar progressBar = null;
    private TextView mResult;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pt_asynctask, container, false);
        progressBar = view.findViewById(R.id.progressbar);
        mResult = view.findViewById(R.id.result);

        view.findViewById(R.id.sum).setOnClickListener(v -> sum());
        return view;
    }

    private void sum() {
        mResult.setText("");
        progressBar.setProgress(0);
        new SumAsyncTask().execute(5);
    }

    class SumAsyncTask extends AsyncTask<Integer, Integer, Long> {

        @Override
        protected void onProgressUpdate(Integer... values) {// UI Thread
            // TODO Auto-generated method stub
            //super.onProgressUpdate(values);
            progressBar.setProgress(values[0]);
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
            mResult.setText(String.valueOf(result));
            Log.d(TAG, "onPostExecute: result=" + result + ",thread id=" + Thread.currentThread().getId() + ",thread name=" + Thread.currentThread().getName());
        }
    }
}
