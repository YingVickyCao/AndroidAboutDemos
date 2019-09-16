package com.hades.example.android.resource.drawable.vector;

import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.VectorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.hades.example.android.R;
import com.hades.example.android.lib.base.BaseFragment;

public class TestVectorDrawableFragment extends BaseFragment {
    private static final String TAG = TestVectorDrawableFragment.class.getSimpleName();

    private ImageView mImageView;
    private MeasurableImageView mMeasurableImageView;
    private TextView checkRenderTime;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.res_drawable_vector, container, false);

        mImageView = view.findViewById(R.id.imageView);
        mMeasurableImageView = view.findViewById(R.id.measurableImageView);
        mMeasurableImageView.setViewRedrawnListener(milliseconds -> onDrawFinished(milliseconds));
        checkRenderTime = view.findViewById(R.id.checkRenderTime);

        view.findViewById(R.id.startAnimation).setOnClickListener(v -> startAnimation());
        view.findViewById(R.id.stopAnimation).setOnClickListener(v -> stopAnimation());

        view.findViewById(R.id.vector_drawable_button).setOnClickListener(v -> vectorDrawableSelected());
        view.findViewById(R.id.png_button).setOnClickListener(v -> pngSelected());
        return view;
    }

    private void startAnimation() {
        if (null != mImageView && null != mImageView.getDrawable() && mImageView.getDrawable() instanceof AnimatedVectorDrawable) {
            AnimatedVectorDrawable drawable = (AnimatedVectorDrawable) mImageView.getDrawable();
            drawable.start();
        }
    }

    private void stopAnimation() {
        if (null != mImageView && null != mImageView.getDrawable() && mImageView.getDrawable() instanceof AnimatedVectorDrawable) {
            AnimatedVectorDrawable drawable = (AnimatedVectorDrawable) mImageView.getDrawable();
            drawable.stop();
        }
    }

    private void vectorDrawableSelected() {
        mMeasurableImageView.setImageResource(R.drawable.drawable_vector_4_placeholder_svg); // 0.10，svg vs png，svg render时间更短
    }

    private void pngSelected() {
        mMeasurableImageView.setImageResource(R.drawable.placeholder_png);  // 0.17
    }

    private void onDrawFinished(double milliseconds) {
        Log.d(TAG, "onDrawFinished: milliseconds=" + milliseconds);
        checkRenderTime.setText(String.valueOf(milliseconds));

        if (mMeasurableImageView.getDrawable() instanceof VectorDrawable) {
            VectorDrawable vectorDrawable = (VectorDrawable) mMeasurableImageView.getDrawable();
            Log.d(TAG, "onDrawFinished: VectorDrawable@" + vectorDrawable.hashCode()); //每次set，同一个svg，得到的VectorDrawable是不同实例
        }
    }
}