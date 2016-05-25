package fbtt.com.fbtt.utils;

import android.util.Log;

import fbtt.com.fbtt.constants.Constants;


/**
 * Created by Administrator on 2016/4/13.
 */
public class DebugUtils {

    public static void i(String TAG,String message){
        if(Constants.ISTEST){
            Log.i(TAG, message);
        }
    }

    public static void e(String TAG,String message){
        if(Constants.ISTEST){
            Log.e(TAG,message);
        }
    }
}
