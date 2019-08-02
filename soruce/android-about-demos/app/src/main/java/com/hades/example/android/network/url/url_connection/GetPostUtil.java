package com.hades.example.android.network.url.url_connection;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

public class GetPostUtil {
    private static final String TAG = GetPostUtil.class.getSimpleName();

    /**
     * 向指定URL发送GET方法的请求
     *
     * @param url    发送请求的URL
     * @param params 请求参数，请求参数应该是name1=value1&name2=value2的形式。
     * @return URL所代表远程资源的响应
     */
    public static String sendGet(String url, String params) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlName = url + "?" + params;
            URL realUrl = new URL(urlName);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "keep-alive");
            conn.setRequestProperty("user-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_6)");
            // 建立实际的连接
            conn.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = conn.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += "\n" + line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 向指定URL发送POST方法的请求
     *
     * @param url    发送请求的URL
     * @param params 请求参数，请求参数应该是name1=value1&name2=value2的形式。
     * @return URL所代表远程资源的响应
     */
    public static String sendPost(String url, String params) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true); // URLConnection.getOutputStream().write()；从服务器端获得字节输出流, 服务器 -> app
            conn.setDoInput(true);  // URLConnection.getInputStream().read();   总是使用conn.getInputStream()获取服务端的响应，因此默认值是true

            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream()); // 先获取OutputStream，再向网络中输出请求参数params
            // 发送请求参数
            out.print(params);  // ②


            // flush输出流的缓冲
            out.flush();

            print("ContentEncoding:--->" + conn.getContentEncoding());//返回 content-encoding 头字段的值
            print("ContentLength:--->" + conn.getContentLength());//返回 content-length 头字段的值
            print("ContentType:--->" + conn.getContentType());//返回 content-type 头字段的值
            print("Date:--->" + conn.getDate());//返回 date 头字段的值
            print("Expiration:--->" + conn.getExpiration());//返回 expires 头字段的值
            print("LastModified:--->" + conn.getLastModified());//返回 last-modified 头字段的值
            print("DoInput:--->" + conn.getDoInput());//返回此 URLConnection 的 doInput 标志的值
            print("DoOutput:--->" + conn.getDoOutput());//返回此 URLConnection 的 doOutput 标志的值
            print("URL:--->" + conn.getURL());//返回此 URLConnection 的 URL 字段的值
            print("Permission:--->" + conn.getPermission());//返回一个权限对象，其代表建立此对象表示的连接所需的权限

            // 获取所有响应头字段
            Map<String, List<String>> map = conn.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }

            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += "\n" + line;
            }
            Log.d(TAG, "sendPost: " + result);
        } catch (Exception e) {
            System.out.println("发送POST请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    public static void print(String str) {
        System.out.println(str);
    }
}