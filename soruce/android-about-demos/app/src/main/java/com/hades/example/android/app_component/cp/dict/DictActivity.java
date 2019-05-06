package com.hades.example.android.app_component.cp.dict;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.hades.example.android.R;
import com.hades.example.android.lib.mock.DummyContentFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DictActivity extends Activity {
    MyDatabaseHelper dbHelper;

    private EditText mInputWorldView;
    private EditText mInputIdView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cp_dict);

        dbHelper = new MyDatabaseHelper(this, 1);

        mInputWorldView = findViewById(R.id.word);
        mInputIdView = findViewById(R.id.key);

        findViewById(R.id.insertBtnClick).setOnClickListener(source -> insert());
        findViewById(R.id.query).setOnClickListener(source -> search());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (dbHelper != null) {
            dbHelper.close();
        }
    }

    private String getInputWord() {
        return mInputWorldView.getText().toString();
    }

    private String buildDetail(String word) {
        return word + "  detail";
    }

    private void insert() {
        String word = getInputWord();
        if (word.isEmpty()) {
            Toast.makeText(DictActivity.this, "Input invalid", Toast.LENGTH_SHORT).show();
            return;
        }
        addWord2Db(word, buildDetail(word));
        Toast.makeText(DictActivity.this, "添加生词成功！", Toast.LENGTH_LONG).show();
    }

    private void addWord2Db(String word, String detail) {
        // table name = dict
        dbHelper.getReadableDatabase().execSQL("insert into dict values(null , ? , ?)", new String[]{word, detail});
    }

    private String getInputQueryUsedId() {
        return mInputIdView.getText().toString();
    }

    private void search() {
        Cursor cursor = queryDictById(getInputQueryUsedId());

        Bundle data = new Bundle();
        data.putSerializable(DummyContentFragment.KEY_SEARCH_RESULT, convertCursorResultToList(cursor));

        Intent intent = new Intent(DictActivity.this, DictSearchResultActivity.class);
        intent.putExtras(data);
        startActivity(intent);
    }

    protected ArrayList<Map<String, String>> convertCursorResultToList(Cursor cursor) {
        ArrayList<Map<String, String>> result = new ArrayList<>();

        while (cursor.moveToNext()) {
            Map<String, String> map = new HashMap<>();
            // 取出查询记录中第2列、第3列的值
            map.put("col2", cursor.getString(1));
            map.put("col3", cursor.getString(2));
            result.add(map);
        }
        return result;
    }

    private Cursor queryDictById(String key) {
        /**
         * FIXED_ERROR:
         * 2019-03-15 11:22:16.590 19002-19002/com.hades.example.android E/AndroidRuntime: FATAL EXCEPTION: main
         *     Process: com.hades.example.android, PID: 19002
         *     android.database.sqlite.SQLiteException: no such table: dict (code 1): , while compiling: select * from dict where word like ? or detail like ?
         *     #################################################################
         *     Error Code : 1 (SQLITE_ERROR)
         *     Caused By : SQL(query) error or missing database.
         *     	(no such table: dict (code 1): , while compiling: select * from dict where word like ? or detail like ?)
         *     #################################################################
         */
//        return dbHelper.getReadableDatabase().rawQuery("select * from table1 where col2 like ? or colo3 like ?", new String[]{"%" + key + "%", "%" + key + "%"});
        return dbHelper.getReadableDatabase().rawQuery("select * from table1 ", null);
    }
}