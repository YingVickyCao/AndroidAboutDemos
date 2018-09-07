package com.hades.android.example.android_about_demos.data_storage.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.hades.android.example.android_about_demos.R;
import com.hades.android.example.android_about_demos.base.BaseFragment;

import static android.content.Context.MODE_PRIVATE;

public class TestDBFragment extends BaseFragment {

    private ListView mTableContentListView;
    private EditText mTitleView;
    private EditText mContentView;

    private final String DB_NAME = "test_db.db";

    private SQLiteDatabase db;
    private SimpleCursorAdapter adapter;
    public static final int ONCE_FRESH_DATA_NUM = 1000;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_db_layout_2, container, false);

        mTableContentListView = view.findViewById(R.id.tableContentList);
        mTitleView = view.findViewById(R.id.title);
        mContentView = view.findViewById(R.id.content);

        view.findViewById(R.id.insertBundlesBtnClick).setOnClickListener(v -> insertBundlesBtnClick());

        view.findViewById(R.id.insertBtnClick).setOnClickListener(v -> insertBtnClick());
        view.findViewById(R.id.deleteBtnClick).setOnClickListener(v -> deleteBtnClick());
        view.findViewById(R.id.updateBtnClick).setOnClickListener(v -> updateBtnClick());
        view.findViewById(R.id.queryBtnClick).setOnClickListener(v -> queryBtnClick());
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        db = getActivity().getApplicationContext().openOrCreateDatabase(DB_NAME, MODE_PRIVATE, null);
    }

    public final static String NEWS_INFO_TABLE_NAME = "news_info";
    public final static String NEWS_INFO_TABLE_id = "_id";
    public final static String NEWS_INFO_TABLE_NEWS_TITLE = "news_title";
    public final static String NEWS_INFO_TABLE_NEWS_CONTENT = "news_content";

    private void doCreateTable() {
//        db.execSQL("create table news_info(_id integer" + " primary key autoincrement," + " news_title varchar(50)," + " news_content varchar(255))");
        db.execSQL("CREATE TABLE " + NEWS_INFO_TABLE_NAME + " ("
                + NEWS_INFO_TABLE_id + " integer PRIMARY KEY AUTOINCREMENT,"
                + NEWS_INFO_TABLE_NEWS_TITLE + " varchar(50),"
                + NEWS_INFO_TABLE_NEWS_CONTENT + " varchar(255)" +
                ")");
    }

    private void insertBtnClick() {
        new Thread(() -> {
            String title = mTitleView.getText().toString();
            String content = mContentView.getText().toString();
            if (title.isEmpty()) {
                return;
            }
            try {
                insert(db, title, content);
                query();
            } catch (SQLiteException se) {
                doCreateTable();
                insert(db, title, content);
            }
        }).start();
    }

    private void insertBundlesBtnClick() {
        new Thread(() -> {
            String title = mTitleView.getText().toString();
            String content = mContentView.getText().toString();
            if (title.isEmpty()) {
                return;
            }
            try {
                insert(db, title, content);
                query();
            } catch (SQLiteException se) {
                doCreateTable();
                insert(db, title, content);
            }
        }).start();
    }

    private void deleteBtnClick() {
//        db.execSQL("DROP TABLE IF EXISTS " + NoBatchNumbersContentProvider.TABLE_NAME);
    }

    private void updateBtnClick() {

    }

    private void queryBtnClick() {
        query();
    }

    private void query() {
        Cursor cursor = db.rawQuery("select * from news_info", null);
        inflateList(cursor);
    }

    private void insert(SQLiteDatabase db, String title, String content) {
        db.execSQL("insert into news_info values(null , ? , ?)", new String[]{title, content});
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