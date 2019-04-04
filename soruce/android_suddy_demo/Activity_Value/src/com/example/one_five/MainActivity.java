package com.example.one_five;

import java.util.ArrayList;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends ActionBarActivity {
	private Button mJump1Btn;
	private Button mJump2Btn;

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
	}

	private void setVlaues() {

	}

	private void setListeners() {
		mJump1Btn.setOnClickListener(jump1Lsn);
		mJump2Btn.setOnClickListener(jump2Lsn);
	}

	private View.OnClickListener jump1Lsn = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			// 方式1：最常用
			// Intent intent = new Intent(MainActivity.this,
			// Jump1Activity.class);
			// startActivity(intent);

			// 方式2
			Intent intent = new Intent();
			intent.setClass(MainActivity.this, Jump1Activity.class);
			startActivity(intent);
		}
	};

	private View.OnClickListener jump2Lsn = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			Intent intent = new Intent(MainActivity.this, Jump2Activity.class);
			intent.putExtra("name", "cy");

			// 大量数据用bundle或序列化
			ArrayList<String> numbers = new ArrayList<String>();
			numbers.add("one");
			numbers.add("one");
			numbers.add("five");
			
			Bundle bundle = new Bundle();
			bundle.putInt("id", 1000);
			bundle.putStringArrayList("number", numbers);
			bundle.putString("dog", "Tom");

			intent.putExtra("bundle", bundle);
			startActivity(intent);
		}
	};
	
	public void startOtherApp(View view)
	{
		
	}

}
