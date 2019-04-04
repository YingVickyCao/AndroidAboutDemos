package dataStream;

import java.io.*;

public class TestDataStream {
	// DataOutputStream�������������Ӧ�ó������ʵ���ʽ������ Java ��������д��������С�
	// Ȼ��Ӧ�ó������ʹ�����������������ݶ��롣
	public static void main(String[] args) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(baos);
		try {
			dos.writeDouble(Math.random());
			dos.writeBoolean(true);
			ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
			System.out.println(bais.available());
			// DataInputStream����һ��������޹أ���ǰ����ϵͳ�ȣ��ķ�ʽ��ֱ�Ӵӵش��ֽ���������ȡJAVA
			// �������ͺ�String���͵����ݣ����������紫��ȣ����紫������Ҫ����ƽ̨�޹أ�
			DataInputStream dis = new DataInputStream(bais);
			System.out.println(dis.readDouble());// ��д���ȶ�
			System.out.println(dis.readBoolean());
			dos.close();
			dis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}