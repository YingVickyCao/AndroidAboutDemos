package com.hades.example.android.data_storage.database;

import android.provider.BaseColumns;

public class FeedReaderContract {
    private FeedReaderContract() {
    }

    public static class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "entry";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_SUBTITLE = "subtitle";
    }
}
