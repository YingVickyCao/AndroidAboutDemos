package com.hades.example.android.widget.custom_view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.hades.example.android.R;

public class Text extends View {
    Paint paint;

    public Text(Context context, AttributeSet set) {
        super(context, set);
        paint = new Paint();
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        canvas.drawColor(Color.BLACK);

        paint.setAntiAlias(true); // 去锯齿
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(getResources().getDimension(R.dimen.stroke_width));
        paint.setTextAlign(Paint.Align.CENTER);

        paint.setTextSize(getResources().getDimension(R.dimen.text_size_20));
        int x = (int) getResources().getDimension(R.dimen.size_50);
        int y = x;
        canvas.drawText(getResources().getString(R.string.circle), x,y, paint);
    }
}