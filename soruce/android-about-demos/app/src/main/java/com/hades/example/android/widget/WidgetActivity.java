package com.hades.example.android.widget;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;

import com.hades.example.android.R;
import com.hades.example.android.lib.base.NoNeedPermissionActivity;
import com.hades.example.android.lib.mock.DummyItem;
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
import com.hades.example.android.widget._radiobutton.TestRadioButtonFragment;
import com.hades.example.android.widget._spinner.TestSpinnerFragment;
import com.hades.example.android.widget._tablayout.TestTabLayoutFragment;
import com.hades.example.android.widget._textview.TestHighlightDigitalClockFragment;
import com.hades.example.android.widget._textview.TestTextViewClickFragment;
import com.hades.example.android.widget._textview.TestTextViewFragment;
import com.hades.example.android.widget.custom_view.TestCustomViewFragment;
import com.hades.example.android.widget.custom_view.cascadelayout.CascadeLayoutActivity;
import com.hades.example.android.widget.drag_drop.DragDropFragment;
import com.hades.example.android.widget.imageview.KenBurnsImageFragment;
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

        hideActionBar();

        initViews();

        findViewById(R.id.page_CustomVew).setOnClickListener(v -> pageCustomVew());
        findViewById(R.id.page_CustomVew4CascadeLayout).setOnClickListener(v -> pageCustomView4CascadeLayout());
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
        findViewById(R.id.testCalendarView).setOnClickListener(v -> testCalendarView());
        findViewById(R.id.testDatePickerAndTimePicker).setOnClickListener(v -> testDatePickerAndTimePicker());
        findViewById(R.id.testNumberPicker).setOnClickListener(v -> testNumberPicker());
        findViewById(R.id.testSearchView).setOnClickListener(v -> testSearchView());
        findViewById(R.id.pageSpinner).setOnClickListener(v -> pageSpinner());
        findViewById(R.id.pageTabLayout).setOnClickListener(v -> pageTabLayout());
        findViewById(R.id.pageRadioButton).setOnClickListener(v -> pageRadioButton());
    }

    @Override
    protected void showCurrentTest() {
        pageCustomVew();
    }

    private void pageCustomVew() {
        showFragment(new TestCustomViewFragment());
    }

    private void pageCustomView4CascadeLayout() {
        showActivity(CascadeLayoutActivity.class);
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
        showFragment(new KenBurnsImageFragment());
    }

    private void pageImageViewScaleType() {
        showFragment(new TestImageViewScaleTypeFragment());
    }

    private void pageRatingBar() {
        showFragment(new TestRatingBarFragment());
    }

    private void pageSeekBar() {
        showFragment(new TestSeekBarFragment());
    }

    private void pageProgressbar() {
        showFragment(new TestProgressBarFragment());
    }

    private void testViewSwitcher() {
        showFragment(new ViewSwitcherFragment());
    }

    private void testImageSwitcher() {
        showFragment(new ImageSwitcherFragment());
    }

    private void testTextSwitcher() {
        showFragment(new TextSwitcherFragment());
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
        showFragment(new DragDropFragment());
    }

    private void testViewFlipper() {
        showFragment(new ViewFlipperFragment());
    }

    private void testCalendarView() {
        showFragment(new CalendarViewFragment());
    }

    private void testDatePickerAndTimePicker() {
        showFragment(new DateTimePickerFragment());
    }

    private void testNumberPicker() {
        showFragment(new NumberPickerFragment());
    }

    private void testSearchView() {
        showFragment(SearchViewFragment.newInstance());
    }

    private void pageSpinner() {
        showFragment(new TestSpinnerFragment());
    }

    private void pageTabLayout() {
        showFragment(new TestTabLayoutFragment());
    }

    private void pageRadioButton() {
        showFragment(new TestRadioButtonFragment());
    }

    @Override
    public void onListFragmentInteraction(DummyItem item) {
        Log.d(TAG, "onListFragmentInteraction: " + item.toString());

    }
}