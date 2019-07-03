package com.hades.example.android.app_component._fragment.dialog;

import android.app.Dialog;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.hades.example.android.R;

public class MyAlertDialogFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog
                .Builder(getActivity())
                .setIcon(R.drawable.ic_adjust_black_18dp)
                .setTitle(getResources().getString(R.string.page_AlertDialog))
                .setPositiveButton(R.string.ok, (dialog, whichButton) -> doPositiveClick())
                .setNeutralButton(R.string.cancel, (dialog, whichButton) -> doNegativeClick())
                .create();
    }

    private void doPositiveClick() {
        ((TestDialogActivity) getActivity()).doPositiveClick();
    }

    private void doNegativeClick() {
        ((TestDialogActivity) getActivity()).doNegativeClick();
    }
}