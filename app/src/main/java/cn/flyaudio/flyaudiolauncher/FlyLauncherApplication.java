package cn.flyaudio.flyaudiolauncher;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpHeaders;

import org.greenrobot.eventbus.EventBus;

import cn.flyaudio.flyaudiolauncher.amap.AMapNaviActivity;
import cn.flyaudio.flyaudiolauncher.utils.AppManager;
import cn.flyaudio.flycodelibrary.utils.AppUtils;

/**
 * @author weifule
 * @date 19-7-24
 * Email: fulewei@foxmail.com
 * Description:
 */
public class FlyLauncherApplication extends Application {

    private static Context context;

    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        registerActivityLifecycleCallbacks(activityLifecycleCallbacks);
   //    MultiDex.install(this);
        AppUtils.init(this);
        OkGo.getInstance().init(this);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.put("Accept","*/*");
        //httpHeaders.put("X-Access-Token","d2cb9f0025aee3cfd7d8ea");
        //当前token值是根据智慧飞歌登陆账号后生成，当前使用的是测试token,实际中需要注册登陆账号后生成
        //需要安装指定版本智慧飞歌，然后用手机号码注册账号，然后用账号和密码生成token
        httpHeaders.put("X-Access-Token","9e473707241785f0b38a86");
        OkGo.getInstance().addCommonHeaders(httpHeaders);
    }

    /**
     * activity生命周期监听
     */
    private ActivityLifecycleCallbacks activityLifecycleCallbacks = new ActivityLifecycleCallbacks() {

        @Override
        public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle bundle) {
            AppManager.getAppManager().addActivity(activity);
            if (activity.getClass() == AMapNaviActivity.class) {
                EventMessage msg = new EventMessage("onActivityCreated","AMapNaviActivity");
                EventBus.getDefault().post(msg);
            }
        }

        @Override
        public void onActivityStarted(@NonNull Activity activity) {

        }

        @Override
        public void onActivityResumed(@NonNull Activity activity) {

        }

        @Override
        public void onActivityPaused(@NonNull Activity activity) {

        }

        @Override
        public void onActivityStopped(@NonNull Activity activity) {

        }

        @Override
        public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle bundle) {

        }

        @Override
        public void onActivityDestroyed(@NonNull Activity activity) {
            AppManager.getAppManager().popActivity(activity);
            if (activity.getClass() == AMapNaviActivity.class) {
                EventMessage msg = new EventMessage("onActivityDestroyed","AMapNaviActivity");
                EventBus.getDefault().post(msg);
            }
        }
    };

    private static boolean isDisplayReady;
    private static boolean isPresentationReady;
    private static boolean isNaviModel;

    public static boolean isDisplayReady() {
        return isDisplayReady;
    }

    public static void setDisplayReady(boolean isDisplayReady) {
        FlyLauncherApplication.isDisplayReady = isDisplayReady;
    }

    public static boolean isPresentationReady() {
        return isPresentationReady;
    }

    public static void setPresentationReady(boolean isPresentationReady) {
        FlyLauncherApplication.isPresentationReady = isPresentationReady;
    }

    public static boolean isNaviModel() {
        return isNaviModel;
    }

    public static void setNaviModel(boolean isNaviModel) {
        FlyLauncherApplication.isNaviModel = isNaviModel;
    }
}
