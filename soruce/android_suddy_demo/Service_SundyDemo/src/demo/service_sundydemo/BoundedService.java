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

	// 实例化对外提供一个IBinder接口。
	private final IBinder mBinder = new MyBinder();

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();

		FILE_NAME = getClass().getSimpleName() + " -> ";
		Log.i(TAG, FILE_NAME + "onCreate,ppid=" + Thread.currentThread().getName());
	}

	// 定义继承Binder的子类，定义和发布IBinder接口。
	public class MyBinder extends Binder {
		// 定义IBinder接口：这是对外提供的功能(推荐)
		public BoundedService getService() {
			Log.i(TAG, FILE_NAME + "getService,ppid=" + Thread.currentThread().getName());
			return BoundedService.this;
		}

		/**
		 * <pre>
		 * // 这是对外提供的功能(不推荐)
		 * public String getSpeakToHello2() {
		 * 	return &quot;Hello,这就是一个BoundService的例子。你明白了吗？&quot;;
		 * }
		 * </pre>
		 **/

	}

	// 这是对外提供的功能
	public String getSpeakToHello() {

		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				result = doCaculate();
			}
		});
		Log.i(TAG, FILE_NAME + "getSpeakToHello,ppid=" + Thread.currentThread().getName());

		// TODO:多线程失败，结果为0
		return "Hello,这就是一个BoundService的例子。你明白了吗？result=" + result;
	}

	/*
	 * <pre> Caculate the process . 执行计算。 这是一个耗时操作
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
