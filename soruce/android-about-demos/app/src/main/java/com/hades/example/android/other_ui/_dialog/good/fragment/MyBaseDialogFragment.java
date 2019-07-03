package com.hades.example.android.other_ui._dialog.good.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;

import com.hades.example.android.R;

public class MyBaseDialogFragment extends DialogFragment {
    int mNum;

    /**
     * Create a new instance of MyBaseDialogFragment, providing "num"
     * as an argument.
     */
    public static MyBaseDialogFragment newInstance(int num) {
        MyBaseDialogFragment f = new MyBaseDialogFragment();
        Bundle args = new Bundle();
        args.putInt("num", num);
        f.setArguments(args);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mNum = getArguments().getInt("num");

        // Pick a style based on the num.
        int style = DialogFragment.STYLE_NORMAL, theme = 0;
        switch (mNum) {
            case 1:
                style = DialogFragment.STYLE_NO_TITLE;
                break;

            case 2:
                style = DialogFragment.STYLE_NO_TITLE; // Common
                theme = android.R.style.Theme_Holo_Light;
                break;

            case 3:
                style = DialogFragment.STYLE_NO_FRAME;
                break;

            case 4:
                style = DialogFragment.STYLE_NO_FRAME;
                theme = android.R.style.Theme_Holo_Light_Panel;
                break;

            case 5:
                style = DialogFragment.STYLE_NO_INPUT;
                break;

            case 6:
                style = DialogFragment.STYLE_NORMAL;
                theme = android.R.style.Theme_Holo;
                break;

            case 7:
                style = DialogFragment.STYLE_NORMAL; // Common
                theme = android.R.style.Theme_Holo_Light_Dialog;
                break;

            case 8:
                style = DialogFragment.STYLE_NORMAL;
                theme = android.R.style.Theme_Holo_Light;
                break;

            case 9:
                style = DialogFragment.STYLE_NORMAL;
                break;
        }
        setStyle(style, theme);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_base_dialog_fragment, container, false);
        String info = "Dialog #" + mNum + ": using style ";
        v.findViewById(R.id.btn).setOnClickListener(v1 -> Toast.makeText(getContext(), info, Toast.LENGTH_SHORT).show());
        TextView tv = v.findViewById(R.id.tv);
        tv.setText(info);
        return v;
    }
}