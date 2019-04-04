package com.example.hades.androidpo._4_stability_op.anr;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.example.hades.androidpo.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * https://blog.csdn.net/demonliuhui/article/details/71453656
 */
public class StrictModeFragment extends Fragment {
    private static final String TAG = StrictModeFragment.class.getSimpleName();

    private SeekBar mSeekBar;
    private Bitmap mBitmap;
    private final String IMAGE_URL = "https://github.com/YingVickyCao/YingVickyCao.github.io/blob/master/img/resources/google_plus_48dp.png";
    private ImageView mImageView;
    private Handler handler;
    private int fileLength;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_strictmode_layout, container, false);
        mImageView = view.findViewById(R.id.imageView);
        mSeekBar = view.findViewById(R.id.seekbar);
        view.findViewById(R.id.detectNetworkInUIThread).setOnClickListener(v -> detectNetworkInUIThread());

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 0x123) {
                    //使用ImageView显示图片

                    mImageView.setImageBitmap(mBitmap);
                }
            }
        };
        return view;
    }

    // TODO: 05/09/2018  
    private void detectNetworkInUIThread() {
        final File file = new File(getActivity().getCacheDir(), "2.png");
        new Thread() {
            public void run() {
                try {
                    URL url = new URL(IMAGE_URL);
                    System.out.println("使用网络");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setConnectTimeout(8000);
                    conn.setReadTimeout(8000);
                    conn.connect();
                    if (200 == conn.getResponseCode()) {
                        //正常连接
                        InputStream is = conn.getInputStream();
                        //Bitmap bitmap=BitmapFactory.decodeStream(is);
                        FileOutputStream fileOutputStream = new FileOutputStream(file);
                        int len;
                        byte[] b = new byte[1024];
                        while ((len = is.read(b)) != -1) {
                            fileOutputStream.write(b, 0, len);
                        }
                        fileOutputStream.close();
                        Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                        fileOutputStream.flush();
                        Message msg = Message.obtain();
                        msg.what = 0x123;
                        msg.obj = bitmap;
                        handler.sendMessage(msg);
                    }
                } catch (Exception e) {
                }
            }
        }.start();

//        if (file.exists()) {
//            Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
//            mImageView.setImageBitmap(bitmap);
//        } else {
//
//        }
    }

}