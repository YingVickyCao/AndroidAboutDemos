package com.example.android.displayingbitmaps.util;

import android.annotation.TargetApi;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;

import java.io.FileDescriptor;

public class ImageUtil {

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public int getBitmapBytesSizeAtBitmapDrawable(BitmapDrawable value) {
        Bitmap bitmap = value.getBitmap();
        if (Utils.isVersionNoLessThan4_4()) {
            return bitmap.getAllocationByteCount();
        }
        if (Utils.isVersionNoLessThan3_1()) {
            return bitmap.getByteCount();
        }
        return bitmap.getRowBytes() * bitmap.getHeight();
    }

    public Bitmap decodeSampledBitmapFromDescriptor(FileDescriptor fileDescriptor, int reqWidth, int reqHeight, IInBitmapListener listener) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFileDescriptor(fileDescriptor, null, options);
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        useInBitmap(options, listener);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFileDescriptor(fileDescriptor, null, options);
    }

    public Bitmap decodeSampledBitmapFromResource(Resources res, int resId, int reqWidth, int reqHeight, IInBitmapListener listener) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);
        // PO:[Bitmap] BitmapFactory.Options.inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        useInBitmap(options, listener);
        options.inJustDecodeBounds = false;

        return BitmapFactory.decodeResource(res, resId, options);
    }

    public Bitmap decodeSampledBitmapFromFile(String filename, int reqWidth, int reqHeight, IInBitmapListener listener) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filename, options);
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        useInBitmap(options, listener);
        options.inJustDecodeBounds = false;

        return BitmapFactory.decodeFile(filename, options);
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

    private void useInBitmap(final BitmapFactory.Options options, IInBitmapListener listener) {
        if (Utils.isVersionNoLessThan3_0() && null != listener) {
            addInBitmapOptions(options, listener);
        }
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void addInBitmapOptions(BitmapFactory.Options options, IInBitmapListener listener) {
        Bitmap inBitmap = listener.check(options);
        if (inBitmap != null) {
            options.inMutable = true;
            // PO:[Bitmap] BitmapFactory.Options.inBitmap
            options.inBitmap = inBitmap;
        }
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public boolean canUsedForInBitmapReuseWithTargetOptions(Bitmap candidate, BitmapFactory.Options targetOptions) {
        if (Utils.isVersionNoLessThan4_4()) {
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

    // PO:[Bitmap] Bitmap.Config.ARGB_4444
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
