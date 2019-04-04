package demo.thread_handler_downloadimage;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.example.c21_2_handler_downloadimage.R;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

/**
 * <pre>
 * 使用子线程Thread下载图片，使用Handler发送（子线程）和接收图片（主线程）。
 * </pre>
 * @author caoying
 *
 */
public class MainActivity extends ActionBarActivity {
	private ImageView mImgIv;

	private String IMG_PATH = "https://www.baidu.com/img/bd_logo1.png";
	private static int DOWNLOAD_COMPLETE = 200;

	private DownloadImgHandler mHandler;
	private ProgressDialog mProgressDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mImgIv = (ImageView) findViewById(R.id.iv_img);

		mHandler = new DownloadImgHandler();

		mProgressDialog = new ProgressDialog(MainActivity.this);
		mProgressDialog.setTitle("正在下载");
		mProgressDialog.setMessage("正在下载图片，请稍后...");
	}

	/**
	 * <pre>
	 * 点击此按钮使用Handler下载图片
	 * </pre>
	 * 
	 * @param view
	 */
	public void doDownloadImg(View view) {
		mProgressDialog.show();
		new Thread(new DownloadImgRunnable()).start();
	}

	private class DownloadImgRunnable implements Runnable {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			// 下载图片
			try {
				// mProgressDialog.show(); //
				// ERROR：写在这里会造成程序崩溃。因为在这个子线程中不能更新UI。在特殊的线程AsyncTask中可以更新UI。

				// 建立连接
				URL url = new URL(IMG_PATH);	// cheked MalformedURLException
				HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();	// checked IOException
				httpURLConnection.setDoInput(true);
				httpURLConnection.connect();

				// 将下载后的图片资源转换为BitMap格式。
				InputStream inputStream = httpURLConnection.getInputStream();
				// mBitmap = BitmapFactory.decodeStream(inputStream);

				// 使用Handler在子线程中发送数据。
				Message message = Message.obtain();
				message.what = DOWNLOAD_COMPLETE;
				message.obj = BitmapFactory.decodeStream(inputStream);
				mHandler.sendMessage(message);

			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	private class DownloadImgHandler extends Handler {
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);

			// 使用Handler在主线程中接收数据。
			// 下载已经完成了
			if (DOWNLOAD_COMPLETE == msg.what) {
				mImgIv.setImageBitmap((Bitmap) msg.obj);
				mProgressDialog.dismiss();
			}
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
