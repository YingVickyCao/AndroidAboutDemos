package com.hades.android.example.android_about_demos.data_storage.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.hades.android.example.android_about_demos.R;
import com.hades.android.example.android_about_demos.base.BaseFragment;

import static android.content.Context.MODE_PRIVATE;

public class TestDBFragment extends BaseFragment {
    private static final String TAG = TestDBFragment.class.getSimpleName();

    private ListView mTableContentListView;

    private final String DB_NAME = "test_db.db";

    private SQLiteDatabase db;
    private SimpleCursorAdapter adapter;
    public static final int ONCE_FRESH_DATA_NUM = 1000;

    public final static String NEWS_INFO_TABLE_NAME = "news_info";
    public final static String NEWS_INFO_TABLE_id = "_id";
    public final static String NEWS_INFO_TABLE_NEWS_TITLE = "news_title";
    public final static String NEWS_INFO_TABLE_NEWS_CONTENT = "news_content";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_db_layout_2, container, false);

        mTableContentListView = view.findViewById(R.id.tableContentList);

        view.findViewById(R.id.insertBtnClick).setOnClickListener(v -> insertBtnClick());
        view.findViewById(R.id.deleteBtnClick).setOnClickListener(v -> deleteBtnClick());
        view.findViewById(R.id.updateBtnClick).setOnClickListener(v -> updateBtnClick());
        view.findViewById(R.id.queryBtnClick).setOnClickListener(v -> queryBtnClick());

        view.findViewById(R.id.insertBundlesBtnClick).setOnClickListener(v -> insertBundlesBtnClick());
        view.findViewById(R.id.createTableBtnClick).setOnClickListener(v -> createTableBtnClick());
        view.findViewById(R.id.deleteTableBtnClick).setOnClickListener(v -> deleteTableBtnClick());
        view.findViewById(R.id.deleteTableAllDataBtnClick).setOnClickListener(v -> deleteTableAllDataBtnClick());
        view.findViewById(R.id.clear).setOnClickListener(v -> clear());
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        db = getDB();
    }

    private void insertBtnClick() {
        new Thread(() -> {
            int count = count();
            String title = String.valueOf(count + 1);
            String content = title + "_Values";
            try {
                insert(db, title, content);
                query();
            } catch (SQLiteException se) {
                createTable();
                insert(db, title, content);
            }
        }).start();
    }

    private void insertBundlesBtnClick() {
        new Thread(() -> {
            int count = count();
            for (int i = 1; i <= ONCE_FRESH_DATA_NUM; i++) {
                String title = String.valueOf(count + i);
                String content = title + "_Values";

                try {
                    insert(db, title, content);
                } catch (Exception se) {
                    createTable();
                    insert(db, title, content);
                }
            }
            query();
        }).start();
    }

    private void deleteBtnClick() {
        delete();
        query();
    }

    private void updateBtnClick() {
        update();
        query();
    }

    /*
    ERROR:
    E/AndroidRuntime: FATAL EXCEPTION: main
    Process: PID: 30518
    android.database.sqlite.SQLiteException: no such table: news_info (code 1)

    #################################################################
    Error Code : 1 (SQLITE_ERROR)
    Caused By : SQL(query) error or missing database.
    	(no such table: news_info (code 1))
    #################################################################
     */
    private void queryBtnClick() {
        query();
    }

    private void createTableBtnClick() {
        createTable();
    }

    private void deleteTableBtnClick() {
        dropTable();
    }

    private void deleteTableAllDataBtnClick() {
        deleteAll();
        query();
    }

    private void clear() {
        if (null != adapter) {
            adapter.swapCursor(null);
            adapter.notifyDataSetChanged();
        }
    }

    private SQLiteDatabase getDB() {
        return db = getActivity().getApplicationContext().openOrCreateDatabase(DB_NAME, MODE_PRIVATE, null);
    }

    /**
     * DML
     */
    private void insert(SQLiteDatabase db, String title, String content) {
        db.execSQL("insert into news_info values(null , ? , ?)", new String[]{title, content});
    }

    private void delete() {
        // FIX_ERROR:android.database.sqlite.SQLiteException: no such column: news_info (code 1): , while compiling: DELETE FROM news_info WHERE news_info=2
        // If catch Exception,it will not crash.
//        try {
//            db.execSQL("DELETE FROM " + NEWS_INFO_TABLE_NAME + " WHERE " + NEWS_INFO_TABLE_NAME + "=1");
//        } catch (Exception ex) {
//            Log.d(TAG, "deleteBtnClick: ");
//            showToast("DELETE ERROR");
//        }

        try {
            // If table does not have the data[news_title=1],it does not throw exception.
//            db.execSQL("delete from news_info where news_title=1");
            db.execSQL("DELETE FROM " + NEWS_INFO_TABLE_NAME + " WHERE " + NEWS_INFO_TABLE_NEWS_TITLE + "=1");
        } catch (Exception ex) {
            Log.d(TAG, "deleteBtnClick: ");
            showToast("DELETE ERROR");
        }
    }

    private void deleteAll() {
        //        db.execSQL("DELETE from news_info");
        db.execSQL("DELETE from " + NEWS_INFO_TABLE_NAME);
    }

    private void update() {
//        db.execSQL("update news_info set news_content=" + System.currentTimeMillis() + " where news_title=5");
        db.execSQL("UPDATE " + NEWS_INFO_TABLE_NAME + " SET " + NEWS_INFO_TABLE_NEWS_CONTENT + "=" + System.currentTimeMillis() + " WHERE " + " news_title=8");
    }

    private void query() {
        Cursor cursor = rawQuery();
        inflateList(cursor);
    }

    private Cursor rawQuery() {
        return db.rawQuery("select * from news_info", null);
    }

    private int count() {
        return rawQuery().getCount();
    }

    /**
     * DDL
     */
    private void createTable() {
        //        db.execSQL("create table news_info(_id integer" + " primary key autoincrement," + " news_title varchar(50)," + " news_content varchar(255))");
        db.execSQL("CREATE TABLE " + NEWS_INFO_TABLE_NAME + " ("
                + NEWS_INFO_TABLE_id + " integer PRIMARY KEY AUTOINCREMENT,"
                + NEWS_INFO_TABLE_NEWS_TITLE + " varchar(50),"
                + NEWS_INFO_TABLE_NEWS_CONTENT + " varchar(255)" +
                ")");
    }

    private void dropTable() {
//        db.execSQL("DROP TABLE IF EXISTS news_info");
        db.execSQL("DROP TABLE IF EXISTS " + NEWS_INFO_TABLE_NAME);
    }

    private void inflateList(Cursor cursor) {
        getActivity().runOnUiThread(() -> {
            if (null == adapter) {
                adapter = new SimpleCursorAdapter(getActivity(), R.layout.list_item_view_3, cursor, new String[]{"news_title", "news_content"}, new int[]{R.id.my_title, R.id.my_content}, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
                mTableContentListView.setAdapter(adapter);
            } else {
                adapter.swapCursor(cursor);
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (db != null && db.isOpen()) {
            db.close();
        }
    }
}