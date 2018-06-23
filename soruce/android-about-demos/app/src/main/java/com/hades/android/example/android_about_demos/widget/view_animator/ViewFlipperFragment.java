package com.hades.android.example.android_about_demos.widget.view_animator;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ViewFlipper;

import com.hades.android.example.android_about_demos.R;

public class ViewFlipperFragment extends Fragment {
    private ViewFlipper viewFlipper;

    public static ViewFlipperFragment newInstance() {
        Bundle args = new Bundle();
        ViewFlipperFragment fragment = new ViewFlipperFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.widget_viewflipper, container, false);
        viewFlipper = view.findViewById(R.id.viewFlipper);
        view.findViewById(R.id.prev).setOnClickListener(this::prev);
        view.findViewById(R.id.next).setOnClickListener(this::next);
        view.findViewById(R.id.auto).setOnClickListener(this::auto);
        view.findViewById(R.id.stopAuto).setOnClickListener(this::stopAuto);

        return view;
    }

    private Context getUsedContext() {
        return viewFlipper.getContext();
    }

    public void prev(View source) {
        viewFlipper.setInAnimation(getUsedContext(), R.anim.slide_in_right);
        viewFlipper.setOutAnimation(getUsedContext(), R.anim.slide_out_left);
        viewFlipper.showPrevious();
        viewFlipper.stopFlipping();
    }

    public void next(View source) {
        viewFlipper.setInAnimation(getUsedContext(), android.R.anim.slide_in_left);
        viewFlipper.setOutAnimation(getUsedContext(), android.R.anim.slide_out_right);
        viewFlipper.showNext();
        viewFlipper.stopFlipping();
    }

    public void auto(View source) {
        viewFlipper.setInAnimation(getUsedContext(), android.R.anim.slide_in_left);
        viewFlipper.setOutAnimation(getUsedContext(), android.R.anim.slide_out_right);
        viewFlipper.startFlipping();
    }

    public void stopAuto(View source) {
        viewFlipper.stopFlipping();
    }

}
