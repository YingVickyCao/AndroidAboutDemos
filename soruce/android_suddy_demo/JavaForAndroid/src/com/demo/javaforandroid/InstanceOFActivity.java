package com.demo.javaforandroid;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class InstanceOFActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_instance);
		
		// ��ȡcolor string array
		String[] colors = getResources().getStringArray(R.array.color_array);
		int len = colors.length;

		// ����LinearLayout�ĵ�һ�ַ�ʽ
		LinearLayout linearLayout = (LinearLayout) findViewById(R.id.ll);

		// ��̬�����ɫ��Button�ؼ�
		for (int i = 0; i < len; i++) {
			Button child = new Button(InstanceOFActivity.this);
			child.setWidth(LayoutParams.MATCH_PARENT);
			child.setHeight(LayoutParams.WRAP_CONTENT);
			child.setBackgroundColor(Color.parseColor(colors[i]));
			child.setText("������ɫ");

			linearLayout.addView(child);
		}

		// ���TextView�ؼ�
		// TODO û�м��س���
		TextView textView = new TextView(InstanceOFActivity.this);
		textView.setWidth(LayoutParams.MATCH_PARENT);
		textView.setHeight(LayoutParams.WRAP_CONTENT);
		textView.setBackgroundColor(Color.parseColor("#99FF66"));
		textView.setText("������ɫ");
		linearLayout.addView(textView);

		// ���EditText�ؼ�
		// TODO û�м��س���
		EditText editText = new EditText(InstanceOFActivity.this);
		editText.setWidth(LayoutParams.MATCH_PARENT);
		editText.setHeight(LayoutParams.WRAP_CONTENT);
		editText.setBackgroundColor(Color.parseColor("#00FFCC"));
		editText.setText("������ɫ");
		linearLayout.addView(editText);

		// ����LinearLayout�ĵڶ��ַ�ʽ
		LinearLayout layoutRoot = (LinearLayout) this.getLayoutInflater().inflate(R.layout.activity_instance, null);
		int count = linearLayout.getChildCount();
		for (int i = 0; i < count; i++) {
			final View view = linearLayout.getChildAt(i);
			view.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					if (view instanceof TextView) {
						Toast.makeText(InstanceOFActivity.this, "����һ��TextView", Toast.LENGTH_SHORT).show();
					} else if (view instanceof Button) {
						Toast.makeText(InstanceOFActivity.this, "����һ��Button", Toast.LENGTH_SHORT).show();
					} else if (view instanceof EditText) {
						Toast.makeText(InstanceOFActivity.this, "����һ��EditText", Toast.LENGTH_SHORT).show();
					}
				}
			});
		}

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
}
