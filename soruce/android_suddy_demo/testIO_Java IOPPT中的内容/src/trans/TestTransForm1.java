package trans;

import java.io.*;

//转换流
public class TestTransForm1 {
	public static void main(String[] args) {
		try {
			OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream("d:\\io\\char.txt"));
			osw.write("mircosoftibmsunapplehp测试");
			System.out.println("osw1:" + osw.getEncoding());
			osw.close();
			
			osw = new OutputStreamWriter(new FileOutputStream("d:\\io\\char.txt", false), "ISO8859_1");// true,代表追加文件
			osw.write("mircosoftibmsunapplehp韵达");
			System.out.println("osw2:" + osw.getEncoding());
			osw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}