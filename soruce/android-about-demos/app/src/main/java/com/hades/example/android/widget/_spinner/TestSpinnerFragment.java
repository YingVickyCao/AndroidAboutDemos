package com.hades.example.android.widget._spinner;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.hades.example.android.R;
import com.hades.example.android.lib.base.BaseFragment;
import com.hades.example.android.lib.mock.DummyContent;

import java.util.ArrayList;

public class TestSpinnerFragment extends BaseFragment {
    Spinner gradeSpinner;
    ArrayList<String> gradeList = DummyContent.ITEMS_STRING_3();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.widget_spinner, container, false);
        gradeSpinner = view.findViewById(R.id.spinnerWithCustomDropdown);

        test();
        return view;
    }

    /*
     <style name="Widget.Spinner">
        <item name="background">@drawable/btn_dropdown</item>
        <item name="clickable">true</item>
        <item name="spinnerMode">dialog</item>

        <item name="dropDownSelector">@drawable/list_selector_background</item>
        <item name="popupBackground">@drawable/spinner_dropdown_background</item>
        <item name="dropDownVerticalOffset">-10dip</item>
        <item name="dropDownHorizontalOffset">0dip</item>
        <item name="dropDownWidth">wrap_content</item>
        <item name="popupPromptView">@layout/simple_dropdown_hint</item>
        <item name="gravity">center</item>
    </style>

     */
    private void test() {

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, gradeList) {
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), R.layout.widget_spinner_checked_text, gradeList) {

            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View view = View.inflate(getContext(), R.layout.widget_spinner_item_layout, null);
                TextView label = view.findViewById(R.id.spinner_item_label);
                label.setText(gradeList.get(position));

                if (gradeSpinner.getSelectedItemPosition() == position) {
                    view.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light, null));
                    label.setTextColor(getResources().getColor(android.R.color.white, null));
                } else {
                    view.setBackgroundColor(getResources().getColor(android.R.color.holo_orange_light, null));
                    label.setTextColor(getResources().getColor(android.R.color.black, null));
                }
                return view;
            }
        };

        gradeSpinner.setAdapter(adapter);
        adapter.setDropDownViewResource(R.layout.widget_spinner_item_layout);
    }
}
