package com.hades.example.android.app_component;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.hades.example.android.R;
import com.hades.example.android.app_component._fragment.dialog.bottomsheet.TestBottomSheetDialogFragment;
import com.hades.example.android.app_component.service.boundservice.TestLocalBoundServiceActivity;
import com.hades.example.android.app_component.service.boundservice.TestRemoteBoundServiceActivity2;
import com.hades.example.android.lib.base.NoNeedPermissionActivity;

public class TestAppComponentActivity extends NoNeedPermissionActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_component_layout);

        initViews();

        findViewById(R.id.pageBondService).setOnClickListener(v -> pageBondService());
        findViewById(R.id.pageBondService2).setOnClickListener(v -> pageBondService2());
        findViewById(R.id.pageBottomSheetDialogFragment).setOnClickListener(v -> pageBottomSheetDialogFragment());
    }

    @Override
    protected void showCurrentTest() {
        pageBottomSheetDialogFragment();
    }

    private void pageBondService() {
        showActivity(TestLocalBoundServiceActivity.class);
    }

    private void pageBondService2() {
        showActivity(TestRemoteBoundServiceActivity2.class);
    }

    private void pageBottomSheetDialogFragment() {
//        showFragment(new TestBottomSheetDialogFragment()); // show as a floating dialog

        BottomSheetDialogFragment fragment = new TestBottomSheetDialogFragment();
        fragment.show(getSupportFragmentManager(), TestBottomSheetDialogFragment.class.getSimpleName()); // show as bottom sheet
    }
}
