package com.example.android.displayingbitmaps.util;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.support.v4.util.LruCache;

import com.example.android.common.logger.Log;
import com.example.android.displayingbitmaps.BuildConfig;

import java.lang.ref.SoftReference;
import java.util.Set;

public class MemoryCache {
    public static final int DEFAULT_MEMORY_CACHE_KILOBYTES_SIZE = 1024 * 5; // 5MB
    protected static final boolean DEFAULT_MEMORY_CACHE_ENABLED = true;
    private static final String TAG = MemoryCache.class.getSimpleName();
    private ImageCacheParams mCacheParams;

    private LruCache<String, BitmapDrawable> mMemoryCache;

    public void init(ImageCacheParams cacheParams, final Set<SoftReference<Bitmap>> mReusableBitmaps) {
        mCacheParams = cacheParams;
        mMemoryCache = new LruCache<String, BitmapDrawable>(cacheParams.memCacheSize) {

            /**
             * Notify the removed entry that is no longer being cached
             */
            @Override
            protected void entryRemoved(boolean evicted, String key, BitmapDrawable oldValue, BitmapDrawable newValue) {
                if (RecyclingBitmapDrawable.class.isInstance(oldValue)) {
                    // The removed entry is a recycling drawable, so notify it
                    // that it has been removed from the memory cache
                    ((RecyclingBitmapDrawable) oldValue).setIsCached(false);
                } else {
                    // The removed entry is a standard BitmapDrawable

                    if (Utils.isVersionNoLessThanHoneycomb()) {
                        // We're running on Honeycomb or later, so add the bitmap
                        // to a SoftReference set for possible use with inBitmap later
                        mReusableBitmaps.add(new SoftReference<Bitmap>(oldValue.getBitmap()));
                    }
                }
            }

            /**
             * Measure item size in kilobytes rather than units which is more practical
             * for a bitmap cache
             */
            @Override
            protected int sizeOf(String key, BitmapDrawable value) {
                final int bitmapSize = getBitmapSize(value) / 1024;
                return bitmapSize == 0 ? 1 : bitmapSize;
            }
        };
    }

    public void addBitmapToCache(String data, BitmapDrawable value) {
        if (data == null || value == null) {
            return;
        }

        if (mMemoryCache != null) {
            if (value instanceof RecyclingBitmapDrawable) {
                ((RecyclingBitmapDrawable) value).setIsCached(true);
            }
            mMemoryCache.put(data, value);
        }

    }

    public BitmapDrawable getBitmapFromMemoryCache(String url) {
        BitmapDrawable memValue = null;

        if (mMemoryCache != null) {
            memValue = mMemoryCache.get(url);
        }
        return memValue;
    }

    public void clearCache() {
        if (mMemoryCache != null) {
            mMemoryCache.evictAll();
            if (BuildConfig.DEBUG) {
                Log.d(TAG, "Memory cache cleared");
            }
        }
    }

    /**
     * Get the size in bytes of a bitmap in a BitmapDrawable. Note that from Android 4.4 (KitKat)
     * onward this returns the allocated memory size of the bitmap which can be larger than the
     * actual bitmap data byte count (in the case it was re-used).
     *
     * @param value
     * @return size in bytes
     */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    private int getBitmapSize(BitmapDrawable value) {
        Bitmap bitmap = value.getBitmap();

        // From KitKat onward use getAllocationByteCount() as allocated bytes can potentially be
        // larger than bitmap byte count.
        if (Utils.isVersionNoLessThanKitKat()) {
            return bitmap.getAllocationByteCount();
        }

        if (Utils.isVersionNoLessThanHoneycombMR1()) {
            return bitmap.getByteCount();
        }

        // Pre HC-MR1
        return bitmap.getRowBytes() * bitmap.getHeight();
    }
}
