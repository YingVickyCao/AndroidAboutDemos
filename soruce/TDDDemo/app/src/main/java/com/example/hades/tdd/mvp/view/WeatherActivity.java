/*******************************************************************************
 * Copyright (c) 2012 Manning
 * See the file license.txt for copying permission.
 ******************************************************************************/
package com.example.hades.tdd.mvp.view;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.hades.tdd.R;
import com.example.hades.tdd.mvp.presenter.IWeatherPresenter;
import com.example.hades.tdd.mvp.presenter.WeatherPresenter;

/**
 * MVP-V,View（视图）
 */
public class WeatherActivity extends Activity implements IWeatherView, View.OnClickListener {
    private static final String TAG = WeatherActivity.class.getSimpleName();
    private ProgressDialog mDialog;
    private TextView mWeatherInfo;

    /**
     * View—–>Presenter
     * 从视图界面出发，用户要请求数据，而Presenter是具体实现者，所以Presenter要提供方法代View的实现者调用，并且View的实现中必须要有Presenter的引用。
     */
    private IWeatherPresenter mPresenter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hack_20_weather);

        mWeatherInfo = findViewById(R.id.weatherInfo);
        findViewById(R.id.clear).setOnClickListener(this);
        findViewById(R.id.getWeatherInfo).setOnClickListener(this);

        mPresenter = new WeatherPresenter();
        /**
         * 为当前 Activity 设置主导器
         */
        mPresenter.setView(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.clear:
                clear();
                break;

            case R.id.getWeatherInfo:
                requestWeatherInfo(v);
                break;
        }
    }

    public void clear() {
        mPresenter.resetWeatherInfo();
    }

    /**
     * View.interface回调方法被触发时，进行相应的视图更新。
     * 这里主要的视图有:
     * 1. 显示对话框
     * 2. 取消对话框
     * 3. 显示天气信息。
     */
    private void requestWeatherInfo(View view) {
        mPresenter.requestWeatherInfo();
    }

    @Override
    public void removeWeatherInfo() {
        Log.d(TAG, "removeWeatherInfo: ");
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mWeatherInfo.setText(null);
            }
        });
    }

    @Override
    public void showWeatherInfo(final String info) {
        Log.d(TAG, "showWeatherInfo: " + info);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mWeatherInfo.setText(info);
            }
        });
    }

    @Override
    public void showWaitingDialog() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mDialog != null && mDialog.isShowing()) {
                    mDialog.dismiss();
                }

                mDialog = ProgressDialog.show(getActivity(), "", "正在获取中...");
            }
        });
    }

    private Activity getActivity() {
        return WeatherActivity.this;
    }

    @Override
    public void dismissWaitingDialog() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mDialog != null && mDialog.isShowing()) {
                    mDialog.dismiss();
                }
            }
        });

    }
}
