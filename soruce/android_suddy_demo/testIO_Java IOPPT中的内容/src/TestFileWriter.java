import java.io.*;
public class TestFileWriter {
  public static void main(String[] args) {
    FileWriter fw = null;
    try {
      fw = new FileWriter("d:\\io\\unicode.dat");
      for(int c=0;c<=50000;c++){
        fw.write(c);
      }
      fw.close();
    } catch (IOException e1) {
    	e1.printStackTrace();
      System.out.println("�ļ�д�����");
      System.exit(-1);
    }
    //TODO ��java�е�char�ͱ������ܲ��ܴ洢һ�����ĺ��֣�Ϊʲô��
    //java����unicode��2���ֽڣ�16λ������ʾһ���ַ��� �����Ǻ��ֻ���������ĸ�����������ԡ�
    //char ��java����2���ֽڡ����Կ��Դ洢����
  }
}
