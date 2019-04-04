package objectIO;

import java.io.*;

public class TestObjectIO {
	// ��Ϸ�������
	public static void main(String args[]) throws Exception {
		T t = new T();
		t.k = 8;
		// д���ݵ��ļ�
		FileOutputStream fos = new FileOutputStream("d:/io/testobjectio.dat");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(t);
		oos.flush();
		oos.close();

		// ���ļ�������
		FileInputStream fis = new FileInputStream("d:/io/testobjectio.dat");
		ObjectInputStream ois = new ObjectInputStream(fis);
		T tReaded = (T) ois.readObject();
		System.out.println(tReaded.i + " " + tReaded.j + " " + tReaded.d + " " + tReaded.k);

	}
}

class T implements Serializable {
	int i = 10;
	int j = 9;
	double d = 2.3;
	// ָ��K��������Ҫ���л�
	transient int k = 15;
}