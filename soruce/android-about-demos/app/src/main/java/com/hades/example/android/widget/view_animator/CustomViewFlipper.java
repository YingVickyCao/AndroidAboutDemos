package com.hades.example.android.widget.view_animator;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Toast;
import android.widget.ViewFlipper;

public class CustomViewFlipper extends ViewFlipper {
    public CustomViewFlipper(Context context) {
        super(context);
    }


    public CustomViewFlipper(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void showNext() {
        /**
         * auto 切换 View时，禁止轮询。
         */
        if (isLast()) {
            Toast.makeText(getContext(), "Already last one", Toast.LENGTH_SHORT).show();
            return;
        }
        super.showNext();
    }

    @Override
    public void showPrevious() {
        /**
         * auto 切换 View时，禁止轮询。
         */
        if (isFirst()) {
            Toast.makeText(getContext(), "Already first one", Toast.LENGTH_SHORT).show();
            return;
        }
        super.showPrevious();
    }

    private boolean isLast() {
        return getDisplayedChild() == (getChildCount() - 1);
    }

    private boolean isFirst() {
        return getDisplayedChild() == 0;
    }
}
