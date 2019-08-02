package com.hades.example.android.app_component._fragment.book;

import androidx.annotation.NonNull;

class Book {
    Integer id;
    String title;
    String desc;

    Book(Integer id, String title, String desc) {
        this.id = id;
        this.title = title;
        this.desc = desc;
    }

    @NonNull
    @Override
    public String toString() {
        return title;
    }
}
