package cn.flyaudio.flyaudiolauncher.amap;

import android.os.Bundle;

import com.amap.api.maps.AMap;

/**
 * @author weifule
 * @date 19-7-23
 * Email: fulewei@foxmail.com
 * Description:
 */
public class AMapPresenterImpl implements IAMapPresenter{

    private IAmapView amapVie;
    private IAMapModel amapModel;


    public AMapPresenterImpl(IAmapView amapView) {
        this.amapVie = amapView;
        this.amapModel = new AMapModelImpl(this);
    }


    @Override
    public void onCreatMap(AMap aMap, int mapType) {
        amapModel.onCreatMap(aMap, mapType);
    }

    @Override
    public void onSearchPoi() {
        amapModel.onSearchPoi();
    }

    @Override
    public void navigateHome() {
        amapModel.navigateHome();
    }

    @Override
    public void navigateCompany() {
        amapModel.navigateCompany();
    }

    @Override
    public void setTrafficEnabled(boolean enabled) {
        amapModel.setTrafficEnabled(enabled);
    }

    @Override
    public void destroyAMap() {
        amapModel.destroyAMap();
    }

    @Override
    public Bundle getPoiBundle() {
        return amapModel.getPoiBundle();
    }

    @Override
    public Bundle getLocalBundle() {
        return amapModel.getLocalBundle();
    }

    @Override
    public void updatePersent() {
        if (amapVie != null) {
            amapVie.updatePersent();
        }
    }
}
