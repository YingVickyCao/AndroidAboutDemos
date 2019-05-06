package com.hades.example.android.android_mechanism.event_handle.base_on_callback;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Button;

@SuppressLint("AppCompatCustomView")
public class MyButton extends Button {
    private static final String TAG = MyButton.class.getSimpleName();

    public MyButton(Context context, AttributeSet set) {
        super(context, set);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            Log.d(TAG, "onTouchEvent: ACTION_DOWN in MyButton");

        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            Log.d(TAG, "onTouchEvent: ACTION_UP in MyButton");
        }
        return true;
    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        if (event.getAction() == MotionEvent.ACTION_DOWN) {
//            Log.d(TAG, "onTouchEvent: ACTION_DOWN in MyButton");
//
//        } else if (event.getAction() == MotionEvent.ACTION_UP) {
//            Log.d(TAG, "onTouchEvent: ACTION_UP in MyButton");
//        }
//        // false，表示没有完全处理该事件，该事件依然向外扩散
//        return false;
//    }
}