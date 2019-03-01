package com.hades.android.example.android_about_demos.resource.drawable.bitmap;

public class ImageDisplayItem {
    private String num;
    private int imageResId;

    public ImageDisplayItem(String num, int imageResId) {
        this.num = num;
        this.imageResId = imageResId;
    }

    public String getNum() {
        return num;
    }

    public int getImageResId() {
        return imageResId;
    }
}
