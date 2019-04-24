package com.hades.example.android.resource.theme.apply_theme;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.content.res.AppCompatResources;

import com.google.android.material.tabs.TabLayout;
import com.hades.example.android.R;
import com.hades.example.android.lib.base.NoNeedPermissionActivity;

public class ThemeChoosePageAActivity extends NoNeedPermissionActivity {
    private static final String TAG = ThemeChoosePageAActivity.class.getSimpleName();

    TextView textView;
    TabLayout tabLayout;
    final static int TAB_NUM = 2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme();
        setContentView(R.layout.res_theme_choose_page);

        textView = findViewById(R.id.textView);

        findViewById(R.id.setLightTheme).setOnClickListener(v -> setLightTheme());
        findViewById(R.id.setDarkTheme).setOnClickListener(v -> setDarkTheme());
        findViewById(R.id.jumpB).setOnClickListener(v -> jumpB());

        findViewById(R.id.color1).setOnClickListener(v -> colorOne());
        findViewById(R.id.color2).setOnClickListener(v -> colorTwo());
        findViewById(R.id.toggleEnable).setOnClickListener(v -> toggleEnable());


        findViewById(R.id.color1).setOnClickListener(v -> selectTab1());
        findViewById(R.id.color2).setOnClickListener(v -> selectTab2());
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
    }

    private void colorOne() {
        ColorStateList colorStateList = AppCompatResources.getColorStateList(this, R.color.color_v1_one);
        textView.setTextColor(colorStateList);
    }

    private void colorTwo() {
        ColorStateList colorStateList = AppCompatResources.getColorStateList(this, R.color.color_v1_two);
        textView.setTextColor(colorStateList);
    }

    private void toggleEnable() {
        textView.setSelected(!textView.isSelected());
    }

    private void jumpB() {
        startActivity(new Intent(this, ThemeChoosePageBActivity.class));
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
        View view = LayoutInflater.from(this).inflate(R.layout.res_theme_switch_color_tab_item_view, null);
        TextView tabDescView = view.findViewById(R.id.tabDesc);
        tabDescView.setText(desc);
        return view;
    }
}
