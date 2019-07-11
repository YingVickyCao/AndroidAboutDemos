package com.hades.example.android.other_ui._popup_window;

import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import androidx.annotation.Nullable;

import com.hades.example.android.R;
import com.hades.example.android.lib.base.BaseFragment;

/**
 * https://www.jianshu.com/p/799dbb86f908
 * https://www.cnblogs.com/jzyhywxz/p/7039503.html
 */
public class TestPopupWindowFragment extends BaseFragment {

    private Button showAtLocationView;
    private Button showAsDropDownView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.other_ui_popupwindow, container, false);

        showAtLocationView = view.findViewById(R.id.showAtLocation);
        showAtLocationView.setOnClickListener(this::showAtLocation);

        showAsDropDownView = view.findViewById(R.id.showAsDropDown);
        showAsDropDownView.setOnClickListener(v -> showAsDropDown());

        return view;
    }

    private void showAtLocation(View v) {
        final PopupWindow popup = getPopupWindow();
//        popup.showAtLocation(showAtLocationView, Gravity.CENTER, 20, 20);        //将PopupWindow显示在指定位置
        popup.showAtLocation(showAtLocationView, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);        //将PopupWindow显示在指定位置
    }

    private void showAsDropDown() {
        final PopupWindow popup = getPopupWindow();
        popup.showAsDropDown(showAsDropDownView, 0, 0, Gravity.CENTER);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            popup.setOverlapAnchor(true); // 对齐方式从View anchor的左下角变成了左上角了.
        }
    }

    PopupWindow getPopupWindow() {
        // popupWindow弹窗外区域设置阴影 - 透明度
        // 在创建popupWindow时：显示阴影
        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        final float old_alpha = lp.alpha;
        lp.alpha = 0.3f;
        getActivity().getWindow().setAttributes(lp);

        View root = LayoutInflater.from(getActivity()).inflate(R.layout.other_ui_popupwindow_popup, null);
        final PopupWindow popup = new PopupWindow(root, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        popup.setOutsideTouchable(true); // 点击空白区域， dismiss PopupWindow
        popup.setAnimationStyle(android.R.style.Animation_Translucent);
        root.findViewById(R.id.dismissPopupWindow).setOnClickListener(
                v1 -> {
                    popup.dismiss();

                    // 在popupWindow dismiss时,去掉阴影
                    WindowManager.LayoutParams lp2 = getActivity().getWindow().getAttributes();
                    lp2.alpha = old_alpha;
                    getActivity().getWindow().setAttributes(lp2);

                });
        return popup;
    }
}
