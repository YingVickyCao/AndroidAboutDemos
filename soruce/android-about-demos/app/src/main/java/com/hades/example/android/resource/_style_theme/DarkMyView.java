package com.hades.example.android.resource._style_theme;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;

import androidx.annotation.ColorInt;
import androidx.annotation.Nullable;

import com.hades.example.android.R;

@SuppressLint("AppCompatCustomView")
public class DarkMyView extends MyViewBase {
    public DarkMyView(Context context) {
        super(context);
    }

    public DarkMyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DarkMyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public DarkMyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
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