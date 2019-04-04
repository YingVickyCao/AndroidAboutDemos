package demo.ui_customview;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class TempActicity extends Activity {

	private Button btn_go = null;
	private MyProgressBar myProgress = null;
	private Handler mHandler = null;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		findView();
		setParam();
		addListener();

		mHandler = new Handler();

	}

	class MyHandler extends Handler {
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			myProgress.setProgress(msg.what);
			super.handleMessage(msg);
		}
	}

	private void findView() {
		btn_go = (Button) findViewById(R.id.button2);
		myProgress = (MyProgressBar) findViewById(R.id.progressBar2);
	}

	private void setParam() {
		btn_go.setText("¿ªÊ¼");
	}

	private void addListener() {
		btn_go.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new Thread(new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						for (int i = 0; i <= 50; i++) {
							mHandler.sendEmptyMessage(i * 2);
							try {
								Thread.sleep(80);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				}).start();
			}
		});
	}
}