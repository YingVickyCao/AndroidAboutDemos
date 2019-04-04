package com.example.hades.androidpo._1_render_op.overdraw.reduce_transparency;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.hades.androidpo.R;

public class ReduceTransparentAnimationsFragment extends Fragment {
    private ImageView image;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.reduce_transparent_animations, container, false);
        image = view.findViewById(R.id.image);
        view.findViewById(R.id.alpha).setOnClickListener(v -> alpha());
        return view;
    }

    private void alpha() {
        final Animation anim = AnimationUtils.loadAnimation(getActivity(), R.anim.anim_alpha);
        anim.setFillAfter(false);
        image.startAnimation(anim);
    }
}
