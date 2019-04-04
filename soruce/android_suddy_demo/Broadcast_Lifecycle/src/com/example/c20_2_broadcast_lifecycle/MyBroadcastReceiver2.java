package com.example.c20_2_broadcast_lifecycle;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyBroadcastReceiver2 extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		Toast.makeText(context, "MyBroadcastReceiver2->"+intent.getStringExtra("content"), Toast.LENGTH_SHORT).show();
	}

}
