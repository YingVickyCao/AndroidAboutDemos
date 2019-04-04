package com.demo.SunnyDemo.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class BoundedService extends Service {
	private static String FILE_NAME;
	private static String TAG = "SunnyDemo";

	// 实例化对外提供一个IBinder接口。
	private final IBinder mBinder = new MyBinder();

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();

		FILE_NAME = getClass().getSimpleName() + " -> ";
		Log.i(TAG, FILE_NAME + "onCreate,ppid=" + Thread.currentThread().getName());
	}

	// 通过继承Binder的子类，定义和实现IBinder接口。
	public class MyBinder extends Binder {
		// 这是对外提供的功能(推荐)
		public BoundedService getService() {
			Log.i(TAG, FILE_NAME + "getService,ppid=" + Thread.currentThread().getName());
			return BoundedService.this;
		}

		/*
		 * // 这是对外提供的功能(不推荐) public String getSpeakToHello2() { return
		 * "Hello,这就是一个BoundService的例子。你明白了吗？"; }
		 */
	}

	// 这是对外提供的功能
	public String getSpeakToHello() {
		Log.i(TAG, FILE_NAME + "getSpeakToHello,ppid=" + Thread.currentThread().getName());
		return "Hello,这就是一个BoundService的例子。你明白了吗？";
	}

	// 通过onBind，发布IBinder接口。
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		Log.i(TAG, FILE_NAME + "onBind,ppid=" + Thread.currentThread().getName());
		return mBinder;
	}

	@Override
	public boolean onUnbind(Intent intent) {
		// TODO Auto-generated method stub
		Log.i(TAG, FILE_NAME + "onUnbind,ppid=" + Thread.currentThread().getName());
		return super.onUnbind(intent);

	}

	@Override
	public void onRebind(Intent intent) {
		// TODO Auto-generated method stub
		super.onRebind(intent);
		Log.i(TAG, FILE_NAME + "onRebind,ppid=" + Thread.currentThread().getName());
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.i(TAG, FILE_NAME + "onDestroy,ppid=" + Thread.currentThread().getName());
	}
}
