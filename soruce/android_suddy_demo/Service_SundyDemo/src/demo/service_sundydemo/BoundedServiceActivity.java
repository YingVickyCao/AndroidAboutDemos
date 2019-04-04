package demo.service_sundydemo;

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
import demo.service_sundydemo.BoundedService.MyBinder;

public class BoundedServiceActivity extends ActionBarActivity {
	private static String FILE_NAME;
	private static String TAG = "SunnyDemo";

	// BoundedService：
	private boolean mBounded = false;
	private BoundedService myBoundedService;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.service_boundservice);
		FILE_NAME = getClass().getSimpleName() + " -> ";
	}

	// BoundedService：Bind to LocalService
	// BoundedService：建立连接
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		Log.i(TAG, FILE_NAME + "onStart,pid=" + Thread.currentThread().getName());
		Intent intent = getIntent();
		intent.setClass(BoundedServiceActivity.this, BoundedService.class);
		bindService(intent, connection, Context.BIND_AUTO_CREATE);
	}

	// BoundedService：Unbind from the service
	// BoundedService：解除连接
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();

		Log.i(TAG, FILE_NAME + "onStop,pid=" + Thread.currentThread().getName());
		if (mBounded) {
			unbindService(connection);
		}
	}

	// BoundedService：建立与Service的连接
	private ServiceConnection connection = new ServiceConnection() {

		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub

			Log.i(TAG, FILE_NAME + "ServiceConnection->onServiceDisconnected,pid=" + Thread.currentThread().getName());
			mBounded = false;
		}

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			// TODO Auto-generated method stub

			Log.i(TAG, FILE_NAME + "ServiceConnection->onServiceConnected,pid=" + Thread.currentThread().getName());
			mBounded = true;
			// 获取Bounded Service实例
			myBoundedService = ((MyBinder) service).getService();
		}
	};

	public void doClick(View view) {
		Log.i(TAG, FILE_NAME + "doClick called. ppid =" + Thread.currentThread().getName());
		switch (view.getId()) {

		// Bounded Service
		case R.id.btn1:
			// 如果处理连接状态，就获取消息
			if (mBounded) {
				Toast.makeText(BoundedServiceActivity.this, myBoundedService.getSpeakToHello(), Toast.LENGTH_LONG).show();
			}
			break;

		// Unbind Service
		case R.id.btn2:
			// 如果处理连接状态，就解除绑定
			if (mBounded) {
				unbindService(connection);
			}
			break;
		}
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.i(TAG, FILE_NAME + "onDestroy,pid=" + Thread.currentThread().getName());
	}
}
