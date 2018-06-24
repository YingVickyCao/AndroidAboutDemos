package com.hades.android.example.android_about_demos.event_handle.base_on_callback;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.Toast;

@SuppressLint("AppCompatCustomView")
public class MyButton extends Button {
    private static final String TAG = MyButton.class.getSimpleName();

    public MyButton(Context context, AttributeSet set) {
        super(context, set);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            Toast.makeText(getContext(), "ACTION_DOWN", Toast.LENGTH_SHORT).show();
            return true;

        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            Toast.makeText(getContext(), "ACTION_UP", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onTouchEvent(event);
    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        if (event.getAction() == MotionEvent.ACTION_DOWN) {
//            Toast.makeText(getContext(), "ACTION_DOWN", Toast.LENGTH_SHORT).show();
//            return super.onTouchEvent(event);
//
//        } else if (event.getAction() == MotionEvent.ACTION_UP) {
//            Toast.makeText(getContext(), "ACTION_UP", Toast.LENGTH_SHORT).show();
//            return super.onTouchEvent(event);
//        }
//        return super.onTouchEvent(event);
//    }
}