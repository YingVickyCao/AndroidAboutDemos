package com.hades.example.android.resource._array;

import android.app.Fragment;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
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

    Button checkTypedArray;

    TextView mTv_stylingWithHtmlMarkup;
    TextView mTv_stylingWithSpannables;
    TextView mTv_formattingStrings;
    TextView mTv_stylingWithAnnotations;

    TextView quantityStringsResult;

    Spannable spannable;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.res_string_integer_array, container, false);

        mTv_formattingStrings = view.findViewById(R.id.formattingStrings);
        mTv_stylingWithHtmlMarkup = view.findViewById(R.id.styling_with_html_markup);
        mTv_stylingWithSpannables = view.findViewById(R.id.styling_with_spannables);
        mTv_stylingWithAnnotations = view.findViewById(R.id.stylingWithAnnotations);

        quantityStringsResult = view.findViewById(R.id.quantityStringsResult);

        checkTypedArray = view.findViewById(R.id.checkTypedArray);

        checkTypedArray.setOnClickListener(v -> checkTypedArray());
        view.findViewById(R.id.getStringArray).setOnClickListener(v -> getStringArray());
        view.findViewById(R.id.quantityStrings).setOnClickListener(v -> quantityStrings());
        view.findViewById(R.id.getIntArray).setOnClickListener(v -> getIntArray());

        mTv_formattingStrings.setText(getString(R.string.formatting_strings, "A", 10));

        mTv_stylingWithHtmlMarkup.setText(Html.fromHtml(getString(R.string.styling_with_html_markup)));
        mTv_stylingWithHtmlMarkup.setMovementMethod(LinkMovementMethod.getInstance());

        spannable = new SpannableString(getString(R.string.styling_with_spannables));
        spannable.setSpan(new BackgroundColorSpan(Color.RED), 1, 4, 0);
        spannable.setSpan(new ForegroundColorSpan(Color.BLUE), 5, 9, 0);
        mTv_stylingWithSpannables.setText(spannable);

        mTv_stylingWithAnnotations.setText(getmTv_stylingWithAnnotations());

        return view;
    }

    // TODO: 2019-04-24
    private SpannableString getmTv_stylingWithAnnotations() {
        SpannableString spannableString = new SpannableString("Styling with annotations");
        Annotation annotation = new Annotation("font", "title_emphasis");
        spannableString.setSpan(annotation, 3, 7, 33);
        return spannableString;
    }

    private void checkTypedArray() {
        Resources res = getResources();
        TypedArray icons = res.obtainTypedArray(R.array.icons);
        Drawable drawable = icons.getDrawable(0);
        icons.recycle();


        TypedArray colors = res.obtainTypedArray(R.array.colors);
        int color = colors.getColor(0, 0);
        colors.recycle();

        checkTypedArray.setBackground(drawable);
        checkTypedArray.setTextColor(color);
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
        return getResources().getQuantityString(R.plurals.numberOfSongsAvailable, count, count);
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
}