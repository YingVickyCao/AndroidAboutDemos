package com.hades.example.android.app_component._activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.hades.example.android.R;

import java.util.Locale;

public class GetViewSizeInOncreateActivity extends Activity {
    private static final String TAG = GetViewSizeInOncreateActivity.class.getSimpleName();

    private TextView mTextView;

    /**
     * GetViewSizeInOncreateActivity: onCreate:
     * GetViewSizeInOncreateActivity: onCreate: width=0,height0
     * GetViewSizeInOncreateActivity: onStart:
     * GetViewSizeInOncreateActivity: onResume:
     * MyTextView: onMeasure:
     * MyTextView: onMeasure:
     * MyTextView: onLayout:
     * MyTextView: layout:
     * GetViewSizeInOncreateActivity: onCreate2: width=1440,height66
     * MyTextView: onDraw:
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
        setContentView(R.layout.activity_get_view_size_in_oncreate_layout);

        mTextView = findViewById(R.id.my_text_view);
        Log.d(TAG, "onCreate: width=" + mTextView.getWidth() + ",height" + mTextView.getHeight());

        //布局绘制后获取View的大小
        mTextView.post(new Runnable() {
            @Override
            public void run() {
                //得到正确的宽和高
                Log.d(TAG, "onCreate2: width=" + mTextView.getWidth() + ",height" + mTextView.getHeight());
                String size = String.format(Locale.getDefault(), "TextView's width: %d, height: %d", mTextView.getWidth(), mTextView.getHeight());
                Toast.makeText(GetViewSizeInOncreateActivity.this, size, Toast.LENGTH_SHORT).show();
                mTextView.setText(size);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }
}

