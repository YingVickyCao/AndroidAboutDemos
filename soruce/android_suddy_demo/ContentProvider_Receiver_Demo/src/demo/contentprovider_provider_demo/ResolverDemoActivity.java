package demo.contentprovider_provider_demo;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class ResolverDemoActivity extends Activity {
	/** Called when the activity is first created. */
	private SimpleCursorAdapter adapter;
	private ListView listView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		listView = (ListView) this.findViewById(R.id.listView);
		ContentResolver contentResolver = getContentResolver();
		Uri selectUri = Uri.parse("content://cn.com.karl.personProvider/PersonProvider");
		Cursor cursor = contentResolver.query(selectUri, null, null, null, null);
		adapter = new SimpleCursorAdapter(this, R.layout.item, cursor, new String[] { "_id", "name", "age" }, new int[] { R.id.id, R.id.name, R.id.age });
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				ListView lView = (ListView) parent;
				Cursor data = (Cursor) lView.getItemAtPosition(position);
				int _id = data.getInt(data.getColumnIndex("_id"));
				Toast.makeText(ResolverDemoActivity.this, _id + "", 1).show();
			}
		});

		Button button = (Button) this.findViewById(R.id.insertbutton);
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				ContentResolver contentResolver = getContentResolver();
				Uri insertUri = Uri.parse("content://cn.com.karl.personProvider/person");
				ContentValues values = new ContentValues();
				values.put("name", "wangkuifeng");
				values.put("age", 23);
				Uri uri = contentResolver.insert(insertUri, values);
				Toast.makeText(ResolverDemoActivity.this, "ÃÌº”ÕÍ≥…", 1).show();
			}
		});
	}
}