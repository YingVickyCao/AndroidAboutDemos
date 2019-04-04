package com.demo.SunnyDemo.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;

public class CustomView extends View {
	String mText;

	public CustomView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public void setText(String text) {
		mText = text;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);

		/*
		 * Paint paint = new Paint(); // 设置颜色为渐变色 paint.setColor(Color.BLUE); //
		 * 设置为空心 // paint.setStyle(Paint.Style.STROKE);
		 * 
		 * canvas.drawRect(0, 100, 0, 100, paint); paint.setColor(Color.BLACK);
		 * canvas.drawText("Hello World", 20, 20, paint);
		 */

		RectF rectF = new RectF(10, 10, 400, 400);
		Paint paint = new Paint();
		paint.setColor(Color.BLUE);
		// canvas.drawOval(rectF, paint) ;
		canvas.drawRect(0, 0, 160, 40, paint);
		if (mText != null && !mText.equals("")) {
			paint.setColor(Color.RED);
			canvas.drawText(mText, 10, 20, paint);
		}

	}

}
