package com.hades.example.android.resource.drawable._bitmap;

import android.content.res.AssetManager;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.hades.example.android.R;

import java.io.IOException;
import java.io.InputStream;

public class TestBitmapFragment extends Fragment {
    private String[] imageFileNames = null;
    private AssetManager assets = null;
    private int currentImg = 0;

    private ImageView image;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.res_bitmap, container, false);
        image = view.findViewById(R.id.image);

        try {
            assets = getContext().getAssets();
            // 获取/assets/目录下所有文件
            imageFileNames = assets.list("");
        } catch (IOException e) {
            e.printStackTrace();
        }
        view.findViewById(R.id.next).setOnClickListener(sources -> next());
        return view;
    }

    private boolean isNotImage() {
        return !imageFileNames[currentImg].endsWith(".png")
                && !imageFileNames[currentImg].endsWith(".jpg")
                && !imageFileNames[currentImg].endsWith(".gif");
    }

    private void next() {
        if (currentImg >= imageFileNames.length) {
            currentImg = 0;
        }

        while (isNotImage()) {
            currentImg++;
            if (currentImg >= imageFileNames.length) {
                currentImg = 0;
            }
        }

        InputStream assetFile = null;
        try {
            assetFile = assets.open(imageFileNames[currentImg++]);
        } catch (IOException e) {
            e.printStackTrace();
        }
        BitmapDrawable bitmapDrawable = (BitmapDrawable) image.getDrawable();
        if (bitmapDrawable != null && !bitmapDrawable.getBitmap().isRecycled()) { // Bitmap.isRecycled(), BitmapDrawable.getBitmap()
            bitmapDrawable.getBitmap().recycle(); // Bitmap.recycle()
        }

        image.setImageBitmap(BitmapFactory.decodeStream(assetFile)); // BitmapFactory.decodeStream(InputStream)
    }
}