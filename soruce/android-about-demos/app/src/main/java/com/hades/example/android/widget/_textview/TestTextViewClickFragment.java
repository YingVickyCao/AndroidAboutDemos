package com.hades.example.android.widget._textview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.hades.example.android.R;
import com.hades.example.android.lib.base.BaseFragment;

public class TestTextViewClickFragment extends BaseFragment {

    TextView mTextView;
    TextView mTextView2;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.widget_textview_4_click_action, container, false);

        mTextView = view.findViewById(R.id.clickTextView);
        mTextView2 = view.findViewById(R.id.clickTextView2);

        mTextView.setClickable(false);
        mTextView2.setClickable(false);

        /**
         * 添加的OnClick后，自动设置 android:clickable=true. 但要注意调用的顺序
         */
        view.findViewById(R.id.clickTextView).setOnClickListener(v -> clickTextView("Red TextView is clicked"));
        view.findViewById(R.id.clickTextView2).setOnClickListener(v -> clickTextView("Blue TextView is clicked"));

        mTextView.setClickable(false);
        return view;
    }

    private void clickTextView(String msg) {
        showToast(msg);
    }

}