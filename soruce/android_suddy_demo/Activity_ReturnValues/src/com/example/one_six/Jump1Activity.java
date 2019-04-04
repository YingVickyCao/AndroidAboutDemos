package com.example.one_six;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Jump1Activity extends Activity {
	private Button mBackBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_jump1);

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
			Toast.makeText(getApplication(), "A画面收到的数据=" + intent.getStringExtra("A_request"), Toast.LENGTH_LONG).show();

			// Intent intent2 = new Intent();
			// intent2.putExtra("A_response", "A返回数据了");
			// setResult(101, intent2);
			intent.putExtra("A_response", "A返回数据了");
			setResult(101, intent);
			finish();
		}
	};
}
