package com.demo.SunnyDemo.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;

import com.demo.SunnyDemo.R;

public class BitmapDrawableView extends View {
	BitmapDrawable drawable;

	public BitmapDrawableView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		// ��ȡͼƬ��Դ
		drawable = (BitmapDrawable) getResources().getDrawable(R.drawable.norm);
		drawable.setBounds(0, 0, 500, 450);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		// ��BitmapDrawable�����ڻ�����
		drawable.draw(canvas);

		// ����ķ���
		// canvas.setBitmap(drawable.getBitmap());
	}

}
