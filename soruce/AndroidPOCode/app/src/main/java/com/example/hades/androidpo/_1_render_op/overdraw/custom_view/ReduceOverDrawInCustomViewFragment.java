package com.example.hades.androidpo._1_render_op.overdraw.custom_view;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.PowerManager;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hades.androidpo.R;

public class ReduceOverDrawInCustomViewFragment extends Fragment {
    private static final String TAG = ReduceOverDrawInCustomViewFragment.class.getSimpleName();

    private int cardResId[] = {R.drawable.test3, R.drawable.test3, R.drawable.test3, R.drawable.test3};
    private MultiCardsView multicardsView = null;
    private PowerManager.WakeLock mWakeLock = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fm_overdraw, container, false);

        multicardsView = view.findViewById(R.id.cardview);
        multicardsView.enableOverdrawOpt(true);

        initMultiCardsView();
        view.findViewById(R.id.btn_overdraw).setOnClickListener(v -> enableOverdrawOpt(false));
        view.findViewById(R.id.btn_perfectdraw).setOnClickListener(v -> enableOverdrawOpt(true));
        return view;
    }

    private void initMultiCardsView() {
        int width = getResources().getDisplayMetrics().widthPixels;
        int height = getResources().getDisplayMetrics().heightPixels;
        // width=1440,height=2768
        Log.d(TAG, "initMultiCardsView:width=" + width + ",height=" + height);

        int cardWidth = width / 2;
        int cardHeight = height / 2;
        // cardWidth=720,cardHeight=1384
        Log.d(TAG, "initMultiCardsView:cardWidth=" + cardWidth + ",cardHeight=" + cardHeight);

        int yOffset = 40;
        int xOffset = 40;

        /**
         left=40,top=40,right=760,bottom=1424
         left=280,top=40,right=1000,bottom=1424
         left=520,top=40,right=1240,bottom=1424
         left=760,top=40,right=1480,bottom=1424
         */
        // SESSION:foreach
        for (int i = 0; i < cardResId.length; i++) {
            SingleCard cd = new SingleCard(new RectF(xOffset, yOffset, xOffset + cardWidth, yOffset + cardHeight));
            Log.d(TAG, "initMultiCardsView: left=" + xOffset + ",top=" + yOffset + ",right=" + (xOffset + cardWidth) + ",bottom=" + (yOffset + cardHeight));
            Bitmap bitmap = loadImageResource(cardResId[i], cardWidth, cardHeight);
            cd.setBitmap(bitmap);
            multicardsView.addCards(cd);
            xOffset += cardWidth / 3;
        }
    }

    private void enableOverdrawOpt(boolean enableOverdrawOpt) {
        multicardsView.enableOverdrawOpt(enableOverdrawOpt);
    }

    public Bitmap loadImageResource(int imageResId, int cardWidth, int cardHeight) {
        Bitmap bitmap = null;
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            options.inDensity = DisplayMetrics.DENSITY_DEFAULT;
            options.inTargetDensity = DisplayMetrics.DENSITY_DEFAULT;
            options.inScreenDensity = DisplayMetrics.DENSITY_DEFAULT;
            BitmapFactory.decodeResource(getResources(), imageResId, options);

            options.inJustDecodeBounds = false;
            options.inSampleSize = findBestSampleSize(options.outWidth, options.outHeight, cardWidth, cardHeight);
            bitmap = BitmapFactory.decodeResource(getResources(), imageResId, options);
        } catch (OutOfMemoryError exception) {
        }
        return bitmap;
    }

    /**
     * Returns the largest power-of-two divisor for use in downscaling a bitmap
     * that will not result in the scaling past the desired dimensions.
     *
     * @param actualWidth   Actual width of the bitmap
     * @param actualHeight  Actual height of the bitmap
     * @param desiredWidth  Desired width of the bitmap
     * @param desiredHeight Desired height of the bitmap
     */
    public static int findBestSampleSize(
            int actualWidth, int actualHeight, int desiredWidth, int desiredHeight) {
        double wr = (double) actualWidth / desiredWidth;
        double hr = (double) actualHeight / desiredHeight;
        double ratio = Math.min(wr, hr);
        float n = 1.0f;
        while ((n * 2) <= ratio) {
            n *= 2;
        }

        return (int) n;
    }

    private void acquireWakeLock(Context ctx) {
        if (null == mWakeLock) {
            PowerManager pm = (PowerManager) ctx.getSystemService(Context.POWER_SERVICE);
            mWakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK | PowerManager.ON_AFTER_RELEASE, "TestLocknService");
            if (null != mWakeLock) {
                mWakeLock.acquire();
            }
        }
    }

    //释放设备电源锁
    private void releaseWakeLock() {
        if (null != mWakeLock) {
            mWakeLock.release();
            mWakeLock = null;
        }
    }
}
