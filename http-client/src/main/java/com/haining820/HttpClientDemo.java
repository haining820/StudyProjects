package com.haining820;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created with IntelliJ IDEA
 * Description:
 * User: hn.yu
 * Date: 2022-09-24
 * Time: 11:05
 */
public class HttpClientDemo {

    public static void main(String[] args) {
        String pathUrl = "http://localhost:9100/hello";
        OutputStreamWriter out = null;
        BufferedReader br = null;
        String result = "";
        try {
            URL url = new URL(pathUrl);
            // 打开和url之间的连接
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            // 设置请求方式
            connection.setRequestMethod("POST");

            // 设置连接超时时间和读超时时间, 值必须大于0，设置为0表示不超时 单位为“毫秒”
//            connection.setConnectTimeout(3000);
//            connection.setReadTimeout(6000);

            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
            connection.setRequestProperty("Content-Type", "application/json;charset=utf-8");

            // DoOutput设置是否向httpUrlConnection输出，DoInput设置是否从httpUrlConnection读入，此外发送post请求必须设置这两个
            connection.setDoOutput(true);
            connection.setDoInput(true);
            /**
             * 下面的三句代码，就是调用第三方http接口
             */
            OutputStream outputStream = connection.getOutputStream();
            out = new OutputStreamWriter(outputStream, "UTF-8");
            out.write("");
            // flush输出流的缓冲
            out.flush();

            /**
             * 下面的代码相当于，获取调用第三方http接口后返回的结果
             */
            // 获取URLConnection对象对应的输入流
            InputStream is = connection.getInputStream();
            // 构造一个字符流缓存
            br = new BufferedReader(new InputStreamReader(is));
            String str = "";
            while ((str = br.readLine()) != null) {
                result += str;
            }
            System.out.println("result->" + result);
            // 关闭流
            is.close();

            int responseCode = connection.getResponseCode();
            System.out.println("responseCode->" + responseCode);

            // 断开连接，disconnect是在底层tcp socket链接空闲时才切断，如果正在被其他线程使用就不切断。
            connection.disconnect();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
