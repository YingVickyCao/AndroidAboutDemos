package com.hades.android.example.android_about_demos.app_component.cp.system.media;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.hades.android.example.android_about_demos.R;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/*
 <!-- 授予读取外部存储设备的的访问权限 -->
 <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>
 <!-- 授予写入外部存储设备的的访问权限 -->
 <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
 */
public class MediaActivity extends Activity {
    ListView show;
    ArrayList<String> names = new ArrayList<>();
    ArrayList<String> descs = new ArrayList<>();
    ArrayList<String> fileNames = new ArrayList<>();
    private View mRoot;
    private RxPermissions rxPermissions;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cp_media);

        rxPermissions = new RxPermissions(this);
        rxPermissions.setLogging(true);

        checkPermission();

        findViewById(R.id.add).setOnClickListener(v -> add());
        findViewById(R.id.view).setOnClickListener(v -> view());

        show = findViewById(R.id.show);
        show.setOnItemClickListener(this::onItemClick);

        mRoot = findViewById(R.id.root);
    }

    private void view() {
        // 清空names、descs、fileNames集合里原有的数据
        names.clear();
        descs.clear();
        fileNames.clear();
        // 通过ContentResolver查询所有图片信息
        Cursor cursor = getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, null, null, null);
        while (cursor.moveToNext()) {
            // 获取图片的显示名
            String name = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME));
            // 获取图片的详细描述
            String desc = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DESCRIPTION));
            // 获取图片的保存位置的数据
            byte[] data = cursor.getBlob(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            // 将图片名添加到names集合中
            names.add(name);
            // 将图片描述添加到descs集合中
            descs.add(desc);
            // 将图片保存路径添加到fileNames集合中
            fileNames.add(new String(data, 0, data.length - 1));
        }
        // 创建一个List集合，List集合的元素是Map
        List<Map<String, Object>> listItems = new ArrayList<>();
        // 将names、descs两个集合对象的数据转换到Map集合中
        for (int i = 0; i < names.size(); i++) {
            Map<String, Object> listItem = new HashMap<>();
            listItem.put("name", names.get(i));
            listItem.put("desc", descs.get(i));
            listItems.add(listItem);
        }
        // 创建一个SimpleAdapter
        SimpleAdapter simpleAdapter = new SimpleAdapter(MediaActivity.this, listItems, R.layout.line, new String[]{"name", "desc"}
                , new int[]{R.id.name, R.id.desc});
        // 为show ListView组件设置Adapter
        show.setAdapter(simpleAdapter);
    }

    private void add() {
        // 创建ContentValues对象，准备插入数据
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.DISPLAY_NAME, "bg2");
        values.put(MediaStore.Images.Media.DESCRIPTION, "金塔");
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
        // 插入数据，返回所插入数据对应的Uri
        Uri uri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
        // 加载应用程序下的jinta图片
        Bitmap bitmap = BitmapFactory.decodeResource(MediaActivity.this.getResources(), R.drawable.bg2);
        System.out.println("======");
        OutputStream os = null;
        try {
            // 获取刚插入的数据的Uri对应的输出流
            os = getContentResolver().openOutputStream(uri); // ①
            // 将bitmap图片保存到Uri对应的数据节点中
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, os);
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onItemClick(AdapterView<?> parent, View source, int position, long id) {
        // 加载view.xml界面布局代表的视图
        View viewDialog = getLayoutInflater().inflate(R.layout.view, null);
        // 获取viewDialog中ID为image的组件
        ImageView image = (ImageView) viewDialog
                .findViewById(R.id.image);
        // 设置image显示指定图片
        image.setImageBitmap(BitmapFactory.decodeFile(
                fileNames.get(position)));
        // 使用对话框显示用户单击的图片
        new AlertDialog.Builder(MediaActivity.this)
                .setView(viewDialog).setPositiveButton("确定", null)
                .show();
    }


    private void checkPermission() {
        if (!rxPermissions.isGranted(Manifest.permission.WRITE_EXTERNAL_STORAGE) || !rxPermissions.isGranted(Manifest.permission.READ_EXTERNAL_STORAGE)) {
            askUser2GrantPermissions();
            return;
        }
    }

    private void requestPermission() {
        rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE).subscribe(new Observer<Boolean>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(Boolean granted) {
                if (granted) {
                    Toast.makeText(MediaActivity.this, "permission available", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MediaActivity.this, "permission not granted", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    private void askUser2GrantPermissions() {
        rxPermissions.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(shouldShowRequestPermissionRationale -> {
                    if (shouldShowRequestPermissionRationale) {
                        Snackbar.make(mRoot, R.string.permission_rationale_4_send_message,
                                Snackbar.LENGTH_INDEFINITE)
                                .setAction(R.string.ok, view -> requestPermission())
                                .show();
                    } else {
                        requestPermission();
                    }
                });
    }

}