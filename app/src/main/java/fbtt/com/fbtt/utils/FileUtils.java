package fbtt.com.fbtt.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.CharArrayWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.Writer;

/**
 * 文件管理类    在以后的开发中也可以使用这个工具类，提高代码的利用性
 * 只要是对SD卡的操作
 * 1、获取SD卡路径   getSDPATH
 * 2、在SD卡上根据传入的目录名创建目录  createSDDir
 * 3、在创建上目录后可以在该目录上创建文件    createSDFile
 * 4、检测文件是否存在  isFileExist
 * 5、将一个InputStream写入到SD卡中   write2SDFromInput
 * 6、将一个字符流写入到SD卡 write2SDFromWrite
 * 注：如果要写入SD卡，只要调用write2SDFromInput函数即可
 *
 * @author Administrator
 */
public class FileUtils {
    private static String SDPATH;
    private static final String TAG = "FileUtils";

    public FileUtils() {
        //得到当前设备外部存储设备的目录
        SDPATH = Environment.getExternalStorageDirectory() + File.separator;
    }

    /**
     * 获取当前SD卡的根目录
     *
     * @return
     */
    public String getSDPATH() {
        return SDPATH;
    }

    /**
     * SD卡上创建目录
     */
    public File createSDDir(String dirName) {
        File dir = new File(SDPATH + dirName);
        Log.i(TAG, "createSDDir " + SDPATH + dirName);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return dir;
    }

    /**
     * SD卡上创建文件
     */
    public File createSDFile(String fileName) throws IOException {
        File file = new File(SDPATH + fileName);
        Log.i(TAG, "createSDFile " + SDPATH + fileName);
        file.createNewFile();
        return file;
    }


    /**
     * 判断SD卡上的文件是否存在
     */
    public boolean isFileExist(String fileName) {
        File file = new File(SDPATH + fileName);
        return file.exists();
    }

    /**
     * 将一个InputStream字节流写入到SD卡中
     */
    public File write2SDFromInput(String Path, String FileName, InputStream input) {
        File file = null;
        OutputStream output = null;   //创建一个写入字节流对象
        try {
            createSDDir(Path);    //根据传入的路径创建目录
            file = createSDFile(Path + FileName); //根据传入的文件名创建
            output = new FileOutputStream(file);
            byte buffer[] = new byte[4 * 1024];   //每次读取4K
            int num = 0;      //需要根据读取的字节大小写入文件
            while ((num = (input.read(buffer))) != -1) {
                output.write(buffer, 0, num);
            }
            output.flush();  //清空缓存
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (output != null)
                    output.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return file;
    }

    /**
     * 把传入的字符流写入到SD卡中
     *
     * @param Path
     * @param FileName
     * @param input
     * @return
     */
    public File write2SDFromWrite(String Path, String FileName, BufferedReader input) {
        File file = null;
        FileWriter output = null;   //创建一个写入字符流对象
        BufferedWriter bufw = null;
        try {
            createSDDir(Path);    //根据传入的路径创建目录
            file = createSDFile(Path + FileName); //根据传入的文件名创建
            output = new FileWriter(file);
            bufw = new BufferedWriter(output);
            String line = null;
            while ((line = (input.readLine())) != null) {
                bufw.write(line);
                bufw.newLine();
            }
            bufw.flush();  //清空缓存
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufw != null)
                    bufw.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return file;
    }

    /**
     * 从文本文件对象中读取内容并转换为字符数组
     *
     * @param file File 对象
     * @return 读到的字符数据
     */
    public static char[] readChars(File file) {
        CharArrayWriter caw = new CharArrayWriter();
        try {
            Reader fr = new FileReader(file);
            Reader in = new BufferedReader(fr);
            int count = 0;
            char[] buf = new char[16384];
            while ((count = in.read(buf)) != -1) {
                if (count > 0) caw.write(buf, 0, count);
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return caw.toCharArray();
    }

    /**
     * 从字符串对象中读取内容并转换为字符数组
     *
     * @param string 在读的String数据
     * @return 字符数组
     */
    public static char[] readChars(String string) {
        CharArrayWriter caw = new CharArrayWriter();
        try {
            Reader sr = new StringReader(string.trim());
            Reader in = new BufferedReader(sr);
            int count = 0;
            char[] buf = new char[16384];
            while ((count = in.read(buf)) != -1) {
                if (count > 0) caw.write(buf, 0, count);
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return caw.toCharArray();
    }

    /**
     * 从二进制文件对象中读取内容并转换为字节数组
     *
     * @param file 要读取的File对象
     * @return 读取后的字节数据
     */
    public static byte[] readBytes(File file) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            InputStream fis = new FileInputStream(file);
            InputStream is = new BufferedInputStream(fis);
            int count = 0;
            byte[] buf = new byte[16384];
            while ((count = is.read(buf)) != -1) {
                if (count > 0) baos.write(buf, 0, count);
            }
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return baos.toByteArray();
    }

    /**
     * 写字节数组内容到二进制文件
     *
     * @param file File对象
     * @param data 输出字节数组
     */
    public static void writeBytes(File file, byte[] data) {
        try {
            OutputStream fos = new FileOutputStream(file);
            OutputStream os = new BufferedOutputStream(fos);
            os.write(data);
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 写字符数组内容到文本文件
     *
     * @param file File对象
     * @param data 输出字节数组
     */
    public static void writeChars(File file, char[] data) {
        try {
            Writer fos = new FileWriter(file);
            Writer os = new BufferedWriter(fos);
            os.write(data);
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Environment.getDataDirectory() +path 读取文件
     *
     * @see #localWriter(Bitmap, String, String)
     */
    public static Bitmap localReader(String name, String path) {
        File fileRe = null;
        try {
            File dataDirectory = Environment.getExternalStorageDirectory();
            if (dataDirectory.exists()) {
                fileRe = new File(dataDirectory.getPath() + File.separator + path + File.separator + name);
                // 文件不存在
                if (fileRe == null || !fileRe.exists()) {
                    return null;
                } else {
                    return BitmapFactory.decodeFile(fileRe.getPath());
                }
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    public static Bitmap localReaderByPath(String name, String path,Context context) {
        File fileRe = null;


        try {
            fileRe = new File(path + File.separator + MD5Util.md5(name));
            // 文件不存在
            if (fileRe == null || !fileRe.exists()) {
                return null;
            } else {
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeFile(fileRe.getPath(), options);
                int width = options.outWidth;
                int height = options.outHeight;
                int inSampleSize = 1;
                int size = width /context.getResources().getDisplayMetrics().widthPixels;
                if (size > 0) {
                    inSampleSize = size;
                }
                Log.i("AsyncImageLoader", "height is: " + height + " width is: " + width + "sampleSize: " + inSampleSize);
                options.inPurgeable = true;
                options.inInputShareable = true;
                options.inSampleSize = inSampleSize;
                options.inJustDecodeBounds = false;
                return BitmapFactory.decodeFile(fileRe.getPath(),options);
            }
        } catch (Exception e) {
            return null;
        }

    }

    /**
     * Environment.getDataDirectory()
     *
     * @param bm
     * @param name
     * @param path
     * @return
     * @see #localReader(String, String)
     */
    public static boolean localWriter(Bitmap bm, String name, String path) {
        File dataDirectory = Environment.getExternalStorageDirectory();
        try {
            if (dataDirectory.exists()) {
                String s = dataDirectory.getPath() + File.separator + path + File.separator;
                File write = new File(s);
                if (!write.exists()) {
                    if (write.mkdirs()) {
                        FileOutputStream fileOutputStream = new FileOutputStream(new File(s + MD5Util.md5(name)));
                        fileOutputStream.write(StreamUtils.bitmap2Bytes(bm));
                        fileOutputStream.close();
                    }
                } else {
                    FileOutputStream fileOutputStream = new FileOutputStream(new File(s + MD5Util.md5(name)));
                    fileOutputStream.write(StreamUtils.bitmap2Bytes(bm));
                    fileOutputStream.close();
                }
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * Environment.getDataDirectory()
     *
     * @param bm
     * @param name
     * @param path
     * @return
     * @see #localReader(String, String)
     */
    public static boolean localWriterByPath(Bitmap bm, String name, String path) {
        try {
            String s = path + File.separator;
            File write = new File(s);
            if (!write.exists()) {
                if (write.mkdirs()) {
                    FileOutputStream fileOutputStream = new FileOutputStream(new File(s + MD5Util.md5(name)));
                    fileOutputStream.write(StreamUtils.bitmap2Bytes(bm));
                    fileOutputStream.close();
                }
            } else {
                FileOutputStream fileOutputStream = new FileOutputStream(new File(s + MD5Util.md5(name)));
                fileOutputStream.write(StreamUtils.bitmap2Bytes(bm));
                fileOutputStream.close();
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
