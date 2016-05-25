package fbtt.com.fbtt.utils;

import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import fbtt.com.fbtt.constants.Constants;


/**
 * Created by Administrator on 2016/4/13.
 */
public class HttpUtils {

    /**
     * 通过http post 提交数据
     *
     * @param url      访问路径
     * @param content  内容
     * @param encoding 返回内容字符编码
     * @return
     */
    public static String HttpPost(String url, String content, String encoding) {
        DebugUtils.i("HttpUtils", "content: " + content);
        HttpURLConnection conn = null;
        String str = "";
        try {
            conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setDoInput(true);// 打开输入流，以便从服务器获取数据
            conn.setDoOutput(true);// 打开输出流，以便向服务器提交数据
            conn.setConnectTimeout(3000); // 设置连接超时时间
            conn.setReadTimeout(3000); //设置返回超时时间,下面要对超时进行处理
            conn.setRequestMethod("POST");
            conn.setUseCaches(false);// 使用Post方式不能使用缓存
            conn.setInstanceFollowRedirects(true);
            //conn.setRequestProperty("Cookie", SessionId);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
            conn.connect();
            DataOutputStream out = new DataOutputStream(conn.getOutputStream());
            out.writeBytes(content);
            out.flush();
            out.close(); // flush and close
            int response = conn.getResponseCode(); // 获得服务器的响应码
            if (response == HttpURLConnection.HTTP_OK) {
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), encoding));
                StringBuffer sb = new StringBuffer();
                String line;
                while ((line = br.readLine()) != null) {
                    line = new String(line.getBytes(), "UTF-8");
                    sb.append(line);
                }
                str = sb.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
            //里面会抛连接和返回超时java.net.SocketTimeoutException，还有IO异常
            return "faild";
        } finally {
            conn.disconnect();
            conn = null;
        }
        return str;
    }

    /**
     * GET请求方式
     *
     * @param url
     * @return
     */
    public static String HttpGet(String url, String encoding) {
        LogUtils.i("HttpUtils", "encoding: " + encoding);
        HttpURLConnection conn = null;
        String str = "";
        try {
            conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setDoInput(true);// 打开输入流，以便从服务器获取数据
            conn.setDoOutput(true);// 打开输出流，以便向服务器提交数据
            conn.setConnectTimeout(30000); // 设置连接超时时间
            conn.setReadTimeout(30000); //设置返回超时时间,下面要对超时进行处理
            conn.setRequestMethod("GET");
            conn.connect();
            int response = conn.getResponseCode(); // 获得服务器的响应码
            if (response == HttpURLConnection.HTTP_OK) {
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), encoding));
                StringBuffer sb = new StringBuffer();
                String line;
                while ((line = br.readLine()) != null) {
                    line = new String(line.getBytes(), "UTF-8");
                    sb.append(line);
                }
                str = sb.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
            //里面会抛连接和返回超时java.net.SocketTimeoutException，还有IO异常
            return "faild";
        } finally {
            if (conn != null)
                conn.disconnect();
        }
        return str;
    }

    /**
     * 通过https post 提交数据
     *
     * @param url      访问路径
     * @param content  内容
     * @param encoding 返回内容字符编码
     * @return
     */
    public static String HttpsPost(String url, String content, String encoding) {
        LogUtils.i("HttpUtils", "content: " + content);
        SSLContext sc = null;
        try {
            sc = SSLContext.getInstance("TLS");
            MyTrustManager mtm = new MyTrustManager();
            sc.init(null, new TrustManager[]{mtm}, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String s, SSLSession sslSession) {
                    return true;
                }
            });

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "faild";
        } catch (KeyManagementException e) {
            e.printStackTrace();
            return "faild";
        }
        HttpsURLConnection conn = null;
        String str = "";
        try {
            conn = (HttpsURLConnection) new URL(url).openConnection();
            conn.setDoInput(true);// 打开输入流，以便从服务器获取数据
            conn.setDoOutput(true);// 打开输出流，以便向服务器提交数据
            conn.setConnectTimeout(30000); // 设置连接超时时间
            conn.setReadTimeout(30000); //设置返回超时时间,下面要对超时进行处理
            conn.setRequestMethod("POST");
            conn.setUseCaches(false);// 使用Post方式不能使用缓存
            conn.setInstanceFollowRedirects(true);
            //conn.setRequestProperty("Cookie", SessionId);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
            conn.connect();
            DataOutputStream out = new DataOutputStream(conn.getOutputStream());
            out.writeBytes(content);
            out.flush();
            out.close(); // flush and close
            int response = conn.getResponseCode(); // 获得服务器的响应码
            if (response == HttpURLConnection.HTTP_OK) {
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), encoding));
                StringBuffer sb = new StringBuffer();
                String line;
                while ((line = br.readLine()) != null) {
                    line = new String(line.getBytes(), "UTF-8");
                    sb.append(line);
                }
                str = sb.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
            //里面会抛连接和返回超时java.net.SocketTimeoutException，还有IO异常
            return "faild";
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
        return str;
    }


    public static String HttpsGet(String url, String encoding) {
        LogUtils.i("HttpUtils", "httpsget url: " + url + " encoding: " + encoding);
        SSLContext sc = null;
        try {
            sc = SSLContext.getInstance("TLS");
            MyTrustManager mtm = new MyTrustManager();
            sc.init(null, new TrustManager[]{mtm}, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String s, SSLSession sslSession) {
                    return true;
                }
            });

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "faild";
        } catch (KeyManagementException e) {
            e.printStackTrace();
            return "faild";
        }
        HttpsURLConnection conn = null;
        String str = "";
        try {
            conn = (HttpsURLConnection) new URL(url).openConnection();
            conn.setDoInput(true);// 打开输入流，以便从服务器获取数据
            conn.setDoOutput(true);// 打开输出流，以便向服务器提交数据
            conn.setConnectTimeout(30000); // 设置连接超时时间
            conn.setReadTimeout(30000); //设置返回超时时间,下面要对超时进行处理
            conn.setRequestMethod("GET");
            conn.connect();
            int response = conn.getResponseCode(); // 获得服务器的响应码
            if (response == HttpURLConnection.HTTP_OK) {
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), encoding));
                StringBuffer sb = new StringBuffer();
                String line;
                while ((line = br.readLine()) != null) {
                    line = new String(line.getBytes(), "UTF-8");
                    sb.append(line);
                }
                str = sb.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
            //里面会抛连接和返回超时java.net.SocketTimeoutException，还有IO异常
            return "faild";
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
        return str;
    }


    /**
     * 获取基本信息
     *
     * @return
     */
    public static StringBuffer getConstansData() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(Constants.order_from);//向服务器传递版本号

        stringBuffer.deleteCharAt(stringBuffer.length() - 1);
        return stringBuffer;
    }

    /**
     *  封装请求体信息
     *  @params 请求体内容，
     *  @encode 编码格式
    */
    public static StringBuffer getRequestData(Map<String, String> params, String encode) {
        StringBuffer stringBuffer = getConstansData(); // 存储封装好的请求体信息
        stringBuffer.append("&");
        try {
            if (params != null && params.size() > 0) {
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    String key = entry.getKey();
                    String value = entry.getValue();
                    if (!TextUtils.isEmpty(key)) {
                        stringBuffer.append(key)
                                .append("=")
                                .append(URLEncoder.encode((TextUtils.isEmpty(value) ? "" : value), encode))
                                .append("&");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        stringBuffer.deleteCharAt(stringBuffer.length() - 1); // 删除最后的一个"&"
        LogUtils.i("", stringBuffer.toString());
        return stringBuffer;
    }




    static class MyTrustManager implements X509TrustManager {

        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {

        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {

        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }
    }


    /**
     * 服务器下载文件
     *
     * @param fileDir  文件保存路径
     * @param fileName 文件名称
     * @param url_     下载路径
     * @return
     */
    public static boolean downLoadingFile(String fileDir, String fileName, String url_) {
        boolean flag = false;
        HttpURLConnection conn = null;
        FileUtils fileUtils = new FileUtils();
        try {
            conn = (HttpURLConnection) (new URL(url_)).openConnection();
            conn.setRequestMethod("GET");
            conn.setReadTimeout(10 * 1000);
            conn.getDoInput();
            conn.getDoOutput();
            conn.connect();
            if (conn.getResponseCode() == 200) {
                File file = fileUtils.write2SDFromInput(fileDir, fileName, conn.getInputStream());
                if (file != null) {
                    flag = true;
                } else {
                    flag = false;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            flag = false;
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
        return flag;
    }


}
