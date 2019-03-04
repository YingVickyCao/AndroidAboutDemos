package com.example.android.displayingbitmaps.ui;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

class ImageAdapter extends BaseAdapter {
    private static final String TAG = ImageAdapter.class.getSimpleName();

    private ImageGridFragment imageGridFragment;
    private final Context mContext;
    private int mItemHeight = 0;
    private int mNumColumns = 0;
    private GridView.LayoutParams mImageViewLayoutParams;
    private String[] mImageThumbUrls;

    public ImageAdapter(ImageGridFragment imageGridFragment, Context context, String[] imageThumbUrls) {
        super();
        mContext = context;
        this.imageGridFragment = imageGridFragment;
        mImageThumbUrls = imageThumbUrls;
        mImageViewLayoutParams = new GridView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
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
        imageGridFragment.mImageFetcher.loadImage(mImageThumbUrls[position], imageView);
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
