package com.hades.android.example.android_about_demos.other_ui;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.hades.android.example.android_about_demos.R;

/**
 * https://www.jianshu.com/p/799dbb86f908
 * https://www.cnblogs.com/jzyhywxz/p/7039503.html
 */
public class PopupWindowFragment extends Fragment {

    private Button mBtn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.other_ui_popupwindow, container, false);

        mBtn = view.findViewById(R.id.showPopupWindow);
        mBtn.setOnClickListener(this::showPopupWindow);

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
}
