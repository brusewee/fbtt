package fbtt.com.fbtt;

import android.app.Activity;
import android.app.Application;

import java.util.ArrayList;

import fbtt.com.fbtt.utils.LogUtils;

/**
 * Created by Administrator on 2016/5/25.
 */
public class FBApplication extends Application {

    public static final String TAG="FBApplication";

    private static FBApplication instance;
    public static ArrayList<Activity> activityLists = new ArrayList<Activity>();

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtils.i(TAG, "create app");
        instance = this;

    }

    /**
     * 添加存储Activity
     * @param activity
     */
    public synchronized static void register(Activity activity){
        for(int i = activityLists.size() -1; i>=0; i--){
            Activity ac = activityLists.get(i);
            if(ac == activity){
                activityLists.remove(ac);
                if(!ac.isFinishing()){
                    ac.finish();
                }
                break;
            }
        }
        activityLists.add(activity);
    }


    /**
     * 根据类名获取所对应Activity
     * @param name
     * @return
     */
    public Activity getActivityByName(String name){
        for(int i = activityLists.size() - 1; i >= 0; i--){
            Activity ac = activityLists.get(i);
            if(ac.isFinishing()){
                continue;
            }
            if(ac.getClass().getName().indexOf(name) >= 0){
                return ac;
            }
        }
        return null;
    }



    /**
     * 杀死Activity
     * @param activity
     */
    public synchronized static void unRegister(Activity activity){
        if(activity != null && activityLists != null  && activityLists.size() != 0){
            activityLists.remove(activity);
            if(!activity.isFinishing()){
                activity.finish();
            }
        }
    }

    /**
     * 杀死所有Activity
     */
    public static void finishAllActivity(){
        if(activityLists != null  && activityLists.size() != 0){
            for(Activity ac:activityLists){
                if(!ac.isFinishing()){
                    ac.finish();
                }
            }
        }
    }



}
