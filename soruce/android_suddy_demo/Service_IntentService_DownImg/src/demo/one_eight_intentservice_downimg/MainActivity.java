package demo.one_eight_intentservice_downimg;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {
	private Button mDownloadBtn;

	private static String FILE_NAME;
	private static String TAG = "NLOG";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		FILE_NAME = this.getClass().getSimpleName() + "->";
		Log.i(TAG, FILE_NAME + "onCreate called");

		findViews();
		setValues();
		setListeners();
	}

	private void findViews() {
		mDownloadBtn = (Button) findViewById(R.id.btn_download);
	}

	private void setValues() {
	}

	private void setListeners() {
		mDownloadBtn.setOnClickListener(downloadLsn);
	}

	private View.OnClickListener downloadLsn = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			Log.i(TAG, FILE_NAME + "downloadLsn called");
			Intent intent = new Intent(MainActivity.this, DownloadService.class);
			startService(intent);
			Toast.makeText(MainActivity.this, "单击下载按钮，开始下载", Toast.LENGTH_SHORT).show();
		}
	};

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
