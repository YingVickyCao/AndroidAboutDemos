package com.hades.example.android.resource.drawable._bitmap.three_level_cache;

import android.content.Context;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.hades.example.android.lib.utils.ThemeUtils;

class ImageAdapterWithActionBar extends BaseAdapter {
    private static final String TAG = ImageAdapterWithActionBar.class.getSimpleName();

    private ImageGridFragment imageGridFragment;
    private final Context mContext;
    private int mItemHeight = 0;
    private int mNumColumns = 0;
    private int mActionBarHeight = 0;
    private GridView.LayoutParams mImageViewLayoutParams;
    private String[] mImageThumbUrls;

    public ImageAdapterWithActionBar(ImageGridFragment imageGridFragment, Context context, String[] imageThumbUrls) {
        super();
        mContext = context;
        this.imageGridFragment = imageGridFragment;
        mImageThumbUrls = imageThumbUrls;

        mImageViewLayoutParams = new GridView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        calculateActionBarHeight();

    }

    private void calculateActionBarHeight() {
        mActionBarHeight = TypedValue.complexToDimensionPixelSize(ThemeUtils.getDataInValueIdByAttrId(mContext, android.R.attr.actionBarSize), mContext.getResources().getDisplayMetrics());
    }

    @Override
    public int getCount() {
        if (getNumColumns() == 0) {
            return 0;
        }
        return countWithEmptyRow();
    }

    private int countWithEmptyRow() {
        Log.d(TAG, "countWithEmptyRow: count=" + mImageThumbUrls.length + mNumColumns);
        return mImageThumbUrls.length + mNumColumns;
    }

    @Override
    public String getItem(int position) {
        Log.d(TAG, "getItem: position=" + position);
        return position < mNumColumns ? null : mImageThumbUrls[position - mNumColumns];
    }

    @Override
    public long getItemId(int position) {
        return position < mNumColumns ? 0 : position - mNumColumns;
    }

    @Override
    public int getViewTypeCount() {
        // Two types of views, the normal ImageView and the top row of empty views
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        return (position < mNumColumns) ? 1 : 0;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    private boolean isTopRow(int position) {
        return position < mNumColumns;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup container) {
        if (isTopRow(position)) {
            if (convertView == null) {
                convertView = new View(mContext);
            }
            // Set empty view with height of ActionBar
            convertView.setLayoutParams(new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, mActionBarHeight));
            return convertView;
        }

        // Now handle the main ImageView thumbnails
        ImageView imageView;
        if (convertView == null) {
            imageView = new RecyclingImageView(mContext);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setLayoutParams(mImageViewLayoutParams);
        } else {
            imageView = (ImageView) convertView;
        }

        // Check the height matches our calculated column width
        if (imageView.getLayoutParams().height != mItemHeight) {
            imageView.setLayoutParams(mImageViewLayoutParams);
        }

        // Finally load the image asynchronously into the ImageView, this also takes care of
        // setting a placeholder image while the background thread runs
        imageGridFragment.mImageFetcher.loadImage(mImageThumbUrls[position - mNumColumns], imageView);
        return imageView;
    }

    void setItemHeight(int height) {
        if (height == mItemHeight) {
            return;
        }
        mItemHeight = height;
        mImageViewLayoutParams = new GridView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, mItemHeight);
        imageGridFragment.mImageFetcher.setImageSize(height);
        notifyDataSetChanged();
    }

    public void setNumColumns(int numColumns) {
        mNumColumns = numColumns;
    }

    public int getNumColumns() {
        return mNumColumns;
    }
}
