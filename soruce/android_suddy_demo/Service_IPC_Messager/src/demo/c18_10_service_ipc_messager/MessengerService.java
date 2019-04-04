package demo.c18_10_service_ipc_messager;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.widget.Toast;

public class MessengerService extends Service {
	public final static int INCOMING = 1000;
	public final static int RESPONSE = 2000;

	// 使用Messenger接受Activity发送来的Message，并交给Handler处理。
	Messenger mMessenger = new Messenger(new IncomingHandler());

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return mMessenger.getBinder();
	}

	class IncomingHandler extends Handler {
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			switch (msg.what) {
			case INCOMING:
				Toast.makeText(MessengerService.this, (CharSequence) msg.obj, Toast.LENGTH_SHORT).show();

				Message responseMsg = Message.obtain();
				responseMsg.what = RESPONSE;
				responseMsg.obj = "hello，service -> Activity";

				mMessenger = msg.replyTo;

				try {
					mMessenger.send(responseMsg);
				} catch (RemoteException ex) {
					// TODO: handle exception
				}
				break;
			default:

				super.handleMessage(msg);
				break;
			}
		}
	}

}
