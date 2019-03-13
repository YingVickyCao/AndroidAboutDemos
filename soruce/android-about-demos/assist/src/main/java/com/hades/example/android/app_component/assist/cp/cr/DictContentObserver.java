package com.hades.example.android.app_component.assist.cp.cr;

import android.database.ContentObserver;
import android.database.Cursor;
import android.os.Handler;
import android.util.Log;

/**
 * https://www.cnblogs.com/zsychanpin/p/7242147.html
 */
final class DictContentObserver extends ContentObserver {
    private static final String TAG = DictContentObserver.class.getSimpleName();

    private DictUserActivity mSMSActivity;

    public DictContentObserver(DictUserActivity activity, Handler handler) {
        super(handler);
        mSMSActivity = activity;
    }

    // TODO: 11/07/2018  DictContentObserver onChange not called
    public void onChange(boolean selfChange) {
        Log.d(TAG, "onChange: ");
        Cursor cursor = doSearchWords(null);
        ;
        if (null == cursor) {
            return;
        }
        while (cursor.moveToNext()) {
            Log.d(TAG, "onChange: " + cursor.toString());
        }
    }

    private Cursor doSearchWords(String key) {
        return mSMSActivity.getContentResolver().query(Dict.Word.WORDS_URI, null, null, null, null);
    }
}