package com.hades.example.android.app_component._fragment.dialog;


import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.hades.example.android.R;
import com.hades.example.android.lib.base.BaseActivity;

public class TestDialogActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_dialog_fragment);

        findViewById(R.id.btn1).setOnClickListener(v -> btn1());
        findViewById(R.id.btn2).setOnClickListener(v -> btn2());
        findViewById(R.id.btn3).setOnClickListener(v -> btn3());
        findViewById(R.id.btn4).setOnClickListener(v -> btn4());
        findViewById(R.id.btn5).setOnClickListener(v -> btn5());
        findViewById(R.id.btn6).setOnClickListener(v -> btn6());
        findViewById(R.id.btn7).setOnClickListener(v -> btn7());
        findViewById(R.id.btn8).setOnClickListener(v -> btn8());
        findViewById(R.id.btn9).setOnClickListener(v -> btn9());
        findViewById(R.id.pageAlertDialog).setOnClickListener(v -> pageAlertDialog());
    }

    private void btn1() {
        showMyBaseDialogFragment(1);
    }

    private void btn2() {
        showMyBaseDialogFragment(2);
    }

    private void btn3() {
        showMyBaseDialogFragment(3);
    }

    private void btn4() {
        showMyBaseDialogFragment(4);
    }

    private void btn5() {
        showMyBaseDialogFragment(5);
    }

    private void btn6() {
        showMyBaseDialogFragment(6);
    }

    private void btn7() {
        showMyBaseDialogFragment(7);
    }

    private void btn8() {
        showMyBaseDialogFragment(8);
    }

    private void btn9() {
        showMyBaseDialogFragment(9);
    }

    void showMyBaseDialogFragment(int index) {
        firstRemoveDialogFragment();

        // Create and show the dialog.
        DialogFragment newFragment = MyBaseDialogFragment.newInstance(index);
        newFragment.show(getSupportFragmentManager(), "dialog");
    }

    private void pageAlertDialog() {
        DialogFragment newFragment = new MyAlertDialogFragment();
        newFragment.show(getSupportFragmentManager(), "dialog");
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

    public void doPositiveClick() {
        Toast.makeText(this, "doPositiveClick", Toast.LENGTH_SHORT).show();
    }

    public void doNegativeClick() {
        Toast.makeText(this, "doNegativeClick", Toast.LENGTH_SHORT).show();
    }
}