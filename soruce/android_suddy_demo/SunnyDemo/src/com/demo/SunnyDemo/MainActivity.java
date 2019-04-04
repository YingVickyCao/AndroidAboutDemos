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
 * ����ReadResource��
 * ��ȡraw��assets�ļ���
 * @author caoying
 * </pre>
 */
public class MainActivity extends ActionBarActivity {
	private String TAG = "MainActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// ���û������setContentView���������ô����
		// ����NativeActionModeAwareLayout�����������setContentView������ض�Ӧ��layout�����û�����ã���������������ֻ����Ƶ�layoutû�б���ʾ������������Ϊʹ����Ĭ�ϵ�layout��
		// setContentView(R.layout.activity_main);
		setContentView(R.layout.activity_main);

		// ʵ���������
		// setContentView(com.demo.SunnyDemo.R.layout.activity_main);
		// �ȼ���setContentView(R.layout.activity_main);
		// this.getWindow().setContentView(this.getLayoutInflater().inflate(R.layout.activity_main,
		// null));

		// ��ȡ��������
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
