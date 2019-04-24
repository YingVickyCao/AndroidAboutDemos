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
        Log.d(TAG, "RectView: 1");
    }

    public RectView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, R.attr.rectViewStyle);
        Log.d(TAG, "RectView: 2");
    }

    public RectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RectView, defStyleAttr, R.style.RectView_DefaultStyle);
        if (null == typedArray) {
            return;
        }
        String text = typedArray.getString(R.styleable.RectView_rvText);
        Log.d(TAG, "RectView: 3,text=" + text);
        setText(text);

        printTypedArray(typedArray);
        printAttributeSet(attrs);

        typedArray.recycle();
    }

    private void printTypedArray(TypedArray typedArray) {
        Log.d(TAG, "RectView: 3,typedArray.getIndexCount()=" + typedArray.getIndexCount());
        for (int i=0; i< typedArray.getIndexCount();i++){
            int index = typedArray.getIndex(i);
            Log.e(TAG, "RectView: 3,index=" + index);
        }

    }
    private void printAttributeSet(AttributeSet attrs) {
        int attributeCount = attrs.getAttributeCount();
        Log.i(TAG, "RectView: 3,当前属性个数为：" + attributeCount);
        for (int i = 0; i < attributeCount; i++) {
            String attributeName = attrs.getAttributeName(i);

            if (attributeName.equals("style")) {
                String attributeValue = attrs.getAttributeValue(i);
                Log.d(TAG, String.format("当前属性索引为：%d,索引名为：%s", i, attributeName) + ",当前属性值为：：" + attributeValue);
            } else {
                Log.d(TAG, String.format("当前属性索引为：%d,索引名为：%s", i, attributeName));
            }
        }
    }
}