package com.hades.example.android.resource.drawable.vector;

import android.graphics.Canvas;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.hades.example.android.R;

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
        mMeasurableImageView.setImageResource(R.drawable.drawable_vector_4_placeholder_svg);
    }

    private void pngSelected() {
        mMeasurableImageView.setImageResource(R.drawable.placeholder_png);
    }
}
