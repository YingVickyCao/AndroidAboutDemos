package com.hades.example.android.widget.custom_view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.hades.example.android.R;

@SuppressLint("AppCompatCustomView")
public class RectView extends TextView {
    private static final String TAG = RectView.class.getSimpleName();

    public RectView(Context context) {
        super(context);
        Log.d(TAG, "CircleView: 1");
    }

    public RectView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        Log.d(TAG, "CircleView: 2");

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RectView, R.attr.reactViewText, R.style.DefaultStyleRes);
        if (null == typedArray) {
            return;
        }
        String text = typedArray.getString(R.styleable.RectView_rvContent);
        setText(text);
        typedArray.recycle();
    }

    public RectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Log.d(TAG, "CircleView: 3");
    }
}