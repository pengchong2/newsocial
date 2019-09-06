package cn.flyaudio.flyaudiolauncher.presentation;

import android.app.Presentation;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.hardware.display.DisplayManager;
import android.os.Handler;
import android.util.Log;
import android.view.Display;

import static cn.flyaudio.flyaudiolauncher.AppConfig.DEBUG;

/**
 * @author weifule
 * @date 19-7-8
 * Email: fulewei@foxmail.com
 * Description:
 */
public class FlyInstrumentClusterController {

    private static final String TAG = "Controller";

    private final Context mContext;
    private ClusterView mClusterView;
    private DisplayManager displayManager;
    private Display display;
    private Presentation presentation;

    private OnDisplayChangedListener listener;

    public interface OnDisplayChangedListener {
        void onDisplayAdded(int displayId);
        void onDisplayRemoved(int displayId);
        void onDisplayChanged(int displayId);
    }

    public FlyInstrumentClusterController(Context context, OnDisplayChangedListener listener) {
        this.listener = listener;
        mContext = context;
        init();
    }

    private void init() {
        displayManager = mContext.getSystemService(DisplayManager.class);
        displayManager.registerDisplayListener(mDisplayListener, new Handler());
        display = getInstrumentClusterDisplay();

        if (DEBUG) {
            Log.d(TAG, "Instrument cluster display: " + display);
        }
        if (display == null) {
            return;
        }
        mClusterView = new ClusterView(mContext);
        presentation = new FlyInstrumentClusterPresentation(mContext, display);
        presentation.setContentView(mClusterView);

        //显示默认的
        //mClusterView.enqueueCard();

        //presentation.show();
    }

    public void showPresentation() {
        if (presentation == null) {
            mClusterView = new ClusterView(mContext);
            presentation = new FlyInstrumentClusterPresentation(mContext, display);
            presentation.setContentView(mClusterView);
        }
        presentation.show();
    }

    public void disPresentation() {
        if (presentation != null) {
            presentation.dismiss();
        }
    }

    public int getDisplayId() {
        if (display != null) {
           return display.getDisplayId();
        }
        return -1;
    }

    private final DisplayManager.DisplayListener mDisplayListener = new DisplayManager.DisplayListener() {

        @Override
        public void onDisplayAdded(int displayId) {
            if (DEBUG){
                Log.d(TAG,"onDisplayAdded displayId " + displayId);
            }
            listener.onDisplayAdded(displayId);
        }

        @Override
        public void onDisplayRemoved(int displayId) {
            if (DEBUG){
                Log.d(TAG,"onDisplayRemoved displayId : " + displayId);
            }
            listener.onDisplayRemoved(displayId);
        }

        @Override
        public void onDisplayChanged(int displayId) {
            if (DEBUG){
                Log.d(TAG,"onDisplayChanged ");
            }
            listener.onDisplayChanged(displayId);
        }
    };

    /**
     * 获取副屏实例
     * @return
     */
    private Display getInstrumentClusterDisplay() {
        if (displayManager != null){
            Display[] displays = displayManager.getDisplays();
            if (DEBUG) {
                Log.d(TAG, "There are currently " + displays.length + " displays connected.");
                for (Display display : displays) {
                    Log.d(TAG, "  " + display);
                }
            }

            if (displays.length > 1) {
                return displays[displays.length - 1];
            }
        }

        return null;
    }

    public void updatePersentation(Bitmap bitmap){
        if (DEBUG){
            Log.d(TAG,"updatePersentation " + bitmap);
        }
        if(mClusterView != null){
            mClusterView.enqueueCard(rotateBitmap(bitmap,-90));
        }
    }


    private Bitmap rotateBitmap(Bitmap source,int rotate){
        if (rotate == 0){
            return source;
        }

        int w = source.getWidth();
        int h = source.getHeight();
        Matrix matrix = new Matrix();
        matrix.postRotate(rotate);

        Bitmap tempBitmap = Bitmap.createBitmap(source,0 ,0,w , h,matrix , true);
        return tempBitmap;
    }


    public void onDestroy() {
        if(displayManager != null) {
            displayManager.unregisterDisplayListener(mDisplayListener);
        }
    }
}
