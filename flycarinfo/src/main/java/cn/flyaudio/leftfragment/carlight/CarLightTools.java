package cn.flyaudio.leftfragment.carlight;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Switch;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;

import cn.flyaudio.leftfragment.R;
import cn.flyaudio.leftfragment.carlight.adapter.CarLightListAdapter;
import cn.flyaudio.leftfragment.carlight.bean.CarLightObject;
import cn.flyaudio.leftfragment.module.LeftViewModel;
import cn.flyaudio.leftfragment.module.TabEntity;
import cn.flyaudio.sdk.manager.FlyNewEnergyManager;

public class CarLightTools {

    private CommonTabLayout commonTabLayout2;
    private CommonTabLayout commonTabLayout3;
    private CommonTabLayout commonTabLayout4;
    private ArrayList<CarLightObject> carLightObjects;
    private ListView mlist;
    private CarLightListAdapter carLightListAdapter;
    private Fragment mFragment;
    private View carlightView;
    private Context context;
    private LeftViewModel mViewModel;
    private static final String TAG = "yyq123";
    private static final boolean DEBUG = true;

    public CarLightTools(View carlightView) {
        this.carlightView = carlightView;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void setmFragment(Fragment mFragment) {
        this.mFragment = mFragment;
        setCarLightView();
    }

    public void setmViewModel(LeftViewModel mViewModel) {
        this.mViewModel = mViewModel;
    }

    private void setCarLightView() {
        View view2 = carlightView;
        if (carlightView == null) return;
        //car_light_tv = view2.findViewById(R.id.car_light_tv);
        //car_light_switch = view2.findViewById(R.id.car_light_switch);
        commonTabLayout2 = view2.findViewById(R.id.commonTabLayout_view2);
        String[] mTitles_2 = {"  ", "  ", "  ", "  ", " "};
        ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
        for (String s : mTitles_2) {
            mTabEntities.add(new TabEntity(s, 0, 0));
        }
        commonTabLayout2.setTabData(mTabEntities);

        commonTabLayout3 = view2.findViewById(R.id.commonTabLayout_view3);
        mViewModel.setmHomeCarLinghtData();
        mViewModel.getHomeCarLinghtData().observe(mFragment, new Observer<ArrayList<CustomTabEntity>>() {
            @Override
            public void onChanged(@Nullable ArrayList<CustomTabEntity> customTabEntities) {
                if (customTabEntities == null) return;
                commonTabLayout3.setTabData(customTabEntities);
                //commonTabLayout3.setCurrentTab(1);
            }
        });

        commonTabLayout4 = view2.findViewById(R.id.commonTabLayout_view4);
        String[] mTitles_4 = {"  ", "  ", "  "};
        ArrayList<CustomTabEntity> mTabEntities4 = new ArrayList<>();
        for (String s : mTitles_4) {
            mTabEntities4.add(new TabEntity(s, 0, 0));
        }
        commonTabLayout4.setTabData(mTabEntities4);
        //String car_light_item_key = getResources().getString(R.string.car_light_list_item_key);
        String car_light_litem_values = context.getResources().getString(R.string.car_light_list_item_values);
        //String[] key = car_light_item_key.split(",");
        String[] values = car_light_litem_values.split(",");
        mlist = view2.findViewById(R.id.left_fragment_listview);
        carLightListAdapter = new CarLightListAdapter(context);
        mViewModel.setCarlightlistdata(values);
        mViewModel.getCarlightlistdata().observe(mFragment, new Observer<String[]>() {
            @Override
            public void onChanged(@Nullable String[] strings) {
                if (strings == null) return;
                carLightObjects = new ArrayList<>();
                CarLightObject carLightObject;
                for (String string : strings) {
                    carLightObject = new CarLightObject();
                    carLightObject.setName(string);
                    carLightObject.setaSwitch(new Switch(context));
                    carLightObjects.add(carLightObject);
                }
                carLightListAdapter.setData(carLightObjects);
                mlist.setAdapter(carLightListAdapter);
            }
        });

        //car_light_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
        //    @Override
        //    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        //        if (!buttonView.isPressed()) {
        //            return;
        //        }
        //        if (DEBUG) Log.d(TAG, "onCheckedChanged: 车灯开关 点击");
        //        if (isChecked) {
        //            //sdkk关闭车灯
        //            //car_light_tv.setTextColor(getResources().getColor(R.color.left_fragment_94989F));
        //            //commonTabLayout2.setIndicatorColor(getResources().getColor(R.color.left_fragment_485e7f));
        //            FlyNewEnergyManager.getInstance().witchExternalLights(FlyNewEnergyManager.EXTERNALLIGHTS_CLOSE);
//
        //        } else {
        //            if (oldLighttype != -1)
        //                FlyNewEnergyManager.getInstance().witchExternalLights(oldLighttype);
        //            //sddk打开车灯
        //            //car_light_tv.setTextColor(getResources().getColor(R.color.left_fragment_ffffff));
        //            //commonTabLayout2.setIndicatorColor(getResources().getColor(R.color.left_fragment_E5512F));
//
        //        }
        //    }
        //});

        commonTabLayout2.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                if (DEBUG) Log.d(TAG, "onTabSelect: commonTabLayout2 :" + position);
                switch (position) {
                    case 0://外部车灯灯关
                        FlyNewEnergyManager.getInstance().witchExternalLights(FlyNewEnergyManager.EXTERNALLIGHTS_CLOSE);
                        break;
                    case 1://外部车灯灯关
                        FlyNewEnergyManager.getInstance().witchExternalLights(FlyNewEnergyManager.EXTERNALLIGHTS_WIDTH_INDICATOR);
                        break;
                    case 2://近光灯
                        FlyNewEnergyManager.getInstance().witchExternalLights(FlyNewEnergyManager.EXTERNAL_LIGHTS_DIPPED);
                        break;
                    case 3://远光灯
                        FlyNewEnergyManager.getInstance().witchExternalLights(FlyNewEnergyManager.EXTERNALLIGHTS_HIGH_BEAMS);
                        break;
                    case 4://自动灯光
                        FlyNewEnergyManager.getInstance().witchExternalLights(FlyNewEnergyManager.EXTERNALLIGHTS_AUTO);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onTabReselect(int position) {

            }
        });

        commonTabLayout3.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                if (DEBUG) Log.d(TAG, "onTabSelect: commonTabLayout3 :" + position);
                switch (position) {
                    case 0://伴我回家灯关
                        FlyNewEnergyManager.getInstance().witchCompany(FlyNewEnergyManager.COMPANY_OFF);
                        break;
                    case 1://开
                        FlyNewEnergyManager.getInstance().witchCompany(FlyNewEnergyManager.COMPANY_ON);
                        break;
                    case 2://自动
                        break;
                    case 3:
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onTabReselect(int position) {

            }
        });

        commonTabLayout4.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                if (DEBUG) Log.d(TAG, "onTabSelect: commonTabLayout4 :" + position);
                switch (position) {
                    case 0://左右转向灯关
                        FlyNewEnergyManager.getInstance().witchLeftRightLights(FlyNewEnergyManager.LEFTRIGHTLIGHTS_OFF);
                        break;
                    case 1://左转向灯关
                        FlyNewEnergyManager.getInstance().witchLeftRightLights(FlyNewEnergyManager.LEFTRIGHTLIGHTS_LEFT);
                        break;
                    case 2://右右转向灯关
                        FlyNewEnergyManager.getInstance().witchLeftRightLights(FlyNewEnergyManager.LEFTRIGHTLIGHTS_RIGHT);
                        break;
                    case 3://自动转向灯
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
    }

    public void onExternalLightsStatus(int i) {
        //if (DEBUG) Log.d(TAG, "carLightBean : " + i);
        if (commonTabLayout2 == null) return;
        switch (i) {
            case FlyNewEnergyManager.EXTERNALLIGHTS_WIDTH_INDICATOR:
                //oldLighttype = FlyNewEnergyManager.EXTERNALLIGHTS_WIDTH_INDICATOR;
                commonTabLayout2.setCurrentTab(1);
                break;
            case FlyNewEnergyManager.EXTERNAL_LIGHTS_DIPPED:
                //oldLighttype = FlyNewEnergyManager.EXTERNAL_LIGHTS_DIPPED;
                commonTabLayout2.setCurrentTab(2);
                break;
            case FlyNewEnergyManager.EXTERNALLIGHTS_HIGH_BEAMS:
                //oldLighttype = FlyNewEnergyManager.EXTERNALLIGHTS_HIGH_BEAMS;
                commonTabLayout2.setCurrentTab(3);
                break;
            case FlyNewEnergyManager.EXTERNALLIGHTS_AUTO:
                //oldLighttype = FlyNewEnergyManager.EXTERNALLIGHTS_AUTO;
                commonTabLayout2.setCurrentTab(4);
                break;
            case FlyNewEnergyManager.EXTERNALLIGHTS_CLOSE:
                //commonTabLayout2.setIndicatorColor(context.getResources().getColor(R.color.left_fragment_485e7f));
                //car_light_tv.setTextColor(context.getResources().getColor(R.color.left_fragment_94989F));
                //oldLighttype = FlyNewEnergyManager.EXTERNALLIGHTS_CLOSE;
                commonTabLayout2.setCurrentTab(0);
                //car_light_switch.setChecked(true);
                break;
            default:
                break;
        }
        /*if (i != FlyNewEnergyManager.EXTERNALLIGHTS_CLOSE) {
            car_light_tv.setTextColor(context.getResources().getColor(R.color.left_fragment_ffffff));
            commonTabLayout2.setIndicatorColor(context.getResources().getColor(R.color.left_fragment_E5512F));
            //car_light_switch.setChecked(false);
        }*/

    }

    public void onCompanyLightsStatus(int i) {
        //if (DEBUG) Log.d(TAG, "onCompanyLightsStatus: " + i);
        if (commonTabLayout3 == null) return;
        switch (i) {
            case FlyNewEnergyManager.COMPANY_AUTO:
                break;
            case FlyNewEnergyManager.COMPANY_ON:
                commonTabLayout3.setCurrentTab(1);
                break;
            case FlyNewEnergyManager.COMPANY_OFF:
                commonTabLayout3.setCurrentTab(0);
                break;
            default:
                break;
        }
    }

    public void onLeftRightLightsStatus(int i) {
        //if (DEBUG) Log.d(TAG, "onLeftRightLightsStatus: " + i);
        if (commonTabLayout4 == null) return;
        switch (i) {
            case FlyNewEnergyManager.LEFTRIGHTLIGHTS_AUTO:
                break;
            case FlyNewEnergyManager.LEFTRIGHTLIGHTS_LEFT:
                commonTabLayout4.setCurrentTab(1);
                break;
            case FlyNewEnergyManager.LEFTRIGHTLIGHTS_RIGHT:
                commonTabLayout4.setCurrentTab(2);
                break;
            case FlyNewEnergyManager.LEFTRIGHTLIGHTS_OFF:
                commonTabLayout4.setCurrentTab(0);
                break;
            default:
                break;
        }
    }

    public void onFrontFogLampStatus(int i) {
        //if (DEBUG) Log.d(TAG, "onFrontFogLampStatus: " + i);
        if (mlist == null) return;
        switch (i) {
            case FlyNewEnergyManager.LIGHTS_OFF:
                carLightObjects.get(0).getaSwitch().setChecked(false);
                break;
            case FlyNewEnergyManager.LIGHTS_ON:
                carLightObjects.get(0).getaSwitch().setChecked(true);
                break;
            default:
                break;
        }
        carLightListAdapter.notifyDataSetChanged();
    }

    public void onTailFogLampStatus(int i) {
        //if (DEBUG) Log.d(TAG, "onTailFogLampStatus: " + i);
        if (mlist == null) return;
        switch (i) {
            case FlyNewEnergyManager.LIGHTS_OFF:
                carLightObjects.get(1).getaSwitch().setChecked(false);
                break;
            case FlyNewEnergyManager.LIGHTS_ON:
                carLightObjects.get(1).getaSwitch().setChecked(true);
                break;
            default:
                break;
        }
        carLightListAdapter.notifyDataSetChanged();
    }

    public void onDrlLightsStatus(int i) {
        //if (DEBUG) Log.d(TAG, "onDrlLightsStatus: " + i);
        if (mlist == null) return;
        switch (i) {
            case FlyNewEnergyManager.LIGHTS_OFF:
                carLightObjects.get(2).getaSwitch().setChecked(false);
                break;
            case FlyNewEnergyManager.LIGHTS_ON:
                carLightObjects.get(2).getaSwitch().setChecked(true);
                break;
            default:
                break;
        }
        carLightListAdapter.notifyDataSetChanged();
    }

    public void onDoubleFlashLightsStatus(int i) {
        //if (DEBUG) Log.d(TAG, "onDoubleFlashLightsStatus: " + i);
        if (mlist == null) return;
        switch (i) {
            case FlyNewEnergyManager.LIGHTS_OFF:
                carLightObjects.get(3).getaSwitch().setChecked(false);
                break;
            case FlyNewEnergyManager.LIGHTS_ON:
                carLightObjects.get(3).getaSwitch().setChecked(true);
                break;
            default:
                break;
        }
        carLightListAdapter.notifyDataSetChanged();
    }

    public void onReversingLightsStatus(int i) {
        //if (DEBUG) Log.d(TAG, "onReversingLightsStatus: " + i);
        if (mlist == null) return;
        switch (i) {
            case FlyNewEnergyManager.LIGHTS_OFF:
                carLightObjects.get(4).getaSwitch().setChecked(false);
                break;
            case FlyNewEnergyManager.LIGHTS_ON:
                carLightObjects.get(4).getaSwitch().setChecked(true);
                break;
            default:
                break;
        }
        carLightListAdapter.notifyDataSetChanged();
    }

    public void onStopLightsStatus(int i) {
        //if (DEBUG) Log.d(TAG, "onStopLightsStatus: " + i);
        if (mlist == null) return;
        switch (i) {
            case FlyNewEnergyManager.LIGHTS_OFF:
                carLightObjects.get(5).getaSwitch().setChecked(false);
                break;
            case FlyNewEnergyManager.LIGHTS_ON:
                carLightObjects.get(5).getaSwitch().setChecked(true);
                break;
            default:
                break;
        }
        carLightListAdapter.notifyDataSetChanged();
    }


}
