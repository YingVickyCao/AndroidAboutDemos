package com.example.android.displayingbitmaps.util.bitmap.fetch;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public interface IInBitmapListener {
    Bitmap checkReusableBitmapsPopulatedIntoInBitmap(BitmapFactory.Options options);
}