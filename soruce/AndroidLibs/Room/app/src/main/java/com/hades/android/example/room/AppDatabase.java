package com.hades.android.example.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Course.class, Student.class, Score.class,}, /*views = {UserDetail.class},*/version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract TeachDao teachDao();
}