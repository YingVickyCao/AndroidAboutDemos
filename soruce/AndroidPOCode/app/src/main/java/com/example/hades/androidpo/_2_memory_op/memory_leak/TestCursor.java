package com.example.hades.androidpo._2_memory_op.memory_leak;

import android.database.Cursor;

public class TestCursor {
    public void test() {
        Cursor cursor = null;
        try {
//            cursor =/
            //.....
        } catch (Exception ex) {

        } finally {
            if (cursor != null) {
                cursor.close();
                cursor = null;
            }
        }
    }
}
