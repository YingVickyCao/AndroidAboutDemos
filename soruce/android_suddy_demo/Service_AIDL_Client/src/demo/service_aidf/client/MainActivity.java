package demo.service_aidf.client;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import demo.service_aidf.DataService;
import demo.service_aidf.R;

public class MainActivity extends Activity {
	DataService dataService;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	/**
	 * MainActivity×÷Îªclient£¬·ÃÎÊService¡£
	 * 
	 * @param view
	 */
	public void boundServiceLsn(View view) {
		Intent intent = new Intent("demo.service_aidl.service.ServerService");
		bindService(intent, serverConnection, Context.BIND_AUTO_CREATE);
	}

	ServiceConnection serverConnection = new ServiceConnection() {

		@Override
		public void onServiceDisconnected(ComponentName name) {
		}

		@Override
		public void onServiceConnected(ComponentName name, IBinder iBinder) {
			dataService = DataService.Stub.asInterface(iBinder);
		}
	};

	public void callServiceLsn(View view) {
		try {
			String result = dataService.getData("123456");
			System.out.println("client -> result=" + result);
			// Toast.makeText(MainActivity.this, dataService.getData("123456"),
			// Toast.LENGTH_SHORT).show();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
