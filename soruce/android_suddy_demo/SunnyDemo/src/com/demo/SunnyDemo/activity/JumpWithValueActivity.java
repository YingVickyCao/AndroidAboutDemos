package com.demo.SunnyDemo.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.demo.SunnyDemo.R;
import com.demo.SunnyDemo.Tools;

public class JumpWithValueActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_jump_with_value);

		// 显示传递过来的值
		// Intent intent = this.getIntent();
		Intent intent = getIntent();
		boolean b = intent.getBooleanExtra("b", false);
		String address = intent.getStringExtra("address");
		Bundle bundle = intent.getExtras();
		String name = bundle.getString("name");
		Toast.makeText(JumpWithValueActivity.this, b + "\n" + address + "\n" + name, Toast.LENGTH_SHORT).show();
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
