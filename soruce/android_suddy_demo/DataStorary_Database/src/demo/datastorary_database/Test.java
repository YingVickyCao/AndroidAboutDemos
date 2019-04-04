package demo.datastorary_database;

import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;

public class Test extends AndroidTestCase {
	public void test() {
		DBHelper dbManager = new DBHelper(getContext(), DBHelper.DB_NAME, null, DBHelper.DB_VER);
		// 使用下面两者之一，创建数据库。
		dbManager.getReadableDatabase();
		// dbManager.getWritableDatabase();
	}
	
	public void test0() {
		DBManager dbManager = new DBManager(getContext());
		// 使用下面两者之一，创建数据库。
		dbManager.getReadableDatabase();
		//dbManager.getWritableDatabase();
	}

	/**
	 * 使用insert语句逐条插入
	 */
	public void testInsert1() {
		DBHelper dbHelper = new DBHelper(getContext(), DBHelper.DB_NAME, null, DBHelper.DB_VER);
		SQLiteDatabase database = dbHelper.getWritableDatabase();
		String sql = "INSERT INTO person(id, name, tel) VALUES(1001, '张一', '18678555420')";
		database.execSQL(sql);
		sql = "INSERT INTO person(id, name, tel)VALUES(1002, '张二', '18678555421')";
	}

	/**
	 * 增
	 */
	public void testInsert2() {
		DBHelper dbHelper = new DBHelper(getContext(), DBHelper.DB_NAME, null, DBHelper.DB_VER);
		SQLiteDatabase database = dbHelper.getWritableDatabase();
		String sql = "INSERT INTO person(id, name, tel)VALUES(?, ?, ?)";
		Object[] bindArgs = { 1003, "张三", "18678555422" };
		database.execSQL(sql, bindArgs);
	}

	/**
	 * 增
	 */
	public void testInsert3() {
		DBHelper dbHelper = new DBHelper(getContext(), DBHelper.DB_NAME, null, DBHelper.DB_VER);
		SQLiteDatabase database = dbHelper.getWritableDatabase();
		String sql = "INSERT INTO person(id, name, tel)VALUES(?, ?, ?)";
		Object[] bindArgs = { 1002, "张二", "18678555421" };
		database.execSQL(sql, bindArgs);

		Object[] bindArgs2 = { 1004, "张四", "18678555423" };
		database.execSQL(sql, bindArgs2);

		Object[] bindArgs3 = { 1005, "张五", "18678555424" };
		database.execSQL(sql, bindArgs3);

		Object[] bindArgs4 = { 1006, "王一", "18678555425" };
		database.execSQL(sql, bindArgs4);

		Object[] bindArgs5 = { 1007, "王二", "18678555426" };
		database.execSQL(sql, bindArgs5);

		Object[] bindArgs6 = { 1008, "王三", "18678555427" };
		database.execSQL(sql, bindArgs6);
	}

	// 删除
	public void testDelete() {
		DBHelper dbHelper = new DBHelper(getContext(), DBHelper.DB_NAME, null, DBHelper.DB_VER);
		SQLiteDatabase database = dbHelper.getWritableDatabase();
		String sql = "DELETE FROM person WHERE id = ?";
		Object[] bindArgs = { 1005 };
		database.execSQL(sql, bindArgs);
	}

	/**
	 * 改
	 */
	public void testUpdate() {
		DBHelper dbHelper = new DBHelper(getContext(), DBHelper.DB_NAME, null, DBHelper.DB_VER);
		SQLiteDatabase database = dbHelper.getWritableDatabase();
		String sql = "UPDATE person SET name = ?, tel = ? WHERE id = ?";
		Object[] bindArgs = { "张三峰", "18678555554", 1001 };
		database.execSQL(sql, bindArgs);
	}

	/**
	 * 查-查询结果返回一条数据
	 */
	public void testQueryOne() {
		DBHelper dbHelper = new DBHelper(getContext(), DBHelper.DB_NAME, null, DBHelper.DB_VER);
		SQLiteDatabase database = dbHelper.getWritableDatabase();
		
		//database.rawQuery(sql, selectionArgs);

	}

	/**
	 * 查-查询结果返回多条数据
	 */
	public void testQueryMulti() {

	}
	
	/**
	 * 查-查询结果返回多条数据
	 */
	public void testQueryMulti2() {
	}

}
