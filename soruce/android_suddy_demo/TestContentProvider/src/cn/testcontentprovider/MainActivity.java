package cn.testcontentprovider;

import android.app.ListActivity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends ListActivity {
	SimpleCursorAdapter simpleCursorAdapter;
	private String[] from = null;
	private int[] to = null;
	Cursor cursor;
	ListView listView;
	private int FLAG = 100;
	ListAdapter listAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Uri uri = Uri.parse("content://cn.bs.testcontentprovider/person");

		/*from = new String[] { PhoneLookup.DISPLAY_NAME, PhoneLookup.PHOTO_ID };
		to = new int[] { R.id.tv_name, R.id.tv_phone };
*/
		listView = (ListView) this.findViewById(android.R.id.list);
		cursor = getContentResolver().query(uri, null, null, null, null);

		if (null != cursor) {
			startManagingCursor(cursor);
		}

		// 建立数据源

		// 建立适配器
		// 关联数据源和适配器
		listAdapter = new SimpleCursorAdapter(MainActivity.this, R.layout.item_contact, cursor, from, to, FLAG);
		// 关联适配器到UI组件
		listView.setAdapter(listAdapter);

		stopManagingCursor(cursor);
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
