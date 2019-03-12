package com.hades.example.android.android_about_demos.temp.sundy.processthread;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.hades.example.android.android_about_demos.temp.sundy.async.CommonConstants;

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
