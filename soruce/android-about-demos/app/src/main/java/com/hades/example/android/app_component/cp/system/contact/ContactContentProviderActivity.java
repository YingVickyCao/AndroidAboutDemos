package com.hades.example.android.app_component.cp.system.contact;

import android.Manifest;
import android.app.Activity;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.CommonDataKinds.StructuredName;
import android.provider.ContactsContract.RawContacts.Data;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.hades.example.android.R;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.ArrayList;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/*
 <!-- 授予读联系人ContentProvider的权限 -->
 <uses-permission android:name="android.permission.READ_CONTACTS" />
 <!-- 授予写联系人ContentProvider的权限 -->
 <uses-permission android:name="android.permission.WRITE_CONTACTS" />
 */
public class ContactContentProviderActivity extends Activity {
    private RxPermissions rxPermissions;
    private View mRoot;
    private boolean mIsHasPermission = false;

    private ListView list;
    private ContactAdapter mAdapter;

    final ArrayList<ContactInfo> mData = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cp_system_contact_cp);

        mRoot = findViewById(R.id.root);
        list = findViewById(R.id.list);
        initView();

        rxPermissions = new RxPermissions(this);
        rxPermissions.setLogging(true);

        findViewById(R.id.checkPermission).setOnClickListener(v -> checkPermission());
        findViewById(R.id.query).setOnClickListener(v -> search());
        findViewById(R.id.add).setOnClickListener(v -> add());
    }

    private void checkPermission() {
        if (!rxPermissions.isGranted(Manifest.permission.WRITE_CONTACTS) || !rxPermissions.isGranted(Manifest.permission.READ_CONTACTS)) {
            askUser2GrantPermissions();
            return;
        } else {
            mIsHasPermission = true;
        }
    }

    private void requestPermission() {
        rxPermissions.request(Manifest.permission.WRITE_CONTACTS, Manifest.permission.READ_CONTACTS).subscribe(new Observer<Boolean>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(Boolean granted) {
                mIsHasPermission = granted;
                if (granted) {
                    Toast.makeText(ContactContentProviderActivity.this, "permission available", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ContactContentProviderActivity.this, "permission not granted", Toast.LENGTH_SHORT).show();
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
        rxPermissions.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_CONTACTS, Manifest.permission.READ_CONTACTS).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean shouldShowRequestPermissionRationale) throws Exception {
                if (shouldShowRequestPermissionRationale) {
                    Snackbar.make(mRoot, R.string.permission_rationale_4_send_message,
                            Snackbar.LENGTH_INDEFINITE)
                            .setAction(R.string.ok, new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    requestPermission();
                                }
                            })
                            .show();
                } else {
                    requestPermission();
                }
            }
        });
    }

    private void initView() {
        mAdapter = new ContactAdapter(mData, ContactContentProviderActivity.this);
        list.setAdapter(mAdapter);
    }

    private String parseContactId(Cursor cursor) {
        return cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
    }

    private String parseContactName(Cursor cursor) {
        return cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
    }

    private ArrayList<String> parseContactPhones(String contactId) {
        Cursor phonesCursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + contactId, null, null);
        if (null == phonesCursor) {
            return null;
        }

        ArrayList<String> phones = new ArrayList<>();
        while (phonesCursor.moveToNext()) {
            String phoneNumber = phonesCursor.getString(phonesCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            phones.add("电话号码：" + phoneNumber);
        }
        phonesCursor.close();
        return phones;
    }

    private ArrayList<String> parseEmails(String contactId) {
        ArrayList<String> emailArray = null;
        Cursor emails = getContentResolver().query(ContactsContract.CommonDataKinds.Email.CONTENT_URI, null, ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = " + contactId, null, null);
        // 遍历查询结果，获取该联系人的多个E-mail地址
        while (emails.moveToNext()) {
            // 获取查询结果中E-mail地址列中数据
            String emailAddress = emails.getString(emails
                    .getColumnIndex(ContactsContract
                            .CommonDataKinds.Email.DATA));
            if (null == emailArray) {
                emailArray = new ArrayList<>();
            }
            emailArray.add("邮件地址:" + emailAddress);
        }
        emails.close();

        return emailArray;
    }

    private void search() {
        if (!mIsHasPermission) {
            Toast.makeText(this, "Request permission first", Toast.LENGTH_SHORT).show();
            return;
        }

        mData.clear();

        // 使用ContentResolver查找联系人数据
        Cursor cursor = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        if (null == cursor) {
            return;
        }

        // 遍历查询结果，获取系统中所有联系人
        while (cursor.moveToNext()) {
            String contactId = parseContactId(cursor);
            ContactInfo contactInfo = new ContactInfo();
            contactInfo.setContactId(contactId);
            contactInfo.setName(parseContactName(cursor));
            contactInfo.setPhones(parseContactPhones(contactId));
            contactInfo.setEmails(parseEmails(contactId));

            mData.add(contactInfo);
        }

        cursor.close();

        if (null != mAdapter) {
            mAdapter.notifyDataSetChanged();
        }
    }

    private void add() {
        if (!mIsHasPermission) {
            Toast.makeText(this, "Request permission first", Toast.LENGTH_SHORT).show();
            return;
        }

        // 获取程序界面中的三个文本框的内容
        String name = ((EditText) findViewById(R.id.name)).getText().toString();
        String phone = ((EditText) findViewById(R.id.phone)).getText().toString();
        String email = ((EditText) findViewById(R.id.email)).getText().toString();

        if (name.isEmpty()) {
            return;
        }
        // 创建一个空的ContentValues
        ContentValues values = new ContentValues();
        // 向RawContacts.CONTENT_URI执行一个空值插入
        // 目的是获取系统返回的rawContactId
        Uri rawContactUri = getContentResolver().insert(ContactsContract.RawContacts.CONTENT_URI, values);
        long rawContactId = ContentUris.parseId(rawContactUri);

        insertName(rawContactId, name);
        insertPhone(rawContactId, phone);
        insertEmail(rawContactId, email);
        Toast.makeText(ContactContentProviderActivity.this, "联系人数据添加成功", Toast.LENGTH_SHORT).show();
    }

    private void insertName(long rawContactId, String name) {
        ContentValues values = new ContentValues();
        values.put(Data.RAW_CONTACT_ID, rawContactId);
        // 设置内容类型
        values.put(Data.MIMETYPE, StructuredName.CONTENT_ITEM_TYPE);
        // 设置联系人名字
        values.put(StructuredName.GIVEN_NAME, name);
        // 向联系人URI添加联系人名字
        getContentResolver().insert(android.provider.ContactsContract.Data.CONTENT_URI, values);
    }

    private void insertPhone(long rawContactId, String phone) {
        ContentValues values = new ContentValues();
        values.put(Data.RAW_CONTACT_ID, rawContactId);
        values.put(Data.MIMETYPE, Phone.CONTENT_ITEM_TYPE);
        // 设置联系人的电话号码
        values.put(Phone.NUMBER, phone);
        // 设置电话类型
        values.put(Phone.TYPE, Phone.TYPE_MOBILE);
        // 向联系人电话号码URI添加电话号码
        getContentResolver().insert(android.provider.ContactsContract.Data.CONTENT_URI, values);
    }

    private void insertEmail(long rawContactId, String email) {
        ContentValues values = new ContentValues();
        values.put(Data.RAW_CONTACT_ID, rawContactId);
        values.put(Data.MIMETYPE, Email.CONTENT_ITEM_TYPE);
        // 设置联系人的E-mail地址
        values.put(Email.DATA, email);
        // 设置该电子邮件的类型
        values.put(Email.TYPE, Email.TYPE_WORK);
        // 向联系人E-mail URI添加E-mail数据
        getContentResolver().insert(android.provider.ContactsContract.Data.CONTENT_URI, values);

    }
}
