package com.example.one_seven;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class NextActivity extends Activity {
	private static String TAG = "NLOG";
	private static String FILE_NAME;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_next);
		
		FILE_NAME = this.getClass().getSimpleName() + "->";
		Log.i(TAG, FILE_NAME + "onCreate func called!");
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
	public void finish() {
		super.finish();
		Log.i(TAG, FILE_NAME + "finish func called!");
	}
}

	
