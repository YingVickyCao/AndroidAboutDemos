package demo.datastorage_sharedpreferences;


import android.test.AndroidTestCase;
import android.util.Log;

/**
 * <pre>
 * ���ڲ��� ���࣬�ü̳�AndroidTestCase��
 * </pre>
 * 
 * @author caoying
 * 
 */
public class Test extends AndroidTestCase {
	private String fileName = "user";

	// ���Ե�Ԫ�����Ƿ���Ч
	public void test1() {
		SFTool tool = new SFTool(getContext());
		tool.testUnitTest("123456");
	}

	// ���Ա������ȡ����
	public void test2() {
		SFTool tool = new SFTool(getContext());
		// ��������
		boolean flag = tool.saveUserInfo(fileName, "hades", "123456");
		System.out.println("-->" + flag);
		Log.d("CY", "-->" + flag);

		// ��ȡ����
		UserInfo info = new UserInfo();
		info = tool.getUserInfo(fileName);
		System.out.println(info.toString());
		Log.d("CY", info.toString());
	}
}
