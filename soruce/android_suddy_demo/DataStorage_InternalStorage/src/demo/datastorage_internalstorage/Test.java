package demo.datastorage_internalstorage;

import android.content.Context;
import android.test.AndroidTestCase;
import demo.datastorage_internalstorage.tools.FSTools;

public class Test extends AndroidTestCase {
	public void testSave() {
		FSTools tools = new FSTools(getContext());
		tools.save("info", Context.MODE_APPEND, "测试文件保存是否成功".getBytes());
	}

	public void testRead() {
		FSTools tools = new FSTools(getContext());
		tools.read("info");
	}

	public void testReadCacle() {
		FSTools tools = new FSTools(getContext());
		tools.saveCacle("cacheSave", Context.MODE_APPEND, "测试缓存是否保存成功".getBytes());
	}

	public void testCacale1() {
		FSTools tools = new FSTools(getContext());
		tools.listCache();
	}
	
	public void testCacale2() {
		FSTools tools = new FSTools(getContext());
		tools.listCache2();
	}
}
