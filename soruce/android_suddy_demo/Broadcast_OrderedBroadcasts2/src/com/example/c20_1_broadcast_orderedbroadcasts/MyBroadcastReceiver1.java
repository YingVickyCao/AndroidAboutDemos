package com.example.c20_1_broadcast_orderedbroadcasts;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * <pre>
 * ����Broadcase������
 * </pre>
 * 
 * @author caoying
 * 
 */
public class MyBroadcastReceiver1 extends BroadcastReceiver {

	public MyBroadcastReceiver1() {

	}

	// ���չ㲥
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		System.out.println(Thread.currentThread().getName() + "," + Thread.currentThread().getId() + "--MyBroadcastReceiver1--" + intent.getStringExtra("content"));
	}

}
