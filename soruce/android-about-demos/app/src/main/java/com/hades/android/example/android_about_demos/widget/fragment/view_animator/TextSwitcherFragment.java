package com.hades.android.example.android_about_demos.widget.fragment.view_animator;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.hades.android.example.android_about_demos.R;

/**
 * TextSwitcher - 左右滑动浏览文本
 **/
public class TextSwitcherFragment extends Fragment implements ViewSwitcher.ViewFactory {
    private TextSwitcher mTextSwitcher;

    // 图片数组
    private String[] arrayTexts = {"Android", "IOS", "MAC", "PC"};
    // 要显示的图片在图片数组中的Index
    private int textIndex;
    // 左右滑动时手指按下的X坐标
    private float touchDownX;
    // 左右滑动时手指松开的X坐标
    private float touchUpX;

    public static TextSwitcherFragment newInstance() {
        return new TextSwitcherFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.widget_textswitcher, container, false);

        mTextSwitcher = view.findViewById(R.id.textSwitcher);
        // 为TextSwitcher设置Factory，用来为TextSwitcher制造TextView
        mTextSwitcher.setFactory(this);
        // 设置TextSwitcher左右滑动事件
        mTextSwitcher.setOnTouchListener((v, event) -> onTouch2(v, event));
        return view;
    }

    @Override
    public View makeView() {
        TextView textView = new TextView(getActivity());
        textView.setTextSize(100);
        textView.setLayoutParams(new TextSwitcher.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        textView.setGravity(Gravity.CENTER);
        textView.setText(arrayTexts[textIndex]);
        return textView;
    }

    public boolean onTouch2(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            // 取得左右滑动时手指按下的X坐标
            touchDownX = event.getX();
            return true;
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            // 取得左右滑动时手指松开的X坐标
            touchUpX = event.getX();
            // 从左往右，看前一文本
            if (touchUpX - touchDownX > 100) {
                // 取得当前要看的文本的index
                textIndex = textIndex == 0 ? arrayTexts.length - 1 : textIndex - 1;
                // 设置文本切换的动画
                mTextSwitcher.setInAnimation(AnimationUtils.loadAnimation(getActivity(), android.R.anim.slide_in_left));
                mTextSwitcher.setOutAnimation(AnimationUtils.loadAnimation(getActivity(), android.R.anim.slide_out_right));
                // 设置当前要看的文本
                mTextSwitcher.setText(arrayTexts[textIndex]);
                // 从右往左，看下一张
            } else if (touchDownX - touchUpX > 100) {
                // 取得当前要看的文本的index
                textIndex = textIndex == arrayTexts.length - 1 ? 0 : textIndex + 1;
                // 设置文本切换的动画
                // 由于Android没有提供slide_out_left和slide_in_right，所以仿照slide_in_left和slide_out_right编写了slide_out_left和slide_in_right
                mTextSwitcher.setInAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.slide_out_left));
                mTextSwitcher.setOutAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.slide_in_right));
                // 设置当前要看的文本
                mTextSwitcher.setText(arrayTexts[textIndex]);
            }
            return true;
        }
        return false;
    }
}