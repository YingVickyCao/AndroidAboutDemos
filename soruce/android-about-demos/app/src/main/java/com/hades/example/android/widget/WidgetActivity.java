package com.hades.example.android.widget;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.hades.example.android.R;
import com.hades.example.android.lib.base.NoNeedPermissionActivity;
import com.hades.example.android.lib.mock.DummyItem;
import com.hades.example.android.other_ui.PopupWindowFragment;
import com.hades.example.android.other_ui.ToastFragment;
import com.hades.example.android.other_ui.dialog.AlertDialogFragment;
import com.hades.example.android.other_ui.dialog.DateTimePickerDialogFragment;
import com.hades.example.android.other_ui.dialog.DialogStyleActivity;
import com.hades.example.android.other_ui.dialog.ProgressDialogFragment;
import com.hades.example.android.other_ui.dialog.TimePickerDialogFragment;
import com.hades.example.android.widget._button.TestButtonActivity;
import com.hades.example.android.widget._layout._constraintlayout.TestConstraintLayoutFragment;
import com.hades.example.android.widget._layout._framelayout.TestFrameLayoutFragment;
import com.hades.example.android.widget._layout._linearlayout.TestLinearLayout4LayoutGravityAndGravityFragment;
import com.hades.example.android.widget._layout._linearlayout.TestLinearLayoutFragment;
import com.hades.example.android.widget._layout._viewgroup.TestViewGroupFragment;
import com.hades.example.android.widget._list._recyclerview._dag_reorder_list.DragAndReorderListActivity;
import com.hades.example.android.widget._list._recyclerview.dummy.DummyRecyclerViewFragment;
import com.hades.example.android.widget._progressbar.TestProgressBarFragment;
import com.hades.example.android.widget._progressbar.TestRatingBarFragment;
import com.hades.example.android.widget._progressbar.TestSeekBarFragment;
import com.hades.example.android.widget._spinner.TestSpinnerFragment;
import com.hades.example.android.widget._textview.TestHighlightDigitalClockFragment;
import com.hades.example.android.widget._textview.TestTextViewClickFragment;
import com.hades.example.android.widget._textview.TestTextViewFragment;
import com.hades.example.android.widget.custom_view.cascadelayout.CascadeLayoutActivityActivity;
import com.hades.example.android.widget.drag_drop.DragDropFragment;
import com.hades.example.android.widget.imageview.KenBurnsImageActivity;
import com.hades.example.android.widget.imageview.TestImageViewScaleTypeFragment;
import com.hades.example.android.widget.pickers.CalendarViewFragment;
import com.hades.example.android.widget.pickers.DateTimePickerFragment;
import com.hades.example.android.widget.pickers.NumberPickerFragment;
import com.hades.example.android.widget.videoview.VideoViewRotateScreenTipActivity;
import com.hades.example.android.widget.view_animator.ImageSwitcherFragment;
import com.hades.example.android.widget.view_animator.TextSwitcherFragment;
import com.hades.example.android.widget.view_animator.ViewFlipperFragment;
import com.hades.example.android.widget.view_animator.ViewSwitcherFragment;
import com.hades.example.android.widget.webview.TestWebViewFragment;

public class WidgetActivity extends NoNeedPermissionActivity implements DummyRecyclerViewFragment.OnListFragmentInteractionListener {
    private static final String TAG = WidgetActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_widget_layout);

        initViews();

        findViewById(R.id.pageConstraintLayout).setOnClickListener(v -> pageConstraintLayout());
        findViewById(R.id.pageLinearLayout).setOnClickListener(v -> pageLinearLayout());
        findViewById(R.id.pageLinearLayout4LayoutGravityAndGravity).setOnClickListener(v -> pageLinearLayout4LayoutGravityAndGravity());
        findViewById(R.id.pageViewGroup).setOnClickListener(v -> pageViewGroup());
        findViewById(R.id.pageFrameLayout).setOnClickListener(v -> pageFrameLayout());
        findViewById(R.id.pageWebView).setOnClickListener(v -> pageWebView());
        findViewById(R.id.pageVideoviewRotateScreenTip).setOnClickListener(v -> pageVideoviewRotateScreenTip());
        findViewById(R.id.pageHighlightDigitalClock).setOnClickListener(v -> pageHighlightDigitalClock());
        findViewById(R.id.pageTextView).setOnClickListener(v -> pageTextView());
        findViewById(R.id.pageTextView4ClickAction).setOnClickListener(v -> pageTextView4ClickAction());
        findViewById(R.id.pageButton).setOnClickListener(v -> pageButton());
        findViewById(R.id.pageKenBurnsImage).setOnClickListener(v -> pageKenBurnsImage());
        findViewById(R.id.pageImageViewScaleType).setOnClickListener(v -> pageImageViewScaleType());
        findViewById(R.id.page_customview).setOnClickListener(v -> customview());
        findViewById(R.id.pageRatingBar).setOnClickListener(v -> pageRatingBar());
        findViewById(R.id.pageSeekBar).setOnClickListener(v -> pageSeekBar());
        findViewById(R.id.pageProgressbar).setOnClickListener(v -> pageProgressbar());
        findViewById(R.id.jumpViewSwitcher).setOnClickListener(v -> testViewSwitcher());
        findViewById(R.id.jumpImageSwitcher).setOnClickListener(v -> testImageSwitcher());
        findViewById(R.id.jumpTextSwitcher).setOnClickListener(v -> testTextSwitcher());

        findViewById(R.id.pageRecyclerView).setOnClickListener(v -> pageRecyclerView());
        findViewById(R.id.recyclerView2).setOnClickListener(v -> recyclerView2());
        findViewById(R.id.pageRecyclerView4DragReorderList).setOnClickListener(v -> pageRecyclerView4DragReorderList());
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
        findViewById(R.id.testPopupWindow).setOnClickListener(v -> testPopupWindow());
        findViewById(R.id.pageSpinner).setOnClickListener(v -> pageSpinner());
    }

    private void pageConstraintLayout() {
        showFragment(new TestConstraintLayoutFragment());
    }

    private void pageLinearLayout() {
        showFragment(new TestLinearLayoutFragment());
    }

    private void pageLinearLayout4LayoutGravityAndGravity() {
        showFragment(new TestLinearLayout4LayoutGravityAndGravityFragment());
    }

    private void pageViewGroup() {
        showFragment(new TestViewGroupFragment());
    }

    private void pageFrameLayout() {
        showFragment(new TestFrameLayoutFragment());
    }

    private void pageWebView() {
        showFragment(new TestWebViewFragment());
    }

    private void pageVideoviewRotateScreenTip() {
        showActivity(VideoViewRotateScreenTipActivity.class);
    }

    private void pageHighlightDigitalClock() {
        showFragment(new TestHighlightDigitalClockFragment());
    }

    private void pageTextView() {
        showFragment(new TestTextViewFragment());
    }

    private void pageTextView4ClickAction() {
        showFragment(new TestTextViewClickFragment());
    }

    private void pageButton() {
        showActivity(TestButtonActivity.class);
    }

    private void pageKenBurnsImage() {
        showActivity(KenBurnsImageActivity.class);
    }

    private void pageImageViewScaleType() {
        showFragment(new TestImageViewScaleTypeFragment());
    }

    private void customview() {
        showActivity(CascadeLayoutActivityActivity.class);
    }

    private void pageRatingBar() {
        showFragment(TestRatingBarFragment.newInstance(), TestRatingBarFragment.class.getSimpleName());
    }

    private void pageSeekBar() {
        showFragment(new TestSeekBarFragment());
    }

    private void pageProgressbar() {
        showFragment(new TestProgressBarFragment());
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

    private void pageRecyclerView() {
        showFragment(DummyRecyclerViewFragment.newInstance(1), DummyRecyclerViewFragment.class.getSimpleName());
    }

    private void recyclerView2() {
        showFragment(DummyRecyclerViewFragment.newInstance(2), DummyRecyclerViewFragment.class.getSimpleName());
    }

    private void pageRecyclerView4DragReorderList() {
        showActivity(DragAndReorderListActivity.class);
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

    private void testPopupWindow() {
        showFragment(new PopupWindowFragment());
    }

    private void pageSpinner() {
        showFragment(new TestSpinnerFragment());
    }

    @Override
    public void onListFragmentInteraction(DummyItem item) {
        Log.d(TAG, "onListFragmentInteraction: " + item.toString());

    }
}