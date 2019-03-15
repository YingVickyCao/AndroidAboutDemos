package com.hades.example.android.widget.custom_view.cascadelayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.hades.example.android.R;

public class CascadeLayout extends ViewGroup {

    private int mHorizontalSpacing;
    private int mVerticalSpacing;

    /**
     * 重要的方法1：构造函数
     * 当通过 XML 文件创建该视图的实例时会调用该构造函数。
     * mHorizontalSpacing 和 mVerticalSpacing 。由自定义属性中获得，如果其值未指定，就是使用默认值
     */
    public CascadeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CascadeLayout);
        try {
            mHorizontalSpacing = a.getDimensionPixelSize(R.styleable.CascadeLayout_horizontal_spacing, getResources().getDimensionPixelSize(R.dimen.cascade_horizontal_spacing));
            mVerticalSpacing = a.getDimensionPixelSize(R.styleable.CascadeLayout_vertical_spacing, getResources().getDimensionPixelSize(R.dimen.cascade_vertical_spacing));
        } finally {
            if (null != a) {
                a.recycle();
            }
        }
    }

    /**
     * 重要的方法2：onMeasure
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        /**
         * 使用宽度和高度计算布局的最终大小以及子视图的 x 与 y 轴的位置
         */
        int width = getPaddingLeft();
        int height = getPaddingTop();

        int verticalSpacing;

        final int count = getChildCount();
        for (int i = 0; i < count; i++) {

            View child = getChildAt(i);

            /**
             * 令每个子视图测量自身
             * measureChild -> measure(int,int)
             */
            measureChild(child, widthMeasureSpec, heightMeasureSpec);

            LayoutParams lp = (LayoutParams) child.getLayoutParams();
            width = getPaddingLeft() + mHorizontalSpacing * i;

            /**
             * 在LayoutParams中保存每个子视图的 x 与 y 坐标
             */
            lp.x = width;
            lp.y = height;

            /**
             * layout_vertical_spacing:为特定子视图重写垂直间距,否则，就用默认的垂直间距
             */
            if (lp.layoutParamsVerticalSpacing >= 0) {
                verticalSpacing = lp.layoutParamsVerticalSpacing;
            } else {
                verticalSpacing = mVerticalSpacing;
            }

            width += child.getMeasuredWidth();
            height += verticalSpacing;
        }

        width += getPaddingRight();
        height += getChildAt(getChildCount() - 1).getMeasuredHeight() + getPaddingBottom();

        /**
         * 使用计算所得的宽和高设置整个布局的测量尺寸
         */
        setMeasuredDimension(resolveSize(width, widthMeasureSpec), resolveSize(height, heightMeasureSpec));
    }

    /**
     * 重要的方法3：onLayout
     * 以onMeasure（）方法计算出的值为参数循环调用子 View 中的 layout()方法。
     *
     * @param changed
     * @param l
     * @param t
     * @param r
     * @param b
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        final int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            LayoutParams lp = (LayoutParams) child.getLayoutParams();

            /**
             * layout(int,int,int,int)
             */
            child.layout(lp.x, lp.y, lp.x + child.getMeasuredWidth(), lp.y + child.getMeasuredHeight());
        }
    }

    @Override
    protected boolean checkLayoutParams(ViewGroup.LayoutParams p) {
        return p instanceof LayoutParams;
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
//        return new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        return new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
//        return new LayoutParams(getContext(), attrs);
        return new CascadeLayout.LayoutParams(getContext(), attrs);
    }

    @Override
    protected LayoutParams generateLayoutParams(ViewGroup.LayoutParams p) {
        return new LayoutParams(p.width, p.height);
    }

    /**
     * 创建自定义LayoutParams， 该类用于保存每个子视图的 x、y 轴位置。
     */
    private static class LayoutParams extends ViewGroup.LayoutParams {
        int x;
        int y;
        int layoutParamsVerticalSpacing;

        LayoutParams(Context context, AttributeSet attrs) {
            super(context, attrs);

            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CascadeLayout_LayoutParams);
            try {
                layoutParamsVerticalSpacing = a.getDimensionPixelSize(R.styleable.CascadeLayout_LayoutParams_layout_vertical_spacing, context.getResources().getDimensionPixelSize(R.dimen.cascade_layout_params_layout_vertical_spacing));
            } finally {
                a.recycle();
            }
        }

        LayoutParams(int w, int h) {
            super(w, h);
        }

    }
}