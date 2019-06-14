package com.hades.example.android.widget.screen_size;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Window;

public abstract class BaseScreenSizeTool {
    private static final String TAG = BaseScreenSizeTool.class.getSimpleName();

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

    public abstract int getRealHeight(Context context);

    /**
     * 屏幕高度
     */
    public abstract int getScreenHeight(Context context);

    /**
     * 应用区的高度 = 屏幕的高度 - 状态栏高度
     * <p>
     * 不能在 onCreate 方法中使用。
     * 因为这种方法依赖于WMS（窗口管理服务的回调）。正是因为窗口回调机制，所以在Activity初始化时执行此方法得到的高度是0。
     * 这个方法推荐在回调方法onWindowFocusChanged()中执行，才能得到预期结果。
     */

    public int getAppViewHeight(Activity context) {
        // 方法1：
//        Rect rect = new Rect();
//        context.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
//        int  appViewHeight =  rect.height();
//        return appViewHeight;

        // 方法2：
        DisplayMetrics dm = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(dm);

        Rect outRect1 = new Rect();
        int appViewHeight = dm.heightPixels - outRect1.height(); //应用区域高度 =  屏幕高度 -状态栏高度

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