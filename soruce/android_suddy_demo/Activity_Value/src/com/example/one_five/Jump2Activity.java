package com.example.one_five;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Jump2Activity extends Activity {
	private Button mshowBtn;

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
		mshowBtn = (Button) findViewById(R.id.btn_show);
	}

	private void setVlaues() {

	}

	private void setListeners() {
		mshowBtn.setOnClickListener(showLsn);
	}

	private View.OnClickListener showLsn = new View.OnClickListener() {

		@Override
		public void onClick(View v) {

			// intent.putExtra("name", "cy");
			//
			// // 大量数据用bundle。或序列化
			// Bundle bundle = new Bundle();
			// bundle.putInt("id", 1000);
			// ArrayList<String> numbers = new ArrayList<String>();
			// numbers.add("one");
			// numbers.add("one");
			// numbers.add("five");
			// bundle.putStringArrayList("number", numbers);
			// bundle.putString("dog", "Tom");
			//
			// intent.putExtra("bundle", bundle);

			String show = "";
			Intent intent = getIntent();
			show = "name=" + intent.getStringExtra("name") + "\n";
			show += "workid=" + intent.getIntExtra("workid", 0) + "\n";

			//
			Bundle bundle = intent.getBundleExtra("bundle" + "");
			show += "number=" + bundle.getStringArrayList("number") + "\n";
			show += "dog=" + bundle.getString("dog");
			Toast.makeText(getApplication(), show, Toast.LENGTH_LONG).show();
		}
	};

}
