package com.hades.example.android.lib.utils.bitmap.cache.disk;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;

import com.hades.example.android.lib.BuildConfig;
import com.hades.example.android.lib.utils.bitmap.cache.ImageCacheParams;
import com.hades.example.android.lib.utils.bitmap.fetch.IInBitmapListener;
import com.hades.example.android.lib.utils.FileUtil;
import com.hades.example.android.lib.utils.ImageUtil;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DiskCache {
    public static final Bitmap.CompressFormat DEFAULT_COMPRESS_FORMAT = Bitmap.CompressFormat.JPEG;
    private static final String TAG = DiskCache.class.getSimpleName();
    public static final boolean DEFAULT_DISK_CACHE_ENABLED = true;
    public static final boolean DEFAULT_INIT_DISK_CACHE_ON_CREATE = false;

    public static final int DEFAULT_DISK_CACHE_BYTES_SIZE = 1024 * 1024 * 10; // 10MB
    public static final int DISK_CACHE_INDEX = 0;

    private DiskLruCache mDiskLruCache;
    private final Object mDiskCacheLock = new Object();
    private boolean mDiskCacheStarting = true;
    private ImageCacheParams mCacheParams;

    FileUtil fileUtil = new FileUtil();
    ImageUtil imageUtil = new ImageUtil();

    public void init(ImageCacheParams cacheParams) {
        mCacheParams = cacheParams;
//        initDiskCache();
    }

    public void initDiskCache() {
        synchronized (mDiskCacheLock) {
            if (mDiskLruCache == null || mDiskLruCache.isClosed()) {
                File file = mCacheParams.diskCacheDir;
                if (mCacheParams.diskCacheEnabled && file != null) {
                    if (!file.exists()) {
                        file.mkdirs();
                    }
                    if (fileUtil.getUsableSpace(file) > mCacheParams.diskCacheSize) {
                        try {
                            mDiskLruCache = DiskLruCache.open(file, 1, 1, mCacheParams.diskCacheSize);
                            Log.d(TAG, "initDiskCache,Disk cache initialized");
                        } catch (final IOException e) {
                            mCacheParams.diskCacheDir = null;
                            Log.e(TAG, "initDiskCache, " + e);
                        }
                    }
                }
            }
            mDiskCacheStarting = false;
            mDiskCacheLock.notifyAll();
        }
    }

    public void cacheBitmap(String data, BitmapDrawable value) {
        synchronized (mDiskCacheLock) {
            if (mDiskLruCache != null) {
                final String key = fileUtil.hashKeyForDisk(data);
                OutputStream out = null;
                try {
                    DiskLruCache.Snapshot snapshot = mDiskLruCache.get(key);
                    if (snapshot == null) {
                        final DiskLruCache.Editor editor = mDiskLruCache.edit(key);
                        if (editor != null) {
                            out = editor.newOutputStream(DISK_CACHE_INDEX);
                            value.getBitmap().compress(mCacheParams.compressFormat, mCacheParams.compressQuality, out);
                            editor.commit();
                            out.close();
                        }
                    } else {
                        snapshot.getInputStream(DISK_CACHE_INDEX).close();
                    }
                } catch (final IOException e) {
                    Log.e(TAG, "cacheBitmap - " + e);
                } catch (Exception e) {
                    Log.e(TAG, "cacheBitmap - " + e);
                } finally {
                    try {
                        if (out != null) {
                            out.close();
                        }
                    } catch (IOException e) {
                        Log.e(TAG, "cacheBitmap: " + e);
                    }
                }
            }
        }
    }

    public Bitmap getBitmap(String url, final IInBitmapListener memoryCache) {
        final String key = fileUtil.hashKeyForDisk(url);
        Bitmap bitmap = null;

        synchronized (mDiskCacheLock) {
            while (mDiskCacheStarting) {
                try {
                    mDiskCacheLock.wait();
                } catch (InterruptedException e) {
                }
            }
            if (mDiskLruCache != null) {
                InputStream inputStream = null;
                try {
                    final DiskLruCache.Snapshot snapshot = mDiskLruCache.get(key);
                    if (snapshot != null) {
                        if (BuildConfig.DEBUG) {
                            Log.d(TAG, "getBitmap: Disk cache hit. ,url" + url + ",key=" + key);
                        }
                        inputStream = snapshot.getInputStream(DISK_CACHE_INDEX);
                        if (inputStream != null) {
                            FileDescriptor fd = ((FileInputStream) inputStream).getFD();
                            // Decode bitmap, but we don't want to sample so give MAX_VALUE as the target dimensions
                            bitmap = imageUtil.decodeSampledBitmapFromDescriptor(fd, Integer.MAX_VALUE, Integer.MAX_VALUE, memoryCache);
                        }
                    }
                } catch (final IOException e) {
                    Log.e(TAG, "getBitmap - " + e);
                } finally {
                    try {
                        if (inputStream != null) {
                            inputStream.close();
                        }
                    } catch (IOException e) {
                    }
                }
            }
            return bitmap;
        }
    }

    public void clearCache() {
        synchronized (mDiskCacheLock) {
            mDiskCacheStarting = true;
            if (mDiskLruCache != null && !mDiskLruCache.isClosed()) {
                try {
                    mDiskLruCache.delete();
                    Log.d(TAG, "clearCache,mDiskLruCache.delete()");
                } catch (IOException e) {
                    Log.e(TAG, "clearCache, " + e);
                }
                mDiskLruCache = null;
                initDiskCache();
            }
        }
    }

    public void flush() {
        synchronized (mDiskCacheLock) {
            if (mDiskLruCache != null) {
                try {
                    mDiskLruCache.flush();
                    if (BuildConfig.DEBUG) {
                        Log.d(TAG, "flush,Disk cache flushed");
                    }
                } catch (IOException e) {
                    Log.e(TAG, "flush,e " + e);
                }
            }
        }
    }

    public void close() {
        synchronized (mDiskCacheLock) {
            if (mDiskLruCache != null) {
                try {
                    if (!mDiskLruCache.isClosed()) {
                        mDiskLruCache.close();
                        mDiskLruCache = null;
                        if (BuildConfig.DEBUG) {
                            Log.d(TAG, "close,Disk cache closed");
                        }
                    }
                } catch (IOException e) {
                    Log.e(TAG, "close," + e);
                }
            }
        }
    }
}