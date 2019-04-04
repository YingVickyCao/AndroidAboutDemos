package com.demo.javaforandroid;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;
import android.util.Log;

/**
 * <pre>
 * Copyright (C) 2014 YUNDA Ltd 
 * 
 * Set the WLAN or MONET network. 
 * 
 * Created by Cao Ying on 2015/4/1.
 * </pre>
 */
public class NetworkSetting {
	public static boolean isSIMNOTExist(Context context) {
		TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		if (TelephonyManager.SIM_STATE_ABSENT == tm.getSimState()) {
			return true;
		}
		return false;
	}

	/**
	 * <pre>
	 * Set MONET network enable.
	 * Created by Cao Ying on 2015/4/1.
	 * 
	 * @param 	context	...
	 * 
	 * @return	true on success, false on failure.
	 * 
	 * </pre>
	 */
	public static boolean setMONETEnable(Context context) {
		ConnectivityManager conMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

		// ConnectivityManager类
		Class<?> conMgrClass = null;
		// ConnectivityManager类中的字段
		Field iConMgrField = null;
		// IConnectivityManager类的引用
		Object iConMgr = null;
		// IConnectivityManager类
		Class<?> iConMgrClass = null;
		// setMobileDataEnabled方法F
		Method setMobileDataEnabledMethod = null;

		try {
			// 取得ConnectivityManager类
			conMgrClass = Class.forName(conMgr.getClass().getName());
			// 取得ConnectivityManager类中的对象mService
			iConMgrField = conMgrClass.getDeclaredField("mService");
			// 设置mService可访问
			iConMgrField.setAccessible(true);
			// 取得mService的实例化类IConnectivityManager
			iConMgr = iConMgrField.get(conMgr);
			// 取得IConnectivityManager类
			iConMgrClass = Class.forName(iConMgr.getClass().getName());
			// 取得IConnectivityManager类中的setMobileDataEnabled(boolean)方法
			setMobileDataEnabledMethod = iConMgrClass.getDeclaredMethod("setMobileDataEnabled", Boolean.TYPE);
			// 设置setMobileDataEnabled方法可访问
			setMobileDataEnabledMethod.setAccessible(true);
			// 调用setMobileDataEnabled方法
			setMobileDataEnabledMethod.invoke(iConMgr, true);

			Log.e("NLOG", "NetworkSetting->" + "setMONETDisable:" + "open MONET successfully");
			return true;
		} catch (Exception ex) {
			Log.e("NLOG", "NetworkSetting->" + "setMONETEnable:" + "ex=" + ex.getMessage());
			return false;
		}
	}

	/**
	 * <pre>
	 * Set MONET network disable.
	 * Created by Cao Ying on 2015/4/1.
	 * 
	 * @param 	context	...
	 * 
	 * @return	true on success, false on failure.
	 * 
	 * </pre>
	 */
	public static boolean setMONETDisable(Context context) {
		ConnectivityManager conMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

		// ConnectivityManager类
		Class<?> conMgrClass = null;
		// ConnectivityManager类中的字段
		Field iConMgrField = null;
		// IConnectivityManager类的引用
		Object iConMgr = null;
		// IConnectivityManager类
		Class<?> iConMgrClass = null;
		// setMobileDataEnabled方法
		Method setMobileDataEnabledMethod = null;

		try {
			// 取得ConnectivityManager类
			conMgrClass = Class.forName(conMgr.getClass().getName());
			// 取得ConnectivityManager类中的对象mService
			iConMgrField = conMgrClass.getDeclaredField("mService");
			// 设置mService可访问
			iConMgrField.setAccessible(true);
			// 取得mService的实例化类IConnectivityManager
			iConMgr = iConMgrField.get(conMgr);
			// 取得IConnectivityManager类
			iConMgrClass = Class.forName(iConMgr.getClass().getName());
			// 取得IConnectivityManager类中的setMobileDataEnabled(boolean)方法
			setMobileDataEnabledMethod = iConMgrClass.getDeclaredMethod("setMobileDataEnabled", Boolean.TYPE);
			// 设置setMobileDataEnabled方法可访问
			setMobileDataEnabledMethod.setAccessible(true);
			// 调用setMobileDataEnabled方法
			setMobileDataEnabledMethod.invoke(iConMgr, false);

			Log.e("NLOG", "NetworkSetting->" + "setMONETDisable:" + "close MONET successfully");
			return true;
		} catch (Exception ex) {
			Log.e("NLOG", "NetworkSetting->" + "setMONETDisable:" + "ex=" + ex.getMessage());
			return false;
		}
	}

	/**
	 * <pre>
	 * Set WLAN network enable.
	 * Created by Cao Ying on 2015/4/1.
	 * 
	 * @param 	context	...
	 * 
	 * @return	true on success, false on failure.
	 * 
	 * </pre>
	 */
	public static boolean setWlanEnable(Context context) {
		WifiManager wm = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
		try {
			if (!wm.isWifiEnabled() && WifiManager.WIFI_STATE_ENABLING != wm.getWifiState()) {
				wm.setWifiEnabled(true);

				Log.e("NLOG", "NetworkSetting->" + "setWlanEnable:" + "open WLAN successfully");
			}
			return true;
		} catch (Exception ex) {
			Log.e("NLOG", "GSMSetting->" + "setWlanEnable:" + "ex=" + ex.getMessage());
			return false;
		}
	}

	/**
	 * <pre>
	 * Set WLAN network disable.
	 * Created by Cao Ying on 2015/4/1.
	 * 
	 * @param 	context	...
	 * 
	 * @return	true on success, false on failure.
	 * 
	 * </pre>
	 */
	public static boolean setWlanDisable(Context context) {
		WifiManager wm = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);

		try {
			if (wm.isWifiEnabled() || WifiManager.WIFI_STATE_ENABLING == wm.getWifiState()) {
				wm.setWifiEnabled(false);

				Log.e("NLOG", "NetworkSetting->" + "setWlanDisable:" + "close WLAN successfully");
			}
			return true;
		} catch (Exception ex) {
			Log.e("NLOG", "GSMSetting->" + "setWlanDisable:" + "ex=" + ex.getMessage());
			return false;
		}
	}

}
