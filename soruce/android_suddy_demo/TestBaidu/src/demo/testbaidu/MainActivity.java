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
 * Demo����: Ӧ��A(TestBaidu)��������һ��Ӧ��(TestContentProvider) �е��Զ���ContentProvider,��: 1
 * �Զ���ContentProvider��ʹ�� 2 ����Ӧ�õ��ø�ContentProvider
 * 
 * ���Է���: 1 ���β���ContentProvider������ɾ��(ע���˳��) 2 ����Ӧ�ò�ѯ��ContentProvider������
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
				// ��ѯ��
				// queryFromContentProvider(-1);

				// ��ѯpersonid=2������
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
		System.out.println("��������:returnUri=" + returnUri);
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
			// ��ѯ��
			uri = Uri.parse("content://cn.bs.testcontentprovider/person");
		} else {
			// ����id��ѯĳ������
			uri = Uri.parse("content://cn.bs.testcontentprovider/person/" + String.valueOf(index));
		}

		// ��Ӧ�����:��ѯ��
		// Cursor cursor= mContentResolver.query(uri, null, null, null, null);

		// ��Ӧ�����:��ѯpersonid=2������
		// ע��:��Ϊname��varchar�ֶε�,����Ӧ��д��"name='xiaoming1'"
		// ��д��"name=xiaoming1"��ѯʱ�ᱨ��
		Cursor cursor = mContentResolver.query(uri, null, "name='xiaoming1'", null, null);

		while (cursor.moveToNext()) {
			int personid = cursor.getInt(cursor.getColumnIndex("personid"));
			String name = cursor.getString(cursor.getColumnIndex("name"));
			String phone = cursor.getString(cursor.getColumnIndex("phone"));
			int salary = cursor.getInt(cursor.getColumnIndex("salary"));
			System.out.println("��ѯ�õ�:personid=" + personid + ",name=" + name + ",phone=" + phone + ",salary=" + salary);
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