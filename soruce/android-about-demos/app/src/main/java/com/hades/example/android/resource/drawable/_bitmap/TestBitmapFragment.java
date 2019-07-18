package com.hades.example.android.resource.drawable._bitmap;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
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
    private ImageView img0;
    private ImageView img1;
    private ImageView img2;
    private ImageView img3;
    private ImageView img4;
    private ImageView img5;

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

        img0 = view.findViewById(R.id.img0);
        img1 = view.findViewById(R.id.img1);
        img2 = view.findViewById(R.id.img2);
        img3 = view.findViewById(R.id.img3);
        img4 = view.findViewById(R.id.img4);
        img5 = view.findViewById(R.id.img5);
//        img1();
//        img2();
//        img3();
        img4();
        img5();
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

    private void img1() {
        if (img0.getDrawable() instanceof BitmapDrawable) {
            Bitmap bitmap = Bitmap.createBitmap(((BitmapDrawable) img0.getDrawable()).getBitmap());
            img1.setImageBitmap(bitmap);
        }
    }

    private void img2() {
        if (img0.getDrawable() instanceof BitmapDrawable) {
            Bitmap bitmap = Bitmap.createBitmap(((BitmapDrawable) img0.getDrawable()).getBitmap(), 50, 50, getResources().getDimensionPixelSize(R.dimen.size_100), getResources().getDimensionPixelSize(R.dimen.size_50));
            img2.setImageBitmap(bitmap);

        }
    }

    private void img3() {
        if (img0.getDrawable() instanceof BitmapDrawable) {
            Matrix matrix = new Matrix();
            matrix.setScale(1, 2);
            Bitmap bitmap = Bitmap.createBitmap(((BitmapDrawable) img0.getDrawable()).getBitmap(), 50, 50, getResources().getDimensionPixelSize(R.dimen.size_100), getResources().getDimensionPixelSize(R.dimen.size_50), matrix, true);
            img3.setImageBitmap(bitmap);

        }
    }

    private void img4() {
        Bitmap bitmap = Bitmap.createBitmap(100, 200, Bitmap.Config.ARGB_8888);
        img4.setImageBitmap(bitmap);
    }

    private void img5() {
        if (img0.getDrawable() instanceof BitmapDrawable) {
            Matrix matrix = new Matrix();
            matrix.setScale(1, 2);
            Bitmap bitmap = Bitmap.createScaledBitmap(((BitmapDrawable) img0.getDrawable()).getBitmap(), 1, 1, true);
            img5.setImageBitmap(bitmap);

        }
    }
}