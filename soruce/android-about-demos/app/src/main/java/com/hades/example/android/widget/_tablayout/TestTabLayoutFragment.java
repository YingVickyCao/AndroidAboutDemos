package com.hades.example.android.widget._tablayout;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.content.res.AppCompatResources;

import com.google.android.material.tabs.TabLayout;
import com.hades.example.android.R;
import com.hades.example.android.lib.base.ThemeBaseFragment;

public class TestTabLayoutFragment extends ThemeBaseFragment {
    private static final String TAG = TestTabLayoutFragment.class.getSimpleName();

    TabLayout tabLayout;
    final static int TAB_NUM = 2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.widget_tablayout, container, false);

        view.findViewById(R.id.selectTab1).setOnClickListener(v -> selectTab1());
        view.findViewById(R.id.selectTab2).setOnClickListener(v -> selectTab2());
        tabLayout = view.findViewById(R.id.tabLayout);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Log.d(TAG, "onTabSelected: index=" + tab.getPosition());
                showToast(String.valueOf(tab.getPosition() + 1));
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                Log.d(TAG, "onTabUnselected: index=" + tab.getPosition());
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                Log.d(TAG, "onTabReselected: index=" + tab.getPosition());
                showToast(String.valueOf(tab.getPosition() + 1));
            }
        });
        setupTabLayout();
        tabLayout.setSelected(true);
        TabLayout.Tab tab = tabLayout.getTabAt(0);
        if (null != tab) {
            tab.select();
        }
        return view;
    }

    private void selectTab1() {
        selectTab(0);
    }

    private void selectTab2() {
        selectTab(1);
    }

    private void selectTab(int index) {
        if (index >= TAB_NUM) {
            return;
        }
        TabLayout.Tab tab = tabLayout.getTabAt(index);
        if (null == tab) {
            return;
        }
        tab.select();
    }

    private void setupTabLayout() {
        for (int i = 0; i < TAB_NUM; i++) {
            TabLayout.Tab tab = tabLayout.newTab();
            View tabView = getTabView("Tab " + (i + 1));
            tab.setCustomView(tabView);
            tabLayout.addTab(tab);
            View customView = tab.getCustomView();
            if (null == customView) {
                continue;
            }
            TextView tabDesc = customView.findViewById(R.id.tabDesc);
            if (null != tabDesc) {
//                tabDesc.setEnabled(i % 2 == 0);
                ColorStateList colorStateList = AppCompatResources.getColorStateList(getActivity(), tabDesc.isEnabled() ? R.color.color_v1_1 : R.color.color_v1_2);
                tabDesc.setTextColor(colorStateList);
            }


            Log.d(TAG, "setupTabLayout: tabView@" + tabView.hashCode() + ",tabCustomView@" + customView.hashCode());// tabView@168256814,tabCustomView@168256814
            customView.setTag(i);
            customView.setOnClickListener(this::tabClick);
        }
    }

    private void tabClick(View v) {
        Integer position = (Integer) v.getTag();
        if (null == position) {
            return;
        }

        Log.d(TAG, "tabClick:position=" + position + ",getSelectedTabPosition=" + tabLayout.getSelectedTabPosition());
//        if (isAllowReClick() && (position == tabLayout.getSelectedTabPosition())) {
        TabLayout.Tab tab = tabLayout.getTabAt(position);
        if (null != tab) {
            tab.select();
        }
//        }
    }

    private boolean isAllowReClick() {
        return true;
    }

    public View getTabView(String desc) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.res_theme_switch_color_tab_item_view, null);
        TextView tabDescView = view.findViewById(R.id.tabDesc);
        tabDescView.setText(desc);
        return view;
    }
}
