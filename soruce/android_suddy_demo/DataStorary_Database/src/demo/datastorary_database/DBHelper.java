package demo.datastorary_database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
	// 数据库名
	public final static String DB_NAME = "info.db";
	// 数据库版本
	public final static int DB_VER = 1;

	// 数据库库为info，数据表为person，字段为主键id，姓名name，电话号码tel
	// SQLite3支持 NULL、INTEGER、REAL（浮点数字）、TEXT(字符串文本)和BLOB(二进制对象)数据类型

	/**
	 * 
	 * @param context
	 * @param name
	 *            数据库名
	 * @param factory
	 *            游标，用于迭代查询后的结果集
	 * @param version
	 *            数据库版本号
	 */
	public DBHelper(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 数据库第一次被创建时被调用，即只调用一次
	 * 
	 * @param db
	 *            数据库操作实例，该类封装了增删改差操作
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		// 创建表语句。数据表为person，字段为主键id，姓名name，电话号码tel
		String sql = "CREATE TABLE person(id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT, tel TEX)";
		db.execSQL(sql);
		System.out.println("-->onCreate func called");

	}

	/**
	 * 数据库版本号变更时，自动调用。
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
