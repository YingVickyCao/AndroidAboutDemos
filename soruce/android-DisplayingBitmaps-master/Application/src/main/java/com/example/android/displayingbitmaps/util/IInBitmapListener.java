package com.example.android.displayingbitmaps.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public interface IInBitmapListener {
    Bitmap check(BitmapFactory.Options options);
}