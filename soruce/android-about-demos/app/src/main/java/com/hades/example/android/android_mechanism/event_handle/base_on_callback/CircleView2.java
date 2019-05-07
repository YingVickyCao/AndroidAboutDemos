package com.hades.example.android.android_mechanism.event_handle.base_on_callback;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.hades.example.android.R;

public class CircleView2 extends View {
    public float currentX = 40;
    public float currentY = 50;

    Paint paint = new Paint();

    public CircleView2(Context context, AttributeSet set) {
        super(context, set);

        paint.setColor(Color.GREEN);
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawCircle(currentX, currentY, getResources().getDimension(R.dimen.size_15), paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // 当前组件的currentX、currentY两个属性
        this.currentX = event.getX();
        this.currentY = event.getY();

        // 通知改组件重绘
        this.invalidate();

        // 返回true表明处理方法已经处理该事件
        return true;
    }
}
