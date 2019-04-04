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
		// ����֪ͨ��ʱ����֪ͨ����ʾ����Ϣ
		builder.setTicker("�����µ�֪ͨ��");
		// ����֪ͨ������ͼƬ�����ݱ��⡢�������ġ�
		builder.setSmallIcon(R.drawable.house);
		builder.setContentTitle("֪ͨ");
		builder.setContentText("MyBroadcastReceiver->"+intent.getStringExtra("content"));
		manager.notify(1001, builder.build());
	}

}
