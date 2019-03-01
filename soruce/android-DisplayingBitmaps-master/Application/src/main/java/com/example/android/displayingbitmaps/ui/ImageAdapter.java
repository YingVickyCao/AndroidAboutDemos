package com.example.android.displayingbitmaps.ui;

import android.content.Context;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.android.displayingbitmaps.provider.Images;

/**
 * The main adapter that backs the GridView. This is fairly standard except the number of
 * columns in the GridView is used to create a fake top row of empty views as we use a
 * transparent ActionBar and don't want the real top row of images to start off covered by it.
 */
class ImageAdapter extends BaseAdapter {

    private ImageGridFragment imageGridFragment;
    private final Context mContext;
    private int mItemHeight = 0;
    private int mNumColumns = 0;
    private int mActionBarHeight = 0;
    private GridView.LayoutParams mImageViewLayoutParams;

    public ImageAdapter(ImageGridFragment imageGridFragment, Context context) {
        super();
        this.imageGridFragment = imageGridFragment;
        mContext = context;
        mImageViewLayoutParams = new GridView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        // Calculate ActionBar height
        TypedValue tv = new TypedValue();
        if (context.getTheme().resolveAttribute(
                android.R.attr.actionBarSize, tv, true)) {
            mActionBarHeight = TypedValue.complexToDimensionPixelSize(
                    tv.data, context.getResources().getDisplayMetrics());
        }
    }

    @Override
    public int getCount() {
        // If columns have yet to be determined, return no items
        if (getNumColumns() == 0) {
            return 0;
        }

        // Size + number of columns for top empty row
        return Images.imageThumbUrls.length + mNumColumns;
    }

    @Override
    public Object getItem(int position) {
        return position < mNumColumns ?
                null : Images.imageThumbUrls[position - mNumColumns];
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

    @Override
    public View getView(int position, View convertView, ViewGroup container) {
        //BEGIN_INCLUDE(load_gridview_item)
        // First check if this is the top row
        if (position < mNumColumns) {
            if (convertView == null) {
                convertView = new View(mContext);
            }
            // Set empty view with height of ActionBar
            convertView.setLayoutParams(new AbsListView.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, mActionBarHeight));
            return convertView;
        }

        // Now handle the main ImageView thumbnails
        ImageView imageView;
        if (convertView == null) { // if it's not recycled, instantiate and initialize
            imageView = new RecyclingImageView(mContext);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setLayoutParams(mImageViewLayoutParams);
        } else { // Otherwise re-use the converted view
            imageView = (ImageView) convertView;
        }

        // Check the height matches our calculated column width
        if (imageView.getLayoutParams().height != mItemHeight) {
            imageView.setLayoutParams(mImageViewLayoutParams);
        }

        // Finally load the image asynchronously into the ImageView, this also takes care of
        // setting a placeholder image while the background thread runs
        imageGridFragment.mImageFetcher.loadImage(Images.imageThumbUrls[position - mNumColumns], imageView);
        return imageView;
        //END_INCLUDE(load_gridview_item)
    }

    /**
     * Sets the item height. Useful for when we know the column width so the height can be set
     * to match.
     *
     * @param height
     */
    void setItemHeight(int height) {
        if (height == mItemHeight) {
            return;
        }
        mItemHeight = height;
        mImageViewLayoutParams =
                new GridView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, mItemHeight);
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
