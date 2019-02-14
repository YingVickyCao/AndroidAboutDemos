package com.hades.android.example.android_about_demos.widget.common;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hades.android.example.android_about_demos.R;

public class QAAboutViewFragemnt extends Fragment {
    ViewGroup redBtnContainer;
    View redBtn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.wiget_qa_about_view, container, false);

        redBtnContainer = view.findViewById(R.id.redBtnContainer);

        view.findViewById(R.id.checkIfHaveAddedView).setOnClickListener(v -> checkIfHaveAddedView());
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
}