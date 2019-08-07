package com.hades.example.android.network.url.http_url_connection;

import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

class DownThread extends Thread {
    private static final String TAG = DownThread.class.getSimpleName();
    private DownUtil downUtil;
    private int startPosition;                    // 当前线程的下载位置
    private int currentPartSize;             // 定义当前线程负责下载的文件大小
    private RandomAccessFile currentRandomAccessFile;   // 当前线程需要下载的文件块
    public int downloadedLength;

    public DownThread(DownUtil downUtil, int startPosition, int currentPartSize, RandomAccessFile currentRandomAccessFile) {
        this.downUtil = downUtil;
        this.startPosition = startPosition;
        this.currentPartSize = currentPartSize;
        this.currentRandomAccessFile = currentRandomAccessFile;
//        Log.d(TAG, "DownThread: @" + hashCode() + ",startPosition=" + startPosition + ",partSize=" + partSize);
    }

    public void setDownloadedLength(int downloadedLength) {
        this.downloadedLength = downloadedLength;
    }

    @Override
    public void run() {
        InputStream inStream = null;
        HttpURLConnection conn = null;
        try {
            URL url = new URL(downUtil.URL);
            conn = (HttpURLConnection) url.openConnection();
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

            inStream = conn.getInputStream();

            // 跳过startPos个字节，表明该线程只下载自己负责的那部分文件
            DownUtil.skipFully(inStream, this.startPosition);
//				inStream.skip(this.startPosition);

            byte[] buffer = new byte[1024];
            int hasRead = 0;
            while (downloadedLength < currentPartSize && (hasRead = inStream.read(buffer)) > 0) {// 读取网络数据，并写入本地文件
                currentRandomAccessFile.write(buffer, 0, hasRead);
                int preLength = downloadedLength;
                downloadedLength += hasRead;  // 累计该线程下载的总大小
                Log.d(TAG, "DownThread: @" + hashCode() + ",run,startPosition=" + startPosition + ",partSize=" + currentPartSize + ",preLength=" + preLength + ",hasRead=" + hasRead + ",downloadedLength=" + downloadedLength);
            }
            currentRandomAccessFile.close();
            inStream.close();
        } catch (Exception ex) {
            Log.d(TAG, "run:1 " + ex);
        } finally {
            if (null != inStream) {
                try {
                    inStream.close();
                } catch (IOException ex) {
                    Log.d(TAG, "run:2 " + ex);
                }

                if (null != conn) {
                    conn.disconnect();
                }
            }
        }
    }

    public void startRun() {
        if (downloadedLength >= currentPartSize) {
            return;
        }
        start();
    }

    public int getDownloadedLength() {
        return downloadedLength;
    }
}
