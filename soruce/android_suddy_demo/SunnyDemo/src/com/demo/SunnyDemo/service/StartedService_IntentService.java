package com.demo.SunnyDemo.service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class StartedService_IntentService extends IntentService {
	private static String TAG = "StartedService_IntentService";

	public StartedService_IntentService() {
		super("StartedService_IntentService");
		// TODO Auto-generated constructor stub
	}

	// onHandleIntent是必须要覆盖的函数
	@Override
	protected void onHandleIntent(Intent intent) {
		// TODO Auto-generated method stub
		// 执行耗时任务
		printLog();
	}

	// 这个Service的任务就是打印log
	public void printLog() {
		Log.i(TAG, "printLog called. begin, ppid =" + Thread.currentThread().getName());

		for (int i = 0; i < 5; i++) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Log.i(TAG, "printLog called. i =" + i);
		}
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		Log.d(TAG, "onCreate");
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		Log.d(TAG, "onStartCommand");
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.d(TAG, "onDestroy");
	}

}
