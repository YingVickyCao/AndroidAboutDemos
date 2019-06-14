package com.hades.example.android.widget.screen_size;

//getNavigationBarHeight

import android.content.Context;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

/**
 * 底部有虚拟按键
 */
public class ScreenSizeToolHavingSoftNavigationBar extends BaseScreenSizeTool {
    /**
     * 屏幕真实高度： 包括虚拟按键 + 状态栏
     * 屏幕高度 = 屏幕真实高度- 虚拟按键 高度
     */

    // 屏幕真实高度
    public int getRealHeight(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        int screenHeight = 0;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) { // >=4.2
            DisplayMetrics dm = new DisplayMetrics();
            display.getRealMetrics(dm);
            screenHeight = dm.heightPixels;

            //或者也可以使用getRealSize方法
//      Point size = new Point();
//      display.getRealSize(size);
//      screenHeight = size.y;
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) { // >=4.0
            try {
                screenHeight = (Integer) Display.class.getMethod("getRawHeight").invoke(display);
            } catch (Exception e) {
                DisplayMetrics dm = new DisplayMetrics();
                display.getMetrics(dm);
                screenHeight = dm.heightPixels;
            }
        }
        return screenHeight;
    }

    public int getScreenHeight(Context context) {
        return getRealHeight(context) - getNavigationBarHeight(context);
    }
}