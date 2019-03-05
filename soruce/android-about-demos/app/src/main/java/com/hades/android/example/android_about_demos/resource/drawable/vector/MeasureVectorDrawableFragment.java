package com.hades.android.example.android_about_demos.resource.drawable.vector;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.hades.android.example.android_about_demos.R;
import com.hades.android.example.android_about_demos.base.BaseFragment;

public class MeasureVectorDrawableFragment extends BaseFragment {
    private MeasurableImageView mImageView;
    private TextView checkRenderTime;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.res_drawable_vector_measurement, container, false);
        mImageView = view.findViewById(R.id.imageView);
        checkRenderTime = view.findViewById(R.id.checkRenderTime);

        mImageView.setViewRedrawnListener(milliseconds -> MeasureVectorDrawableFragment.this.onDrawFinished(milliseconds));

        RadioGroup group = view.findViewById(R.id.drawable_choice);
        group.setOnCheckedChangeListener((group1, checkedId) -> onImageTypeChanged(checkedId));
        group.check(R.id.vector_drawable_button);
        view.findViewById(R.id.checkRenderTime).setOnClickListener(v -> checkRenderTime());
        return view;
    }

    private void onImageTypeChanged(@IdRes final int checkedId) {
        switch (checkedId) {
            case R.id.vector_drawable_button:
                vectorDrawableSelected();
                break;
            case R.id.png_button:
                pngSelected();
                break;
        }
    }

    private void vectorDrawableSelected() {
        mImageView.setImageResource(R.drawable.drawable_vector_4_placeholder_svg);
    }

    private void pngSelected() {
        mImageView.setImageResource(R.drawable.placeholder_png);
    }

    private void checkRenderTime() {
    }

    private void onDrawFinished(double milliseconds) {
        checkRenderTime.setText(String.valueOf(milliseconds));
    }

}
