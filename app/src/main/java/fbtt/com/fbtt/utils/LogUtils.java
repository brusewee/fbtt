package fbtt.com.fbtt.utils;

import android.util.Log;

import fbtt.com.fbtt.constants.Constants;


/**
 * Created by Administrator on 2016/4/13.
 */
public class LogUtils {

    public static void i(String tag,String msg){
        if(Constants.ISTEST){
            Log.i(tag, msg);
        }
    }
    public static void d(String tag,String msg){
        if(Constants.ISTEST){
            Log.d(tag, msg);
        }
    }
    public static void e(String tag,String msg){
        if(Constants.ISTEST){
            Log.e(tag, msg);
        }
    }
    public static void v(String tag,String msg){
        if(Constants.ISTEST){
            Log.v(tag, msg);
        }
    }
    public static void w(String tag,String msg){
        if(Constants.ISTEST){
            Log.w(tag,msg);
        }
    }
}
