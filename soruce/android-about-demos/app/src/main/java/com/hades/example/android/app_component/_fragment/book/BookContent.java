package com.hades.example.android.app_component._fragment.book;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class BookContent {

    static List<Book> BOOK_ITEMS = new ArrayList<Book>();
    static Map<Integer, Book> ITEM_MAP = new HashMap<Integer, Book>();

    static {
        addItem(new Book(1, "Book1", "This is Book1"));
        addItem(new Book(2, "Book2", "This is Book2"));
        addItem(new Book(3, "Book3", "This is Book3"));
    }

    private static void addItem(Book book) {
        BOOK_ITEMS.add(book);
        ITEM_MAP.put(book.id, book);
    }
}