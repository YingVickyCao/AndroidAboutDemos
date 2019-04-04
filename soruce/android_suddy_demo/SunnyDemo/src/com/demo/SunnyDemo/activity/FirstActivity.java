package com.demo.SunnyDemo.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Process;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.Toast;

import com.demo.SunnyDemo.R;
import com.demo.SunnyDemo.Tools;

/**
 * <pre>
 *  有关Activity的测试
 * @author caoying
 * </pre>
 */
public class FirstActivity extends ActionBarActivity {
	private String TAG = "FirstActivity";
	private EditText mInputEt;

	// 手机现场恢复
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fist);

		mInputEt = (EditText) findViewById(R.id.et_input);
		// 手机现场恢复
		// 恢复通过onSaveInstanceState保存的数据
		if (null != savedInstanceState) {
			mInputEt.setText(savedInstanceState.getString("save"));
			Toast.makeText(FirstActivity.this, savedInstanceState.getString("save"), Toast.LENGTH_SHORT).show();
		}

		Log.d(TAG, "onCreate func called!");

	}

	public void doClick(View view) {
		switch (view.getId()) {
		// 从assets目录下读取txt文件
		case R.id.btn1:
			Tools.getAssetsTextFile(FirstActivity.this);
			break;

		// 从assets/test/目录下读取txt文件
		case R.id.btn2:
			Tools.getAssetsTextFile2(FirstActivity.this);
			break;

		// 从raw目录读取txt文件
		case R.id.btn3:
			Tools.getRawTextFile(FirstActivity.this);
			break;

		// 要跳转的Activity没有在manifest中注册
		case R.id.btn4:
			Intent intent = new Intent(FirstActivity.this, NoManifestActivity.class);
			startActivity(intent);
			break;

		// 如何杀死进程
		case R.id.btn5:
			// 不能成功杀死进程
			// finish();
			// 能成功杀死进程
			// System.exit(0);
			// 能成功杀死进程
			Process.killProcess(Process.myPid());
			break;

		// 界面跳转
		case R.id.btn6:
			Intent intent7 = new Intent(FirstActivity.this, JumpActivity.class);
			startActivity(intent7);
			break;

		// 界面跳转，并传值
		case R.id.btn7:
			Bundle bundle = new Bundle();
			bundle.putString("name", "hello");

			Intent intent8 = new Intent(FirstActivity.this, JumpWithValueActivity.class);
			intent8.putExtra("b", true);
			intent8.putExtras(bundle);
			startActivity(intent8);
			break;

		// 界面跳转，并传值，然后再回传值
		case R.id.btn8:
			Intent intent9 = new Intent(FirstActivity.this, JumpWithBackValueActivity.class);
			intent9.putExtra("content", "sended from FirstActivity to JumpWithBackValueActivity");
			startActivityForResult(intent9, 1000);
			break;

		// 弹出一个对话框，看看对生命周期函数有何影响
		case R.id.btn9:
			// sdk/docs/guide/topics/ui/dialogs.html
			AlertDialog.Builder builder = new AlertDialog.Builder(FirstActivity.this);
			builder.setMessage("关闭后，看看对对生命周期函数有何影响呗？");
			builder.setTitle("这是一个对话框");
			builder.create();
			builder.setPositiveButton("确定", new OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub

				}
			});
			builder.show();
			break;

		// 跳转到一个局部窗体，看看对生命周期函数有何影响
		case R.id.btn10:
			Intent intent10 = new Intent(FirstActivity.this, FloatActivity.class);
			startActivity(intent10);

			break;

		// 切换到一个窗体，看看对生命周期函数有何影响
		case R.id.btn11:
			// 使用这种方式，点击几次才能跳转，跳转后，按返回键后程序直接退出了，没有回到FirstActivity主界面
			// Intent intent_11 = this.getIntent();
			Intent intent_11 = new Intent(FirstActivity.this, JumpActivity.class);
			intent_11.setClass(FirstActivity.this, JumpActivity.class);
			startActivity(intent_11);
			break;

		// 动态修改控件属性
		case R.id.btn12:
			Button _btn = (Button) findViewById(R.id.btn12);
			_btn.setText("动态修改button属性，看就这效果");
			break;

		// TODO:
		// 动态添加控件
		case R.id.btn13:
			LinearLayout _ll = (LinearLayout) findViewById(R.id.ll);
			Button newButton = new Button(this);
			newButton.setText("这是一个动态添加的按钮，成功了吧");
			if (null != _ll) {
				_ll.addView(newButton, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
			}
			break;
		}
	}

	// 看看回传的值是什么？
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		Log.d(TAG, "onActivityResult func called!");
		Log.d("CY", "onActivityResult called");

		Log.d(Tools.getTag(getApplication()), Tools.getMethodName(getApplication()) + "requestCode=" + requestCode + ",resultCode=" + resultCode);
		// 取出回传的值，并toast出来
		// 使用requestCode 和resultCode区分从哪个界面回传过来的
		if (1000 == requestCode && 1001 == resultCode) {
			String back_content = data.getStringExtra("back_content");

			// 显示收到的值
			Toast.makeText(FirstActivity.this, back_content, Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();

		Log.d(TAG, "onRestart func called!");
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		Log.d(TAG, "onStart func called!");
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.d(TAG, "onResume func called!");
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		Log.d(TAG, "onPause func called!");
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		Log.d(TAG, "onStop func called!");
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.d(TAG, "onDestroy func called!");
	}

	@Override
	public boolean isFinishing() {
		// TODO Auto-generated method stub
		Log.d(TAG, "isFinishing func called!");
		return super.isFinishing();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		super.onConfigurationChanged(newConfig);
		Log.d(TAG, "onConfigurationChanged func called!");

		setContentView(R.layout.activity_fist);

		// 这是一个横屏
		if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
			Log.d(TAG, "这是一个横屏");
		} else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
			Log.d(TAG, "这是一个竖屏");
		}
	}

	// 手机现场保护
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
		Log.d(TAG, "onSaveInstanceState func called!");
		// 手机现场保护
		outState.putString("save", mInputEt.getText().toString());
	}

	// 手机现场恢复
	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onRestoreInstanceState(savedInstanceState);
		Log.d(TAG, "onRestoreInstanceState func called!");

		// 手机现场恢复
		mInputEt.setText(savedInstanceState.getString("save"));
	}
}
