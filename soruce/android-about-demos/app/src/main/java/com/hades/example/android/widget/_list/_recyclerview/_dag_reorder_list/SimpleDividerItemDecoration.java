package com.hades.example.android.widget._list._recyclerview._dag_reorder_list;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.hades.example.android.R;

public class SimpleDividerItemDecoration extends RecyclerView.ItemDecoration {
    private static final String TAG = SimpleDividerItemDecoration.class.getSimpleName();
    private Drawable mDivider;
    private int mDividerHeight;
    private int mOrientation = LinearLayoutManager.VERTICAL;

    private static final int[] ATTRS = new int[]{android.R.attr.listDivider};

    public SimpleDividerItemDecoration(Context context, int dividerHeight) {
        final TypedArray a = context.obtainStyledAttributes(ATTRS);
        mDivider = a.getDrawable(0);
        a.recycle();
        mDividerHeight = dividerHeight;
    }

    public SimpleDividerItemDecoration(Context context, @DrawableRes Integer divider, int dividerHeight) {
        if (null == divider) {
            mDivider = ContextCompat.getDrawable(context, R.drawable.drawable_shape_4_divider_vertical);
        } else {
            mDivider = ContextCompat.getDrawable(context, divider);
        }
        mDividerHeight = dividerHeight;
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        Log.d(TAG, "onDrawOver: ");
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();

        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount - 1; i++) {// Last item has no divider=(i < childCount - 1)
            Log.d(TAG, "onDrawOver: i=" + i);
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            int top = child.getBottom() + params.bottomMargin;
            int bottom = top + mDividerHeight;
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }
}