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
		// ִ��AsyncTask
		asyncTask = new MyAsyncTask(MainActivity.this);
		asyncTask.execute(100000);
		Toast.makeText(MainActivity.this, asyncTask + "", Toast.LENGTH_SHORT).show();
	}

	/**
	 * �����βΣ�����Params������Progress�����Result�������ββ���ʱʹ��Void��
	 */
	class MyAsyncTask extends AsyncTask<Integer, Integer, Long> {
		ProgressDialog progressDialog;
		boolean isRunning = true;

		public MyAsyncTask(Context context) {
			progressDialog = new ProgressDialog(context);
			progressDialog.setTitle("��ʾ");
			progressDialog.setMessage("���ڼ��㣬���Ժ�...");
			progressDialog.setCancelable(true);
			progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			progressDialog.setOnCancelListener(new OnCancelListener() {

				@Override
				public void onCancel(DialogInterface dialog) {
					// TODO Auto-generated method stub
					// ȡ��Dialogʱ��ִֹͣ���첽����
					// �������Է��֣���ʹ����cancel������Ҳ����ȡ���첽����
					isRunning = false;
				}
			});
		}

		// ������ִ��֮ǰִ�б�UI�̵߳��á�
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();

			progressDialog.show();
		}

		// ����̨�߳�ִ�С�ִ�к�̨��ʱ������
		// �βε���AsyncTask����ĵ�һ���βΣ�����Params��
		// ����ִ�н�����ظ�onPostExecute��
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

				// �������
				progress_value = (int) ((i / (float) params[0]) * 100);
				Log.i("CY", "doInBackground" + "------" + progress_value + "");
				// ��������
				publishProgress(progress_value);
			}
			return result;
		}

		// ��UI�̵߳��ã�����UI����
		@Override
		protected void onProgressUpdate(Integer... values) {
			// TODO Auto-generated method stub
			super.onProgressUpdate(values);
			// ��ʾ���������Ľ���
			progressDialog.setProgress(values[0]);
		}

		// ����̨������ɺ󣬱�UI�̵߳��á�
		@Override
		protected void onPostExecute(Long result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);

			Log.i("CY", "onPostExecute" + "------" + result);

			if (null != result) {
				Toast.makeText(MainActivity.this, "������Ϊresult=" + result, Toast.LENGTH_SHORT).show();
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
