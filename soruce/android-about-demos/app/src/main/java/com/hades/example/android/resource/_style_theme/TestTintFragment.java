package com.hades.example.android.resource._style_theme;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.fragment.app.Fragment;

import com.hades.example.android.Constant;
import com.hades.example.android.R;
import com.hades.example.android.resource.drawable.vector.TestVectorDrawableFragment;
import com.squareup.picasso.Picasso;

public class TestTintFragment extends Fragment {
    private static final String TAG = TestVectorDrawableFragment.class.getSimpleName();

    private ImageView mImageView_svg;
    private ImageView mImageView_png_url;
    private ImageView mImageView_png_url_2;
    private boolean isSelected = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.res_tint, container, false);

        /**
         * android:tint="@color/menu_icon_color" 改变menu color?
         * API 21    Android 5.0      ✗
         * API 22    Android 5.1      ✗
         * API 23    Android 6.0      ✓
         * API 24    Android 7.0      ✓
         * API 25    Android 7.1      ✓
         * API 26    Android 8.0      ✓
         * API 27    Android 8.1      ✓
         * API 28    Android 9.0      ✓
         */
        mImageView_svg = view.findViewById(R.id.svg);
        mImageView_png_url = view.findViewById(R.id.png_url);
        mImageView_png_url_2 = view.findViewById(R.id.png_url2);

        mImageView_svg.setImageDrawable(AppCompatResources.getDrawable(getActivity(), R.drawable.drawable_vector_add));
        mImageView_svg.getDrawable().mutate();

        //非透明，not ok
        Picasso.with(getActivity()).load(Constant.IMAGE_URL).placeholder(R.drawable.ic_launcher).fit().into(mImageView_png_url);
        // 透明, ok
        Picasso.with(getActivity()).load(Constant.IMAGE_URL2).placeholder(R.drawable.ic_launcher).fit().into(mImageView_png_url_2);


        view.findViewById(R.id.switchMenuColor).setOnClickListener(v -> switchMenuColor());
        return view;
    }

    private void switchMenuColor() {
        mImageView_svg.setSelected(isSelected);
        mImageView_png_url.setSelected(isSelected);
        mImageView_png_url_2.setSelected(isSelected);

        isSelected = !isSelected;
    }
}