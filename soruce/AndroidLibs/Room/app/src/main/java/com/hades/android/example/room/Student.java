package com.hades.android.example.room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "stu")
public class Student {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "stu_id")
    public int id;

    @ColumnInfo(name = "stu_name")
    public String stuName;
}