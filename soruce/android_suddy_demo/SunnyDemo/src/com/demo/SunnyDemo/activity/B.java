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
public class B extends ActionBarActivity {
	private String TAG = "B";
	// A被创建的次数
	public static int count_B = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_b);

		Log.d(TAG, "onCreate func called!taskId=" + getTaskId());
		// 创建一次，加1
		Log.d(TAG, "onCreate func called! after count_B=" + count_B);
		count_B++;
		Log.d(TAG, "onCreate func called! after count_B=" + count_B);
		setTitle("Activity B，B=" + count_B);
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
			Intent intent = new Intent(B.this, A.class);
			startActivity(intent);
			break;

		case R.id.btn_startB:
			Intent intent2 = new Intent(B.this, B.class);
			startActivity(intent2);
			break;
		}

	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.d(TAG, "onDestroy func called!");
		Log.d(TAG, "onDestroy func called! befor count_B=" + count_B);
		count_B--;
		Log.d(TAG, "onDestroy func called! befor count_B=" + count_B);
	}
}
