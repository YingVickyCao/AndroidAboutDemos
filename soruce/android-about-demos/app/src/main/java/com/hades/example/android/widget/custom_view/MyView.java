package com.hades.example.android.widget.custom_view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.ColorInt;
import androidx.annotation.Nullable;

import com.hades.example.android.R;

@SuppressLint("AppCompatCustomView")
public class MyView extends View {

    private @ColorInt
    int colorId;
    Paint paint = new Paint();

    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
//
//        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyView, R.attr.MyViewStyle, defStyleRes);
//        if (null == typedArray) {
//            return;
//        }
//        colorId = typedArray.getColor(R.styleable.MyView_bgColor, Color.RED);
//        typedArray.recycle();
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paint.setColor(Color.RED); //设置画笔颜色

        paint.setStyle(Paint.Style.FILL);   //设置画笔为空心 如果将这里改为Style.STROKE  这个图中的实线圆柱体就变成了空心的圆柱体
        canvas.drawRect(0, 0, getSize(), getSize(), paint); //绘制矩形
    }

    private int getSize() {
        return (int) getResources().getDimension(R.dimen.size_80);
    }
}