package com.example.android.displayingbitmaps.util;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
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

    private ImageCacheParams mCacheParams;

    private LruCache<String, BitmapDrawable> mMemoryCache;
    private Set<SoftReference<Bitmap>> mReusableBitmaps;

    public void init(ImageCacheParams cacheParams) {
        mCacheParams = cacheParams;
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

        mMemoryCache = new LruCache<String, BitmapDrawable>(cacheParams.memCacheSize) {

            /**
             * Notify the removed entry that is no longer being cached
             */
            @Override
            protected void entryRemoved(boolean evicted, String key, BitmapDrawable oldValue, BitmapDrawable newValue) {
                if (Utils.isVersionNoLessThanHoneycomb()) {
                    // We're running on Honeycomb or later, so add the bitmap
                    // to a SoftReference set for possible use with inBitmap later
                    mReusableBitmaps.add(new SoftReference<Bitmap>(oldValue.getBitmap()));
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
                        if (imageUtil.canUsedForInBitmapReuseWithTargetOptions(item, options)) {
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
