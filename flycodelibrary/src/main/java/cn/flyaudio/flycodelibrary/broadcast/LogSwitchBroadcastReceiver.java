package cn.flyaudio.flycodelibrary.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import cn.flyaudio.flycodelibrary.net.OkGoUtils;
import cn.flyaudio.flycodelibrary.utils.L;

/**
 * @author newtrekWang
 * @fileName LogSwitchBroadcastReceiver
 * @createDate 2018/12/3 17:43
 * @email 408030208@qq.com
 * @desc log开关广播接收器
 */
public class LogSwitchBroadcastReceiver extends BroadcastReceiver {
    /**
     * 键名
     */
    public static final String  LOG_OPEN = "openLog";

    @Override
    public void onReceive(Context context, Intent intent) {
        // 是否开启log打印，默认值为false
        boolean logOpen =  intent.getBooleanExtra(LOG_OPEN,false);
        if (logOpen){
            L.setMode(L.DEBUG);
            OkGoUtils.getInstance().setShowLog(true);
        }else{
            L.setMode(L.RELEASE);
            OkGoUtils.getInstance().setShowLog(false);
        }
    }
}
