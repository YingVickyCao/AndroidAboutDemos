package com.hades.example.android.app_component.assist.cp.cr;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.widget.EditText;
import android.widget.Toast;

import com.hades.example.android.app_component.assist.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class DictUserActivity extends Activity {

    private ContentResolver contentResolver;
    public static final String KEY_SEARCH_RESULT = "search_result";
    private DictContentObserver mDictContentObserver;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cp_dict_user);

        contentResolver = getContentResolver();

        mDictContentObserver = new DictContentObserver(DictUserActivity.this, new Handler());
        getContentResolver().registerContentObserver(Dict.Word.WORDS_URI, true, mDictContentObserver);

        findViewById(R.id.insert).setOnClickListener(v -> insert());
        findViewById(R.id.search).setOnClickListener(v -> search());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (null != mDictContentObserver) {
            getContentResolver().unregisterContentObserver(mDictContentObserver);
            mDictContentObserver = null;
        }
    }

    private void insert() {
        String word = ((EditText) findViewById(R.id.word)).getText().toString();

        if (word.isEmpty()) {
            Toast.makeText(DictUserActivity.this, "Input invalid", Toast.LENGTH_SHORT).show();
            return;
        }

        String detail = buildDetail(word);

        doInsertWords(word, detail);

        Toast.makeText(DictUserActivity.this, "添加生词成功！", Toast.LENGTH_SHORT).show();
    }


    private String buildDetail(String word) {
        return word + "  detail";
    }

    private void doInsertWords(String word, String detail) {
        ContentValues values = new ContentValues();
        values.put(Dict.Word.WORD, word);
        values.put(Dict.Word.DETAIL, detail);
        contentResolver.insert(Dict.Word.WORDS_URI, values);
    }

    private void search() {
        String key = ((EditText) findViewById(R.id.key)).getText().toString();

        Cursor cursor = doSearchWords(key);

        // 创建一个Bundle对象
        Bundle data = new Bundle();
        data.putSerializable(KEY_SEARCH_RESULT, convertCursorToList(cursor));
        Intent intent = new Intent(DictUserActivity.this, DictUserSearchResultActivity.class);
        intent.putExtras(data);
        startActivity(intent);
    }

    private Cursor doSearchWords(String key) {
        return contentResolver.query(Dict.Word.WORDS_URI, null, "word like ? or detail like ?", new String[]{"%" + key + "%", "%" + key + "%"}, null);
    }

    private ArrayList<Map<String, String>> convertCursorToList(Cursor cursor) {
        ArrayList<Map<String, String>> result = new ArrayList<>();
        if (null == cursor) {
            return result;
        }
        // 遍历Cursor结果集
        while (cursor.moveToNext()) {
            // 将结果集中的数据存入ArrayList中
            Map<String, String> map = new HashMap<>();
            // 取出查询记录中第2列、第3列的值
            map.put(Dict.Word.WORD, cursor.getString(1));
            map.put(Dict.Word.DETAIL, cursor.getString(2));
            result.add(map);
        }
        return result;
    }
}