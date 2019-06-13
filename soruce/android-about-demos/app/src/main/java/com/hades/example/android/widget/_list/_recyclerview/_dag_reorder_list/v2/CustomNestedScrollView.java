package com.hades.example.android.widget._list._recyclerview._dag_reorder_list.v2;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CustomNestedScrollView extends NestedScrollView {
    private static final String TAG = CustomNestedScrollView.class.getSimpleName();

    public CustomNestedScrollView(@NonNull Context context) {
        super(context);
    }

    public CustomNestedScrollView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomNestedScrollView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    // TODO: 2019-06-04  

    @Override
    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
        Log.d(TAG, "onNestedPreScroll: dx=" + dx + ",dy=" + dy);

//        final RecyclerView rv = (RecyclerView) target;
//        if ((dy < 0 && isRvScrolledToTop(rv)) || (dy > 0 && !isNsvScrolledToBottom(this))) {
//            scrollBy(0, dy);
//            consumed[1] = dy;
//            return;
//        }
        super.onNestedPreScroll(target, dx, dy, consumed);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        Log.d(TAG, "onScrollChanged: l=" + l + ",t=" + t + ",oldl" + oldl + ",oldt=" + oldt);
    }

    @Override
    public void onScreenStateChanged(int screenState) {
        super.onScreenStateChanged(screenState);
        Log.d(TAG, "onScreenStateChanged: screenState=" + screenState);
    }

    @Override
    protected void onVisibilityChanged(@NonNull View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
        Log.d(TAG, "onVisibilityChanged: visibility=" + visibility);
    }

    @Override
    public boolean onDragEvent(DragEvent event) {
        Log.d(TAG, "onDragEvent: ");
        return super.onDragEvent(event);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Log.d(TAG, "onDetachedFromWindow:");
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Log.d(TAG, "onAttachedToWindow:");
    }

    @Override
    public boolean onNestedPrePerformAccessibilityAction(View target, int action, Bundle args) {
        return super.onNestedPrePerformAccessibilityAction(target, action, args);
    }

    @Override
    public boolean onInterceptHoverEvent(MotionEvent event) {
        return super.onInterceptHoverEvent(event);
    }

    @Override
    protected void onWindowVisibilityChanged(int visibility) {
        super.onWindowVisibilityChanged(visibility);
        Log.d(TAG, "onWindowVisibilityChanged:visibility=" + visibility);
    }

    @Override
    public boolean onHoverEvent(MotionEvent event) {
        return super.onHoverEvent(event);
    }

    @Override
    public void onViewAdded(View child) {
        super.onViewAdded(child);
        Log.d(TAG, "onViewAdded:child=" + child);
    }

    @Override
    public void onViewRemoved(View child) {
        super.onViewRemoved(child);
        Log.d(TAG, "onViewRemoved:child=" + child);
    }

    @Override
    public void onVisibilityAggregated(boolean isVisible) {
        super.onVisibilityAggregated(isVisible);
        Log.d(TAG, "onVisibilityAggregated:isVisible=" + isVisible);
    }

    @Override
    public boolean onNestedPreFling(View target, float velocityX, float velocityY) {
        Log.d(TAG, "onNestedPreScroll: velocityX=" + velocityX + ",dy=" + velocityY);

//        final RecyclerView rv = (RecyclerView) target;
//        if ((velocityY < 0 && isRvScrolledToTop(rv)) || (velocityY > 0 && !isNsvScrolledToBottom(this))) {
//            fling((int) velocityY);
//            return true;
//        }
        return super.onNestedPreFling(target, velocityX, velocityY);
    }

    @Override
    public boolean onNestedFling(View target, float velocityX, float velocityY, boolean consumed) {
        return super.onNestedFling(target, velocityX, velocityY, consumed);
    }

    private static boolean isNsvScrolledToBottom(NestedScrollView nsv) {
        return !nsv.canScrollVertically(1);
    }

    private static boolean isRvScrolledToTop(RecyclerView rv) {
        final LinearLayoutManager lm = (LinearLayoutManager) rv.getLayoutManager();
        return lm.findLastVisibleItemPosition() == 0 && lm.findViewByPosition(0).getTop() == 0;
    }
}