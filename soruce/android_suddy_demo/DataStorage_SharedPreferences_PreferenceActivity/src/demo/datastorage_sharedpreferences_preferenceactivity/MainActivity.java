package demo.datastorage_sharedpreferences_preferenceactivity;

import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends PreferenceActivity implements OnPreferenceChangeListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 已经继承了PreferenceActivity，setContentView不再需要了，需要的是addPreferencesFromResource。
		// setContentView(R.layout.activity_main);
		// 使用自定义的文件名保存数据，而非默认的
		getPreferenceManager().setSharedPreferencesName("config");
		// 加载preferences layout
		addPreferencesFromResource(R.xml.preferences);
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

	// TODO:没有弹出信息
	@Override
	public boolean onPreferenceChange(Preference preference, Object newValue) {
		// TODO Auto-generated method stub
		if ("cp_saveAutpPwd".equals(preference.getKey())) {
			CheckBoxPreference checkBoxPreference = (CheckBoxPreference) findPreference("cp_saveAutpPwd");
			Toast.makeText(MainActivity.this, checkBoxPreference.getKey() + "-->" + checkBoxPreference.getTitle(), 1).show();
			// checkBoxPreference.getKey();
			// checkBoxPreference.getTitle();
			// checkBoxPreference.getSummary();
			EditTextPreference editTextPreference = (EditTextPreference) findPreference("ep_id");
			editTextPreference.setEnabled(checkBoxPreference.isChecked());
		}

		// public void onSharedPreferenceChanged(SharedPreferences
		// sharedPreferences, String key) {
		// // TODO Auto-generated method stub
		// if (key.equals("rp_qq")) {
		// System.out.println("--->rpqq--" + sharedPreferences.getString(key,
		// ""));
		// Preference preference = findPreference(key);
		// preference.setSummary(sharedPreferences.getString(key, ""));
		// }
		return false;
	}
}
