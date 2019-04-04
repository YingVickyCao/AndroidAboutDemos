package com.hades.android.example.room;

import androidx.room.ColumnInfo;

public class ScoreStuCourseTuple {
    @ColumnInfo(name = "course_name")
    public String courseName;

    @ColumnInfo(name = "stu_name")
    public String stuName;

    int score;
}
