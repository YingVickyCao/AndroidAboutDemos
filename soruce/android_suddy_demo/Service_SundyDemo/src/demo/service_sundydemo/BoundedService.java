package demo.service_sundydemo;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;

public class BoundedService extends Service {
	private static String FILE_NAME;
	private static String TAG = "SunnyDemo";
	long result = 0;

	// ʵ���������ṩһ��IBinder�ӿڡ�
	private final IBinder mBinder = new MyBinder();

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();

		FILE_NAME = getClass().getSimpleName() + " -> ";
		Log.i(TAG, FILE_NAME + "onCreate,ppid=" + Thread.currentThread().getName());
	}

	// ����̳�Binder�����࣬����ͷ���IBinder�ӿڡ�
	public class MyBinder extends Binder {
		// ����IBinder�ӿڣ����Ƕ����ṩ�Ĺ���(�Ƽ�)
		public BoundedService getService() {
			Log.i(TAG, FILE_NAME + "getService,ppid=" + Thread.currentThread().getName());
			return BoundedService.this;
		}

		/**
		 * <pre>
		 * // ���Ƕ����ṩ�Ĺ���(���Ƽ�)
		 * public String getSpeakToHello2() {
		 * 	return &quot;Hello,�����һ��BoundService�����ӡ�����������&quot;;
		 * }
		 * </pre>
		 **/

	}

	// ���Ƕ����ṩ�Ĺ���
	public String getSpeakToHello() {

		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				result = doCaculate();
			}
		});
		Log.i(TAG, FILE_NAME + "getSpeakToHello,ppid=" + Thread.currentThread().getName());

		// TODO:���߳�ʧ�ܣ����Ϊ0
		return "Hello,�����һ��BoundService�����ӡ�����������result=" + result;
	}

	/*
	 * <pre> Caculate the process . ִ�м��㡣 ����һ����ʱ����
	 * 
	 * @return </pre>
	 */
	private long doCaculate() {
		long returnResult = 0;

		long j = 98, i = 99, k = 100;

		for (int m = 0; m < 10000; m++) {
			returnResult = j * i * k * m;
		}
		return returnResult;
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
