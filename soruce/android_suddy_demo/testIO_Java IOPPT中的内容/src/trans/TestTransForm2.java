package trans;

import java.io.*;

public class TestTransForm2 {
	public static void main(String args[]) {
		InputStreamReader isr = new InputStreamReader(System.in);// ��׼��������System.in��InputStream��Ķ��󣬵���������Ҫ�Ӽ��̶�������ʱ��ֻ��Ҫ����System.in�е�read()����
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
} // ����