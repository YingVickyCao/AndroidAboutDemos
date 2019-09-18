package com.hades.example.android.lib.mock;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.hades.example.android.lib.R;
import com.hades.example.android.lib.base.BaseFragment;

public class DummyFragment extends BaseFragment {
    public static final String ARG_SECTION_NUMBER = "section_number";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dummy_fragment, container, false);

        TextView textView = view.findViewById(R.id.text);
        textView.setGravity(Gravity.CENTER_HORIZONTAL);
        Bundle args = getArguments();
        textView.setText(String.valueOf(args.getInt(ARG_SECTION_NUMBER)));
        textView.setTextSize(30);
        return view;
    }
}
