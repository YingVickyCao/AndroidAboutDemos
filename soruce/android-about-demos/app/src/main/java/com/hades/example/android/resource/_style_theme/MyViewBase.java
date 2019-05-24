package com.hades.example.android.resource._style_theme;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.ColorInt;
import androidx.annotation.Nullable;

import com.hades.example.android.R;

@SuppressLint("AppCompatCustomView")
public abstract class MyViewBase extends View {
    Paint paint = new Paint();


    public MyViewBase(Context context) {
        super(context);
    }

    public MyViewBase(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyViewBase(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyViewBase(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//      paint.setColor(getColor()); //设置画笔颜色

        paint.setColor(getColor2()); //设置画笔颜色
        paint.setAlpha(getAlphaValue());

        paint.setStyle(Paint.Style.FILL);   //设置画笔为空心 如果将这里改为Style.STROKE  这个图中的实线圆柱体就变成了空心的圆柱体
        canvas.drawRect(0, 0, getSize(), getSize(), paint); //绘制矩形
    }

    private int getSize() {
        return (int) getResources().getDimension(R.dimen.size_80);
    }

    @ColorInt
    protected abstract int getColor();

    @ColorInt
    protected abstract int getColor2();

    protected abstract int getAlphaValue();
}