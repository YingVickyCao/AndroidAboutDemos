package com.hades.android.example.android_about_demos.data_storage.io;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.hades.android.example.android_about_demos.R;
import com.hades.android.example.android_about_demos.base.BaseFragment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

public class TestIOFragment extends BaseFragment {
    private static final String TAG = TestIOFragment.class.getSimpleName();
    final String Internal_Storage_FILE_NAME = "test_IO.txt";
    final String EXTERNAL_STORAGE_FILE_NAME = "sd_test_IO.txt";
    ListView listView;
    TextView textView;
    // 记录当前的父文件夹
    File currentParent;
    // 记录当前路径下的所有文件的文件数组
    File[] currentFiles;

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

        // 获取列出全部文件的ListView
        listView = view.findViewById(R.id.list);
        textView = view.findViewById(R.id.path);

        // 获取系统的SD卡的目录
        File root = new File("/mnt/sdcard/");
        // 如果 SD卡存在
        if (root.exists()) {
            currentParent = root;
            currentFiles = root.listFiles();
            // 使用当前目录下的全部文件、文件夹来填充ListView
            inflateListView(currentFiles);
        }

        // 为ListView的列表项的单击事件绑定监听器
        listView.setOnItemClickListener(this::clickItem);

        view.findViewById(R.id.parent).setOnClickListener(v -> parent());

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
                fis = getActivity().openFileInput(Internal_Storage_FILE_NAME);

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
                fos = getActivity().openFileOutput(Internal_Storage_FILE_NAME, MODE_PRIVATE);
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
        // 如果手机插入了SD卡，而且应用程序具有访问SD的权限
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            new Thread(() -> {

                FileInputStream fis = null;
                BufferedReader br = null;
                try {

                    File sdCardDir = Environment.getExternalStorageDirectory();
                    Log.d(TAG, "readSDCard: sdCardDir");
                    fis = new FileInputStream(sdCardDir.getCanonicalPath() + File.separator + EXTERNAL_STORAGE_FILE_NAME);
                    br = new BufferedReader(new InputStreamReader(fis));

                    StringBuilder sb = new StringBuilder("");
                    String line = null;
                    while ((line = br.readLine()) != null) {
                        sb.append(line);
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

                    if (null != br) {
                        try {
                            br.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                }
            }).start();
        }
    }

    private void writeSDCard() {
        // 如果手机插入了SD卡，而且应用程序具有访问SD的权限
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {

            new Thread(() -> {

                String content = String.valueOf(System.currentTimeMillis()) + "SD";
                RandomAccessFile raf = null;
                try {
                    // 获取SD卡的目录
                    File sdCardDir = Environment.getExternalStorageDirectory(); // /storage/emulated/0
                    File targetFile = new File(sdCardDir.getCanonicalPath() + File.separator + EXTERNAL_STORAGE_FILE_NAME); // /storage/emulated/0/sd_test_IO.txt   => sdcard/sd_test_IO.txt

                    raf = new RandomAccessFile(targetFile, "rw");
                    raf.seek(targetFile.length());// 将文件记录指针移动到最后
                    raf.write(content.getBytes());

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (null != raf) {
                        try {
                            raf.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();

        }
    }

    private void parent() {
        try {
            if (!currentParent.getCanonicalPath()
                    .equals("/mnt/sdcard")) {
                // 获取上一级目录
                currentParent = currentParent.getParentFile();
                // 列出当前目录下所有文件
                currentFiles = currentParent.listFiles();
                // 再次更新ListView
                inflateListView(currentFiles);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clickItem(AdapterView<?> parent, View view, int position, long id) {
        // 用户单击了文件，直接返回，不做任何处理
        if (currentFiles[position].isFile()) return;
        // 获取用户点击的文件夹下的所有文件
        File[] tmp = currentFiles[position].listFiles();
        if (tmp == null || tmp.length == 0) {
            showToast("当前路径不可访问或该路径下没有文件");
        } else {
            // 获取用户单击的列表项对应的文件夹，设为当前的父文件夹
            currentParent = currentFiles[position]; // ②
            // 保存当前的父文件夹内的全部文件和文件夹
            currentFiles = tmp;
            // 再次更新ListView
            inflateListView(currentFiles);
        }
    }

    private void inflateListView(File[] files) {
        // 创建一个List集合，List集合的元素是Map
        List<Map<String, Object>> listItems =
                new ArrayList<Map<String, Object>>();
        for (int i = 0; i < files.length; i++) {
            Map<String, Object> listItem =
                    new HashMap<String, Object>();
            // 如果当前File是文件夹，使用folder图标；否则使用file图标
            if (files[i].isDirectory()) {
                listItem.put("icon", R.drawable.folder);
            } else {
                listItem.put("icon", R.drawable.file);
            }
            listItem.put("fileName", files[i].getName());
            // 添加List项
            listItems.add(listItem);
        }
        // 创建一个SimpleAdapter
        SimpleAdapter simpleAdapter = new SimpleAdapter(getActivity(), listItems, R.layout.list_item_view_2, new String[]{"icon", "fileName"}, new int[]{R.id.icon, R.id.file_name});
        // 为ListView设置Adapter
        listView.setAdapter(simpleAdapter);
        try {
            textView.setText("当前路径为："
                    + currentParent.getCanonicalPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}