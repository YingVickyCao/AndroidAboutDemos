package com.demo.SunnyDemo.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

/**
 * <pre>
 * ����Ϊ�Զ��岼��
 * </pre>
 * 
 */
public class AddCustomView3Activity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		// ��������
		CustomView customView = new CustomView(AddCustomView3Activity.this);
		customView.setText("Hello");
		// ��䲼��
		setContentView(customView);
		// ����¼�
		customView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(AddCustomView3Activity.this, "Hello,����һ����̬���ؿؼ�������", Toast.LENGTH_SHORT).show();
			}
		});

	}
}
