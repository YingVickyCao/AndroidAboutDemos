package com.hades.android.example.android_about_demos.widget.textview;

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
import android.widget.TextView;

import com.hades.android.example.android_about_demos.R;
import com.hades.android.example.android_about_demos.base.BaseFragment;

public class TextViewFormatHTMLFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.widget_textview_layout, container, false);

        format1(view);
        format2(view);
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
}
