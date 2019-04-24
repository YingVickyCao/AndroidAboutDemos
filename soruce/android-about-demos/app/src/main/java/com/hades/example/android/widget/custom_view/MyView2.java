package com.hades.example.android.widget.custom_view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.hades.example.android.R;

@SuppressLint("AppCompatCustomView")
public class MyView2 extends View {
    Paint paint = new Paint();


    public MyView2(Context context) {
        super(context);
    }

    public MyView2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyView2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyView2(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        paint.setColor(Color.RED); //设置画笔颜色
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            paint.setColor(getResources().getColor(R.color.light_m_0_opacity_30, getContext().getTheme())); //设置画笔颜色
        } else {
            paint.setColor(getResources().getColor(R.color.light_m_0_opacity_30)); //设置画笔颜色
        }
        paint.setStyle(Paint.Style.FILL);   //设置画笔为空心 如果将这里改为Style.STROKE  这个图中的实线圆柱体就变成了空心的圆柱体
//        canvas.drawColor(Color.WHITE); //将背景填充成白色
        canvas.drawRect(0, 0, getSize(), getSize(), paint); //绘制矩形
    }

    private int getSize() {
        return (int) getResources().getDimension(R.dimen.size_100);
    }
}