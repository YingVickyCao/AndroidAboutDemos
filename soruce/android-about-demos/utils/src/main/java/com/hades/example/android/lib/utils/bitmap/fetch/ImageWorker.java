/*
 * Copyright (C) 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hades.example.android.lib.utils.bitmap.fetch;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.AsyncTask;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import android.util.Log;
import android.widget.ImageView;

import com.hades.example.android.lib.BuildConfig;
import com.hades.example.android.lib.utils.ImageUtil;
import com.hades.example.android.lib.utils.bitmap.cache.ImageCache;
import com.hades.example.android.lib.utils.bitmap.cache.ImageCacheParams;

public abstract class ImageWorker implements IImageWorker {
    private static final String TAG = ImageWorker.class.getSimpleName();
    private static final int FADE_IN_TIME = 200;

    public ImageCache mImageCache;
    public ImageCacheParams mImageCacheParams;
    public Bitmap mLoadingBitmap;
    private boolean mFadeInBitmap = true;
    public boolean mExitTasksEarly = false;
    protected boolean mPauseWork = false;
    public final Object mPauseWorkLock = new Object();

    private static final int MESSAGE_CLEAR = 0;
    private static final int MESSAGE_INIT_DISK_CACHE = 1;
    private static final int MESSAGE_FLUSH = 2;
    private static final int MESSAGE_CLOSE = 3;

    protected Resources mResources;
    protected ImageUtil imageUtil = new ImageUtil();

    protected ImageWorker(Context context) {
        mResources = context.getResources();
    }

    public void loadImage(String url, ImageView boundImageView, OnImageLoadedListener listener) {
        if (url == null) {
            return;
        }

        BitmapDrawable value = null;
        if (mImageCache != null) {
            value = mImageCache.getBitmapFromMemoryCache(url);
        }

        if (value != null) {
            boundImageView.setImageDrawable(value);
            if (listener != null) {
                listener.onImageLoaded(true);
            }
        } else if (cancelPotentialWork(url, boundImageView)) {
            final BitmapWorkerTask task = new BitmapWorkerTask(this, url, boundImageView, listener);
            final AsyncDrawable asyncDrawable = new AsyncDrawable(mResources, mLoadingBitmap, task);
            boundImageView.setImageDrawable(asyncDrawable);
            task.execute();
        }
    }

    @Override
    public void loadImage(String url, ImageView boundImageView) {
        loadImage(url, boundImageView, null);
    }

    public void setLoadingImage(Bitmap bitmap) {
        mLoadingBitmap = bitmap;
    }

    public void setLoadingImage(int resId) {
        mLoadingBitmap = BitmapFactory.decodeResource(mResources, resId);
    }

    public void addImageCache(FragmentManager fragmentManager, ImageCacheParams cacheParams) {
        mImageCacheParams = cacheParams;
        mImageCache = ImageCache.getInstance(fragmentManager, mImageCacheParams);
        new CacheAsyncTask().execute(MESSAGE_INIT_DISK_CACHE);
    }

    public void addImageCache(FragmentActivity activity, String diskCacheDirectoryName) {
        mImageCacheParams = new ImageCacheParams(activity, diskCacheDirectoryName);
        mImageCache = ImageCache.getInstance(activity.getSupportFragmentManager(), mImageCacheParams);
        new CacheAsyncTask().execute(MESSAGE_INIT_DISK_CACHE);
    }

    public void setImageFadeIn(boolean fadeIn) {
        mFadeInBitmap = fadeIn;
    }

    public void setExitTasksEarly(boolean exitTasksEarly) {
        mExitTasksEarly = exitTasksEarly;
        setPauseWork(false);
    }

    protected abstract Bitmap processBitmap(Object data);

    protected ImageCache getImageCache() {
        return mImageCache;
    }

    public void cancelWork(ImageView imageView) {
        final BitmapWorkerTask bitmapWorkerTask = getBitmapWorkerTask(imageView);
        if (bitmapWorkerTask != null) {
            bitmapWorkerTask.cancel(true);
            if (BuildConfig.DEBUG) {
                final Object bitmapData = bitmapWorkerTask.mData;
                Log.d(TAG, "cancelWork - cancelled work for " + bitmapData);
            }
        }
    }

    public boolean cancelPotentialWork(Object data, ImageView imageView) {
        final BitmapWorkerTask bitmapWorkerTask = getBitmapWorkerTask(imageView);
        if (bitmapWorkerTask != null) {
            final Object bitmapData = bitmapWorkerTask.mData;
            if (bitmapData == null || !bitmapData.equals(data)) {
                bitmapWorkerTask.cancel(true);
                if (BuildConfig.DEBUG) {
                    Log.d(TAG, "cancelPotentialWork - cancelled work for " + data);
                }
            } else {
                return false;
            }
        }
        return true;
    }

    public BitmapWorkerTask getBitmapWorkerTask(ImageView imageView) {
        if (imageView != null) {
            final Drawable drawable = imageView.getDrawable();
            if (drawable instanceof AsyncDrawable) {
                final AsyncDrawable asyncDrawable = (AsyncDrawable) drawable;
                return asyncDrawable.getBitmapWorkerTask();
            }
        }
        return null;
    }

    public void setImageDrawable(ImageView imageView, Drawable drawable) {
        if (mFadeInBitmap) {
            // Transition drawable with a transparent drawable and the final drawable
            final TransitionDrawable td = new TransitionDrawable(new Drawable[]{new ColorDrawable(mResources.getColor(android.R.color.transparent)), drawable});
            imageView.setBackgroundDrawable(new BitmapDrawable(mResources, mLoadingBitmap));
            imageView.setImageDrawable(td);
            td.startTransition(FADE_IN_TIME);
        } else {
            imageView.setImageDrawable(drawable);
        }
    }

    public void setPauseWork(boolean pauseWork) {
        synchronized (mPauseWorkLock) {
            mPauseWork = pauseWork;
            if (!mPauseWork) {
                mPauseWorkLock.notifyAll();
            }
        }
    }

    protected class CacheAsyncTask extends AsyncTask<Object, Void, Void> {

        @Override
        protected Void doInBackground(Object... params) {
            switch ((Integer) params[0]) {
                case MESSAGE_CLEAR:
                    clearCacheInternal();
                    break;
                case MESSAGE_INIT_DISK_CACHE:
                    initDiskCacheInternal();
                    break;
                case MESSAGE_FLUSH:
                    flushCacheInternal();
                    break;
                case MESSAGE_CLOSE:
                    closeCacheInternal();
                    break;
            }
            return null;
        }
    }

    protected void initDiskCacheInternal() {
        if (mImageCache != null) {
            mImageCache.initDiskCache();
        }
    }

    protected void clearCacheInternal() {
        if (mImageCache != null) {
            mImageCache.clearCache();
        }
    }

    protected void flushCacheInternal() {
        if (mImageCache != null) {
            mImageCache.flush();
        }
    }

    protected void closeCacheInternal() {
        if (mImageCache != null) {
            mImageCache.close();
            mImageCache = null;
        }
    }

    public void clearCache() {
        new CacheAsyncTask().execute(MESSAGE_CLEAR);
    }

    public void flushCache() {
        new CacheAsyncTask().execute(MESSAGE_FLUSH);
    }

    public void closeCache() {
        new CacheAsyncTask().execute(MESSAGE_CLOSE);
    }
}