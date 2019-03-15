package com.hades.example.android.mock;

/**
 * A dummy item representing a piece of content.
 */
public class DummyItem {
    public final String id;
    public final String colo2;
    public final String col3;

    public DummyItem(String id, String content, String details) {
        this.id = id;
        this.colo2 = content;
        this.col3 = details;
    }

    @Override
    public String toString() {
        return "{" +
                "id='" + id + '\'' +
                ", colo2='" + colo2 + '\'' +
                ", col3='" + col3 + '\'' +
                '}';
    }
}
