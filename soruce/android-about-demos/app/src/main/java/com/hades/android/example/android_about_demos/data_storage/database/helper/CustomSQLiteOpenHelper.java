package com.hades.android.example.android_about_demos.data_storage.database.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.hades.android.example.android_about_demos.data_storage.database.common.DBConfig;
import com.hades.android.example.android_about_demos.data_storage.database.tables.ContactInfoTable;

public class CustomSQLiteOpenHelper extends SQLiteOpenHelper {
    private static final String TAG = CustomSQLiteOpenHelper.class.getSimpleName();

    // PO:Context must = ApplicationContext
    private Context mContext = null;

    public CustomSQLiteOpenHelper(Context context) {
        super(context, DBConfig.DATABASE_FILE, null, DBConfig.DB_VER);
        mContext = context;
    }

    // 数据库创建成功后回调的方法，后面的操作都是基于返回的db
    @Override
    public void onCreate(SQLiteDatabase db) {
        // 创建+读入歌曲数据库表
        Log.i(TAG, "CreateDB :");
        // 创建表,如果有新增字段必须进入CreateTable里面来加入新的字段
        createTable(db);
    }

    // 数据库版本变化后，会回调该方法。
    // 在应用开发中，如果有表的增删改 OR 字段的变化，通过版本号来区分。
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    // 数据库版本降级处理
    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            Log.i(TAG, "数据库降级 old:" + oldVersion + " newVersion:" + newVersion);

            //用来触发重新扫描
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
    }

    public void setContext(Context context) {
        mContext = context;
    }

    public Context getContext() {
        return mContext;
    }

    private void createTable(SQLiteDatabase db) {
        createContactInfoTable(db);
    }

    private void createContactInfoTable(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS " + ContactInfoTable.TABLE_NAME);
        db.execSQL(ContactInfoTable.TABLE_CREATE);
    }
}
