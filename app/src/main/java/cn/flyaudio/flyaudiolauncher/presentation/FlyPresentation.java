package cn.flyaudio.flyaudiolauncher.presentation;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;

import androidx.annotation.NonNull;

import com.amap.api.maps.AMap;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;

import cn.flyaudio.flyaudiolauncher.FlyLauncherApplication;
import cn.flyaudio.flyaudiolauncher.utils.Constants;

import static cn.flyaudio.flyaudiolauncher.AppConfig.DEBUG;

/**
 * @author weifule
 * @date 19-7-26
 * Email: fulewei@foxmail.com
 * Description:
 */
public class FlyPresentation {

    private static final String TAG = "FlyPresentation";

    private AMap aMap;
    private Context context;
    boolean mBound = false;
    private Messenger sender, receiver;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            if (DEBUG){
                Log.d(TAG,"handleMessage " + msg.what);
            }
            switch (msg.what) {
                case Constants.MSG_DISPLAYADDED:
                    break;
                case Constants.MSG_DISPLAYREMOVED:
                    break;
                case Constants.MSG_DISPLAYCHANGED:
                    break;
                case Constants.MSG_PERSENT_SHOW:
                    updatePersent();
                case Constants.MSG_PERSENT_DISS:
                    break;
                default:
                    break;
            }
        }
    };

    public FlyPresentation(Context context, AMap aMap) {
        if (DEBUG){
            Log.d(TAG,"FlyPresentation");
        }
        this.context = context;
        this.aMap = aMap;

        Intent intent = new Intent(context, FlyInstrumentService.class);
        context.bindService(intent, mConnection, Context.BIND_AUTO_CREATE);

        String serviceName = "cn.flyaudio.flyvirtualdisplay.VirtualDisplayService";
        Intent serverIntent = new Intent();
        ComponentName componentName = new ComponentName("cn.flyaudio.flyvirtualdisplay", serviceName);
        serverIntent.setComponent(componentName);
        context.startService(serverIntent);

        receiver = new Messenger(mHandler);
        startBackgroundThread();

        aMap.getMapScreenShot(new MyMapScreenShotListener());
        aMap.setOnCameraChangeListener(new MyOnCameraChangeListener());
    }

    private ServiceConnection mConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className, IBinder service) {
            if (DEBUG){
                Log.d(TAG,"onServiceConnected");
            }
            sender = new Messenger(service);
            mBound = true;
            sendMessageToService(Constants.MSG_INIT, null);
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            if (DEBUG){
                Log.d(TAG,"onServiceDisconnected");
            }
            sender = null;
            mBound = false;
        }
    };

    /**
     * 滑动地图的时候更新投射界面，暂时不需要
     */
    private class MyOnCameraChangeListener implements AMap.OnCameraChangeListener {

        @Override
        public void onCameraChange(CameraPosition cameraPosition) {
//            if (isStartScreenShot && isPersentReady) {
//                //mBackgroundHandler.sendEmptyMessage(1);
//            }
        }

        @Override
        public void onCameraChangeFinish(CameraPosition cameraPosition) {
            if (FlyLauncherApplication.isDisplayReady() && FlyLauncherApplication.isPresentationReady()) {
                mBackgroundHandler.sendEmptyMessage(1);
            }
        }
    }

    private class MyMapScreenShotListener implements AMap.OnMapScreenShotListener {

        @Override
        public void onMapScreenShot(Bitmap bitmap) {

        }

        @Override
        public void onMapScreenShot(Bitmap bitmap, int i) {
            if (DEBUG){
                Log.d(TAG,"onMapScreenShot bitmap : " + bitmap);
            }
            if(null == bitmap){
                return;
            }
            if (mBound){
                sendMessageToService(Constants.MSG_UPDATEPERSENTATION, bitmap);
            }
        }
    }

    public void releasePresentation(){
        if (mBound) {
            context.unbindService(mConnection);
            mBound = false;
        }

        if (sender != null){
            sendMessageToService(Constants.MSG_STOP_SERVER,null);
        }

        stopBackgroundThread();
    }

    private void sendMessageToService(int msgWhat, Object data) {
        Message msg = Message.obtain();
        msg.what = msgWhat;
        msg.obj = data;
        msg.replyTo = receiver;

        try {
            sender.send(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private HandlerThread mBackgroundThread;
    private Handler mBackgroundHandler;
    
    private void startBackgroundThread() {
        mBackgroundThread = new HandlerThread("ScreenShotBackground");
        mBackgroundThread.start();
        mBackgroundHandler = new Handler(mBackgroundThread.getLooper()){
            @Override
            public void handleMessage(@NonNull Message msg) {
                aMap.getMapScreenShot(new MyMapScreenShotListener());
            }
        };
    }

    /**
     * Stops the background thread and its {@link Handler}.
     */
    private void stopBackgroundThread() {
        mBackgroundThread.quitSafely();
        try {
            mBackgroundThread.join();
            mBackgroundThread = null;
            mBackgroundHandler = null;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void startNavi(){
        sendMessageToService(Constants.MSG_START_NAVI,null);
    }

    public void startNavi(LatLng startLatLng, LatLng endLatLng) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("startLatLng", startLatLng);
        bundle.putParcelable("endLatLng", endLatLng);
        sendMessageToService(Constants.MSG_START_NAVI, bundle);
    }

    public void showPresentation() {
        sendMessageToService(Constants.MSG_SHOW_PRESENT,null);
    }

    public void disPresentation() {
        sendMessageToService(Constants.MSG_DISS_PRESENT,null);
    }

    public void updatePersent() {
        if (DEBUG){
            Log.d(TAG,"updatePersent isDisplayReady : " + FlyLauncherApplication.isDisplayReady()
                    + " isPresentationReady : " + FlyLauncherApplication.isPresentationReady());
        }
        if (FlyLauncherApplication.isDisplayReady() && FlyLauncherApplication.isPresentationReady()) {
            mBackgroundHandler.sendEmptyMessageDelayed(1,5000);
        }
    }
}
