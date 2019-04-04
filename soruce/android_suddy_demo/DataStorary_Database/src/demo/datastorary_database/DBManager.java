package demo.datastorary_database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBManager extends SQLiteOpenHelper {
	private final static String DB_NAME = "mydb.db";
	private final static int DB_VER = 1;

	// 创建、打开或管理数据库
	public DBManager(Context context) {
		super(context, DB_NAME, null, DB_VER);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		// 创建表语句
		String sql = "create table person(pid integer primary key autoincrement,name varchar(64),address varchar(64))";
		db.execSQL(sql);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
