package com.example.android.displayingbitmaps.util;

import android.annotation.TargetApi;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;

import java.io.FileDescriptor;

public class ImageUtil {
    public Bitmap decodeSampledBitmapFromDescriptor(FileDescriptor fileDescriptor, int reqWidth, int reqHeight, MemoryCache cache) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFileDescriptor(fileDescriptor, null, options);
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        options.inJustDecodeBounds = false;
        useInBitmap(options, cache);
        return BitmapFactory.decodeFileDescriptor(fileDescriptor, null, options);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void addInBitmapOptions(BitmapFactory.Options options, MemoryCache cache) {
        if (cache != null) {
            Bitmap inBitmap = cache.getBitmapFromReusableSet(options);
            if (inBitmap != null) {
                options.inMutable = true;
                options.inBitmap = inBitmap;
            }
        }
    }

    private static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            while ((halfHeight / inSampleSize) > reqHeight && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }

            long totalPixels = width * height / inSampleSize;
            final long totalReqPixelsCap = reqWidth * reqHeight * 2;
            while (totalPixels > totalReqPixelsCap) {
                inSampleSize *= 2;
                totalPixels /= 2;
            }
        }
        return inSampleSize;
    }

    public Bitmap decodeSampledBitmapFromFile(String filename, int reqWidth, int reqHeight, MemoryCache cache) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filename, options);
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        useInBitmap(options, cache);
        options.inJustDecodeBounds = false;

        return BitmapFactory.decodeFile(filename, options);
    }

    private void useInBitmap(final BitmapFactory.Options options, MemoryCache cache) {
        if (Utils.isVersionNoLessThanHoneycomb()) {
            addInBitmapOptions(options, cache);
        }
    }

    public Bitmap decodeSampledBitmapFromResource(Resources res, int resId, int reqWidth, int reqHeight, MemoryCache cache) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        useInBitmap(options, cache);
        options.inJustDecodeBounds = false;

        return BitmapFactory.decodeResource(res, resId, options);
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public boolean canUsedForInBitmapReuseWithTargetOptions(Bitmap candidate, BitmapFactory.Options targetOptions) {
        if (Utils.isVersionNoLessThanKitKat()) {
            // From Android 4.4 (KitKat) onward we can re-use if the byte size of the new bitmap is smaller than the reusable bitmap candidate allocation byte count.
            int width = targetOptions.outWidth / targetOptions.inSampleSize;
            int height = targetOptions.outHeight / targetOptions.inSampleSize;
            int byteCount = width * height * getBytesPerPixel(candidate.getConfig());
            return byteCount <= candidate.getAllocationByteCount();
        } else {
            // On earlier versions, the dimensions must match exactly and the inSampleSize must be 1
            return candidate.getWidth() == targetOptions.outWidth && candidate.getHeight() == targetOptions.outHeight && targetOptions.inSampleSize == 1;
        }
    }

    public int getBytesPerPixel(Bitmap.Config config) {
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
}
