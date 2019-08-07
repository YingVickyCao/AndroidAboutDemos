package com.hades.example.android.network.url.http_url_connection;

import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class DownUtil {
    private static final String TAG = DownUtil.class.getSimpleName();

    String URL;
    private String savePath; // 指定所下载的文件的保存位置
    private int threadNum;
    private DownThread[] threads;
    private int totalDownloadFileSize;
    private Map<Integer, Integer> mDownloadedLengthMap;

    public DownUtil(String path, String savePath, int threadNum, Map<Integer, Integer> downloadedLengthMap) {
        this.URL = path;
        this.threadNum = threadNum;
        // 初始化threads数组
        threads = new DownThread[threadNum];
        this.savePath = savePath;
        mDownloadedLengthMap = downloadedLengthMap;

        if (null == mDownloadedLengthMap) {
            mDownloadedLengthMap = new HashMap<>();
        }
    }

    public void download() throws Exception {
        URL url = new URL(URL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(5 * 1000);
        conn.setRequestMethod("GET");
        conn.setRequestProperty(
                "Accept",
                "image/gif, image/jpeg, image/pjpeg, image/pjpeg, "
                        + "application/x-shockwave-flash, application/xaml+xml, "
                        + "application/vnd.ms-xpsdocument, application/x-ms-xbap, "
                        + "application/x-ms-application, application/vnd.ms-excel, "
                        + "application/vnd.ms-powerpoint, application/msword, */*");
        conn.setRequestProperty("Accept-Language", "zh-CN");
        conn.setRequestProperty("Charset", "UTF-8");
        conn.setRequestProperty("Connection", "Keep-Alive");
        totalDownloadFileSize = conn.getContentLength();
        conn.disconnect();

        int partSize = totalDownloadFileSize / threadNum + 1;
        for (int i = 0; i < threadNum; i++) {
            int startPosition = i * partSize;         // 计算每条线程的下载的开始位置
            RandomAccessFile currentRandomAccessFile = new RandomAccessFile(savePath, "rw");  // 每个线程使用一个RandomAccessFile进行下载
            currentRandomAccessFile.seek(startPosition);             // 定位该线程的下载位置

            threads[i] = new DownThread(this, startPosition, partSize, currentRandomAccessFile);  // 创建下载线程
            if (mDownloadedLengthMap.containsKey(i)) {
                Integer length = mDownloadedLengthMap.get(i);
                if (null != length) {
                    threads[i].setDownloadedLength(length);
                }
            }
            threads[i].startRun();     // 启动下载线程
        }
    }

    public int getDownloadProgress() {
        int sumSize = 0;
        for (int i = 0; i < threadNum; i++) {
            sumSize += threads[i].downloadedLength;
        }
        double completeRate = sumSize * 1.0 / totalDownloadFileSize;
        return (int) (completeRate * 100);
    }

    public Map<Integer, Integer> getPartDownloadProgress() {
        for (int i = 0; i < threadNum; i++) {
            mDownloadedLengthMap.clear();
            mDownloadedLengthMap.put(i, threads[i].downloadedLength);
        }
        return mDownloadedLengthMap;
    }

    // 定义一个为InputStream跳过bytes字节的方法
    public static void skipFully(InputStream in, long bytes) throws IOException {
        long remainning = bytes;
        long len = 0;
        while (remainning > 0) {
            len = in.skip(remainning);
            remainning -= len;
            Log.d(TAG, "skipFully: ");
        }
    }
}
