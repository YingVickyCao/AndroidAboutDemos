package demo.datastorary_database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
	// ���ݿ���
	public final static String DB_NAME = "info.db";
	// ���ݿ�汾
	public final static int DB_VER = 1;

	// ���ݿ��Ϊinfo�����ݱ�Ϊperson���ֶ�Ϊ����id������name���绰����tel
	// SQLite3֧�� NULL��INTEGER��REAL���������֣���TEXT(�ַ����ı�)��BLOB(�����ƶ���)��������

	/**
	 * 
	 * @param context
	 * @param name
	 *            ���ݿ���
	 * @param factory
	 *            �α꣬���ڵ�����ѯ��Ľ����
	 * @param version
	 *            ���ݿ�汾��
	 */
	public DBHelper(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	/**
	 * ���ݿ��һ�α�����ʱ�����ã���ֻ����һ��
	 * 
	 * @param db
	 *            ���ݿ����ʵ���������װ����ɾ�Ĳ����
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		// ��������䡣���ݱ�Ϊperson���ֶ�Ϊ����id������name���绰����tel
		String sql = "CREATE TABLE person(id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT, tel TEX)";
		db.execSQL(sql);
		System.out.println("-->onCreate func called");

	}

	/**
	 * ���ݿ�汾�ű��ʱ���Զ����á�
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		String sql = "ALTER TABLE person ADD phone TEXT";
		db.execSQL(sql);
		System.out.println("-->onUpgrade func called");
	}

	@Override
	public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		super.onDowngrade(db, oldVersion, newVersion);
		System.out.println("-->onDowngrade func called");
	}

	@Override
	public void onConfigure(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		super.onConfigure(db);
		System.out.println("-->onConfigure func called");
	}

	@Override
	public void onOpen(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		super.onOpen(db);
		System.out.println("-->onOpen func called");
	}

	@Override
	public synchronized void close() {
		// TODO Auto-generated method stub
		super.close();
		System.out.println("-->close func called");
	}

	@Override
	public SQLiteDatabase getReadableDatabase() {
		// TODO Auto-generated method stub
		System.out.println("-->getReadableDatabase func called");
		return super.getReadableDatabase();
	}

	@Override
	public SQLiteDatabase getWritableDatabase() {
		// TODO Auto-generated method stub
		System.out.println("-->getWritableDatabase func called");
		return super.getWritableDatabase();
	}

	@Override
	public void setWriteAheadLoggingEnabled(boolean enabled) {
		// TODO Auto-generated method stub
		super.setWriteAheadLoggingEnabled(enabled);
		System.out.println("-->setWriteAheadLoggingEnabled func called");
	}

	@Override
	public String getDatabaseName() {
		// TODO Auto-generated method stub
		System.out.println("-->getDatabaseName func called");
		return super.getDatabaseName();
	}
}
