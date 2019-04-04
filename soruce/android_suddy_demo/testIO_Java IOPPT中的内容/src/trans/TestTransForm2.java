package trans;

import java.io.*;

public class TestTransForm2 {
	public static void main(String args[]) {
		InputStreamReader isr = new InputStreamReader(System.in);// 标准输入流：System.in是InputStream类的对象，当程序中需要从键盘读入数据时候，只需要调用System.in中的read()方法
		BufferedReader br = new BufferedReader(isr);
		String s = null;
		try {
			s = br.readLine();
			while (s != null) {
				if (s.equalsIgnoreCase("exit"))
					break;
				System.out.println(s.toUpperCase());
				s = br.readLine();
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
} // 阻塞