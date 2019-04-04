package com.example.c20_1_broadcast_orderedbroadcasts;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * <pre>
 * 定义Broadcase接受者
 * </pre>
 * 
 * @author caoying
 * 
 */
public class MyBroadcastReceiver2 extends BroadcastReceiver {

	public MyBroadcastReceiver2() {

	}

	// 接收广播
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		System.out.println(Thread.currentThread().getName() + "," + Thread.currentThread().getId() + "--MyBroadcastReceiver2--" + intent.getStringExtra("content"));
		intent.putExtra("content", "更改广播内容");
		System.out.println(Thread.currentThread().getName() + "," + Thread.currentThread().getId() + "--MyBroadcastReceiver2--" + intent.getStringExtra("content"));
	}

}
