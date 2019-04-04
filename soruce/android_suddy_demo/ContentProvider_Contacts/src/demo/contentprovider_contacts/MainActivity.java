package demo.contentprovider_contacts;

import java.util.ArrayList;

import android.app.ListActivity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.PhoneLookup;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends ListActivity {
	private String[] from = null;
	private int[] to = null;
	Cursor cursor;
	ListView listView;
	private int FLAG = 100;
	ListAdapter listAdapter;

	// [content://com.android.contacts/contacts]
	private static final Uri CONTACTS_URI = ContactsContract.Contacts.CONTENT_URI;
	// [content://com.android.contacts/data/phones]
	private static final Uri PHONES_URI = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
	// [content://com.android.contacts/data/emails]
	private static final Uri EMAIL_URI = ContactsContract.CommonDataKinds.Email.CONTENT_URI;

	private static final String _ID = ContactsContract.Contacts._ID;
	private static final String DISPLAY_NAME = ContactsContract.Contacts.DISPLAY_NAME;
	private static final String HAS_PHONE_NUMBER = ContactsContract.Contacts.HAS_PHONE_NUMBER;
	private static final String CONTACT_ID = ContactsContract.Data.CONTACT_ID;

	private static final String PHONE_NUMBER = ContactsContract.CommonDataKinds.Phone.NUMBER;
	private static final String PHONE_TYPE = ContactsContract.CommonDataKinds.Phone.TYPE;
	private static final String EMAIL_DATA = ContactsContract.CommonDataKinds.Email.DATA;
	private static final String EMAIL_TYPE = ContactsContract.CommonDataKinds.Email.TYPE;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		way1();
	}

	private String getPhoneTypeNameById(int typeId) {
		switch (typeId) {
		case ContactsContract.CommonDataKinds.Phone.TYPE_HOME:
			return "home";
		case ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE:
			return "mobile";
		case ContactsContract.CommonDataKinds.Phone.TYPE_WORK:
			return "work";
		default:
			return "none";
		}
	}

	private String getEmailTypeNameById(int typeId) {
		switch (typeId) {
		case ContactsContract.CommonDataKinds.Email.TYPE_HOME:
			return "home";
		case ContactsContract.CommonDataKinds.Email.TYPE_WORK:
			return "work";
		case ContactsContract.CommonDataKinds.Email.TYPE_OTHER:
			return "other";
		default:
			return "none";
		}
	}

	public void way2() {

		ContentResolver resolver = getContentResolver();
		Cursor c = resolver.query(CONTACTS_URI, null, null, null, null);
		while (c.moveToNext()) {
			int _id = c.getInt(c.getColumnIndex(_ID));
			String displayName = c.getString(c.getColumnIndex(DISPLAY_NAME));

			ArrayList<String> phones = new ArrayList<String>();
			ArrayList<String> emails = new ArrayList<String>();

			String selection = CONTACT_ID + "=" + _id; // the 'where' clause

			// 获取手机号
			int hasPhoneNumber = c.getInt(c.getColumnIndex(HAS_PHONE_NUMBER));
			if (hasPhoneNumber > 0) {
				Cursor phc = resolver.query(PHONES_URI, null, selection, null, null);
				while (phc.moveToNext()) {
					String phoneNumber = phc.getString(phc.getColumnIndex(PHONE_NUMBER));
					int phoneType = phc.getInt(phc.getColumnIndex(PHONE_TYPE));
					phones.add(getPhoneTypeNameById(phoneType) + " : " + phoneNumber);
				}
				phc.close();
			}

			// 获取邮箱
			Cursor emc = resolver.query(EMAIL_URI, null, selection, null, null);
			while (emc.moveToNext()) {
				String emailData = emc.getString(emc.getColumnIndex(EMAIL_DATA));
				int emailType = emc.getInt(emc.getColumnIndex(EMAIL_TYPE));
				emails.add(getEmailTypeNameById(emailType) + " : " + emailData);
			}
			emc.close();

		}
		c.close();
	}

	public void way1() {
		from = new String[] { PhoneLookup.DISPLAY_NAME, PhoneLookup.PHOTO_ID };
		to = new int[] { R.id.tv_name, R.id.tv_phone };

		listView = (ListView) this.findViewById(android.R.id.list);
		cursor = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);

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
