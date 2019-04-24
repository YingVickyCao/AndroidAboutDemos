package com.hades.example.android.widget.custom_view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;

import androidx.annotation.ColorInt;
import androidx.annotation.Nullable;

import com.hades.example.android.R;

@SuppressLint("AppCompatCustomView")
public class MyView2 extends MyViewBase {
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

    @ColorInt
    protected int getColor2() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return getResources().getColor(R.color.light_m_0_opacity_30, getContext().getTheme());
        } else {
            return getResources().getColor(R.color.light_m_0_opacity_30);
        }
    }

    @ColorInt
    protected int getColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return getResources().getColor(R.color.light_m_0, getContext().getTheme());
        } else {
            return getResources().getColor(R.color.light_m_0);
        }
    }

    @Override
    protected int getAlphaValue() {
        return 77;
    }
}