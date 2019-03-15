package com.hades.example.android.app_component.cp.dict;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/*
    // data/data/com.xxx.xxx/databases.db
 */
public class MyDatabaseHelper extends SQLiteOpenHelper {
    private static final String TAG = MyDatabaseHelper.class.getSimpleName();

    private final static String DB_NAME = "used_test_content_provider.db";

    final String CREATE_TABLE_1_SQL = "create table table1(_id integer primary " + "key autoincrement , col2 ,colo3)";
    /**
     * FIXED_ERROR:
     * 2019-03-15 11:11:53.511 14465-14465/com.hades.example.android E/SQLiteLog: (1) table table1 already exists
     * Process: com.hades.example.android, PID: 14465
     * android.database.sqlite.SQLiteException: table table1 already exists (code 1): , while compiling: create table table1(_id integer primary key autoincrement , col2 ,colo3)
     * #################################################################
     * Error Code : 1 (SQLITE_ERROR)
     * Caused By : SQL(query) error or missing database.
     * (table table1 already exists (code 1): , while compiling: create table table1(_id integer primary key autoincrement , col2 ,colo3))
     * #################################################################
     */
//    final String CREATE_TABLE_2_SQL = "create table table1(_id integer primary " + "key autoincrement , col2 ,colo3)";
    final String CREATE_TABLE_2_SQL = "create table table2(_id integer primary " + "key autoincrement , col2 ,colo3)";
    final String CREATE_TABLE_3_SQL = "create table table3(_id integer primary " + "key autoincrement , col2 ,colo3)";

    public static final String TABLE_1_NAME = "table1";
    public static final String TABLE_2_NAME = "table2";
    public static final String TABLE_3_NAME = "table3";
    private static final String KEY_COLUMN_ID = "id";
    private static final String KEY_COLUMN_COL2 = "col2";
    private static final String KEY_COLUM_COL3 = "colo3";

    public final String INSERT_table1 = "insert into " + TABLE_1_NAME
            + "(" + KEY_COLUMN_ID
            + "," + KEY_COLUMN_COL2
            + "," + KEY_COLUM_COL3
            + ") values(?,?,?)";

    public final String INSERT_table2 = "insert into " + TABLE_2_NAME
            + "(" + KEY_COLUMN_ID
            + "," + KEY_COLUMN_COL2
            + "," + KEY_COLUM_COL3
            + ") values(?,?,?)";

    public final String INSERT_table3 = "insert into " + TABLE_3_NAME
            + "(" + KEY_COLUMN_ID
            + "," + KEY_COLUMN_COL2
            + "," + KEY_COLUM_COL3
            + ") values(?,?,?)";

    public MyDatabaseHelper(Context context, int version) {
        this(context, DB_NAME, version);
    }

    public MyDatabaseHelper(Context context, String name, int version) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // When first use DB, auto create table
        db.execSQL(CREATE_TABLE_1_SQL);
        db.execSQL(CREATE_TABLE_2_SQL);
        db.execSQL(CREATE_TABLE_3_SQL);

        insertTable(db, TABLE_1_NAME, buildTable1Data());
        insertTable(db, TABLE_2_NAME, buildTable2Data());
        insertTable(db, TABLE_3_NAME, buildTable3Data());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG, "onUpgrade: oldVersion=" + oldVersion + ",newVersion=" + newVersion);
    }

    private void insertTable(SQLiteDatabase db, String tableName, List<RowBean> list) {
        /**
         * FIXED_ERROR:java.lang.IllegalStateException: getDatabase called recursively
         *
         * https://blog.csdn.net/adayabetter/article/details/44516217
         */
//        SQLiteDatabase database = getWritableDatabase();
        for (int i = 0; i < list.size(); i++) {
            db.insert(tableName, null, convertBean2ContentValues(list.get(i)));
        }
    }

    private ContentValues convertBean2ContentValues(RowBean info) {
        ContentValues value = new ContentValues();
        value.put(KEY_COLUMN_ID, info.getId());
        value.put(KEY_COLUMN_COL2, info.getCol2());
        value.put(KEY_COLUM_COL3, info.getCol3());
        return value;
    }

    List<RowBean> buildTable1Data() {
        List<RowBean> list = new ArrayList<>();
        list.add(new RowBean(1, "City", 1));
        list.add(new RowBean(2, "China", 11));
        list.add(new RowBean(3, "D", 2));
        return list;
    }

    List<RowBean> buildTable2Data() {
        List<RowBean> list = new ArrayList<>();
        list.add(new RowBean(1, "ABC", 2));
        list.add(new RowBean(2, "hello", 204));
        list.add(new RowBean(3, "Book", 9));
        list.add(new RowBean(3, "OP", 15));
        return list;
    }

    List<RowBean> buildTable3Data() {
        List<RowBean> list = new ArrayList<>();
        list.add(new RowBean(1, "Agile", 1911));
        return list;
    }
}