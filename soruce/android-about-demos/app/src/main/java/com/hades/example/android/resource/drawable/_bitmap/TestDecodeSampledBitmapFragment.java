package com.hades.example.android.resource.drawable._bitmap;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;

import com.hades.example.android.R;
import com.hades.example.android.lib.base.BaseFragment;
import com.hades.example.android.lib.utils.ImageUtil;

public class TestDecodeSampledBitmapFragment extends BaseFragment {
    private static final String TAG = TestDecodeSampledBitmapFragment.class.getSimpleName();

    private ImageView mImageView;
    private ImageUtil bitmapUtil = new ImageUtil();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.res_bitmap_decode, container, false);
        mImageView = view.findViewById(R.id.imageView);
        view.findViewById(R.id.clear).setOnClickListener(v -> clear());
        view.findViewById(R.id.setImageResource).setOnClickListener(v -> setImageResource());
        view.findViewById(R.id.decodeResource).setOnClickListener(v -> decodeResource());
        view.findViewById(R.id.decodeResourceWithSampled).setOnClickListener(v -> decodeResourceWithSampled2());
        view.findViewById(R.id.decodeFile).setOnClickListener(v -> decodeFile());
        return view;
    }

    private void clear() {
        mImageView.getLayoutParams().height = RelativeLayout.LayoutParams.WRAP_CONTENT;
        mImageView.getLayoutParams().width = RelativeLayout.LayoutParams.MATCH_PARENT;
        mImageView.requestLayout();

        mImageView.setImageResource(android.R.color.holo_orange_light);
    }

    /**
     * checkDrawableMemory: [Bitmap]: Width=5120,Height=2880,ByteCount=58982400,[ImageView]: width=1440, height=1460
     */
    private void setImageResource() {
        mImageView.setImageResource(R.drawable.wallpaper);
        checkDrawableMemory();
    }

    private void decodeFile() {
        // ERROR:BitmapFactory: Unable to decode stream: java.io.FileNotFoundException: /sdcard/wallpaper.jpg (Permission denied)
        // ERROR:BitmapFactory: Unable to decode stream: java.io.FileNotFoundException: /storage/emulated/0/wallpaper.jpg (Permission denied)
//        Bitmap bitmap = BitmapFactory.decodeFile(FILE_PATH_NAME);
    }

    /**
     * checkDrawableMemory: [Bitmap]: Width=5120,Height=2880,ByteCount=58982400,[ImageView]: width=1440, height=1460
     */
    private void decodeResource() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.wallpaper);
        mImageView.setImageBitmap(bitmap);
        checkDrawableMemory();
    }

    /**
     * checkDrawableMemory: [Bitmap]: Width=2560,Height=1440,ByteCount=14745600,[ImageView]: width=1440, height=2520
     */
    private void decodeResourceWithSampled2() {
        int requireWidth = mImageView.getWidth();
        int requireHeight = bitmapUtil.getRequireHeight(getResources(), R.drawable.wallpaper, mImageView.getWidth());
        Bitmap bitmap2 = bitmapUtil.decodeSampledBitmapFromResource(getResources(), R.drawable.wallpaper, requireWidth, requireHeight);
        mImageView.setImageBitmap(bitmap2);
        checkDrawableMemory();
    }

    /**
     * checkDrawableMemory: [Bitmap]: Width=2560,Height=1440,ByteCount=14745600,[ImageView]: width=1440, height=400
     */
    private void decodeResourceWithSampled() {
        BitmapFactory.Options options = new BitmapFactory.Options();
        // First decode with inJustDecodeBounds=true to check dimensions
        options.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.wallpaper, options);
        int outWidth = options.outWidth;
        int outHeight = options.outHeight;
        Log.d(TAG, "decodeSampledBitmapFromResource: [Bitmap] outWidth=" + outWidth + ",outHeight=" + outHeight);

        int requireWidth = mImageView.getWidth();
        int requireHeight = mImageView.getWidth() * outHeight / outWidth;

        Log.d(TAG, "decodeSampledBitmapFromResource:[ImageView] width=" + mImageView.getWidth() + ",height=" + mImageView.getHeight());
        mImageView.getLayoutParams().height = requireHeight;
        mImageView.requestLayout();
        Log.d(TAG, "decodeSampledBitmapFromResource:[Bitmap]=" + bitmap + ",requireWidth=" + requireWidth + ",requireHeight=" + requireHeight);
        Log.d(TAG, "decodeSampledBitmapFromResource:[ImageView] width=" + mImageView.getWidth() + ",height=" + mImageView.getHeight());

        // Calculate inSampleSize
        Log.d(TAG, "decodeSampledBitmapFromResource: options.inSampleSize=" + options.inSampleSize);
        options.inSampleSize = calculateInSampleSize(options, requireWidth, requireHeight);
        Log.d(TAG, "decodeSampledBitmapFromResource: options.inSampleSize=" + options.inSampleSize);
        Log.d(TAG, "decodeSampledBitmapFromResource: [Bitmap] outWidth=" + outWidth + ",outHeight=" + outHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(), R.drawable.wallpaper, options);

        mImageView.setImageBitmap(bitmap2);
        checkDrawableMemory();
    }

    private int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) >= reqHeight && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    private void checkDrawableMemory() {
        BitmapDrawable drawable = (BitmapDrawable) mImageView.getDrawable();
        if (null != drawable) {
            Bitmap bitmap = drawable.getBitmap();
            int bitmapWidth = bitmap.getWidth();
            int bitmapHeight = bitmap.getHeight();
            int bitmapByteCount = bitmap.getByteCount();

            int width = mImageView.getWidth();
            int height = mImageView.getHeight();
            Log.d(TAG, "checkDrawableMemory: [Bitmap]: Width=" + bitmapWidth + ",Height=" + bitmapHeight + ",ByteCount=" + bitmapByteCount + ",[ImageView]: width=" + width + ", height=" + height);
        }
    }
}