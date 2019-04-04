package demo.service_aidl.service;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import demo.service_aidl.R;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	private ServiceConnection serviceConnection = new ServiceConnection() {

		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			// TODO Auto-generated method stub

		}
	};

	/**
	 * 绑定后Service的服务将会开启，client就可以访问。
	 * 
	 * @param view
	 */
	public void startServiceLsn(View view) {
		Intent intent = new Intent("demo.service_aidl.service.ServerService");
		startService(intent);
	}
}
