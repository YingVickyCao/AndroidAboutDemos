package com.hades.example.android.widget.textview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;

@SuppressLint("AppCompatCustomView")
public class MyTextView extends TextView {
    private static final String TAG = MyTextView.class.getSimpleName();

    public MyTextView(Context context) {
        super(context);
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    /**
     * Way6: View Override onSizeChanged()
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {    // After onResume
        super.onSizeChanged(w, h, oldw, oldh);
        Log.d(TAG, "onSizeChanged:" + "6" + ",width=" + getWidth() + ",height=" + getHeight());// onSizeChanged:6,width=263,height=263
    }

    /**
     * Way7: View Override onLayout()
     */
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {    // After onResume
        super.onLayout(changed, left, top, right, bottom);

        Log.d(TAG, "onLayout:" + "7" + ",width=" + getWidth() + ",height=" + getHeight()); // onLayout:7,width=263,height=263
    }
}
