package demo.contentprovider_net_demo;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class PersonProvider extends ContentProvider {
	private DBOpenHelper dbOpenHelper;
	private static final UriMatcher MATCHER = new UriMatcher(UriMatcher.NO_MATCH);
	private static final int PERSONS = 1;
	private static final int PERSON = 2;
	static {
		MATCHER.addURI("cn.com.karl.personProvider", "person", PERSONS);
		MATCHER.addURI("cn.com.karl.personProvider", "person/#", PERSON);
	}

	@Override
	public boolean onCreate() {
		// TODO Auto-generated method stub
		this.dbOpenHelper = new DBOpenHelper(this.getContext());
		return false;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
		// TODO Auto-generated method stub
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
		switch (MATCHER.match(uri)) {
		case PERSONS:
			return db.query("person", projection, selection, selectionArgs, null, null, sortOrder);

		case PERSON:
			long id = ContentUris.parseId(uri);
			String where = "_id=" + id;
			if (selection != null && !"".equals(selection)) {
				where = selection + " and " + where;
			}
			return db.query("person", projection, where, selectionArgs, null, null, sortOrder);

		default:
			throw new IllegalArgumentException("Unkwon Uri:" + uri.toString());
		}
	}

	// �������ݵ�MIME���͡�
	@Override
	public String getType(Uri uri) {
		// TODO Auto-generated method stub
		switch (MATCHER.match(uri)) {
		case PERSONS:
			return "vnd.android.cursor.dir/person";

		case PERSON:
			return "vnd.android.cursor.item/person";

		default:
			throw new IllegalArgumentException("Unkwon Uri:" + uri.toString());
		}
	}

	// ����person���е����м�¼ /person
	// ����person����ָ��id�ļ�¼ /person/10
	@Override
	public Uri insert(Uri uri, ContentValues values) {
		// TODO Auto-generated method stub
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
		switch (MATCHER.match(uri)) {
		case PERSONS:
			// �ر�˵һ�µڶ��������ǵ�name�ֶ�Ϊ��ʱ�����Զ�����һ��NULL��
			long rowid = db.insert("person", "name", values);
			Uri insertUri = ContentUris.withAppendedId(uri, rowid);// �õ�����������¼��Uri
			this.getContext().getContentResolver().notifyChange(uri, null);
			return insertUri;

		default:
			throw new IllegalArgumentException("Unkwon Uri:" + uri.toString());
		}
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
		int count = 0;
		switch (MATCHER.match(uri)) {
		case PERSONS:
			count = db.delete("person", selection, selectionArgs);
			return count;

		case PERSON:
			long id = ContentUris.parseId(uri);
			String where = "_id=" + id;
			if (selection != null && !"".equals(selection)) {
				where = selection + " and " + where;
			}
			count = db.delete("person", where, selectionArgs);
			return count;

		default:
			throw new IllegalArgumentException("Unkwon Uri:" + uri.toString());
		}
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
		int count = 0;
		switch (MATCHER.match(uri)) {
		case PERSONS:
			count = db.update("person", values, selection, selectionArgs);
			return count;
		case PERSON:
			long id = ContentUris.parseId(uri);
			String where = "_id=" + id;
			if (selection != null && !"".equals(selection)) {
				where = selection + " and " + where;
			}
			count = db.update("person", values, where, selectionArgs);
			return count;
		default:
			throw new IllegalArgumentException("Unkwon Uri:" + uri.toString());
		}
	}

}
