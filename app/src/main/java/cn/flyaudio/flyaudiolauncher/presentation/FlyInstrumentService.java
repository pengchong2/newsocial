package cn.flyaudio.flyaudiolauncher.presentation;

import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;

import cn.flyaudio.flyaudiolauncher.FlyLauncherApplication;
import cn.flyaudio.flyaudiolauncher.amap.AMapNaviActivity;
import cn.flyaudio.flyaudiolauncher.utils.Constants;

import static cn.flyaudio.flyaudiolauncher.AppConfig.DEBUG;

/**
 * @author weifule
 * @date 19-7-8
 * Email: fulewei@foxmail.com
 * Description:
 */
public class FlyInstrumentService extends Service {

    private static final String TAG = "FlyInstrumentService";
    private Messenger messenger;
    private FlyInstrumentClusterController mController;


    @SuppressLint({"HandlerLeak", "NewApi"})
    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            if (DEBUG){
                Log.d(TAG,"handleMessage " + msg.what);
            }
           messenger = msg.replyTo;
           switch (msg.what){
               case Constants.MSG_UPDATEPERSENTATION:
                    updatePersentation((Bitmap) msg.obj);
                    break;
               case Constants.MSG_INIT:
                   if (mController != null) {
                       int displayId = mController.getDisplayId();
                       if (displayId != -1) {
                           sendMessageToActivity(Constants.MSG_DISPLAYADDED, null);
                       }
                   }
                   break;
               case Constants.MSG_START_NAVI:
                   if (msg.obj != null){
                       int displayId = mController.getDisplayId();
                       if (DEBUG){
                           Log.d(TAG," handleMessage MSG_START_NAVI displayId : " + displayId);
                       }
                       if (displayId != -1) {
                           ActivityOptions options = ActivityOptions.makeBasic();
                           options.setLaunchDisplayId(displayId);
                           Intent intent = new Intent(FlyInstrumentService.this, AMapNaviActivity.class);
                           intent.putExtra("data", (Bundle) msg.obj);
                           intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                           //startActivity(intent);
                           startActivity(intent, options.toBundle());
                       }
                   }
                   break;
               case Constants.MSG_SHOW_PRESENT:
                   if (mController != null) {
                       mController.showPresentation();
                       FlyLauncherApplication.setPresentationReady(true);
                       sendMessageToActivity(Constants.MSG_PERSENT_SHOW, null);
                   }
                   break;
               case Constants.MSG_DISS_PRESENT:
                   if (mController != null) {
                       mController.disPresentation();
                       FlyLauncherApplication.setPresentationReady(false);
                   }
                   break;
               case Constants.MSG_STOP_SERVER:
                   stopSelf();
                   break;
                default:
                    break;
           }
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        if (DEBUG){
            Log.d(TAG,"onCreate : ");
        }
        messenger = new Messenger(handler);
        mController = new FlyInstrumentClusterController(this, listener);

        if (mController.getDisplayId() != -1) {
            FlyLauncherApplication.setDisplayReady(true);
            if (!FlyLauncherApplication.isNaviModel()) {
                mController.showPresentation();
                FlyLauncherApplication.setPresentationReady(true);
                sendMessageToActivity(Constants.MSG_PERSENT_SHOW, null);
            }
        }
    }

    private void sendMessageToActivity(int msgWhat, Object data) {
        Message msg = Message.obtain();
        msg.what = msgWhat;
        msg.obj = data;

        try {
            messenger.send(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (mController != null) {
            mController.onDestroy();
            mController = null;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return messenger.getBinder();
    }

    public void updatePersentation(Bitmap bitmap){
        if (DEBUG){
            Log.d(TAG,"updatePersentation " + bitmap);
        }
        if (mController != null){
            mController.updatePersentation(bitmap);
        }
    }

    private final FlyInstrumentClusterController.OnDisplayChangedListener listener = new FlyInstrumentClusterController.OnDisplayChangedListener() {

        @Override
        public void onDisplayAdded(int displayId) {
            if (DEBUG){
                Log.d(TAG,"onDisplayAdded ");
            }
            FlyLauncherApplication.setDisplayReady(true);
            if (mController != null) {
                mController.showPresentation();
                FlyLauncherApplication.setPresentationReady(true);
            }
        }

        @Override
        public void onDisplayRemoved(int displayId) {
            if (DEBUG){
                Log.d(TAG,"onDisplayRemoved ");
            }
            FlyLauncherApplication.setDisplayReady(false);
            if (mController != null) {
                mController.showPresentation();
                FlyLauncherApplication.setPresentationReady(false);
            }
        }

        @Override
        public void onDisplayChanged(int displayId) {
            if (DEBUG){
                Log.d(TAG,"onDisplayChanged ");
            }
            sendMessageToActivity(Constants.MSG_DISPLAYCHANGED, null);
        }
    };

}
