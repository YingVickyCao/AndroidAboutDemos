package com.hades.example.android.resource._color_state_list;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.hades.example.android.R;
import com.hades.example.android.lib.base.BaseFragment;

public class TestColorStateListFragment extends BaseFragment {
    private TextView mSwitchedTextColorTextView;
    private LinearLayout mll1;
    private ViewGroup mll2;
    private TextView mDownload1;
    private TextView mDownload2;
    private Button btn1;
    private Button btn2;

    private int color_red_1 = 0x00FF0000;
    private int color_red_2 = 0xFF0000;

    public TestColorStateListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.res_color_state_list, container, false);

        mSwitchedTextColorTextView = view.findViewById(R.id.switchedTextColor);

        mll1 = view.findViewById(R.id.ll);
        mll2 = view.findViewById(R.id.ll2);

        mDownload1 = view.findViewById(R.id.download);
        mDownload2 = view.findViewById(R.id.download2);

        view.findViewById(R.id.switchTextViewTextColor11).setOnClickListener(v -> switchTextViewTextColor1_1());
        view.findViewById(R.id.switchTextViewTextColor12).setOnClickListener(v -> switchTextViewTextColor1_2());

        view.findViewById(R.id.switchTextViewTextColor21).setOnClickListener(v -> switchTextViewTextColor2_1());
        view.findViewById(R.id.switchTextViewTextColor22).setOnClickListener(v -> switchTextViewTextColor2_2());

        view.findViewById(R.id.reset).setOnClickListener(v -> reset());
        view.findViewById(R.id.ll2).setOnClickListener(v -> download());
        view.findViewById(R.id.download2).setOnClickListener(v -> download2());
        view.findViewById(R.id.reset2).setOnClickListener(v -> reset2());

        mll2.setSelected(true);

        btn1 = view.findViewById(R.id.btn1);
        btn2 = view.findViewById(R.id.btn2);
        view.findViewById(R.id.enableTrue).setOnClickListener(v -> enableTrue(btn1));
        view.findViewById(R.id.enableFalse).setOnClickListener(v -> enableFalse(btn1));
        view.findViewById(R.id.enableTrue2).setOnClickListener(v -> enableTrue(btn2));
        view.findViewById(R.id.enableFalse2).setOnClickListener(v -> enableFalse(btn2));

        TextView tv1 = view.findViewById(R.id.tv1);
        tv1.setTextColor(getResources().getColor(R.color.textview_color_enable)); // work

        TextView tv2 = view.findViewById(R.id.tv2);
//        tv2.setTextColor(getResources().getColor(R.color.textview_color_enable_v2)); // not work

//        tv2.setTextColor(AppCompatResources.getColorStateList(getActivity(), R.color.textview_color_enable_v2)); // work

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            tv2.setTextColor(getResources().getColorStateList(R.color.textview_color_enable_v2, getActivity().getTheme())); // work
        } else {
            tv2.setTextColor(getResources().getColorStateList(R.color.textview_color_enable_v2)); // not work
        }
        return view;
    }

    private void switchTextViewTextColor1_1() {
        mSwitchedTextColorTextView.setSelected(true);
    }

    private void switchTextViewTextColor1_2() {
        mSwitchedTextColorTextView.setSelected(false);
    }

    private void switchTextViewTextColor2_1() {
        mll1.setSelected(true);
    }

    private void switchTextViewTextColor2_2() {
        mll1.setSelected(false);
    }

    private void reset() {
        mll2.setClickable(true);
        mll2.setEnabled(true);

        mDownload1.setText(R.string.download);
    }

    private void download() {
        showToast("Click download btn");
        mll2.setClickable(false);
        mll2.setEnabled(false);

        mDownload1.setText(R.string.downloaded);
    }


    private void reset2() {
        mDownload2.setClickable(true);
        mDownload2.setEnabled(true);

        mDownload2.setText(R.string.download);
    }

    private void download2() {
        showToast("Click download btn");
        mDownload2.setClickable(false);
        mDownload2.setEnabled(false);

        mDownload2.setText(R.string.downloaded);
    }

    private void enableTrue(Button btn) {
        btn.setEnabled(true);
        btn.setClickable(true);
    }

    private void enableFalse(Button btn) {
        btn.setEnabled(false);
        btn.setClickable(false);
    }
}