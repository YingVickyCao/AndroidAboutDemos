package demo.thread_handler_progressbar;

import com.example.c21_3_handler_progressbar.R;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

/**
 * ����UI�ķ�ʽ����������
 * 
 * @author caoying
 * 
 */
public class MainActivity extends ActionBarActivity {
	private ProgressDialog mProgressDialog;

	private Handler mHandler = new Handler() {
		@Override
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			System.out.println(msg.arg1 + "," + msg.arg2 + "," + msg.what);
			// ��һ�̶�
			mProgressDialog.setProgress(msg.arg1);
			// �ڶ��̶�
			mProgressDialog.setSecondaryProgress(msg.arg2);
			// �ж����ؽ����ˣ�Ҫ�رս�����ʾ��
			if (10 == msg.what) {
				mProgressDialog.dismiss();
			}
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mProgressDialog = new ProgressDialog(MainActivity.this);
		mProgressDialog.setTitle("��������");
		mProgressDialog.setProgress(0);
		mProgressDialog.setSecondaryProgress(0);
		mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
	}

	public void doUpdateBar(View view) {

		mProgressDialog.show();
		new Thread(new MyRunnable()).start();
	}

	private class MyRunnable implements Runnable {
		@Override
		public void run() {
			// TODO Auto-generated method stub
			// ģ���ʱ����
			int i = 1;
			while (i <= 10) {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Message message = Message.obtain();
				message.arg1 = i * 10;
				message.arg2 = (i - 1) * 10;
				// ʹ��what��עMessage��Ϣ˵�������
				message.what = i;
				mHandler.sendMessage(message);

				i++;
			}
		}
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
