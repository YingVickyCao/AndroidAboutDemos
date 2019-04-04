package com.example.c22_4_screen_support;

import java.util.ArrayList;
import java.util.List;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class TitleFragment extends ListFragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, getData());
		setListAdapter(arrayAdapter);
		return super.onCreateView(inflater, container, savedInstanceState);
	}

	public List<String> getData() {
		List<String> data = new ArrayList<String>();
		for (int i = 0; i < 1000; i++) {
			data.add(i + "");
		}
		return data;
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub

		super.onPause();
	}
}
