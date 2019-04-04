package com.hades.android.example.room;

import androidx.room.ColumnInfo;

public class ScoreUpdateTuple {
    @ColumnInfo(name = "course_id")
    int courseId;

    @ColumnInfo(name = "stu_id")
    int stuId;

    int score;
}
