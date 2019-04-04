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
      System.out.println("文件写入错误");
      System.exit(-1);
    }
    //TODO 在java中的char型变量中能不能存储一个中文汉字？为什么？
    //java采用unicode，2个字节（16位）来表示一个字符， 无论是汉字还是数字字母，或其他语言。
    //char 在java中是2个字节。所以可以存储中文
  }
}
