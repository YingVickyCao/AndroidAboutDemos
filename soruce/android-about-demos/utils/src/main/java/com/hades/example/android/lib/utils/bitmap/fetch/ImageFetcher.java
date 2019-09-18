package com.hades.example.android.lib.utils.bitmap.fetch;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.TrafficStats;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import com.hades.example.android.lib.BuildConfig;
import com.hades.example.android.lib.R;
import com.hades.example.android.lib.utils.bitmap.cache.ImageCacheParams;
import com.hades.example.android.lib.utils.bitmap.cache.disk.DiskLruCache;
import com.hades.example.android.lib.utils.FileUtil;
import com.hades.example.android.lib.utils.ImageUtil;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class ImageFetcher extends ImageResize {
    private static final String TAG = ImageFetcher.class.getSimpleName();
    private static final int HTTP_CACHE_SIZE = 10 * 1024 * 1024; // 10MB
    private static final String HTTP_CACHE_DIR = "http";
    private static final int IO_BUFFER_SIZE = 10 * 1024;
    private static final int DISK_CACHE_INDEX = 0;

    private DiskLruCache mHttpDiskCache;
    private File mHttpCacheDir;
    private boolean mHttpDiskCacheStarting = true;
    private final Object mHttpDiskCacheLock = new Object();

    private FileUtil fileUtil = new FileUtil();
    private ImageUtil imageUtil = new ImageUtil();

    public ImageFetcher(Context context, int targetImageWidth, int targetImageHeight) {
        super(context, targetImageWidth, targetImageHeight);
        init(context);
    }

    public ImageFetcher(Context context, int imageSize) {
        super(context, imageSize);
        init(context);
    }

    private void init(Context context) {
        checkNetworkConnection(context);
        mHttpCacheDir = fileUtil.getDiskCacheDir(context, HTTP_CACHE_DIR);
    }

    @Override
    protected void initDiskCacheInternal() {
        super.initDiskCacheInternal();
        initHttpDiskCache();
    }

    private void initHttpDiskCache() {
        if (!mHttpCacheDir.exists()) {
            mHttpCacheDir.mkdirs();
        }
        synchronized (mHttpDiskCacheLock) {
            if (fileUtil.getUsableSpace(mHttpCacheDir) > HTTP_CACHE_SIZE) {
                try {
                    mHttpDiskCache = DiskLruCache.open(mHttpCacheDir, 1, 1, HTTP_CACHE_SIZE);
                    if (BuildConfig.DEBUG) {
                        Log.d(TAG, "HTTP cache initialized");
                    }
                } catch (IOException e) {
                    mHttpDiskCache = null;
                }
            }
            mHttpDiskCacheStarting = false;
            mHttpDiskCacheLock.notifyAll();
        }
    }

    @Override
    protected void clearCacheInternal() {
        super.clearCacheInternal();
        synchronized (mHttpDiskCacheLock) {
            if (mHttpDiskCache != null && !mHttpDiskCache.isClosed()) {
                try {
                    mHttpDiskCache.delete();
                    if (BuildConfig.DEBUG) {
                        Log.d(TAG, "HTTP cache cleared");
                    }
                } catch (IOException e) {
                    Log.e(TAG, "clearCacheInternal - " + e);
                }
                mHttpDiskCache = null;
                mHttpDiskCacheStarting = true;
                initHttpDiskCache();
            }
        }
    }

    @Override
    protected void flushCacheInternal() {
        super.flushCacheInternal();
        synchronized (mHttpDiskCacheLock) {
            if (mHttpDiskCache != null) {
                try {
                    mHttpDiskCache.flush();
                    if (BuildConfig.DEBUG) {
                        Log.d(TAG, "HTTP cache flushed");
                    }
                } catch (IOException e) {
                    Log.e(TAG, "flush - " + e);
                }
            }
        }
    }

    @Override
    protected void closeCacheInternal() {
        super.closeCacheInternal();
        synchronized (mHttpDiskCacheLock) {
            if (mHttpDiskCache != null) {
                try {
                    if (!mHttpDiskCache.isClosed()) {
                        mHttpDiskCache.close();
                        mHttpDiskCache = null;
                        if (BuildConfig.DEBUG) {
                            Log.d(TAG, "HTTP cache closed");
                        }
                    }
                } catch (IOException e) {
                    Log.e(TAG, "closeCacheInternal - " + e);
                }
            }
        }
    }

    // TODO: 2019/3/4
    private void checkNetworkConnection(Context context) {
        final ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if (networkInfo == null || !networkInfo.isConnectedOrConnecting()) {
            Toast.makeText(context, R.string.no_network_connection_toast, Toast.LENGTH_LONG).show();
            Log.e(TAG, "checkNetworkConnection - no connection found");
        }
    }

    /**
     * The main process method, which will be called by the ImageWorker in the AsyncTask background
     * thread.
     *
     * @param data The data to load the bitmap, in this case, a regular http URL
     * @return The downloaded and resized bitmap
     */
    private Bitmap processBitmap4DownloadResize(String data) {
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "processBitmap4DownloadResize - " + data);
        }

        final String key = fileUtil.hashKeyForDisk(data);
        FileDescriptor fileDescriptor = null;
        FileInputStream fileInputStream = null;
        DiskLruCache.Snapshot snapshot;
        synchronized (mHttpDiskCacheLock) {
            // Wait for disk cache to initialize
            while (mHttpDiskCacheStarting) {
                try {
                    mHttpDiskCacheLock.wait();
                } catch (InterruptedException e) {
                }
            }

            if (mHttpDiskCache != null) {
                try {
                    snapshot = mHttpDiskCache.get(key);
                    if (snapshot == null) {
                        if (BuildConfig.DEBUG) {
                            Log.d(TAG, "processBitmap4DownloadResize, not found in http cache, downloading...");
                        }
                        DiskLruCache.Editor editor = mHttpDiskCache.edit(key);
                        if (editor != null) {
                            if (downloadUrlToStream(data, editor.newOutputStream(DISK_CACHE_INDEX))) {
                                editor.commit();
                            } else {
                                editor.abort();
                            }
                        }
                        snapshot = mHttpDiskCache.get(key);
                    }
                    if (snapshot != null) {
                        fileInputStream = (FileInputStream) snapshot.getInputStream(DISK_CACHE_INDEX);
                        fileDescriptor = fileInputStream.getFD();
                    }
                } catch (IOException e) {
                    Log.e(TAG, "processBitmap4DownloadResize - " + e);
                } catch (IllegalStateException e) {
                    Log.e(TAG, "processBitmap4DownloadResize - " + e);
                } finally {
                    if (fileDescriptor == null && fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e) {
                        }
                    }
                }
            }
        }

        Bitmap bitmap = null;
        if (fileDescriptor != null) {
            bitmap = imageUtil.decodeSampledBitmapFromDescriptor(fileDescriptor, mImageWidth, mImageHeight, mImageCache);
        }
        if (fileInputStream != null) {
            try {
                fileInputStream.close();
            } catch (IOException e) {
                Log.e(TAG, "processBitmap4DownloadResize: " + e);
            }
        }
        return bitmap;
    }

    @Override
    protected Bitmap processBitmap(Object data) {
        return processBitmap4DownloadResize(String.valueOf(data));
    }

    private URLConnection createConnection(URL url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.addRequestProperty("User-Agent", ImageCacheParams.SDK_USER_AGENT);
        connection.setInstanceFollowRedirects(true);
        return connection;
    }

    /**
     * Download a bitmap from a URL and write the content to an output stream.
     *
     * @param url The URL to fetch
     * @return true if successful, false otherwise
     */
    public boolean downloadUrlToStream(String url, OutputStream outputStream) {
        disableConnectionReuseIfNecessary();
        // TODO: 2019/3/4  HttpsURLConnection
        HttpURLConnection connection = null;

        InputStream in = null;
        OutputStream out = null;

        try {
            /**
             FIXED_ERROR:java.lang.Throwable: Untagged socket detected; use TrafficStats.setThreadSocketTag() to track all network usage at android.os.StrictMode.onUntaggedSocket(StrictMode.java:2010)
             https://github.com/bitstadium/HockeySDK-Android/blob/5649d6e4e951eeb6d0d050d0563c645800b5e631/hockeysdk/src/main/java/net/hockeyapp/android/tasks/DownloadFileTask.java
             */
//            final URL url = new URL(url);
//            connection = (HttpURLConnection) url.openConnection();
            connection = (HttpURLConnection) createConnection(new URL(url));
            TrafficStats.setThreadStatsTag(ImageCacheParams.THREAD_STATS_TAG);
            connection.connect();

            int lengthOfFile = connection.getContentLength();
            Log.d(TAG, "downloadUrlToStream: lengthOfFile=" + lengthOfFile);
            String status = connection.getHeaderField("Status");

            if (status != null) {
                if (!status.startsWith("200")) {
                    return false;
                }
            }

            in = new BufferedInputStream(connection.getInputStream(), IO_BUFFER_SIZE);
            out = new BufferedOutputStream(outputStream, IO_BUFFER_SIZE);

            byte data[] = new byte[1024];
            int count;
            long total = 0;
            while ((count = in.read(data)) != -1) {
                total += count;
//                publishProgress((int) (total * 100 / lengthOfFile));
                // TODO:ImageFetcher: downloadUrlToStream: thread name= AsyncTask #3,thread id=13869,write 437916
                // use threadpool fetch data.
                if (BuildConfig.DEBUG) {
                    Log.d(TAG, "downloadUrlToStream: thread name= " + Thread.currentThread().getName() + ",thread id=" + Thread.currentThread().getId() + ",write " + total);
                }
                out.write(data, 0, count);
            }

            out.flush();
            return (total > 0);
//            return true;
        } catch (final IOException e) {
            Log.e(TAG, "Error in downloadBitmap - " + e);
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (final IOException e) {
                Log.e(TAG, "downloadUrlToStream: " + e);
            }
        }
        return false;
    }

    /**
     * Workaround for bug pre-Froyo, see here for more info:
     * http://android-developers.blogspot.com/2011/09/androids-http-clients.html
     */
    public static void disableConnectionReuseIfNecessary() {
        // HTTP connection reuse which was buggy pre-froyo
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.FROYO) {
            System.setProperty("http.keepAlive", "false");
        }
    }
}
