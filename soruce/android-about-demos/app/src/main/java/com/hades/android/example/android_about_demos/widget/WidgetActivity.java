package com.hades.android.example.android_about_demos.widget;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.hades.android.example.android_about_demos.R;
import com.hades.android.example.android_about_demos.base.BaseActivity;
import com.hades.android.example.android_about_demos.other_ui.PopupWindowFragment;
import com.hades.android.example.android_about_demos.other_ui.ToastFragment;
import com.hades.android.example.android_about_demos.other_ui.dialog.AlertDialogFragment;
import com.hades.android.example.android_about_demos.other_ui.dialog.DateTimePickerDialogFragment;
import com.hades.android.example.android_about_demos.other_ui.dialog.DialogStyleActivity;
import com.hades.android.example.android_about_demos.other_ui.dialog.ProgressDialogFragment;
import com.hades.android.example.android_about_demos.other_ui.dialog.TimePickerDialogFragment;
import com.hades.android.example.android_about_demos.other_ui.notifiaction.NotificationFragment;
import com.hades.android.example.android_about_demos.widget.button.UseButton2AvoidCheckEditInputDateActivity;
import com.hades.android.example.android_about_demos.widget.custom_view.cascadelayout.CascadeLayoutActivity;
import com.hades.android.example.android_about_demos.widget.drag_drop.DragDropFragment;
import com.hades.android.example.android_about_demos.widget.imageview.KenBurnsImageActivity;
import com.hades.android.example.android_about_demos.widget.linearlayout.LinearLayoutActivity;
import com.hades.android.example.android_about_demos.widget.list.recyclerview.dag_reorder_list.DragReorderListFragment;
import com.hades.android.example.android_about_demos.widget.list.recyclerview.dummy.DummyItem;
import com.hades.android.example.android_about_demos.widget.list.recyclerview.dummy.DummyRecyclerViewFragment;
import com.hades.android.example.android_about_demos.widget.pickers.CalendarViewFragment;
import com.hades.android.example.android_about_demos.widget.pickers.DateTimePickerFragment;
import com.hades.android.example.android_about_demos.widget.pickers.NumberPickerFragment;
import com.hades.android.example.android_about_demos.widget.view_animator.ImageSwitcherFragment;
import com.hades.android.example.android_about_demos.widget.view_animator.RatingBarFragment;
import com.hades.android.example.android_about_demos.widget.view_animator.TextSwitcherFragment;
import com.hades.android.example.android_about_demos.widget.view_animator.ViewFlipperFragment;
import com.hades.android.example.android_about_demos.widget.view_animator.ViewSwitcherFragment;

public class WidgetActivity extends BaseActivity implements DummyRecyclerViewFragment.OnListFragmentInteractionListener {
    private static final String TAG = WidgetActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_widget_layout);

        initViews();

        findViewById(R.id.pageUseButton2AvoidCheckEditInputDate).setOnClickListener(v -> pageUseButton2AvoidCheckEditInputDate());
        findViewById(R.id.pageKenBurnsImage).setOnClickListener(v -> pageKenBurnsImage());
        findViewById(R.id.page_customview).setOnClickListener(v -> customview());
        findViewById(R.id.linearlayout).setOnClickListener(v -> linearlayout());
        findViewById(R.id.jumpRatingBar).setOnClickListener(v -> testRatingBar());
        findViewById(R.id.jumpViewSwitcher).setOnClickListener(v -> testViewSwitcher());
        findViewById(R.id.jumpImageSwitcher).setOnClickListener(v -> testImageSwitcher());
        findViewById(R.id.jumpTextSwitcher).setOnClickListener(v -> testTextSwitcher());

        findViewById(R.id.recyclerView).setOnClickListener(v -> recyclerView());
        findViewById(R.id.recyclerView2).setOnClickListener(v -> recyclerView2());
        findViewById(R.id.recyclerView4DragReorderList).setOnClickListener(v -> testRecyclerView4DragReorderList());
        findViewById(R.id.testDragAndDrop).setOnClickListener(v -> testDragAndDrop());

        findViewById(R.id.testViewFlipper).setOnClickListener(v -> testViewFlipper());
        findViewById(R.id.testToast).setOnClickListener(v -> testToast());
        findViewById(R.id.testCalendarView).setOnClickListener(v -> testCalendarView());
        findViewById(R.id.testDatePickerAndTimePicker).setOnClickListener(v -> testDatePickerAndTimePicker());
        findViewById(R.id.testNumberPicker).setOnClickListener(v -> testNumberPicker());
        findViewById(R.id.testSearchView).setOnClickListener(v -> testSearchView());
        findViewById(R.id.testAlertDialog).setOnClickListener(v -> testAlertDialog());
        findViewById(R.id.testDialogStyleActivity).setOnClickListener(v -> testDialogStyleActivity());
        findViewById(R.id.testProgressDialog).setOnClickListener(v -> testProgressDialog());
        findViewById(R.id.testDatePickerDialog).setOnClickListener(v -> testDatePickerDialog());
        findViewById(R.id.testTimePickerDialog).setOnClickListener(v -> testTimePickerDialog());
        findViewById(R.id.testNotification).setOnClickListener(v -> testNotification());
        findViewById(R.id.testPopupWindow).setOnClickListener(v -> testPopupWindow());
    }

    @Override
    protected boolean isNeedCheckPermission() {
        return false;
    }

    @Override
    protected void showCurrentTest() {
        linearlayout();
    }

    private void pageUseButton2AvoidCheckEditInputDate() {
        showActivity(UseButton2AvoidCheckEditInputDateActivity.class);
    }

    private void pageKenBurnsImage() {
        startActivity(KenBurnsImageActivity.class);
    }

    private void customview() {
        showActivity(CascadeLayoutActivity.class);
    }

    private void linearlayout() {
        showActivity(LinearLayoutActivity.class);
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

    private void recyclerView() {
        showFragment(DummyRecyclerViewFragment.newInstance(1), DummyRecyclerViewFragment.class.getSimpleName());
    }

    private void recyclerView2() {
        showFragment(DummyRecyclerViewFragment.newInstance(2), DummyRecyclerViewFragment.class.getSimpleName());
    }

    private void testRecyclerView4DragReorderList() {
        showFragment(DragReorderListFragment.newInstance(), DragReorderListFragment.class.getSimpleName());
    }

    private void testDragAndDrop() {
        showFragment(DragDropFragment.newInstance(), DragDropFragment.class.getSimpleName());
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

    private void testProgressDialog() {
        showFragment(new ProgressDialogFragment());
    }

    private void testDatePickerDialog() {
        showFragment(DateTimePickerDialogFragment.newInstance());
    }

    private void testTimePickerDialog() {
        showFragment(TimePickerDialogFragment.newInstance());
    }

    private void testNotification() {
        showFragment(new NotificationFragment());
    }

    private void testPopupWindow() {
        showFragment(new PopupWindowFragment());
    }

    @Override
    public void onListFragmentInteraction(DummyItem item) {
        Log.d(TAG, "onListFragmentInteraction: " + item.toString());

    }
}