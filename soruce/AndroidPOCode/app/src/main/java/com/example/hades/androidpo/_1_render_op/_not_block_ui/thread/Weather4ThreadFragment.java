package com.example.hades.androidpo._1_render_op._not_block_ui.thread;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hades.androidpo.R;

public class Weather4ThreadFragment extends Fragment {
    private static final String TAG = Weather4ThreadFragment.class.getSimpleName();

    private View progressBar;
    private TextView result;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.weather_layout, container, false);

        progressBar = view.findViewById(R.id.progressBar);
        result = view.findViewById(R.id.result);

        view.findViewById(R.id.download).setOnClickListener(v -> requestWeather());
        return view;
    }

    private void requestWeather() {
        Log.d(TAG, "requestWeather: ");
        progressBar.setVisibility(View.VISIBLE);
        result.setText("");

        new Thread(new Runnable() {
            @Override
            public void run() {

                for (int i = 0; i < 5; i++) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                String weather = "Sunday" + System.currentTimeMillis();
                result.post(new Runnable() {
                    @Override
                    public void run() {
                        result.setText(weather);
                        progressBar.setVisibility(View.GONE);
                    }
                });
            }
        }).start();
    }
}
