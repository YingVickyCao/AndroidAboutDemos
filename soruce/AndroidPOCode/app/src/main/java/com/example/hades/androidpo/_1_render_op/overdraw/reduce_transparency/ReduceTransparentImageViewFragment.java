package com.example.hades.androidpo._1_render_op.overdraw.reduce_transparency;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.hades.androidpo.BaseFragment;
import com.example.hades.androidpo.R;

import static com.example.hades.androidpo._1_render_op.overdraw.custom_view.ReduceOverDrawInCustomViewFragment.findBestSampleSize;

public class ReduceTransparentImageViewFragment extends BaseFragment {
    private ImageView image;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.reduce_transparent_imageview, container, false);
        image = view.findViewById(R.id.image);
        view.findViewById(R.id.getTransparentImage).setOnClickListener(v -> getTransparentImage());
        return view;
    }

    private void getTransparentImage() {
        Bitmap bitmap = loadImageResource(R.drawable.test3, 200, 150);
        image.setImageBitmap(bitmap);
        image.setAlpha(0.2f);
    }

    public Bitmap loadImageResource(int imageResId, int cardWidth, int cardHeight) {
        Bitmap bitmap = null;
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            options.inDensity = DisplayMetrics.DENSITY_DEFAULT;
            options.inTargetDensity = DisplayMetrics.DENSITY_DEFAULT;
            options.inScreenDensity = DisplayMetrics.DENSITY_DEFAULT;
            BitmapFactory.decodeResource(getResources(), imageResId, options);

            options.inJustDecodeBounds = false;
            options.inSampleSize = findBestSampleSize(options.outWidth, options.outHeight, cardWidth, cardHeight);
            bitmap = BitmapFactory.decodeResource(getResources(), imageResId, options);
        } catch (OutOfMemoryError exception) {
        }
        return bitmap;
    }
}
