package com.hades.example.android.data_storage.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.hades.example.android.lib.utils.LogHelper;

public class FeedSQLiteOpenHelper extends SQLiteOpenHelper {
    private static final String TAG = FeedSQLiteOpenHelper.class.getSimpleName();

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "FeedReader.db";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE IF NOT EXISTS " + Table1ReaderContract.TableEntry.TABLE_NAME + " (" +
                    Table1ReaderContract.TableEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    Table1ReaderContract.TableEntry.COL2 + " TEXT," +
                    Table1ReaderContract.TableEntry.COL3 + " INTEGER)";

    public static final String SQL_RETRIEVE_ENTRIES = "SELECT * FROM " + Table1ReaderContract.TableEntry.TABLE_NAME;
    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + Table1ReaderContract.TableEntry.TABLE_NAME;

    public FeedSQLiteOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d(TAG, "FeedSQLiteOpenHelper() " + LogHelper.getThreadInfo());
    }

    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "onCreate: " + LogHelper.getThreadInfo());
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is to simply to discard the data and start over
        Log.d(TAG, "onUpgrade: oldVersion=" + oldVersion + ",newVersion=" + newVersion + LogHelper.getThreadInfo());
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG, "onDowngrade: oldVersion=" + oldVersion + ",newVersion=" + newVersion + LogHelper.getThreadInfo());
        onUpgrade(db, oldVersion, newVersion);
    }
}
