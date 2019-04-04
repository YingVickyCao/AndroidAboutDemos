package demo.service_sundydemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.util.EncodingUtils;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class Tools {
	// log打印时，获取使用log的函数名
	public static String getMethodName(Context context) {
		StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
		StackTraceElement e = stacktrace[3];
		String methodName = e.getMethodName();
		return methodName + "func called.";
	}

	// 取得包名
	public static String getPackageName(Context context) {
		return context.getPackageName();
	}

	// 取得tag，其值为调用的该函数的类名
	public static String getTag(Context context) {
		return context.getClass().getSimpleName();
	}

	// 从assets目录下读取txt文件
	public static void getAssetsTextFile(Context context) {
		String fileName = "assets_file.txt";
		InputStream inputStream = null;

		try {
			// 获取assets文件
			inputStream = context.getResources().getAssets().open(fileName);

			if (null == inputStream) {
				return;
			}

			int len = inputStream.available();
			if (0 >= len) {
				return;
			}

			byte[] buffer = new byte[len];
			inputStream.read(buffer);
			String ret = EncodingUtils.getString(buffer, "GBK");
			Toast.makeText(context, ret, Toast.LENGTH_SHORT).show();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (null != inputStream) {
				try {
					inputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	// 从assets/test/目录下读取txt文件
	public static void getAssetsTextFile2(Context context) {
		// 访问/assets/test/assets_file2.txt文件，文件名应该为"test/assets_file2.txt"，这是一个相对路径
		// String fileName = "/test/assets_file2.txt";
		String fileName = "test/assets_file2.txt";
		InputStream inputStream = null;

		try {
			// 获取assets文件
			inputStream = context.getResources().getAssets().open(fileName);

			if (null == inputStream) {
				return;
			}

			int len = inputStream.available();
			if (0 >= len) {
				return;
			}

			byte[] buffer = new byte[len];
			inputStream.read(buffer);
			String ret = EncodingUtils.getString(buffer, "GBK");
			Toast.makeText(context, ret, Toast.LENGTH_SHORT).show();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (null != inputStream) {
				try {
					inputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	// 从raw目录读取txt文件
	public static void getRawTextFile(Context context) {

		// 字节输入流
		InputStream inputStream = null;
		// 字符输入流
		InputStreamReader inputStreamReader = null;
		BufferedReader bufferedReader = null;

		try {
			// 获取raw文件
			inputStream = context.getResources().openRawResource(R.raw.raw_file);

			if (null != inputStream) {
				// 使用UTF-8读取的是乱码
				// inputStreamReader = new InputStreamReader(inputStream,
				// "UTF-8");
				inputStreamReader = new InputStreamReader(inputStream, "GBK");

				if (null != inputStreamReader) {
					bufferedReader = new BufferedReader(inputStreamReader);

					if (null != bufferedReader) {
						// 读取的第1行总是null
						// String ret = null;
						// String info = null;

						String ret = "";
						String info = "";
						while (null != (info = bufferedReader.readLine())) {
							ret += info + "\n";
						}
						Toast.makeText(context, ret, Toast.LENGTH_SHORT).show();
					}
				}
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				if (null != inputStream) {
					inputStream.close();
				}
				if (null != inputStreamReader) {
					inputStreamReader.close();
				}

				if (null != bufferedReader) {
					bufferedReader.close();
				}
			} catch (IOException ex) {
				Log.e("SunnyDemo", ex.getStackTrace().toString());
				Toast.makeText(context, "关闭资源异常", Toast.LENGTH_SHORT).show();
			}
		}
	}

	public final static int MUSIC_PLAY = 1;
	public final static int MUSIC_PAUSE = MUSIC_PLAY + 1;
	public final static int MUSIC_STOP = MUSIC_PAUSE + 1;

}
