package com.hades.example.android.widget._textview;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hades.example.android.R;
import com.hades.example.android.lib.base.BaseFragment;

public class TestTextViewFragment extends BaseFragment {

    private TextView mSwitchedTextColorTextView;
    private LinearLayout mll1;
    private ViewGroup mll2;
    private TextView mDownload1;
    private TextView mDownload2;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.widget_textview, container, false);

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

        format1(view);
        format2(view);

        mll2.setSelected(true);
        return view;
    }

    private void format1(View view) {
        final TextView textView1 = view.findViewById(R.id.my_text_view_html);
        textView1.setText(Html.fromHtml(getString(R.string.text1)));
        textView1.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private void format2(View view) {
        final Spannable text2 = new SpannableString(getString(R.string.text2));
        text2.setSpan(new BackgroundColorSpan(Color.RED), 1, 4, 0);
        text2.setSpan(new ForegroundColorSpan(Color.BLUE), 5, 9, 0);
        ((TextView) view.findViewById(R.id.my_text_view_spannable)).setText(text2);
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
}