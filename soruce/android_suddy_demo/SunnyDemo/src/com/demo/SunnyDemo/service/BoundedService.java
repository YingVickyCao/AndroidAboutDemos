package com.demo.SunnyDemo.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class BoundedService extends Service {
	private static String FILE_NAME;
	private static String TAG = "SunnyDemo";

	// ʵ���������ṩһ��IBinder�ӿڡ�
	private final IBinder mBinder = new MyBinder();

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();

		FILE_NAME = getClass().getSimpleName() + " -> ";
		Log.i(TAG, FILE_NAME + "onCreate,ppid=" + Thread.currentThread().getName());
	}

	// ͨ���̳�Binder�����࣬�����ʵ��IBinder�ӿڡ�
	public class MyBinder extends Binder {
		// ���Ƕ����ṩ�Ĺ���(�Ƽ�)
		public BoundedService getService() {
			Log.i(TAG, FILE_NAME + "getService,ppid=" + Thread.currentThread().getName());
			return BoundedService.this;
		}

		/*
		 * // ���Ƕ����ṩ�Ĺ���(���Ƽ�) public String getSpeakToHello2() { return
		 * "Hello,�����һ��BoundService�����ӡ�����������"; }
		 */
	}

	// ���Ƕ����ṩ�Ĺ���
	public String getSpeakToHello() {
		Log.i(TAG, FILE_NAME + "getSpeakToHello,ppid=" + Thread.currentThread().getName());
		return "Hello,�����һ��BoundService�����ӡ�����������";
	}

	// ͨ��onBind������IBinder�ӿڡ�
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
