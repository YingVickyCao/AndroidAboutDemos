package demo.thread_asynctask_download2;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.example.c6_3_asynctask_download.R;

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

/**
 * <pre>
 * 使用异步任务AsyncTask下载网络图片，边下载边保存，下载时更新进度，并显示图片。
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
		dialog.setTitle("提示");
		dialog.setMessage("正在下载，请稍后...");
		dialog.setCancelable(false);
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
	 * 三个形参：参数Params、进度Progress、结果Result。三个形参不用时使用Void。
	 */
	class MyAsyncTask extends AsyncTask<String, Integer, byte[]> {
		// 在任务执行之前执行被UI线程调用。
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			dialog.show();
		}

		// 被后台线程执行。执行后台耗时操作。
		// 形参等于AsyncTask传入的第一个形参：参数Params；
		// 并把执行结果返回给onPostExecute。
		@Override
		protected byte[] doInBackground(String... params) {
			// TODO Auto-generated method stub
			// TODO 过时的API
			HttpClient httpClient = new DefaultHttpClient();
			HttpGet httpGet = new HttpGet(params[0]);
			HttpResponse httpResponse;
			byte[] result = null;
			InputStream inputStream = null;
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

			try {
				httpResponse = httpClient.execute(httpGet);
				// 文件总长度
				long length = httpResponse.getEntity().getContentLength();
				// 统计读的文件长度
				int total_len = 0;
				// 每次读的文件长度
				byte[] data = new byte[1024];
				int len = 0;

				if (200 == httpResponse.getStatusLine().getStatusCode()) {
					// result =
					// EntityUtils.toByteArray(httpResponse.getEntity());
					inputStream = httpResponse.getEntity().getContent();
					while ((len = inputStream.read(data)) != -1) {
						total_len += len;
						// 计算进度
						int progress_value = (int) ((total_len / (float) length) * 100);
						// 发布进度
						publishProgress(progress_value);
						outputStream.write(data, 0, len);

					}
				}
				result = outputStream.toByteArray();
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				httpClient.getConnectionManager().shutdown();
			}

			return null;
		}

		// 被UI线程调用，更新进度提示
		@Override
		protected void onProgressUpdate(Integer... values) {
			// TODO Auto-generated method stub
			super.onProgressUpdate(values);
			// 显示发布的进度
			dialog.setProgress(values[0]);
		}

		// 当后台计算完成后，被UI线程调用。
		@Override
		protected void onPostExecute(byte[] result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);

			if (null != result) {
				Log.i(TAG, FILE_NAME + "result=" + result.toString());
				// 把数据解码成一张图片
				Bitmap bitmap = BitmapFactory.decodeByteArray(result, 0, result.length);
				// 显示图片
				imageView.setImageBitmap(bitmap);
			} else {
				Toast.makeText(MainActivity.this, "图片获取失败", Toast.LENGTH_SHORT).show();
				// TODO：由于网络下载图片失败，现在使用其他图片模拟情况。
				imageView.setImageResource(R.drawable.bd_logo1);

			}
			dialog.dismiss();
		}
	}
}
