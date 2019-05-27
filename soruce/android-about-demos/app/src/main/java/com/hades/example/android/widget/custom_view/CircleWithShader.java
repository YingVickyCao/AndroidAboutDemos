package com.hades.example.android.widget.custom_view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import com.hades.example.android.R;

public class CircleWithShader extends View {
    Paint paint;

    public CircleWithShader(Context context, AttributeSet set) {
        super(context, set);
        paint = new Paint();
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        canvas.drawColor(Color.BLACK);

        // 为Paint设置渐变器
        Shader mShader = new LinearGradient(0, 0, 40, 60, new int[]{Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW}, null, Shader.TileMode.REPEAT);
        paint.setShader(mShader);
        //设置阴影
//        paint.setShadowLayer(25, 20, 20, Color.GRAY);

        paint.setAntiAlias(true); // 去锯齿
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setStrokeWidth(4);

        // 绘制圆形
        int radius = (int) getResources().getDimension(R.dimen.size_50);
        canvas.drawCircle(radius * 2, radius * 2, radius, paint);
    }
}