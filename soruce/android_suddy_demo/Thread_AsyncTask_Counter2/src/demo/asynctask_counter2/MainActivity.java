package demo.asynctask_counter2;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Loader;
import android.os.AsyncTask;
import android.os.AsyncTask.Status;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

	MyAsyncTask asyncTask;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
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

	public void addlsn(View view) {
		// 执行AsyncTask
		asyncTask = new MyAsyncTask(MainActivity.this);
		asyncTask.execute(100000);
		Toast.makeText(MainActivity.this, asyncTask + "", Toast.LENGTH_SHORT).show();
	}

	/**
	 * 三个形参：参数Params、进度Progress、结果Result。三个形参不用时使用Void。
	 */
	class MyAsyncTask extends AsyncTask<Integer, Integer, Long> {
		ProgressDialog progressDialog;
		boolean isRunning = true;

		public MyAsyncTask(Context context) {
			progressDialog = new ProgressDialog(context);
			progressDialog.setTitle("提示");
			progressDialog.setMessage("正在计算，请稍后...");
			progressDialog.setCancelable(true);
			progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			progressDialog.setOnCancelListener(new OnCancelListener() {

				@Override
				public void onCancel(DialogInterface dialog) {
					// TODO Auto-generated method stub
					// 取消Dialog时，停止执行异步任务。
					// 经过测试发现，即使调用cancel方法，也不会取消异步任务。
					isRunning = false;
				}
			});
		}

		// 在任务执行之前执行被UI线程调用。
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();

			progressDialog.show();
		}

		// 被后台线程执行。执行后台耗时操作。
		// 形参等于AsyncTask传入的第一个形参：参数Params；
		// 并把执行结果返回给onPostExecute。
		@Override
		protected Long doInBackground(Integer... params) {
			// TODO Auto-generated method stub

			long result = 0;
			int progress_value = 0;

			for (int i = 0; i <= params[0]; i++) {

				if (!isRunning) {
					return null;
				}

				result += i;

				// 计算进度
				progress_value = (int) ((i / (float) params[0]) * 100);
				Log.i("CY", "doInBackground" + "------" + progress_value + "");
				// 发布进度
				publishProgress(progress_value);
			}
			return result;
		}

		// 被UI线程调用，更新UI进度
		@Override
		protected void onProgressUpdate(Integer... values) {
			// TODO Auto-generated method stub
			super.onProgressUpdate(values);
			// 显示发布过来的进度
			progressDialog.setProgress(values[0]);
		}

		// 当后台计算完成后，被UI线程调用。
		@Override
		protected void onPostExecute(Long result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);

			Log.i("CY", "onPostExecute" + "------" + result);

			if (null != result) {
				Toast.makeText(MainActivity.this, "计算结果为result=" + result, Toast.LENGTH_SHORT).show();
			}
			progressDialog.dismiss();
		}

		@Override
		protected void onCancelled() {
			// TODO Auto-generated method stub
			super.onCancelled();

			Log.i("CY", "onCancelled");
		}

	}

}
