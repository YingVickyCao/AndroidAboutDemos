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
 * 布局为自定义布局，
 * 控件为自定义控件。
 * 
 * </pre>
 * 
 */
public class AddCustomViewActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		// 定义要加载的布局
		LinearLayout layout = new LinearLayout(AddCustomViewActivity.this);
		layout.setPadding(20, 20, 20, 20);
		layout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));

		// 定义要加载的控件
		Button button = new Button(AddCustomViewActivity.this);
		button.setBackgroundColor(Color.RED);
		button.setWidth(500);
		button.setText("HelloWorld");
		button.setHeight(LayoutParams.WRAP_CONTENT);

		// 添加控件
		layout.addView(button);

		// 填充布局
		setContentView(layout);

		// 定义控件Button的Click事件
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(AddCustomViewActivity.this, "Hello,这是一个动态加载控件的例子", Toast.LENGTH_SHORT).show();
			}
		});
	}
}
