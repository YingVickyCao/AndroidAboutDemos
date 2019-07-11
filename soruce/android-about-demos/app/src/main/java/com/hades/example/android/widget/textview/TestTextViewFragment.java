package com.hades.example.android.widget.textview;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.hades.example.android.R;
import com.hades.example.android.lib.base.BaseFragment;

public class TestTextViewFragment extends BaseFragment {
    private static final String TAG = TestTextViewFragment.class.getSimpleName();
    private TextView tv1;

    TextView mTextView;
    TextView mTextView2;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.widget_textview, container, false);
        Log.d(TAG, "onCreateView: ");

        getViewSize(view);
        setOnClickListener_vs_clickable(view);

        return view;
    }

    /**
     * Get View size
     */
    private void getViewSize(View view) {
        /**
         * Get View size
         *
         * Activity onCreate() / Fragment onCreateView 方法中获取某个 View 组件的宽度和高度，而直接调用 getWidth()、getHeight()、getMeasuredWidth()、getMeasuredHeight() 方法只会得到 0。
         *
         * Way1: ViewTreeObserver. OnPreDrawListener     // Many times
         *
         * Way2: ViewTreeObserver. OnGlobalLayoutListener // Many times, Recommended
         *
         * Way3: View.Click
         *
         * Way4: View.post()                              // One times, Recommended
         *
         * Way5: View.OnLayoutChangeListener()            // Many times
         *
         * Way6: View Override onSizeChanged()            // Many times
         *
         * Way7: View Override onLayout()                 // Many times
         *
         */
        tv1 = view.findViewById(R.id.tv1);
        TextView tv2 = view.findViewById(R.id.tv2);

        printTv1Size("0-1"); // ERROR: 0-1,width=0,height=0
        printTv1Size("0-2", tv1.getMeasuredWidth(), tv2.getMeasuredHeight());// ERROR:0-2,width=0,height=0

        /**
         * 方法1：ViewTreeObserver. OnGlobalLayoutListener
         * 使用 ViewTreeObserver. OnPreDrawListener 监听事件
         * 在视图tree 中view visibility 变化时，会被调用多次，因此获取到视图的宽度和高度后要移除该监听事件。
         * 还有其他ViewTreeObserver Listener
         */
        // https://blog.csdn.net/linghu_java/article/details/46544811
        // Way1: ViewTreeObserver. OnPreDrawListener
        tv1.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                printTv1Size("1"); // 1,width=263,height=263
                tv1.getViewTreeObserver().removeOnPreDrawListener(this);
                return true;
            }
        });

        // Way2: ViewTreeObserver. OnGlobalLayoutListener
        tv1.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                printTv1Size("2"); // 2,width=263,height=263
                tv1.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
        ((Switch) view.findViewById(R.id.toggleHideShow)).setOnCheckedChangeListener((buttonView, isChecked) -> {
            // When tv2 change Visibility, print "2,width=263,height=263"
            if (isChecked) {
                tv2.setVisibility(View.VISIBLE);
            } else {
                tv2.setVisibility(View.GONE);
            }
        });

        // Way3: View.Click
        view.findViewById(R.id.requestViewSize).setOnClickListener(v -> printTv1Size("3")); // 3,width=263,height=263

        /**
         * Way4: View.post()
         */
        tv1.post(() -> printTv1Size("4")); // 4,width=263,height=263

        /**
         * Way5: View.OnLayoutChangeListener() API >= 11）
         * 在视图的 layout 改变时调用该事件，会被多次调用，因此需要在获取到视图的宽度和高度后执行 remove 方法移除该监听事件。
         */
        tv1.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int l, int t, int r, int b, int oldL, int oldT, int oldR, int oldB) {
                tv1.removeOnLayoutChangeListener(this);
                printTv1Size("5", tv1.getWidth(), tv1.getHeight());// 5,width=263,height=263
            }
        });

    }

    /**
     * setOnClickListener 对android:clickable=true 的影响
     */
    private void setOnClickListener_vs_clickable(View view) {
        mTextView = view.findViewById(R.id.clickTextView);
        mTextView2 = view.findViewById(R.id.clickTextView2);
        mTextView.setClickable(false);
        mTextView2.setClickable(false);
        // 添加的OnClick后，自动设置 android:clickable=true. 但要注意调用的顺序
        view.findViewById(R.id.clickTextView).setOnClickListener(v -> clickTextView("Red TextView is clicked"));
        mTextView.setClickable(false);
        view.findViewById(R.id.clickTextView2).setOnClickListener(v -> clickTextView("Blue TextView is clicked"));
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    private void printTv1Size(String tag) {
        int width = tv1.getWidth();
        int height = tv1.getHeight();
        Log.d(TAG, "printTv1Size:" + tag + ",width=" + width + ",height=" + height);
    }

    private void printTv1Size(String tag, int width, int height) {
        Log.d(TAG, "printTv1Size:" + tag + ",width=" + width + ",height=" + height);
    }

    private void clickTextView(String msg) {
        showToast(msg);
    }
}