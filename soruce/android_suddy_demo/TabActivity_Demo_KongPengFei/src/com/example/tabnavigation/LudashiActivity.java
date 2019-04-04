package com.example.tabnavigation;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

@SuppressWarnings("deprecation")
public class LudashiActivity extends TabActivity {

	private TabHost mTabHost;
	private int[] mTabImage = new int[] { R.drawable.selector_ludashi_tabitem_image_myphone, R.drawable.selector_ludashi_tabitem_image_bench, R.drawable.selector_ludashi_tabitem_image_optimize,
			R.drawable.selector_ludashi_tabitem_image_find };
	private int[] mTabText = new int[] { R.string.ludashi_tab1, R.string.ludashi_tab2, R.string.ludashi_tab3, R.string.ludashi_tab4 };
	private String[] mTabTag = new String[] { "tab1", "tab2", "tab3", "tab4" };
	private Class<?>[] mTabClass = new Class<?>[] { Tab1.class, Tab2.class, Tab3.class, Tab4.class };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tab_ludashi);

		initUI();

	}

	private void initUI() {
		this.setTitle(R.string.button2);

		this.mTabHost = this.getTabHost();
		this.mTabHost.setup();

		// 璁剧疆鏄剧ず鐨勫浘鍍忓拰鏂囧瓧
		for (int i = 0; i < mTabClass.length; i++) {
			View view = LayoutInflater.from(this).inflate(R.layout.tab_ludashi_item, null);

			((ImageView) view.findViewById(R.id.tabwidget_item_image)).setImageResource(mTabImage[i]);
			((TextView) view.findViewById(R.id.tabwidget_item_text)).setText(mTabText[i]);

			this.mTabHost.addTab(this.mTabHost.newTabSpec(mTabTag[i]).setIndicator(view).setContent(new Intent(this, mTabClass[i])));
		}

		// 璁剧疆榛樿閫変腑椤�
		this.mTabHost.setCurrentTab(0);
	}
}
