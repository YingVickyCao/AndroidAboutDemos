package com.hades.example.android.widget.screen_size;

//getNavigationBarHeight

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * 底部没有虚拟按键
 */
public class ScreenSizeToolNotHavingSoftNavigationBar extends BaseScreenSizeTool {

    @Override
    public int getRealHeight(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        return dm.heightPixels;
    }

    public int getScreenHeight(Context context) {
        return getRealHeight(context);
    }
}