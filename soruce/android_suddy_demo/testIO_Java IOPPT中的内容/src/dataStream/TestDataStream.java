package dataStream;

import java.io.*;

public class TestDataStream {
	// DataOutputStream数据输出流允许应用程序以适当方式将基本 Java 数据类型写入输出流中。
	// 然后应用程序可以使用数据输入流将数据读入。
	public static void main(String[] args) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(baos);
		try {
			dos.writeDouble(Math.random());
			dos.writeBoolean(true);
			ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
			System.out.println(bais.available());
			// DataInputStream能以一种与机器无关（当前操作系统等）的方式，直接从地从字节输入流读取JAVA
			// 基本类型和String类型的数据，常用于网络传输等（网络传输数据要求与平台无关）
			DataInputStream dis = new DataInputStream(bais);
			System.out.println(dis.readDouble());// 先写的先读
			System.out.println(dis.readBoolean());
			dos.close();
			dis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}