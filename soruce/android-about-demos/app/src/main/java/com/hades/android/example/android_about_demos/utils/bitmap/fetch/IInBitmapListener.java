package com.hades.android.example.android_about_demos.utils.bitmap.fetch;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public interface IInBitmapListener {
    Bitmap checkReusableBitmapsPopulatedIntoInBitmap(BitmapFactory.Options options);
}