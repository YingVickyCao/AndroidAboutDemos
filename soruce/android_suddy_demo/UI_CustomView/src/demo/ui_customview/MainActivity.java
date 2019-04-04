package demo.ui_customview;

import java.util.concurrent.TimeUnit;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

public class MainActivity extends ActionBarActivity {

	ProgressBar progressBar;
	MyProgressBar progressBar2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// 横向进度条
		progressBar = (ProgressBar) findViewById(R.id.progressBar1);
		// 设置最大值为100
		progressBar.setMax(100);

		// 横向进度条
		progressBar2 = (MyProgressBar) findViewById(R.id.progressBar2);
		// 设置最大值为100
		progressBar2.setMax(100);
	}

	public void downloadLsn(View view) {
		new MyAsyncTask().execute();
	}

	public void downloadLsn2(View view) {
		new MyAsyncTask2().execute();
	}

	class MyAsyncTask extends AsyncTask<Void, Integer, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			// 模拟图片下载
			for (int i = 0; i <= 100; i = i + 20) {
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// 发布进度
				publishProgress(i);
			}

			return null;
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			// TODO Auto-generated method stub
			super.onProgressUpdate(values);
			// 更新发布的进度
			progressBar.setProgress(values[0]);
		}

	}

	class MyAsyncTask2 extends AsyncTask<Void, Integer, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			int percent = 0;
			// 模拟图片下载
			for (int i = 0; i <= 100; i = i + 20) {
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// 发布进度
				publishProgress(i);
			}

			return null;
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			// TODO Auto-generated method stub
			super.onProgressUpdate(values);
			// 更新发布的进度
			progressBar2.setProgress(values[0]);
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
