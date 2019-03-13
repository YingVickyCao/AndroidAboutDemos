package com.hades.example.android.mock;

/**
 * A dummy item representing a piece of content.
 */
public class DummyItem {
    public final String id;
    public final String content;
    public final String details;

    public DummyItem(String id, String content, String details) {
        this.id = id;
        this.content = content;
        this.details = details;
    }

    @Override
    public String toString() {
        return "{" +
                "id='" + id + '\'' +
                ", content='" + content + '\'' +
                ", details='" + details + '\'' +
                '}';
    }
}
