package demo.c18_10_service_ipc_messager;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.view.View;
import android.widget.Toast;

/**
 * <pre>
 * 千锋老罗-18.9_android Service进程间通信之Messenger一.mp4
 * 双向：从Activity到Service，然后service到Activity。
 * </pre>
 */
public class MainActivity extends Activity {
	Messenger messenger;
	Messenger mHandleResponseMessenger = new Messenger(new Handler() {

		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if (MessengerService.RESPONSE == msg.what) {
				Toast.makeText(MainActivity.this, msg.obj.toString(), Toast.LENGTH_SHORT).show();
			}
		};
	});

	ServiceConnection serviceConnection = new ServiceConnection() {
		@Override
		public void onServiceDisconnected(ComponentName name) {
			messenger = null;
			mBound = false;
		}

		@Override
		public void onServiceConnected(ComponentName name, IBinder binder) {
			messenger = new Messenger(binder);
			mBound = true;
		}
	};;
	boolean mBound = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		// 绑定Service
		bindService(new Intent(MainActivity.this, MessengerService.class), serviceConnection, Context.BIND_AUTO_CREATE);
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();

		if (mBound) {
			// 解绑Service
			unbindService(serviceConnection);
			mBound = false;
		}
	}

	public void sendMsgToService(View view) {

		Message message = Message.obtain();
		message.obj = "Activity -> Service";
		message.replyTo = mHandleResponseMessenger;
		message.what = MessengerService.INCOMING;

		try {
			// 使用Messenger发送Message
			messenger.send(message);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
