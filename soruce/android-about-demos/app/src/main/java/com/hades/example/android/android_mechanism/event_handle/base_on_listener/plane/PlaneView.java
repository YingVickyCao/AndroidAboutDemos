package com.hades.example.android.android_mechanism.event_handle.base_on_listener.plane;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import com.hades.example.android.R;

public class PlaneView extends View {
    public float currentX;
    public float currentY;
//    Bitmap plane;

    Paint paint = new Paint();

    public PlaneView(Context context) {
        super(context);
        // 定义飞机图片
//        plane = BitmapFactory.decodeResource(context.getResources(), R.drawable.drawable_shape_4_circular);
        setFocusable(true);

        paint.setColor(Color.RED);
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // Waring:Avoid object allocations during draw/layout operations (preallocate and reuse instead)
//        Paint paint = new Paint();
        // 绘制飞机
//        canvas.drawBitmap(plane, currentX, currentY, paint);
        canvas.drawCircle(currentX, currentY,getResources().getDimension(R.dimen.size_15), paint);
    }
}