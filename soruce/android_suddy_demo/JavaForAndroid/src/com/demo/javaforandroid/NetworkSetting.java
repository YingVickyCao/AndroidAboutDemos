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

		// ConnectivityManager��
		Class<?> conMgrClass = null;
		// ConnectivityManager���е��ֶ�
		Field iConMgrField = null;
		// IConnectivityManager�������
		Object iConMgr = null;
		// IConnectivityManager��
		Class<?> iConMgrClass = null;
		// setMobileDataEnabled����F
		Method setMobileDataEnabledMethod = null;

		try {
			// ȡ��ConnectivityManager��
			conMgrClass = Class.forName(conMgr.getClass().getName());
			// ȡ��ConnectivityManager���еĶ���mService
			iConMgrField = conMgrClass.getDeclaredField("mService");
			// ����mService�ɷ���
			iConMgrField.setAccessible(true);
			// ȡ��mService��ʵ������IConnectivityManager
			iConMgr = iConMgrField.get(conMgr);
			// ȡ��IConnectivityManager��
			iConMgrClass = Class.forName(iConMgr.getClass().getName());
			// ȡ��IConnectivityManager���е�setMobileDataEnabled(boolean)����
			setMobileDataEnabledMethod = iConMgrClass.getDeclaredMethod("setMobileDataEnabled", Boolean.TYPE);
			// ����setMobileDataEnabled�����ɷ���
			setMobileDataEnabledMethod.setAccessible(true);
			// ����setMobileDataEnabled����
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

		// ConnectivityManager��
		Class<?> conMgrClass = null;
		// ConnectivityManager���е��ֶ�
		Field iConMgrField = null;
		// IConnectivityManager�������
		Object iConMgr = null;
		// IConnectivityManager��
		Class<?> iConMgrClass = null;
		// setMobileDataEnabled����
		Method setMobileDataEnabledMethod = null;

		try {
			// ȡ��ConnectivityManager��
			conMgrClass = Class.forName(conMgr.getClass().getName());
			// ȡ��ConnectivityManager���еĶ���mService
			iConMgrField = conMgrClass.getDeclaredField("mService");
			// ����mService�ɷ���
			iConMgrField.setAccessible(true);
			// ȡ��mService��ʵ������IConnectivityManager
			iConMgr = iConMgrField.get(conMgr);
			// ȡ��IConnectivityManager��
			iConMgrClass = Class.forName(iConMgr.getClass().getName());
			// ȡ��IConnectivityManager���е�setMobileDataEnabled(boolean)����
			setMobileDataEnabledMethod = iConMgrClass.getDeclaredMethod("setMobileDataEnabled", Boolean.TYPE);
			// ����setMobileDataEnabled�����ɷ���
			setMobileDataEnabledMethod.setAccessible(true);
			// ����setMobileDataEnabled����
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
