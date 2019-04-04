package demo.ui_dialog_alertdialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	// 普通的AlertDialog
	public void showAlertDialog(View view) {
		AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
		builder.setTitle("弹出了一个对话框");
		builder.setMessage("确定删除吗");
		builder.setCancelable(false);
		builder.setNegativeButton("否", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, "点击了否按钮", Toast.LENGTH_SHORT).show();
			}
		});

		builder.setPositiveButton("是", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, "点击了是按钮", Toast.LENGTH_SHORT).show();
			}
		});

		builder.setNeutralButton("忽略", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, "点击了忽略按钮", Toast.LENGTH_SHORT).show();
			}
		});

		builder.show();
	}

	// 含有选项的AlertDialog
	public void showAlertDialog_traditionalSingleChoice(View view) {
		AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
		builder.setTitle("选择颜色");
		// 数据源是string-array
		builder.setItems(R.array.colors_array, new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				// 为了获取选中的值，使用ListAdapter关联string-array。
				ListAdapter adapter = ArrayAdapter.createFromResource(MainActivity.this, R.array.colors_array, android.R.layout.simple_list_item_single_choice);
				Toast.makeText(MainActivity.this, Integer.toString(which) + "," + adapter.getItem(which), Toast.LENGTH_SHORT).show();
			}
		});
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, Integer.toString(which), Toast.LENGTH_SHORT).show();
			}
		});
		builder.show();

	}

	// 含有单选框的AlertDialog
	public void showAlertDialog_persistentSingleChoice(View view) {
		AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
		builder.setTitle("选择爱好");
		// 使用数组作为数据源
		final String[] habby = new String[] { "购物", "爬山", "看电影" };
		builder.setSingleChoiceItems(habby, -1, new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, Integer.toString(which) + "," + habby[which], Toast.LENGTH_SHORT).show();
			}
		});

		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, Integer.toString(which), Toast.LENGTH_SHORT).show();
			}
		});
		builder.show();
	}

	// 含有复选框的AlertDialog
	public void showAlertDialog_persistentMultipleChoice(View view) {
		AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
		builder.setTitle("选择爱好");
		// 使用数组作为数据源
		final String[] habby = new String[] { "购物", "爬山", "看电影" };
		builder.setMultiChoiceItems(habby, new boolean[] { false, false, false }, new DialogInterface.OnMultiChoiceClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which, boolean isChecked) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, Integer.toString(which) + "," + habby[which], Toast.LENGTH_SHORT).show();
			}
		});
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, Integer.toString(which), Toast.LENGTH_SHORT).show();
			}
		});
		builder.show();
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
