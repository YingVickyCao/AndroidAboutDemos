package com.example.c20_1_broadcast_orderedbroadcasts;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

/**
 * <pre>
 * 发送有序广播示例。
 * 注册方式：使用清单文件receiver tag。
 * 
 * 广播的接受优先级设定为：2(10) -> 3(9) -> 1(8)
 * （1）、当Intent指定为3,且中间没有终止广播时时，仅有3能收到广播。
 * Intent intent = new Intent(MainActivity.this, MyBroadcastReceiver3.class);
 * 06-25 08:49:13.435: I/System.out(8421): main,1--send sendOrderedBroadcast
 * 06-25 08:49:13.460: I/System.out(8421): main,1--MyBroadcastReceiver3--你好，这是一个广播示例
 * 
 * （2）、当Intent没有指定广播，且中间没有终止广播时，广播按既定顺序传递。
 * （3）、当Intent没有指定广播，某一个接受者改变了内容，且中间没有终止广播时，广播按既定顺序传递，改变的内容不能传递其他接受者。
 * Intent intent = new Intent();
 * 06-25 08:51:13.160: I/System.out(8596): main,1--send sendOrderedBroadcast
 * 06-25 08:51:13.180: I/System.out(8596): main,1--MyBroadcastReceiver2--你好，这是一个广播示例
 * 06-25 08:51:13.180: I/System.out(8596): main,1--MyBroadcastReceiver2--更改广播内容
 * 06-25 08:51:13.185: I/System.out(8596): main,1--MyBroadcastReceiver3--你好，这是一个广播示例
 * 06-25 08:51:13.185: I/System.out(8596): main,1--MyBroadcastReceiver1--你好，这是一个广播示例
 * 
 * （4）、当Intent没有指定广播，且3时终止广播时，广播按既定顺序传递，且1不能收到广播。
 * Intent intent = new Intent();
 * 3:abortBroadcast();
 * 06-25 08:52:01.635: I/System.out(8746): main,1--send sendOrderedBroadcast
 * 06-25 08:52:01.655: I/System.out(8746): main,1--MyBroadcastReceiver2--你好，这是一个广播示例
 * 06-25 08:52:01.655: I/System.out(8746): main,1--MyBroadcastReceiver2--更改广播内容
 * 06-25 08:52:01.660: I/System.out(8746): main,1--MyBroadcastReceiver3--你好，这是一个广播示例
 * 
 * （5）、当Intent没有指定广播，中间没有终止广播，且指定1的优先级接收时，任何接收者都不能接受广播。原因可能是与既定优先级有冲突。
 * Intent intent = new Intent();
 * sendOrderedBroadcast(intent, "8");
 * 06-25 08:56:48.080: I/System.out(9141): main,1--send sendOrderedBroadcast
 * 
 * </pre>
 * 
 * @author caoying
 * 
 */
public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void sendBroadcast(View view) {
		// Intent intent = new Intent(MainActivity.this,
		// MyBroadcastReceiver3.class);
		Intent intent = new Intent();
		intent.putExtra("content", "你好，这是一个广播示例");
		intent.setAction("test");
		// 发送广播
		// sendOrderedBroadcast(intent, null);
		sendOrderedBroadcast(intent, "8");
		System.out.println(Thread.currentThread().getName() + "," + Thread.currentThread().getId() + "--send sendOrderedBroadcast");
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
