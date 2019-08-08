package com.hades.example.android.network.url.http_url_connection;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hades.example.android.Constant;
import com.hades.example.android.R;

import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import static android.content.Context.MODE_PRIVATE;
import static com.hades.example.android.Constant.SHARED_PREFERENCES_FILE_NAME;
import static com.hades.example.android.Constant.SHARED_PREFERENCES_KEY_DOWNLOAD;

public class MultiThreadDownFragment extends Fragment {
    private static final String TAG = MultiThreadDownFragment.class.getSimpleName();

    ProgressBar bar;
    ImageView img;
    TextView percent;

    private int mDownStatus;

    DownUtil downUtil;
    Handler handler;
    private Map<Integer, Integer> map;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.network_multi_thread_down_layut, container, false);

        bar = view.findViewById(R.id.bar);
        img = view.findViewById(R.id.img);
        percent = view.findViewById(R.id.percent);
        view.findViewById(R.id.down).setOnClickListener(v -> downBn());

        map = getCacheProgressMap();

        downUtil = new DownUtil(Constant.IMAGE_URL, Constant.IMAGE_PATH, 3, map);
        intHandler();

        loadImgFromLocalCache();

        view.setOnKeyListener((v, keyCode, event) -> {
            if (keyCode == KeyEvent.KEYCODE_BACK && KeyEvent.ACTION_DOWN == event.getAction()) {
                Log.d(TAG, "onKey: Back");
                cache();
                return true;
            } else {
                Log.d(TAG, "onKey: Others");
                return false;
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        map = getCacheProgressMap();
    }

    private void cache() {
        Map<Integer, Integer> map = downUtil.getPartDownloadProgress();
        if (null == map) {
            return;
        }
        Log.d(TAG, "cache: map=" + map.toString());
        Gson gson = new Gson();
        String json = gson.toJson(map);
        cache(json);
    }

    private Map<Integer, Integer> getCacheProgressMap() {
        String jsonArray = getCache();
        if (null == jsonArray) {
            return null;
        }
        Gson gson = new Gson();
        Map<Integer, Integer> map = gson.fromJson(jsonArray, new TypeToken<Map<Integer, Integer>>() {
        }.getType());
        return map;
    }

    private void cache(String json) {
        SharedPreferences preferences = getActivity().getSharedPreferences(SHARED_PREFERENCES_FILE_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(SHARED_PREFERENCES_KEY_DOWNLOAD, json);
        editor.commit();
    }

    private String getCache() {
        SharedPreferences preferences = getActivity().getSharedPreferences(SHARED_PREFERENCES_FILE_NAME, MODE_PRIVATE);
        return preferences.getString(SHARED_PREFERENCES_KEY_DOWNLOAD, null);
    }

    @SuppressLint("HandlerLeak")
    private void intHandler() {
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == Constant.KEY_UPDATE_UI) {
                    bar.setProgress(mDownStatus);
                    percent.setText(mDownStatus + "%");
                }
            }
        };
    }

    private void loadImgFromLocalCache() {
        new Thread(() -> {
            try {
                Bitmap bitmap = BitmapFactory.decodeFile(Constant.IMAGE_PATH);
                if (null != bitmap) {
                    if (null != getActivity()) {
                        getActivity().runOnUiThread(() -> img.setImageBitmap(bitmap));
                    }
                }
            } catch (Exception ex) {
                Log.d(TAG, "loadImgFromLocalCache: " + ex);
            }
        }).start();
    }

    private void downBn() {
        new Thread() {
            @Override
            public void run() {
                try {
                    downUtil.download();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                // 定义每秒调度获取一次完成进度
                final Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        mDownStatus = downUtil.getDownloadProgress();
                        handler.sendEmptyMessage(Constant.KEY_UPDATE_UI);
                        if (mDownStatus >= 100) {
                            timer.cancel();
                        }
                    }
                }, 0, 100);
            }
        }.start();
    }
}