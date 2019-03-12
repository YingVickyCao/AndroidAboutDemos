package com.hades.example.android.utils.bitmap.fetch;

import android.widget.ImageView;

public interface IImageWorker {
    void loadImage(String url, ImageView boundImageView);
}
