package com.hades.example.android.data_storage.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hades.example.android.R;
import com.hades.example.android.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

public class TestSQLiteFragment extends BaseFragment {
    private static final String TAG = TestSQLiteFragment.class.getSimpleName();


    // FIXED_ERROR: java.lang.NullPointerException: Attempt to invoke virtual method 'android.database.sqlite.SQLiteDatabase android.content.Context.openOrCreateDatabase(java.lang.String, int,
//    private FeedReaderDbHelper dbHelper = new FeedReaderDbHelper(getContext());
    private FeedReaderDbHelper dbHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.data_storage_sqlite, container, false);

        dbHelper = new FeedReaderDbHelper(getActivity());

        view.findViewById(R.id.insert).setOnClickListener(v -> insert());
        view.findViewById(R.id.query).setOnClickListener(v -> query());
        view.findViewById(R.id.update).setOnClickListener(v -> update());
        view.findViewById(R.id.delete).setOnClickListener(v -> delete());
        return view;
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

    private void update() {

    }

    private void delete() {

    }
}
