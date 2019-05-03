package com.hades.example.android.data_storage.io;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.hades.example.android.R;
import com.hades.example.android.lib.base.BaseFragment;

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
    TextView currentPath;

    File currentDirParent;
    File[] currentDirFiles;
    List<Map<String, Object>> currentDirFilesList = new ArrayList<>();
    private SimpleAdapter simpleAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_io_layout, container, false);

        view.findViewById(R.id.read).setOnClickListener(v -> read());
        view.findViewById(R.id.write).setOnClickListener(v -> write());

        /**
         <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>
         <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
         */
        view.findViewById(R.id.readSDCard).setOnClickListener(v -> readSDCard());
        view.findViewById(R.id.writeSDCard).setOnClickListener(v -> writeSDCard());

        simpleAdapter = new SimpleAdapter(getActivity(), currentDirFilesList, R.layout.list_item_view_2, new String[]{"icon", "fileName"}, new int[]{R.id.icon, R.id.title});
        view.findViewById(R.id.back2Parent).setOnClickListener(v -> back2Parent());
        currentPath = view.findViewById(R.id.currentPath);
        listView = view.findViewById(R.id.list);
        listView.setOnItemClickListener(this::clickItem);
        listView.setAdapter(simpleAdapter);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // 获取系统的SD卡的目录
//        File root = new File("/mnt/sdcard/");
        File root = Environment.getExternalStorageDirectory();
        // 如果 SD卡存在
        /**
         * File.exists()
         */
        if (root.exists()) {
            currentDirParent = root;
            /**
             * File.listFiles()
             */
            currentDirFiles = root.listFiles();
            useCurrentDirAllFiles2InflateListView(currentDirFiles);
        }
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

                    Log.d(TAG, "readSDCard: sdCardDir.getPath()=" + sdCardDir.getPath()); // /storage/emulated/0
                    Log.d(TAG, "readSDCard: sdCardDir.getCanonicalPath()=" + sdCardDir.getCanonicalPath()); // /storage/emulated/0
                    Log.d(TAG, "readSDCard: sdCardDir.getAbsolutePath()=" + sdCardDir.getAbsolutePath()); // /storage/emulated/0

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
        // TODO: 覆盖写
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

    private boolean isTop() throws IOException {
        return currentDirParent.getCanonicalPath().equals(Environment.getExternalStorageDirectory().getCanonicalPath());
    }

    private void back2Parent() {
        try {
            if (isTop()) {
                showToast("Top Already");
                return;
            }

            /**
             * File.getParentFile()
             */
            currentDirParent = currentDirParent.getParentFile();
            currentDirFiles = currentDirParent.listFiles();
            useCurrentDirAllFiles2InflateListView(currentDirFiles);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clickItem(AdapterView<?> parent, View view, int position, long id) {
        if (currentDirFiles[position].isFile()) {
            return;
        }

        File[] tmp = currentDirFiles[position].listFiles();
        if (tmp == null || tmp.length == 0) {
            showToast("当前路径不可访问或该路径下没有文件");
        } else {
            currentDirParent = currentDirFiles[position];
            currentDirFiles = tmp;
            useCurrentDirAllFiles2InflateListView(currentDirFiles);
        }
    }

    private void useCurrentDirAllFiles2InflateListView(File[] files) {
        List<Map<String, Object>> listItems = new ArrayList<>();
        for (File file : files) {
            Map<String, Object> listItem = new HashMap<>();
            /**
             * File.isDirectory()
             */
            if (file.isDirectory()) {
                listItem.put("icon", R.drawable.folder);
            } else {
                listItem.put("icon", R.drawable.file);
            }
            /**
             * File.getName()
             */
            listItem.put("fileName", file.getName());
            listItems.add(listItem);
        }

        if (!listItems.isEmpty()) {
            currentDirFilesList.clear();
            currentDirFilesList.addAll(listItems);
        }

        simpleAdapter.notifyDataSetChanged();

        try {
            currentPath.setText(currentDirParent.getCanonicalPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}