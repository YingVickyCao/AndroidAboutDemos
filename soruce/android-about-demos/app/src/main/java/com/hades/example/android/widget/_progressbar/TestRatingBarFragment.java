package com.hades.example.android.widget._progressbar;

import android.app.Fragment;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;

import com.hades.example.android.R;

public class TestRatingBarFragment extends Fragment {
    private static final String TAG = TestRatingBarFragment.class.getSimpleName();

    public static TestRatingBarFragment newInstance() {
        return new TestRatingBarFragment();
    }

    private ImageView image;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.widget_progressbar_ratingbar, container, false);

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