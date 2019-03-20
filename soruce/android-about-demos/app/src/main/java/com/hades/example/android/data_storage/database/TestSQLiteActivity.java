package com.hades.example.android.data_storage.database;

import android.app.Fragment;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.hades.example.android.R;
import com.hades.example.android.lib.base.NoNeedPermissionActivity;
import com.hades.example.android.lib.mock.DummyContent;
import com.hades.example.android.lib.mock.DummyItem;
import com.hades.example.android.lib.utils.DateUtil;
import com.hades.example.android.lib.utils.DummyContentFragment;

import java.util.ArrayList;
import java.util.List;

public class TestSQLiteActivity extends NoNeedPermissionActivity {
    private static final String TAG = TestSQLiteActivity.class.getSimpleName();

    private FeedSQLiteOpenHelper dbHelper;
    private DateUtil mDateUtil = new DateUtil();

    private TextView mUsedTimeTv;
    private View mProgressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.data_storage_sqlite);
        initViews();
        showFragmentRoot();

        mUsedTimeTv = findViewById(R.id.usedTime);
        mProgressBar = findViewById(R.id.progressBar);

        dbHelper = new FeedSQLiteOpenHelper(this);

        findViewById(R.id.insertOne).setOnClickListener(v -> insertOne());
        findViewById(R.id.insertMultiple).setOnClickListener(v -> insertMultiple());
        findViewById(R.id.insertMultipleWithTransaction).setOnClickListener(v -> insertMultipleWithTransaction());

        findViewById(R.id.query).setOnClickListener(v -> query());
        findViewById(R.id.queryAll).setOnClickListener(v -> queryAll());
        findViewById(R.id.rawQueryAll).setOnClickListener(v -> rawQueryAll());

        findViewById(R.id.fuzzySearch).setOnClickListener(v -> fuzzySearch());
        findViewById(R.id.fuzzySearch2).setOnClickListener(v -> fuzzySearch2());

        findViewById(R.id.update).setOnClickListener(v -> update());
        findViewById(R.id.updateFuzzy).setOnClickListener(v -> updateFuzzy());

        findViewById(R.id.delete).setOnClickListener(v -> delete());
        findViewById(R.id.deleteAll).setOnClickListener(v -> deleteAll());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // PO:SQLiteOpenHelper.close()
        dbHelper.close();
    }

    private void insertOne() {
        showProgressBar();
        new Thread(() -> {
            SQLiteDatabase db = getWritableDatabase();
            Log.d(TAG, "insertOne: " + TAG + "@" + hashCode() + ",SQLiteDatabase@" + db.hashCode());
            ContentValues rowInitColumnValues = new ContentValues();
            rowInitColumnValues.put(Table1ReaderContract.TableEntry.COL2, "A");
            rowInitColumnValues.put(Table1ReaderContract.TableEntry.COL3, "100");

            long insertedNewRowId = db.insert(Table1ReaderContract.TableEntry.TABLE_NAME, null, rowInitColumnValues);
            Log.d(TAG, "insertOne: newRowId=" + insertedNewRowId);

            queryAll(db);
        }).start();
    }

    /**
     * 10000=
     * 0h:0m:13s:86ms
     * 0h:0m:13s:351ms
     * 0h:0m:12s:744ms
     * 0h:0m:12s:856ms
     */
    private void insertMultiple() {
        showProgressBar();
        new Thread(() -> {

            long start = System.currentTimeMillis();
            SQLiteDatabase db = getWritableDatabase();
            // insertMultiple(db, Table1ReaderContract.TableEntry.TABLE_NAME, DummyContent.ITEMS_3);
            insertMultiple(db, Table1ReaderContract.TableEntry.TABLE_NAME, DummyContent.ITEMS_1000());

            long end = System.currentTimeMillis();
            setUsedTime(start, end);

            queryAll(db);
        }).start();
    }

    private void insertMultiple(SQLiteDatabase db, String tableName, List<DummyItem> list) {
        for (int i = 0; i < list.size(); i++) {
            DummyItem dummyItem = list.get(i);
            // Way1
            db.insert(tableName, null, convertBean2ContentValues(dummyItem));

            // Way2
//            String sql = "INSERT INTO table1 VALUES(? , ? , ?)";
//            Object[] bindArgs = new Object[]{dummyItem.getId(), dummyItem.getColo2(), dummyItem.getCol3()};
//            db.execSQL(sql, bindArgs);

            // TODO: 2019/3/20
            // Way3:
            //            String sql = "INSERT INTO table1 VALUES(null , ? , ?)";
//            Object[] bindArgs = new Object[]{dummyItem.getColo2(), dummyItem.getCol3()};
//            db.execSQL(sql, bindArgs);
        }
    }

    private ContentValues convertBean2ContentValues(DummyItem info) {
        ContentValues value = new ContentValues();
        value.put(Table1ReaderContract.TableEntry._ID, info.getId());
        value.put(Table1ReaderContract.TableEntry.COL2, info.getColo2());
        value.put(Table1ReaderContract.TableEntry.COL3, info.getCol3());
        return value;
    }

    /**
     * 10000=
     * 0h:0m:0s:518ms
     * 0h:0m:0s:338ms
     * 0h:0m:0s:333ms
     */
    private void insertMultipleWithTransaction() {
        showProgressBar();
        new Thread(() -> {
            long start = System.currentTimeMillis();

            SQLiteDatabase db = getWritableDatabase();
            try {
                db.beginTransaction();
                //            insertMultiple(db, Table1ReaderContract.TableEntry.TABLE_NAME, DummyContent.ITEMS_3);
                insertMultiple(db, Table1ReaderContract.TableEntry.TABLE_NAME, DummyContent.ITEMS_1000());
                db.setTransactionSuccessful();
            } finally {
                db.endTransaction();
            }

            long end = System.currentTimeMillis();
            setUsedTime(start, end);
            queryAll(db);
        }).start();
    }

    /**
     * 10000 =
     * 0h:0m:0s:57ms
     * 0h:0m:0s:57ms
     * 0h:0m:0s:57ms
     * <p>
     * 100000 =
     * 0h:0m:1s:271ms
     * 0h:0m:1s:274ms
     * 0h:0m:1s:281ms
     */
    private void queryAll() {
        showProgressBar();
        new Thread(() -> {
            long start = System.currentTimeMillis();

            Cursor cursor = getReadableDatabase().query(Table1ReaderContract.TableEntry.TABLE_NAME, null, null, null, null, null, null);
            handleQueryResult(cursor);

            long end = System.currentTimeMillis();
            setUsedTime(start, end);
        }).start();
    }

    /**
     * 10000=
     * 0h:0m:0s:58ms
     * 0h:0m:0s:58ms
     * 0h:0m:0s:56ms
     * 0h:0m:0s:61ms
     * <p>
     * 100000 =
     * 0h:0m:1s:283ms
     * 0h:0m:1s:271ms
     * 0h:0m:1s:271ms
     */
    private void rawQueryAll() {
        showProgressBar();

        new Thread(() -> {
            // PO: Use getReadableDatabase()/getWritableDatabase() in background thread
            long start = System.currentTimeMillis();

            Cursor cursor = getReadableDatabase().rawQuery(FeedSQLiteOpenHelper.SQL_RETRIEVE_ENTRIES, null);
            handleQueryResult(cursor);

            long end = System.currentTimeMillis();
            setUsedTime(start, end);
        }).start();
    }

    private void queryAll(SQLiteDatabase db) {
        Cursor cursor = db.rawQuery(FeedSQLiteOpenHelper.SQL_RETRIEVE_ENTRIES, null);
        handleQueryResult(cursor);
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

    /**
     * colo2 任意位置含有1
     * 100000 =
     * 0h:0m:0s:676ms
     * 0h:0m:0s:591ms
     * 0h:0m:0s:595ms
     */
    private void fuzzySearch() {
        hideProgressBar();
        new Thread(() -> {
            long start = System.currentTimeMillis();

            SQLiteDatabase db = getReadableDatabase();
            String[] returnedColumns = {BaseColumns._ID, Table1ReaderContract.TableEntry.COL2, Table1ReaderContract.TableEntry.COL3};

            String selection = Table1ReaderContract.TableEntry.COL2 + " LIKE ?";
            String keyword = "1";
            String[] selectionArgs = {"%" + keyword + "%"};

            String orderBy = Table1ReaderContract.TableEntry.COL3 + " ASC";// DESC

            // PO: SQLiteDatabase Use query instead of rawQuery
            Cursor cursor = db.query(Table1ReaderContract.TableEntry.TABLE_NAME, returnedColumns, selection, selectionArgs, null, null, orderBy);
            handleQueryResult(cursor);

            long end = System.currentTimeMillis();
            setUsedTime(start, end);
        }).start();
    }

    /**
     * <pre>
     * 模糊查询
     *
     * colo2 任意位置含有1
     * 通配符：任何位置包含1
     *
     * 100000 =
     * 0h:0m:0s:591ms
     * 0h:0m:0s:594ms
     * 0h:0m:0s:599ms
     * <pre/>
     */
    private void fuzzySearch2() {
        hideProgressBar();
        new Thread(() -> {
            long start = System.currentTimeMillis();

            SQLiteDatabase db = getReadableDatabase();
            String keyword = "1";

            // Way1:
            String sql = "SELECT " + BaseColumns._ID + "," + Table1ReaderContract.TableEntry.COL2 + "," + Table1ReaderContract.TableEntry.COL3
                    + " FROM " + Table1ReaderContract.TableEntry.TABLE_NAME
                    + " WHERE " + Table1ReaderContract.TableEntry.COL2
                    + " LIKE '%" + keyword + "%'"
                    + " ORDER BY " + Table1ReaderContract.TableEntry.COL3 + " ASC";// DESC

            // Way2:
//            String sql ="SELECT _id,col2, col3 FROM table1 WHERE col2 like '%1%' ORDER BY col3 ASC ";

            Cursor cursor = db.rawQuery(sql, null);
            handleQueryResult(cursor);

            long end = System.currentTimeMillis();
            setUsedTime(start, end);
        }).start();
    }

    // col2 = A
    private void update() {
        new Thread(() -> {
            SQLiteDatabase db = getWritableDatabase();

            // String sql = "UPDATE table1 SET col2= 1553048517967 WHERE col2=A "; // error
            // Way1:
//            String sql = "UPDATE table1 SET col2= '1553048517967' WHERE col2='A' "; // ok
//            db.execSQL(sql);

            // Way2:
//            db.execSQL("UPDATE table1  SET col2=?  WHERE col2 = ?", new Object[]{String.valueOf(System.currentTimeMillis()), "A"}); // ok

            // way3
            String title = "New";
            ContentValues values = new ContentValues();
            values.put(Table1ReaderContract.TableEntry.COL2, title);

            String whereClause = Table1ReaderContract.TableEntry.COL2 + " = ?";
            String keyword = "A";
            String[] whereArgs = {keyword};

            int count = db.update(Table1ReaderContract.TableEntry.TABLE_NAME, values, whereClause, whereArgs);
            Log.d(TAG, "update:count=" + count);


        }).start();
    }

    // colo2 任意位置含有A
    private void updateFuzzy() {
        new Thread(() -> {
            SQLiteDatabase db = getWritableDatabase();

            // Way1:
            String newCol2Value = "New";
            ContentValues values = new ContentValues();
            values.put(Table1ReaderContract.TableEntry.COL2, newCol2Value);

            String whereClause = Table1ReaderContract.TableEntry.COL2 + " LIKE ?";
            String keyword = "A";
            String[] whereArgs = {"%" + keyword + "%"};

            // colo3 任意位置含有A
            int count = db.update(Table1ReaderContract.TableEntry.TABLE_NAME, values, whereClause, whereArgs);
            Log.d(TAG, "update:count=" + count);

            // Way2:
//            String sql = "UPDATE table1 SET col2='New' WHERE col2 LIKE '%A%' ";
//            db.execSQL(sql);
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

    private void deleteAll() {
        showProgressBar();
        new Thread(() -> {
            SQLiteDatabase db = getWritableDatabase();
            int deletedRowNum = db.delete(Table1ReaderContract.TableEntry.TABLE_NAME, null, null);
            Log.d(TAG, "delete: deletedRowNum=" + deletedRowNum);

            Cursor cursor = getReadableDatabase().rawQuery(FeedSQLiteOpenHelper.SQL_RETRIEVE_ENTRIES, null);
            handleQueryResult(cursor);

        }).start();
    }

    private void handleQueryResult(Cursor cursor) {
        ArrayList<DummyItem> list = cursor2BeanList(cursor);
        // PO: Cursor.close
        if (null != cursor) {
            cursor.close();
        }

        runOnUiThread(() -> {
            hideProgressBar();
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

    private void setUsedTime(long start, long end) {
        String duringTime = mDateUtil.compareDate(start, end);
        Log.d(TAG, "setUsedTime: " + start + "-" + end + " = " + duringTime);
        runOnUiThread(() -> {
            mUsedTimeTv.setText(duringTime);
            hideProgressBar();
        });
    }

    private void showProgressBar() {
        mUsedTimeTv.setText("");
        mProgressBar.setVisibility(View.VISIBLE);
        mFragmentRoot.setVisibility(View.GONE);
    }

    private void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
        mFragmentRoot.setVisibility(View.VISIBLE);
    }
}