package com.hades.example.android;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hades.example.android.R;

public class QAAboutFragment extends Fragment {
    private static final String TAG = QAAboutFragment.class.getSimpleName();

    ViewGroup redBtnContainer;
    View redBtn;
    TextView textViewInFrameLayout;
    TextView textViewInLinearLayout;
    View checkedGoneView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.qa_about, container, false);

        redBtnContainer = view.findViewById(R.id.redBtnContainer);
        textViewInFrameLayout = view.findViewById(R.id.textViewInFrameLayout);
        checkedGoneView = view.findViewById(R.id.checkedGoneView);

        view.findViewById(R.id.checkIfHaveAddedView).setOnClickListener(v -> checkIfHaveAddedView());
        view.findViewById(R.id.frameLayoutLayoutGravity).setOnClickListener(v -> frameLayoutLayoutGravity());
        view.findViewById(R.id.linearLayoutLayoutGravity).setOnClickListener(v -> linearLayoutLayoutGravity());
        view.findViewById(R.id.checkGoneViewSize).setOnClickListener(v -> checkGoneViewSize());
        return view;
    }

    private void checkIfHaveAddedView() {
        if (null == redBtnContainer.findViewById(R.id.red_btn_root)) {
            if (null == redBtn) {
                redBtn = getRedBtn();
            }
            redBtnContainer.addView(redBtn);
        } else {
            redBtnContainer.removeView(redBtn);
        }
    }

    private View getRedBtn() {
        return View.inflate(getActivity(), R.layout.red_btn, null);
    }

    private void frameLayoutLayoutGravity() {
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) textViewInFrameLayout.getLayoutParams();
        // android:layout_gravity="center"
        layoutParams.gravity = Gravity.CENTER;
        textViewInFrameLayout.setLayoutParams(layoutParams);

//        android:gravity="center"
        textViewInFrameLayout.setGravity(Gravity.CENTER);
    }

    private void linearLayoutLayoutGravity() {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) textViewInLinearLayout.getLayoutParams();
        layoutParams.gravity = Gravity.CENTER;
        textViewInLinearLayout.setLayoutParams(layoutParams);

//        android:gravity="center"
        textViewInLinearLayout.setGravity(Gravity.CENTER);
    }

    private void checkGoneViewSize() {
        if (checkedGoneView.getVisibility() == View.VISIBLE) {
            checkedGoneView.setVisibility(View.GONE);
            printLayoutParams("GONE");
        } else {
            checkedGoneView.setVisibility(View.VISIBLE);
            printLayoutParams("VISIBLE");
        }
    }

    private void printLayoutParams(String tag) {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) checkedGoneView.getLayoutParams();
        Log.d(TAG, tag + ",height=" + layoutParams.height + ",width=" + layoutParams.width);
    }
}
