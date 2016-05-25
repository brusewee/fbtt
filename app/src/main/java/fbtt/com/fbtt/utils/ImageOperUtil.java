package fbtt.com.fbtt.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Administrator on 2016/4/13.
 */
public class ImageOperUtil {

    public static Bitmap readFileAndOptions(String filePath) {
        BitmapFactory.Options opt = new BitmapFactory.Options();

        opt.inPreferredConfig = Bitmap.Config.RGB_565;

        opt.inPurgeable = true;

        opt.inInputShareable = true;

        Bitmap bitmap = null;
        try {

            bitmap = BitmapFactory.decodeFile(filePath, opt);
        } catch (Exception e) {

        }
        return bitmap;
    }

    /**
     * <p>防止bitmap溢出,通过资源ID,返回bitmapDrawable</p>
     *
     * @param context
     * @param resId
     * @return
     */
    public static BitmapDrawable readResImgAndOptions(Context context, int resId) {
        BitmapFactory.Options opt = new BitmapFactory.Options();

        opt.inPreferredConfig = Bitmap.Config.RGB_565;

        opt.inPurgeable = true;

        opt.inInputShareable = true;

        InputStream is = context.getResources().openRawResource(resId);

        Bitmap bitmap = BitmapFactory.decodeStream(is, null, opt);

        try {
            is.close();
        } catch (IOException e) {
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                }
            }
        }

        return new BitmapDrawable(context.getResources(), bitmap);
    }
}
