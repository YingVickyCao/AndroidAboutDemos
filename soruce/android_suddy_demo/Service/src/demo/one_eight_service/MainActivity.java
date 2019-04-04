package demo.one_eight_service;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {
	private Button mStartServiceBtn;
	private Button mStopServiceBtn;

	private Button mStartService2Btn;
	private Button mStopService2Btn;

	private static String FILE_NAME;
	private static String TAG = "NLOG";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		FILE_NAME = this.getClass().getSimpleName() + "->";
		Log.i(TAG, FILE_NAME + "onCreate called");

		findViews();
		setValues();
		setListeners();
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

	private void findViews() {
		mStartServiceBtn = (Button) findViewById(R.id.btn_startService);
		mStopServiceBtn = (Button) findViewById(R.id.btn_stopService);
		mStartService2Btn = (Button) findViewById(R.id.btn_startService2);
		mStopService2Btn = (Button) findViewById(R.id.btn_stopService2);
	}

	private void setValues() {
	}

	private void setListeners() {
		mStartServiceBtn.setOnClickListener(startServiceLsn);
		mStopServiceBtn.setOnClickListener(stopServiceLsn);
	}

	private View.OnClickListener startServiceLsn = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			Log.i(TAG, FILE_NAME + "startServiceLsn called");
			Intent intent = new Intent(MainActivity.this, ServiceOne.class);
			startService(intent);
			Toast.makeText(MainActivity.this, "单击start按钮", Toast.LENGTH_SHORT).show();
		}
	};

	private View.OnClickListener stopServiceLsn = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			Log.i(TAG, FILE_NAME + "stopServiceLsn called");
			Intent intent = new Intent(MainActivity.this, ServiceOne.class);
			stopService(intent);
			Toast.makeText(MainActivity.this, "单击stop按钮", Toast.LENGTH_SHORT).show();
		}
	};

	private View.OnClickListener startService2Lsn = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			Log.i(TAG, FILE_NAME + "startServiceLsn called");
			Intent intent = new Intent(MainActivity.this, ServiceOne.class);
			startService(intent);
			Toast.makeText(MainActivity.this, "单击start按钮", Toast.LENGTH_SHORT).show();
		}
	};

	private View.OnClickListener stopService2Lsn = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			Log.i(TAG, FILE_NAME + "stopServiceLsn called");
			Intent intent = new Intent(MainActivity.this, ServiceOne.class);
			stopService(intent);
			Toast.makeText(MainActivity.this, "单击stop按钮", Toast.LENGTH_SHORT).show();
		}
	};
}
