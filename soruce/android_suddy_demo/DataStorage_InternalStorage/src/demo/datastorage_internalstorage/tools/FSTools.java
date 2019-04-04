package demo.datastorage_internalstorage.tools;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.content.Context;

public class FSTools {
	Context mContext;

	public FSTools(Context context) {
		// TODO Auto-generated constructor stub
		mContext = context;
	}

	// 建立二级文件目录。
	public boolean save(String fileName, int mode, byte[] data) {
		boolean flag = false;
		FileOutputStream fileOutputStream = null;

		try {

			// 得到文件目录
			File folder = new File(mContext.getFilesDir() + "/cy/");
			if (!folder.exists()) {
				folder.mkdir();
			}

			// 组装文件目录
			fileOutputStream = new FileOutputStream(folder.getAbsolutePath() + "/" + fileName);
			fileOutputStream.write(data, 0, data.length);
			flag = true;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getStackTrace());
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getStackTrace());
			e.printStackTrace();
		} finally {
			if (null != fileOutputStream) {
				try {
					fileOutputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return flag;
	}

	public String read(String fileName) {
		FileInputStream fileInputStream = null;
		ByteArrayOutputStream byteArrayOutputStream = null;
		try {
			fileInputStream = mContext.openFileInput(fileName);
			// 从文件读懂缓存，然后再把缓存返回
			byteArrayOutputStream = new ByteArrayOutputStream();
			int len = 0;
			byte[] data = new byte[1024];
			while ((len = fileInputStream.read(data)) != -1) {
				byteArrayOutputStream.write(data, 0, len);
			}
			return new String(byteArrayOutputStream.toByteArray());
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (null != fileInputStream) {
				try {
					fileInputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if (null != byteArrayOutputStream) {
				try {
					byteArrayOutputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return "";
	}

	public void showDir() {
		File file = mContext.getCacheDir();
		System.out.println("--saveCacle--file.getAbsolutePath()=" + file.getAbsolutePath());
	}

	// 建立二级缓存目录。
	public boolean saveCacle(String fileName, int mode, byte[] data) {
		FileOutputStream fileOutputStream = null;
		boolean flag = false;
		try {
			// 创建指定文件夹
			// 组装文件路径，如果该路径不存在，则创建该路径
			// 存放位置:/data/data/包名/cache/cy/
			File folder = new File(mContext.getCacheDir() + "/cy/");
			if (!folder.exists()) {
				folder.mkdir();
			}

			System.out.println("--saveCacle--" + folder.getAbsolutePath());

			fileOutputStream = new FileOutputStream(folder.getAbsolutePath() + "/" + fileName);
			fileOutputStream.write(data);
			flag = true;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (null != fileOutputStream) {
				try {
					fileOutputStream.close(); // cheked: IOException
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				fileOutputStream = null;
			}
		}

		return flag;
	}

	public void listCache() {
		String[] ss = mContext.fileList();
		for (String s : ss) {
			System.out.println("--listCache--" + s);
		}
	}

	public void listCache2() {
		File file1 = mContext.getFilesDir();
		System.out.println("--listCache2--" + file1.getAbsolutePath());

		File file2 = new File(file1.getAbsolutePath() + "/txt");
		System.out.println("--listCache2--" + file2.getAbsolutePath());

		File[] list = file2.listFiles();
		if (null != list) {
			for (File f : list) {
				System.out.println("--listCache2--" + f);
			}
		}
	}
}
