package com.hades.example.android.widget.custom_view;

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

        getActivity().runOnUiThread(() -> printLocations());
//        root.setOnTouchListener((v, event) -> {
//            printMotionEvent(event);
//            return false;
//        });
        return view;
    }

    private void printScreenDensity() {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int height = displayMetrics.heightPixels;// 屏幕高度的像素 px
        Log.d(TAG, "printScreenDensity:height=" + height);//2042
    }


    private void printLocations() {
        printScreenDensity();
        printLocationInWindow();
    }

    private void printLocationInWindow() {
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
}
