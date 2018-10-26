package com.hades.android.example.android_about_demos.resource.drawable.vector;

import android.graphics.drawable.AnimatedVectorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.graphics.drawable.AnimatedVectorDrawableCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.hades.android.example.android_about_demos.R;
import com.hades.android.example.android_about_demos.base.BaseFragment;

public class VectorDrawableFragment extends BaseFragment {
    private ImageView mImageView2;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.res_drawable_vector, container, false);
        mImageView2 = view.findViewById(R.id.imageView2);
        view.findViewById(R.id.startAnimation).setOnClickListener(v -> startAnimation());
        view.findViewById(R.id.stopAnimation).setOnClickListener(v -> stopAnimation());
        return view;
    }

    private void startAnimation() {
        if (null != mImageView2 && null != mImageView2.getDrawable() && mImageView2.getDrawable() instanceof AnimatedVectorDrawable) {
            AnimatedVectorDrawable drawable = (AnimatedVectorDrawable) mImageView2.getDrawable();
            drawable.start();
        }
    }

    private void stopAnimation() {
        if (null != mImageView2 && null != mImageView2.getDrawable() && mImageView2.getDrawable() instanceof AnimatedVectorDrawable) {
            AnimatedVectorDrawable drawable = (AnimatedVectorDrawable) mImageView2.getDrawable();
            drawable.stop();
        }
    }
}
