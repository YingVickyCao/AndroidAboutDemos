package demo.service_aidl.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import demo.service_aidl.DataService;

public class ServerService extends Service {

	Binder binder = new DataService.Stub() {
		// ��д�ķ����ᱻclient���á�
		@Override
		public String getData(String name) throws RemoteException {
			System.out.println("service -> name=" + name);
			return name.equals("123456") ? "Hello,Service -> Client" : "";
		}
	};

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return binder;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		// ��ʼ��Service
		super.onCreate();
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		// ����Service
		super.onDestroy();
	}

}
