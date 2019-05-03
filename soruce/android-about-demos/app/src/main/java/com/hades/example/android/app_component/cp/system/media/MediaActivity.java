package com.hades.example.android.app_component.cp.system.media;

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
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.hades.example.android.R;
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
    private ListView mShowListView;
    private List<MediaInfo> mData = new ArrayList<>();
    List<Map<String, Object>> listItems = new ArrayList<>();
    private SimpleAdapter mAdapter;

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

        mShowListView = findViewById(R.id.tableContentList);
        mShowListView.setOnItemClickListener(this::onItemClick);
        mAdapter = new SimpleAdapter(MediaActivity.this, listItems, R.layout.cp_media_resarch_result, new String[]{"name", "desc"}, new int[]{R.id.name, R.id.desc});
        mShowListView.setAdapter(mAdapter);

        mRoot = findViewById(R.id.root);
    }

    private void view() {
        mData.clear();
        searchImagesInfo();
        convertData();
        mAdapter.notifyDataSetChanged();
    }

    private void searchImagesInfo() {
        // 通过ContentResolver查询所有图片信息
        Cursor cursor = getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, null, null, null);
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME));
            String desc = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DESCRIPTION));
            // 获取图片的保存位置的数据
            byte[] data = cursor.getBlob(cursor.getColumnIndex(MediaStore.Images.Media.DATA));

            MediaInfo mediaInfo = new MediaInfo(name, desc, new String(data, 0, data.length - 1));
            mData.add(mediaInfo);
        }
        cursor.close();
    }

    private void convertData() {
        listItems.clear();
        for (int i = 0; i < mData.size(); i++) {
            Map<String, Object> listItem = new HashMap<>();
            listItem.put("name", mData.get(i).getName());
            listItem.put("desc", mData.get(i).getDesc());
            listItems.add(listItem);
        }
    }

    private void add() {
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.DISPLAY_NAME, "bg2");
        values.put(MediaStore.Images.Media.DESCRIPTION, "金塔");
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");

        // 插入数据，返回所插入数据对应的Uri
        Uri uri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
        if (null == uri) {
            return;
        }

        Bitmap bitmap = BitmapFactory.decodeResource(MediaActivity.this.getResources(), R.drawable.bg2);
        OutputStream os = null;
        try {
            // 获取刚插入的数据的Uri对应的输出流
            os = getContentResolver().openOutputStream(uri);
            // 将bitmap图片保存到Uri对应的数据节点中
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, os);
            if (null != os) {
                os.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onItemClick(AdapterView<?> parent, View source, int position, long id) {
        View viewDialog = getLayoutInflater().inflate(R.layout.cp_media_view_one_image, null);
        ImageView image = viewDialog.findViewById(R.id.imageView);
        image.setImageBitmap(BitmapFactory.decodeFile(mData.get(position).getFileName()));
        new AlertDialog.Builder(MediaActivity.this).setView(viewDialog).setPositiveButton("确定", null).show();
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