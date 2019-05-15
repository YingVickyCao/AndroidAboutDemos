package com.hades.example.android.resource.drawable._bitmap;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.hades.example.android.R;
import com.hades.example.android.lib.base.BaseFragment;

public class TestBitmapMemoryAndScreenDensityFragment extends BaseFragment {
    private static final String TAG = TestBitmapMemoryAndScreenDensityFragment.class.getSimpleName();

    private ImageView mImageView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.res_bitmap_memory_and_screen_density, container, false);
        view.findViewById(R.id.getScreenDensity).setOnClickListener(v -> getScreenDensity());
        view.findViewById(R.id.checkDrawableMemory).setOnClickListener(v -> checkDrawableMemory());
        mImageView = view.findViewById(R.id.imageView);
        return view;
    }

    private void getScreenDensity() {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        float density = displayMetrics.density;// 屏幕密度（基数倍数）
        int xdpi = (int) displayMetrics.xdpi;   // 宽度方向上的ppi
        int ydpi = (int) displayMetrics.ydpi;   // 高度方向上的ppi

        int densityDpi = displayMetrics.densityDpi;//  android默认的屏幕像素密度 ppi
        int widthPixels = displayMetrics.widthPixels; // 屏幕宽度的像素 px
        int heightPixels = displayMetrics.heightPixels;// 屏幕高度的像素 px

        float scaledDensity = displayMetrics.scaledDensity;// 字体的放大系数

        // Galaxy Note4, 5.7inch，2560 X1440， 【实际计算】屏幕像素密度 = 515pi
        // (1) density=4.0,xdpi=508,ydpi=516,densityDpi=640,widthPixels=1440,heightPixels=2560,scaledDensity=4.0
        // (2) adb shell wm size
        //     Physical size: 1440x2560
        // (3) adb shell wm density
        //     Physical density: 640

        // 带soft导航键时，heightPixels 小于 实际标注的值。
        Log.d(TAG, "getScreenDensity: density=" + density + ",xdpi=" + xdpi + ",ydpi=" + ydpi
                + ",densityDpi=" + densityDpi + ",widthPixels=" + widthPixels + ",heightPixels=" + heightPixels
                + ",scaledDensity=" + scaledDensity);
    }

    /*
     // HuaWei nova, 1080 x 1092 =>
        // xxxh: bitmapWidth=203,bitmapHeight=360,byteCount=292320,width=203,height=360
        // xxh:  bitmapWidth=270,bitmapHeight=480,byteCount=518400,width=270,height=480
        // xh:   bitmapWidth=405,bitmapHeight=720,byteCount=1166400,width=405,height=720
        // no:   bitmapWidth=270,bitmapHeight=480,byteCount=518400,width=270,height=480
        // h:    bitmapWidth=540,bitmapHeight=960,byteCount=2073600,width=540,height=960
        // m:    bitmapWidth=810,bitmapHeight=1440,byteCount=4665600,width=810,height=1440
        // l:    bitmapWidth=1080,bitmapHeight=1920,byteCount=8294400,width=1080,height=1548


        same image
        in /drawable:
        checkDrawableMemory: bitmapWidth=384,bitmapHeight=384,byteCount=589824,width=384,height=384

        in /drawable-mdpi:
        checkDrawableMemory: bitmapWidth=384,bitmapHeight=384,byteCount=589824,width=384,height=384
     */
    private void checkDrawableMemory() {
        BitmapDrawable drawable = (BitmapDrawable) mImageView.getDrawable();
        if (null != drawable) {
            Bitmap bitmap = drawable.getBitmap();
            int bitmapWidth = bitmap.getWidth();
            int bitmapHeight = bitmap.getHeight();
            int bitmapByteCount = bitmap.getByteCount();
            int width = mImageView.getWidth();
            int height = mImageView.getHeight();
            Log.d(TAG, "checkDrawableMemory: bitmapWidth=" + bitmapWidth + ",bitmapHeight=" + bitmapHeight + ",byteCount=" + bitmapByteCount + ",width=" + width + ",height=" + height);
        }
    }
}