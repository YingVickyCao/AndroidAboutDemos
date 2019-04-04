package demo.broadcast_order;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class Three extends BroadcastReceiver {

	public Three() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		String name = intent.getStringExtra("name");
		System.out.println("--Three->>" + name);
	}

}
