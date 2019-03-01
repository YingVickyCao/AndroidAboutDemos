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

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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

    /**
     * Create a new ImageCache object using the specified parameters. This should not be
     * called directly by other classes, instead use
     * {@link ImageCache#getInstance(android.support.v4.app.FragmentManager, ImageCacheParams)} to fetch an ImageCache
     * instance.
     *
     * @param cacheParams The cache parameters to use to initialize the cache
     */
    private ImageCache(ImageCacheParams cacheParams) {
        init(cacheParams);
    }

    /**
     * Return an {@link ImageCache} instance. A {@link RetainFragment} is used to retain the
     * ImageCache object across configuration changes such as a change in device orientation.
     *
     * @param fragmentManager The fragment manager to use when dealing with the retained fragment.
     * @param cacheParams     The cache parameters to use if the ImageCache needs instantiation.
     * @return An existing retained ImageCache object or a new one if one did not exist
     */
    public static ImageCache getInstance(FragmentManager fragmentManager, ImageCacheParams cacheParams) {
        // Search for, or create an instance of the non-UI RetainFragment
        final RetainFragment mRetainFragment = LoadImageUtil.findOrCreateRetainFragment(fragmentManager);

        // See if we already have an ImageCache stored in RetainFragment
        ImageCache imageCache = (ImageCache) mRetainFragment.getObject();

        // No existing ImageCache, create one and store it in RetainFragment
        if (imageCache == null) {
            imageCache = new ImageCache(cacheParams);
            mRetainFragment.setObject(imageCache);
        }

        return imageCache;
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

            if (cacheParams.memoryCacheEnabled) {
                mMemoryCache.init(cacheParams, mReusableBitmaps);
            }
        }
    }

    public void initDiskCache() {
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
    protected Bitmap getBitmapFromReusableSet(BitmapFactory.Options options) {
        Bitmap bitmap = null;

        if (mReusableBitmaps != null && !mReusableBitmaps.isEmpty()) {
            synchronized (mReusableBitmaps) {
                final Iterator<SoftReference<Bitmap>> iterator = mReusableBitmaps.iterator();
                Bitmap item;

                while (iterator.hasNext()) {
                    item = iterator.next().get();

                    if (null != item && item.isMutable()) {
                        // Check to see it the item can be used for inBitmap
                        if (LoadImageUtil.canUseForInBitmap(item, options)) {
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
     * Clears both the memory and disk cache associated with this ImageCache object. Note that
     * this includes disk access so this should not be executed on the main/UI thread.
     */
    public void clearCache() {
        mMemoryCache.clearCache();
        mDiskCache.clearCache();
    }

    /**
     * Flushes the disk cache associated with this ImageCache object. Note that this includes
     * disk access so this should not be executed on the main/UI thread.
     */
    public void flush() {
        mDiskCache.flush();
    }

    /**
     * Closes the disk cache associated with this ImageCache object. Note that this includes
     * disk access so this should not be executed on the main/UI thread.
     */
    public void close() {
        mDiskCache.close();
    }

    /**
     * A simple non-UI Fragment that stores a single Object and is retained over configuration
     * changes. It will be used to retain the ImageCache object.
     */
    public static class RetainFragment extends Fragment {
        public static final String TAG = RetainFragment.class.getSimpleName();

        private Object mObject;

        /**
         * Empty constructor as per the Fragment documentation
         */
        public RetainFragment() {
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            // Make sure this Fragment is retained over a configuration change
            setRetainInstance(true);
        }

        /**
         * Store a single object in this Fragment.
         *
         * @param object The object to store
         */
        public void setObject(Object object) {
            mObject = object;
        }

        /**
         * Get the stored object.
         *
         * @return The stored object
         */
        public Object getObject() {
            return mObject;
        }
    }

}
