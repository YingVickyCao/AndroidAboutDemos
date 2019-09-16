package com.hades.example.android.resource._style_theme;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.ArrayRes;
import androidx.annotation.Nullable;

import com.hades.example.android.R;
import com.hades.example.android.lib.base.BaseFragment;
import com.hades.example.android.lib.utils.ThemeUtils;

public class TestAttrInThemeFragment extends BaseFragment {
    private static final String TAG = TestAttrInThemeFragment.class.getSimpleName();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.res_theme_4_attr, container, false);

        setTextColor(view);
        useStyle(view);

        return view;
    }

    private void useStyle(View view) {
        TextView result = view.findViewById(R.id.useStyle);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            result.setTextAppearance(R.style.GreenText_Large);
        } else {
            result.setTextAppearance(getActivity(), R.style.GreenText_Large);
        }
    }

    private void setTextColor(View view) {
        TextView color1 = view.findViewById(R.id.color1);
        TextView color2 = view.findViewById(R.id.color2);
        getTextColors(R.array.colors_integer_array, color1, color2);

        TextView color3 = view.findViewById(R.id.color3);
        TextView color4 = view.findViewById(R.id.color4);
        getTextColors(ThemeUtils.getResourceIdByAttrId(getActivity(), R.attr.ColorsIntegerArray), color3, color4);
    }

    private void getTextColors(@ArrayRes int id, TextView color1, TextView color2) {
        /**
         * getResources().getIntArray()
         */
        int[] ints = getResources().getIntArray(id);
        color1.setBackgroundColor(ints[0]);

        color2.setBackgroundColor(ints[1]);
    }
}