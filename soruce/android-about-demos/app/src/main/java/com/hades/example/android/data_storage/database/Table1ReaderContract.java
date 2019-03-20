package com.hades.example.android.data_storage.database;

import android.provider.BaseColumns;

public class Table1ReaderContract {
    private Table1ReaderContract() {
    }

    public static class TableEntry implements BaseColumns {
        public static final String TABLE_NAME = "table1";
        public static final String COL2 = "col2";
        public static final String COL3 = "col3";
    }

    public static class TableEntryUpgrade implements BaseColumns {
        public static final String TABLE_NAME = "table1";
        public static final String COL2 = "col2_upgrade";
        public static final String COL3 = "col3_upgrade";
    }
}
