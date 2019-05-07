package com.hades.example.android.resource._style_theme;

import android.os.Build;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.ArrayRes;
import androidx.annotation.Nullable;

import com.hades.example.android.R;
import com.hades.example.android.lib.base.BaseFragment;

public class TestAttrInThemeFragment extends BaseFragment {
    private static final String TAG = TestAttrInThemeFragment.class.getSimpleName();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.res_theme_4_attr, container, false);

        colorInIntegerArray(view);
        loadAttrFromStyle(view);
        return view;
    }

    private void colorInIntegerArray(View view) {
        TextView color1 = view.findViewById(R.id.color1);
        TextView color2 = view.findViewById(R.id.color2);
        getIntArrayColors(R.array.colors_integer_array, color1, color2);

        TypedValue typedValue = new TypedValue();
        getActivity().getTheme().resolveAttribute(R.attr.ColorsIntegerArray, typedValue, true);
        int resourceId = typedValue.resourceId;
        TextView color3 = view.findViewById(R.id.color3);
        TextView color4 = view.findViewById(R.id.color4);
        getIntArrayColors(resourceId, color3, color4);
    }

    private void getIntArrayColors(@ArrayRes int id, TextView color1, TextView color2) {
        /**
         * getResources().getIntArray()
         */
        int[] ints = getResources().getIntArray(id);
        color1.setBackgroundColor(ints[0]);
        color2.setBackgroundColor(ints[1]);
    }

    private void loadAttrFromStyle(View view) {
        TextView result = view.findViewById(R.id.loadAttrFromStyle);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            result.setTextAppearance(R.style.GreenText_Large);
        } else {
            result.setTextAppearance(getActivity(), R.style.GreenText_Large);
        }
    }
}
