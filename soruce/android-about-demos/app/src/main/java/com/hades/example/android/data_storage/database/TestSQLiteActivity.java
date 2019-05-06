package com.hades.example.android.data_storage.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.hades.example.android.R;
import com.hades.example.android.lib.base.NoNeedPermissionActivity;
import com.hades.example.android.lib.mock.DummyContent;
import com.hades.example.android.lib.mock.DummyItem;
import com.hades.example.android.lib.utils.DateUtil;
import com.hades.example.android.lib.mock.DummyContentFragment;

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

        findViewById(R.id.clear).setOnClickListener(v -> clear());

        findViewById(R.id.insertOne).setOnClickListener(v -> insertOne());
        findViewById(R.id.insertMultiple).setOnClickListener(v -> insertMultiple());
        findViewById(R.id.insertMultipleWithTransaction).setOnClickListener(v -> insertMultipleWithTransaction());
        findViewById(R.id.insertSQLiteStatementWithTransaction).setOnClickListener(v -> insertSQLiteStatementWithTransaction());

        findViewById(R.id.queryTotalCountOfOneTable).setOnClickListener(v -> queryTotalCountOfOneTable());
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

    private void clear() {
        mUsedTimeTv.setText("");
        handleQueryResult(null);
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
     * <pre>
     * db.insert():
     *
     * 1000 =
     * 0h:0m:6s:258ms
     * 0h:0m:5s:542ms
     * 0h:0m:4s:836ms
     *
     * 10000 =
     * 0h:0m:6s:374ms
     * 0h:0m:5s:119ms
     * 0h:0m:4s:482ms
     * 0h:0m:4s:559ms
     * 0h:0m:4s:35ms
     * 0h:0m:4s:58ms
     *
     * 100000 =
     * After waiting long time, app crashed.
     *
     * zygote64: Background concurrent copying GC freed 367180(13MB) AllocSpace objects, 18(12MB) LOS objects, 49% free, 11MB/22MB, paused 463us total 155.723m
     * zygote64: Background concurrent copying GC freed 307260(10MB) AllocSpace objects, 0(0B) LOS objects, 49% free, 11MB/22MB, paused 304us total 108.584ms
     * zygote64: Background concurrent copying GC freed 308157(10MB) AllocSpace objects, 0(0B) LOS objects, 49% free, 11MB/22MB, paused 447us total 121.089ms
     * zygote64: Background concurrent copying GC freed 308157(10MB) AllocSpace objects, 0(0B) LOS objects, 49% free, 11MB/22MB, paused 508us total 116.254ms
     * zygote64: Background concurrent copying GC freed 308157(10MB) AllocSpace objects, 0(0B) LOS objects, 49% free, 11MB/22MB, paused 323us total 107.851ms
     * ...
     *
     * Crash log:
     * E/SQLiteLog: (1802) os_unix.c:35890: (2) stat(/data/user/0/com.hades.example.android/databases/FeedReader.db) -
     * E/SQLiteLog: (1) Process example.android : Pid (30147) Uid (10503) Euid (10503) Gid (10503) Egid (10503)
     * E/SQLiteLog: (1) osStat failed "/data/user/0/com.hades.example.android/databases/FeedReader.db" due to error (2)
     * E/SQLiteLog: (1) Stat of /data/user/0/com.hades.example.android/databases : st_mode(40771) st_uid(10503) st_gid(10503) st_ino(918936)
     * E/SQLiteLog: (1) Stat of /data/user/0/com.hades.example.android : st_mode(40700) st_uid(10503) st_gid(10503) st_ino(917526)
     * E/SQLiteLog: (1) Stat of /data/user/0 : st_mode(40771) st_uid(1000) st_gid(1000) st_ino(917505)
     * E/SQLiteLog: (1) Stat of /data/user : st_mode(40711) st_uid(1000) st_gid(1000) st_ino(1179650)
     * E/SQLiteLog: (1) Stat of /data : st_mode(40771) st_uid(1000) st_gid(1000) st_ino(2)
     * E/SQLiteLog: (1802) statement aborts at 21: [INSERT INTO table1(_id,col2,col3) VALUES (?,?,?)] disk I/O error
     * E/SQLiteLog: (1) Force to rollback the active transaction caused by the special error (10), during 'INSERT INTO table1(_id,col2,col3) VALUES (?,?,?)'
     * E/SQLiteDatabase: Error inserting _id=96503 col2=Dummy96503 col3=96503
     *     android.database.sqlite.SQLiteDiskIOException: disk I/O error (code 1802)
     *     #################################################################
     *     Error Code : 1802 (SQLITE_IOERR_FSTAT)
     *     Caused By : Failed to get database file information with system call stat(). Please confirm whether database file has been removed.
     *     	(disk I/O error (code 1802))
     *     #################################################################
     *
     * -------------------------------------------------------------------------
     * execSQL insert:
     *
     * 1000:
     * 0h:0m:4s:792ms
     * 0h:0m:5s:693ms
     * 0h:0m:5s:425ms
     *
     * 10000 =
     * 0h:0m:6s:40ms
     * 0h:0m:5s:827ms
     * 0h:0m:5s:920ms
     *
     * 100000 =
     * 0h:10m:13s:960ms
     *
     * logs:
     * I/zygote64: Do full code cache collection, code=252KB, data=192KB
     * I/zygote64: After code cache collection, code=204KB, data=131KB
     * I/zygote64: Background concurrent copying GC freed 182408(7MB) AllocSpace objects, 0(0B) LOS objects, 49% free, 7MB/14MB, paused 297us total 102.899ms
     * w/CursorWindow: Window is full: requested allocation 404 bytes, free space 321 bytes, window size 2097152 bytes
     * w/CursorWindow: Window is full: requested allocation 404 bytes, free space 321 bytes, window size 2097152 bytes
     * w/CursorWindow: Window is full: requested allocation 36 bytes, free space 21 bytes, window size 2097152 bytes
     * w/CursorWindow: Window is full: requested allocation 36 bytes, free space 21 bytes, window size 2097152 bytes
     *
     * zygote64: Do partial code cache collection, code=246KB, data=210KB
     * I/zygote64: After code cache collection, code=244KB, data=210KB
     * I/zygote64: Increasing code cache capacity to 1024KB
     * I/zygote64: Compiler allocated 9MB to compile void android.widget.TextView.<init>(android.content.Context, android.util.AttributeSet, int, int)
     * <pre/>
     */
    private void insertMultiple() {
        showProgressBar();
        new Thread(() -> {
            long start = System.currentTimeMillis();

            SQLiteDatabase db = getWritableDatabase();
            insertMultiple(db, Table1ReaderContract.TableEntry.TABLE_NAME, getDummyItems());//DummyContent.ITEMS_1000,DummyContent.ITEMS_3,DummyContent.ITEMS_100000

            long end = System.currentTimeMillis();
            setUsedTime(start, end);

            queryAll(db);
        }).start();
    }

    private List<DummyItem> getDummyItems() {
//        return DummyContent.ITEMS_100000();
//        return DummyContent.ITEMS_10000();
        return DummyContent.ITEMS_1000();
    }

    private void insertMultiple(SQLiteDatabase db, String tableName, List<DummyItem> list) {
//        insertMultiple_way1(db, tableName, list);
        insertMultiple_way2(db, tableName, list);
//        insertMultiple_way3(db, tableName, list);
    }

    private void insertMultiple_way1(SQLiteDatabase db, String tableName, List<DummyItem> list) {
        for (int i = 0; i < list.size(); i++) {
            DummyItem dummyItem = list.get(i);
            // Way1
            db.insert(tableName, null, convertBean2ContentValues(dummyItem));
        }
    }

    private void insertMultiple_way2(SQLiteDatabase db, String tableName, List<DummyItem> list) {
        for (int i = 0; i < list.size(); i++) {
            DummyItem dummyItem = list.get(i);

            // Way2
            String sql = "INSERT INTO table1 VALUES(? , ? , ?)";
            Object[] bindArgs = new Object[]{dummyItem.getId(), dummyItem.getColo2(), dummyItem.getCol3()};
            db.execSQL(sql, bindArgs);
        }
    }

    private void insertMultiple_way3(SQLiteDatabase db, String tableName, List<DummyItem> list) {
        for (int i = 0; i < list.size(); i++) {
            DummyItem dummyItem = list.get(i);

            // Way3:
            String sql = "INSERT INTO table1 VALUES(null , ? , ?)";
            Object[] bindArgs = new Object[]{dummyItem.getColo2(), dummyItem.getCol3()};
            db.execSQL(sql, bindArgs);
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
     * <pre>
     * db.insert() with Transaction:
     *
     * 1000 =
     * 0h:0m:0s:49ms
     * 0h:0m:0s:53ms
     * 0h:0m:0s:52ms
     *
     * 10000 =
     * 0h:0m:0s:518ms
     * 0h:0m:0s:338ms
     * 0h:0m:0s:333ms
     *
     * 100000 =
     * 0h:0m:3s:447ms
     * 0h:0m:3s:221ms
     * 0h:0m:3s:248ms
     * ----------------------------------
     * execSQL insert with Transaction:
     *
     * 1000=
     * 0h:0m:0s:48ms
     * 0h:0m:0s:42ms
     * 0h:0m:0s:44ms
     * 0h:0m:0s:48ms
     *
     * 10000=
     * 0h:0m:0s:123ms
     * 0h:0m:0s:59ms
     * 0h:0m:0s:55ms
     *
     * 100000 =
     * 0h:0m:2s:845ms
     * 0h:0m:2s:616ms
     * 0h:0m:2s:641ms
     *
     * <pre/>
     */
    private void insertMultipleWithTransaction() {
        showProgressBar();
        new Thread(() -> {
            long start = System.currentTimeMillis();

            SQLiteDatabase db = getWritableDatabase();
            try {
                db.beginTransaction();
                insertMultiple(db, Table1ReaderContract.TableEntry.TABLE_NAME, getDummyItems());
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
     * <pre>
     * SQLiteStatement.executeInsert() with Transaction
     * 1000 =
     * 0h:0m:0s:40ms
     * 0h:0m:0s:44ms
     * 0h:0m:0s:46ms
     *
     * 10000 =
     * 0h:0m:0s:65ms
     * 0h:0m:0s:48ms
     * 0h:0m:0s:44ms
     * 0h:0m:0s:45ms
     *
     * 100000 =
     * 0h:0m:2s:931ms
     * 0h:0m:2s:939ms
     * 0h:0m:2s:928ms
     *
     * <pre/>
     */
    private void insertSQLiteStatementWithTransaction() {
        showProgressBar();
        new Thread(() -> {
            long start = System.currentTimeMillis();

            SQLiteDatabase db = getWritableDatabase();
            try {
                db.beginTransaction();

                StringBuffer sql_insert;
                sql_insert = new StringBuffer();
                sql_insert.append("INSERT INTO table1(col2,col3) ");
                sql_insert.append(" VALUES(?, ?)");

                List<DummyItem> items = getDummyItems();
                for (DummyItem user : items) {

                    SQLiteStatement statement = db.compileStatement(sql_insert.toString());
                    statement.bindString(1, user.getColo2());
                    statement.bindLong(2, user.getCol3());
                    statement.executeInsert();
                }

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
     * <pre>
     * db.query():
     *
     * 10000 =
     * 0h:0m:0s:57ms
     * 0h:0m:0s:57ms
     * 0h:0m:0s:57ms
     *
     * 100000 =
     * 0h:0m:1s:271ms
     * 0h:0m:1s:274ms
     * 0h:0m:1s:281ms
     * <pre/>
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
     * <pre>
     * db.rawQuery():
     *
     * 10000=
     * 0h:0m:0s:58ms
     * 0h:0m:0s:58ms
     * 0h:0m:0s:56ms
     * 0h:0m:0s:61ms
     *
     * 100000 =
     * 0h:0m:1s:283ms
     * 0h:0m:1s:271ms
     * 0h:0m:1s:271ms
     *
     * <pre/>
     */
    private void rawQueryAll() {
        showProgressBar();

        new Thread(() -> {
            long start = System.currentTimeMillis();

            // PO: Use getReadableDatabase()/getWritableDatabase() in background thread
            Cursor cursor = getReadableDatabase().rawQuery(FeedSQLiteOpenHelper.SQL_RETRIEVE_TABLE_TABLE1, null);
            handleQueryResult(cursor);

            long end = System.currentTimeMillis();
            setUsedTime(start, end);
        }).start();
    }

    private void queryAll(SQLiteDatabase db) {
        Cursor cursor = db.rawQuery(FeedSQLiteOpenHelper.SQL_RETRIEVE_TABLE_TABLE1, null);
        handleQueryResult(cursor);
    }

    private void queryTotalCountOfOneTable() {
        showProgressBar();
        new Thread(() -> {
            queryTotalCountOfOneTable_way1();
            queryTotalCountOfOneTable_way2();
            queryTotalCountOfOneTable_way3();

            runOnUiThread(() -> hideProgressBar());
        }).start();

    }

    /**
     * 100000 =
     * 0h:0m:0s:1ms
     * 0h:0m:0s:1ms
     * 0h:0m:0s:2ms
     */
    private void queryTotalCountOfOneTable_way1() {
        long start = System.currentTimeMillis();

        SQLiteDatabase db = getReadableDatabase();
        // String sql = "SELECT count(*) FROM table1";
        String sql = "SELECT count(*) FROM " + Table1ReaderContract.TableEntry.TABLE_NAME;
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        int count = cursor.getInt(0); // ok
        cursor.close();

        long end = System.currentTimeMillis();
        String duringTime = mDateUtil.compareDate(start, end);
        Log.d(TAG, "queryTotalCountOfOneTable_way1: duringTime=" + duringTime + ",size=" + count);
    }

    /**
     * 100000 =
     * 0h:0m:0s:188ms
     * 0h:0m:0s:190ms
     * 0h:0m:0s:190ms
     */
    private void queryTotalCountOfOneTable_way2() {
        long start = System.currentTimeMillis();

        SQLiteDatabase db = getReadableDatabase();
        // String sql = "SELECT * FROM table1";
        String sql = "SELECT * FROM " + Table1ReaderContract.TableEntry.TABLE_NAME;
        Cursor cursor = db.rawQuery(sql, null);
        int count = cursor.getCount();
        cursor.close();

        long end = System.currentTimeMillis();
        String duringTime = mDateUtil.compareDate(start, end);
        Log.d(TAG, "queryTotalCountOfOneTable_way2: duringTime=" + duringTime + ",size=" + count);
    }

    /**
     * 100000 =
     * 0h:0m:0s:1ms
     * 0h:0m:0s:1ms
     * 0h:0m:0s:1ms
     */
    private void queryTotalCountOfOneTable_way3() {
        long start = System.currentTimeMillis();

        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT count(*) FROM " + Table1ReaderContract.TableEntry.TABLE_NAME;
        long numRows = DatabaseUtils.longForQuery(db, sql, null);

        long end = System.currentTimeMillis();
        String duringTime = mDateUtil.compareDate(start, end);
        Log.d(TAG, "queryTotalCountOfOneTable_way3: duringTime=" + duringTime + ",size=" + numRows);
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
            fuzzySearch2_way1();
//            fuzzySearch2_way2();
        }).start();
    }

    private void fuzzySearch2_way1() {
        long start = System.currentTimeMillis();

        SQLiteDatabase db = getReadableDatabase();
        String keyword = "1";

        // Way1:
        String sql = "SELECT " + BaseColumns._ID + "," + Table1ReaderContract.TableEntry.COL2 + "," + Table1ReaderContract.TableEntry.COL3
                + " FROM " + Table1ReaderContract.TableEntry.TABLE_NAME
                + " WHERE " + Table1ReaderContract.TableEntry.COL2
                + " LIKE '%" + keyword + "%'"
                + " ORDER BY " + Table1ReaderContract.TableEntry.COL3 + " ASC";// DESC

        Cursor cursor = db.rawQuery(sql, null);
        handleQueryResult(cursor);

        long end = System.currentTimeMillis();
        setUsedTime(start, end);
    }

    private void fuzzySearch2_way2() {
        long start = System.currentTimeMillis();

        SQLiteDatabase db = getReadableDatabase();

        // Way2:
        String sql = "SELECT _id,col2, col3 FROM table1 WHERE col2 like '%1%' ORDER BY col3 ASC ";

        Cursor cursor = db.rawQuery(sql, null);
        handleQueryResult(cursor);

        long end = System.currentTimeMillis();
        setUsedTime(start, end);
    }

    // col2 = A
    private void update() {
        new Thread(() -> {
            update_way1();
//            update_way2();
//            update_way3();
        }).start();
    }

    private void update_way1() {
        SQLiteDatabase db = getWritableDatabase();

        // String sql = "UPDATE table1 SET col2= 1553048517967 WHERE col2=A "; // error
        String sql = "UPDATE table1 SET col2= '1553048517967' WHERE col2='A' "; // ok
        db.execSQL(sql);
    }

    private void update_way2() {
        SQLiteDatabase db = getWritableDatabase();

        db.execSQL("UPDATE table1  SET col2=?  WHERE col2 = ?", new Object[]{String.valueOf(System.currentTimeMillis()), "A"}); // ok
    }

    private void update_way3() {
        SQLiteDatabase db = getWritableDatabase();

        String title = "New";
        ContentValues values = new ContentValues();
        values.put(Table1ReaderContract.TableEntry.COL2, title);

        String whereClause = Table1ReaderContract.TableEntry.COL2 + " = ?";
        String keyword = "A";
        String[] whereArgs = {keyword};

        int count = db.update(Table1ReaderContract.TableEntry.TABLE_NAME, values, whereClause, whereArgs);
        Log.d(TAG, "update:count=" + count);
    }

    // colo2 任意位置含有A
    private void updateFuzzy() {
        new Thread(() -> {
            updateFuzzy_way1();
//            updateFuzzy_way2();
        }).start();
    }

    private void updateFuzzy_way1() {
        SQLiteDatabase db = getWritableDatabase();

        String newCol2Value = "New";
        ContentValues values = new ContentValues();
        values.put(Table1ReaderContract.TableEntry.COL2, newCol2Value);

        String whereClause = Table1ReaderContract.TableEntry.COL2 + " LIKE ?";
        String keyword = "A";
        String[] whereArgs = {"%" + keyword + "%"};

        int count = db.update(Table1ReaderContract.TableEntry.TABLE_NAME, values, whereClause, whereArgs);
        Log.d(TAG, "update:count=" + count);

    }

    private void updateFuzzy_way2() {
        SQLiteDatabase db = getWritableDatabase();

        String sql = "UPDATE table1 SET col2='New' WHERE col2 LIKE '%A%' "; // ok
        db.execSQL(sql);
    }

    // col2 任意位置包含A
    private void delete() {
        new Thread(() -> {
            delete_way1();
//            delete_way2();

        }).start();
    }

    private void delete_way1() {
        SQLiteDatabase db = getWritableDatabase();

        // Way1:
        String whereClause = Table1ReaderContract.TableEntry.COL2 + " LIKE ?";
        String keyword = "A";
        String[] whereArgs = {"%" + keyword + "%"};

        int deletedRowNum = db.delete(Table1ReaderContract.TableEntry.TABLE_NAME, whereClause, whereArgs);
        Log.d(TAG, "delete: deletedRowNum=" + deletedRowNum);
    }

    private void delete_way2() {
        SQLiteDatabase db = getWritableDatabase();

        String sql = "DELETE FROM  table1 WHERE col2 LIKE '%A%' ";
        db.execSQL(sql);
        queryAll(db);
    }

    private void deleteAll() {
        showProgressBar();
        new Thread(() -> {
            deleteAll_way1();
//            deleteAll_way2();
        }).start();
    }

    private void deleteAll_way1() {
        SQLiteDatabase db = getWritableDatabase();

        // Way1:
        int deletedRowNum = db.delete(Table1ReaderContract.TableEntry.TABLE_NAME, null, null);
        Log.d(TAG, "delete: deletedRowNum=" + deletedRowNum);

        Cursor cursor = getReadableDatabase().rawQuery(FeedSQLiteOpenHelper.SQL_RETRIEVE_TABLE_TABLE1, null);
        handleQueryResult(cursor);
    }

    private void deleteAll_way2() {
        SQLiteDatabase db = getWritableDatabase();
        String sql = "DELETE FROM table1";
        db.execSQL(sql);
        queryAll(db);
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
            Fragment fragment = getSupportFragmentManager().findFragmentByTag(DummyContentFragment.TAG);
            if (null == fragment) {
                fragment = DummyContentFragment.getInstance(list);
                getSupportFragmentManager().beginTransaction().add(R.id.fragmentRoot, fragment, DummyContentFragment.TAG).commit();
            } else {
                ((DummyContentFragment) fragment).setList(list);
            }
        });
    }

    protected ArrayList<DummyItem> cursor2BeanList(Cursor cursor) {
        ArrayList<DummyItem> list = new ArrayList<>();
        if (null == cursor) {
            return list;
        }
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