package demo.thread_handler_textview;

import com.example.c21_1_handler_textview.R;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

/**
 * <pre>
 * ʹ���̺߳�Handler����TextView
 * �̸߳����ȡ���ݡ�������Ϣ��Handler������ղ�������Ϣ������UI��
 * </pre>
 * 
 * @author caoying
 * 
 */
public class MainActivity extends ActionBarActivity {
	private MyHanlder mHandler;
	private TextView mContentTv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mContentTv = (TextView) findViewById(R.id.tv_content);
		mHandler = new MyHanlder();
	}

	// ����˰�ť��ʼ�������ݡ�
	// �̸߳����ȡ���ݡ�������Ϣ��Handler������ղ�������Ϣ������UI��
	public void doAppendContent(View view) {
		new Thread(new MyThreadRunnable()).start();
	}

	private class MyThreadRunnable implements Runnable {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			// ����Ϣ���л�ȡ��Ϣ������У���ȡ����ϢЯ�����ݣ���handler���͸���Ϣ�����û�У��򴴽�һ����Ϣ��
			Message message = Message.obtain();
			message.arg1 = 2000;
			message.obj = "hello o world";
			Bundle bundlerData = new Bundle();
			bundlerData.putInt("year", 2015);
			bundlerData.putString("name", "CY");
			message.setData(bundlerData);
			mHandler.sendMessage(message);
		}
	}

	public class MyHanlder extends Handler {
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);

			mContentTv.append(Integer.toString(msg.arg1) + "\n");
			mContentTv.append(msg.obj.toString() + "\n");
			mContentTv.append(msg.getData().getInt("year") + "\n");
			mContentTv.append(msg.getData().getString("name"));
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
