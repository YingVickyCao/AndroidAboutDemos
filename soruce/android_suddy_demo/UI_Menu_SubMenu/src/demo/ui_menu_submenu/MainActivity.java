package demo.ui_menu_submenu;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

/**
 * <pre>
 * 创建OptionsMenu，形式为二级菜单形式
 * </pre>
 * 
 * @author caoying
 * 
 */
public class MainActivity extends ActionBarActivity {

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
		switch (item.getItemId()) {
		case R.id.file:
			Toast.makeText(MainActivity.this, "点击了文件按钮", Toast.LENGTH_SHORT).show();
			break;

		case R.id.action_settings:
			Toast.makeText(MainActivity.this, "点击了settings按钮", Toast.LENGTH_SHORT).show();
			break;

		case R.id.open:
			Toast.makeText(MainActivity.this, "点击了打开按钮", Toast.LENGTH_SHORT).show();
			break;

		case R.id.close:
			Toast.makeText(MainActivity.this, "点击了关闭按钮", Toast.LENGTH_SHORT).show();
			break;

		case R.id.edit:
			Toast.makeText(MainActivity.this, "点击了编辑按钮", Toast.LENGTH_SHORT).show();
			break;

		case R.id.copy:
			Toast.makeText(MainActivity.this, "点击了复制按钮", Toast.LENGTH_SHORT).show();
			break;

		case R.id.cut:
			Toast.makeText(MainActivity.this, "点击了剪切按钮", Toast.LENGTH_SHORT).show();
			break;

		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public boolean onMenuOpened(int featureId, Menu menu) {
		// TODO Auto-generated method stub
		return super.onMenuOpened(featureId, menu);
	}

}
