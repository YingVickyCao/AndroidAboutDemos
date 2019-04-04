package com.example.one_six;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * <pre>
 * 程序one_six：
 * 测试界面跳转与返回时的回传值问题。
 * 相关函数：
 * ①startActivityForResult
 * ②onActivityResult
 * ③setResult
 * 
 * <pre>
 */
public class MainActivity extends ActionBarActivity {
	private Button mJump1Btn;
	private Button mJump2Btn;
	private TextView mResultTv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		findViews();
		setVlaues();
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

	private void findViews() {
		mJump1Btn = (Button) findViewById(R.id.btn_jump1);
		mJump2Btn = (Button) findViewById(R.id.btn_jump2);
		mResultTv = (TextView) findViewById(R.id.tv_resutlt);
	}

	private void setVlaues() {

	}

	private void setListeners() {
		mJump1Btn.setOnClickListener(jump1Lsn);
		mJump2Btn.setOnClickListener(jump2Lsn);
	}

	/**
	 * <pre>
	 * 请求A界面时，requestCode设为100，从A界面返回时，resultCode设为101。
	 * 请求B界面时，requestCode设为200，从B界面返回时，resultCode设为201。
	 * </pre>
	 */
	private View.OnClickListener jump1Lsn = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			Intent intent = new Intent(MainActivity.this, Jump1Activity.class);
			intent.putExtra("A_request", "向A发送数据");
			startActivityForResult(intent, 100);
		}
	};

	private View.OnClickListener jump2Lsn = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			Intent intent = new Intent(MainActivity.this, Jump2Activity.class);
			intent.putExtra("B_request", "向B发送数据");
			startActivityForResult(intent, 200);
		}
	};

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		Log.i("onActivityResult", "requestCode=" + requestCode + ",resultCode=" + resultCode + ",date=" + data);
		if (100 == requestCode && 101 == resultCode) {
			mResultTv.setText(data.getStringExtra("A_response"));
		}

		if (200 == requestCode && 201 == resultCode) {
			mResultTv.setText(data.getStringExtra("B_response"));
		}
	};

}
