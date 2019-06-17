package com.hades.example.android.widget._list._recyclerview._dag_reorder_list.v2.screen_size;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;

public class ScreenSizeTool {
    private static final String TAG = ScreenSizeTool.class.getSimpleName();

    /**
     * 底部有虚拟按键
     * 屏幕真实高度： 包括虚拟按键 + 状态栏
     * 屏幕高度 = 屏幕真实高度- 虚拟导航栏
     * <p>
     * <p>
     * 底部没有虚拟按键
     * 屏幕真实高度： 包括状态栏，不包括虚拟按键
     * 屏幕高度 = 屏幕真实高度
     */
    public int getRealHeight(Context context) {
        if (hasNavigationBar(context)) {
            return getRealHeight4HavingSoftNavigationBar(context);
        } else {
            return getRealHeight4NotHavingSoftNavigationBar(context);
        }
    }

    public int getScreenHeight(Context context) {
        if (hasNavigationBar(context)) {
            return getScreenHeight4HavingSoftNavigationBar(context);
        } else {
            return getScreenHeight4NotHavingSoftNavigationBar(context);
        }
    }


    private int getRealHeight4NotHavingSoftNavigationBar(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        return dm.heightPixels;
    }

    private int getScreenHeight4NotHavingSoftNavigationBar(Context context) {
        return getRealHeight(context);
    }

    private int getRealHeight4HavingSoftNavigationBar(Context context) {
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

    private int getScreenHeight4HavingSoftNavigationBar(Context context) {
        return getRealHeight(context) - getNavigationBarHeight(context);
    }

    private boolean hasNavigationBar(Context context) {
        int id = context.getResources().getIdentifier("config_showNavigationBar", "bool", "android");
        return id > 0 && context.getResources().getBoolean(id);
    }

    /**
     * 状态栏高度
     */
    public int getStatusBarHeight(Activity context) {
//        // 方法1：
//        int statusBarHeight = 0;
//        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");// <dimen name="status_bar_height">@dimen/status_bar_height_portrait(24dp)</dimen>
//        if (resourceId > 0) {
//            statusBarHeight = context.getResources().getDimensionPixelSize(resourceId);
//        }
//        return statusBarHeight;

        // 方法2：
        Rect rect = new Rect();
        // 应用区的顶部 = 状态栏高度
        context.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        return rect.top;
    }

    /**
     * 应用区的高度 = 屏幕的高度 - 状态栏高度
     * <p>
     * 不能在 onCreate 方法中使用。
     * 因为这种方法依赖于WMS（窗口管理服务的回调）。正是因为窗口回调机制，所以在Activity初始化时执行此方法得到的高度是0。
     * 这个方法推荐在回调方法onWindowFocusChanged()中执行，才能得到预期结果。
     **/

    public int getAppViewHeight(Activity context) {
        // 方法1：
//        Rect rect = new Rect();
//        context.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
//        int  appViewHeight =  rect.height();
//        return appViewHeight;

        // 方法2：
        DisplayMetrics dm = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(dm);
        int appViewHeight = dm.heightPixels ;

        Log.d(TAG, "应用区高度:" + appViewHeight);
        return appViewHeight;
    }


    /**
     * 标题栏高度
     * 标题栏高度 = 应用区高度 - view 显示高度
     */
    public int getAppBarHeight(Activity activity) {
        int statusBarHeight = getStatusBarHeight(activity);

        int topOfView = activity.getWindow().findViewById(Window.ID_ANDROID_CONTENT).getTop(); //要用这种方法

        int titleBarHeight = topOfView - statusBarHeight;
        Log.d(TAG, "getAppBarHeight: viewTop " + topOfView + " - statusBarHeight " + statusBarHeight + "= titleBarHeight " + titleBarHeight);

        if (titleBarHeight < 0) {// no AppCompat title bar
            titleBarHeight = 0;
        }
        return titleBarHeight;
    }

    /**
     * view显示高度
     * = setContentView 高度
     * = Window.ID_ANDROID_CONTENT
     * = android.R.id.content
     */
    public int getContentViewHeight(Activity activity) {
        Rect rectangle = new Rect();
//        activity.getWindow().findViewById(Window.ID_ANDROID_CONTENT).getDrawingRect(rectangle);
        activity.getWindow().findViewById(android.R.id.content).getDrawingRect(rectangle);
        return rectangle.height();
    }

    /**
     * 虚拟按键高度
     */
    public int getNavigationBarHeight(Context context) {
        int navigationBarHeight = -1;
        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android"); // <dimen name="navigation_bar_height">48dp</dimen>
        if (resourceId > 0) {
            navigationBarHeight = resources.getDimensionPixelSize(resourceId);
        }
        return navigationBarHeight;
    }
}