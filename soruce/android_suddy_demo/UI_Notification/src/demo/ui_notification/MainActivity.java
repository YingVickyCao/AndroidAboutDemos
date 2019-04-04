package demo.ui_notification;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RemoteViews;

/**
 * <pre>
 * ֪ͨ����ʾ������
 * ʾ��1����֪ͨͨ��
 * ʾ��2���Զ���֪ͨ
 * </pre>
 * 
 */
public class MainActivity extends Activity {
	private NotificationCompat.Builder mBuilder;
	private NotificationManager mManager;
	private static int NF_UPDATE_ID = 1000;
	private static int NF_DOWNLOAD_ID = NF_UPDATE_ID + 1;
	private static int NF_DOWNLOAD_COMPLETE_ID = NF_DOWNLOAD_ID + 1;
	private static int NF_DOWNLOAD_COMPLETE__CUSTOM_ID = NF_DOWNLOAD_COMPLETE_ID + 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

	}

	/**
	 * <pre>
	 * ����һ����֪ͨͨ
	 * @param view
	 * 
	 * <pre>
	 */
	public void showNormalNotification(View view) {
		// �õ�Notificationʵ��
		mBuilder = new NotificationCompat.Builder(MainActivity.this);

		// �õ�NotificationManagerʵ����
		// ��Ҫ�õ�NOTIFICATION_SERVICE��������Ҫ�ײ�
		mManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

		// ���ñ���
		mBuilder.setContentTitle("����һ����֪ͨͨ");
		// ����������ʾ
		mBuilder.setContentText("�°汾�������ݣ�\n�����������ж������ʱ��ȡ�����ظ���\n���ϴ�ɨ�����ݹ��ܶ��������������ϴ�ʱ�����ظ��ϴ���ɨ��ƽ̨��\n�޸���ӡ�ɹ�ʱ��ʾ��Ϣ����ȷ����");
		// ����Сͼ��
		mBuilder.setSmallIcon(R.drawable.ic_launcher);
		// ��������������ʾ
		mBuilder.setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE);

		// ����֪ͨ��action���õ��֪ͨ����ת���û���
		Intent intent = new Intent(MainActivity.this, MainActivity.class);
		PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 10001, intent, PendingIntent.FLAG_ONE_SHOT);
		mBuilder.setContentIntent(pendingIntent);

		// ����֪ͨ
		// ���ID��ͬ���ᱻ���棨updated����֪ͨ���ǵ���
		mManager.notify(NF_UPDATE_ID, mBuilder.build());
	}

	/**
	 * <pre>
	 * ������½�������֪ͨ
	 * @param view
	 * 
	 * <pre>
	 */
	public void showProgressNotification(View view) {
		// �õ�����Notification Service��Manager
		mManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

		// �õ�Builder
		mBuilder = new NotificationCompat.Builder(MainActivity.this);

		// ����Сͼ��
		mBuilder.setSmallIcon(R.drawable.ic_launcher);

		// �������ı���
		mBuilder.setContentTitle("�����ļ�");

		// ������������
		mBuilder.setContentText("������");
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub

				// �����е�֪ͨ����
				for (int i = 0; i <= 100; i += 10) {
					// ������ʾ�̶ȣ����̶�Ϊ100�����ж�
					mBuilder.setProgress(100, i, false);

					// ��ʾ֪ͨ
					mManager.notify(NF_DOWNLOAD_ID, mBuilder.build());

					// ģ�����غ�ʱ
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				// ������ɺ��֪ͨ����
				// ����������Ϣ
				mBuilder.setContentText("��������ˡ�����鿴�ļ�");

				// �������������̶�
				mBuilder.setProgress(0, 0, false);

				// ����֪ͨ��action���õ��֪ͨ����ת���ڶ�������
				Intent intent = getIntent();
				intent.setClass(MainActivity.this, SecondActivity.class);
				PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, NF_DOWNLOAD_COMPLETE_ID, intent, PendingIntent.FLAG_ONE_SHOT);
				mBuilder.setContentIntent(pendingIntent);

				// ��ʾ֪ͨ
				mManager.notify(NF_DOWNLOAD_ID, mBuilder.build());

			}
		}).start();

	}

	/**
	 * <pre>
	 * �����Զ���֪ͨ
	 * @param view
	 * </pre>
	 */
	public void showCustomNotification(View view) {
		// �õ�NotificationManager
		mManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

		// �õ�Builder
		mBuilder = new NotificationCompat.Builder(MainActivity.this);

		// ����Сͼ��
		mBuilder.setSmallIcon(R.drawable.ic_launcher);

		// �����Զ���RemoteViews
		final RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.notification);
		// ����������Ϣ
		remoteViews.setTextViewText(R.id.tv_rv, "������");

		// ���RemoteViews
		mBuilder.setContent(remoteViews);

		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub

				// �����е�֪ͨ����
				for (int i = 0; i <= 100; i += 10) {
					// ������ʾ�̶ȣ����̶�Ϊ100�����ж�
					remoteViews.setProgressBar(R.id.pb_rv, 100, i, false);
					// ���ÿ̶�
					remoteViews.setTextViewText(R.id.tv_rv, Integer.toString(i) + "%100");
					// ���RemoteViews
					mBuilder.setContent(remoteViews);

					// ��ʾ֪ͨ
					mManager.notify(NF_DOWNLOAD_ID, mBuilder.build());

					// ģ�����غ�ʱ
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				// ������ɺ��֪ͨ����
				// ����������Ϣ
				remoteViews.setTextViewText(R.id.tv_rv, "��������ˡ�");
				// ���ÿ̶�
				remoteViews.setTextViewText(R.id.tv_rv, "100%100");
				// ���RemoteViews
				mBuilder.setContent(remoteViews);

				// ����֪ͨ��action���õ��֪ͨ����ת���ڶ�������
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, SecondActivity.class);
				PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, NF_DOWNLOAD_COMPLETE__CUSTOM_ID, intent, PendingIntent.FLAG_ONE_SHOT);
				mBuilder.setContentIntent(pendingIntent);

				// ��ʾ֪ͨ
				mManager.notify(NF_DOWNLOAD_ID, mBuilder.build());

			}
		}).start();

	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();

		// ����ȡ������֪ͨ
		mManager.cancelAll();
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
