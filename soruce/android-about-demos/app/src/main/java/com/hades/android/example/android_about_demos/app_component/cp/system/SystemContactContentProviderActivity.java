package com.hades.android.example.android_about_demos.app_component.cp.system;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
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
import android.support.design.widget.Snackbar;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.EditText;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.hades.android.example.android_about_demos.R;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.ArrayList;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class SystemContactContentProviderActivity extends Activity {
    private RxPermissions rxPermissions;
    private View mRoot;
    private boolean mIsHasPermission = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cp_system_contact_cp);

        mRoot = findViewById(R.id.root);

        rxPermissions = new RxPermissions(this);
        rxPermissions.setLogging(true);

        findViewById(R.id.checkPermission).setOnClickListener(v -> checkPermission());
        findViewById(R.id.search).setOnClickListener(v -> search());
        findViewById(R.id.add).setOnClickListener(v -> add());
    }

    private String parseContactId(Cursor cursor) {
        return cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
    }

    private String parseContactName(Cursor cursor) {
        return cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
    }

    private ArrayList<String> parseContactPhones(String contactId) {
        Cursor phonesCursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null
                , ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + contactId, null, null);

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

    private void requestPermission() {
        rxPermissions.request(Manifest.permission.WRITE_CONTACTS, Manifest.permission.READ_CONTACTS).subscribe(new Observer<Boolean>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(Boolean granted) {
                mIsHasPermission = granted;
                if (granted) {
                    Toast.makeText(SystemContactContentProviderActivity.this, "permission available", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SystemContactContentProviderActivity.this, "permission not granted", Toast.LENGTH_SHORT).show();
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

    private void search() {
        if (!mIsHasPermission) {
            Toast.makeText(this, "Request permission first", Toast.LENGTH_SHORT).show();
            return;
        }

        final ArrayList<String> names = new ArrayList<>();
        final ArrayList<ArrayList<String>> details = new ArrayList<>();
        ContactInfo contactInfo = new ContactInfo();

        // 使用ContentResolver查找联系人数据
        Cursor cursor = getContentResolver().query(
                ContactsContract.Contacts.CONTENT_URI, null, null,
                null, null);
        // 遍历查询结果，获取系统中所有联系人
        while (cursor.moveToNext()) {
            String contactId = parseContactId(cursor);
            contactInfo.setContactId(contactId);

            String name = parseContactName(cursor);
            names.add(name);
            contactInfo.setName(name);

            ArrayList<String> phones = parseContactPhones(contactId);
            contactInfo.setPhones(phones);

            // 使用ContentResolver查找联系人的E-mail地址
            Cursor emails = getContentResolver().query(
                    ContactsContract.CommonDataKinds.Email.CONTENT_URI,
                    null, ContactsContract.CommonDataKinds.Email
                            .CONTACT_ID + " = " + contactId, null, null);

            // 遍历查询结果，获取该联系人的多个E-mail地址
            while (emails.moveToNext()) {
                // 获取查询结果中E-mail地址列中数据
                String emailAddress = emails.getString(emails
                        .getColumnIndex(ContactsContract
                                .CommonDataKinds.Email.DATA));
                phones.add("邮件地址：" + emailAddress);
            }
            emails.close();

            details.add(phones);
        }
        cursor.close();
        // 加载result.xml界面布局代表的视图
        View resultDialog = getLayoutInflater().inflate(
                R.layout.result, null);
        // 获取resultDialog中ID为list的ExpandableListView
        ExpandableListView list = (ExpandableListView) resultDialog
                .findViewById(R.id.list);
        // 创建一个ExpandableListAdapter对象
        ExpandableListAdapter adapter =
                new BaseExpandableListAdapter() {
                    // 获取指定组位置、指定子列表项处的子列表项数据
                    @Override
                    public Object getChild(int groupPosition,
                                           int childPosition) {
                        return details.get(groupPosition).get(
                                childPosition);
                    }

                    @Override
                    public long getChildId(int groupPosition,
                                           int childPosition) {
                        return childPosition;
                    }

                    @Override
                    public int getChildrenCount(int groupPosition) {
                        return details.get(groupPosition).size();
                    }

                    private TextView getTextView() {
                        AbsListView.LayoutParams lp = new AbsListView
                                .LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT
                                , 64);
                        TextView textView = new TextView(
                                SystemContactContentProviderActivity.this);
                        textView.setLayoutParams(lp);
                        textView.setGravity(Gravity.CENTER_VERTICAL
                                | Gravity.LEFT);
                        textView.setPadding(36, 0, 0, 0);
                        textView.setTextSize(20);
                        return textView;
                    }

                    // 该方法决定每个子选项的外观
                    @Override
                    public View getChildView(int groupPosition,
                                             int childPosition, boolean isLastChild,
                                             View convertView, ViewGroup parent) {
                        TextView textView = getTextView();
                        textView.setText(getChild(groupPosition,
                                childPosition).toString());
                        return textView;
                    }

                    // 获取指定组位置处的组数据
                    @Override
                    public Object getGroup(int groupPosition) {
                        return names.get(groupPosition);
                    }

                    @Override
                    public int getGroupCount() {
                        return names.size();
                    }

                    @Override
                    public long getGroupId(int groupPosition) {
                        return groupPosition;
                    }

                    // 该方法决定每个组选项的外观
                    @Override
                    public View getGroupView(int groupPosition,
                                             boolean isExpanded, View convertView,
                                             ViewGroup parent) {
                        TextView textView = getTextView();
                        textView.setText(getGroup(groupPosition)
                                .toString());
                        return textView;
                    }

                    @Override
                    public boolean isChildSelectable(int groupPosition,
                                                     int childPosition) {
                        return true;
                    }

                    @Override
                    public boolean hasStableIds() {
                        return true;
                    }
                };
        // 为ExpandableListView设置Adapter对象
        list.setAdapter(adapter);
        // 使用对话框来显示查询结果
        new AlertDialog.Builder(SystemContactContentProviderActivity.this)
                .setView(resultDialog).setPositiveButton("确定", null)
                .show();
    }

    private void checkPermission() {
        if (!rxPermissions.isGranted(Manifest.permission.WRITE_CONTACTS) || !rxPermissions.isGranted(Manifest.permission.READ_CONTACTS)) {
            askUser2GrantPermissions();
            return;
        }else {
            mIsHasPermission = true;
        }
    }
    
    private void add() {
        if (!mIsHasPermission) {
            Toast.makeText(this, "Request permission first", Toast.LENGTH_SHORT).show();
            return;
        }

        // 获取程序界面中的三个文本框的内容
        String name = ((EditText) findViewById(R.id.name))
                .getText().toString();
        String phone = ((EditText) findViewById(R.id.phone))
                .getText().toString();
        String email = ((EditText) findViewById(R.id.email))
                .getText().toString();
        // 创建一个空的ContentValues
        ContentValues values = new ContentValues();
        // 向RawContacts.CONTENT_URI执行一个空值插入
        // 目的是获取系统返回的rawContactId
        /**
         * ERROR:java.lang.SecurityException: Permission Denial: opening provider com.android.providers.contacts.ContactsProvider2
         * Solution:
         */


//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
//            int hasWriteContactsPermission = checkSelfPermission(Manifest.permission.READ_CONTACTS);
//            if (hasWriteContactsPermission != PackageManager.PERMISSION_GRANTED){
//                requestPermissions(new String[]{Manifest.permission.WRITE_CONTACTS},REQUEST_CODE_ASK_PERMISSIONS);
//                return;
//            }


        Uri rawContactUri = getContentResolver().insert(
                ContactsContract.RawContacts.CONTENT_URI, values);
        long rawContactId = ContentUris.parseId(rawContactUri);
        values.clear();
        values.put(Data.RAW_CONTACT_ID, rawContactId);
        // 设置内容类型
        values.put(Data.MIMETYPE, StructuredName.CONTENT_ITEM_TYPE);
        // 设置联系人名字
        values.put(StructuredName.GIVEN_NAME, name);
        // 向联系人URI添加联系人名字
        getContentResolver().insert(android.provider.ContactsContract
                .Data.CONTENT_URI, values);
        values.clear();
        values.put(Data.RAW_CONTACT_ID, rawContactId);
        values.put(Data.MIMETYPE, Phone.CONTENT_ITEM_TYPE);
        // 设置联系人的电话号码
        values.put(Phone.NUMBER, phone);
        // 设置电话类型
        values.put(Phone.TYPE, Phone.TYPE_MOBILE);
        // 向联系人电话号码URI添加电话号码
        getContentResolver().insert(android.provider.ContactsContract
                .Data.CONTENT_URI, values);
        values.clear();
        values.put(Data.RAW_CONTACT_ID, rawContactId);
        values.put(Data.MIMETYPE, Email.CONTENT_ITEM_TYPE);
        // 设置联系人的E-mail地址
        values.put(Email.DATA, email);
        // 设置该电子邮件的类型
        values.put(Email.TYPE, Email.TYPE_WORK);
        // 向联系人E-mail URI添加E-mail数据
        getContentResolver().insert(android.provider.ContactsContract
                .Data.CONTENT_URI, values);
        Toast.makeText(SystemContactContentProviderActivity.this, "联系人数据添加成功",
                Toast.LENGTH_SHORT).show();
    }

    private void doAdd() {
        // 获取程序界面中的三个文本框的内容
        String name = ((EditText) findViewById(R.id.name))
                .getText().toString();
        String phone = ((EditText) findViewById(R.id.phone))
                .getText().toString();
        String email = ((EditText) findViewById(R.id.email))
                .getText().toString();
        // 创建一个空的ContentValues
        ContentValues values = new ContentValues();
        // 向RawContacts.CONTENT_URI执行一个空值插入
        // 目的是获取系统返回的rawContactId
        Uri rawContactUri = getContentResolver().insert(
                ContactsContract.RawContacts.CONTENT_URI, values);
        long rawContactId = ContentUris.parseId(rawContactUri);
        values.clear();
        values.put(Data.RAW_CONTACT_ID, rawContactId);
        // 设置内容类型
        values.put(Data.MIMETYPE, StructuredName.CONTENT_ITEM_TYPE);
        // 设置联系人名字
        values.put(StructuredName.GIVEN_NAME, name);
        // 向联系人URI添加联系人名字
        getContentResolver().insert(android.provider.ContactsContract
                .Data.CONTENT_URI, values);
        values.clear();
        values.put(Data.RAW_CONTACT_ID, rawContactId);
        values.put(Data.MIMETYPE, Phone.CONTENT_ITEM_TYPE);
        // 设置联系人的电话号码
        values.put(Phone.NUMBER, phone);
        // 设置电话类型
        values.put(Phone.TYPE, Phone.TYPE_MOBILE);
        // 向联系人电话号码URI添加电话号码
        getContentResolver().insert(android.provider.ContactsContract
                .Data.CONTENT_URI, values);
        values.clear();
        values.put(Data.RAW_CONTACT_ID, rawContactId);
        values.put(Data.MIMETYPE, Email.CONTENT_ITEM_TYPE);
        // 设置联系人的E-mail地址
        values.put(Email.DATA, email);
        // 设置该电子邮件的类型
        values.put(Email.TYPE, Email.TYPE_WORK);
        // 向联系人E-mail URI添加E-mail数据
        getContentResolver().insert(android.provider.ContactsContract
                .Data.CONTENT_URI, values);
        Toast.makeText(SystemContactContentProviderActivity.this, "联系人数据添加成功",
                Toast.LENGTH_SHORT).show();
    }
}
