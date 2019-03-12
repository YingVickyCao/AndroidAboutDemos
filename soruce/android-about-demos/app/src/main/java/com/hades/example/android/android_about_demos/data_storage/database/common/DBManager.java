package com.hades.example.android.android_about_demos.data_storage.database.common;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.hades.example.android.android_about_demos.data_storage.database.helper.CustomSQLiteOpenHelper;


/**
 * DB管理类，主要为创建表及DB升级
 */
public class DBManager implements DBConfig {
    private static final String TAG = "DBManager";

    private static SQLiteDatabase mDB = null;
    private static SQLiteDatabase mDBReadable = null;

    private static CustomSQLiteOpenHelper mCustomSQLiteOpenHelper = null;


    public static void close() {
        if (mDB != null) {
            try {
                mDB.close();
            } catch (Exception e) {
            }
            mDB = null;
            Log.i(TAG, "[DBManager]close()");
        }
        if (mDBReadable != null) {
            try {
                mDBReadable.close();
            } catch (Exception e) {
            }
            mDBReadable = null;
            Log.i(TAG, "[DBManager] mDBReadable close()");
        }
    }

    private static CustomSQLiteOpenHelper getDatabaseHelper(Context AppContex) {
        if (mCustomSQLiteOpenHelper == null) {
            mCustomSQLiteOpenHelper = new CustomSQLiteOpenHelper(AppContex);
        }
        return mCustomSQLiteOpenHelper;
    }

    /**
     * @param AppContext 这里的Context一定要用ApplicationContext 初始化DB
     * @return
     */
    public static synchronized void InitDB(Context AppContext) {
        // GLog.i(TAG, "getDB");
        // 暂时在启动初始化，如果初始化时间较长，在第一次使用初使化也是一种考虑方法
        getWriteDB(AppContext);
        getReadDB(AppContext);
    }

    /**
     * 获取写DB
     */
    public static synchronized SQLiteDatabase getWriteDB(Context AppContext) {
        // GLog.i(TAG, "getDB");
        if (mDB == null || !mDB.isOpen()) {
            mDB = getDatabaseHelper(AppContext).getWritableDatabase();
//            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.HONEYCOMB) {
//               // mDB.enableWriteAheaGLogging();
//                GLog.i(TAG, "[DBManager]getDB() enableWriteAheaGLogging");
//            }
            Log.i(TAG, "[DBManager]getWriteDB()");
        }
        return mDB;
    }

    /**
     * 获取只读DBHelper
     */
    public static synchronized SQLiteDatabase getReadDB(Context AppContext) {
        // GLog.i(TAG, "getDB");
        if (mDBReadable == null || !mDBReadable.isOpen()) {
            mDBReadable = getDatabaseHelper(AppContext).getReadableDatabase();

            Log.i(TAG, "[DBManager]getReadDB()");
        }
        return mDBReadable;
    }

}

