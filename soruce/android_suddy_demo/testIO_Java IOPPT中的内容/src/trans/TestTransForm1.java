package trans;

import java.io.*;

//ת����
public class TestTransForm1 {
	public static void main(String[] args) {
		try {
			OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream("d:\\io\\char.txt"));
			osw.write("mircosoftibmsunapplehp����");
			System.out.println("osw1:" + osw.getEncoding());
			osw.close();
			
			osw = new OutputStreamWriter(new FileOutputStream("d:\\io\\char.txt", false), "ISO8859_1");// true,����׷���ļ�
			osw.write("mircosoftibmsunapplehp�ϴ�");
			System.out.println("osw2:" + osw.getEncoding());
			osw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}