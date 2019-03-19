package com.hades.example.android.data_storage.database;

import android.app.Fragment;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.support.annotation.Nullable;
import android.util.Log;

import com.hades.example.android.R;
import com.hades.example.android.lib.base.NoNeedPermissionActivity;
import com.hades.example.android.lib.mock.DummyContent;
import com.hades.example.android.lib.mock.DummyItem;
import com.hades.example.android.lib.utils.DummyContentFragment;

import java.util.ArrayList;
import java.util.List;

public class TestSQLiteActivity extends NoNeedPermissionActivity {
    private static final String TAG = TestSQLiteActivity.class.getSimpleName();

    private FeedSQLiteOpenHelper dbHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.data_storage_sqlite);

        dbHelper = new FeedSQLiteOpenHelper(this);

        findViewById(R.id.insertOne).setOnClickListener(v -> insertOne());
        findViewById(R.id.insertMultiple).setOnClickListener(v -> insertMultiple());
        findViewById(R.id.queryAll).setOnClickListener(v -> queryAll());
        findViewById(R.id.query).setOnClickListener(v -> query());
        findViewById(R.id.fuzzySearch).setOnClickListener(v -> fuzzySearch());
        findViewById(R.id.fuzzySearch2).setOnClickListener(v -> fuzzySearch2());

        findViewById(R.id.update).setOnClickListener(v -> update());
        findViewById(R.id.delete).setOnClickListener(v -> delete());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // PO:SQLiteOpenHelper.close()
        dbHelper.close();
    }

    private void insertOne() {
        new Thread(() -> {
            SQLiteDatabase db = getWritableDatabase();
            Log.d(TAG, "insertOne: " + TAG + "@" + hashCode() + ",SQLiteDatabase@" + db.hashCode());
            ContentValues rowInitColumnValues = new ContentValues();
            rowInitColumnValues.put(Table1ReaderContract.TableEntry.COL2, "A");
            rowInitColumnValues.put(Table1ReaderContract.TableEntry.COL3, "100");

            long insertedNewRowId = db.insert(Table1ReaderContract.TableEntry.TABLE_NAME, null, rowInitColumnValues);
            Log.d(TAG, "insertOne: newRowId=" + insertedNewRowId);
        }).start();
    }

    private void insertMultiple() {
        new Thread(() -> {
            SQLiteDatabase db = getWritableDatabase();
            insertMultiple(db, Table1ReaderContract.TableEntry.TABLE_NAME, DummyContent.ITEMS_1);
        }).start();
    }

    private void insertMultiple(SQLiteDatabase db, String tableName, List<DummyItem> list) {
        for (int i = 0; i < list.size(); i++) {
            db.insert(tableName, null, convertBean2ContentValues(list.get(i)));
        }
    }

    private ContentValues convertBean2ContentValues(DummyItem info) {
        ContentValues value = new ContentValues();
        value.put(Table1ReaderContract.TableEntry._ID, info.getId());
        value.put(Table1ReaderContract.TableEntry.COL2, info.getColo2());
        value.put(Table1ReaderContract.TableEntry.COL3, info.getCol3());
        return value;
    }

    private void queryAll() {
        new Thread(() -> {
            // PO: getReadableDatabase()/getWritableDatabase()
            Cursor cursor = getReadableDatabase().rawQuery(FeedSQLiteOpenHelper.SQL_RETRIEVE_ENTRIES, null);
            handleQueryResult(cursor);
        }).start();
    }

    // Search : colo2 content = D
    private void query() {
        new Thread(() -> {
            SQLiteDatabase db = getReadableDatabase();

            String[] returnedColumns = {BaseColumns._ID, Table1ReaderContract.TableEntry.COL2, Table1ReaderContract.TableEntry.COL3};

            String selection = Table1ReaderContract.TableEntry.COL2 + " = ?";
            String[] selectionArgs = {"D"};

            String orderBy = Table1ReaderContract.TableEntry.COL3 + " DESC";

            Cursor cursor = db.query(Table1ReaderContract.TableEntry.TABLE_NAME, returnedColumns, selection, selectionArgs, null, null, orderBy);

            handleQueryResult(cursor);
        }).start();
    }

    private void fuzzySearch() {
        SQLiteDatabase db = getReadableDatabase();
        String[] returnedColumns = {BaseColumns._ID, Table1ReaderContract.TableEntry.COL2, Table1ReaderContract.TableEntry.COL3};

        String selection = Table1ReaderContract.TableEntry.COL2 + " LIKE ?";
        String keyword = "i";
        String[] selectionArgs = {"%" + keyword + "%"};

        String orderBy = Table1ReaderContract.TableEntry.COL3 + " DESC";

        // PO: SQLiteDatabase Use query instead of rawQuery
        Cursor cursor = db.query(Table1ReaderContract.TableEntry.TABLE_NAME, returnedColumns, selection, selectionArgs, null, null, orderBy);

        handleQueryResult(cursor);
    }

    // colo3 任意位置含有1
    private void fuzzySearch2() {
        SQLiteDatabase db = getReadableDatabase();
        String keyword = "i";
        String sql = "SELECT " + BaseColumns._ID + "," + Table1ReaderContract.TableEntry.COL2 + "," + Table1ReaderContract.TableEntry.COL3
                + " FROM " + Table1ReaderContract.TableEntry.TABLE_NAME
                + " WHERE " + Table1ReaderContract.TableEntry.COL2
                + " LIKE '%" + keyword + "%'"
                + " ORDER BY " + Table1ReaderContract.TableEntry.COL3 + " DESC";


        Cursor cursor = db.rawQuery(sql, null);

        handleQueryResult(cursor);
    }

    // colo3 任意位置含有1
    private void update() {
        new Thread(() -> {
            SQLiteDatabase db = getWritableDatabase();

            String title = "MyNewTitle";
            ContentValues values = new ContentValues();
            values.put(Table1ReaderContract.TableEntry.COL2, title);

            String whereClause = Table1ReaderContract.TableEntry.COL3 + " LIKE ?";
            String keyword = "1";
            String[] whereArgs = {"%" + keyword + "%"};

            int count = db.update(Table1ReaderContract.TableEntry.TABLE_NAME, values, whereClause, whereArgs);

            Log.d(TAG, "update:count=" + count);
        }).start();
    }

    // col2 任意位置包含e
    private void delete() {
        new Thread(() -> {
            SQLiteDatabase db = getWritableDatabase();
            String whereClause = Table1ReaderContract.TableEntry.COL2 + " LIKE ?";
            String keyword = "e";
            String[] whereArgs = {"%" + keyword + "%"};

            int deletedRowNum = db.delete(Table1ReaderContract.TableEntry.TABLE_NAME, whereClause, whereArgs);
            Log.d(TAG, "delete: deletedRowNum=" + deletedRowNum);
        }).start();
    }

    private void handleQueryResult(Cursor cursor) {
        ArrayList<DummyItem> list = cursor2BeanList(cursor);
        // PO: Cursor.close
        if (null != cursor) {
            cursor.close();
        }

        runOnUiThread(() -> {
            // TODO: 2019/3/15 refactor
            Fragment fragment = getFragmentManager().findFragmentByTag(DummyContentFragment.TAG);
            if (null == fragment) {
                fragment = DummyContentFragment.getInstance(list);
                getFragmentManager().beginTransaction().add(R.id.fragmentRoot, fragment, DummyContentFragment.TAG).commit();
            } else {
                ((DummyContentFragment) fragment).setList(list);
            }
        });
    }

    protected ArrayList<DummyItem> cursor2BeanList(Cursor cursor) {
        ArrayList<DummyItem> list = new ArrayList<>();
        while (cursor.moveToNext()) {
//            DummyItem dummyItem = new DummyItem(cursor.getInt(0), cursor.getString(1), cursor.getInt(2));
            // cursor..getColumnIndexOrThrow
            DummyItem dummyItem = new DummyItem(cursor.getInt(cursor.getColumnIndex(Table1ReaderContract.TableEntry._ID))
                    , cursor.getString(cursor.getColumnIndex(Table1ReaderContract.TableEntry.COL2))
                    , cursor.getInt(cursor.getColumnIndex(Table1ReaderContract.TableEntry.COL3)));
            list.add(dummyItem);
        }
        return list;
    }

    private SQLiteDatabase getWritableDatabase() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        /**
         * TestSQLiteActivity@2527639,SQLiteDatabase@256491584
         * TestSQLiteActivity@127308036,SQLiteDatabase@6779339
         */
        Log.d(TAG, "getWritableDatabase: " + TAG + "@" + hashCode() + ",SQLiteDatabase@" + db.hashCode());
        return db;
    }

    private SQLiteDatabase getReadableDatabase() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Log.d(TAG, "getReadableDatabase : " + TAG + "@" + hashCode() + ",SQLiteDatabase@" + db.hashCode());
        return db;
    }
}