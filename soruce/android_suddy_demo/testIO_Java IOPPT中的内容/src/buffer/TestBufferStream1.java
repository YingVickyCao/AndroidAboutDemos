package buffer;

import java.io.*;

public class TestBufferStream1 {
	public static void main(String[] args) {
		try {
			FileInputStream fis = new FileInputStream("d:\\io\\HelloWorld.txt");
			BufferedInputStream bis = new BufferedInputStream(fis);
			int c = 0;
			System.out.println(bis.read());
			bis.mark(100);// ���������еĵ�ǰλ��������ǡ�reset
							// �����ĺ������ý��������¶�λ������ǵ�λ���ϣ��Ա������ȡ�������¶�ȡ��ͬ���ֽڡ�
			for (int i = 0; i <= 10 && (c = bis.read()) != -1; i++) {
				System.out.print(c + " ");
			}
			System.out.println();
			bis.reset();// ��mark���Ӧ
			for (int i = 0; i <= 10 && (c = bis.read()) != -1; i++) {
				System.out.print((char) c + " ");
			}
			bis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}