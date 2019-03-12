package com.hades.example.android.temp.sundy.async;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class ProcessLifecycleBroadcastReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context arg0, Intent arg1) {
		// TODO Auto-generated method stub
		if (arg1.getAction().equals(Intent.CATEGORY_HOME))
		{
			Log.i(CommonConstants.LOGCAT_TAG_NAME, "go to HomeScreen") ;
		}
	}

}
