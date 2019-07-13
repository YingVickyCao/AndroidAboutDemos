package com.hades.example.android.resource.anim;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.hades.example.android.R;

public class TestFrameAnimationFragment extends Fragment {
    private static final String TAG = TestFrameAnimationFragment.class.getSimpleName();

    private ImageView image;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.res_frame_animation, container, false);

        image = view.findViewById(R.id.imageView);
        image.setBackgroundResource(R.drawable.anim_frame);

//        image.setImageResource(R.drawable.anim_frame); // not ok
//        image.setImageDrawable(getResources().getDrawable(R.drawable.anim_frame)); // ok

        view.findViewById(R.id.startAnimation).setOnClickListener(v -> startAnimation());
        view.findViewById(R.id.stopAnimation).setOnClickListener(v -> stopAnimation());

        return view;
    }

    private void startAnimation() {
        Drawable drawable = image.getBackground();
//        drawable = image.getDrawable();
        if (null != drawable && drawable instanceof Animatable && !((Animatable) drawable).isRunning()) {
            Log.d(TAG, "startAnimation:1 isRunning = " + ((Animatable) drawable).isRunning());
            ((Animatable) drawable).start();
            Log.d(TAG, "startAnimation:2 isRunning = " + ((Animatable) drawable).isRunning());
        }
    }

    private void stopAnimation() {
        Drawable drawable = image.getBackground();
//        Drawable drawable = image.getDrawable();
        if (null != drawable && drawable instanceof Animatable && ((Animatable) drawable).isRunning()) {
            Log.d(TAG, "stopAnimation: 1 isRunning = " + ((Animatable) drawable).isRunning());
            ((Animatable) drawable).stop();
            Log.d(TAG, "stopAnimation: 2 isRunning = " + ((Animatable) drawable).isRunning());
        }
    }
}