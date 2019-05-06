package com.hades.example.android.android_mechanism.event_handle.base_on_callback;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.hades.example.android.R;

/**
 * 基于回调的事件处理
 */
public class EventHandlerBaseOnCallbackActivity extends Activity {
    private static final String TAG = EventHandlerBaseOnCallbackActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.event_handler_base_on_callback);

        View btn = findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: setOnClickListener");
            }
        });

//        btn.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if (event.getAction() == MotionEvent.ACTION_DOWN) {
//                    Log.d(TAG, "onTouch: ACTION_DOWN in setOnTouchListener");
//
//                } else if (event.getAction() == MotionEvent.ACTION_UP) {
//                    Log.d(TAG, "onTouch: ACTION_UP in setOnTouchListener");
//                }
//                return false;
//            }
//        });

        btn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    Log.d(TAG, "onTouch: ACTION_DOWN in setOnTouchListener");

                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    Log.d(TAG, "onTouch: ACTION_UP in setOnTouchListener");
                }
                return true;
            }
        });

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            Log.d(TAG, "onTouchEvent: ACTION_DOWN in Activity");

        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            Log.d(TAG, "onTouchEvent: ACTION_UP in Activity");
        }
        return true;
    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        if (event.getAction() == MotionEvent.ACTION_DOWN) {
//            Log.d(TAG, "onTouchEvent: ACTION_DOWN in Activity");
//
//        } else if (event.getAction() == MotionEvent.ACTION_UP) {
//            Log.d(TAG, "onTouchEvent: ACTION_UP in Activity");
//        }
//        return false;
//    }
}