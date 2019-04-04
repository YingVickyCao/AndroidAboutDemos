package demo.thread_handler_message;

import com.example.c21_4_handler_message.R;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

/**
 * <pre>
 * 各种形式：获取Message，并使用Handler发送和接收Message。
 * </pre>
 * 
 * @author caoying
 * 
 */
public class MainActivity extends ActionBarActivity {
	private TextView mContentTv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mContentTv = (TextView) findViewById(R.id.tv_content);
	}

	public void doSendMsg(View view) {
		switch (view.getId()) {
		case R.id.btn_send1:
			new Thread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					sendMsg1();
				}
			}).start();
			break;

		case R.id.btn_send2:
			new Thread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					sendMsg2();
				}
			}).start();
			break;

		case R.id.btn_send3:
			new Thread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					sendMsg3();
				}
			}).start();
			break;

		case R.id.btn_send4:
			new Thread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					sendMsg4();
				}
			}).start();
			break;

		case R.id.btn_send5:
			new Thread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					sendMsg5();
				}
			}).start();
			break;

		case R.id.btn_send6:
			new Thread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					sendMsg6();
				}
			}).start();
			break;

		case R.id.btn_send7:
			new Thread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					sendMsg7();
				}
			}).start();
			break;
		}
	}

	private Handler mHandler = new Handler() {
		@Override
		public void handleMessage(android.os.Message msg) {
			System.out.println("In handleMessage func receive ：" + Thread.currentThread().getName() + "," + Thread.currentThread().getId() + "，" + msg.arg1 + "--" + msg.what + "--" + msg.arg2 + "--"
					+ msg.obj);
			// mContentTv.append(msg.arg1 + "--" + msg.what + "--" + msg.arg2 +
			// "--" + msg.obj);
			mContentTv.setText(msg.arg1 + "--" + msg.what + "--" + msg.arg2 + "--" + msg.obj);
		};
	};

	private void sendMsg1() {
		Message message = Message.obtain();
		message.arg1 = 1;
		message.what = 1001;
		System.out.println("In sendMsg1 func send ：" + Thread.currentThread().getName() + "," + Thread.currentThread().getId() + "，" + message.arg1 + "--" + message.what);
		mHandler.sendMessage(message);
	}

	private void sendMsg2() {
		Message message = Message.obtain(mHandler);
		message.arg1 = 2;
		message.what = 1002;
		System.out.println("In sendMsg2 func send ：" + Thread.currentThread().getName() + "," + Thread.currentThread().getId() + "，" + message.arg1 + "--" + message.what);
		message.sendToTarget();
	}

	private void sendMsg3() {
		Message message = Message.obtain(mHandler, 1003);
		message.arg1 = 3;
		System.out.println("In sendMsg3 func send ：" + Thread.currentThread().getName() + "," + Thread.currentThread().getId() + "，" + message.arg1 + "--" + message.what);
		message.sendToTarget();
	}

	private void sendMsg4() {
		Message message = Message.obtain(mHandler, 1004, 4, 0, null);
		System.out.println("In sendMsg4 func send ：" + Thread.currentThread().getName() + "," + Thread.currentThread().getId() + "，" + message.arg1 + "--" + message.what);
		message.sendToTarget();
	}

	private void sendMsg5() {
		Message message = mHandler.obtainMessage();
		message.arg1 = 5;
		message.what = 1005;
		System.out.println("In sendMsg5 func send ：" + Thread.currentThread().getName() + "," + Thread.currentThread().getId() + "，" + message.arg1 + "--" + message.what);
		mHandler.sendMessageDelayed(message, 3000);
	}

	private void sendMsg6() {
		Message message = mHandler.obtainMessage(1006);
		message.arg1 = 6;
		System.out.println("In sendMsg5 func send ：" + Thread.currentThread().getName() + "," + Thread.currentThread().getId() + "，" + message.arg1 + "--" + message.what);
		message.sendToTarget();
	}

	private void sendMsg7() {
		Message message = mHandler.obtainMessage(1007, 7, 0, null);
		System.out.println("In sendMsg7 func send ：" + Thread.currentThread().getName() + "," + Thread.currentThread().getId() + "，" + message.arg1 + "--" + message.what);
		message.sendToTarget();
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
