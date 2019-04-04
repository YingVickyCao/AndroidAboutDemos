package com.demo.SunnyDemo.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ToggleButton;

import com.demo.SunnyDemo.R;

/**
 * <pre>
 * ToggleButton
 * </pre>
 */
public class UIViewActivity extends Activity {
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.ui_view);

		

		// CheckBox����Ҫ�¼�:onCheckedChanged������ѡ�к�ȡ����������
		CheckBox checkBox;
		checkBox = (CheckBox) findViewById(R.id.checkbox_meat);
		checkBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					Toast.makeText(UIViewActivity.this, "meat��ѡ����2", Toast.LENGTH_SHORT).show();
				} else {
					Toast.makeText(UIViewActivity.this, "meat��ȡ����2", Toast.LENGTH_SHORT).show();
				}
			}
		});

	
	}

	// CheckBox����Ҫ�¼�:onClick������ѡ�к�ȡ����������
	public void onCheckboxClicked(View view) {
		boolean checked = ((CheckBox) view).isChecked();
		switch (view.getId()) {
		/*
		 * case R.id.checkbox_meat: if (checked) {
		 * Toast.makeText(UIViewActivity.this, "meat��ѡ����",
		 * Toast.LENGTH_SHORT).show(); } else {
		 * Toast.makeText(UIViewActivity.this, "meat��ȡ����",
		 * Toast.LENGTH_SHORT).show(); } break;
		 */

		case R.id.checkbox_cheese:
			if (checked) {
				Toast.makeText(UIViewActivity.this, "cheese��ѡ����", Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(UIViewActivity.this, "cheese��ȡ����", Toast.LENGTH_SHORT).show();
			}
			break;

		}
	}

}
