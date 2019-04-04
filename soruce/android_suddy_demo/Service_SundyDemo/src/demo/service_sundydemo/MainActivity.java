package demo.service_sundydemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

/**
 * <pre>
 * Lesson5:四大组件之二:Service初步
 * </pre>
 * 
 * @author Administrator
 * 
 */
public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void doClick1(View view) {
		Intent intent = new Intent(MainActivity.this, PlayMusicActivity.class);
		startActivity(intent);
	}

	public void doClick2(View view) {
		Intent intent = new Intent(MainActivity.this, BoundedServiceActivity.class);
		startActivity(intent);
	}

	public void doClick3(View view) {
		Intent intent = new Intent(MainActivity.this, ServiceMainActivity.class);
		startActivity(intent);
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
