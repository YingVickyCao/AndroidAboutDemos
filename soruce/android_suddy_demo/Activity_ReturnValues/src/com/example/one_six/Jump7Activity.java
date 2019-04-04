package com.example.one_six;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Jump7Activity extends Activity {
	private Button mBackBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_jump2);

		findViews();
		setVlaues();
		setListeners();
	}

	private void findViews() {
		mBackBtn = (Button) findViewById(R.id.btn_back);
	}

	private void setVlaues() {

	}

	private void setListeners() {
		mBackBtn.setOnClickListener(backLsn);
	}

	private View.OnClickListener backLsn = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			Intent intent = getIntent();
			Toast.makeText(getApplication(), "B画面收到的数据=" + intent.getStringExtra("B_request"), Toast.LENGTH_LONG).show();

			// Intent intent2 = new Intent();
			// intent2.putExtra("B_response", "B返回数据了");
			// setResult(201, intent2);
			intent.putExtra("B_response", "B返回数据了");
			setResult(201, intent);
			finish();
		}
	};

}
