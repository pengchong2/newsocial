package cn.flyaudio.leftfragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;

import cn.flyaudio.leftfragment.adapter.ViewAdapter;
import cn.flyaudio.leftfragment.carlight.CarLightTools;
import cn.flyaudio.leftfragment.carlock.CarLockToolsTest;
import cn.flyaudio.leftfragment.module.LeftViewModel;
import cn.flyaudio.leftfragment.view.ViewPagerScroller;
import cn.flyaudio.sdk.FlySDKManager;
import cn.flyaudio.sdk.InitListener;
import cn.flyaudio.sdk.listener.INewEnergyListener;
import cn.flyaudio.sdk.manager.FlyNewEnergyManager;

public class LeftFragment extends Fragment implements InitListener,
        RadioGroup.OnCheckedChangeListener, ViewPager.OnPageChangeListener,
        CompoundButton.OnCheckedChangeListener, INewEnergyListener, View.OnTouchListener {

    private static final String TAG = "yyq123";
    private static final boolean DEBUG = true;
    private ArrayList<View> page;
    private ViewPager viewpage;
    private RadioGroup rg;
    private CarLightTools carLightTools;
    private CarLockToolsTest carLockTools;

    public static LeftFragment newInstance() {
        return new LeftFragment();
    }

    @SuppressLint("InflateParams")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: ");
        View rootView = inflater.inflate(R.layout.left_fragment, container, false);
        rootView.setOnTouchListener(this);
        viewpage = rootView.findViewById(R.id.vp);
        rg = rootView.findViewById(R.id.left_fragment_rg);
        rg.setOnCheckedChangeListener(this);
        viewpage.addOnPageChangeListener(this);
        page = new ArrayList<>();
        LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
        page.add(layoutInflater.inflate(R.layout.carpage_011, null));
        page.add(layoutInflater.inflate(R.layout.carpage_02, null));

        ViewAdapter viewAdapter = new ViewAdapter(page);
        //设置适配器
        viewpage.setAdapter(viewAdapter);
        viewpage.setCurrentItem(0);
        rg.check(R.id.left_fragment_rb1);
        ViewPagerScroller pagerScroller = new ViewPagerScroller(getActivity());
        pagerScroller.setScrollDuration(0);
        pagerScroller.initViewPagerScroll(viewpage);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (DEBUG) Log.d(TAG, "onActivityCreated: ");
        FlySDKManager.getInstance().initialize(getActivity(), this);
        LeftViewModel mViewModel = ViewModelProviders.of(this).get(LeftViewModel.class);
        carLockTools = new CarLockToolsTest(page.get(0));
        carLockTools.setmFragment(this);
        carLockTools.setmViewModel(mViewModel);
        carLockTools.setContext(getActivity());
        carLightTools = new CarLightTools(page.get(1));
        carLightTools.setContext(getActivity());
        carLightTools.setmViewModel(mViewModel);
        carLightTools.setmFragment(this);

        FlyNewEnergyManager.getInstance().setListener(this);
        //FlyNewEnergyManager.getInstance().registerCallBackListener();

    }


    @Override
    public void onError() {
        if (DEBUG) Log.d(TAG, "flysdk onError: ");
    }

    @Override
    public void onSucceed() {
        if (DEBUG) Log.d(TAG, "flysdk onSucceed: ");
        FlyNewEnergyManager.getInstance().registerCallBackListener();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if (checkedId == R.id.left_fragment_rb1) {
            viewpage.setCurrentItem(0);
        } else if (checkedId == R.id.left_fragment_rb2) {
            viewpage.setCurrentItem(1);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) { }

    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case 0://车锁
                if (rg.getCheckedRadioButtonId() != R.id.left_fragment_rb1) {
                    rg.check(R.id.left_fragment_rb1);
                }
                break;
            case 1://车灯
                if (rg.getCheckedRadioButtonId() != R.id.left_fragment_rb2) {
                    rg.check(R.id.left_fragment_rb2);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

    }

    @Override
    public void onExternalLightsStatus(int i) {
        if (DEBUG) Log.d(TAG, "carLightBean : " + i);
        if (carLightTools != null) carLightTools.onExternalLightsStatus(i);
    }

    @Override
    public void onCompanyLightsStatus(int i) {
        if (DEBUG) Log.d(TAG, "onCompanyLightsStatus: " + i);
        if (carLightTools != null) carLightTools.onCompanyLightsStatus(i);
    }

    @Override
    public void onLeftRightLightsStatus(int i) {
        if (DEBUG) Log.d(TAG, "onLeftRightLightsStatus: " + i);
        if (carLightTools != null) carLightTools.onLeftRightLightsStatus(i);
    }

    @Override
    public void onFrontFogLampStatus(int i) {
        if (DEBUG) Log.d(TAG, "onFrontFogLampStatus: " + i);
        if (carLightTools != null) carLightTools.onFrontFogLampStatus(i);
    }

    @Override
    public void onTailFogLampStatus(int i) {
        if (DEBUG) Log.d(TAG, "onTailFogLampStatus: " + i);
        if (carLightTools != null) carLightTools.onTailFogLampStatus(i);
    }

    @Override
    public void onDrlLightsStatus(int i) {
        if (DEBUG) Log.d(TAG, "onDrlLightsStatus: " + i);
        if (carLightTools != null) carLightTools.onDrlLightsStatus(i);
    }

    @Override
    public void onDoubleFlashLightsStatus(int i) {
        if (DEBUG) Log.d(TAG, "onDoubleFlashLightsStatus: " + i);
        if (carLightTools != null) carLightTools.onDoubleFlashLightsStatus(i);
    }

    @Override
    public void onReversingLightsStatus(int i) {
        if (DEBUG) Log.d(TAG, "onReversingLightsStatus: " + i);
        if (carLightTools != null) carLightTools.onReversingLightsStatus(i);
    }

    @Override
    public void onStopLightsStatus(int i) {
        if (DEBUG) Log.d(TAG, "onStopLightsStatus: " + i);
        if (carLightTools != null) carLightTools.onStopLightsStatus(i);
    }

    @Override
    public void onWiperStatus(int i) {
        if (DEBUG) Log.d(TAG, "onWiperStatus: " + i);
        if (carLockTools != null) carLockTools.onWiperStatus(i);
    }

    @Override
    public void onDoorStatus(int i, int i1, int i2, int i3) {
        if (DEBUG) Log.d(TAG, "onDoorStatus: " + i);
        if (carLockTools != null) carLockTools.onDoorStatus(i,i1,i2,i3);
    }

    @Override
    public void onTrunkStatus(int i) {
        //onTrunkStatus(int status)
        //后备箱状态 LOCK_OFF = 0; LOCK_ON = 1;
        if (DEBUG) Log.d(TAG, "onTrunkStatus: " + i);
        if (carLockTools != null) carLockTools.onTrunkStatus(i);
    }

    @Override
    public void onWindowStatus(int i, int i1, int i2, int i3) {
        //onWindowStatus(int frontLeft, int frontRight, int backLeft, int backRight)
        // 0x00:表示关闭 0x01:表示全打开 0x02:表示通风
        if (carLockTools != null) carLockTools.onWindowStatus(i, i1, i2, i3);
    }

    @Override
    public void onMirrorStatus(int i, int i1) {
        //后视镜状态 LOCK_OFF = 0; 折叠 LOCK_ON = 1; 展开
        if (DEBUG) Log.d(TAG, "onMirrorStatus: i:" + i);
        if (carLockTools != null) carLockTools.onMirrorStatus(i, i1);
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return true;
    }
}
