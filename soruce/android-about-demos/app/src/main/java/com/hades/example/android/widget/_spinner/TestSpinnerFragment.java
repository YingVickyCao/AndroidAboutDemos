package com.hades.example.android.widget._spinner;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.hades.example.android.R;
import com.hades.example.android.lib.base.BaseFragment;
import com.hades.example.android.lib.mock.DummyContent;

import java.util.ArrayList;

public class TestSpinnerFragment extends BaseFragment {
    private static final String TAG = TestSpinnerFragment.class.getSimpleName();

    Spinner gradeSpinner;
    ArrayList<String> gradeList = DummyContent.ITEMS_STRING_3();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.widget_spinner, container, false);
        gradeSpinner = view.findViewById(R.id.spinnerWithCustomDropdown3);

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

//            @Override
//            public View getDropDownView(int position, View convertView, ViewGroup parent) {
//                View view;
//                if (null == convertView) {
//                    Log.d(TAG, "getDropDownView:" + ",position=" + position + ",convertView=null");
////                    view = View.inflate(getContext(), android.R.layout.simple_spinner_dropdown_item, null);
//                    view = View.inflate(getContext(), R.layout.simple_spinner_dropdown_item, null);
//                } else {
//                    view = convertView;
//                    Log.d(TAG, "getDropDownView:" + ",position=" + position + ",convertView@" + convertView.hashCode());
//                }
//
//                TextView tv = view.findViewById(R.id.spinner_item_label);
//                tv.setText(gradeList.get(position));
//
//                if (gradeSpinner.getSelectedItemPosition() == position) {
//                    view.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light, null));
//                    tv.setTextColor(getResources().getColor(android.R.color.white, null));
//                } else {
//                    view.setBackgroundColor(getResources().getColor(android.R.color.holo_orange_light, null));
//                    tv.setTextColor(getResources().getColor(android.R.color.black, null));
//                }
//                return view;
//            }

            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View view;
                if (null == convertView) {
                    Log.d(TAG, "getDropDownView:" + ",position=" + position + ",convertView=null");
//                    view = View.inflate(getContext(), android.R.layout.simple_spinner_dropdown_item, null);
                    view = View.inflate(getContext(), R.layout.simple_spinner_dropdown_item, null);
                } else {
                    view = convertView;
                    Log.d(TAG, "getDropDownView:" + ",position=" + position + ",convertView@" + convertView.hashCode());
                }

                TextView tv = view.findViewById(android.R.id.text1);
                tv.setText(gradeList.get(position));

                if (gradeSpinner.getSelectedItemPosition() == position) {
//                    view.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light, null));
                    tv.setTextColor(getResources().getColor(android.R.color.holo_blue_light, getContext().getTheme()));
                } else {
//                    view.setBackgroundColor(getResources().getColor(android.R.color.holo_orange_light, null));
                    tv.setTextColor(getResources().getColor(android.R.color.white, getContext().getTheme()));
                }
                return view;
            }

//            @Override
//            public View getView(int position, View convertView, ViewGroup parent) {
//                /*
//                 getView: convertView=null
//                 */
//                if (convertView == null) {
//                    Log.d(TAG, "getView: convertView=" + null);
//                    LayoutInflater inflater = LayoutInflater.from(getContext());
//                    convertView = inflater.inflate(android.R.layout.simple_spinner_item, parent, false);
//                }
//                TextView tv = convertView.findViewById(android.R.id.text1);
//                tv.setText(gradeList.get(position));
//                tv.setTextColor(getResources().getColor(android.R.color.holo_red_light, null));
//
//                return convertView;
//            }
        };

        gradeSpinner.setAdapter(adapter);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);// 自定义下拉菜单样式
    }
}
