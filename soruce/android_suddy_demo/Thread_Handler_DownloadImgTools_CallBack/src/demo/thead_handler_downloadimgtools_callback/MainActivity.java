package demo.thead_handler_downloadimgtools_callback;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.c21_4_handler_downloadimgtools_callback.R;

import demo.thead_handler_downloadimgtools_callback.DownloadTools2.DownloadCallback;

/***
 * <pre>
 * 封装下载图片工具类
 * </pre>
 * 
 * @author caoying
 * 
 */
public class MainActivity extends ActionBarActivity {
	private ImageView mImageView;
	private final String IMG_PATH = "https://www.baidu.com/img/bd_logo1.png";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mImageView = (ImageView) findViewById(R.id.iv_image);
	}

	public void doDownloadImg(View view) {
		// DownloadTools tools = new DownloadTools(MainActivity.this);
		// tools.download(IMG_PATH, new DownloadCallback() {
		//
		// @Override
		// public void getContent(byte[] result) {
		// // TODO Auto-generated method stub
		// mImageView.setImageBitmap(BitmapFactory.decodeByteArray(result, 0,
		// result.length));
		// }
		// });

		DownloadTools2 tools = new DownloadTools2(MainActivity.this);
		tools.download(IMG_PATH, new DownloadCallback() {

			@Override
			public void getContent(byte[] result) {
				// TODO Auto-generated method stub
				mImageView.setImageBitmap(BitmapFactory.decodeByteArray(result, 0, result.length));
			}
		});
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
