package com.hades.example.android.widget.view_animator;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.annotation.Nullable;

import com.hades.example.android.R;
import com.hades.example.android.lib.base.BaseFragment;

public class ViewFlipperFragment extends BaseFragment implements View.OnTouchListener {
    private static final String TAG = ViewFlipperFragment.class.getSimpleName();

    private ViewFlipper viewFlipper;
    private float mTouchDownX;
    private float mTouchUpX;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.widget_viewflipper, container, false);
        viewFlipper = view.findViewById(R.id.viewFlipper);
        view.findViewById(R.id.prev).setOnClickListener(this::prev);
        view.findViewById(R.id.next).setOnClickListener(this::next);
        view.findViewById(R.id.auto).setOnClickListener(this::auto);
        view.findViewById(R.id.stopAuto).setOnClickListener(this::stopAuto);

//        addViewBySource();

        Log.d(TAG, "onCreateView: getChildCount=" + viewFlipper.getChildCount());
        Log.d(TAG, "onCreateView: getDisplayedChild=" + viewFlipper.getDisplayedChild());

        /**
         * 滑动ViewFlipper，切换 View
         */
        viewFlipper.setOnTouchListener(this);
        return view;
    }

    private void addViewBySource() {
        String[] showedTextArray = {"1", "2", "3"};
        int showedTextIndex;

        for (int i = 0; i < 3; i++) {
            showedTextIndex = i;
            TextView textView = new TextView(getUsedContext());
            textView.setTextSize(100);
            textView.setLayoutParams(new TextSwitcher.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
            textView.setGravity(Gravity.CENTER);
            textView.setText(showedTextArray[showedTextIndex]);
            viewFlipper.addView(textView);
        }
    }

    private Context getUsedContext() {
        return viewFlipper.getContext();
    }

    public void prev(View source) {
        /**
         * 点击切换 View时，禁止轮询。
         */
        if (isFirst()) {
            Toast.makeText(getUsedContext(), "Already first one", Toast.LENGTH_SHORT).show();
            return;
        }
        Log.d(TAG, "prev: getChildCount=" + viewFlipper.getChildCount());
        Log.d(TAG, "prev: getDisplayedChild=" + viewFlipper.getDisplayedChild());

        viewFlipper.setInAnimation(getUsedContext(), android.R.anim.slide_in_left);
        viewFlipper.setOutAnimation(getUsedContext(), android.R.anim.slide_out_right);

        viewFlipper.showPrevious();
        viewFlipper.stopFlipping();
    }

    public void next(View source) {
        Log.d(TAG, "next: getChildCount=" + viewFlipper.getChildCount());
        Log.d(TAG, "next: getDisplayedChild=" + viewFlipper.getDisplayedChild());

        /**
         * 点击切换 View时，禁止轮询。
         */
        if (isLast()) {
            Toast.makeText(getUsedContext(), "Already last one", Toast.LENGTH_SHORT).show();
            return;
        }

        viewFlipper.setInAnimation(getUsedContext(), R.anim.slide_in_right);
        viewFlipper.setOutAnimation(getUsedContext(), R.anim.slide_out_left);

        viewFlipper.showNext();
        viewFlipper.stopFlipping();
    }

    private boolean isLast() {
        return viewFlipper.getDisplayedChild() == (viewFlipper.getChildCount() - 1);
    }

    private boolean isFirst() {
        return viewFlipper.getDisplayedChild() == 0;
    }

    public void auto(View source) {

        viewFlipper.setInAnimation(getUsedContext(), R.anim.slide_in_right);
        viewFlipper.setOutAnimation(getUsedContext(), R.anim.slide_out_left);

        viewFlipper.startFlipping();
    }

    public void stopAuto(View source) {
        viewFlipper.stopFlipping();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            mTouchDownX = event.getX();
            return true;
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            mTouchUpX = event.getX();
            if (mTouchUpX - mTouchDownX > 100) {
                prev(v);

            } else if (mTouchDownX - mTouchUpX > 100) {
                next(v);
            }
            return true;
        }
        return false;
    }
}
