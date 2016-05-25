package fbtt.com.fbtt.utils;

/**
 * Created by Administrator on 2016/4/13.
 */

import android.os.AsyncTask;
import android.os.Handler;

import java.util.Map;

import fbtt.com.fbtt.constants.Constants;


/**
 * 自定义Task线程
 * Created by Administrator on 2014/11/25.
 */

public class CustomTask extends AsyncTask<String, Void, String> {
    public static final String TAG = "CustomTask1";
    private Handler handler;//回调Handler
    private String url_;//访问路径
    private boolean flag;//判断get或者post方法  get为false,post为true
    private String content;//post传递的参数
    private String encode;//编码
    private int handler_what;//回调handler的 what判断值


    /**
     * @param handler
     * @param handler_what handler 判断值
     * @param url_         接口
     * @param isPost       判断是否为post访问   true为post
     * @param maps         参数集合
     * @param encode       文字编码
     */
    public CustomTask(Handler handler, int handler_what, String url_,
                      boolean isPost, Map<String, String> maps, String encode) {
        this.handler = handler;
        this.handler_what = handler_what;
        this.flag = isPost;
        this.url_ = (Constants.ISTEST ? Constants.ClientHTTP : Constants.HOST_ONLINE_HTTPS) + url_;
        this.encode = encode;
        if (flag) {
            content = HttpUtils.getRequestData(maps, encode).toString();
        }
        LogUtils.i(TAG, "curl -d '" + content + "' '" + this.url_ + "'");
    }

    /**
     * @param handler
     * @param handler_what handler 判断值
     * @param url_         接口
     * @param isPost       判断是否为post访问   true为post
     * @param maps         参数集合
     * @param encode       文字编码
     * @param if_ping      判断是否拼接路径  true为拼接
     */
    public CustomTask(Handler handler, int handler_what, String url_,
                      boolean isPost, Map<String, String> maps, String encode, boolean if_ping) {
        this.handler = handler;
        this.handler_what = handler_what;
        this.flag = isPost;
        if (if_ping) {
            this.url_ = (Constants.ISTEST ? Constants.ClientHTTP : Constants.HOST_ONLINE_HTTPS) + url_;
        } else {
            this.url_ = url_;
        }
        this.encode = encode;
        if (flag) {
            content = HttpUtils.getRequestData(maps, encode).toString();
        }
        DebugUtils.e("ServerCustomTask", "url: " + this.url_ + " content: " + content);
    }

    /**
     * @param handler
     * @param handler_what handler 判断值
     * @param url_         接口
     * @param isPost       判断是否为post访问   true为post
     * @param maps         参数集合
     * @param if_ping      判断是否拼接路径  true为拼接
     */
    public CustomTask(Handler handler, int handler_what, String url_,
                      boolean isPost, Map<String, String> maps, boolean if_ping) {
        this.handler = handler;
        this.handler_what = handler_what;
        this.flag = isPost;
        if (if_ping) {
            this.url_ = (Constants.ISTEST ? Constants.ClientHTTP : Constants.HOST_ONLINE_HTTPS) + url_;
        } else {
            this.url_ = url_;
        }
        if (flag) {
            content = HttpUtils.getRequestData(maps, encode).toString();
        }
        DebugUtils.e("ServerCustomTask", "url: " + this.url_ + " content: " + content);
    }



    @Override
    protected String doInBackground(String... params) {
        if (flag) {
            if (Constants.ISTEST) {
                return HttpUtils.HttpPost(url_, content, encode);
            } else {
                return HttpUtils.HttpsPost(url_, content, encode);
            }
        } else {
            if (Constants.ISTEST) {
                return HttpUtils.HttpGet(url_, encode);
            } else {
                return HttpUtils.HttpsGet(url_, encode);
            }
        }
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        handler.sendMessage(handler.obtainMessage(handler_what, s));
    }
}
