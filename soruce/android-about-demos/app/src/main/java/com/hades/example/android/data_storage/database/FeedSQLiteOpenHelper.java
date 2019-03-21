package com.hades.example.android.data_storage.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.hades.example.android.lib.utils.LogHelper;

public class FeedSQLiteOpenHelper extends SQLiteOpenHelper {
    private static final String TAG = FeedSQLiteOpenHelper.class.getSimpleName();

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "FeedReader.db";

    private static final String SQL_CREATE_TABLE_TABLE1 =
            "CREATE TABLE IF NOT EXISTS " + Table1ReaderContract.TableEntry.TABLE_NAME + " (" +
                    Table1ReaderContract.TableEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    Table1ReaderContract.TableEntry.COL2 + " TEXT," +
                    Table1ReaderContract.TableEntry.COL3 + " INTEGER)";

    static final String SQL_RETRIEVE_TABLE_TABLE1 = "SELECT * FROM " + Table1ReaderContract.TableEntry.TABLE_NAME;
    private static final String SQL_DROP_TABLE_TABLE1 = "DROP TABLE IF EXISTS " + Table1ReaderContract.TableEntry.TABLE_NAME;

    // v1
    public static final String SQL_CREATE_TABLE_BOOK = "CREATE TABLE IF NOT EXISTS Book ("
            + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
            + "author TEXT, "
            + "price REAL, "
            + "pages INTEGER, "
            + "name TEXT)";

    // v2: add Category table
    public static final String SQL_CREATE_TABLE_CATEGORY = "CREATE TABLE IF NOT EXISTS Category ("
            + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
            + "category_name TEXT, "
            + "category_code INTEGER)";

    // v3: add link between table Book and Category
    public static final String SQL_ADD_LINK_BETWEEN_BOOK_AND_CATEGORY = "ALTER TABLE Book ADD COLUMN category_id INTEGER";

    public FeedSQLiteOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d(TAG, "FeedSQLiteOpenHelper() " + LogHelper.getThreadInfo());
    }

    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "onCreate: " + LogHelper.getThreadInfo());
        db.execSQL(SQL_CREATE_TABLE_TABLE1);
        db.execSQL(SQL_CREATE_TABLE_BOOK);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade_dev(db, oldVersion, newVersion);
    }

    public void onUpgrade_test(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is to simply to discard the data and start over
        Log.d(TAG, "onUpgrade: oldVersion=" + oldVersion + ",newVersion=" + newVersion + LogHelper.getThreadInfo());
        db.execSQL(SQL_DROP_TABLE_TABLE1);
        onCreate(db);
    }

    /**
     * <pre>
     *     switch中每一个case的都没有使用break，Why?
     *     这是为了保证在跨版本升级的时候，每一次的数据库修改都能被全部执行到。
     *     If v2 -> v3, case 2 is called.
     *     If v1 -> v3, case 1 and case 2 are called.
     *     使用这种方式来维护数据库的升级，不管版本怎样更新，都可以保证数据库的表结构是最新的，而且表中的数据也完全不会丢失了。
     * <pre/>
     */
    private void onUpgrade_dev(SQLiteDatabase db, int oldVersion, int newVersion) {
        switch (oldVersion) {
            case 1:
                db.execSQL(SQL_CREATE_TABLE_CATEGORY);

            case 2:
                db.execSQL(SQL_ADD_LINK_BETWEEN_BOOK_AND_CATEGORY);

            default:
        }
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG, "onDowngrade: oldVersion=" + oldVersion + ",newVersion=" + newVersion + LogHelper.getThreadInfo());
        onUpgrade(db, oldVersion, newVersion);
    }
}
