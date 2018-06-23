package com.hades.android.example.android_about_demos.widget;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ScrollView;

import com.hades.android.example.android_about_demos.R;
import com.hades.android.example.android_about_demos.other_ui.ToastFragment;
import com.hades.android.example.android_about_demos.other_ui.dialog.AlertDialogFragment;
import com.hades.android.example.android_about_demos.other_ui.dialog.DateTimePickerDialogFragment;
import com.hades.android.example.android_about_demos.other_ui.dialog.DialogStyleActivity;
import com.hades.android.example.android_about_demos.other_ui.dialog.TimePickerDialogFragment;
import com.hades.android.example.android_about_demos.widget.list.recyclerview.dag_reorder_list.DragReorderListFragment;
import com.hades.android.example.android_about_demos.widget.pickers.CalendarViewFragment;
import com.hades.android.example.android_about_demos.widget.pickers.DateTimePickerFragment;
import com.hades.android.example.android_about_demos.widget.pickers.NumberPickerFragment;
import com.hades.android.example.android_about_demos.widget.view_animator.ImageSwitcherFragment;
import com.hades.android.example.android_about_demos.widget.view_animator.RatingBarFragment;
import com.hades.android.example.android_about_demos.widget.view_animator.TextSwitcherFragment;
import com.hades.android.example.android_about_demos.widget.view_animator.ViewFlipperFragment;
import com.hades.android.example.android_about_demos.widget.view_animator.ViewSwitcherFragment;

public class TestWidgetActivity extends Activity implements View.OnClickListener {
    private ScrollView mScrollView;
    private View mFragmentRoot;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.widget_test_layout);

        mScrollView = findViewById(R.id.scrollView);
        mFragmentRoot = findViewById(R.id.fragmentRoot);
//        showBtns();

        findViewById(R.id.show).setOnClickListener(this);
        findViewById(R.id.hide).setOnClickListener(this);

        findViewById(R.id.jumpRatingBar).setOnClickListener(this);
        findViewById(R.id.jumpViewSwitcher).setOnClickListener(this);
        findViewById(R.id.jumpImageSwitcher).setOnClickListener(this);
        findViewById(R.id.jumpTextSwitcher).setOnClickListener(this);

        findViewById(R.id.recyclerView4DragReorderList).setOnClickListener(this);
        findViewById(R.id.testViewFlipper).setOnClickListener(this);
        findViewById(R.id.testToast).setOnClickListener(this);
        findViewById(R.id.testDatePickerAndTimePicker).setOnClickListener(this);
        findViewById(R.id.testNumberPicker).setOnClickListener(this);
        findViewById(R.id.testSearchView).setOnClickListener(this);
        findViewById(R.id.testAlertDialog).setOnClickListener(this);
        findViewById(R.id.testDialogStyleActivity).setOnClickListener(this);
        findViewById(R.id.testDatePickerDialog).setOnClickListener(this);
        findViewById(R.id.testTimePickerDialog).setOnClickListener(this);

        showCurrentTest();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.show:
                showBtns();
                break;

            case R.id.hide:
                hideBtns();
                break;

            case R.id.jumpRatingBar:
                testRatingBar();
                break;

            case R.id.jumpViewSwitcher:
                testViewSwitcher();
                break;

            case R.id.jumpImageSwitcher:
                testImageSwitcher();
                break;

            case R.id.jumpTextSwitcher:
                testTextSwitcher();
                break;

            case R.id.recyclerView4DragReorderList:
                testRecyclerView4DragReorderList();
                break;

            case R.id.testViewFlipper:
                testViewFlipper();
                break;

            case R.id.testToast:
                testToast();
                break;

            case R.id.testCalendarView:
                testCalendarView();
                break;

            case R.id.testDatePickerAndTimePicker:
                testDatePickerAndTimePicker();
                break;

            case R.id.testNumberPicker:
                testNumberPicker();
                break;

            case R.id.testSearchView:
                testSearchView();
                break;

            case R.id.testAlertDialog:
                testAlertDialog();
                break;

            case R.id.testDialogStyleActivity:
                testDialogStyleActivity();
                break;

            case R.id.testDatePickerDialog:
                testDatePickerDialog();
                break;

            case R.id.testTimePickerDialog:
                testTimePickerDialog();
                break;
        }
    }

    private void showBtns() {
        mScrollView.setVisibility(View.VISIBLE);
        mFragmentRoot.setVisibility(View.GONE);
    }

    private void hideBtns() {
        mScrollView.setVisibility(View.GONE);
        mFragmentRoot.setVisibility(View.VISIBLE);
    }

    private void showCurrentTest() {
        testTimePickerDialog();
    }

    private void showFragment(Fragment fragment, String tag) {
        hideBtns();
        getFragmentManager().beginTransaction().replace(R.id.fragmentRoot, fragment, tag).commit();
    }

    private void showFragment(Fragment fragment) {
        hideBtns();
        getFragmentManager().beginTransaction().replace(R.id.fragmentRoot, fragment, fragment.getClass().getSimpleName()).commit();
    }

    private void showActivity(Class<?> dest) {
        startActivity(new Intent(this, dest));
    }

    private void testRatingBar() {
        showFragment(RatingBarFragment.newInstance(), RatingBarFragment.class.getSimpleName());
    }

    private void testViewSwitcher() {
        showFragment(ViewSwitcherFragment.newInstance(), ViewSwitcherFragment.class.getSimpleName());
    }

    private void testImageSwitcher() {
        showFragment(ImageSwitcherFragment.newInstance(), ImageSwitcherFragment.class.getSimpleName());
    }

    private void testTextSwitcher() {
        showFragment(TextSwitcherFragment.newInstance(), TextSwitcherFragment.class.getSimpleName());
    }

    private void testRecyclerView4DragReorderList() {
        showFragment(DragReorderListFragment.newInstance(), DragReorderListFragment.class.getSimpleName());
    }

    private void testViewFlipper() {
        showFragment(ViewFlipperFragment.newInstance(), ViewFlipperFragment.class.getSimpleName());
    }

    private void testToast() {
        showFragment(ToastFragment.newInstance(), ToastFragment.class.getSimpleName());
    }

    private void testCalendarView() {
        showFragment(CalendarViewFragment.newInstance(), CalendarViewFragment.class.getSimpleName());
    }

    private void testDatePickerAndTimePicker() {
        showFragment(DateTimePickerFragment.newInstance(), DateTimePickerFragment.class.getSimpleName());
    }

    private void testNumberPicker() {
        showFragment(NumberPickerFragment.newInstance(), NumberPickerFragment.class.getSimpleName());
    }

    private void testSearchView() {
        showFragment(SearchViewFragment.newInstance());
    }

    private void testAlertDialog() {
        showFragment(AlertDialogFragment.newInstance());
    }

    private void testDialogStyleActivity() {
        showActivity(DialogStyleActivity.class);
    }

    private void testDatePickerDialog() {
        showFragment(DateTimePickerDialogFragment.newInstance());
    }

    private void testTimePickerDialog() {
        showFragment(TimePickerDialogFragment.newInstance());
    }
}