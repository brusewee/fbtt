package fbtt.com.fbtt.constants;

/**
 * Created by Administrator on 2016/4/13.
 */
public class Constants {
    public static final String UTF8 = "UTF-8";//字符编码
    public static final boolean ISTEST = true;//是否为测试环境
    public static final int HANDLER_RL_START = 1;//handler判断值，下载图片成功返回值
    public static final String version = "1.0.0";
    public static final String version_ = "100";
    public static final boolean is28 = false;
    public static final String ClientHTTP2 = "http://115.29.245.28:42111/tz" + version_ + "/";//28测试
    public static final String ClientHTTP1 = "http://218.213.215.30:9200/";//dev测试服务器
    public static final String ClientHTTP = is28 ? ClientHTTP2 : ClientHTTP1;//28测试
    public static final String HOST_ONLINE_HTTPS = "https://d5ds88.cgtz.com/tz" + version_ + "/";//线上服务器
    public static final String order_from = "accessFrom=android_phone&";//向服务器发送版請求來源
    //+ "version=" + version +"&"
    /**
     * 保存内容的key值
     **/

    public static final int BOOLEAN = 1;
    public static final int FLOAT = 2;
    public static final int INT = 3;
    public static final int LONG = 4;
    public static final int STRING = 5;
    public static final int STRINGSET = 6;
    public static final String LOCATION_LIST="api/area/list.json";
    public static final int LOCATION_LIST_FLAGE=100;
    public static final int LOCATION_LIST_FLAGE_ICON=101;
    public static final String CATEGORY_LIST="api/category/list.json";
    public static final String NEWS_LIST="api/news/list.json";







}
