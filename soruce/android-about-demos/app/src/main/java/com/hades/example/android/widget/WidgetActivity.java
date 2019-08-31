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
import com.hades.example.android.widget._layout._viewgroup.TestViewGroupFragment;
import com.hades.example.android.widget._layout.linearlayout.TestLinearLayout4LayoutGravityAndGravityFragment;
import com.hades.example.android.widget._layout.linearlayout.TestLinearLayoutCannotChangeColor2Fragment;
import com.hades.example.android.widget._layout.linearlayout.TestLinearLayoutCannotChangeColorFragment;
import com.hades.example.android.widget._layout.linearlayout.TestLinearLayoutFragment;
import com.hades.example.android.widget._list._listview.TestListViewFragment;
import com.hades.example.android.widget._list._recyclerview._dag_reorder_list.v1.DragAndReorderListActivity;
import com.hades.example.android.widget._list._recyclerview._dag_reorder_list.v2.screen_size.TestViewLocationFragment;
import com.hades.example.android.widget._list._recyclerview.dummy.DummyRecyclerViewFragment;
import com.hades.example.android.widget._list._recyclerview.dummy.IItemClickAction;
import com.hades.example.android.widget._progressbar.TestProgressBarFragment;
import com.hades.example.android.widget._progressbar.TestRatingBarFragment;
import com.hades.example.android.widget._progressbar.TestSeekBarFragment;
import com.hades.example.android.widget._radiobutton.TestRadioButtonFragment;
import com.hades.example.android.widget._search_view.SearchViewFragment;
import com.hades.example.android.widget._spinner.TestSpinnerFragment;
import com.hades.example.android.widget._surfaceview.TestSurfaceViewPlayVideoFragment;
import com.hades.example.android.widget._switch.TestSwitchFragment;
import com.hades.example.android.widget._tablayout.TestTabLayoutFragment;
import com.hades.example.android.widget.custom_view.TestCustomViewFragment;
import com.hades.example.android.widget.custom_view.cascadelayout.CascadeLayoutActivity;
import com.hades.example.android.widget.drag_drop.DragDropFragment;
import com.hades.example.android.widget.imageview.KenBurnsImageFragment;
import com.hades.example.android.widget.imageview.TestImageViewFragment;
import com.hades.example.android.widget.imageview.TestImageViewScaleTypeFragment;
import com.hades.example.android.widget.pickers.CalendarViewFragment;
import com.hades.example.android.widget.pickers.DateTimePickerFragment;
import com.hades.example.android.widget.pickers.NumberPickerFragment;
import com.hades.example.android.widget.textview.TestHighlightDigitalClockFragment;
import com.hades.example.android.widget.textview.TestTextViewFragment;
import com.hades.example.android.widget._videoview.VideoViewRotateScreenTipActivity;
import com.hades.example.android.widget.view_animator.ImageSwitcherFragment;
import com.hades.example.android.widget.view_animator.TextSwitcherFragment;
import com.hades.example.android.widget.view_animator.ViewFlipperFragment;
import com.hades.example.android.widget.view_animator.ViewSwitcherFragment;
import com.hades.example.android.widget.webview.TestWebViewFragment;

public class WidgetActivity extends NoNeedPermissionActivity implements IItemClickAction {
    private static final String TAG = WidgetActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme_Light);
        setContentView(R.layout.activity_widget_layout);

//        hideActionBar();

        initViews();

        findViewById(R.id.pageCustomVew).setOnClickListener(v -> pageCustomVew());
        findViewById(R.id.pageListView).setOnClickListener(v -> pageListView());
        findViewById(R.id.pageViewLocation).setOnClickListener(v -> pageViewLocation());
        findViewById(R.id.page_CustomVew4CascadeLayout).setOnClickListener(v -> pageCustomView4CascadeLayout());
        findViewById(R.id.pageConstraintLayout).setOnClickListener(v -> pageConstraintLayout());
        findViewById(R.id.pageLinearLayout).setOnClickListener(v -> pageLinearLayout());
        findViewById(R.id.pageLinearLayoutCanNotChangeColor).setOnClickListener(v -> pageLinearLayoutCanNotChangeColor());
        findViewById(R.id.pageLinearLayoutCanNotChangeColor2).setOnClickListener(v -> pageLinearLayoutCanNotChangeColor2());
        findViewById(R.id.pageLinearLayout4LayoutGravityAndGravity).setOnClickListener(v -> pageLinearLayout4LayoutGravityAndGravity());
        findViewById(R.id.pageViewGroup).setOnClickListener(v -> pageViewGroup());
        findViewById(R.id.pageFrameLayout).setOnClickListener(v -> pageFrameLayout());
        findViewById(R.id.pageWebView).setOnClickListener(v -> pageWebView());
        findViewById(R.id.pageVideoView).setOnClickListener(v -> pageVideoView());
        findViewById(R.id.pageSurfaceViewPlayVideo).setOnClickListener(v -> pageSurfaceViewPlayVideo());
        findViewById(R.id.pageHighlightDigitalClock).setOnClickListener(v -> pageHighlightDigitalClock());
        findViewById(R.id.pageTextView).setOnClickListener(v -> pageTextView());
        findViewById(R.id.pageButton).setOnClickListener(v -> pageButton());
        findViewById(R.id.pageImageView).setOnClickListener(v -> pageImageView());
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
        findViewById(R.id.pageSwitch).setOnClickListener(v -> pageSwitch());
    }

    @Override
    protected void showCurrentTest() {
        pageSwitch();
    }

    private void pageViewLocation() {
        showFragment(new TestViewLocationFragment());
    }

    private void pageListView() {
        showFragment(new TestListViewFragment());
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

    private void pageLinearLayoutCanNotChangeColor() {
        showFragment(new TestLinearLayoutCannotChangeColorFragment());
    }

    private void pageLinearLayoutCanNotChangeColor2() {
        showFragment(new TestLinearLayoutCannotChangeColor2Fragment());
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

    private void pageVideoView() {
        showActivity(VideoViewRotateScreenTipActivity.class);
    }

    private void pageSurfaceViewPlayVideo(){
        showFragment(new TestSurfaceViewPlayVideoFragment());
    }

    private void pageHighlightDigitalClock() {
        showFragment(new TestHighlightDigitalClockFragment());
    }

    private void pageTextView() {
        showFragment(new TestTextViewFragment());
    }

    private void pageButton() {
        showActivity(TestButtonActivity.class);
    }

    private void pageImageView() {
        showFragment(new TestImageViewFragment());
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

    private void pageSwitch() {
        showFragment(new TestSwitchFragment());
    }

    @Override
    public void onItemClickListener(DummyItem item) {
        Log.d(TAG, "onItemClickListener: " + item.toString());

    }
}