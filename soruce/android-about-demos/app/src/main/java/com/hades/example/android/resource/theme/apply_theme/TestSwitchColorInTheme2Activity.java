package com.hades.example.android.resource.theme.apply_theme;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v7.content.res.AppCompatResources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.hades.example.android.R;

public class TestSwitchColorInTheme2Activity extends ThemeChoosePageAActivity {
    private static final String TAG = TestSwitchColorInTheme2Activity.class.getSimpleName();

    TabLayout tabLayout;
    final static int TAB_NUM = 2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme();

        setContentView(R.layout.res_theme_switch_color_2);

        tabLayout = findViewById(R.id.tabLayout);
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

        findViewById(R.id.setRedTheme).setOnClickListener(v -> doSetRedThemeBtnClick());
        findViewById(R.id.setGreenTheme).setOnClickListener(v -> doSetGreenThemeBtnClick());

        findViewById(R.id.selectTab1).setOnClickListener(v -> selectTab1());
        findViewById(R.id.selectTab2).setOnClickListener(v -> selectTab2());
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
                ColorStateList colorStateList = AppCompatResources.getColorStateList(this, tabDesc.isEnabled() ? R.color.color_v1_one : R.color.color_v1_two);
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
        if (isAllowReClick() && (position == tabLayout.getSelectedTabPosition())) {
            TabLayout.Tab tab = tabLayout.getTabAt(position);
            if (null != tab) {
                tab.select();
            }
        }
    }

    private boolean isAllowReClick() {
        return true;
    }

    public View getTabView(String desc) {
        View view = LayoutInflater.from(this).inflate(R.layout.res_theme_switch_color_tab_item_view, null);
        TextView tabDescView = view.findViewById(R.id.tabDesc);
        tabDescView.setText(desc);
        return view;
    }
}