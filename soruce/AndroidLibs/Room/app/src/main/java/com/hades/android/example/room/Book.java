package com.hades.android.example.room;

import android.graphics.Bitmap;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;


//@Fts4
//@Fts4(languageId = "lid")
//@Entity//(ignoredColumns = "picture")
@Entity(tableName = "books", primaryKeys = {"firstName", "lastName"})
public class Book {
    @PrimaryKey
    public int book_id;

    @ColumnInfo(name = "first_name")
    public String firstName;

    @ColumnInfo(name = "last_name")
    public String lastName;

    @NonNull
    public String bookName;

    @Ignore
    public Bitmap picture;
}