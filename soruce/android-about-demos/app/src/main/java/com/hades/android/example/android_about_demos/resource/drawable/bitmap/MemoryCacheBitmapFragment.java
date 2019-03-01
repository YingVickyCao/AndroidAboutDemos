package com.hades.android.example.android_about_demos.resource.drawable.bitmap;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.hades.android.example.android_about_demos.R;
import com.hades.android.example.android_about_demos.component.bitmap.BitmapUtil;

import java.util.ArrayList;
import java.util.List;

public class MemoryCacheBitmapFragment extends Fragment {
    private static final int MODEL_COUNT = 50;
    private ListView mListView;
    private ImageDisplayAdapter mAdapter;
    private List<ImageDisplayItem> mList;

    private LruCache<String, Bitmap> memoryCache;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.res_memory_cache_bitmap, container, false);
        mListView = view.findViewById(R.id.list);

        mList = mockListData();
        mAdapter = new ImageDisplayAdapter(mList, getActivity());
        mListView.setAdapter(mAdapter);

        initLruCache();
        return view;
    }

    private void initLruCache() {
        // Get max available VM memory, exceeding this amount will throw an
        // OutOfMemory exception. Stored in kilobytes as LruCache takes an
        // int in its constructor.
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);

        // Use 1/8th of the available memory for this memory cache.
        final int cacheSize = maxMemory / 8;

        memoryCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                // The cache size will be measured in kilobytes rather than
                // number of items.
                return bitmap.getByteCount() / 1024;
            }
        };
    }

    private List<ImageDisplayItem> mockListData() {
        final List<ImageDisplayItem> ret = new ArrayList<>(MODEL_COUNT);
        for (int i = 0; i < MODEL_COUNT; i++) {
            ret.add(new ImageDisplayItem(String.valueOf(i + 1), R.drawable.wallpaper));
        }
        return ret;
    }

    public void addBitmapToMemoryCache(String key, Bitmap bitmap) {
        if (getBitmapFromMemCache(key) == null) {
            memoryCache.put(key, bitmap);
        }
    }

    public Bitmap getBitmapFromMemCache(String key) {
        return memoryCache.get(key);
    }

    public void loadBitmap(int resId, ImageView mImageView) {
        final String imageKey = String.valueOf(resId);

        final Bitmap bitmap = getBitmapFromMemCache(imageKey);
        if (bitmap != null) {
            mImageView.setImageBitmap(bitmap);
        } else {
            mImageView.setImageResource(R.drawable.image_placeholder);
            BitmapWorkerTask task = new BitmapWorkerTask(mImageView);
            task.execute(resId);
        }
    }

    class BitmapWorkerTask extends AsyncTask<Integer, Void, Bitmap> {
        BitmapUtil  bitmapUtil = new BitmapUtil();
        private ImageView ImageView;

        public BitmapWorkerTask(android.widget.ImageView imageView) {
            ImageView = imageView;
        }

        // Decode image in background.
        @Override
        protected Bitmap doInBackground(Integer... params) {
            final Bitmap bitmap = bitmapUtil.decodeSampledBitmapFromResource(getResources(), params[0], 100, 100);
            addBitmapToMemoryCache(String.valueOf(params[0]), bitmap);
            return bitmap;
        }
    }
}