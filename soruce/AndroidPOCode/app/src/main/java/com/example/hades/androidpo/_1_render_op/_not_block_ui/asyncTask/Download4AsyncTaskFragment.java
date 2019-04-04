package com.example.hades.androidpo._1_render_op._not_block_ui.asyncTask;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.hades.androidpo.R;

public class Download4AsyncTaskFragment extends Fragment {
    private static final String TAG = Download4AsyncTaskFragment.class.getSimpleName();

    private View progressBar;
    private ProgressBar progressBarValue;
    private TextView resultView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.download_files_layout, container, false);

        progressBar = view.findViewById(R.id.progressBar);
        progressBarValue = view.findViewById(R.id.progressBarValue);
        resultView = view.findViewById(R.id.result);

        view.findViewById(R.id.download).setOnClickListener(v -> requestWeather());
        return view;
    }

    private void requestWeather() {
        new DownloadFilesTask().execute(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    }

    /**
     * Why don't use AsyncTask?
     * 1) Does not support Unit Test smartly.
     * 2) Static or leak.
     */
    private class DownloadFilesTask extends AsyncTask<Integer, Integer, Long> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
            resultView.setText("");
        }

        protected Long doInBackground(Integer... urls) {
            int count = urls.length;
            long totalSize = 0;
            for (int i = 0; i < count; i++) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                totalSize += urls[i];
                publishProgress((int) ((i / (float) count) * 100));
                if (isCancelled()) break;
            }
            return totalSize;
        }

        protected void onProgressUpdate(Integer... progress) {
            progressBarValue.setProgress(progress[0]);
        }

        protected void onPostExecute(Long result) {
            resultView.setText("Downloaded " + result + " bytes");
            progressBar.setVisibility(View.GONE);
            progressBarValue.setProgress(0);
        }
    }
}
