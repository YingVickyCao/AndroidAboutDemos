package com.hades.example.android.other_ui._dialog;


import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.hades.example.android.R;
import com.hades.example.android.lib.base.BaseActivity;
import com.hades.example.android.other_ui._dialog.depressed.DateTimePickerDialogFragment;
import com.hades.example.android.other_ui._dialog.depressed.TestAlertDialogFragment;
import com.hades.example.android.other_ui._dialog.depressed.TestProgressDialogFragment;
import com.hades.example.android.other_ui._dialog.depressed.TimePickerDialogFragment;
import com.hades.example.android.other_ui._dialog.good.activity.DialogStyleActivity;
import com.hades.example.android.other_ui._dialog.good.fragment.ShowAsDialogOrEmbeddedDialogFragment;
import com.hades.example.android.other_ui._dialog.good.fragment.MyAlertDialogFragment;
import com.hades.example.android.other_ui._dialog.good.fragment.MyBaseDialogFragment;
import com.hades.example.android.other_ui._dialog.good.fragment.TestBottomSheetDialogFragment;

public class TestDialogActivity extends BaseActivity implements MyAlertDialogFragment.NoticeDialogListener {
    boolean mIsLargeLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.other_ui_dialog);

        mIsLargeLayout = getResources().getBoolean(R.bool.is_large_layout);

        initViews();

        findViewById(R.id.btn1).setOnClickListener(v -> showMyBaseDialogFragment(1));
        findViewById(R.id.btn2).setOnClickListener(v -> showMyBaseDialogFragment(2));
        findViewById(R.id.btn2).setOnClickListener(v -> showMyBaseDialogFragment(3));
        findViewById(R.id.btn4).setOnClickListener(v -> showMyBaseDialogFragment(4));
        findViewById(R.id.btn5).setOnClickListener(v -> showMyBaseDialogFragment(5));
        findViewById(R.id.btn6).setOnClickListener(v -> showMyBaseDialogFragment(6));
        findViewById(R.id.btn7).setOnClickListener(v -> showMyBaseDialogFragment(7));
        findViewById(R.id.btn8).setOnClickListener(v -> showMyBaseDialogFragment(8));
        findViewById(R.id.btn9).setOnClickListener(v -> showMyBaseDialogFragment(9));

        findViewById(R.id.page_AlertDialog_DialogFragment).setOnClickListener(v -> page_AlertDialog_DialogFragment());

//        findViewById(R.id.showAsDialogOrEmbeddedFragment).setOnClickListener(v -> showedAsDialogOrEmbeddedFragment());
        findViewById(R.id.showAsDialog).setOnClickListener(v -> showdAsDialog());
        findViewById(R.id.showAsEmbeddedFragment).setOnClickListener(v -> showAsEmbeddedFragment());

        findViewById(R.id.pageBottomSheetDialogFragment).setOnClickListener(v -> pageBottomSheetDialogFragment());

        findViewById(R.id.pageDialogStyleActivity).setOnClickListener(v -> pageDialogStyleActivity());

        findViewById(R.id.page_AlertDialog).setOnClickListener(v -> pageAlertDialog());
        findViewById(R.id.pageProgressDialog).setOnClickListener(v -> pageProgressDialog());
        findViewById(R.id.pageDatePickerDialog).setOnClickListener(v -> pageDatePickerDialog());
        findViewById(R.id.pageTimePickerDialog).setOnClickListener(v -> pageTimePickerDialog());
    }

    private void firstRemoveDialogFragment() {
        // DialogFragment.show() will take care of adding the fragment
        // in a transaction.  We also want to remove any currently showing
        // dialog, so make our own transaction and take care of that here.
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment prev = getSupportFragmentManager().findFragmentByTag("dialog");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);
    }

    void showMyBaseDialogFragment(int index) {
        firstRemoveDialogFragment();

        // Create and show the dialog.
        DialogFragment newFragment = MyBaseDialogFragment.newInstance(index);
        newFragment.show(getSupportFragmentManager(), "dialog");
    }

    private void page_AlertDialog_DialogFragment() {
        DialogFragment newFragment = new MyAlertDialogFragment();
        newFragment.show(getSupportFragmentManager(), "dialog"); // tag: findFragmentByTag()
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        Toast.makeText(this, "onDialogPositiveClick", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        Toast.makeText(this, "onDialogNegativeClick", Toast.LENGTH_SHORT).show();
    }

    private void showedAsDialogOrEmbeddedFragment() {
        firstRemoveDialogFragment();
        if (mIsLargeLayout) {
            // The device is using a large layout, so show the fragment as a dialog
            showdAsDialog();
        } else {
            // The device is smaller, so show the fragment fullscreen
            showAsEmbeddedFragment();
        }
    }

    private void showdAsDialog() {
        new ShowAsDialogOrEmbeddedDialogFragment().show(getSupportFragmentManager(), "dialog"); // be created and shown as a dialog
    }

    private void showAsEmbeddedFragment() {
        showFragment(new ShowAsDialogOrEmbeddedDialogFragment(), "dialog");                     // embedded : added as content in a view hierarchy
    }

    private void pageBottomSheetDialogFragment() {
        firstRemoveDialogFragment();
        showFragment(new TestBottomSheetDialogFragment()); // embedded : added as content in a view hierarchy
//        new TestBottomSheetDialogFragment().show(getSupportFragmentManager(), "dialog"); // be created and shown as a dialog
    }

    private void pageDialogStyleActivity() {
        showActivity(DialogStyleActivity.class);
    }

    private void pageAlertDialog() {
        showFragment(new TestAlertDialogFragment());
    }

    private void pageProgressDialog() {
        showFragment(new TestProgressDialogFragment());
    }

    private void pageDatePickerDialog() {
        showFragment(new DateTimePickerDialogFragment());
    }

    private void pageTimePickerDialog() {
        showFragment(new TimePickerDialogFragment());
    }
}