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
public class CMBActivity extends TabActivity 
{

	private TabHost mTabHost;
	private int []mTabImage=new int[]{R.drawable.selector_cmb_tabitem_image_myfavorite,R.drawable.selector_cmb_tabitem_image_lifetool,R.drawable.selector_cmb_tabitem_image_financialtool,R.drawable.selector_cmb_tabitem_image_fortune};
	private int []mTabText=new int[]{R.string.cmb_tab1,R.string.cmb_tab2,R.string.cmb_tab3,R.string.cmb_tab4};
	private String[]mTabTag=new String[]{"tab1","tab2","tab3","tab4"};
	private Class<?>[] mTabClass=new Class<?>[]{Tab1.class,Tab2.class,Tab3.class,Tab4.class};
	
	@Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_cmb);
        
        initUI();
        
    }

	private void initUI()
	{
		this.setTitle(R.string.button1);
		
		this.mTabHost=this.getTabHost();
        this.mTabHost.setup();
        
        //设置显示的图像和文字
        for(int i=0;i<mTabClass.length;i++)
        {
           View view=LayoutInflater.from(this).inflate(R.layout.tab_cmb_item, null);
       
          ((ImageView)view.findViewById(R.id.tabwidget_item_image)).setImageResource(mTabImage[i]);
          ((TextView)view.findViewById(R.id.tabwidget_item_text)).setText(mTabText[i]);
          
           this.mTabHost.addTab(this.mTabHost.newTabSpec(mTabTag[i]).setIndicator(view).setContent(new Intent(this,mTabClass[i]))); 
        }
       
        //设置默认选中项
        this.mTabHost.setCurrentTab(0);
	}
}
    
