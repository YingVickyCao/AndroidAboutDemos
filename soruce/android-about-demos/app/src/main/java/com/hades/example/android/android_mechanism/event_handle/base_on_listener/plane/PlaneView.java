package com.hades.example.android.android_mechanism.event_handle.base_on_listener.plane;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

import com.hades.example.android.R;

public class PlaneView extends View {
    public float currentX = 100;
    public float currentY = 100;
    Bitmap plane;

    public PlaneView(Context context) {
        super(context);
        plane = BitmapFactory.decodeResource(context.getResources(), R.drawable.drawable_shape_4_circular);
        setFocusable(true);
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//         Waring:Avoid object allocations during draw/layout operations (preallocate and reuse instead)
        Paint paint = new Paint();
        canvas.drawBitmap(plane, currentX, currentY, paint);
    }
}