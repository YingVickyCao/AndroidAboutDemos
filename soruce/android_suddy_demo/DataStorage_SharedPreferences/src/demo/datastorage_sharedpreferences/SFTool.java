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

	// ���Ե�Ԫ�����Ƿ���Ч
	public void testUnitTest(String data) {
		System.out.println(data);
	}

	/**
	 * <pre>
	 * �����û��������� 
	 *  @param fileName	Ҫ������ļ������Զ����.xml��׺
	 *  @param name
	 *  @param pwd
	 *  @return	�Ƿ��ύ�ɹ�
	 * </pre>
	 */
	public boolean saveUserInfo(String fileName, String name, String pwd) {
		SharedPreferences preferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
		Editor editor = preferences.edit();
		editor.putString("name", name);
		editor.putString("password", pwd);
		// �ύ����
		return editor.commit();
	}

	/**
	 * <pre>
	 * ��ȡ�û���������
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

	// �����û���������
	public void saveUserInfo(UserInfo info) {
	}

	// ��װ�洢������
	public void saveDatas(String fileName, int mode, Map<String, Object> data) {

	}

}
