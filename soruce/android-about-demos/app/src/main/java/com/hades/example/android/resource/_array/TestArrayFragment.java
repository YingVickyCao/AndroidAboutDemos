package com.hades.example.android.resource._array;

import android.app.Fragment;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.hades.example.android.R;

/**
 * https://www.cnblogs.com/andriod-html5/archive/2012/04/30/2539419.html
 */
public class TestArrayFragment extends Fragment {
    private static final String TAG = TestArrayFragment.class.getSimpleName();

    Button normalArrayBtn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.res_array, container, false);
        normalArrayBtn = view.findViewById(R.id.getNormalArray);
        normalArrayBtn.setOnClickListener(v -> getNormalArray());
        view.findViewById(R.id.getStringArray).setOnClickListener(v -> getStringArray());
        view.findViewById(R.id.getIntArray).setOnClickListener(v -> getIntArray());
        return view;
    }

    private void getNormalArray() {
        TypedArray typedArray = getResources().obtainTypedArray(R.array.normal_array);
//        Drawable drawable = typedArray.getDrawable(0);
//        normalArrayBtn.setBackground(drawable);

        int color = typedArray.getColor(1, 0);
        normalArrayBtn.setBackgroundColor(color);
    }

    private void getStringArray() {
        /**
         * getResources().getStringArray()
         */
        String[] strings = getResources().getStringArray(R.array.string_array);
        String str = "";
        for (int i = 0; i < strings.length; i++) {
            str += strings[i] + ",";
        }
        Log.d(TAG, "getStringArray: " + str);
    }

    private void getIntArray() {
        /**
         * getResources().getIntArray()
         */
        int[] ints = getResources().getIntArray(R.array.int_array);
        String str = "";
        for (int i = 0; i < ints.length; i++) {
            str += ints[i] + ",";
        }
        Log.d(TAG, "getIntArray: " + str);
    }

}
