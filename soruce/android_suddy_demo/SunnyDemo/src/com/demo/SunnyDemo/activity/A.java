package com.demo.SunnyDemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;

import com.demo.SunnyDemo.R;

/**
 * <pre>
 * 练习launchMode
 * @author caoying
 * </pre>
 */
public class A extends ActionBarActivity {
	private String TAG = "A";
	// A被创建的次数
	public static int count_A = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_a);

		Log.d(TAG, "onCreate func called!taskId=" + getTaskId());

		Log.d(TAG, "onCreate func called! befor count_A=" + count_A);
		// 创建一次，加1
		count_A++;
		Log.d(TAG, "onCreate func called! after count_A=" + count_A);
		setTitle("Activity A，A=" + count_A);
	}

	@Override
	protected void onNewIntent(Intent intent) {
		// TODO Auto-generated method stub
		super.onNewIntent(intent);

		Log.d(TAG, "onNewIntent func called!");
	}

	public void doClick(View view) {
		switch (view.getId()) {
		case R.id.btn_startA:
			Intent intent = new Intent(A.this, A.class);
			startActivity(intent);
			break;

		case R.id.btn_startB:
			Intent intent2 = new Intent(A.this, B.class);
			startActivity(intent2);
			break;
		}

	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.d(TAG, "onDestroy func called!");
		Log.d(TAG, "onDestroy func called! befor count_A=" + count_A);
		count_A--;
		Log.d(TAG, "onDestroy func called! befor count_A=" + count_A);
	}
}
