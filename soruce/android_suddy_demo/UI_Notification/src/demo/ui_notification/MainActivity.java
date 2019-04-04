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
 * 通知提醒示例程序。
 * 示例1：普通通知。
 * 示例2：自定义通知
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
	 * 定义一个普通通知
	 * @param view
	 * 
	 * <pre>
	 */
	public void showNormalNotification(View view) {
		// 得到Notification实例
		mBuilder = new NotificationCompat.Builder(MainActivity.this);

		// 得到NotificationManager实例。
		// 需要得到NOTIFICATION_SERVICE服务，这需要底层
		mManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

		// 设置标题
		mBuilder.setContentTitle("这是一个普通通知");
		// 设置内容提示
		mBuilder.setContentText("新版本更新内容：\n待揽件功能中多次请求时获取订单重复。\n待上传扫描数据功能多次连续点击立即上传时数据重复上传到扫描平台。\n修复打印成功时提示信息不真确问题");
		// 设置小图标
		mBuilder.setSmallIcon(R.drawable.ic_launcher);
		// 设置声音和震动提示
		mBuilder.setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE);

		// 设置通知的action：该点击通知，跳转到该画面
		Intent intent = new Intent(MainActivity.this, MainActivity.class);
		PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 10001, intent, PendingIntent.FLAG_ONE_SHOT);
		mBuilder.setContentIntent(pendingIntent);

		// 发送通知
		// 如果ID相同，会被后面（updated）的通知覆盖掉。
		mManager.notify(NF_UPDATE_ID, mBuilder.build());
	}

	/**
	 * <pre>
	 * 定义更新进度条的通知
	 * @param view
	 * 
	 * <pre>
	 */
	public void showProgressNotification(View view) {
		// 得到管理Notification Service的Manager
		mManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

		// 得到Builder
		mBuilder = new NotificationCompat.Builder(MainActivity.this);

		// 设置小图标
		mBuilder.setSmallIcon(R.drawable.ic_launcher);

		// 设置正文标题
		mBuilder.setContentTitle("下载文件");

		// 设置正文内容
		mBuilder.setContentText("下载中");
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub

				// 下载中的通知设置
				for (int i = 0; i <= 100; i += 10) {
					// 设置显示刻度：最大刻度为100，不中断
					mBuilder.setProgress(100, i, false);

					// 显示通知
					mManager.notify(NF_DOWNLOAD_ID, mBuilder.build());

					// 模拟下载耗时
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				// 下载完成后的通知设置
				// 设置正文消息
				mBuilder.setContentText("下载完成了。点击查看文件");

				// 完成任务后，消除刻度
				mBuilder.setProgress(0, 0, false);

				// 设置通知的action：该点击通知，跳转到第二个画面
				Intent intent = getIntent();
				intent.setClass(MainActivity.this, SecondActivity.class);
				PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, NF_DOWNLOAD_COMPLETE_ID, intent, PendingIntent.FLAG_ONE_SHOT);
				mBuilder.setContentIntent(pendingIntent);

				// 显示通知
				mManager.notify(NF_DOWNLOAD_ID, mBuilder.build());

			}
		}).start();

	}

	/**
	 * <pre>
	 * 定义自定义通知
	 * @param view
	 * </pre>
	 */
	public void showCustomNotification(View view) {
		// 得到NotificationManager
		mManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

		// 得到Builder
		mBuilder = new NotificationCompat.Builder(MainActivity.this);

		// 设置小图标
		mBuilder.setSmallIcon(R.drawable.ic_launcher);

		// 设置自定义RemoteViews
		final RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.notification);
		// 设置正文消息
		remoteViews.setTextViewText(R.id.tv_rv, "下载中");

		// 填充RemoteViews
		mBuilder.setContent(remoteViews);

		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub

				// 下载中的通知设置
				for (int i = 0; i <= 100; i += 10) {
					// 设置显示刻度：最大刻度为100，不中断
					remoteViews.setProgressBar(R.id.pb_rv, 100, i, false);
					// 设置刻度
					remoteViews.setTextViewText(R.id.tv_rv, Integer.toString(i) + "%100");
					// 填充RemoteViews
					mBuilder.setContent(remoteViews);

					// 显示通知
					mManager.notify(NF_DOWNLOAD_ID, mBuilder.build());

					// 模拟下载耗时
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				// 下载完成后的通知设置
				// 设置正文消息
				remoteViews.setTextViewText(R.id.tv_rv, "下载完成了。");
				// 设置刻度
				remoteViews.setTextViewText(R.id.tv_rv, "100%100");
				// 填充RemoteViews
				mBuilder.setContent(remoteViews);

				// 设置通知的action：该点击通知，跳转到第二个画面
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, SecondActivity.class);
				PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, NF_DOWNLOAD_COMPLETE__CUSTOM_ID, intent, PendingIntent.FLAG_ONE_SHOT);
				mBuilder.setContentIntent(pendingIntent);

				// 显示通知
				mManager.notify(NF_DOWNLOAD_ID, mBuilder.build());

			}
		}).start();

	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();

		// 设置取消所有通知
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
