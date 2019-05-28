package com.hades.example.android.widget.custom_view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.hades.example.android.R;

public class Oval extends View {
    Paint paint;

    public Oval(Context context) {
        super(context);
        paint = new Paint();
    }

    public Oval(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
    }

    public Oval(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint();
    }

    public Oval(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        paint = new Paint();
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        canvas.drawColor(Color.BLACK);

        paint.setAntiAlias(true);
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(getResources().getDimension(R.dimen.stroke_width));

        int left = (int) getResources().getDimension(R.dimen.size_50);
        int top = (int) getResources().getDimension(R.dimen.size_25);
        int right = (int) getResources().getDimension(R.dimen.size_150);
        int bottom = (int) getResources().getDimension(R.dimen.size_75);

        // 绘制椭圆
        canvas.drawOval(left, top, right, bottom, paint);
    }
}
