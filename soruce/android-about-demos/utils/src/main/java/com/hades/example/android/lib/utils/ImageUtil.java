package com.hades.example.android.lib.utils;

import android.annotation.TargetApi;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;

import com.hades.example.android.lib.utils.bitmap.fetch.IInBitmapListener;

import java.io.FileDescriptor;

public class ImageUtil {

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public int getBitmapBytesSizeAtBitmapDrawable(BitmapDrawable value) {
        // PO:[Bitmap] get bitmap bytes size - bitmap.getAllocationByteCount()
        Bitmap bitmap = value.getBitmap();
        if (VersionUtil.isVersionNoLessThan4_4()) {
            return bitmap.getAllocationByteCount();
        }

        // PO:[Bitmap] get bitmap bytes size - bitmap.getByteCount()
        if (VersionUtil.isVersionNoLessThan3_1()) {
            return bitmap.getByteCount();
        }

        // PO:[Bitmap] get bitmap bytes size - bitmap.getRowBytes() * bitmap.getHeight()
        return bitmap.getRowBytes() * bitmap.getHeight();
    }

    public Bitmap decodeSampledBitmapFromDescriptor(FileDescriptor fileDescriptor, int reqWidth, int reqHeight) {
        return decodeSampledBitmapFromDescriptor(fileDescriptor, reqWidth, reqHeight);
    }

    public Bitmap decodeSampledBitmapFromDescriptor(FileDescriptor fileDescriptor, int reqWidth, int reqHeight, IInBitmapListener listener) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFileDescriptor(fileDescriptor, null, options);
        if (null != listener) {
            options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
            useInBitmap(options, listener);
        }
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFileDescriptor(fileDescriptor, null, options);
    }

    public Bitmap decodeSampledBitmapFromResource(Resources res, int resId, int reqWidth, int reqHeight) {
        return decodeSampledBitmapFromResource(res, resId, reqWidth, reqHeight, null);
    }

//    public Bitmap decodeSampledBitmapFromResource(Resources res, int id, int requireWidth, int requireHeight) {
//        BitmapFactory.Options options = new BitmapFactory.Options();
//        options.inJustDecodeBounds = true;
//        BitmapFactory.decodeResource(res, id, options);
//        options.inSampleSize = calculateInSampleSize(options, requireWidth, requireHeight);
//        options.inJustDecodeBounds = false;
//        return BitmapFactory.decodeResource(res, id, options);
//    }


    public Bitmap decodeSampledBitmapFromResource(Resources res, int resId, int reqWidth, int reqHeight, IInBitmapListener listener) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);
        if (null != listener) {
            // PO:[Bitmap] BitmapFactory.Options.inSampleSize
            options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
            useInBitmap(options, listener);
        }
        options.inJustDecodeBounds = false;

        return BitmapFactory.decodeResource(res, resId, options);
    }

    public Bitmap decodeSampledBitmapFromFile(String filename, int reqWidth, int reqHeight) {
        return decodeSampledBitmapFromFile(filename, reqWidth, reqHeight, null);
    }

    public Bitmap decodeSampledBitmapFromFile(String filename, int reqWidth, int reqHeight, IInBitmapListener listener) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filename, options);
        if (null != listener) {
            options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
            useInBitmap(options, listener);
        }
        options.inJustDecodeBounds = false;

        return BitmapFactory.decodeFile(filename, options);
    }

    public int getRequireHeight(Resources res, int id, int originImageViewWidth) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, id, options);
        options.inJustDecodeBounds = false;
        return originImageViewWidth * options.outHeight / options.outWidth;
    }

    public int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
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
        if (VersionUtil.isNoLessThanV3() && null != listener) {
            addInBitmapOptions(options, listener);
        }
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void addInBitmapOptions(BitmapFactory.Options options, IInBitmapListener listener) {
        if (!VersionUtil.isNoLessThanV3()) {
            return;
        }
        Bitmap inBitmap = listener.getReusableBitmap4InBitmap(options);
        if (inBitmap != null) {
            options.inMutable = true;
            // PO:[Bitmap] BitmapFactory.Options.inBitmap
            options.inBitmap = inBitmap;
        }
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public boolean canUsedForInBitmapReuseWithTargetOptions(Bitmap candidate, BitmapFactory.Options targetOptions) {
        if (VersionUtil.isVersionNoLessThan4_4()) {
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
