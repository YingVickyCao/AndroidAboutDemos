package com.hades.example.android.other_ui._dialog;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.hades.example.android.R;

public class MyProgressDialog2 extends ProgressDialog {
    private static final String TAG = MyProgressDialog2.class.getSimpleName();

    View mView;
    private ProgressBar mProgress;
    private TextView mMessageView;
    private CharSequence mMessage;

    public MyProgressDialog2(Context context) {
        super(context);
    }

    public MyProgressDialog2(Context context, int theme) {
        super(context, theme);
        Log.d(TAG, "MyProgressDialog2: ");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");

        mView = LayoutInflater.from(getContext()).inflate(R.layout.my_progress_dialog, null);
        mMessageView = mView.findViewById(R.id.message);
        mProgress = mView.findViewById(R.id.progress);

        mMessageView.setText(mMessage);
        setContentView(mView);
    }

    @Override
    public void setMessage(CharSequence message) {
        if (mProgress != null) {
            Log.d(TAG, "setMessage: mMessageView,message=" + message);
            mMessageView.setText(message);
        } else {
            Log.d(TAG, "setMessage: message=" + message);
            mMessage = message;
        }
    }
}