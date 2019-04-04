package com.demo.SunnyDemo.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.demo.SunnyDemo.R;

/**
 * <pre>
 * 布局为XML布局，
 * 控件为自定义控件。
 * 
 * </pre>
 * 
 */
public class AddCustomView2Activity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// 填充布局
		setContentView(R.layout.ui_add_custom_view);

		LinearLayout layout = (LinearLayout) findViewById(R.id.ll);

		// 定义要加载的控件
		Button button = new Button(AddCustomView2Activity.this);
		button.setBackgroundColor(Color.YELLOW);
		button.setWidth(500);
		button.setAlpha(10);
		button.setText("HelloWorld");
		button.setHeight(LayoutParams.WRAP_CONTENT);

		// 添加控件
		layout.addView(button);

		// 定义控件Button的Click事件
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(AddCustomView2Activity.this, "Hello,这是一个动态加载控件的例子", Toast.LENGTH_SHORT).show();
			}
		});
	}
}
