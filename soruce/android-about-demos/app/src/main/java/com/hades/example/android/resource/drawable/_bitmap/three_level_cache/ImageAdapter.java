package com.hades.example.android.resource.drawable._bitmap.three_level_cache;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.hades.example.android.lib.utils.bitmap.fetch.IImageWorker;

class ImageAdapter extends BaseAdapter {
    private static final String TAG = ImageAdapter.class.getSimpleName();

    private final Context mContext;
    private IImageWorker mlistener;

    private int mItemHeight = 0;
    private int mNumColumns = 0;
    private GridView.LayoutParams mImageViewLayoutParams;
    private String[] mImageThumbUrls;

    public ImageAdapter(Context context, String[] imageThumbUrls) {
        super();
        mContext = context;
        mImageThumbUrls = imageThumbUrls;
        mImageViewLayoutParams = new GridView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    }

    public void setListener(IImageWorker listener) {
        this.mlistener = listener;
    }

    @Override
    public int getCount() {
        return mImageThumbUrls.length;
    }

    @Override
    public String getItem(int position) {
        Log.d(TAG, "getItem: position=" + position);
        return mImageThumbUrls[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup container) {
        ImageView imageView;
        if (convertView == null) {
            imageView = new RecyclingImageView(mContext);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setLayoutParams(mImageViewLayoutParams);
        } else {
            imageView = (ImageView) convertView;
        }

        if (imageView.getLayoutParams().height != mItemHeight) {
            imageView.setLayoutParams(mImageViewLayoutParams);
        }

        loadImageAsync2ImageView(position, imageView);
        return imageView;
    }

    private void loadImageAsync2ImageView(int position, ImageView imageView) {
        if (null != mlistener) {
            mlistener.loadImage(mImageThumbUrls[position], imageView);
        }
    }

    boolean setItemHeight(int height) {
        if (height == mItemHeight) {
            return false;
        }
        mItemHeight = height;
        mImageViewLayoutParams = new GridView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, mItemHeight);
        notifyDataSetChanged();
        return true;
    }

    public void setNumColumns(int numColumns) {
        mNumColumns = numColumns;
    }

    public int getNumColumns() {
        return mNumColumns;
    }
}
