package com.example.android.displayingbitmaps.util.bitmap.fetch;

import android.widget.ImageView;

public interface IImageWorker {
    void loadImage(String url, ImageView boundImageView);
}
