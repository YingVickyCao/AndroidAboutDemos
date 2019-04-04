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
public class Intent_Class extends ActionBarActivity {
	private String TAG = "A";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_intent_class);

		setTitle("这是Intent_Class主界面");
		Log.d(TAG, "onCreate func called!taskId=" + getTaskId());
	}

	public void doClick(View view) {
		switch (view.getId()) {
		case R.id.btn_startA:
			Intent intent = getIntent();
			intent.setClass(Intent_Class.this, A.class);
			startActivity(intent);
			break;

		case R.id.btn_startB:
			Intent intent2 = getIntent();
			intent2.setClass(Intent_Class.this, B.class);
			startActivity(intent2);
			break;
		}
	}
}
