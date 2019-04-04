package com.example.c20_1_broadcast_orderedbroadcasts;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

/**
 * <pre>
 * ��������㲥ʾ����
 * ע�᷽ʽ��ʹ���嵥�ļ�receiver tag��
 * 
 * �㲥�Ľ������ȼ��趨Ϊ��2(10) -> 3(9) -> 1(8)
 * ��1������Intentָ��Ϊ3,���м�û����ֹ�㲥ʱʱ������3���յ��㲥��
 * Intent intent = new Intent(MainActivity.this, MyBroadcastReceiver3.class);
 * 06-25 08:49:13.435: I/System.out(8421): main,1--send sendOrderedBroadcast
 * 06-25 08:49:13.460: I/System.out(8421): main,1--MyBroadcastReceiver3--��ã�����һ���㲥ʾ��
 * 
 * ��2������Intentû��ָ���㲥�����м�û����ֹ�㲥ʱ���㲥���ȶ�˳�򴫵ݡ�
 * ��3������Intentû��ָ���㲥��ĳһ�������߸ı������ݣ����м�û����ֹ�㲥ʱ���㲥���ȶ�˳�򴫵ݣ��ı�����ݲ��ܴ������������ߡ�
 * Intent intent = new Intent();
 * 06-25 08:51:13.160: I/System.out(8596): main,1--send sendOrderedBroadcast
 * 06-25 08:51:13.180: I/System.out(8596): main,1--MyBroadcastReceiver2--��ã�����һ���㲥ʾ��
 * 06-25 08:51:13.180: I/System.out(8596): main,1--MyBroadcastReceiver2--���Ĺ㲥����
 * 06-25 08:51:13.185: I/System.out(8596): main,1--MyBroadcastReceiver3--��ã�����һ���㲥ʾ��
 * 06-25 08:51:13.185: I/System.out(8596): main,1--MyBroadcastReceiver1--��ã�����һ���㲥ʾ��
 * 
 * ��4������Intentû��ָ���㲥����3ʱ��ֹ�㲥ʱ���㲥���ȶ�˳�򴫵ݣ���1�����յ��㲥��
 * Intent intent = new Intent();
 * 3:abortBroadcast();
 * 06-25 08:52:01.635: I/System.out(8746): main,1--send sendOrderedBroadcast
 * 06-25 08:52:01.655: I/System.out(8746): main,1--MyBroadcastReceiver2--��ã�����һ���㲥ʾ��
 * 06-25 08:52:01.655: I/System.out(8746): main,1--MyBroadcastReceiver2--���Ĺ㲥����
 * 06-25 08:52:01.660: I/System.out(8746): main,1--MyBroadcastReceiver3--��ã�����һ���㲥ʾ��
 * 
 * ��5������Intentû��ָ���㲥���м�û����ֹ�㲥����ָ��1�����ȼ�����ʱ���κν����߶����ܽ��ܹ㲥��ԭ���������ȶ����ȼ��г�ͻ��
 * Intent intent = new Intent();
 * sendOrderedBroadcast(intent, "8");
 * 06-25 08:56:48.080: I/System.out(9141): main,1--send sendOrderedBroadcast
 * 
 * </pre>
 * 
 * @author caoying
 * 
 */
public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void sendBroadcast(View view) {
		// Intent intent = new Intent(MainActivity.this,
		// MyBroadcastReceiver3.class);
		Intent intent = new Intent();
		intent.putExtra("content", "��ã�����һ���㲥ʾ��");
		intent.setAction("test");
		// ���͹㲥
		// sendOrderedBroadcast(intent, null);
		sendOrderedBroadcast(intent, "8");
		System.out.println(Thread.currentThread().getName() + "," + Thread.currentThread().getId() + "--send sendOrderedBroadcast");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
