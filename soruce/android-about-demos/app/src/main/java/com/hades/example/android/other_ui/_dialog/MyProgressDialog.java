package com.hades.example.android.other_ui._dialog;

import android.app.ProgressDialog;
import android.content.Context;

/*
    https://www.cnblogs.com/guop/p/5139937.html

    ProgressDialog is Depressed.
 */
public class MyProgressDialog extends ProgressDialog {
    public MyProgressDialog(Context context) {
        super(context);
    }

    public MyProgressDialog(Context context, int theme) {
        super(context, theme);
    }
}