package com.hades.android.example.room;

import android.graphics.Bitmap;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;

//@Fts4
//@Fts4(languageId = "lid")
//@Entity//(ignoredColumns = "picture") // class extends Score.class
@Entity(tableName = "score"
        , primaryKeys = {"stu_id", "course_id"}
        , foreignKeys = {@ForeignKey(entity = Student.class, parentColumns = "stu_id", childColumns = "stu_id")
        , @ForeignKey(entity = Course.class, parentColumns = "course_id", childColumns = "course_id")})
public class Score {
    @ColumnInfo(name = "stu_id")
    int stuId;

    @ColumnInfo(name = "course_id")
    int courseId;

    int score;

    int temp;

//    int temp2;

    @Ignore
    Bitmap picture;
}