package com.demo.SunnyDemo.activity;

import com.demo.SunnyDemo.R;
import com.demo.SunnyDemo.Tools;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class JumpWithBackValueActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_jump_with_backvalue);
	}

	public void doClick(View view) {

		// 取出收到的值
		// Intent intent = this.getIntent();
		Intent intent = getIntent();
		String content = intent.getStringExtra("content");

		// 显示收到的值
		Toast.makeText(JumpWithBackValueActivity.this, content, Toast.LENGTH_SHORT).show();

		// 回传值
		intent.putExtra("back_content", "back from JumpWithBackValueActivity");
		setResult(1001, intent);
		finish();
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		Log.d(Tools.getTag(getApplication()), Tools.getMethodName(getApplication()));
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		Log.d(Tools.getTag(getApplication()), Tools.getMethodName(getApplication()));
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.d(Tools.getTag(getApplication()), Tools.getMethodName(getApplication()));
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		Log.d(Tools.getTag(getApplication()), Tools.getMethodName(getApplication()));
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		Log.d(Tools.getTag(getApplication()), Tools.getMethodName(getApplication()));
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.d(Tools.getTag(getApplication()), Tools.getMethodName(getApplication()));
	}
}
