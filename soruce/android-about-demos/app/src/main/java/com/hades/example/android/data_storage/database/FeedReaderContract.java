package com.hades.example.android.data_storage.database;

import android.provider.BaseColumns;

public class FeedReaderContract {
    private FeedReaderContract() {
    }

    public static class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "table1";
        public static final String TABLE_NAME_2 = "table2";
        public static final String TABLE_NAME_3 = "table3";
        public static final String COL2 = "col2";
        public static final String COL3 = "col3";
    }
}
