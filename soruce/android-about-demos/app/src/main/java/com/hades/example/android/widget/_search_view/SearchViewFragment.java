package com.hades.example.android.widget._search_view;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.hades.example.android.R;
import com.hades.example.android.lib.base.BaseFragment;

/**
 * SearchView 监听输入，ListView为SearchView显示自动补齐列表
 */
public class SearchViewFragment extends BaseFragment {
    public static SearchViewFragment newInstance() {

        Bundle args = new Bundle();

        SearchViewFragment fragment = new SearchViewFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.widget_searchview, container, false);

        ListView listView = view.findViewById(R.id.lv);
        SearchView searchView = view.findViewById(R.id.sv);

        initListView(listView);
        initSearchView(searchView, listView);

        return view;
    }

    private void initListView(ListView listView) {
        final String[] mAutoFinishListStrings = {"A", "adf", "Background", "Foreground", "big", "ABC", "cab", "Circle"};
        listView.setAdapter(new ArrayAdapter<>(getUsedContext(), android.R.layout.simple_list_item_1, mAutoFinishListStrings));
        /**
         * 设置ListView启用过滤
         */
        listView.setTextFilterEnabled(true);
    }

    private void initSearchView(SearchView mSv, ListView mLv) {
        // 是否自动缩小为图标
        mSv.setIconifiedByDefault(false);

        // QA: >
        // 是否显示搜索按钮
        mSv.setSubmitButtonEnabled(true);
//        mSv.setSubmitButtonEnabled(false);

        // 提示文本
//        mSv.setQueryHint("查找");

        // 为该SearchView组件设置事件监听器
        mSv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            // 用户输入字符时激发该方法
            @Override
            public boolean onQueryTextChange(String newText) {
                // 如果newText不是长度为0的字符串
                if (TextUtils.isEmpty(newText)) {
                    /**
                     * 清除ListView的过滤
                     */
                    mLv.clearTextFilter();
                } else {
                    /**
                     * 使用用户输入的内容对ListView的列表项进行过滤
                     */
                    mLv.setFilterText(newText);
                }
                return true;
            }

            // 单击搜索按钮时激发该方法
            @Override
            public boolean onQueryTextSubmit(String query) {
                // 实际应用中应该在该方法内执行实际查询
                // 此处仅使用Toast显示用户输入的查询内容
                Toast.makeText(getUsedContext(), "您的选择是:" + query, Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }

    private Context getUsedContext() {
        return getActivity();
    }
}