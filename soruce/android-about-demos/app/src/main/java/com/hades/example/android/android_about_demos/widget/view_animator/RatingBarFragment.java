package com.hades.example.android.android_about_demos.widget.view_animator;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;

import com.hades.example.android.android_about_demos.R;

public class RatingBarFragment extends Fragment {
    private static final String TAG = RatingBarFragment.class.getSimpleName();

    public static RatingBarFragment newInstance() {
        return new RatingBarFragment();
    }

    private ImageView image;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.widget_ratingbar, container, false);

        image = view.findViewById(R.id.text);

        RatingBar ratingBar = view.findViewById(R.id.rating);
        ratingBar.setOnRatingBarChangeListener((arg0, rating, fromUser) -> {
            Log.d(TAG, "onRatingChanged: rating=" + rating + ",fromUser=" + fromUser);
            setImageAlpha((int) rating);
        });
        return view;
    }

    private void setImageAlpha(int rating) {
        image.setImageAlpha(rating * 255 / 5);
    }
}