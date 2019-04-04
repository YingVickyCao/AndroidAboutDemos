package com.example.one_seven;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * <pre>
 * Activity的生命周期函数一共有7个。
 */
public class MainActivity extends ActionBarActivity {
	private static String TAG = "NLOG";
	private static String FILE_NAME;
	private Button mDiagBtn;
	private Button mJumpBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		FILE_NAME = this.getClass().getSimpleName() + "->";
		Log.i(TAG, FILE_NAME + "onCreate func called!");
		findViews();
		setVlaues();
		setListeners();
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		Log.i(TAG, FILE_NAME + "onRestart func called!");
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		Log.i(TAG, FILE_NAME + "onStart func called!");
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.i(TAG, FILE_NAME + "onResume func called!");
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		Log.i(TAG, FILE_NAME + "onPause func called!");
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		Log.i(TAG, FILE_NAME + "onStop func called!");
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.i(TAG, FILE_NAME + "onDestroy func called!");
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
		mDiagBtn = (Button) findViewById(R.id.btn_diag);
		mJumpBtn = (Button) findViewById(R.id.btn_jump);
	}

	private void setVlaues() {

	}

	private void setListeners() {
		mDiagBtn.setOnClickListener(diagLsn);
		mJumpBtn.setOnClickListener(nextLsn);
	}

	private View.OnClickListener diagLsn = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			Log.i(TAG, FILE_NAME + "diagLsn click performed!");
			new AlertDialog.Builder(MainActivity.this).setMessage("弹出这个对话框，此后会执行哪个生命周期函数？").setPositiveButton("Ok", null).show();
			Toast.makeText(getApplication(), "hello", Toast.LENGTH_LONG).show();
		}
	};

	private View.OnClickListener nextLsn = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			Log.i(TAG, FILE_NAME + "nextLsn click performed!");
			Intent intent = new Intent(MainActivity.this, NextActivity.class);
			startActivity(intent);
		}
	};

	@Override
	public void finish() {
		super.finish();
		Log.i(TAG, FILE_NAME + "finish func called!");
	}

}
