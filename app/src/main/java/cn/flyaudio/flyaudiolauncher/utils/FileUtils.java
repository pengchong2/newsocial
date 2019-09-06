package cn.flyaudio.flyaudiolauncher.utils;

import android.util.Log;

import java.io.FileWriter;
import java.io.IOException;

/**
 * @author weifule
 * @date 19-8-7
 * Email: fulewei@foxmail.com
 * Description:
 */
public class FileUtils {
    private static final String TAG = "FFileUtils";

    public static final String path = "/storage/emulated/0/FlyLog/" + "/logcat.txt";

    public static void writer(String fileName, String content) {
        try {
            String contentTime = DateUtils.getDate() + " " + content + "\r\n";
            // 打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
            FileWriter writer = new FileWriter(fileName, true);
            writer.write(contentTime);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            Log.d(TAG, " FileUtils writer IOException : " + e.getMessage());
        }
    }
}
