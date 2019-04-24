package com.hades.example.android.widget._list._recyclerview._dag_reorder_list;

import android.os.Bundle;
import androidx.annotation.Nullable;

import com.hades.example.android.R;
import com.hades.example.android.lib.base.NoNeedPermissionActivity;

public class DragAndReorderListActivity extends NoNeedPermissionActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme_Green);

        setContentView(R.layout.activity_only_fragemnt);

        getFragmentManager().beginTransaction().replace(R.id.root, new DragAndReorderListFragment(), DragAndReorderListFragment.class.getSimpleName()).commit();
    }
}
