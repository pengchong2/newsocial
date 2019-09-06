package cn.flyaudio.flyaudiolauncher.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

/**
 * @author weifule
 * @date 19-7-27
 * Email: fulewei@foxmail.com
 * Description:
 */
public class FlyLauncherWidgetView extends RelativeLayout {

    public FlyLauncherWidgetView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return true;
    }
}
