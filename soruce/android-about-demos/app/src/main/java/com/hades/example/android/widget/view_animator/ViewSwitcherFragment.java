package com.hades.example.android.widget.view_animator;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import androidx.annotation.Nullable;

import com.hades.example.android.R;
import com.hades.example.android.lib.base.BaseFragment;

import java.util.ArrayList;

/**
 * 实例：模仿Android 系统的 Home launcher 界面
 * 使用 ViewSwitcher 组合多个 GridView，每个GridView代表一个屏幕的应用程序，GridView中每一个单元格显示一个应用程序的图标和程序名。
 */
public class ViewSwitcherFragment extends BaseFragment {
    private static final String TAG = ViewSwitcherFragment.class.getSimpleName();

    public static final int NUMBER_PER_SCREEN = 8;
    public static final int NUMBER_APPS = 40;

    public static class MockedAppInfo {
        public String appName;
        public Drawable appIcon;
    }

    private ArrayList<MockedAppInfo> mMockedAppsInfoList = new ArrayList<MockedAppInfo>();
    private int mCurrentScreenNo = -1;
    private int mTotalScreenNum;

    ViewSwitcher switcher;
    LayoutInflater inflater;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.widget_viewswitcher, container, false);

        view.findViewById(R.id.prev).setOnClickListener(v -> prev());
        view.findViewById(R.id.next).setOnClickListener(v -> next());

        this.inflater = inflater;

        mockAppInfoList();
        screenCount();
        switcher = view.findViewById(R.id.jumpViewSwitcher);

        switcher.setFactory(() -> inflater.inflate(R.layout.widget_viewswitcher_4_slidelistview, null));

        showDefaultScreen();
        return view;
    }

    private void mockAppInfoList() {
        for (int i = 0; i < NUMBER_APPS; i++) {
            String label = "" + i;
            Drawable drawable = getResources().getDrawable(R.drawable.ic_launcher);

            MockedAppInfo item = new MockedAppInfo();
            item.appName = label;
            item.appIcon = drawable;
            mMockedAppsInfoList.add(item);

        }
    }

    private void screenCount() {
        mTotalScreenNum = mMockedAppsInfoList.size() % NUMBER_PER_SCREEN == 0
                ? mMockedAppsInfoList.size() / NUMBER_PER_SCREEN
                : mMockedAppsInfoList.size() / NUMBER_PER_SCREEN + 1;
    }

    private void showDefaultScreen() {
        next();
    }

    public void next() {
        if (mCurrentScreenNo < mTotalScreenNum - 1) {
            mCurrentScreenNo++;
            /**
             * 为viewSwitcher的组件显示过程动画
             */
//            switcher.setInAnimation(getActivity(), R.anim.slide_in_right);
            /**
             * 为viewSwitcher的组件隐藏过程动画
             */
//            switcher.setOutAnimation(getActivity(), R.anim.slide_out_left);
            //控制下一屏将要显示的GridView对应的Adapter
            ((GridView) switcher.getNextView()).setAdapter(adapter);
            /**
             * 显示下一屏
             * 学习手势检测后，也可通过手势检测实现显示下一屏
             */
            switcher.showNext();
        }
    }

    public void prev() {
        if (mCurrentScreenNo > 0) {
            mCurrentScreenNo--;
//            switcher.setInAnimation(getActivity(), R.anim.slide_in_right);
//            switcher.setOutAnimation(getActivity(), R.anim.slide_out_left);

            ((GridView) switcher.getNextView()).setAdapter(adapter);

            /**
             * 显示上一屏
             */
            switcher.showPrevious();
        }
    }

    private BaseAdapter adapter = new BaseAdapter() {
        @Override
        public int getCount() {
            //如果已经到了最后一屏，且应用程序的数量不能整除NUMBER_PER_SCREEN
            if (mCurrentScreenNo == mTotalScreenNum - 1 && mMockedAppsInfoList.size() % NUMBER_PER_SCREEN != 0) {
                //最后一屏且应用程序的数量对NUMBER_PER_SCREEN求余
                return mMockedAppsInfoList.size() % NUMBER_PER_SCREEN;
            }
            //否则每屏显示的程序数量为NUMBER_PER_SCREEN
            return NUMBER_PER_SCREEN;
        }

        @Override
        public MockedAppInfo getItem(int position) {
            return mMockedAppsInfoList.get(getAppInfoIndexBaseOnScreenNo(position));
        }

        private int getAppInfoIndexBaseOnScreenNo(int position) {
            return mCurrentScreenNo * NUMBER_PER_SCREEN + position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;
            if (convertView == null) {
                view = inflater.inflate(R.layout.widget_viewswitcher_4_slidelistview_itemview, null);
            }
            ImageView imageView = view.findViewById(R.id.imageview);
            imageView.setImageDrawable(getItem(position).appIcon);
            TextView textView = view.findViewById(R.id.textview);
            textView.setText(getItem(position).appName);
            return view;
        }
    };
}
