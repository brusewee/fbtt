package fbtt.com.fbtt.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.text.TextUtils;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;


/**
 * Created by Administrator on 2016/4/13.
 */
public class UtilityUtils {

    /**
     * 判断是否有网络连接
     *
     * @param context
     * @return
     */
    public static boolean isNewWorkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager =
                    (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (mConnectivityManager != null) {
                NetworkInfo mobileInfo = mConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
                NetworkInfo wifiInfo = mConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
                if ((mobileInfo == null || !mobileInfo.isAvailable()) && (wifiInfo == null || !wifiInfo.isAvailable())) {
                    return false;
                }
            } else {
                return false;
            }
        }
        return true;
    }

    public static void makeText_short(Context context, String msg) {
        if (!TextUtils.isEmpty(msg) && context != null) {
            Toast.makeText(context, TextUtils.isEmpty(msg) ? "" : msg, Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Toast提示
     *
     * @param context
     * @param msg
     */
    public static void makeText(Context context, String msg) {
        if (!TextUtils.isEmpty(msg) && context != null) {
            Toast.makeText(context, TextUtils.isEmpty(msg) ? "" : msg, Toast.LENGTH_LONG).show();
        }
    }

    /**
     * @param activity 支持页面
     * @param obj      解析数据图像,解析为Json对象
     * @return
     * @throws JSONException
     */
    public static JSONObject parseObj(Context activity, Object obj) throws JSONException {
        return new JSONObject(obj.toString());
    }





    /**
     * 判断SD卡是否存在
     *
     * @return
     */
    public static boolean isHaveSD() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * 获取错误信息
     *
     * @param arg1
     * @return
     */
    public static String getErrorInfo(Throwable arg1) {
        Writer writer = new StringWriter();
        PrintWriter pw = new PrintWriter(writer);
        arg1.printStackTrace(pw);
        pw.close();
        String error = writer.toString();
        return error;
    }

    /**
     * 获取版本号
     *
     * @return 当前应用的版本号
     */
    public static String getVersion(Context context) {
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            String version = info.versionName;
            return version;
        } catch (Exception e) {
            e.printStackTrace();
            return "0";
        }
    }

    /**
     * 手机号添加星号
     *
     * @param str
     * @return
     */
    public static String subStarPhone(String str) {
        if (TextUtils.isEmpty(str) && str.length() < 11) {
            return "";
        } else {
            return str.substring(0, 3) + "****" + str.substring(7);
        }
    }





}
