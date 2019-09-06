package cn.flyaudio.flyaudiolauncher.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author weifule
 * @date 19-8-7
 * Email: fulewei@foxmail.com
 * Description:
 */
public class DateUtils {

    public static String getDate(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        return simpleDateFormat.format(date);
    }

}
