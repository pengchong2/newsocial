package cn.flyaudio.leftfragment.module;


import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.flyco.tablayout.listener.CustomTabEntity;

import java.util.ArrayList;


public class LeftViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private MutableLiveData<ArrayList<CustomTabEntity>> mNameEvent = new MutableLiveData<>();
    private MutableLiveData<ArrayList<CustomTabEntity>> mHomeCarLinghtData = new MutableLiveData<>();
    private MutableLiveData<String[]> carlightlistdata = new MutableLiveData<>();

    public MutableLiveData<ArrayList<CustomTabEntity>> getNameEvent() {
        return mNameEvent;
    }


    public MutableLiveData<ArrayList<CustomTabEntity>> getHomeCarLinghtData() {
        return mHomeCarLinghtData;
    }

    public MutableLiveData<String[]> getCarlightlistdata() {
        return carlightlistdata;
    }

    public void setMTitles() {
        String[] mTitles_2 = {"单次", "低速", "高速", "间歇控制"};
        ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
        for (String s : mTitles_2) {
            mTabEntities.add(new TabEntity(s, 0, 0));
        }
        mNameEvent.setValue(mTabEntities);
    }

    public void setMTitles1() {
        String[] mTitles_2 = {"OFF", "单次", "低速", "高速", "间歇控制"};
        ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
        for (String s : mTitles_2) {
            mTabEntities.add(new TabEntity(s, 0, 0));
        }
        mNameEvent.setValue(mTabEntities);
    }

    public void setmHomeCarLinghtData() {
        String[] mTitles_2 = {"OFF", "ON"};
        ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
        for (String s : mTitles_2) {
            mTabEntities.add(new TabEntity(s, 0, 0));
        }
        mHomeCarLinghtData.setValue(mTabEntities);
    }

    public void setCarlightlistdata(String[] mTitles_2) {
        /*String[] mTitles_2 ={"前雾灯","后雾灯", "日间行车灯", "双闪灯", "倒车灯", "刹车灯"}*/
        carlightlistdata.setValue(mTitles_2);
    }

}
