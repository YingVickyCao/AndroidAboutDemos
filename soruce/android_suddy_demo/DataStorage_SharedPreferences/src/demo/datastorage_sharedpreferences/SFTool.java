package demo.datastorage_sharedpreferences;

import java.util.Map;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SFTool {
	private Context context;

	public SFTool(Context context) {
		// TODO Auto-generated constructor stub
		this.context = context;
	}

	// 测试单元测试是否生效
	public void testUnitTest(String data) {
		System.out.println(data);
	}

	/**
	 * <pre>
	 * 保存用户名和密码 
	 *  @param fileName	要保存的文件名，自动添加.xml后缀
	 *  @param name
	 *  @param pwd
	 *  @return	是否提交成功
	 * </pre>
	 */
	public boolean saveUserInfo(String fileName, String name, String pwd) {
		SharedPreferences preferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
		Editor editor = preferences.edit();
		editor.putString("name", name);
		editor.putString("password", pwd);
		// 提交数据
		return editor.commit();
	}

	/**
	 * <pre>
	 * 获取用户名和密码
	 * @param fileName
	 * @param name
	 * @param pwd
	 * @return
	 * </pre>
	 */
	public UserInfo getUserInfo(String fileName) {
		SharedPreferences preferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
		UserInfo userInfo = new UserInfo();
		userInfo.setName(preferences.getString("name", ""));
		userInfo.setPwd(preferences.getString("password", ""));
		return userInfo;
	}

	// 保存用户名和密码
	public void saveUserInfo(UserInfo info) {
	}

	// 封装存储工具类
	public void saveDatas(String fileName, int mode, Map<String, Object> data) {

	}

}
