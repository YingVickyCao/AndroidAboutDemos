package demo.thread_asynctask_download;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.c6_1_asynctask_download.R;

/**
 * <pre>
 * ʹ���첽����AsyncTask��������ͼƬ������ʾͼƬ��
 * </pre>
 * 
 * @author caoying
 * 
 */
public class MainActivity extends ActionBarActivity {
	private ImageView imageView;
	ProgressDialog dialog;
	private final String TAG = "NLOG";
	private String FILE_NAME;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		imageView = (ImageView) findViewById(R.id.imageView1);
		FILE_NAME = this.getClass().getSimpleName() + "->";

		dialog = new ProgressDialog(MainActivity.this);
		dialog.setTitle("��ʾ");
		dialog.setMessage("�������أ����Ժ�...");
		dialog.setCancelable(false);
		dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);

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

	/**
	 * <pre>
	 * </pre>
	 * 
	 * @param view
	 */
	public void downloadImgLsn(View view) {
		new MyAsyncTask().execute("https://www.baidu.com/img/bd_logo1.png");
	}

	/**
	 * �����βΣ�����Params������Progress�����Result�������ββ���ʱʹ��Void��
	 */
	class MyAsyncTask extends AsyncTask<String, Integer, byte[]> {
		// ������ִ��֮ǰִ�б�UI�̵߳��á�
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			dialog.show();
		}

		// ����̨�߳�ִ�С�ִ�к�̨��ʱ������
		// �βε���AsyncTask����ĵ�һ���βΣ�����Params��
		// ����ִ�н�����ظ�onPostExecute��
		@Override
		protected byte[] doInBackground(String... params) {
			// TODO Auto-generated method stub
			// TODO ��ʱ��Api
			HttpClient httpClient = new DefaultHttpClient();
			HttpGet httpGet = new HttpGet(params[0]);
			HttpResponse httpResponse;
			byte[] result = null;

			try {
				httpResponse = httpClient.execute(httpGet);
				if (200 == httpResponse.getStatusLine().getStatusCode()) {
					result = EntityUtils.toByteArray(httpResponse.getEntity());
				}
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				httpClient.getConnectionManager().shutdown();
			}

			return result;
		}

		// ��UI�̵߳��ã�����UI����
		@Override
		protected void onProgressUpdate(Integer... values) {
			// TODO Auto-generated method stub
			super.onProgressUpdate(values);
			dialog.setProgress(values[0]);
		}

		// ����̨������ɺ󣬱�UI�̵߳���
		@Override
		protected void onPostExecute(byte[] result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);

			if (null != result) {
				Log.i(TAG, FILE_NAME + "result=" + result.toString());
				// �����ݽ����һ��ͼƬ
				Bitmap bitmap = BitmapFactory.decodeByteArray(result, 0, result.length);
				// ��ʾͼƬ
				imageView.setImageBitmap(bitmap);
			} else {
				Toast.makeText(MainActivity.this, "ͼƬ��ȡʧ��", Toast.LENGTH_SHORT).show();
				// TODO��������������ͼƬʧ�ܣ�����ʹ������ͼƬģ�������
				imageView.setImageResource(R.drawable.bd_logo1);

			}
			dialog.dismiss();
		}
	}
}
