package com.hades.example.android.resource.drawable._bitmap;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.hades.example.android.R;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class TestBitmapFragment extends Fragment {
    private static final String TAG = TestBitmapFragment.class.getSimpleName();

    private String[] imageFileNames = null;
    private AssetManager assets = null;
    private int currentImg = 0;

    private ImageView image;
    private ImageView img0;
    private ImageView img1_1;
    private ImageView img1_2;
    private ImageView img1_3;
    private ImageView img1_4;
    private ImageView img1_5;

    private ImageView img2_1;
    private ImageView img2_2;
    private ImageView img2_3;
    private ImageView img2_4;
    private ImageView img2_5;

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
        img1_1 = view.findViewById(R.id.img1_1);
        img1_2 = view.findViewById(R.id.img1_2);
        img1_3 = view.findViewById(R.id.img1_3);
        img1_4 = view.findViewById(R.id.img1_4);
        img1_5 = view.findViewById(R.id.img1_5);

        img2_1 = view.findViewById(R.id.img2_1);
        img2_2 = view.findViewById(R.id.img2_2);
        img2_3 = view.findViewById(R.id.img2_3);
        img2_4 = view.findViewById(R.id.img2_4);
        img2_5 = view.findViewById(R.id.img2_5);

//        img1_1();
//        img1_2();
//        img1_3();
//        img1_4();
//        img1_5();

        img2_1();
        img2_2();
        img2_3();
        img2_4();
        img2_5();
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
            img1_1.setImageBitmap(bitmap);
        }
    }

    private void img2() {
        if (img0.getDrawable() instanceof BitmapDrawable) {
            Bitmap bitmap = Bitmap.createBitmap(((BitmapDrawable) img0.getDrawable()).getBitmap(), 50, 50, getResources().getDimensionPixelSize(R.dimen.size_100), getResources().getDimensionPixelSize(R.dimen.size_50));
            img1_2.setImageBitmap(bitmap);

        }
    }

    private void img3() {
        if (img0.getDrawable() instanceof BitmapDrawable) {
            Matrix matrix = new Matrix();
            matrix.setScale(1, 2);
            Bitmap bitmap = Bitmap.createBitmap(((BitmapDrawable) img0.getDrawable()).getBitmap(), 50, 50, getResources().getDimensionPixelSize(R.dimen.size_100), getResources().getDimensionPixelSize(R.dimen.size_50), matrix, true);
            img1_3.setImageBitmap(bitmap);

        }
    }

    private void img1_4() {
        Bitmap bitmap = Bitmap.createBitmap(100, 200, Bitmap.Config.ARGB_8888);
        img1_4.setImageBitmap(bitmap);
    }

    private void img1_5() {
        if (img0.getDrawable() instanceof BitmapDrawable) {
            Matrix matrix = new Matrix();
            matrix.setScale(1, 2);
            Bitmap bitmap = Bitmap.createScaledBitmap(((BitmapDrawable) img0.getDrawable()).getBitmap(), 1, 1, true);
            img1_5.setImageBitmap(bitmap);

        }
    }

    private void img2_1() {
        Bitmap bitmap = BitmapFactory.decodeFile("/sdcard/photo7.jpg");
        img2_1.setImageBitmap(bitmap);
    }

    private void img2_2() {
        try {
            InputStream inputStream = getContext().getAssets().open("photo7.jpg");
            byte[] bytes = getBytes(inputStream);
            Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 20, bytes.length / 2); // ERROR: not work
            img2_2.setImageBitmap(bitmap); // not shown
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private byte[] getBytes(InputStream is) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] b = new byte[1024];
        int len = 0;
        while ((len = is.read(b, 0, 1024)) != -1) {
            baos.write(b, 0, len);
            baos.flush();
        }
        byte[] bytes = baos.toByteArray();
        return bytes;
    }

    private void img2_3() {
        try {
            InputStream inputStream = getContext().getAssets().open("photo7.jpg");
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            img2_3.setImageBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void img2_4() {
        AssetManager am = getActivity().getAssets();
        try {
            AssetFileDescriptor afd = am.openFd("photo7.jpg");
            FileInputStream is = new FileInputStream("file:///android_asset/photo7.jpg");
            Bitmap bitmap = BitmapFactory.decodeFileDescriptor(is.getFD()); // ERROR: not work
            img2_4.setImageBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
            Log.d(TAG, "img2_4: ");
        }
    }

    private void img2_5() {

    }
}