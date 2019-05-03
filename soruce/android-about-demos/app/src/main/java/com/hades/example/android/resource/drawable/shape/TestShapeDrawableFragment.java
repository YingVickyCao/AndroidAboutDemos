package com.hades.example.android.resource.drawable.shape;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.hades.example.android.R;
import com.hades.example.android.lib.base.BaseFragment;

public class TestShapeDrawableFragment extends BaseFragment {
    private static final String TAG = TestShapeDrawableFragment.class.getSimpleName();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.res_drawable_shape, container, false);
        view.findViewById(R.id.checkIntrinsicHeight).setOnClickListener(v -> checkIntrinsicHeight());
        return view;
    }

    private void checkIntrinsicHeight() {
        // 5dp -> px=20
        /**
         * 20,20,20
         * No matter Shape Drawable put in any drawable folder, 不进行放缩
         */
        Drawable drawable = getResources().getDrawable(R.drawable.drawable_shape_4_divider_vertical);
        Log.d(TAG, "checkIntrinsicHeight:drawable intrinsicHeight=" + drawable.getIntrinsicHeight());
        Log.d(TAG, "checkIntrinsicHeight:drawable minimumHeight=" + drawable.getMinimumHeight());
        Log.d(TAG, "checkIntrinsicHeight:R.dimen.size_5= " + getResources().getDimension(R.dimen.size_5));
    }
}
