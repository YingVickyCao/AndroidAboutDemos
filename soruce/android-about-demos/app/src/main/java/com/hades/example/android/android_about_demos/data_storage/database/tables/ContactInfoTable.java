package com.hades.example.android.android_about_demos.data_storage.database.tables;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import com.hades.example.android.android_about_demos.data_storage.database.bean.ContactInfo;

import java.util.ArrayList;
import java.util.List;

public class ContactInfoTable extends BaseTable {
    private static String TAG = ContactInfoTable.class.getSimpleName();

    public static final String TABLE_NAME = "Table_ContactInfo";
    private static final String KEY_CONTACT_ID = "num";
    private static final String KEY_CONTACT_NAME = "name";
    private static final String KEY_CONTACT_PHONE = "phone";

    private SQLiteStatement mSQLiteStatement = null;

    public static final String TABLE_CREATE = "create table if not exists "
            + TABLE_NAME + " (" + KEY_CONTACT_ID
            + " LONG primary key , " + KEY_CONTACT_NAME + " TEXT," + KEY_CONTACT_PHONE + " TEXT "
            + ");";

    public final String STR_INSERT_STATEMENT_CONTACTS = "insert into " + TABLE_NAME
            + "(" + KEY_CONTACT_ID
            + "," + KEY_CONTACT_NAME
            + "," + KEY_CONTACT_PHONE
            + ") values(?,?,?)";

    public ContactInfoTable(Context context) {
        super(context);
    }

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    @Override
    public String[] getAllKey() {
        return new String[]{
                TABLE_NAME + "." + KEY_CONTACT_ID,
                TABLE_NAME + "." + KEY_CONTACT_NAME,
                TABLE_NAME + "." + KEY_CONTACT_PHONE
        };
    }

    @Override
    public SQLiteStatement getSQLiteStatement() {
        if (null == mSQLiteStatement) {
            mSQLiteStatement = getSqliteDB().compileStatement(STR_INSERT_STATEMENT_CONTACTS);
        }
        return mSQLiteStatement;
    }

    public boolean insertContactInfoForStat(ContactInfo info) {
        getSQLiteStatement().clearBindings();
        getSQLiteStatement().bindLong(1, info.getId());
        getSQLiteStatement().bindString(2, info.getName());
        getSQLiteStatement().bindString(3, info.getPhone());
        return getSQLiteStatement().executeInsert() > 0;
    }

    public boolean insertContactInfo(ContactInfo info) {
        return getSqliteDB().insert(TABLE_NAME, null, transContactInfo(info)) > 0;
    }

    public boolean updateContactInfo(ContactInfo info) {
        return getSqliteDB().update(TABLE_NAME, transContactInfo(info), kv(KEY_CONTACT_ID, info.getId()), null) > 0;
    }

    public boolean deleteContactInfo(ContactInfo info) {
        return getSqliteDB().delete(TABLE_NAME, kv(KEY_CONTACT_ID, info.getId()), null) > 0;
    }

    public List<ContactInfo> getAllContacts() {
        List<ContactInfo> songList = new ArrayList<>();
        Cursor cursors = null;
        cursors = getSqliteDB().query(true,
                TABLE_NAME,
                new String[]{KEY_CONTACT_ID, KEY_CONTACT_NAME, KEY_CONTACT_PHONE},
                null
                , null, null, null, null, null);
        if (null != cursors && cursors.moveToFirst()) {
            do {
                songList.add(transContactCursor(cursors));
            } while (cursors.moveToNext());
        }
        return songList;
    }

    private ContactInfo transContactCursor(Cursor cursor) {
        long id = cursor.getLong(cursor.getColumnIndex(KEY_CONTACT_ID));
        String name = cursor.getString(cursor.getColumnIndex(KEY_CONTACT_NAME));
        String number = cursor.getString(cursor.getColumnIndex(KEY_CONTACT_PHONE));
        return new ContactInfo(id, name, number);
    }

    private ContentValues transContactInfo(ContactInfo info) {
        ContentValues value = new ContentValues();
        value.put(KEY_CONTACT_ID, info.getId());
        value.put(KEY_CONTACT_NAME, info.getName());
        value.put(KEY_CONTACT_PHONE, info.getPhone());
        return value;
    }
}