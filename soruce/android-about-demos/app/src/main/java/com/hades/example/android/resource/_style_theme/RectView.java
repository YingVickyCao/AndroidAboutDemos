package com.hades.example.android.resource._style_theme;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.hades.example.android.R;

/*
// Instance1
D/RectView: RectView: 3,------->
D/RectView: RectView: 3,text=3-Style in Theme,size=1050,size_300_dp=1050,size_300_px=300

D/RectView: RectView: 3,typedArray.getIndexCount()=2
E/RectView: RectView: 3,index=0
E/RectView: RectView: 3,index=1
I/RectView: RectView: 3,当前属性个数为：7
D/RectView: 当前属性索引为：0,索引名为：textColor
D/RectView: 当前属性索引为：1,索引名为：gravity
D/RectView: 当前属性索引为：2,索引名为：layout_gravity
D/RectView: 当前属性索引为：3,索引名为：background
D/RectView: 当前属性索引为：4,索引名为：layout_width
D/RectView: 当前属性索引为：5,索引名为：layout_height
D/RectView: 当前属性索引为：6,索引名为：minHeight
D/RectView: RectView: 3,<-------
D/RectView: RectView: 2

// Instance2
D/RectView: RectView: 3,------->
D/RectView: RectView: 3,text=6-Not use theme,size=300,size_300_dp=1050,size_300_px=300

D/RectView: RectView: 3,typedArray.getIndexCount()=2
E/RectView: RectView: 3,index=0
E/RectView: RectView: 3,index=1
I/RectView: RectView: 3,当前属性个数为：9
D/RectView: 当前属性索引为：0,索引名为：textColor
D/RectView: 当前属性索引为：1,索引名为：gravity
D/RectView: 当前属性索引为：2,索引名为：layout_gravity
D/RectView: 当前属性索引为：3,索引名为：background
D/RectView: 当前属性索引为：4,索引名为：layout_width
D/RectView: 当前属性索引为：5,索引名为：layout_height

D/RectView: 当前属性索引为：6,索引名为：layout_marginTop

D/RectView: 当前属性索引为：7,索引名为：minHeight

D/RectView: 当前属性索引为：8,索引名为：style,当前属性值为：：@2131951843
D/RectView: RectView: 3,<-------
D/RectView: RectView: 2
 */

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

        Log.d(TAG, "RectView: 3,------->");

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RectView, defStyleAttr, R.style.RectView_DefaultStyle);
//        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RectView, defStyleAttr, 0);
        if (null == typedArray) {
            return;
        }
        String text = typedArray.getString(R.styleable.RectView_rvText);
        int size = (int) typedArray.getDimension(R.styleable.RectView_rvSize, 0);
        int size_300_dp = (int) getResources().getDimension(R.dimen.size_300);
        int size_300_px = (int) getResources().getDimension(R.dimen.size_300_px);

        /*
         RectView: 3,text=3-Style in Theme,size=1050,size_300_dp=1050,size_300_px=300
         RectView: 3,text=6-Not use theme,size=300,size_300_dp=1050,size_300_px=300
         */
        Log.d(TAG, "RectView: 3,text=" + text + ",size=" + size + ",size_300_dp=" + size_300_dp + ",size_300_px=" + size_300_px);
        setText(text);

        CustomViewTools tools = new CustomViewTools();
        tools.printAttributeSet(TAG, attrs);
        tools.printTypedArray(TAG, typedArray);

        typedArray.recycle();
        Log.d(TAG, "RectView: 3,<-------");
    }
}