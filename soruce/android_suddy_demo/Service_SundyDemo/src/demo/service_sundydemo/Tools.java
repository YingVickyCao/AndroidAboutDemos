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
	// log��ӡʱ����ȡʹ��log�ĺ�����
	public static String getMethodName(Context context) {
		StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
		StackTraceElement e = stacktrace[3];
		String methodName = e.getMethodName();
		return methodName + "func called.";
	}

	// ȡ�ð���
	public static String getPackageName(Context context) {
		return context.getPackageName();
	}

	// ȡ��tag����ֵΪ���õĸú���������
	public static String getTag(Context context) {
		return context.getClass().getSimpleName();
	}

	// ��assetsĿ¼�¶�ȡtxt�ļ�
	public static void getAssetsTextFile(Context context) {
		String fileName = "assets_file.txt";
		InputStream inputStream = null;

		try {
			// ��ȡassets�ļ�
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

	// ��assets/test/Ŀ¼�¶�ȡtxt�ļ�
	public static void getAssetsTextFile2(Context context) {
		// ����/assets/test/assets_file2.txt�ļ����ļ���Ӧ��Ϊ"test/assets_file2.txt"������һ�����·��
		// String fileName = "/test/assets_file2.txt";
		String fileName = "test/assets_file2.txt";
		InputStream inputStream = null;

		try {
			// ��ȡassets�ļ�
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

	// ��rawĿ¼��ȡtxt�ļ�
	public static void getRawTextFile(Context context) {

		// �ֽ�������
		InputStream inputStream = null;
		// �ַ�������
		InputStreamReader inputStreamReader = null;
		BufferedReader bufferedReader = null;

		try {
			// ��ȡraw�ļ�
			inputStream = context.getResources().openRawResource(R.raw.raw_file);

			if (null != inputStream) {
				// ʹ��UTF-8��ȡ��������
				// inputStreamReader = new InputStreamReader(inputStream,
				// "UTF-8");
				inputStreamReader = new InputStreamReader(inputStream, "GBK");

				if (null != inputStreamReader) {
					bufferedReader = new BufferedReader(inputStreamReader);

					if (null != bufferedReader) {
						// ��ȡ�ĵ�1������null
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
				Toast.makeText(context, "�ر���Դ�쳣", Toast.LENGTH_SHORT).show();
			}
		}
	}

	public final static int MUSIC_PLAY = 1;
	public final static int MUSIC_PAUSE = MUSIC_PLAY + 1;
	public final static int MUSIC_STOP = MUSIC_PAUSE + 1;

}
