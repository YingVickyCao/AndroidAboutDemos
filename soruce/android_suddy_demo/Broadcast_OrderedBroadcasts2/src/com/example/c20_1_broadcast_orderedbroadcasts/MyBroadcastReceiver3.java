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
public class MyBroadcastReceiver3 extends BroadcastReceiver {

	public MyBroadcastReceiver3() {

	}

	// ���չ㲥
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		System.out.println(Thread.currentThread().getName() + "," + Thread.currentThread().getId() + "--MyBroadcastReceiver3--" + intent.getStringExtra("content"));
		//abortBroadcast();
	}

}
