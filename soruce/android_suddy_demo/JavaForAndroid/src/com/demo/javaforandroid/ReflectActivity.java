package com.demo.javaforandroid;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ReflectActivity extends ActionBarActivity {
	private Button mWlanBtn;
	private Button mMONETBtn;
	private static boolean mobet_status;

	/**
	 * <pre>
	 * The click event of Wlan setting button. 
	 * Created by Cao Ying on 2015/3/31.
	 * </pre>
	 */
	private View.OnClickListener wlanLsn = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			int a = 5;
			Intent intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
			a = 10;
			startActivity(intent);
		}
	};

	/**
	 * <pre>
	 * The click event of MONET setting button. 
	 * Created by Cao Ying on 2015/3/31.
	 * </pre>
	 */
	private View.OnClickListener monetLsn = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			if (NetworkSetting.isSIMNOTExist(ReflectActivity.this)) {
				Toast.makeText(ReflectActivity.this, getString(R.string.user_network_tip3), Toast.LENGTH_SHORT).show();
				return;
			}

			if (mobet_status) {
				NetworkSetting.setWlanEnable(ReflectActivity.this);
				NetworkSetting.setMONETDisable(ReflectActivity.this);
				Toast.makeText(ReflectActivity.this, getString(R.string.user_network_tip2), Toast.LENGTH_LONG).show();
			} else {
				NetworkSetting.setWlanDisable(ReflectActivity.this);
				NetworkSetting.setMONETEnable(ReflectActivity.this);
				Toast.makeText(ReflectActivity.this, getString(R.string.user_network_tip1), Toast.LENGTH_LONG).show();
			}
		}
	};

	/**
	 * Bound layout controls Created by Cao Ying on 2014/12/29
	 */
	private void findViews() {
		mWlanBtn = (Button) findViewById(R.id.btn_wlan);
		mMONETBtn = (Button) findViewById(R.id.btn_monet);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reflect);

		// Bound layout controls
		findViews();
		// Set contents of layout controls.
		setValues();
		// Binds a function to the click event of each matched element.
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

	/**
	 * Set events of layout controls Created by Cao Ying on 2014/12/29
	 */
	private void setListeners() {
		// The click event of wlan setting button.
		mWlanBtn.setOnClickListener(wlanLsn);
		// The click event of MONET setting button.
		mMONETBtn.setOnClickListener(monetLsn);

	}

	/**
	 * Set contents of layout controls Created by Cao Ying on 2014/12/29
	 */
	private void setValues() {
	}
}
