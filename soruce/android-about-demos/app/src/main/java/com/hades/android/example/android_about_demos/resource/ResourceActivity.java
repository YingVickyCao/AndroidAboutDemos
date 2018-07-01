package com.hades.android.example.android_about_demos.resource;

import android.app.Activity;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Button;

import com.hades.android.example.android_about_demos.R;

/**
 * https://www.cnblogs.com/andriod-html5/archive/2012/04/30/2539419.html
 */
public class ResourceActivity extends Activity {
    private static final String TAG = ResourceActivity.class.getSimpleName();

    Button normalArrayBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resources);

        normalArrayBtn = findViewById(R.id.getNormalArray);
        normalArrayBtn.setOnClickListener(v -> getNormalArray());
        findViewById(R.id.getStringArray).setOnClickListener(v -> getStringArray());
        findViewById(R.id.getIntArray).setOnClickListener(v -> getIntArray());
    }

    private void getNormalArray() {
        TypedArray typedArray = getResources().obtainTypedArray(R.array.normal_array);
//        Drawable drawable = typedArray.getDrawable(0);
//        normalArrayBtn.setBackground(drawable);

        int  color = typedArray.getColor(1,0);
        normalArrayBtn.setBackgroundColor(color);
    }

    private void getStringArray() {
        /**
         * getResources().getStringArray()
         */
        String[] strings = getResources().getStringArray(R.array.string_array);
        String str = "";
        for (int i = 0; i < strings.length; i++) {
            str += strings[i] + ",";
        }
        Log.d(TAG, "getStringArray: " + str);
    }

    private void getIntArray() {
        /**
         * getResources().getIntArray()
         */
        int[] ints = getResources().getIntArray(R.array.int_array);
        String str = "";
        for (int i = 0; i < ints.length; i++) {
            str += ints[i] + ",";
        }
        Log.d(TAG, "getIntArray: " + str);
    }

}
