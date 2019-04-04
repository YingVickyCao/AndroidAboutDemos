package com.hades.android.example.room;

import androidx.room.ColumnInfo;

public class ScoreTuple {
    @ColumnInfo(name = "course_id")
    int courseId;

    int score;
}
