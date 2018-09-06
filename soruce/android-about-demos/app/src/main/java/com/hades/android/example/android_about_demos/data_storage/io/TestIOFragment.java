package com.hades.android.example.android_about_demos.data_storage.io;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hades.android.example.android_about_demos.R;
import com.hades.android.example.android_about_demos.base.BaseFragment;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static android.content.Context.MODE_PRIVATE;

public class TestIOFragment extends BaseFragment {
    final String FILE_NAME = "test_IO.txt";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_io_layout, container, false);

        view.findViewById(R.id.read).setOnClickListener(v -> read());
        view.findViewById(R.id.write).setOnClickListener(v -> write());

        /**
         <uses-permission android:name="android.permission.MOUNT_UNMOUNT_FILESYSTEMS"/>
         <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
         */
        view.findViewById(R.id.readSDCard).setOnClickListener(v -> readSDCard());
        view.findViewById(R.id.writeSDCard).setOnClickListener(v -> writeSDCard());
        return view;
    }

    /**
     * FileInputStream => byte[]
     */
    private void read() {
        new Thread(() -> {
            FileInputStream fis = null;
            try {

                /**
                 * /data/data/<package_name>/files/file_name
                 */
                fis = getActivity().openFileInput(FILE_NAME);

                byte[] buff = new byte[1024];
                int hasRead = 0;
                StringBuilder sb = new StringBuilder("");
                while ((hasRead = fis.read(buff)) > 0) {
                    sb.append(new String(buff, 0, hasRead));
                }
                String result = sb.toString();
                showToast(result);

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (null != fis) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    /**
     * FileOutputStream => PrintStream
     */
    private void write() {
        String content = String.valueOf(System.currentTimeMillis());
        new Thread(() -> {
            FileOutputStream fos = null;
            PrintStream ps = null;
            try {
                fos = getActivity().openFileOutput(FILE_NAME, MODE_PRIVATE);
                ps = new PrintStream(fos);
                ps.println(content);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (null != fos) {
                    try {
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                if (null != ps) {
                    ps.close();
                }
            }
        }).start();
    }

    private void readSDCard() {
    }

    private void writeSDCard() {

    }
}