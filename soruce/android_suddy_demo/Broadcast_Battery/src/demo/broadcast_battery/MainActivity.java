package demo.broadcast_battery;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

/**
 * <pre>
 * ʹ��registerBroadcaseע��㲥�����������仯��
 * </pre>
 * 
 * @author Administrator
 * 
 */
public class MainActivity extends Activity {
	private TextView mContentTv;
	private BatteryBroadcastReceiver receiver;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mContentTv = (TextView) findViewById(R.id.tv_content);

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		/**
		 * <pre>
		 * ACTION_BATTERY_CHANGED
		 * ACTION_BATTERY_LOW
		 * ACTION_BATTERY_OKAY
		 * </pre>
		 */
		// ʹ��registerReceiverע��㲥������
		receiver = new BatteryBroadcastReceiver(MainActivity.this);
		// �����ͼ����
		IntentFilter filter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
		registerReceiver(receiver, filter);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		// ʹ��unregisterReceiverע���㲥������
		unregisterReceiver(receiver);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
