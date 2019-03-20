package com.hades.example.android.data_storage.database.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.hades.example.android.data_storage.database.common.DBConfig;
import com.hades.example.android.data_storage.database.tables.ContactInfoTable;

public class CustomSQLiteOpenHelper extends SQLiteOpenHelper {
    private static final String TAG = CustomSQLiteOpenHelper.class.getSimpleName();

    // PO: Context cached by global DB Manager must = Application Context
    private Context mContext = null;

    public CustomSQLiteOpenHelper(Context context) {
        super(context, DBConfig.DATABASE_FILE, null, DBConfig.DB_VER);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG, "CreateDB :");
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
