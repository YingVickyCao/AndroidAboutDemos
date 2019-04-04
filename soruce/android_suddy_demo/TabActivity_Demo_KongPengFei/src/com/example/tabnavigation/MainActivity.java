package com.example.tabnavigation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener
{

	private Button button1;
	private Button button2;
	private Button button3;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        button1=(Button)findViewById(R.id.button1);
        button1.setOnClickListener(this);
        button2=(Button)findViewById(R.id.button2);
        button2.setOnClickListener(this);
        button3=(Button)findViewById(R.id.button3);
        button3.setOnClickListener(this);
    }

	@Override
	public void onClick(View v) 
	{
		switch(v.getId())
		{
		 case R.id.button1:
		 {
			Intent intent=new Intent();
			intent.setClass(this, CMBActivity.class);
			MainActivity.this.startActivity(intent);
		 }
		 break;
		case R.id.button2:
		 {
			Intent intent=new Intent();
			intent.setClass(this, LudashiActivity.class);
			MainActivity.this.startActivity(intent);
		 }
		 break;
		case R.id.button3:
		 {
			Intent intent=new Intent();
			intent.setClass(this, GjjActivity.class);
			MainActivity.this.startActivity(intent);
		 }
		 break;
		}
	}
		


}