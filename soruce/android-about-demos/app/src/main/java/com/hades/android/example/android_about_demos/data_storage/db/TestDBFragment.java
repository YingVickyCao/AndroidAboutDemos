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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_db_layout_2, container, false);

        mTableContentListView = view.findViewById(R.id.tableContentList);
        mTitleView = view.findViewById(R.id.title);
        mContentView = view.findViewById(R.id.content);
        view.findViewById(R.id.insertClick).setOnClickListener(v -> insertClick());

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        db = getActivity().getApplicationContext().openOrCreateDatabase(DB_NAME, MODE_PRIVATE, null);
    }

    private void insertClick() {
        String title = mTitleView.getText().toString();
        String content = mContentView.getText().toString();
        if (title.isEmpty()) {
            return;
        }
        try {
            insertData(db, title, content);
            Cursor cursor = db.rawQuery("select * from news_inf", null);
            inflateList(cursor);
        } catch (SQLiteException se) {
            // 执行DDL创建数据表
            db.execSQL("create table news_inf(_id integer" + " primary key autoincrement," + " news_title varchar(50)," + " news_content varchar(255))");
            // 执行insert语句插入数据
            insertData(db, title, content);
            // 执行查询
            Cursor cursor = db.rawQuery("select * from news_inf", null);
            inflateList(cursor);
        }
    }

    private void insertData(SQLiteDatabase db, String title, String content) {
        db.execSQL("insert into news_inf values(null , ? , ?)", new String[]{title, content});
    }

    private void inflateList(Cursor cursor) {
        if (null == adapter) {
            adapter = new SimpleCursorAdapter(getActivity(), R.layout.list_item_view_3, cursor, new String[]{"news_title", "news_content"}, new int[]{R.id.my_title, R.id.my_content}, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
            mTableContentListView.setAdapter(adapter);
        } else {
            adapter.swapCursor(cursor);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (db != null && db.isOpen()) {
            db.close();
        }
    }
}