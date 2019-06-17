package com.hades.example.android.widget._list._recyclerview._dag_reorder_list.v2.screen_size;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.hades.example.android.R;

/**
 * https://www.jb51.net/article/122684.htm
 */
public class TestViewLocationFragment extends Fragment {
    private static final String TAG = TestViewLocationFragment.class.getSimpleName();
    private View root;
    private View ll;
    private View btn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.widget_view_location, container, false);
        root = view.findViewById(R.id.root);
        ll = view.findViewById(R.id.ll);
        btn = view.findViewById(R.id.btn);

        btn.setOnClickListener(v -> printLocations());
        root.setOnSystemUiVisibilityChangeListener(visibility -> {
            if (((visibility & View.SYSTEM_UI_FLAG_LOW_PROFILE) == 0)
                    || ((visibility & View.SYSTEM_UI_FLAG_HIDE_NAVIGATION) == 0)
                    || ((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0)) {
                Log.d(TAG, "onSystemUiVisibilityChange: system bars are visible"); // system bars are visible
            } else {
                Log.d(TAG, "onSystemUiVisibilityChange: system bars are not visible");
            }
        });

        Log.d(TAG, "onCreateView: "+(hasNavigationBar() ? "Has navigation bar":"Not hav navigation bar"));
        return view;
    }

    /*

    Setting-> 显示 屏幕分辨率

    adb shell wm size
    Physical size: 1440x3040  默认分辨率
    Override size: 1080x2280  修改分辨率
   */
    private void printScreenDensity() {
        ScreenSizeTool sizeTool = new ScreenSizeTool();
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int height = displayMetrics.heightPixels;// 屏幕高度的像素 px
        Log.d(TAG, "printScreenDensity:height=" + height);//2042

        Log.d(TAG, "printScreenDensity: 状态栏高度=" + sizeTool.getStatusBarHeight(getActivity())); // 状态栏高度=112
        Log.d(TAG, "printScreenDensity: 虚拟按键高度=" + sizeTool.getNavigationBarHeight(getContext())); // 虚拟按键高度=126

        Log.d(TAG, "printScreenDensity: 屏幕真实高度=" + sizeTool.getRealHeight(getContext())); // 屏幕真实高度=2280
        Log.d(TAG, "printScreenDensity: 屏幕高度：  =" + sizeTool.getScreenHeight(getContext())); // 屏幕高度： 底部有虚拟按键 =2154
        Log.d(TAG, "printScreenDensity: 标题栏高度=" + sizeTool.getAppBarHeight(getActivity())); // 标题栏高度=35
        Log.d(TAG, "printScreenDensity: 应用区域高度=" + sizeTool.getAppViewHeight(getActivity())); // 应用区域高度=2042

        Log.d(TAG, "printScreenDensity: view 显示的高度=" + sizeTool.getContentViewHeight(getActivity())); // view 显示的高度=1895
        Log.d(TAG, "printScreenDensity: view 显示的高度=" + root.getHeight());   // view 显示的高度=1895
    }


    private void printLocations() {
        printScreenDensity();
        printLocation();
    }

    private void printLocation() {
        int[] locationInWindow = new int[2];
        root.getLocationInWindow(locationInWindow);
        Log.d(TAG, "printScreenDensity: locationInWindow=" + print(locationInWindow));// locationInWindow=[0,0]

        int[] locationOnScreen = new int[2];
        root.getLocationOnScreen(locationOnScreen);
        Log.d(TAG, "printScreenDensity: locationOnScreen=" + print(locationOnScreen)); // locationOnScreen=[0,0]
    }

    private void printMotionEvent(MotionEvent event) {
        Log.d(TAG, "printMotionEvent: event action=" + event.getAction() + ",rawX=" + event.getRawX() + ",rawY=" + event.getRawY());
    }

    private String print(int[] location) {
        String str = "[";
        str += location[0];
        str += ",";
        str += location[1];
        str += "]";
        return str;
    }

    public boolean hasNavigationBar() {
        int id = getResources().getIdentifier("config_showNavigationBar", "bool", "android");
        return id > 0 && getResources().getBoolean(id);
    }
}