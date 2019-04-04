package com.demo.SunnyDemo.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.demo.SunnyDemo.R;
import com.demo.SunnyDemo.service.BoundedService.MyBinder;

public class ServiceMainActivity extends ActionBarActivity {
	private static String TAG = "ServiceMainActivity";

	// BoundedService：
	private boolean mBounded = false;
	private BoundedService myBoundedService;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.service_main);
		Log.i(TAG, "onCreate");
	}

	// BoundedService：Bind to LocalService
	// BoundedService：建立连接
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();

		Intent intent = getIntent();
		intent.setClass(ServiceMainActivity.this, BoundedService.class);
		bindService(intent, connection, Context.BIND_AUTO_CREATE);
	}

	// BoundedService：Unbind from the service
	// BoundedService：解除连接
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		if (mBounded) {
			unbindService(connection);
		}
	}

	// BoundedService：建立与Service的连接
	private ServiceConnection connection = new ServiceConnection() {

		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub
			mBounded = false;
		}

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			// TODO Auto-generated method stub
			mBounded = true;
			// 获取Bounded Service实例
			myBoundedService = ((MyBinder) service).getService();
		}
	};

	public void doClick(View view) {
		Log.i(TAG, "doClick called. ppid =" + Thread.currentThread().getName());
		switch (view.getId()) {
		// 启动StartedService
		case R.id.btn1:
			Intent intent = getIntent();
			intent.setClass(ServiceMainActivity.this, StartedService.class);
			// 通过startService启动StartedService
			startService(intent);
			break;

		// 停止StartedService
		case R.id.btn2:
			break;

		// 启动IntentService
		case R.id.btn3:
			Intent intent2 = getIntent();
			intent2.setClass(ServiceMainActivity.this, StartedService_IntentService.class);
			startService(intent2);
			break;

		// Bounded Service
		case R.id.btn4:
			// 如果处理连接状态，就获取消息
			if (mBounded) {
				Toast.makeText(ServiceMainActivity.this, myBoundedService.getSpeakToHello(), Toast.LENGTH_LONG).show();
			}
			break;
		}
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.i(TAG, "onDestroy");
	}
}
