package com.hades.example.android.widget.custom_view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.hades.example.android.R;

public class RectView extends View {
    private static final String TAG = RectView.class.getSimpleName();

    Paint paint;

    public RectView(Context context, AttributeSet set) {
        super(context, set);
        paint = new Paint();
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        Log.d(TAG, "draw:" + "left=" + getLeft() + ",top=" + getTop() + ",right=" + getRight() + ",bottom=" + getBottom());

        canvas.drawColor(Color.BLACK);

        paint.setAntiAlias(true);
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(getResources().getDimension(R.dimen.size_2));

        int left = (int) getResources().getDimension(R.dimen.size_50);
        int top = (int) getResources().getDimension(R.dimen.size_25);
        int right = (int) getResources().getDimension(R.dimen.size_150);
        int bottom = (int) getResources().getDimension(R.dimen.size_75);
        canvas.drawRect(left, top, right, bottom, paint);
    }
}