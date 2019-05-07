package com.hades.example.android.android_mechanism.event_handle.base_on_listener.plane;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.hades.example.android.R;

public class CircleView1 extends View {
    public float currentX;
    public float currentY;

    Paint paint = new Paint();

    public CircleView1(Context context) {
        super(context);
        setFocusable(true);

        paint.setColor(Color.RED);
    }

    public CircleView1(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        setFocusable(true);
        paint.setColor(Color.RED);
    }

    public CircleView1(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(currentX, currentY, getResources().getDimension(R.dimen.size_15), paint);
    }
}