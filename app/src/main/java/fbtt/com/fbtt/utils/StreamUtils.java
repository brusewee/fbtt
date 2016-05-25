package fbtt.com.fbtt.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Administrator on 2016/4/14.
 */
public class StreamUtils {

    private static final String TAG = "StreamUtil";

    /**
     * 输入流返回字符串
     *
     * @param is
     * @return
     */
    public static String readStream(InputStream is) {
        InputStreamReader isr = null;
        BufferedReader br = null;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            isr = new InputStreamReader(is, "UTF-8");
            br = new BufferedReader(isr);
            String temp;
            while ((temp = br.readLine()) != null) {
                stringBuilder.append(temp);
            }
        } catch (Exception e) {
            Log.e(TAG, "流读取失败:" + e);
        } finally {
            try {
                if (br != null) {
                    br.close();
                }

                if (isr != null) {
                    isr.close();
                }

                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                Log.e(TAG, "流关闭失败:" + e);
            }
        }

        return stringBuilder.toString();

    }

    /**
     * #通过url 从网络中加载img 返回 bitmap
     *
     * @param url
     * @return
     */
    public static Bitmap getBitmapFromUrl(String url) {

        Bitmap bitmap = null;
        BufferedInputStream bis = null;
        InputStream inputStream = null;
        HttpURLConnection urlConn = null;
        ByteArrayOutputStream swapStream = null;
        try {
            URL urlHttp = new URL(url);
            urlConn = (HttpURLConnection) urlHttp.openConnection();
            inputStream = urlConn.getInputStream();
            swapStream = new ByteArrayOutputStream();
            byte[] buff = new byte[100];
            int rc = 0;
            while ((rc = inputStream.read(buff, 0, 100)) > 0) {
                swapStream.write(buff, 0, rc);
            }
            bitmap = BitmapFactory.decodeByteArray(swapStream.toByteArray(), 0, swapStream.toByteArray().length);
        } catch (Exception e) {
            Log.e(TAG, "请求图片异常" + e);
        } finally {

            try {
                if (swapStream != null) {
                    swapStream.close();
                }
                if (urlConn != null) {
                    urlConn.disconnect();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
                if (bis != null) {
                    bis.close();
                }
            } catch (IOException e) {
                Log.e(TAG, "流关闭异常");
            }
        }

        return bitmap;
    }

    /**
     * 将一个字符串转化为输入流
     */
    public static InputStream getStringStream(String sInputString) {
        if (sInputString != null && !sInputString.trim().equals("")) {
            try {
                ByteArrayInputStream tInputStringStream = new ByteArrayInputStream(sInputString.getBytes("UTF-8"));
                return tInputStringStream;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }


    public static byte[] bitmap2Bytes(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }
}
