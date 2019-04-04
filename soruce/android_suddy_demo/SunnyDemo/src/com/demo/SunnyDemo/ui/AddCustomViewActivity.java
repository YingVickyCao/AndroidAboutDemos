package com.demo.SunnyDemo.ui;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * <pre>
 * ����Ϊ�Զ��岼�֣�
 * �ؼ�Ϊ�Զ���ؼ���
 * 
 * </pre>
 * 
 */
public class AddCustomViewActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		// ����Ҫ���صĲ���
		LinearLayout layout = new LinearLayout(AddCustomViewActivity.this);
		layout.setPadding(20, 20, 20, 20);
		layout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));

		// ����Ҫ���صĿؼ�
		Button button = new Button(AddCustomViewActivity.this);
		button.setBackgroundColor(Color.RED);
		button.setWidth(500);
		button.setText("HelloWorld");
		button.setHeight(LayoutParams.WRAP_CONTENT);

		// ��ӿؼ�
		layout.addView(button);

		// ��䲼��
		setContentView(layout);

		// ����ؼ�Button��Click�¼�
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(AddCustomViewActivity.this, "Hello,����һ����̬���ؿؼ�������", Toast.LENGTH_SHORT).show();
			}
		});
	}
}
