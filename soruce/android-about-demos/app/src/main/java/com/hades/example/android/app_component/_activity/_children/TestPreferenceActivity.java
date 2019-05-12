package com.hades.example.android.app_component._activity._children;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.widget.Button;

import com.hades.example.android.R;

import java.util.List;

public class TestPreferenceActivity extends PreferenceActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (hasHeaders()) {
            Button button = new Button(this);
            button.setText("设置操作");
            setListFooter(button);
        }
    }

    // 重写该该方法，负责加载页面布局文件
    @Override
    public void onBuildHeaders(List<Header> target) {
        // 加载选项设置列表的布局文件
        loadHeadersFromResource(R.xml.preference_headers, target);
    }

    // 重写该方法，验证各PreferenceFragment是否有效
    @Override
    public boolean isValidFragment(String fragmentName) {
        return true;
    }

}

