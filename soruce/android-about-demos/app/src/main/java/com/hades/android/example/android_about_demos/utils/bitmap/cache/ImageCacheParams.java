package com.hades.android.example.android_about_demos.utils.bitmap.cache;

import android.content.Context;
import android.graphics.Bitmap;

import com.hades.android.example.android_about_demos.utils.bitmap.cache.disk.DiskCache;
import com.hades.android.example.android_about_demos.utils.bitmap.cache.mememory.MemoryCache;
import com.hades.android.example.android_about_demos.utils.common.FileUtil;

import java.io.File;

/**
 * A holder class that contains cache parameters.
 */
public class ImageCacheParams {
    public int memCacheSize = MemoryCache.DEFAULT_MEMORY_CACHE_KILOBYTES_SIZE;
    public int diskCacheSize = DiskCache.DEFAULT_DISK_CACHE_BYTES_SIZE;
    public File diskCacheDir;
    public Bitmap.CompressFormat compressFormat = DiskCache.DEFAULT_COMPRESS_FORMAT;
    public int compressQuality = ImageCache.DEFAULT_COMPRESS_QUALITY;
    public boolean memoryCacheEnabled = MemoryCache.DEFAULT_MEMORY_CACHE_ENABLED;
    public boolean diskCacheEnabled = DiskCache.DEFAULT_DISK_CACHE_ENABLED;
    public boolean initDiskCacheOnCreate = DiskCache.DEFAULT_INIT_DISK_CACHE_ON_CREATE;

    FileUtil fileUtil = new FileUtil();

    public ImageCacheParams(Context context, String diskCacheDirectoryName) {
        diskCacheDir = fileUtil.getDiskCacheDir(context, diskCacheDirectoryName);
    }

    public ImageCacheParams(Context context, String diskCacheDirectoryName, float percentOfAppMemory) {
        diskCacheDir = fileUtil.getDiskCacheDir(context, diskCacheDirectoryName);
        memCacheSize = setMemCacheSizePercent(percentOfAppMemory);
    }

    public int setMemCacheSizePercent(float percentOfAppMemory) {
        if (percentOfAppMemory < 0.01f || percentOfAppMemory > 0.8f) {
            throw new IllegalArgumentException("setMemCacheSizePercent - percent must be between 0.01 and 0.8 (inclusive)");
        }
        return Math.round(percentOfAppMemory * getAppMemory() / 1024);
    }

    private float getAppMemory() {
        return Runtime.getRuntime().maxMemory();
    }

}
