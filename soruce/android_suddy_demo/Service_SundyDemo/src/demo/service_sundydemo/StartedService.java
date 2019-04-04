package demo.service_sundydemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class StartedService extends Service {
	private static String TAG = "StartedService";
	long result = 0;

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		Log.d(TAG, "onCreate");
	}

	// ��onStartCommand��ִ������
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		Log.d(TAG, "onStartCommand");
		
		//stopSelf(startId);
		stopService(intent);
		
		// �����߳���ִ������
		printLog();
		
	/*	// �����߳���ִ������
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				printLog();
			}
		}).start();
*/
		return super.onStartCommand(intent, flags, startId);
	}

	// ��ʹִ������printLog��onDestroyҲ�������
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.d(TAG, "onDestroy");
	}

	// ���Service��������Ǵ�ӡlog
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
			
			result = i;
			 
		}
	}

	// onBind����StartedService���������ں���
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
}
