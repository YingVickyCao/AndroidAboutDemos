package com.demo.SunnyDemo;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.demo.SunnyDemo.activity.FirstActivity;

/**
 * <pre>
 * 程序ReadResource：
 * 读取raw和assets文件。
 * @author caoying
 * </pre>
 */
public class MainActivity extends ActionBarActivity {
	private String TAG = "MainActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 如果没有设置setContentView，程序会怎么样？
		// 对于NativeActionModeAwareLayout，如果设置了setContentView，则加载对应的layout。如果没有设置，程序运行正常，只是设计的layout没有被显示出来。这是因为使用了默认的layout。
		// setContentView(R.layout.activity_main);
		setContentView(R.layout.activity_main);

		// 实际上是这个
		// setContentView(com.demo.SunnyDemo.R.layout.activity_main);
		// 等价于setContentView(R.layout.activity_main);
		// this.getWindow().setContentView(this.getLayoutInflater().inflate(R.layout.activity_main,
		// null));

		// 获取当期类名
		// this.getApplication().getClass().getSimpleName();

		Log.d(TAG, "onCreate func called!");
	}

	public void doClick(View view) {
		switch (view.getId()) {
		case R.id.btn1:
			Intent intent2 = new Intent(MainActivity.this, FirstActivity.class);
			startActivity(intent2);
			break;
		}
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();

		Log.d(TAG, "onRestart func called!");
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		Log.d(TAG, "onStart func called!");
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.d(TAG, "onResume func called!");
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		Log.d(TAG, "onPause func called!");
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		Log.d(TAG, "onStop func called!");
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.d(TAG, "onDestroy func called!");
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

	class applicationf extends Application {

	}
}
