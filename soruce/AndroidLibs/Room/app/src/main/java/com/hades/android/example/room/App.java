package com.hades.android.example.room;

import android.app.Application;

import androidx.room.Room;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

public class App extends Application {
    private AppDatabase mAppDatabase;

    @Override
    public void onCreate() {
        super.onCreate();

        mAppDatabase = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "room.db")
//                .allowMainThreadQueries()
//                .addMigrations(MIGRATION_1_2)
                .build();
    }

    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE score  ADD COLUMN temp2 INTEGER");
        }
    };

    public AppDatabase getAppDatabase() {
        return mAppDatabase;
    }
}
