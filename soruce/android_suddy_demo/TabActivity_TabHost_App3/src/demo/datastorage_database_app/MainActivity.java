package demo.datastorage_database_app;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

/**
 * <pre>
 * ʹ��MainActivity����HomeActivity��CountActivity��
 * ��������һ����ǩ���л�����ͬ�Ľ��档
 * </pre>
 * 
 */
public class MainActivity extends TabActivity implements OnTabChangeListener {
	private String preTab;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		TabHost tabHost = getTabHost();
		tabHost.setup();

		// LayoutInflater.from(this).inflate(R.layout.activity_home,
		// tabHost.getTabContentView(), true);

		// ������ҳ����
		TabSpec homeTabSpec = tabHost.newTabSpec("home");
		// homeTabSpec.setIndicator("��ҳ",
		// getResources().getDrawable(R.drawable.gjj_tabitem_news_pressed));
		View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.navigation_item, null);
		((ImageView) view.findViewById(R.id.iv_navi_icon)).setImageDrawable(getResources().getDrawable(R.drawable.gjj_tabitem_news_pressed));
		((TextView) view.findViewById(R.id.tv_navi_content)).setText("��ҳ");

		homeTabSpec.setIndicator(view);
		homeTabSpec.setContent(new Intent(MainActivity.this, HomeActivity.class));
		tabHost.addTab(homeTabSpec);

		// ����ͳ�ƽ���
		TabSpec countTabSpec = tabHost.newTabSpec("count");
		// countTabSpec.setIndicator("ͳ��",
		// getResources().getDrawable(R.drawable.gjj_tabitem_tool_pressed));
		View view2 = LayoutInflater.from(MainActivity.this).inflate(R.layout.navigation_item, null);
		((ImageView) view2.findViewById(R.id.iv_navi_icon)).setImageDrawable(getResources().getDrawable(R.drawable.gjj_tabitem_tool_pressed));
		((TextView) view2.findViewById(R.id.tv_navi_content)).setVisibility(View.INVISIBLE);
		countTabSpec.setIndicator(view2);
		countTabSpec.setContent(new Intent(MainActivity.this, CountActivity.class));
		tabHost.addTab(countTabSpec);

		// �������ý���
		TabSpec settingTabSpec = tabHost.newTabSpec("setting");
		View view3 = LayoutInflater.from(MainActivity.this).inflate(R.layout.navigation_item, null);
		((ImageView) view3.findViewById(R.id.iv_navi_icon)).setImageDrawable(getResources().getDrawable(R.drawable.gjj_tabitem_account_pressed));
		((TextView) view3.findViewById(R.id.tv_navi_content)).setText("����");
		settingTabSpec.setIndicator(view3);
		settingTabSpec.setContent(new Intent(MainActivity.this, SettingActivity.class));
		tabHost.addTab(settingTabSpec);

		tabHost.setOnTabChangedListener(this);
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

	@Override
	public void onTabChanged(String tabId) {
		// TODO Auto-generated method stub
		if ("count".equals(tabId)) {
			TabHost tabs = getTabHost();
			tabs.setCurrentTabByTag(preTab);
			Intent intent = new Intent(MainActivity.this, SettingActivity.class);
			startActivity(intent);
		} else {
			preTab = tabId;
		}

	}

}
