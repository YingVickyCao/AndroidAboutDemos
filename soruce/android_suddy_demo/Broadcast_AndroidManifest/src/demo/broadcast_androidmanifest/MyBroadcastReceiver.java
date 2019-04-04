package demo.broadcast_androidmanifest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * <pre>
 * 定义Broadcase接受者
 * </pre>
 * 
 * @author caoying
 * 
 */
public class MyBroadcastReceiver extends BroadcastReceiver {

	public MyBroadcastReceiver() {

	}

	// 接收广播
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		Toast.makeText(context, intent.getStringExtra("content"), Toast.LENGTH_SHORT).show();
	}

}
