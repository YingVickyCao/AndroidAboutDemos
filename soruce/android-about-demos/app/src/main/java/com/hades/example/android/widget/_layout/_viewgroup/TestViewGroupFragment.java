package com.hades.example.android.widget._layout._viewgroup;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.hades.example.android.R;
import com.hades.example.android.lib.base.BaseFragment;

public class TestViewGroupFragment extends BaseFragment {
    private static final String TAG = TestViewGroupFragment.class.getSimpleName();

    ViewGroup redBtnContainer;
    View checkedGoneView;
    View redBtn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.widget_layout_viewgroup, container, false);

        redBtnContainer = view.findViewById(R.id.redBtnContainer);
        checkedGoneView = view.findViewById(R.id.checkedGoneView);

        view.findViewById(R.id.checkIfHaveAddedView).setOnClickListener(v -> checkIfHaveAddedView());
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
        return View.inflate(getActivity(), R.layout.widget_red_btn, null);
    }

    private void printLayoutParams(String tag) {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) checkedGoneView.getLayoutParams();
        Log.d(TAG, tag + ",height=" + layoutParams.height + ",width=" + layoutParams.width);
    }

    /**
     * GONE,    height=400,width=-1
     * VISIBLE, height=400,width=-1
     */
    private void checkGoneViewSize() {
        if (checkedGoneView.getVisibility() == View.VISIBLE) {
            checkedGoneView.setVisibility(View.GONE);
            printLayoutParams("GONE");
        } else {
            checkedGoneView.setVisibility(View.VISIBLE);
            printLayoutParams("VISIBLE");
        }
    }
}