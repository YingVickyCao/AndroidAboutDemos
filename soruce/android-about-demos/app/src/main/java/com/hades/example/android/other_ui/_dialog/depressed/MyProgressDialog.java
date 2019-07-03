package com.hades.example.android.other_ui._dialog.depressed;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.content.res.AppCompatResources;

import com.hades.example.android.R;

public class MyProgressDialog extends ProgressDialog {
    private static final String TAG = MyProgressDialog.class.getSimpleName();
    private Context mContext;

    public MyProgressDialog(Context context) {
        super(context);
        mContext = context;

        Log.d(TAG, "Constructor ");
    }

    public MyProgressDialog(Context context, int theme) {
        super(context, theme);

        Log.d(TAG, "Constructor ");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");

        ProgressBar progressBar = findViewById(android.R.id.progress);
        progressBar.setIndeterminateDrawable(AppCompatResources.getDrawable(getContext(), R.drawable.drawable_rotate_loading_indeterminate));

        FrameLayout frameLayout = findViewById(android.R.id.custom);
        TextView message = frameLayout.findViewById(android.R.id.message);
        message.setTextColor(getContext().getResources().getColor(android.R.color.holo_red_dark));

//        ViewGroup body = frameLayout.findViewById(android.R.id.body); // can not find body
        ViewParent body = message.getParent();
        if (body instanceof ViewGroup) {
            ((ViewGroup) body).setBackgroundColor(getContext().getResources().getColor(android.R.color.holo_blue_light));
        }


        ViewParent root = message.getParent();
        if (root instanceof LinearLayout) {
            LinearLayout frameLayoutRoot = (LinearLayout) root;
            int height = frameLayoutRoot.getLayoutParams().height;
            Log.d(TAG, "onCreate: height = " + height); // height = -1

//            getContext() = ContextThemeWrapper  getContext() => mContext
            if (mContext instanceof Activity) {
                ((Activity) mContext).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        int height = frameLayoutRoot.getLayoutParams().height;
                        int paddingLeft = frameLayout.getPaddingLeft();
                        Log.d(TAG, "onCreate: height2 = " + height + ",paddingLeft=" + paddingLeft); // height = -1
                    }
                });
            } else {
                Log.d(TAG, "onCreate: Context is not Activity");
            }

        }
    }

    @Override
    public void show() {
        super.show();
        Log.d(TAG, "show: ");
    }

    @Override
    public void setView(View view) {
        super.setView(view);

        Log.d(TAG, "setView: ");
    }
}