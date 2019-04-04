package sundy.android.demo.broadcastreceiver;

import sundy.android.demo.R;
import sundy.android.demo.uibase.ViewActivity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class MyBroadcastReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context arg0, Intent arg1) {
		// TODO Auto-generated method stub
		if(arg1.getAction().equals("sundy.android.demo.HiMessage"))
		{
			NotificationManager notifyManager = (NotificationManager)arg0.getSystemService(Context.NOTIFICATION_SERVICE);
			
			int icon = R.drawable.icon;
			CharSequence tickerText = "�����Զ�����Ϣ����";
			long when = System.currentTimeMillis();

			//����һ��Notificationʵ��
			Notification notify = new Notification(icon, tickerText, when);
			
			PendingIntent pi = PendingIntent.getActivity(arg0, 1233, new Intent(Intent.ACTION_DIAL,Uri.parse("tel:10086")), 0)  ;
			//Notification���������¼���Ϣ
			notify.setLatestEventInfo(arg0, "���յ��Զ�����Ϣ!", arg1.getStringExtra("message"), pi)  ;
			//��������
			notifyManager.notify(11, notify) ;
		}
	}

}
