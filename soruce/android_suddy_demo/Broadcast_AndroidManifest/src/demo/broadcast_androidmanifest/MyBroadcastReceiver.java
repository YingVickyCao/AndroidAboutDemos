package demo.broadcast_androidmanifest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * <pre>
 * ����Broadcase������
 * </pre>
 * 
 * @author caoying
 * 
 */
public class MyBroadcastReceiver extends BroadcastReceiver {

	public MyBroadcastReceiver() {

	}

	// ���չ㲥
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		Toast.makeText(context, intent.getStringExtra("content"), Toast.LENGTH_SHORT).show();
	}

}
