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
        findViewById(R.id.query).setOnClickListener(v -> query2());
        findViewById(R.id.update).setOnClickListener(v -> update());
        findViewById(R.id.delete).setOnClickListener(v -> delete());
    }

    private void insert() {
        // Gets the data repository in write mode
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE, "A");
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_SUBTITLE, "ABCDE");

        // Insert the new row, returning the primary key value of the new row
        // The second argument tells the framework what to do in the event that the ContentValues is empty
        long newRowId = db.insert(FeedReaderContract.FeedEntry.TABLE_NAME, null, values);
        Log.d(TAG, "insert: newRowId=" + newRowId);
    }

    private void query() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // Define a columns that specifies which columns from the database
        // you will actually use after this query.
        String[] columns = {
                BaseColumns._ID,
                FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE,
                FeedReaderContract.FeedEntry.COLUMN_NAME_SUBTITLE
        };

        // Filter results WHERE "title" = 'My Title'
        String selection = FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE + " = ?";
        String[] selectionArgs = {"My Title"};

        // How you want the results sorted in the resulting Cursor
        String orderBy =
                FeedReaderContract.FeedEntry.COLUMN_NAME_SUBTITLE + " DESC";

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
        Fragment fragment = getFragmentManager().findFragmentByTag(DisplayDBFragment.TAG);
        if (null == fragment) {
            fragment = DisplayDBFragment.getInstance(list);
            getFragmentManager().beginTransaction().add(R.id.fragmentRoot, fragment, DisplayDBFragment.TAG).commit();
        } else {
            ((DisplayDBFragment) fragment).setList(list);
        }
    }

    protected ArrayList<DummyItem> cursor2BeanList(Cursor cursor) {
        ArrayList<DummyItem> list = new ArrayList<>();
        while (cursor.moveToNext()) {
            DummyItem dummyItem = new DummyItem(cursor.getString(0), cursor.getString(1), cursor.getString(2));
            list.add(dummyItem);
        }
        return list;
    }

    private void update() {

    }

    private void delete() {

    }
}
