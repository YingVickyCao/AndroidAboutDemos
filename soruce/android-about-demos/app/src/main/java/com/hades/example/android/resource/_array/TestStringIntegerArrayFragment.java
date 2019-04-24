package com.hades.example.android.resource._array;

import android.app.Fragment;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Annotation;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.hades.example.android.R;

/**
 * https://www.cnblogs.com/andriod-html5/archive/2012/04/30/2539419.html
 */
public class TestStringIntegerArrayFragment extends Fragment {
    private static final String TAG = TestStringIntegerArrayFragment.class.getSimpleName();

    Button normalArrayBtn;
    TextView quantityStringsResult;
    TextView formattingStrings;
    TextView stylingWithAnnotations;
    TextView stylingWithAnnotations2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.res_string_integer_array, container, false);
        normalArrayBtn = view.findViewById(R.id.getNormalArray);
        quantityStringsResult = view.findViewById(R.id.quantityStringsResult);
        formattingStrings = view.findViewById(R.id.formattingStrings);
        stylingWithAnnotations = view.findViewById(R.id.stylingWithAnnotations);
        stylingWithAnnotations2 = view.findViewById(R.id.stylingWithAnnotations2);

        format1(view);
        format2(view);

        normalArrayBtn.setOnClickListener(v -> getNormalArray());

        view.findViewById(R.id.getStringArray).setOnClickListener(v -> getStringArray());
        view.findViewById(R.id.quantityStrings).setOnClickListener(v -> quantityStrings());

        view.findViewById(R.id.getIntArray).setOnClickListener(v -> getIntArray());

        formattingStrings.setText(getString(R.string.formatting_strings, "A", 10));
        stylingWithAnnotations.setText(getStylingWithAnnotations());

//        stylingWithAnnotations2.setText(getStylingWithAnnotations2());

        return view;
    }

    private void getNormalArray() {
        TypedArray typedArray = getResources().obtainTypedArray(R.array.normal_array);
//        Drawable drawable = typedArray.getDrawable(0);
//        normalArrayBtn.setBackground(drawable);

        int color = typedArray.getColor(1, 0);
        normalArrayBtn.setBackgroundColor(color);
    }

    private void quantityStrings() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < 15; i++) {
            str.append(i).append(":").append(getQuantityStrings(i));
            str.append("\n");
        }
        quantityStringsResult.setText(str);
    }

    private String getQuantityStrings(int count) {
        Resources res = getResources();
        return res.getQuantityString(R.plurals.numberOfSongsAvailable, count, count);
    }

    private void getStringArray() {
        /**
         * getResources().getStringArray()
         */
        String[] strings = getResources().getStringArray(R.array.string_array);
        String str = "";
        for (int i = 0; i < strings.length; i++) {
            str += strings[i] + ",";
        }
        Log.d(TAG, "getStringArray: " + str);
    }

    private void getIntArray() {
        /**
         * getResources().getIntArray()
         */
        int[] ints = getResources().getIntArray(R.array.int_array);
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < ints.length; i++) {
            str.append(ints[i]).append(",");
        }
        Log.d(TAG, "getIntArray: " + str);
    }


    private void format1(View view) {
        final TextView textView1 = view.findViewById(R.id.my_text_view_html);
        textView1.setText(Html.fromHtml(getString(R.string.styling_with_html_markup)));
        textView1.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private void format2(View view) {
        final Spannable text2 = new SpannableString(getString(R.string.styling_with_spannables));
        text2.setSpan(new BackgroundColorSpan(Color.RED), 1, 4, 0);
        text2.setSpan(new ForegroundColorSpan(Color.BLUE), 5, 9, 0);
        ((TextView) view.findViewById(R.id.my_text_view_spannable)).setText(text2);
    }

    // TODO: 2019-04-24
    private SpannableString getStylingWithAnnotations() {
        SpannableString spannableString = new SpannableString("My spantastic text");
        Annotation annotation = new Annotation("font", "title_emphasis");
        spannableString.setSpan(annotation, 3, 7, 33);
        return spannableString;
    }

    // TODO: 2019-04-24
    private SpannableString getStylingWithAnnotations2() {
        SpannableString spannableString = (SpannableString) getText(R.string.title);
        Annotation annotation = new Annotation("font", "title_emphasis");
        spannableString.setSpan(annotation, 3, 7, 33);
        return spannableString;
    }
}