package com.example.android.displayingbitmaps.util;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.util.LruCache;

import com.example.android.common.logger.Log;
import com.example.android.displayingbitmaps.BuildConfig;

import java.lang.ref.SoftReference;
import java.util.Set;

import static com.example.android.displayingbitmaps.util.ImageCache.getBitmapSize;

public class MemoryCache {
    private static final String TAG = MemoryCache.class.getSimpleName();

    private LruCache<String, BitmapDrawable> mMemoryCache;

    public void init(ImageCacheParams cacheParams, final Set<SoftReference<Bitmap>> mReusableBitmaps) {
        mMemoryCache = new LruCache<String, BitmapDrawable>(cacheParams.memCacheSize) {

            /**
             * Notify the removed entry that is no longer being cached
             */
            @Override
            protected void entryRemoved(boolean evicted, String key,
                                        BitmapDrawable oldValue, BitmapDrawable newValue) {
                if (RecyclingBitmapDrawable.class.isInstance(oldValue)) {
                    // The removed entry is a recycling drawable, so notify it
                    // that it has been removed from the memory cache
                    ((RecyclingBitmapDrawable) oldValue).setIsCached(false);
                } else {
                    // The removed entry is a standard BitmapDrawable

                    if (Utils.hasHoneycomb()) {
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
}
