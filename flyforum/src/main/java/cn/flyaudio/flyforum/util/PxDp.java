package cn.flyaudio.flyforum.util;

import android.content.Context;

/**
 * Created by 叶兴运 on
 * 2019/3/14.9:53
 */
public class PxDp {
     /** 根据手机的分辨率PX(像素)转成DP
      */
    public static int px2dip(Context mContext, float pxValue) {
        float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
