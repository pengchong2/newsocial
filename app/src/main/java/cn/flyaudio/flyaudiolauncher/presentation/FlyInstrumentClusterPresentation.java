package cn.flyaudio.flyaudiolauncher.presentation;

import android.app.Presentation;
import android.content.Context;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import cn.flyaudio.flyaudiolauncher.utils.ScreenUtils;

/**
 * @author weifule
 * @date 19-7-8
 * Email: fulewei@foxmail.com
 * Description:
 */
public class FlyInstrumentClusterPresentation extends Presentation {

    private static final String TAG = "Presentation";

    public FlyInstrumentClusterPresentation(Context outerContext, Display display) {
        super(outerContext, display);

        //getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        Window window = getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        int screenWidth = ScreenUtils.getScreenWidth(outerContext);
        int screenHight = ScreenUtils.getScreenHight(outerContext);

        Log.d(TAG," screenWidth : " + screenWidth + " screenHight : " + screenHight);


        layoutParams.width = screenWidth;
        layoutParams.height = screenHight;


        if (window != null) {
            WindowManager.LayoutParams attr = window.getAttributes();
            if (attr != null) {
                attr.height = ViewGroup.LayoutParams.MATCH_PARENT;
                attr.width = ViewGroup.LayoutParams.MATCH_PARENT;
                attr.gravity = Gravity.CENTER;
            }
        }
    }
}
