package com.hades.android.example.android_about_demos.resource.drawable.vector;

import android.graphics.Canvas;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.hades.android.example.android_about_demos.R;

/**
 * Activity that allows choosing between a VectorDrawable and a PNG resource as a source for
 * an ImageView. The Activity displays how long the {@link ImageView#onDraw(Canvas)} method took.
 */
public class MainActivity extends AppCompatActivity {

    private MeasurableImageView mMeasurableImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void vectorDrawableSelected() {
        mMeasurableImageView.setImageResource(R.drawable.placeholder_svg);
    }

    private void pngSelected() {
        mMeasurableImageView.setImageResource(R.drawable.placeholder_png);
    }
}
