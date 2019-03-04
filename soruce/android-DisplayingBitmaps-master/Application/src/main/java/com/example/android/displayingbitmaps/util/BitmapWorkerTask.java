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
        return backgroundProcessing(params);
    }

    private BitmapDrawable backgroundProcessing(Void... params) {
        //BEGIN_INCLUDE(load_bitmap_in_background)
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "doInBackground - starting work");
        }

        final String dataString = String.valueOf(mData);
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

        // If the image cache is available and this task has not been cancelled by another
        // thread and the ImageView that was originally bound to this task is still bound back
        // to this task and our "exit early" flag is not set then try and fetch the bitmap from
        // the cache
        if (imageWorker.mImageCache != null && !isCancelled() && getAttachedImageView() != null
                && !imageWorker.mExitTasksEarly) {
            bitmap = imageWorker.mImageCache.getBitmapFromDiskCache(dataString);
        }

        // If the bitmap was not found in the cache and this task has not been cancelled by
        // another thread and the ImageView that was originally bound to this task is still
        // bound back to this task and our "exit early" flag is not set, then call the main
        // process method (as implemented by a subclass)
        if (bitmap == null && !isCancelled() && getAttachedImageView() != null
                && !imageWorker.mExitTasksEarly) {
            bitmap = imageWorker.processBitmap(mData);
        }

        // If the bitmap was processed and the image cache is available, then add the processed
        // bitmap to the cache for future use. Note we don't check if the task was cancelled
        // here, if it was, and the thread is still running, we may as well add the processed
        // bitmap to our cache as it might be used again in the future
        if (bitmap != null) {
            if (Utils.isVersionNoLessThanHoneycomb()) {
                // Running on Honeycomb or newer, so wrap in a standard BitmapDrawable
                drawable = new BitmapDrawable(imageWorker.mResources, bitmap);
            } else {
                // Running on Gingerbread or older, so wrap in a RecyclingBitmapDrawable
                // which will recycle automagically
                drawable = new RecyclingBitmapDrawable(imageWorker.mResources, bitmap);
            }

            if (imageWorker.mImageCache != null) {
                imageWorker.mImageCache.addBitmapToCache(dataString, drawable);
            }
        }

        if (BuildConfig.DEBUG) {
            Log.d(TAG, "doInBackground - finished work");
        }

        return drawable;
        //END_INCLUDE(load_bitmap_in_background)
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
