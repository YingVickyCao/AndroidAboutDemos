package com.hades.example.android.widget.view_animator;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextSwitcher;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.hades.example.android.R;
import com.hades.example.android.lib.base.BaseFragment;

/**
 * TextSwitcher - 左右滑动浏览文本
 **/
public class TextSwitcherFragment extends BaseFragment {
    private TextSwitcher mTextSwitcher;

    private String[] mShowedTextArray = {"Android", "IOS", "MAC", "PC"};
    private int mShowedTextIndex;

    private float mCoordinateXWhenTouchDown;
    private float mCoordinateXWhenTouchUp;

    public static TextSwitcherFragment newInstance() {
        return new TextSwitcherFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.widget_textswitcher, container, false);

        mTextSwitcher = view.findViewById(R.id.textSwitcher);
        // 为TextSwitcher设置Factory，用来为TextSwitcher制造TextView
        /**
         * setFactory(ViewFactory factory)
         */
        mTextSwitcher.setFactory(() -> {
            TextView textView = new TextView(getActivity());
            textView.setTextSize(100);
            textView.setLayoutParams(new TextSwitcher.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
            textView.setGravity(Gravity.CENTER);
            textView.setText(mShowedTextArray[mShowedTextIndex]);
            return textView;
        });
        // 设置TextSwitcher左右滑动事件
        mTextSwitcher.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                // 取得左右滑动时手指按下的X坐标
                mCoordinateXWhenTouchDown = event.getX();
                return true;

            } else if (event.getAction() == MotionEvent.ACTION_UP) {
                // 取得左右滑动时手指松开的X坐标
                mCoordinateXWhenTouchUp = event.getX();

                // 从左往右，看前一文本
                if (mCoordinateXWhenTouchUp - mCoordinateXWhenTouchDown > 100) {
                    prev();
                } else if (mCoordinateXWhenTouchDown - mCoordinateXWhenTouchUp > 100) {
                    next();
                }
                return true;
            }
            return false;
        });
        return view;
    }

    private void prev() {
        // 取得当前要看的文本的index
        mShowedTextIndex = mShowedTextIndex == 0 ? mShowedTextArray.length - 1 : mShowedTextIndex - 1;
        // 设置文本切换的动画
        mTextSwitcher.setInAnimation(AnimationUtils.loadAnimation(getActivity(), android.R.anim.slide_in_left));
        mTextSwitcher.setOutAnimation(AnimationUtils.loadAnimation(getActivity(), android.R.anim.slide_out_right));
        // 设置当前要看的文本
        mTextSwitcher.setText(mShowedTextArray[mShowedTextIndex]);
        // 从右往左，看下一张
    }

    private void next() {
        // 取得当前要看的文本的index
        mShowedTextIndex = mShowedTextIndex == mShowedTextArray.length - 1 ? 0 : mShowedTextIndex + 1;
        // 设置文本切换的动画
        // 由于Android没有提供slide_out_left和slide_in_right，所以仿照slide_in_left和slide_out_right编写了slide_out_left和slide_in_right
        mTextSwitcher.setInAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.slide_out_left));
        mTextSwitcher.setOutAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.slide_in_right));
        // 设置当前要看的文本
        mTextSwitcher.setText(mShowedTextArray[mShowedTextIndex]);
    }
}