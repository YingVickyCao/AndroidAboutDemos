package com.hades.android.example.android_about_demos.app_component.cp.cr;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends Activity {

    private ContentResolver contentResolver;
    private Uri uri = Uri.parse("content://com.hades.android.example.android_about_demos.app_component.cp.FirstContentProvider/");

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contentResolver = getContentResolver();
    }

    public void query(View source) {
        // TODO: 05/07/2018
        // 调用ContentResolver的query()方法
        // 实际返回的是该Uri对应的ContentProvider的query()的返回值
        Cursor c = contentResolver.query(uri, null, "query_where", null, null);
        Toast.makeText(this, "远程ContentProvide返回的Cursor为：" + c, Toast.LENGTH_SHORT).show();
    }

    public void insert(View source) {
        ContentValues values = new ContentValues();
        values.put("name", "fkjava");
        Uri newUri = contentResolver.insert(uri, values);
        Toast.makeText(this, "远程ContentProvide新插入记录的Uri为：" + newUri, Toast.LENGTH_SHORT).show();
    }

    public void update(View source) {
        ContentValues values = new ContentValues();
        values.put("name", "fkjava");
        int count = contentResolver.update(uri, values, "update_where", null);
        Toast.makeText(this, "远程ContentProvide更新记录数为：" + count, Toast.LENGTH_SHORT).show();
    }

    public void delete(View source) {
        int count = contentResolver.delete(uri, "delete_where", null);
        Toast.makeText(this, "远程ContentProvide删除记录数为：" + count, Toast.LENGTH_SHORT).show();
    }
}