package com.hades.example.android.widget.textview;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.hades.example.android.R;
import com.hades.example.android.lib.base.BaseFragment;

public class TestTextViewFragment extends BaseFragment {
    private static final String TAG = TestTextViewFragment.class.getSimpleName();
    private TextView tv1;

    TextView mTextView;
    TextView mTextView2;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.widget_textview, container, false);

        /**
         * Get View size
         */
        tv1 = view.findViewById(R.id.tv1);
        printTv1Size("1"); // 1,width=0,height=0
        view.findViewById(R.id.requestViewSize).setOnClickListener(v -> printTv1Size("2")); // 2,width=263,height=263
        // https://blog.csdn.net/linghu_java/article/details/46544811
        tv1.getViewTreeObserver().addOnGlobalLayoutListener(() -> printTv1Size("3")); // 3,width=263,height=263

        /**
         * setOnClickListener 对android:clickable=true 的影响
         */
        mTextView = view.findViewById(R.id.clickTextView);
        mTextView2 = view.findViewById(R.id.clickTextView2);
        mTextView.setClickable(false);
        mTextView2.setClickable(false);
        // 添加的OnClick后，自动设置 android:clickable=true. 但要注意调用的顺序
        view.findViewById(R.id.clickTextView).setOnClickListener(v -> clickTextView("Red TextView is clicked"));
        mTextView.setClickable(false);
        view.findViewById(R.id.clickTextView2).setOnClickListener(v -> clickTextView("Blue TextView is clicked"));
        return view;
    }

    private void printTv1Size(String tag) {
        int width = tv1.getWidth();
        int height = tv1.getHeight();
        Log.d(TAG, "printTv1Size:" + tag + ",width=" + width + ",height=" + height);
    }

    private void clickTextView(String msg) {
        showToast(msg);
    }
}