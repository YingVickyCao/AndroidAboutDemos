package com.example.android.displayingbitmaps.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.util.LruCache;

import com.example.android.common.logger.Log;
import com.example.android.displayingbitmaps.BuildConfig;

import java.lang.ref.SoftReference;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class MemoryCache {
    private static final String TAG = MemoryCache.class.getSimpleName();

    public static final int DEFAULT_MEMORY_CACHE_KILOBYTES_SIZE = 1024 * 5; // 5MB
    protected static final boolean DEFAULT_MEMORY_CACHE_ENABLED = true;

    ImageUtil imageUtil = new ImageUtil();

    private LruCache<String, BitmapDrawable> mMemoryCache;
    private Set<SoftReference<Bitmap>> mReusableBitmapsPopulatedIntoInBitmap;

    public void init(ImageCacheParams cacheParams) {
        if (Utils.isVersionNoLessThan3_0()) {
            mReusableBitmapsPopulatedIntoInBitmap = Collections.synchronizedSet(new HashSet<SoftReference<Bitmap>>());
        }

        mMemoryCache = new LruCache<String, BitmapDrawable>(cacheParams.memCacheSize) {
            @Override
            protected void entryRemoved(boolean evicted, String key, BitmapDrawable oldValue, BitmapDrawable newValue) {
                if (Utils.isVersionNoLessThan3_0()) {
                    mReusableBitmapsPopulatedIntoInBitmap.add(new SoftReference<>(oldValue.getBitmap()));
                }
            }

            @Override
            protected int sizeOf(String key, BitmapDrawable value) {
                final int bitmapSize = imageUtil.getBitmapBytesSizeAtBitmapDrawable(value) / 1024;
                return bitmapSize == 0 ? 1 : bitmapSize;
            }
        };
    }

    public void addBitmapToCache(String data, BitmapDrawable value) {
        if (data == null || value == null) {
            return;
        }

        if (mMemoryCache != null) {
            mMemoryCache.put(data, value);
        }
    }

    public BitmapDrawable getBitmapFromMemoryCache(String url) {
        BitmapDrawable bitmapDrawable = null;

        if (mMemoryCache != null) {
            bitmapDrawable = mMemoryCache.get(url);
        }
        return bitmapDrawable;
    }

    public void clearCache() {
        if (mMemoryCache != null) {
            mMemoryCache.evictAll();
            if (BuildConfig.DEBUG) {
                Log.d(TAG, "Memory cache cleared");
            }
        }
    }

    Bitmap getBitmapUsed4InBitmapFromReusableSet(BitmapFactory.Options options) {
        Bitmap bitmap = null;
        if (mReusableBitmapsPopulatedIntoInBitmap != null && !mReusableBitmapsPopulatedIntoInBitmap.isEmpty()) {
            synchronized (mReusableBitmapsPopulatedIntoInBitmap) {
                final Iterator<SoftReference<Bitmap>> iterator = mReusableBitmapsPopulatedIntoInBitmap.iterator();
                Bitmap item;
                while (iterator.hasNext()) {
                    item = iterator.next().get();
                    if (null != item && item.isMutable()) {
                        if (imageUtil.canUsedForInBitmapReuseWithTargetOptions(item, options)) {
                            bitmap = item;
                            iterator.remove();
                            break;
                        }
                    } else {
                        iterator.remove();
                    }
                }
            }
        }
        return bitmap;
    }
}
