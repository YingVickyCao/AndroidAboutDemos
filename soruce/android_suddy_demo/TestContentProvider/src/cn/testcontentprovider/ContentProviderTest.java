package cn.testcontentprovider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

/**
 * ע������: ��AndroidManifest.xml��ע��ContentProviderʱ������
 * android:exported="true"��ʾ��������Ӧ�÷���. ����TestBaidu���Ӧ�òſ��Է��ʸô���ContentProvider
 */
public class ContentProviderTest extends ContentProvider {
	private DBOpenHelper dbOpenHelper;
	private UriMatcher URI_MATCHER;
	private static final int PERSONS = 0;
	private static final int PERSON = 1;

	@Override
	public boolean onCreate() {
		initUriMatcher();
		dbOpenHelper = new DBOpenHelper(getContext());
		return true;
	}

	// ��ʼ��UriMatcher
	private void initUriMatcher() {
		URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);
		// ��ʾ�������е�person,����PERSONSΪ���ض�Uri�ı�ʶ��
		URI_MATCHER.addURI("cn.bs.testcontentprovider", "person", PERSONS);
		// ��ʾ����ĳһ��person,����PERSONΪ���ض�Uri�ı�ʶ��
		URI_MATCHER.addURI("cn.bs.testcontentprovider", "person/#", PERSON);
	}

	/**
	 * �������: �������ֻ��һ�ֿ���:��һ�ű��в��� ���ؽ��Ϊ������¼��Ӧ��Uri ����db.insert()���ؽ��Ϊ������¼��Ӧ������ֵ
	 */
	@Override
	public Uri insert(Uri uri, ContentValues values) {
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
		switch (URI_MATCHER.match(uri)) {
		case PERSONS:
			long rowid = db.insert("person", "name,phone,salary", values);
			return ContentUris.withAppendedId(uri, rowid);
		default:
			throw new IllegalArgumentException("unknown uri" + uri.toString());
		}
	}

	/**
	 * ���²���: ���²��������ֿ���:����һ�ű���߸���ĳ������ �ڸ���ĳ������ʱԭ�������ڲ�ѯĳ������,����.
	 */
	@Override
	public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
		int updataNum = 0;
		switch (URI_MATCHER.match(uri)) {
		// ���±�
		case PERSONS:
			updataNum = db.update("person", values, selection, selectionArgs);
			break;
		// ����id����ĳ������
		case PERSON:
			long id = ContentUris.parseId(uri);
			String where = "personid=" + id;
			if (selection != null && !"".equals(selection.trim())) {
				where = selection + " and " + where;
			}
			updataNum = db.update("person", values, where, selectionArgs);
			break;
		default:
			throw new IllegalArgumentException("unknown uri" + uri.toString());
		}
		return updataNum;
	}

	/**
	 * ɾ������: ɾ�����������ֿ���:ɾ��һ�ű����ɾ��ĳ������ ��ɾ��ĳ������ʱԭ�������ڲ�ѯĳ������,����.
	 */
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
		int deletedNum = 0;
		switch (URI_MATCHER.match(uri)) {
		// ɾ����
		case PERSONS:
			deletedNum = db.delete("person", selection, selectionArgs);
			break;
		// ����idɾ��ĳ������
		case PERSON:
			long id = ContentUris.parseId(uri);
			String where = "personid=" + id;
			if (selection != null && !"".equals(selection.trim())) {
				where = selection + " and " + where;
			}
			deletedNum = db.delete("person", where, selectionArgs);
			break;
		default:
			throw new IllegalArgumentException("unknown uri" + uri.toString());
		}
		return deletedNum;
	}

	/**
	 * <pre>
	 * ��ѯ����: ��ѯ���������ֿ���:��ѯһ�ű���߲�ѯĳ������ 
	 * ע������: �ڲ�ѯĳ������ʱҪע��--��Ϊ�˴��ǰ���personid����ѯ
	 * ĳ������,����ͬʱ���ܻ�����������.
	 * ����: Ҫ��personidΪ2��nameΪxiaoming1 �����ڲ�ѯʱ��Ϊ����: 
	 * ��һ��: ������personid����where��ѯ���� 
	 * �ڶ���: �ж��Ƿ�����������(��name),�������� ��ƴ��where��ѯ����. ��ϸ�������.
	 * </pre>
	 */
	@Override
	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
		Cursor cursor;
		switch (URI_MATCHER.match(uri)) {
		// ��ѯ��
		case PERSONS:
			cursor = db.query("person", projection, selection, selectionArgs, null, null, sortOrder);
			break;
		// ����id��ѯĳ������
		case PERSON:
			// ��һ��:
			long id = ContentUris.parseId(uri);
			String where = "personid=" + id;
			// �ڶ���:
			if (selection != null && !"".equals(selection.trim())) {
				where = selection + " and " + where;
			}
			cursor = db.query("person", projection, where, selectionArgs, null, null, sortOrder);
			break;
		default:
			throw new IllegalArgumentException("unknown uri" + uri.toString());
		}
		return cursor;
	}

	/**
	 * ���ص�ǰUri����������ݵ�MIME����. �����Uri��Ӧ�����ݿ��ܰ���������¼,��ô����
	 * �ַ���Ӧ����"vnd.android.cursor.dir/"��ͷ �����Uri��Ӧ������ֻ����һ����¼,��ô����
	 * �ַ���Ӧ����"vnd.android.cursor.item/"��ͷ
	 */
	@Override
	public String getType(Uri uri) {
		switch (URI_MATCHER.match(uri)) {
		case PERSONS:
			return "vnd.android.cursor.dir/persons";
		case PERSON:
			return "vnd.android.cursor.item/person";
		default:
			throw new IllegalArgumentException("unknown uri" + uri.toString());
		}
	}
}