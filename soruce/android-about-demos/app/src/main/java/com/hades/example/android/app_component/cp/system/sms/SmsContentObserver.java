package com.hades.example.android.app_component.cp.system.sms;

import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;

/**
 * https://www.cnblogs.com/zsychanpin/p/7242147.html
 */
final class SmsContentObserver extends ContentObserver {
    private static final String TAG = SmsContentObserver.class.getSimpleName();
    private SMSActivity mSMSActivity;

    public SmsContentObserver(SMSActivity SMSActivity, Handler handler) {
        super(handler);
        mSMSActivity = SMSActivity;
    }

    public void onChange(boolean selfChange) {
        Log.d(TAG, "onChange: ");
        /**
         * content://sms/inbox 收件箱
         */
        Cursor cursor = mSMSActivity.getContentResolver().query(Uri.parse("content://sms/inbox"), null, null, null, null);
        if (null == cursor) {
            return;
        }
        // 遍历查询得到的结果集，即可获取用户正在发送的短信
        while (cursor.moveToNext()) {
            StringBuilder sb = new StringBuilder();
            sb.append("address=").append(cursor.getString(cursor.getColumnIndex("address")));
            // 获取短信的标题
            sb.append(";subject=").append(cursor.getString(cursor.getColumnIndex("subject")));
            // 获取短信的内容
            sb.append(";body=").append(cursor.getString(cursor.getColumnIndex("body")));
            // 获取短信的发送时间
            sb.append(";time=").append(cursor.getLong(cursor.getColumnIndex("date")));
            Log.d(TAG, "onChange: " + "发送短信：" + sb.toString());
        }
    }
}
