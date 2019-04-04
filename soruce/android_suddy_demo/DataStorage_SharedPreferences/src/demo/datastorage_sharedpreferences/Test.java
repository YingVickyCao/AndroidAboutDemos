package demo.datastorage_sharedpreferences;


import android.test.AndroidTestCase;
import android.util.Log;

/**
 * <pre>
 * 用于测试 的类，该继承AndroidTestCase。
 * </pre>
 * 
 * @author caoying
 * 
 */
public class Test extends AndroidTestCase {
	private String fileName = "user";

	// 测试单元测试是否生效
	public void test1() {
		SFTool tool = new SFTool(getContext());
		tool.testUnitTest("123456");
	}

	// 测试保存与读取数据
	public void test2() {
		SFTool tool = new SFTool(getContext());
		// 保存数据
		boolean flag = tool.saveUserInfo(fileName, "hades", "123456");
		System.out.println("-->" + flag);
		Log.d("CY", "-->" + flag);

		// 读取数据
		UserInfo info = new UserInfo();
		info = tool.getUserInfo(fileName);
		System.out.println(info.toString());
		Log.d("CY", info.toString());
	}
}
