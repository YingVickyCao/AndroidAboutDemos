package com.demo.SunnyDemo.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

/**
 * <pre>
 * 布局为自定义布局
 * </pre>
 * 
 */
public class AddCustomView3Activity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		// 声明布局
		CustomView customView = new CustomView(AddCustomView3Activity.this);
		customView.setText("Hello");
		// 填充布局
		setContentView(customView);
		// 添加事件
		customView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(AddCustomView3Activity.this, "Hello,这是一个动态加载控件的例子", Toast.LENGTH_SHORT).show();
			}
		});

	}
}
