package cn.flyaudio.flyaudiolauncher.amap;

import android.os.Bundle;

import com.amap.api.maps.AMap;

/**
 * @author weifule
 * @date 19-7-23
 * Email: fulewei@foxmail.com
 * Description:
 */
public interface IAMapPresenter {
    /**
     * 初始化高德地图
     */
    void onCreatMap(AMap aMap, int mapType);

    /**
     * 搜索
     */
    void onSearchPoi();

    /**
     * 导航回家
     */
    void navigateHome();

    /**
     * 导航到公司
     */
    void navigateCompany();

    /**
     * 打开路况
     */
    void setTrafficEnabled(boolean enabled);

    /**
     * 回收地图资源
     */
    void destroyAMap();

    /**
     * 获取当前位置坐标
     * @return
     */
    Bundle getPoiBundle();

    /**
     * 获取POI坐标
     * @return
     */
    Bundle getLocalBundle();

    /**
     * 更新投射页面
     */
    void updatePersent();
}
