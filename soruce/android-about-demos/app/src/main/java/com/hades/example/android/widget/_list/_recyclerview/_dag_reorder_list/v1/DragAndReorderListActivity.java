package com.hades.example.android.widget._list._recyclerview._dag_reorder_list.v1;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.hades.example.android.R;
import com.hades.example.android.lib.base.NoNeedPermissionActivity;

public class DragAndReorderListActivity extends NoNeedPermissionActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme_Dark);

        setContentView(R.layout.widget_recyclerview_4_drag_reorder_list_activity_v1);

        showFragment(new DragAndReorderListFragment());
    }
}
