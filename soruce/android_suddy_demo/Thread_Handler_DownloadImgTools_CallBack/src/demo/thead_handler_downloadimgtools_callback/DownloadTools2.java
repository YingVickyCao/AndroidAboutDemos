package demo.thead_handler_downloadimgtools_callback;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

/**
 * <pre>
 * 封装下载工具类
 * </pre>
 * 
 * @author caoying
 * 
 */
public class DownloadTools2 {
	private ProgressDialog mProgressDialog;
	private MyHandler handler;

	public DownloadTools2(Context context) {
		mProgressDialog = new ProgressDialog(context);
		mProgressDialog.setTitle("正在下载");
		mProgressDialog.setMessage("正在下载中，请稍后...");
	}

	public void download(final String path, final DownloadCallback callback) {
		// TODO Auto-generated constructor stub
		handler = new MyHandler(callback);
		new Thread(new MyThreadRunnable(path)).start();
		mProgressDialog.show();
	}

	// 使用接口回调得到下载结果。
	public interface DownloadCallback {
		public void getContent(byte[] result);
	}

	// 执行耗时操作：下载图片
	private class MyThreadRunnable implements Runnable {
		String path;

		public MyThreadRunnable(String path) {
			this.path = path;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			HttpClient httpClient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost(path);
			Log.d("CY", "111");

			try {
				HttpResponse httpResponse = httpClient.execute(httpPost);

				if (200 == httpResponse.getStatusLine().getStatusCode()) {
					byte[] result = EntityUtils.toByteArray(httpResponse.getEntity());
					Message message = Message.obtain();
					message.obj = result;
					message.what = 100;
					handler.sendMessage(message);
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
		}
	}

	private class MyHandler extends Handler {
		DownloadCallback callback;

		public MyHandler(DownloadCallback callback) {
			// TODO Auto-generated constructor stub
			this.callback = callback;
		}

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);

			if (msg.what == 100) {
				callback.getContent((byte[]) msg.obj);
				mProgressDialog.dismiss();
			}
		}
	}

}
