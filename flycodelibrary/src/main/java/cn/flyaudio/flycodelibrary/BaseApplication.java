package cn.flyaudio.flycodelibrary;

import android.app.Application;

import cn.flyaudio.flycodelibrary.utils.AppUtils;

/**
 * @author newtrekWang
 * @fileName BaseApplication
 * @createDate 2018/11/13 10:13
 * @email 408030208@qq.com
 * @desc 基础的Application类，做一些基础的初始化工作
 */
public abstract class BaseApplication extends Application {
    /**
     * Application单例
     */
    private static Application instance;


    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        // 初始化工具
        AppUtils.init(this);
    }

    /**
     *  提供Application单例
     * @return
     */
    public static Application getInstance() {
        return instance;
    }

}
