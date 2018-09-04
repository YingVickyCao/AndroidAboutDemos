package com.hades.android.example.android_about_demos.data_storage.database.test;

import android.content.Context;
import android.util.Log;

import com.hades.android.example.android_about_demos.data_storage.database.bean.ContactInfo;
import com.hades.android.example.android_about_demos.data_storage.database.tables.ContactInfoTable;

import java.util.ArrayList;
import java.util.List;

public class ContactsManager {
    private static ContactsManager mContactsManager = null;
    private static Context mContext = null;
    private ArrayList<ContactInfo> contactInfos = new ArrayList<ContactInfo>();
    private ContactInfoTable mContactInfoTable = null;
    private final String TAG = "ContactsManager";

    public static void programStart(Context ctx) {
        mContext = ctx;
    }

    public static synchronized ContactsManager getInstance() {
        if (null == mContactsManager) {
            mContactsManager = new ContactsManager();
        }
        return mContactsManager;
    }

    public ContactsManager() {
        mContactInfoTable = new ContactInfoTable(mContext);
    }

    public ArrayList<ContactInfo> insertContactInfos() {
        long timeStart = System.currentTimeMillis();

        mContactInfoTable.getSqliteDB().beginTransaction();
        try {
            for (int i = 1; i <= 10000; i++) {
                ContactInfo info = new ContactInfo(i, "姓名" + i, "1389832" + (0000 + i));
                contactInfos.add(info);
                boolean insert = mContactInfoTable.insertContactInfoForStat(info);
                Log.d(TAG, "insert Contact phone :" + insert);
            }
            mContactInfoTable.getSqliteDB().setTransactionSuccessful();
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        } finally {
            mContactInfoTable.getSqliteDB().endTransaction();
        }

        long timeEnd = System.currentTimeMillis();
        Log.i(TAG, "insertContactInfos: " + (timeEnd - timeStart));
        return contactInfos;
    }

    public List<ContactInfo> readContactInfo() {
        long timeStart = System.currentTimeMillis();

        List<ContactInfo> songList = mContactInfoTable.getAllContacts();
        long timeEnd = System.currentTimeMillis();

        Log.i(TAG, "readContactInfo: " + (timeEnd - timeStart) + ",size=" + songList.size());
        return songList;
    }
}