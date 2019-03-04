package com.example.android.displayingbitmaps.util;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;

import com.example.android.common.logger.Log;
import com.example.android.displayingbitmaps.BuildConfig;

import java.lang.ref.WeakReference;

class BitmapWorkerTask extends AsyncTask<Void, Void, BitmapDrawable> {
    private static final String TAG = BitmapWorkerTask.class.getSimpleName();

    private ImageWorker imageWorker;
    public Object mData;
    private final WeakReference<ImageView> imageViewReference;
    private final OnImageLoadedListener mOnImageLoadedListener;

    public BitmapWorkerTask(ImageWorker imageWorker, Object data, ImageView imageView) {
        this.imageWorker = imageWorker;
        mData = data;
        imageViewReference = new WeakReference<ImageView>(imageView);
        mOnImageLoadedListener = null;
    }

    /**
     * Background processing.
     */
    @Override
    protected BitmapDrawable doInBackground(Void... params) {
        return loadBitmapInBackground(params);
    }

        if (BuildConfig.DEBUG) {
            Log.d(TAG, "doInBackground - starting work");
        }
        final String url = String.valueOf(mData);
        Bitmap bitmap = null;
        BitmapDrawable drawable = null;

        // Wait here if work is paused and the task is not cancelled
        synchronized (imageWorker.mPauseWorkLock) {
            while (imageWorker.mPauseWork && !isCancelled()) {
                try {
                    imageWorker.mPauseWorkLock.wait();
                } catch (InterruptedException e) {
                }
            }
        }

        bitmap = findBitmapInDishCache(url);
        bitmap = processBitmap(bitmap);
        if (bitmap != null) {
            if (Utils.isVersionNoLessThanHoneycomb()) {
                drawable = new BitmapDrawable(imageWorker.mResources, bitmap);
            }

            if (imageWorker.mImageCache != null) {
                imageWorker.mImageCache.addBitmapToCache(url, drawable);
            }
        }

        if (BuildConfig.DEBUG) {
            Log.d(TAG, "doInBackground - finished work");
        }

        return drawable;
    }

    private Bitmap findBitmapInDishCache(String url) {
        if (imageWorker.mImageCache != null && !isCancelled() && getAttachedImageView() != null && !imageWorker.mExitTasksEarly) {
            return imageWorker.mImageCache.getBitmapFromDiskCache(url);
        }
        return null;
    }

    private Bitmap processBitmap(Bitmap bitmap) {
        if (bitmap == null && !isCancelled() && getAttachedImageView() != null && !imageWorker.mExitTasksEarly) {
            bitmap = imageWorker.processBitmap(mData);
        }
        return bitmap;
    }

    public BitmapWorkerTask(ImageWorker imageWorker, Object data, ImageView imageView, OnImageLoadedListener listener) {
        this.imageWorker = imageWorker;
        mData = data;
        imageViewReference = new WeakReference<ImageView>(imageView);
        mOnImageLoadedListener = listener;
    }

    /**
     * Once the image is processed, associates it to the imageView
     */
    @Override
    protected void onPostExecute(BitmapDrawable value) {
        //BEGIN_INCLUDE(complete_background_work)
        boolean success = false;
        // if cancel was called on this task or the "exit early" flag is set then we're done
        if (isCancelled() || imageWorker.mExitTasksEarly) {
            value = null;
        }

        final ImageView imageView = getAttachedImageView();
        if (value != null && imageView != null) {
            if (BuildConfig.DEBUG) {
                Log.d(TAG, "onPostExecute - setting bitmap");
            }
            success = true;
            imageWorker.setImageDrawable(imageView, value);
        }
        if (mOnImageLoadedListener != null) {
            mOnImageLoadedListener.onImageLoaded(success);
        }
        //END_INCLUDE(complete_background_work)
    }

    @Override
    protected void onCancelled(BitmapDrawable value) {
        super.onCancelled(value);
        synchronized (imageWorker.mPauseWorkLock) {
            imageWorker.mPauseWorkLock.notifyAll();
        }
    }

    /**
     * Returns the ImageView associated with this task as long as the ImageView's task still
     * points to this task as well. Returns null otherwise.
     */
    private ImageView getAttachedImageView() {
        final ImageView imageView = imageViewReference.get();
        final BitmapWorkerTask bitmapWorkerTask = imageWorker.getBitmapWorkerTask(imageView);

        if (this == bitmapWorkerTask) {
            return imageView;
        }

        return null;
    }
}
