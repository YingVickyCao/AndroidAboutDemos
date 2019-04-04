package buffer;

import java.io.*;

public class TestBufferStream1 {
	public static void main(String[] args) {
		try {
			FileInputStream fis = new FileInputStream("d:\\io\\HelloWorld.txt");
			BufferedInputStream bis = new BufferedInputStream(fis);
			int c = 0;
			System.out.println(bis.read());
			bis.mark(100);// 在输入流中的当前位置上作标记。reset
							// 方法的后续调用将此流重新定位在最后标记的位置上，以便后续读取操作重新读取相同的字节。
			for (int i = 0; i <= 10 && (c = bis.read()) != -1; i++) {
				System.out.print(c + " ");
			}
			System.out.println();
			bis.reset();// 与mark相对应
			for (int i = 0; i <= 10 && (c = bis.read()) != -1; i++) {
				System.out.print((char) c + " ");
			}
			bis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}