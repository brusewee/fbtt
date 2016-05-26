package fbtt.com.fbtt.utils;

import android.os.AsyncTask;
import android.os.Handler;

import fbtt.com.fbtt.constants.Constants;


/**
 * Created by Administrator on 2016/4/14.
 */
public class DownloadingTask extends AsyncTask<String,Void,Boolean> {

    private String fileDir;
    private String fileName;
    private String url_;
    private Handler mHandler;
    public DownloadingTask(String fileDir,String fileName,String url_,Handler mHandler){
        this.fileDir = fileDir;
        this.fileName = fileName;
        this.url_ = url_;
        this.mHandler =mHandler;
    }
    @Override
    protected Boolean doInBackground(String... params) {
        boolean downFlag = HttpUtils.downLoadingFile(fileDir,"/"+fileName,url_);
        return downFlag;
    }

    @Override
    protected void onPostExecute(Boolean result) {
        super.onPostExecute(result);
        mHandler.sendMessage(mHandler.obtainMessage(Constants.HANDLER_RL_START,result));
    }
}
