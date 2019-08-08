package com.hades.example.android.network.url.url;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.hades.example.android.R;
import com.hades.example.android.lib.utils.LogHelper;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import static android.content.Context.MODE_PRIVATE;
import static com.hades.example.android.Constant.KEY_UPDATE_UI;

public class TestURLFragment extends Fragment {
    private static final String TAG = TestURLFragment.class.getSimpleName();

    ImageView show;
    Bitmap bitmap; // 代表从网络下载得到的图片

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == KEY_UPDATE_UI) {
                // 使用ImageView显示该图片
                show.setImageBitmap(bitmap);
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.network_url_layout, container, false);
        show = view.findViewById(R.id.show);

        view.findViewById(R.id.request).setOnClickListener(v -> request());
        return view;
    }

    private void request() {
        new Thread() {
            public void run() {
                doRequestImg();
            }
        }.start();
        
        /***
         *
         * <pre>
         *
         * 1. [thread =2,main]
         *
         * 2. ERROR: Android 9.0 Can not do network request on UI Thread
         * NetworkSecurityConfig: No Network Security Config specified, using platform default
         * android.os.NetworkOnMainThreadException,
         * at android.os.StrictMode$AndroidBlockGuardPolicy.onNetwork(StrictMode.java:1513)
         * <pre/>
         */
//        doRequestImg();
    }

    private void doRequestImg() {
        try {
            Log.d(TAG, "doRequestImg: " + LogHelper.getThreadInfo());
            /**
             * ERROR:
             * NetworkSecurityConfig: No Network Security Config specified, using platform default
             * System.out: (HTTPLog)-Static: isSBSettingEnabled false
             * java.io.IOException: Cleartext HTTP traffic to yingvickycao.github.io not permitted
             *
             * Solution:
             * http -> https
             */
            URL url = new URL("https://yingvickycao.github.io/img/resources/pro1.jpg");

            /**
             * <pre>
             * file: /img/resources/pro1.jpg
             * host:yingvickycao.github.io
             * path:/img/resources/pro1.jpg
             * prort:-1
             * protocol:https
             * authority:yingvickycao.github.io
             * content:buffer(com.android.okhttp.internal.http.Http1xStream$FixedLengthSource@e27a4a).inputStream()
             * ref:null
             * query:null
             * defaultPort:443
             * userInfo:null
             * <pre/>
             */
            Log.d(TAG, "doRequestImg: file: " + url.getFile());
            Log.d(TAG, "doRequestImg: host:" + url.getHost());
            Log.d(TAG, "doRequestImg: path:" + url.getPath());
            Log.d(TAG, "doRequestImg: port:" + url.getPort());
            Log.d(TAG, "doRequestImg: protocol:" + url.getProtocol());
            Log.d(TAG, "doRequestImg: authority:" + url.getAuthority());
            Log.d(TAG, "doRequestImg: content:" + url.getContent());
            Log.d(TAG, "doRequestImg: ref:" + url.getRef());
            Log.d(TAG, "doRequestImg: query:" + url.getQuery());
            Log.d(TAG, "doRequestImg: defaultPort:" + url.getDefaultPort());
            Log.d(TAG, "doRequestImg: userInfo:" + url.getUserInfo());

            // 打开该URL对应的资源的输入流
            InputStream is = url.openStream();
            bitmap = BitmapFactory.decodeStream(is); // URL resource -> View
            handler.sendEmptyMessage(KEY_UPDATE_UI);
            is.close();


            save(url);
//            save2(url);
        } catch (IOException e) {
            Log.d(TAG, "doRequestImg: " + e);
        }
    }

    private void save(URL url) {
        try {
            if (null != getActivity()) {
                // 再次打开URL对应的资源的输入流
                InputStream is = url.openStream(); // 打开与此URL的连接，并返回一个读取URL资源的InputStream
                OutputStream os = getActivity().openFileOutput("pro1.jpg", MODE_PRIVATE); // /data/user/0/com.hades.example.android/files/pro1.jpg
                byte[] buff = new byte[1024];
                int hasRead = 0;
                while ((hasRead = is.read(buff)) > 0) {// URL resource -> File system
                    os.write(buff, 0, hasRead);
                }
                is.close();
                os.close();
            }
        } catch (IOException ex) {
            Log.d(TAG, "save: " + ex);
        }
    }

    private void save2(URL url) {
        InputStream raw = null;
        InputStream in = null;
        FileOutputStream fout = null;
        try {
            // TODO Auto-generated method stub
            URLConnection uc = url.openConnection();
//            String contentType = uc.getContentType();
            int contentLength = uc.getContentLength();
        /*
         * 可以限制不下载哪种文本文件
        if (contentType.startsWith("text/") || contentLength == -1) {
            throw new IOException("This is not a binary file.");
        }*/

            raw = uc.getInputStream();
            in = new BufferedInputStream(raw);

            byte[] data = new byte[contentLength];
            int offset = 0;
            while (offset < contentLength) {
                int bytesRead = in.read(data, offset, data.length - offset);
                if (bytesRead == -1) {
                    break;
                }
                offset += bytesRead;
            }

            if (offset != contentLength) {
                throw new IOException("Only read " + offset + " bytes; Expected " + contentLength + " bytes");
            }

            String filename = "pro2.jpg";
            fout = new FileOutputStream(filename);
            fout.write(data);
            fout.flush();
        } catch (IOException ex) {
            Log.d(TAG, "doRequestImg: " + ex);
        } finally {
            try {
                if (null != raw) {
                    raw.close();
                }
                if (null != in) {
                    in.close();
                }

                if (null != fout) {
                    fout.close();
                }
            } catch (IOException ex) {
                Log.d(TAG, "save: " + ex);
            }
        }
    }
}
