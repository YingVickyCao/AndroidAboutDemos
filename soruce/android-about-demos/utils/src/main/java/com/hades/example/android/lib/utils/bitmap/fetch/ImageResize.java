package com.hades.example.android.lib.utils.bitmap.fetch;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

public class ImageResize extends ImageWorker {
    private static final String TAG = ImageResize.class.getSimpleName();
    int mImageWidth;
    int mImageHeight;

    ImageResize(Context context, int imageWidth, int imageHeight) {
        super(context);
        setImageSize(imageWidth, imageHeight);
    }

    ImageResize(Context context, int imageSize) {
        super(context);
        setImageSize(imageSize);
    }

    public void setImageSize(int width, int height) {
        mImageWidth = width;
        mImageHeight = height;
    }

    public void setImageSize(int size) {
        setImageSize(size, size);
    }

    private Bitmap processBitmap(int resId) {
        Log.d(TAG, "processBitmap,resId= " + resId);
        return imageUtil.decodeSampledBitmapFromResource(mResources, resId, mImageWidth, mImageHeight, mImageCache);
    }

    @Override
    protected Bitmap processBitmap(Object data) {
        return processBitmap(Integer.parseInt(String.valueOf(data)));
    }
}
