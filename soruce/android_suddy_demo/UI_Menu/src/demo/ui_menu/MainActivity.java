package demo.ui_menu;

import android.app.Activity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

/**
 * <pre>
 * ����OptionsMenu����ʽΪһ���˵���ʽ
 * </pre>
 * 
 * @author caoying
 * 
 */

public class MainActivity extends Activity {
	private final String TAG = "NLOG";
	private String FILE_NAME;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		FILE_NAME = this.getClass().getSimpleName() + "->";
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	/**
	 * <pre>
	 * ��δ���item Click�¼�����onOptionsItemSelected��ʹ��getItemId()����
	 * </pre>
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		// int id = item.getItemId();
		// if (id == R.id.action_settings) {
		// return true;
		// }

		switch (item.getItemId()) {
		case R.id.action_settings:
			Toast.makeText(MainActivity.this, "�����settins��ť", Toast.LENGTH_SHORT).show();
			break;

		case R.id.action_open:
			Toast.makeText(MainActivity.this, "����˴򿪰�ť", Toast.LENGTH_SHORT).show();
			break;

		case R.id.action_close:
			Toast.makeText(MainActivity.this, "����˹رհ�ť", Toast.LENGTH_SHORT).show();
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
		// TODO Auto-generated method stub
		super.onCreateContextMenu(menu, v, menuInfo);
		// ͨ��Inflate����Menu
		getMenuInflater().inflate(R.menu.main, menu);
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.action_settings:
			Toast.makeText(MainActivity.this, "�����settins��ť", Toast.LENGTH_SHORT).show();
			return true;

		case R.id.action_open:
			Toast.makeText(MainActivity.this, "����˴򿪰�ť", Toast.LENGTH_SHORT).show();
			return true;

		case R.id.action_close:
			Toast.makeText(MainActivity.this, "����˹رհ�ť", Toast.LENGTH_SHORT).show();
			return true;

		default:
			return super.onContextItemSelected(item);
		}
	}
}
