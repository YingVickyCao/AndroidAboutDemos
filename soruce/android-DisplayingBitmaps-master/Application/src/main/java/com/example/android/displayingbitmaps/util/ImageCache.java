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

package com.example.android.displayingbitmaps.util;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.support.v4.app.FragmentManager;

import com.example.android.common.logger.Log;

import java.lang.ref.SoftReference;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * This class handles disk and memory caching of bitmaps in conjunction with the
 * {@link ImageWorker} class and its subclasses. Use
 * {@link ImageCache#getInstance(android.support.v4.app.FragmentManager, ImageCacheParams)} to get an instance of this
 * class, although usually a cache should be added directly to an {@link ImageWorker} by calling
 * {@link ImageWorker#addImageCache(android.support.v4.app.FragmentManager, ImageCacheParams)}.
 */
public class ImageCache {
    public static final String TAG = "ImageCache";

    // Compression settings when writing images to disk cache
    public static final CompressFormat DEFAULT_COMPRESS_FORMAT = CompressFormat.JPEG;
    public static final int DEFAULT_COMPRESS_QUALITY = 70;

    MemoryCache mMemoryCache = new MemoryCache();
    DiskCache mDiskCache = new DiskCache();

    private Set<SoftReference<Bitmap>> mReusableBitmaps;

    private ImageCache(ImageCacheParams cacheParams) {
        init(cacheParams);
    }

    static ImageCache getInstance(FragmentManager fragmentManager, ImageCacheParams cacheParams) {
        final RetainImageCacheFragment mRetainFragment = findOrCreateRetainFragment(fragmentManager);
        ImageCache imageCache = (ImageCache) mRetainFragment.getObject();
        if (imageCache == null) {
            imageCache = new ImageCache(cacheParams);
            mRetainFragment.setObject(imageCache);
        }

        return imageCache;
    }

    static RetainImageCacheFragment findOrCreateRetainFragment(FragmentManager fm) {
        RetainImageCacheFragment fragment = (RetainImageCacheFragment) fm.findFragmentByTag(RetainImageCacheFragment.TAG);
        if (null != fragment) {
            return fragment;
        }

        fragment = new RetainImageCacheFragment();
        fm.beginTransaction().add(fragment, RetainImageCacheFragment.TAG).commitAllowingStateLoss();

        return fragment;
    }


    /**
     * Initialize the cache, providing all parameters.
     *
     * @param cacheParams The cache parameters to initialize the cache
     */
    private void init(ImageCacheParams cacheParams) {
        mDiskCache.init(cacheParams);
        if (cacheParams.initDiskCacheOnCreate) {
            mDiskCache.initDiskCache();
        }

        if (cacheParams.memoryCacheEnabled) {
            Log.d(TAG, "Memory cache created (size = " + cacheParams.memCacheSize + ")");

            // If we're running on Honeycomb or newer, create a set of reusable bitmaps that can be
            // populated into the inBitmap field of BitmapFactory.Options. Note that the set is
            // of SoftReferences which will actually not be very effective due to the garbage
            // collector being aggressive clearing Soft/WeakReferences. A better approach
            // would be to use a strongly references bitmaps, however this would require some
            // balancing of memory usage between this set and the bitmap LruCache. It would also
            // require knowledge of the expected size of the bitmaps. From Honeycomb to JellyBean
            // the size would need to be precise, from KitKat onward the size would just need to
            // be the upper bound (due to changes in how inBitmap can re-use bitmaps).
            if (Utils.isVersionNoLessThanHoneycomb()) {
                mReusableBitmaps = Collections.synchronizedSet(new HashSet<SoftReference<Bitmap>>());
            }
        }

        if (cacheParams.memoryCacheEnabled) {
            mMemoryCache.init(cacheParams, mReusableBitmaps);
        }
    }

    void initDiskCache() {
        mDiskCache.initDiskCache();
    }

    void addBitmapToCache(String data, BitmapDrawable value) {
        if (data == null || value == null) {
            return;
        }
        mMemoryCache.addBitmapToCache(data, value);
        mDiskCache.addBitmapToCache(data, value);
    }

    BitmapDrawable getBitmapFromMemoryCache(String url) {
        BitmapDrawable memValue = mMemoryCache.getBitmapFromMemoryCache(url);
        if (memValue != null) {
            Log.d(TAG, "Memory cache hit");
        }
        return memValue;
    }

    Bitmap getBitmapFromDiskCache(String url) {
        return mDiskCache.getBitmapFromDiskCache(url, this);
    }

    /**
     * @param options - BitmapFactory.Options with out* options populated
     * @return Bitmap that case be used for inBitmap
     */
    Bitmap getBitmapFromReusableSet(BitmapFactory.Options options) {
        Bitmap bitmap = null;

        if (mReusableBitmaps != null && !mReusableBitmaps.isEmpty()) {
            synchronized (mReusableBitmaps) {
                final Iterator<SoftReference<Bitmap>> iterator = mReusableBitmaps.iterator();
                Bitmap item;

                while (iterator.hasNext()) {
                    item = iterator.next().get();

                    if (null != item && item.isMutable()) {
                        // Check to see it the item can be used for inBitmap
                        if (canUseForInBitmap(item, options)) {
                            bitmap = item;

                            // Remove from reusable set so it can't be used again
                            iterator.remove();
                            break;
                        }
                    } else {
                        // Remove from the set if the reference has been cleared.
                        iterator.remove();
                    }
                }
            }
        }

        return bitmap;
    }

    /**
     * @param candidate     - Bitmap to check
     * @param targetOptions - Options that have the out* value populated
     * @return true if <code>candidate</code> can be used for inBitmap re-use with
     * <code>targetOptions</code>
     */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    private boolean canUseForInBitmap(Bitmap candidate, BitmapFactory.Options targetOptions) {
        if (!Utils.isVersionNoLessThanKitKat()) {
            // On earlier versions, the dimensions must match exactly and the inSampleSize must be 1
            return candidate.getWidth() == targetOptions.outWidth && candidate.getHeight() == targetOptions.outHeight && targetOptions.inSampleSize == 1;
        }

        // From Android 4.4 (KitKat) onward we can re-use if the byte size of the new bitmap
        // is smaller than the reusable bitmap candidate allocation byte count.
        int width = targetOptions.outWidth / targetOptions.inSampleSize;
        int height = targetOptions.outHeight / targetOptions.inSampleSize;
        int byteCount = width * height * getBytesPerPixel(candidate.getConfig());
        return byteCount <= candidate.getAllocationByteCount();
    }

    private int getBytesPerPixel(Bitmap.Config config) {
        if (config == Bitmap.Config.ARGB_8888) {
            return 4;
        } else if (config == Bitmap.Config.RGB_565) {
            return 2;
        } else if (config == Bitmap.Config.ARGB_4444) {
            return 2;
        } else if (config == Bitmap.Config.ALPHA_8) {
            return 1;
        }
        return 1;
    }

    void clearCache() {
        mMemoryCache.clearCache();
        mDiskCache.clearCache();
    }

    void flush() {
        mDiskCache.flush();
    }

    void close() {
        mDiskCache.close();
    }

}