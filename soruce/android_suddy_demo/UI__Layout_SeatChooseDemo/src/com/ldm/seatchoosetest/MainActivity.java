package com.ldm.seatchoosetest;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Toast;

import com.ldm.seatchoosetest.model.Seat;
import com.ldm.seatchoosetest.model.SeatInfo;
import com.ldm.seatchoosetest.view.SSThumView;
import com.ldm.seatchoosetest.view.SSView;

public class MainActivity extends Activity {
	private static final int ROW = 20;
	private static final int EACH_ROW_COUNT =30;
	private SSView mSSView;
	private SSThumView mSSThumView;
	private ArrayList<SeatInfo> list_seatInfos = new ArrayList<SeatInfo>();
	private ArrayList<ArrayList<Integer>> list_seat_conditions = new ArrayList<ArrayList<Integer>>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
	}
	
	
	private void init(){
		mSSView = (SSView)this.findViewById(R.id.mSSView);
		mSSThumView = (SSThumView)this.findViewById(R.id.ss_ssthumview);
//		mSSView.setXOffset(20);
		setSeatInfo();
		mSSView.init(EACH_ROW_COUNT, ROW, list_seatInfos, list_seat_conditions, mSSThumView, 5);
		mSSView.setOnSeatClickListener(new OnSeatClickListener() {
			
			@Override
			public boolean b(int column_num, int row_num, boolean paramBoolean) {
				String desc =  "您选择了第"+(row_num+1)+"排" + " 第" + (column_num+1) +"列";
				Toast.makeText(MainActivity.this,desc.toString(), Toast.LENGTH_SHORT).show();
				return false;
			}
			
			@Override
			public boolean a(int column_num, int row_num, boolean paramBoolean) {
				String desc =  "您取消了第"+(row_num+1)+"排" + " 第" + (column_num+1) +"列";
				Toast.makeText(MainActivity.this,desc.toString(), Toast.LENGTH_SHORT).show();	
				return false;
			}
			
			@Override
			public void a() {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	
	
	
	
	private void setSeatInfo(){
		for(int i =0;i<ROW;i++){//8行
			SeatInfo mSeatInfo = new SeatInfo();
			ArrayList<Seat> mSeatList = new ArrayList<Seat>();
			ArrayList<Integer> mConditionList = new ArrayList<Integer>();
			for(int j=0;j<EACH_ROW_COUNT;j++){//每排20个座位
				Seat mSeat = new Seat();
				if(j<3){
					mSeat.setN("Z");
					mConditionList.add(0);
				}else{
					mSeat.setN(String.valueOf(j-2));
					if(j>10){
						mConditionList.add(2);
					}else{
						mConditionList.add(1);
					}
					
				}
				mSeat.setDamagedFlg("");
				mSeat.setLoveInd("0");
				mSeatList.add(mSeat);
			}
			mSeatInfo.setDesc(String.valueOf(i+1));
			mSeatInfo.setRow(String.valueOf(i+1));
			mSeatInfo.setSeatList(mSeatList);
			list_seatInfos.add(mSeatInfo);
			list_seat_conditions.add(mConditionList);
		}
	
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
}
