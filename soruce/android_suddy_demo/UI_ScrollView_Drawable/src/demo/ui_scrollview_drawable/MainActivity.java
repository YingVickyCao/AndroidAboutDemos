package demo.ui_scrollview_drawable;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.c3_10_scrollview.R;

public class MainActivity extends ActionBarActivity {
	// private ScrollView mScrollView;
	private LinearLayout mLinearLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// mScrollView = (ScrollView) findViewById(R.id.sv);
		mLinearLayout = (LinearLayout) findViewById(R.id.ll);
		for (int i = 0; i < 20; i++) {
			ImageView imageView = new ImageView(MainActivity.this);
			Drawable drawable = getResources().getDrawable(R.drawable.bd_logo1);
			imageView.setImageDrawable(drawable);
			mLinearLayout.addView(imageView);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the acDtion bar if it is
		// present.
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
