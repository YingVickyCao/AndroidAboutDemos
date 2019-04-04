package demo.one_eight_service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class ServiceSecond extends Service {

	private static String FILE_NAME;
	private static String TAG = "NLOG";

	@Override
	public IBinder onBind(Intent intent) {
		Log.i(TAG, FILE_NAME + "onBind called");
		return null;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		FILE_NAME = this.getClass().getSimpleName() + "->";
		Log.i(TAG, FILE_NAME + "onCreate called");
	}

	@Override
	@Deprecated
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		super.onStart(intent, startId);
		Log.i(TAG, FILE_NAME + "onStart called");
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		Log.i(TAG, FILE_NAME + "onStartCommand called");
		stopSelf();
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public boolean onUnbind(Intent intent) {
		// TODO Auto-generated method stub
		Log.i(TAG, FILE_NAME + "onUnbind called");
		return super.onUnbind(intent);
	}

	@Override
	public void onRebind(Intent intent) {
		// TODO Auto-generated method stub
		Log.i(TAG, FILE_NAME + "onRebind called");
		super.onRebind(intent);
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.i(TAG, FILE_NAME + "onDestroy called");
	}
}
