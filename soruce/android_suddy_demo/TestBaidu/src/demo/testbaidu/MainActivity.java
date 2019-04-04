package demo.testbaidu;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * Demo描述: 应用A(TestBaidu)调用另外一个应用(TestContentProvider) 中的自定义ContentProvider,即: 1
 * 自定义ContentProvider的使用 2 其它应用调用该ContentProvider
 * 
 * 测试方法: 1 依次测试ContentProvider的增查删改(注意该顺序) 2 其它应用查询该ContentProvider的数据
 * 
 */
public class MainActivity extends Activity {
	private Button mAddButton;
	private Button mDeleteButton;
	private Button mUpdateButton;
	private Button mQueryButton;
	private Button mTypeButton;
	private ContentResolver mContentResolver;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
	}

	private void init() {
		mContentResolver = this.getContentResolver();

		mAddButton = (Button) findViewById(R.id.addButton);
		mAddButton.setOnClickListener(new ClickListenerImpl());

		mDeleteButton = (Button) findViewById(R.id.deleteButton);
		mDeleteButton.setOnClickListener(new ClickListenerImpl());

		mUpdateButton = (Button) findViewById(R.id.updateButton);
		mUpdateButton.setOnClickListener(new ClickListenerImpl());

		mQueryButton = (Button) findViewById(R.id.queryButton);
		mQueryButton.setOnClickListener(new ClickListenerImpl());

		mTypeButton = (Button) findViewById(R.id.typeButton);
		mTypeButton.setOnClickListener(new ClickListenerImpl());

	}

	private class ClickListenerImpl implements OnClickListener {
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.addButton:
				Person person = null;
				for (int i = 0; i < 5; i++) {
					person = new Person("xiaoming" + i, "9527" + i, (8888 + i));
					testInsert(person);
				}
				break;
			case R.id.deleteButton:
				testDelete(1);
				break;
			case R.id.updateButton:
				testUpdate(3);
				break;
			case R.id.queryButton:
				// 查询表
				// queryFromContentProvider(-1);

				// 查询personid=2的数据
				testQuery(2);
				break;
			case R.id.typeButton:
				testType();
				break;
			default:
				break;
			}

		}

	}

	private void testInsert(Person person) {
		ContentValues contentValues = new ContentValues();
		contentValues.put("name", person.getName());
		contentValues.put("phone", person.getPhone());
		contentValues.put("salary", person.getSalary());
		Uri insertUri = Uri.parse("content://cn.bs.testcontentprovider/person");
		Uri returnUri = mContentResolver.insert(insertUri, contentValues);
		System.out.println("新增数据:returnUri=" + returnUri);
	}

	private void testDelete(int index) {
		Uri uri = Uri.parse("content://cn.bs.testcontentprovider/person/" + String.valueOf(index));
		mContentResolver.delete(uri, null, null);
	}

	private void testUpdate(int index) {
		Uri uri = Uri.parse("content://cn.bs.testcontentprovider/person/" + String.valueOf(index));
		ContentValues values = new ContentValues();
		values.put("name", "hanmeimei");
		values.put("phone", "1234");
		values.put("salary", 333);
		mContentResolver.update(uri, values, null, null);
	}

	private void testQuery(int index) {
		Uri uri = null;
		if (index <= 0) {
			// 查询表
			uri = Uri.parse("content://cn.bs.testcontentprovider/person");
		} else {
			// 按照id查询某条数据
			uri = Uri.parse("content://cn.bs.testcontentprovider/person/" + String.valueOf(index));
		}

		// 对应上面的:查询表
		// Cursor cursor= mContentResolver.query(uri, null, null, null, null);

		// 对应上面的:查询personid=2的数据
		// 注意:因为name是varchar字段的,所以应该写作"name='xiaoming1'"
		// 若写成"name=xiaoming1"查询时会报错
		Cursor cursor = mContentResolver.query(uri, null, "name='xiaoming1'", null, null);

		while (cursor.moveToNext()) {
			int personid = cursor.getInt(cursor.getColumnIndex("personid"));
			String name = cursor.getString(cursor.getColumnIndex("name"));
			String phone = cursor.getString(cursor.getColumnIndex("phone"));
			int salary = cursor.getInt(cursor.getColumnIndex("salary"));
			System.out.println("查询得到:personid=" + personid + ",name=" + name + ",phone=" + phone + ",salary=" + salary);
		}
		cursor.close();
	}

	private void testType() {
		Uri dirUri = Uri.parse("content://cn.bs.testcontentprovider/person");
		String dirType = mContentResolver.getType(dirUri);
		System.out.println("dirType:" + dirType);

		Uri itemUri = Uri.parse("content://cn.bs.testcontentprovider/person/3");
		String itemType = mContentResolver.getType(itemUri);
		System.out.println("itemType:" + itemType);
	}
}