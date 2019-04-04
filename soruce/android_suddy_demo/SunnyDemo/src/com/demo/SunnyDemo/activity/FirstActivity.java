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
 *  �й�Activity�Ĳ���
 * @author caoying
 * </pre>
 */
public class FirstActivity extends ActionBarActivity {
	private String TAG = "FirstActivity";
	private EditText mInputEt;

	// �ֻ��ֳ��ָ�
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fist);

		mInputEt = (EditText) findViewById(R.id.et_input);
		// �ֻ��ֳ��ָ�
		// �ָ�ͨ��onSaveInstanceState���������
		if (null != savedInstanceState) {
			mInputEt.setText(savedInstanceState.getString("save"));
			Toast.makeText(FirstActivity.this, savedInstanceState.getString("save"), Toast.LENGTH_SHORT).show();
		}

		Log.d(TAG, "onCreate func called!");

	}

	public void doClick(View view) {
		switch (view.getId()) {
		// ��assetsĿ¼�¶�ȡtxt�ļ�
		case R.id.btn1:
			Tools.getAssetsTextFile(FirstActivity.this);
			break;

		// ��assets/test/Ŀ¼�¶�ȡtxt�ļ�
		case R.id.btn2:
			Tools.getAssetsTextFile2(FirstActivity.this);
			break;

		// ��rawĿ¼��ȡtxt�ļ�
		case R.id.btn3:
			Tools.getRawTextFile(FirstActivity.this);
			break;

		// Ҫ��ת��Activityû����manifest��ע��
		case R.id.btn4:
			Intent intent = new Intent(FirstActivity.this, NoManifestActivity.class);
			startActivity(intent);
			break;

		// ���ɱ������
		case R.id.btn5:
			// ���ܳɹ�ɱ������
			// finish();
			// �ܳɹ�ɱ������
			// System.exit(0);
			// �ܳɹ�ɱ������
			Process.killProcess(Process.myPid());
			break;

		// ������ת
		case R.id.btn6:
			Intent intent7 = new Intent(FirstActivity.this, JumpActivity.class);
			startActivity(intent7);
			break;

		// ������ת������ֵ
		case R.id.btn7:
			Bundle bundle = new Bundle();
			bundle.putString("name", "hello");

			Intent intent8 = new Intent(FirstActivity.this, JumpWithValueActivity.class);
			intent8.putExtra("b", true);
			intent8.putExtras(bundle);
			startActivity(intent8);
			break;

		// ������ת������ֵ��Ȼ���ٻش�ֵ
		case R.id.btn8:
			Intent intent9 = new Intent(FirstActivity.this, JumpWithBackValueActivity.class);
			intent9.putExtra("content", "sended from FirstActivity to JumpWithBackValueActivity");
			startActivityForResult(intent9, 1000);
			break;

		// ����һ���Ի��򣬿������������ں����к�Ӱ��
		case R.id.btn9:
			// sdk/docs/guide/topics/ui/dialogs.html
			AlertDialog.Builder builder = new AlertDialog.Builder(FirstActivity.this);
			builder.setMessage("�رպ󣬿����Զ��������ں����к�Ӱ���£�");
			builder.setTitle("����һ���Ի���");
			builder.create();
			builder.setPositiveButton("ȷ��", new OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub

				}
			});
			builder.show();
			break;

		// ��ת��һ���ֲ����壬�������������ں����к�Ӱ��
		case R.id.btn10:
			Intent intent10 = new Intent(FirstActivity.this, FloatActivity.class);
			startActivity(intent10);

			break;

		// �л���һ�����壬�������������ں����к�Ӱ��
		case R.id.btn11:
			// ʹ�����ַ�ʽ��������β�����ת����ת�󣬰����ؼ������ֱ���˳��ˣ�û�лص�FirstActivity������
			// Intent intent_11 = this.getIntent();
			Intent intent_11 = new Intent(FirstActivity.this, JumpActivity.class);
			intent_11.setClass(FirstActivity.this, JumpActivity.class);
			startActivity(intent_11);
			break;

		// ��̬�޸Ŀؼ�����
		case R.id.btn12:
			Button _btn = (Button) findViewById(R.id.btn12);
			_btn.setText("��̬�޸�button���ԣ�������Ч��");
			break;

		// TODO:
		// ��̬��ӿؼ�
		case R.id.btn13:
			LinearLayout _ll = (LinearLayout) findViewById(R.id.ll);
			Button newButton = new Button(this);
			newButton.setText("����һ����̬��ӵİ�ť���ɹ��˰�");
			if (null != _ll) {
				_ll.addView(newButton, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
			}
			break;
		}
	}

	// �����ش���ֵ��ʲô��
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		Log.d(TAG, "onActivityResult func called!");
		Log.d("CY", "onActivityResult called");

		Log.d(Tools.getTag(getApplication()), Tools.getMethodName(getApplication()) + "requestCode=" + requestCode + ",resultCode=" + resultCode);
		// ȡ���ش���ֵ����toast����
		// ʹ��requestCode ��resultCode���ִ��ĸ�����ش�������
		if (1000 == requestCode && 1001 == resultCode) {
			String back_content = data.getStringExtra("back_content");

			// ��ʾ�յ���ֵ
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

		// ����һ������
		if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
			Log.d(TAG, "����һ������");
		} else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
			Log.d(TAG, "����һ������");
		}
	}

	// �ֻ��ֳ�����
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
		Log.d(TAG, "onSaveInstanceState func called!");
		// �ֻ��ֳ�����
		outState.putString("save", mInputEt.getText().toString());
	}

	// �ֻ��ֳ��ָ�
	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onRestoreInstanceState(savedInstanceState);
		Log.d(TAG, "onRestoreInstanceState func called!");

		// �ֻ��ֳ��ָ�
		mInputEt.setText(savedInstanceState.getString("save"));
	}
}
