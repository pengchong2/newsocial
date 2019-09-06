package cn.flyaudio.leftfragment.carlock;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.yxf.clippathlayout.PathInfo;
import com.yxf.clippathlayout.pathgenerator.OvalRingPathGenerator;

import java.util.ArrayList;

import cn.flyaudio.leftfragment.R;
import cn.flyaudio.leftfragment.module.LeftViewModel;
import cn.flyaudio.leftfragment.module.TabEntity;
import cn.flyaudio.sdk.manager.FlyNewEnergyManager;

public class CarLockToolsTest implements View.OnClickListener, OnTabSelectListener {
    private Button car_hsj_rl_open, car_hsj_lr_close, car_hsj_l_jd, car_hsj_r_jd;
    private View mLeftViewRight, mRightViewRight, mTopViewRight, mBottomViewRight;
    private CommonTabLayout commonTabLayout1;
    private Button wfl_o, wfl_f, wfr_o, wfr_f, wbl_o, wbr_o, wbr_f, wbl_f;
    private Fragment mFragment;
    private Context context;
    private LeftViewModel mViewModel;
    private static final String TAG = "yyq123";
    private static final boolean DEBUG = true;
    private View view1;
    private ImageView iv_hsj;
    private Switch carhbx_state,cardoor_clock;
    private Button cardoor_lf_o,cardoor_lf_f,cardoor_lb_o,cardoor_lb_f;
    private Button cardoor_rf_o,cardoor_rf_f,cardoor_rb_o,cardoor_rb_f;


    public CarLockToolsTest(View view1) {
        this.view1 = view1;
    }

    public void setmFragment(Fragment mFragment) {
        this.mFragment = mFragment;
    }

    public void setContext(Context context) {
        this.context = context;
        setCarLockView();
    }

    public void setmViewModel(LeftViewModel mViewModel) {
        this.mViewModel = mViewModel;
    }

    private void setCarLockView() {
        commonTabLayout1 = view1.findViewById(R.id.commonTabLayout_view1);
        mViewModel.setMTitles1();
        mViewModel.getNameEvent().observe(mFragment, new Observer<ArrayList<CustomTabEntity>>() {
            @Override
            public void onChanged(@Nullable ArrayList<CustomTabEntity> customTabEntities) {
                if (customTabEntities == null) return;
                commonTabLayout1.setTabData(customTabEntities);
            }
        });
        commonTabLayout1.setOnTabSelectListener(this);
        initUiCar2DoorSelect(view1);

        View left01 = view1.findViewById(R.id.right_01);
        iv_hsj = left01.findViewById(R.id.left_fragment_hsj_iv);
        mLeftViewRight = left01.findViewById(R.id.car_hsj_left_view);
        mRightViewRight = left01.findViewById(R.id.car_hsj_right_view);
        mTopViewRight = left01.findViewById(R.id.car_hsj_top_view);
        mBottomViewRight = left01.findViewById(R.id.car_hsj_bottom_view);

        carhbx_state = view1.findViewById(R.id.carhbx_state);
        carhbx_state.setChecked(false);
        carhbx_state.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!buttonView.isPressed()) {
                    //后备箱开关
                    //todo
                    Log.d(TAG, "onCheckedChanged: ");
                }
            }
        });
        //cardoor_clock
        cardoor_clock = view1.findViewById(R.id.cardoor_clock);
        cardoor_clock.setChecked(false);
        cardoor_clock.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!buttonView.isPressed()) {
                    //左右前后车门是否锁上
                    //todo
                    Log.d(TAG, "onCheckedChanged: ");
                }
            }
        });

        setHsjViewOnClickRight();

        car_hsj_rl_open = view1.findViewById(R.id.car_hsj_rl_open);
        car_hsj_lr_close = view1.findViewById(R.id.car_hsj_lr_close);
        car_hsj_l_jd = view1.findViewById(R.id.car_hsj_l_jd);
        car_hsj_r_jd = view1.findViewById(R.id.car_hsj_r_jd);

        car_hsj_rl_open.setOnClickListener(this);
        car_hsj_lr_close.setOnClickListener(this);

        car_hsj_r_jd.setOnClickListener(this);
        car_hsj_l_jd.setOnClickListener(this);

        initUI2HsjRingView();

        initWindowsBtn();

        initDoorBtn();

        setCarDoorSwitch();

        setCarWindowSwitch();
        setCarWindowVentilateSwitch(
                (Button) (view1.findViewById(R.id.car_tf_front_l)),
                (Button) (view1.findViewById(R.id.car_tf_front_r)),
                (Button) (view1.findViewById(R.id.car_tf_back_l)),
                (Button) (view1.findViewById(R.id.car_tf_back_r)));
    }

    private void initDoorBtn() {
        cardoor_lf_o = (view1.findViewById(R.id.cardoor_lf_o));
        cardoor_lf_f = (view1.findViewById(R.id.cardoor_lf_f));
        cardoor_lb_o = (view1.findViewById(R.id.cardoor_lb_o));
        cardoor_lb_f = (view1.findViewById(R.id.cardoor_lb_f));

        cardoor_rf_o = (view1.findViewById(R.id.cardoor_rf_o));
        cardoor_rf_f = (view1.findViewById(R.id.cardoor_rf_f));
        cardoor_rb_o = (view1.findViewById(R.id.cardoor_rb_o));
        cardoor_rb_f = (view1.findViewById(R.id.cardoor_rb_f));
    }

    private void initWindowsBtn() {
        wfl_o = (view1.findViewById(R.id.car_window_front_l1));
        wfl_f = (view1.findViewById(R.id.car_window_front_l2));
        wfr_o = (view1.findViewById(R.id.car_window_front_r1));
        wfr_f = (view1.findViewById(R.id.car_window_front_r2));

        wbl_o = (view1.findViewById(R.id.car_window_back_l1));
        wbl_f = (view1.findViewById(R.id.car_window_back_l2));
        wbr_o = (view1.findViewById(R.id.car_window_back_r1));
        wbr_f = (view1.findViewById(R.id.car_window_back_r2));
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.car_hsj_rl_open) {
            //sdkk关闭左后视镜
            setUi2LeftHsjTools(false, true/*, R.color.left_fragment_94989F*/);
            //todo

        } else if (id == R.id.car_hsj_lr_close) {
            //sdkk打开左后视镜
            setUi2LeftHsjTools(true, false/*, R.color.left_fragment_ffffff*/);
            //todo

        } else if (id == R.id.car_hsj_r_jd) {
            //sdkk打开右后视镜
            setUi2RightHsjTools(false, true/*, R.color.left_fragment_ffffff*/);
            //todo

        } else if (id == R.id.car_hsj_l_jd) {
            //sdkk关闭右后视镜
            setUi2RightHsjTools(true, false/*, R.color.left_fragment_94989F*/);
            //todo

        }
    }

    @SuppressLint("NewApi")
    private void setHsjViewEnable(boolean isclick) {
        mLeftViewRight.setEnabled(isclick);
        mRightViewRight.setEnabled(isclick);
        mTopViewRight.setEnabled(isclick);
        mBottomViewRight.setEnabled(isclick);
        if (isclick) {
            iv_hsj.setImageDrawable(context.getDrawable(R.drawable.left_fragment_hsj_bg));
        } else {
            iv_hsj.setImageDrawable(context.getDrawable(R.drawable.left_fragment_hsj_bg_noclick));
        }
    }

    //sdkk 窗全开
    private void setCarWindowSwitch() {
        wfl_o.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBtnEnabel(wfl_f, true, wfl_o, false);

            }
        });
        wfl_f.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBtnEnabel(wfl_f, false, wfl_o, true);

            }
        });

        wfr_o.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBtnEnabel(wfr_o, false, wfr_f, true);
            }
        });
        wfr_f.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBtnEnabel(wfr_o, true, wfr_f, false);

            }
        });

        wbl_o.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBtnEnabel(wbl_o, false, wbl_f, true);

            }
        });
        wbl_f.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBtnEnabel(wbl_o, true, wbl_f, false);
            }
        });

        wbr_o.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBtnEnabel(wbr_o, false, wbr_f, true);

            }
        });
        wbr_f.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBtnEnabel(wbr_o, true, wbr_f, false);

            }
        });

    }

    //sdkk 门全开
    private void setCarDoorSwitch() {
        cardoor_lf_o.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBtnEnabel(cardoor_lf_f, true, cardoor_lf_o, false);

            }
        });
        cardoor_lf_f.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBtnEnabel(cardoor_lf_f, false, cardoor_lf_o, true);

            }
        });

        cardoor_lb_o.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBtnEnabel(cardoor_lb_o, false, cardoor_lb_f, true);
            }
        });
        cardoor_lb_f.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBtnEnabel(cardoor_lb_o, true, cardoor_lb_f, false);

            }
        });

        cardoor_rf_o.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBtnEnabel(cardoor_rf_o, false, cardoor_rf_f, true);

            }
        });
        cardoor_rf_f.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBtnEnabel(cardoor_rf_o, true, cardoor_rf_f, false);
            }
        });

        cardoor_rb_o.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBtnEnabel(cardoor_rb_o, false, cardoor_rb_f, true);

            }
        });
        cardoor_rb_f.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBtnEnabel(cardoor_rb_o, true, cardoor_rb_f, false);

            }
        });

    }

    private void initUiCar2DoorSelect(View view1) {
        final View carwindow = view1.findViewById(R.id.carwindow);
        final View cardoor = view1.findViewById(R.id.cardoor);
        CommonTabLayout commonTabLayout6 = view1.findViewById(R.id.commonTabLayout_view6);
        String[] mTitles_2 = {"车门", "车窗"};
        ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
        for (String s : mTitles_2) {
            mTabEntities.add(new TabEntity(s, 0, 0));
        }

        commonTabLayout6.setTabData(mTabEntities);
        commonTabLayout6.setCurrentTab(0);
        carwindow.setVisibility(View.GONE);
        cardoor.setVisibility(View.VISIBLE);
        commonTabLayout6.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                switch (position) {
                    case 0:
                        carwindow.setVisibility(View.GONE);
                        cardoor.setVisibility(View.VISIBLE);
                        break;
                    case 1:
                        carwindow.setVisibility(View.VISIBLE);
                        cardoor.setVisibility(View.GONE);
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

    private void setUi2LeftHsjTools(boolean b, boolean b2) {
        setBtnEnabel(car_hsj_rl_open, b, car_hsj_lr_close, b2);
        setHsjViewEnable(b2);
    }

    private void setUi2RightHsjTools(boolean b, boolean b2) {
        setBtnEnabel(car_hsj_r_jd, b, car_hsj_l_jd, b2);
    }

    //sdkk通风
    private void setCarWindowVentilateSwitch(Button left_front1, Button right_front1,
                                             Button left_back1, Button right_back1) {
        left_front1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        right_front1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        left_back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        right_back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }

    public void onDoorStatus(int i, int i1, int i2, int i3) {
        //int frontLeft, int frontRight, int backLeft, int backRight
        //cardoor_clock
        switch (i) {
            case FlyNewEnergyManager.LOCK_OFF:
                setBtnEnabel(cardoor_lf_f, false, cardoor_lf_o, true);
                break;
            case FlyNewEnergyManager.LOCK_ON:
                setBtnEnabel(cardoor_lf_f, true, cardoor_lf_o, false);
                break;
            default:
                break;
        }
        switch (i1) {
            case FlyNewEnergyManager.LOCK_OFF:
                setBtnEnabel(cardoor_rf_f, false, cardoor_rf_o, true);
                break;
            case FlyNewEnergyManager.LOCK_ON:
                setBtnEnabel(cardoor_rf_f, true, cardoor_rf_o, false);
                break;
            default:
                break;
        }
        switch (i2) {
            case FlyNewEnergyManager.LOCK_OFF:
                setBtnEnabel(cardoor_lb_f, false, cardoor_lb_o, true);
                break;
            case FlyNewEnergyManager.LOCK_ON:
                setBtnEnabel(cardoor_lb_f, true, cardoor_lb_o, false);
                break;
            default:
                break;
        }
        switch (i3) {
            case FlyNewEnergyManager.LOCK_OFF:
                setBtnEnabel(cardoor_rb_f, false, cardoor_rb_o, true);
                break;
            case FlyNewEnergyManager.LOCK_ON:
                setBtnEnabel(cardoor_rb_f, true, cardoor_rb_o, false);
                break;
            default:
                break;
        }


        if ((i==FlyNewEnergyManager.LOCK_ON||i1==FlyNewEnergyManager.LOCK_ON)||
                (i2 == FlyNewEnergyManager.LOCK_ON || i3 == FlyNewEnergyManager.LOCK_ON)) {
            cardoor_clock.setChecked(false);
            return;
        }

        if ((i==FlyNewEnergyManager.LOCK_OFF&&i1==FlyNewEnergyManager.LOCK_OFF)&&
                (i2 == FlyNewEnergyManager.LOCK_OFF&&i3 == FlyNewEnergyManager.LOCK_OFF)) {
            cardoor_clock.setChecked(true);
        }

    }

    private void setBtnEnabel(Button cardoor_lf_f, boolean b, Button cardoor_lf_o, boolean b2) {
        cardoor_lf_f.setEnabled(b);
        cardoor_lf_o.setEnabled(b2);
    }

    public void onTrunkStatus(int i) {
        //onTrunkStatus(int status)
        //后备箱状态 LOCK_OFF = 0; LOCK_ON = 1;
        if (carhbx_state != null) {
            switch (i) {
                case FlyNewEnergyManager.LOCK_OFF:
                    carhbx_state.setChecked(false);
                    break;
                case FlyNewEnergyManager.LOCK_ON:
                    carhbx_state.setChecked(true);
                    break;
                default:
                    break;
            }
        }
    }

    public void onMirrorStatus(int i, int i1) {
        //后视镜状态 LOCK_OFF = 0; 折叠 LOCK_ON = 1; 展开
        /*switch (i) {
            case FlyNewEnergyManager.LOCK_OFF:
                setUi2LeftHsjTools(false, true*//*, R.color.left_fragment_94989F*//*);
                break;
            case FlyNewEnergyManager.LOCK_ON:
                setUi2LeftHsjTools(true, false*//*, R.color.left_fragment_ffffff*//*);
                break;
            default:
                break;
        }

        switch (i1) {
            case FlyNewEnergyManager.LOCK_OFF:
                setUi2RightHsjTools(true, false*//*, R.color.left_fragment_94989F*//*);
                break;
            case FlyNewEnergyManager.LOCK_ON:
                setUi2RightHsjTools(false, true*//*, R.color.left_fragment_ffffff*//*);
                break;
            default:
                break;
        }*/

        if (i == FlyNewEnergyManager.COMPANY_ON || i1 == FlyNewEnergyManager.COMPANY_ON) {
            setUi2LeftHsjTools(true, false);
        } else if (i == FlyNewEnergyManager.LOCK_OFF && i1 == FlyNewEnergyManager.LOCK_OFF) {
            setUi2LeftHsjTools(false, true);
        }
    }

    public void onWiperStatus(int i) {
        if (DEBUG) Log.d(TAG, "onWiperStatus: " + i);
        //前雨刮间歇档 0x00://关闭 0x01://单次 0x02://低速 0x03://高速 0x04://间歇控制
        if (commonTabLayout1 == null) return;
        switch (i) {
            case 0x00:
                commonTabLayout1.setCurrentTab(0);
                break;
            case 0x01:
                commonTabLayout1.setCurrentTab(1);
                break;
            case 0x02:
                commonTabLayout1.setCurrentTab(2);
                break;
            case 0x03:
                commonTabLayout1.setCurrentTab(3);
                break;
            case 0x04:
                commonTabLayout1.setCurrentTab(4);
                break;
            default:
                break;
        }
    }

    public void onWindowStatus(int i, int i1, int i2, int i3) {
        //onWindowStatus(int frontLeft, int frontRight, int backLeft, int backRight)
        // 0x00:表示关闭 0x01:表示全打开 0x02:表示通风
        if (DEBUG) Log.d(TAG, "onWindowStatus i : " + i + " i1 : " + i1 + "i2 : " + i2 + " i3 : " + i3);
        switch (i) {
            case 0x00:
                setBtnEnabel(wfl_f, false, wfl_o, true);
                break;
            case 0x01:
                setBtnEnabel(wfl_f, true, wfl_o, false);
                break;
            case 0x02:
                break;
            default:
                break;
        }
        switch (i1) {
            case 0x00:
                setBtnEnabel(wfr_f, false, wfr_o, true);
                break;
            case 0x01:
                setBtnEnabel(wfr_f, true, wfr_o, false);
                break;
            case 0x02:
                break;
            default:
                break;
        }
        switch (i2) {
            case 0x00:
                setBtnEnabel(wbl_f, false, wbl_o, true);
                break;
            case 0x01:
                break;
            case 0x02:
                setBtnEnabel(wbl_f, true, wbl_o, false);
                break;
            default:
                break;
        }
        switch (i3) {
            case 0x00:
                setBtnEnabel(wbr_f, false, wbr_o, true);
                break;
            case 0x01:
                setBtnEnabel(wbr_f, true, wbr_o, false);
                break;
            case 0x02:
                break;
            default:
                break;
        }
    }

    @Override
    public void onTabSelect(int position) {
        if (DEBUG) Log.d(TAG, "onTabSelect: position:" + position);
        //调节雨刮
        //sdkk雨刮调节
        switch (position) {
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            default:
                break;
        }
    }

    @Override
    public void onTabReselect(int position) {

    }

    private void setHsjViewOnClickRight() {
        mLeftViewRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "1", Toast.LENGTH_SHORT).show();

            }
        });
        mRightViewRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mTopViewRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mBottomViewRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }


    private void initUI2HsjRingView() {
        new PathInfo.Builder(new OvalRingPathGenerator(0.05f, 45, 90), mBottomViewRight)
                .create()
                .apply();
        new PathInfo.Builder(new OvalRingPathGenerator(0.05f, 135, 90), mLeftViewRight)
                .create()
                .apply();
        new PathInfo.Builder(new OvalRingPathGenerator(0.05f, 225, 90), mTopViewRight)
                .create()
                .apply();
        new PathInfo.Builder(new OvalRingPathGenerator(0.05f, 315, 90), mRightViewRight)
                .create()
                .apply();
    }
}
