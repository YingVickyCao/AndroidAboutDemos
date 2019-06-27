package com.hades.example.android.resource.dimension;

import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.hades.example.android.R;

/**
 * https://www.cnblogs.com/touko/p/6478851.html
 */
public class TestDimensionFragment extends Fragment {
    private static final String TAG = TestDimensionFragment.class.getSimpleName();

    private float mPx;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.res_dimension, container, false);
        view.findViewById(R.id.px2dp).setOnClickListener(v -> px2dp());
        view.findViewById(R.id.dp2px).setOnClickListener(v -> dp2px());
        return view;
    }

    private void px2dp() {
        float scale = getContext().getResources().getDisplayMetrics().density;
        float dp = mPx / scale; // 100.0
        Log.d(TAG, "px2dp: dp = " + dp);
    }

    private void dp2px() {
        float px = getResources().getDimension(R.dimen.size_100); // 262.5
        mPx = px;
        Log.d(TAG, "dp2px: px = " + px);

        float px2 = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, px, getContext().getResources().getDisplayMetrics()); // 262.5
        Log.d(TAG, "dp2px: px2 = " + px2);

        float scale = getContext().getResources().getDisplayMetrics().density;
        float px3 = (100f * scale); // 262.5
        Log.d(TAG, "dp2px: px3 = " + px3);

    }
}
