package com.hades.example.android.widget.custom_view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.ColorInt;
import androidx.annotation.Nullable;

import com.hades.example.android.R;

@SuppressLint("AppCompatCustomView")
public class MyView1 extends View {
    Paint paint = new Paint();


    public MyView1(Context context) {
        super(context);
    }

    public MyView1(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyView1(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyView1(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        paint.setColor(getColor()); //设置画笔颜色

        paint.setColor(getColor2()); //设置画笔颜色
        paint.setAlpha((int) (0.8 * 255));


        paint.setStyle(Paint.Style.FILL);   //设置画笔为空心 如果将这里改为Style.STROKE  这个图中的实线圆柱体就变成了空心的圆柱体
//        canvas.drawColor(Color.WHITE); //将背景填充成白色
        canvas.drawRect(0, 0, getSize(), getSize(), paint); //绘制矩形
    }

    private int getSize() {
        return (int) getResources().getDimension(R.dimen.size_100);
    }

    @ColorInt
    private int getColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return getResources().getColor(R.color.dark_m_0_opacity_80, getContext().getTheme());
        } else {
            return getResources().getColor(R.color.dark_m_0_opacity_80);
        }
    }

    @ColorInt
    private int getColor2() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return getResources().getColor(R.color.dark_m_0, getContext().getTheme());
        } else {
            return getResources().getColor(R.color.dark_m_0);
        }
    }
}