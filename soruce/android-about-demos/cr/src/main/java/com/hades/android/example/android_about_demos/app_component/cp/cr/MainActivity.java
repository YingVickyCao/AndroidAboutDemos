package com.hades.android.example.android_about_demos.app_component.cp.cr;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class MainActivity extends Activity {

//    private ContentResolver contentResolver;
//    private Uri uri = Uri.parse("content://com.hades.android.example.android_about_demos.app_component.cp.DictContentProvider/");
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        contentResolver = getContentResolver();
//
//        findViewById(R.id.query).setOnClickListener(v -> query());
//        findViewById(R.id.insert).setOnClickListener(v -> insert());
//        findViewById(R.id.update).setOnClickListener(v -> update());
//        findViewById(R.id.delete).setOnClickListener(v -> delete());
//    }
//
//    public void query() {
//        // TODO: 05/07/2018
//        // 调用ContentResolver的query()方法
//        // 实际返回的是该Uri对应的ContentProvider的query()的返回值
//        Cursor c = contentResolver.query(uri, null, "query_where", null, null);
//        Toast.makeText(this, "远程ContentProvide返回的Cursor为：" + c, Toast.LENGTH_SHORT).show();
//    }
//
//    public void insert() {
//        ContentValues values = new ContentValues();
//        values.put("name", "fkjava");
//        Uri newUri = contentResolver.insert(uri, values);
//        Toast.makeText(this, "远程ContentProvide新插入记录的Uri为：" + newUri, Toast.LENGTH_SHORT).show();
//    }
//
//    public void update() {
//        ContentValues values = new ContentValues();
//        values.put("name", "fkjava");
//        int count = contentResolver.update(uri, values, "update_where", null);
//        Toast.makeText(this, "远程ContentProvide更新记录数为：" + count, Toast.LENGTH_SHORT).show();
//    }
//
//    public void delete() {
//        int count = contentResolver.delete(uri, "delete_where", null);
//        Toast.makeText(this, "远程ContentProvide删除记录数为：" + count, Toast.LENGTH_SHORT).show();
//    }

    ContentResolver contentResolver;
    Button insert = null;
    Button search = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        // 获取系统的ContentResolver对象
        contentResolver = getContentResolver();
        insert = (Button) findViewById(R.id.insert);
        search = (Button) findViewById(R.id.search);
        // 为insert按钮的单击事件绑定事件监听器
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View source) {
                // 获取用户输入
                String word = ((EditText) findViewById(R.id.word))
                        .getText().toString();
                String detail = ((EditText) findViewById(R.id.detail))
                        .getText().toString();
                // 插入生词记录
                ContentValues values = new ContentValues();
                values.put(Words.Word.WORD, word);
                values.put(Words.Word.DETAIL, detail);
                contentResolver.insert(
                        Words.Word.DICT_CONTENT_URI, values);
                // 显示提示信息
                Toast.makeText(MainActivity.this, "添加生词成功！"
                        , Toast.LENGTH_SHORT).show();
            }
        });

        // 为search按钮的单击事件绑定事件监听器
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View source) {
                // 获取用户输入
                String key = ((EditText) findViewById(R.id.key))
                        .getText().toString();
                // 执行查询
                Cursor cursor = contentResolver.query(
                        Words.Word.DICT_CONTENT_URI, null,
                        "word like ? or detail like ?", new String[]{
                                "%" + key + "%", "%" + key + "%"}, null);
                // 创建一个Bundle对象
                Bundle data = new Bundle();
                data.putSerializable("data", converCursorToList(cursor));
                // 创建一个Intent
                Intent intent = new Intent(MainActivity.this,
                        ResultActivity.class);
                intent.putExtras(data);
                // 启动Activity
                startActivity(intent);
            }
        });
    }

    private ArrayList<Map<String, String>> converCursorToList(Cursor cursor) {
        ArrayList<Map<String, String>> result = new ArrayList<>();
        // 遍历Cursor结果集
        while (cursor.moveToNext()) {
            // 将结果集中的数据存入ArrayList中
            Map<String, String> map = new HashMap<>();
            // 取出查询记录中第2列、第3列的值
            map.put(Words.Word.WORD, cursor.getString(1));
            map.put(Words.Word.DETAIL, cursor.getString(2));
            result.add(map);
        }
        return result;
    }
}