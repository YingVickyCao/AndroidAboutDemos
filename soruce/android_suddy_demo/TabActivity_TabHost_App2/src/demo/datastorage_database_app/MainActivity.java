package demo.datastorage_database_app;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
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
 * ����ʱ���л�����ͬ�Ľ��档
 * ����ʵ��ԭ��ͨ������dispatchTouchEvent�л����¼���
 * </pre>
 * 
 */
public class MainActivity extends TabActivity implements OnTabChangeListener {

	private static final int SWIPE_MIN_DISTANCE = 120;
	private static final int SWIPE_MAX_OFF_PATH = 250;
	private static final int SWIPE_THRESHOLD_VELOCITY = 200;
	private GestureDetector gestureDetector;
	View.OnTouchListener gestureListener;
	int currentView = 0;
	private static int maxTabIndex = 2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		gestureDetector = new GestureDetector(new TabHostTouch());

		/*
		 * gestureListener = new View.OnTouchListener() { public boolean
		 * onTouch(View v, MotionEvent event) { if
		 * (gestureDetector.onTouchEvent(event)) { return true; } return false;
		 * } };
		 */
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
		((TextView) view2.findViewById(R.id.tv_navi_content)).setText("ͳ��");
		countTabSpec.setIndicator(view2);
		countTabSpec.setContent(new Intent(MainActivity.this, CountActivity.class));
		tabHost.addTab(countTabSpec);

		tabHost.setOnTabChangedListener(this);
	}

	/**
	 * <pre>
	 * ����ʵ��ԭ��ͨ������dispatchTouchEvent�л����¼���
	 * dispatchTouchEvent�ڽ��յ��û�����ʱ��֮�󱻴�����	������dispatchTouchEvent�л�ȡ��ǰ�û��¼���
	 * ����ж��ǻ�������ִ���л�tab��������ֹ��ǰ�����¼�������ִ���������̡�
	 * </pre>
	 */
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		// TODO Auto-generated method stub

		// �ж���������ǻ������������¼���
		if (gestureDetector.onTouchEvent(ev)) {
			ev.setAction(MotionEvent.ACTION_CANCEL);
		}
		return super.dispatchTouchEvent(ev);
	}

	// ʵ�����һ����պ�ҳ��Ҳ�л���Ч������
	class TabHostTouch extends SimpleOnGestureListener {
		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
			TabHost tabHost = getTabHost();
			try {
				if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH)
					return false;
				// right to left swipe
				if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
					Log.i("test", "right");
					if (currentView == maxTabIndex) {
						currentView = 0;
					} else {
						currentView++;
					}
					tabHost.setCurrentTab(currentView);
				} else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
					Log.i("test", "left");
					if (currentView == 0) {
						currentView = maxTabIndex;
					} else {
						currentView--;
					}
					tabHost.setCurrentTab(currentView);
				}
			} catch (Exception e) {
			}
			return false;
		}
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

	}

}
