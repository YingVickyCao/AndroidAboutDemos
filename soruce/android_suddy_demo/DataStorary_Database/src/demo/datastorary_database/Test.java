package demo.datastorary_database;

import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;

public class Test extends AndroidTestCase {
	public void test() {
		DBHelper dbManager = new DBHelper(getContext(), DBHelper.DB_NAME, null, DBHelper.DB_VER);
		// ʹ����������֮һ���������ݿ⡣
		dbManager.getReadableDatabase();
		// dbManager.getWritableDatabase();
	}
	
	public void test0() {
		DBManager dbManager = new DBManager(getContext());
		// ʹ����������֮һ���������ݿ⡣
		dbManager.getReadableDatabase();
		//dbManager.getWritableDatabase();
	}

	/**
	 * ʹ��insert�����������
	 */
	public void testInsert1() {
		DBHelper dbHelper = new DBHelper(getContext(), DBHelper.DB_NAME, null, DBHelper.DB_VER);
		SQLiteDatabase database = dbHelper.getWritableDatabase();
		String sql = "INSERT INTO person(id, name, tel) VALUES(1001, '��һ', '18678555420')";
		database.execSQL(sql);
		sql = "INSERT INTO person(id, name, tel)VALUES(1002, '�Ŷ�', '18678555421')";
	}

	/**
	 * ��
	 */
	public void testInsert2() {
		DBHelper dbHelper = new DBHelper(getContext(), DBHelper.DB_NAME, null, DBHelper.DB_VER);
		SQLiteDatabase database = dbHelper.getWritableDatabase();
		String sql = "INSERT INTO person(id, name, tel)VALUES(?, ?, ?)";
		Object[] bindArgs = { 1003, "����", "18678555422" };
		database.execSQL(sql, bindArgs);
	}

	/**
	 * ��
	 */
	public void testInsert3() {
		DBHelper dbHelper = new DBHelper(getContext(), DBHelper.DB_NAME, null, DBHelper.DB_VER);
		SQLiteDatabase database = dbHelper.getWritableDatabase();
		String sql = "INSERT INTO person(id, name, tel)VALUES(?, ?, ?)";
		Object[] bindArgs = { 1002, "�Ŷ�", "18678555421" };
		database.execSQL(sql, bindArgs);

		Object[] bindArgs2 = { 1004, "����", "18678555423" };
		database.execSQL(sql, bindArgs2);

		Object[] bindArgs3 = { 1005, "����", "18678555424" };
		database.execSQL(sql, bindArgs3);

		Object[] bindArgs4 = { 1006, "��һ", "18678555425" };
		database.execSQL(sql, bindArgs4);

		Object[] bindArgs5 = { 1007, "����", "18678555426" };
		database.execSQL(sql, bindArgs5);

		Object[] bindArgs6 = { 1008, "����", "18678555427" };
		database.execSQL(sql, bindArgs6);
	}

	// ɾ��
	public void testDelete() {
		DBHelper dbHelper = new DBHelper(getContext(), DBHelper.DB_NAME, null, DBHelper.DB_VER);
		SQLiteDatabase database = dbHelper.getWritableDatabase();
		String sql = "DELETE FROM person WHERE id = ?";
		Object[] bindArgs = { 1005 };
		database.execSQL(sql, bindArgs);
	}

	/**
	 * ��
	 */
	public void testUpdate() {
		DBHelper dbHelper = new DBHelper(getContext(), DBHelper.DB_NAME, null, DBHelper.DB_VER);
		SQLiteDatabase database = dbHelper.getWritableDatabase();
		String sql = "UPDATE person SET name = ?, tel = ? WHERE id = ?";
		Object[] bindArgs = { "������", "18678555554", 1001 };
		database.execSQL(sql, bindArgs);
	}

	/**
	 * ��-��ѯ�������һ������
	 */
	public void testQueryOne() {
		DBHelper dbHelper = new DBHelper(getContext(), DBHelper.DB_NAME, null, DBHelper.DB_VER);
		SQLiteDatabase database = dbHelper.getWritableDatabase();
		
		//database.rawQuery(sql, selectionArgs);

	}

	/**
	 * ��-��ѯ������ض�������
	 */
	public void testQueryMulti() {

	}
	
	/**
	 * ��-��ѯ������ض�������
	 */
	public void testQueryMulti2() {
	}

}
