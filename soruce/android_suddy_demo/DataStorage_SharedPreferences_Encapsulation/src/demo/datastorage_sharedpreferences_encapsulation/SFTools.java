package demo.datastorage_sharedpreferences_encapsulation;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * <pre>
 * 封装SharedPreferences工具类
 * @author caoying
 * </pre>
 */
public class SFTools {
	private Context context;

	public SFTools(Context context) {
		// TODO Auto-generated constructor stub
		this.context = context;
	}

	/**
	 * <pre>
	 * 保存数据
	 * @param fileName
	 * @param map	数据集
	 * @return	保存是否成功
	 * </pre>
	 */
	public boolean saveRFData(String fileName, Map<String, Object> map) {
		SharedPreferences preferences = context.getSharedPreferences(fileName, Context.MODE_APPEND);
		Editor editor = preferences.edit();
		// 清空缓存
		editor.clear();

		for (Map.Entry<String, Object> item : map.entrySet()) {
			String key = item.getKey();
			Object value = item.getValue();

			if (value instanceof Boolean) {
				editor.putBoolean(key, (Boolean) value);
			} else if (value instanceof Float) {
				editor.putFloat(key, (Float) value);
			} else if (value instanceof Integer) {
				editor.putInt(key, (Integer) value);
			} else if (value instanceof Long) {
				editor.putLong(key, (Long) value);
			} else if (value instanceof String) {
				editor.putString(key, (String) value);
			} else if (value instanceof HashSet<?>) {
				// TODO
				// editor.putStringSet(key, new HashSet<String>() );
				editor.putStringSet(key, (Set<String>) value);
			}
		}
		return editor.commit();
	}

	/**
	 * <pre>
	 * 获取数据
	 * @param fileName
	 * @return
	 * </pre>
	 */
	public Map<String, ?> getRFData(String fileName) {
		Map<String, ?> map = null;
		SharedPreferences preferences = context.getSharedPreferences(fileName, Context.MODE_APPEND);
		map = preferences.getAll();
		return map;
	}
}
