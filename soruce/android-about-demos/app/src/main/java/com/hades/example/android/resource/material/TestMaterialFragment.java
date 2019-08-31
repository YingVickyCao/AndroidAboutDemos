package com.hades.example.android.resource.material;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.hades.example.android.R;
import com.hades.example.android.lib.base.BaseFragment;
import com.hades.example.android.media.audio.media_player.TestMediaPlayer4AudioFragment;

import java.io.IOException;
import java.io.InputStream;

// Other Assert sample , see {}

/**
 * {@link TestMediaPlayer4AudioFragment}
 */
public class TestMaterialFragment extends BaseFragment {
    private ImageView mImageView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.res_material, container, false);

        mImageView = view.findViewById(R.id.imageView);

        view.findViewById(R.id.loadAssertsImg).setOnClickListener(arg0 -> loadAssertsImg());
        return view;
    }

    private void loadAssertsImg() {
        InputStream is = null;
        try {
            is = getActivity().getAssets().open("play_fire.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Bitmap bitmap = BitmapFactory.decodeStream(is);
        mImageView.setImageBitmap(bitmap);
    }
}