package com.example.c20_2_broadcast_lifecycle;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		Toast.makeText(context, "MyBroadcastReceiver->"+intent.getStringExtra("content"), Toast.LENGTH_SHORT).show();
		
		NotificationManager manager =(NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
		NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
		// 设置通知来时，在通知栏提示的信息
		builder.setTicker("您有新的通知了");
		// 设置通知的内容图片、内容标题、内容正文。
		builder.setSmallIcon(R.drawable.house);
		builder.setContentTitle("通知");
		builder.setContentText("MyBroadcastReceiver->"+intent.getStringExtra("content"));
		manager.notify(1001, builder.build());
	}

}
