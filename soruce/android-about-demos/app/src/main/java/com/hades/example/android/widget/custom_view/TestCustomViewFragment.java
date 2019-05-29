package com.hades.example.android.widget.custom_view;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.hades.example.android.R;

public class TestCustomViewFragment extends Fragment {
    private static final String TAG = TestCustomViewFragment.class.getSimpleName();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.custom_view_path, container, false);
//        View view = inflater.inflate(R.layout.custom_view, container, false);
//        View view = inflater.inflate(R.layout.custom_view_canvas_left_position_1, container, false);
//        View view = inflater.inflate(R.layout.custom_view_canvas_left_position_2, container, false);
        getScreenDensity();
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

        Log.d(TAG, "getScreenDensity: density=" + density + ",xdpi=" + xdpi + ",ydpi=" + ydpi + ",densityDpi=" + densityDpi + ",widthPixels=" + widthPixels + ",heightPixels=" + heightPixels
                + ",scaledDensity=" + scaledDensity);
    }
}
