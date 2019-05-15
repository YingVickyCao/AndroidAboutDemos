package com.hades.example.android.app_component._intent_and_intent_filter._action_category;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.TextView;

import androidx.loader.content.CursorLoader;

import com.hades.example.android.R;
import com.hades.example.android.lib.base.PermissionActivity;

public class SystemActionActivity extends PermissionActivity {
    final int REQUEST_CODE_PICK_CONTACT = 1000;

    private TextView contactName;
    private TextView phoneNum;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intent_action_system);

        contactName = findViewById(R.id.contactName);
        phoneNum = findViewById(R.id.contactPhone);

        findViewById(R.id.actionGetContent).setOnClickListener(v -> actionGetContent());
        findViewById(R.id.backToSystemHome).setOnClickListener(v -> backToSystemHome());
    }

    @Override
    protected void requestPermission() {
        checkPermission("Request permission for READ_CONTACTS", Manifest.permission.READ_CONTACTS);
    }

    private void actionGetContent() {
//         <uses-permission android:name="android.permission.READ_CONTACTS" />
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_PICK);
        intent.setType(ContactsContract.Contacts.CONTENT_TYPE);//vnd.android.cursor.dir/contact
        startActivityForResult(intent, REQUEST_CODE_PICK_CONTACT);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_PICK_CONTACT && resultCode == Activity.RESULT_OK) {
            handlePhoneData(data);
        }
    }

    private void handlePhoneData(Intent data) {
        Uri contactData = data.getData();
        if (null == contactData) {
            return;
        }
        CursorLoader cursorLoader = new CursorLoader(SystemActionActivity.this, contactData, null, null, null, null);
        Cursor cursor = cursorLoader.loadInBackground();
        if (null == cursor) {
            return;
        }

        new Thread(() -> {
            if (cursor.moveToFirst()) {
                String contactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                String contactName = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME));

                String phoneNumTemp = "此联系人暂未输入电话号码";
                Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + contactId, null, null);
                if (null != phones) {
                    if (phones.moveToFirst()) {
                        phoneNumTemp = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    }
                    phones.close();
                }

                final String phoneNum = phoneNumTemp;

                runOnUiThread(() -> {
                    SystemActionActivity.this.contactName.setText(contactName);
                    SystemActionActivity.this.phoneNum.setText(phoneNum);
                });
            }

            cursor.close();
        }).start();
    }

    private void backToSystemHome() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);
    }
}