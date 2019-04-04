package demo.broadcast_battery;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * 
 * <pre>
 * ע����������仯�Ĺ㲥
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
		// ��Ӧ��IntentFilter xml���á�
		if (intent.getAction().equals(Intent.ACTION_BATTERY_CHANGED)) {
			// ��ǰ����
			int state = intent.getIntExtra("level", 0);
			// �������̶ȣ��趨Ϊ100
			int scale = intent.getIntExtra("scale", 100);
			// ����͵�ǰ����
			Toast.makeText(context, "��ǰ��������" + (state * 100 / scale) + "%",Toast.LENGTH_SHORT).show();
		}

	}

}
