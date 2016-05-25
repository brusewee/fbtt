package fbtt.com.fbtt.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Set;

import fbtt.com.fbtt.constants.Constants;


/**
 * Created by Administrator on 2016/4/13.
 */
public class UtilitySharedpreferences {

    private SharedPreferences.Editor spe;
    private Context context;
    //指定sharepreferences文件的名称
    private String configxml = "";
    private SharedPreferences sp;

    public UtilitySharedpreferences(Context context, String configxml) {
        this.context = context;
        this.configxml = configxml;
        sp = context.getSharedPreferences(configxml, Context.MODE_PRIVATE);
        spe = sp.edit();
    }


    /**
     * 保存键值对到xml文件中
     *
     * @param type  类型判断 1 boolean 2 float 3 int 4 long 5 string 6 stringset
     * @param key
     * @param value
     */
    public void putMsg(int type, String key, Object value) {
        switch (type) {
            case Constants.BOOLEAN:
                spe.putBoolean(key, (Boolean) value);
                break;
            case Constants.FLOAT:
                spe.putFloat(key, (Float) value);
                break;
            case Constants.INT:
                spe.putInt(key, (Integer) value);
                break;
            case Constants.LONG:
                spe.putLong(key, (Long) value);
                break;
            case Constants.STRING:
                spe.putString(key, (String) value);
                break;
            case Constants.STRINGSET:
                spe.putStringSet(key, (Set<String>) value);
                break;
        }
        spe.commit();
    }

    /**
     * 根据key值，获取xml中的内容
     *
     * @param type
     * @param key
     */
    public Object getMsg(int type, String key, Object value) {
        Object obj = null;
        switch (type) {
            case Constants.BOOLEAN:
                obj = sp.getBoolean(key, (Boolean) value);
                break;
            case Constants.FLOAT:
                obj = sp.getFloat(key, (Float) value);
                break;
            case Constants.INT:
                obj = sp.getInt(key, (Integer) value);
                break;
            case Constants.LONG:
                obj = sp.getLong(key, (Long) value);
                break;
            case Constants.STRING:
                obj = sp.getString(key, (String) value);
                break;
            case Constants.STRINGSET:
                obj = sp.getStringSet(key, (Set<String>) value);
                break;
        }
        return obj;
    }



}
