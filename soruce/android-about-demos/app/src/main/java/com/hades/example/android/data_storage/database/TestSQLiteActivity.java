package com.hades.example.android.data_storage.database;

import android.app.Fragment;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.hades.example.android.R;
import com.hades.example.android.mock.DummyContent;
import com.hades.example.android.mock.DummyItem;

import java.util.ArrayList;
import java.util.List;

public class TestSQLiteActivity extends AppCompatActivity {
    private static final String TAG = TestSQLiteActivity.class.getSimpleName();

    // FIXED_ERROR: java.lang.NullPointerException: Attempt to invoke virtual method 'android.database.sqlite.SQLiteDatabase android.content.Context.openOrCreateDatabase(java.lang.String, int,
//    private FeedReaderDbHelper dbHelper = new FeedReaderDbHelper(getContext());
    private FeedReaderDbHelper dbHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.data_storage_sqlite);

        dbHelper = new FeedReaderDbHelper(this);

        findViewById(R.id.insert).setOnClickListener(v -> insert());
        findViewById(R.id.insertMultiple).setOnClickListener(v -> insertMultiple());
        findViewById(R.id.query).setOnClickListener(v -> query2());
        findViewById(R.id.update).setOnClickListener(v -> update());
        findViewById(R.id.delete).setOnClickListener(v -> delete());
    }

    private void insert() {
        // Gets the data repository in write mode
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(FeedReaderContract.FeedEntry.COL2, "A");
        values.put(FeedReaderContract.FeedEntry.COL3, "ABCDE");

        // Insert the new row, returning the primary key value of the new row
        // The second argument tells the framework what to do in the event that the ContentValues is empty
        long newRowId = db.insert(FeedReaderContract.FeedEntry.TABLE_NAME, null, values);
        Log.d(TAG, "insert: newRowId=" + newRowId);
    }

    private void insertMultiple() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        insertMultiple(db, FeedReaderContract.FeedEntry.TABLE_NAME, DummyContent.ITEMS_1);
//        insertMultiple(db, FeedReaderContract.FeedEntry.TABLE_NAME_2, DummyContent.ITEMS_2);
//        insertMultiple(db, FeedReaderContract.FeedEntry.TABLE_NAME_3, DummyContent.ITEMS_3);
    }

    // TODO: 2019/3/15 refactor:move DummyItem,DummyContent -> lib
    private void insertMultiple(SQLiteDatabase db, String tableName, List<DummyItem> list) {
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

    private ContentValues convertBean2ContentValues(DummyItem info) {
        ContentValues value = new ContentValues();
        value.put(FeedReaderContract.FeedEntry._ID, info.getId());
        value.put(FeedReaderContract.FeedEntry.COL2, info.getColo2());
        value.put(FeedReaderContract.FeedEntry.COL3, info.getCol3());
        return value;
    }


    private void query() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // Define a columns that specifies which columns from the database
        // you will actually use after this query.
        String[] columns = {
                BaseColumns._ID,
                FeedReaderContract.FeedEntry.COL2,
                FeedReaderContract.FeedEntry.COL3
        };

        // Filter results WHERE "title" = 'My Title'
        String selection = FeedReaderContract.FeedEntry.COL2 + " = ?";
        String[] selectionArgs = {"My Title"};

        // How you want the results sorted in the resulting Cursor
        String orderBy = FeedReaderContract.FeedEntry.COL3 + " DESC";

        Cursor cursor = db.query(
                FeedReaderContract.FeedEntry.TABLE_NAME,   // The table to query
                columns,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                orderBy               // The sort order
        );

        List itemIds = new ArrayList<>();
        while (cursor.moveToNext()) {
            long itemId = cursor.getLong(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry._ID));
            itemIds.add(itemId);
        }
        cursor.close();
    }

    private void query2() {
        Cursor cursor = dbHelper.getReadableDatabase().rawQuery(FeedReaderDbHelper.SQL_RETRIEVE_ENTRIES, null);
        ArrayList<DummyItem> list = cursor2BeanList(cursor);
        // TODO: 2019/3/15 refactor
        Fragment fragment = getFragmentManager().findFragmentByTag(DummyContentFragment.TAG);
        if (null == fragment) {
            fragment = DummyContentFragment.getInstance(list);
            getFragmentManager().beginTransaction().add(R.id.fragmentRoot, fragment, DummyContentFragment.TAG).commit();
        } else {
            ((DummyContentFragment) fragment).setList(list);
        }
    }

    protected ArrayList<DummyItem> cursor2BeanList(Cursor cursor) {
        ArrayList<DummyItem> list = new ArrayList<>();
        while (cursor.moveToNext()) {
            DummyItem dummyItem = new DummyItem(cursor.getInt(0), cursor.getString(1), cursor.getInt(2));
            list.add(dummyItem);
        }
        return list;
    }

    private void update() {

    }

    private void delete() {

    }
}
