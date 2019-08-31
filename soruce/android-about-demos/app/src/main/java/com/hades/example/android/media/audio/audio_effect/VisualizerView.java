package com.hades.example.android.media.audio.audio_effect;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.hades.example.android.R;

public class VisualizerView extends View {
    // bytes数组保存了波形抽样点的值
    private byte[] bytes;
    private float[] points;
    private Paint paint = new Paint();
    private Rect rect = new Rect();
    private byte type = 0;

    public VisualizerView(Context context) {
        super(context);

        init();
    }

    public VisualizerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, R.attr.rectViewStyle);

        init();
    }

    public VisualizerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init() {
        bytes = null;
        paint.setStrokeWidth(1f);
        paint.setAntiAlias(true);
        paint.setColor(Color.parseColor("#52BE80"));
        paint.setStyle(Paint.Style.FILL);
    }

    public void updateVisualizer(byte[] ftt) {
        bytes = ftt;
        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent me) {
        if (me.getAction() != MotionEvent.ACTION_DOWN) {
            return false;
        }
        type++;
        if (type >= 3) {
            type = 0;
        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (bytes == null) {
            return;
        }
        canvas.drawColor(Color.WHITE);
        // 使用rect对象记录该组件的宽度和高度
        rect.set(0, 0, getWidth(), getHeight());
        switch (type) {
            case 0:
                onDraw4MachineWave(canvas);
                break;
            case 1:
                onDraw4HistogramWave(canvas);
                break;
            case 2:
                // 如果point数组还未初始化
                onDraw4WaveWave(canvas);
                break;
        }
    }

    private void onDraw4MachineWave(Canvas canvas) {
        for (int i = 0; i < bytes.length - 1; i++) {
            float left = getWidth() * i / (bytes.length - 1);
            // 根据波形值计算该矩形的高度
            float top = rect.height() - (byte) (bytes[i + 1] + 128) * rect.height() / 128;
            float right = left + 1;
            float bottom = rect.height();
            canvas.drawRect(left, top, right, bottom, paint);
        }
    }

    private void onDraw4HistogramWave(Canvas canvas) {
        for (int i = 0; i < bytes.length - 1; i += 18) {
            float left = rect.width() * i / (bytes.length - 1);
            // 根据波形值计算该矩形的高度
            float top = rect.height() - (byte) (bytes[i + 1] + 128) * rect.height() / 128;
            float right = left + 6;
            float bottom = rect.height();
            canvas.drawRect(left, top, right, bottom, paint);
        }
    }

    private void onDraw4WaveWave(Canvas canvas) {
        if (points == null || points.length < bytes.length * 4) {
            points = new float[bytes.length * 4];
        }
        for (int i = 0; i < bytes.length - 1; i++) {
            // 计算第i个点的x坐标
            points[i * 4] = rect.width() * i / (bytes.length - 1);

            // 根据bytes[i]的值（波形点的值）计算第i个点的y坐标
            points[i * 4 + 1] = (rect.height() / 2) + ((byte) (bytes[i] + 128)) * 128 / (rect.height() / 2);

            // 计算第i+1个点的x坐标
            points[i * 4 + 2] = rect.width() * (i + 1) / (bytes.length - 1);

            // 根据bytes[i+1]的值（波形点的值）计算第i+1个点的y坐标
            points[i * 4 + 3] = (rect.height() / 2) + ((byte) (bytes[i + 1] + 128)) * 128 / (rect.height() / 2);
        }
        // 绘制波形曲线
        canvas.drawLines(points, paint);
    }
}