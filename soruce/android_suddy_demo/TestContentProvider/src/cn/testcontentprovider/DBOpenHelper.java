package cn.testcontentprovider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBOpenHelper extends SQLiteOpenHelper {
	public DBOpenHelper(Context context) {
		super(context, "contentprovidertest.db", null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("create table person(personid integer primary key autoincrement,name varchar(20),phone varchar(12),salary Integer(12))");
	}

	// �����ݿ�汾�ŷ����仯ʱ���ø÷���
	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		// db.execSQL("ALTER TABLE person ADD phone varchar(12) NULL");
		// db.execSQL("ALTER TABLE person ADD salary Integer NULL");
	}
}
