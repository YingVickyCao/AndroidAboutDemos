package demo.broadcast_battery;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * 
 * <pre>
 * 注册监听电量变化的广播
 * </pre>
 * 
 * @author Administrator
 * 
 */
public class BatteryBroadcastReceiver extends BroadcastReceiver {
	Context context;

	public BatteryBroadcastReceiver(Context context) {
		this.context = context;
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		// 对应于IntentFilter xml配置。
		if (intent.getAction().equals(Intent.ACTION_BATTERY_CHANGED)) {
			// 当前电量
			int state = intent.getIntExtra("level", 0);
			// 最大电量刻度，设定为100
			int scale = intent.getIntExtra("scale", 100);
			// 计算和当前电量
			Toast.makeText(context, "当前电量等于" + (state * 100 / scale) + "%",Toast.LENGTH_SHORT).show();
		}

	}

}
