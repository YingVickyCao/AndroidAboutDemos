package com.hades.example.android.other_ui._popup_window;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ScrollView;

import androidx.annotation.Nullable;

import com.hades.example.android.R;
import com.hades.example.android.lib.base.BaseFragment;

/**
 * https://www.jianshu.com/p/799dbb86f908
 * https://www.cnblogs.com/jzyhywxz/p/7039503.html
 */
public class TestPopupWindowFragment extends BaseFragment {

    private Button mBtn;
    private Button showSpinner;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.other_ui_popupwindow, container, false);

        mBtn = view.findViewById(R.id.showPopupWindow);
        showSpinner = view.findViewById(R.id.showSpinner);

        mBtn.setOnClickListener(this::showPopupWindow);
        showSpinner.setOnClickListener(v -> showSpinner());

        return view;
    }

    private void showPopupWindow(View v) {
        View root = LayoutInflater.from(getActivity()).inflate(R.layout.other_ui_popupwindow_popup, null);
        final PopupWindow popup = new PopupWindow(root, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        /**
         * 点击空白区域， dismiss PopupWindow。
         */
        popup.setOutsideTouchable(true);
        popup.setAnimationStyle(android.R.style.Animation_Translucent);
        root.findViewById(R.id.dismissPopupWindow).setOnClickListener(
                v1 -> {
                    popup.dismiss();
                });

        // 以下拉方式显示
//        popup.showAsDropDown(v);
        //将PopupWindow显示在指定位置
        popup.showAtLocation(mBtn, Gravity.CENTER, 20, 20);
    }

    private void showSpinner() {
        ScrollView scrollView = new ScrollView(getActivity());

        LinearLayout linearLayout = new LinearLayout(getActivity());
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        linearLayout.setLayoutParams(layoutParams);
        linearLayout.setGravity(LinearLayout.VERTICAL);

        View view = LayoutInflater.from(getActivity()).inflate(R.layout.other_ui_popupwindow_item, null);
        linearLayout.addView(view);

//        scrollView.setLayoutDirection(scrollView);

        final PopupWindow popup = new PopupWindow(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        popup.setOutsideTouchable(false);
        popup.setAnimationStyle(android.R.style.Animation_Translucent);
        popup.showAsDropDown(showSpinner);
        popup.setContentView(scrollView);
    }
}
