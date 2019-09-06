package cn.flyaudio.flyaudiolauncher.widget;

import android.appwidget.AppWidgetHost;
import android.appwidget.AppWidgetHostView;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProviderInfo;
import android.content.ComponentName;
import android.content.Context;
import android.util.Log;
import android.util.Pair;

import cn.flyaudio.flyaudiolauncher.utils.FileUtils;

import static cn.flyaudio.flyaudiolauncher.AppConfig.DEBUG;

/**
 * @author weifule
 * @date 19-7-24
 * Email: fulewei@foxmail.com
 * Description:
 */
public class WidgetHostViewLoader {

    private static final String TAG = "WidgetHostViewLoader";

    private int APPWIDGET_HOST_ID;
    private AppWidgetManager mAppWidgetManager;
    private AppWidgetHost mAppWidgetHost;

    private Context context;

    public WidgetHostViewLoader(Context contex, int hostId) {
        if (DEBUG){
            Log.d(TAG , " WidgetHostViewLoader  ");
            FileUtils.writer(FileUtils.path,"WidgetHostViewLoader");
        }
        this.context = contex;
        this.APPWIDGET_HOST_ID = hostId;
        initWidgetHost();
    }

    public AppWidgetHost getAppWidgetHost(){
        return mAppWidgetHost;
    }

    public void onStart(){
        mAppWidgetHost.startListening();
    }

    public void onResume() {
        mAppWidgetHost.startListening();
    }

    public Pair<Integer,AppWidgetProviderInfo> buildAppWidgetInfo(Context context, String packageName, String className){
        if (DEBUG){
            Log.d(TAG , " packageName : " + packageName + " className : " + className);
            FileUtils.writer(FileUtils.path," packageName : " + packageName + " className : " + className);
        }
        ComponentName componentName = new ComponentName(packageName, className);
        int widgetId = mAppWidgetHost.allocateAppWidgetId();
        mAppWidgetManager.bindAppWidgetIdIfAllowed(widgetId, componentName);
        AppWidgetProviderInfo widgetInfo = mAppWidgetManager.getAppWidgetInfo(widgetId);
        Pair<Integer,AppWidgetProviderInfo> providerInfoPair = new Pair<>(widgetId, widgetInfo);
        return providerInfoPair;
    }

    private void initWidgetHost() {
        mAppWidgetManager = AppWidgetManager.getInstance(context.getApplicationContext());
        mAppWidgetHost = new AppWidgetHost(context.getApplicationContext(), APPWIDGET_HOST_ID);
        //监听widget变化事件，没有这句widget不能刷新
        mAppWidgetHost.startListening();
    }


    public AppWidgetHostView preloadWidget(int appWidgetId){
        if (DEBUG){
            Log.d(TAG , " preloadWidget  appWidgetId : " + appWidgetId);
            FileUtils.writer(FileUtils.path," preloadWidget  appWidgetId : " + appWidgetId);
        }
        if (context != null){
            AppWidgetProviderInfo widgetInfo = AppWidgetManager.getInstance(context.getApplicationContext()).getAppWidgetInfo(appWidgetId);
            if (DEBUG){
                Log.d(TAG , " preloadWidget  widgetInfo : " + widgetInfo);
                FileUtils.writer(FileUtils.path," preloadWidget  widgetInfo : " + widgetInfo.toString());
            }
            //创建widget view
            AppWidgetHostView widgetView = mAppWidgetHost.createView(context.getApplicationContext(), appWidgetId, widgetInfo);
            if (DEBUG){
                Log.d(TAG , " preloadWidget widgetView : " + appWidgetId);
                FileUtils.writer(FileUtils.path," preloadWidget widgetView : " + appWidgetId);
            }
            return widgetView;
        }
        return null;
    }

    public void releaseWidget(){
        if (mAppWidgetHost != null){
            //退出时停止监听
            mAppWidgetHost.stopListening();
        }
    }


}
