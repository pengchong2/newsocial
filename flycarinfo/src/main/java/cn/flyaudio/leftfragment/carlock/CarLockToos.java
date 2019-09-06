package cn.flyaudio.leftfragment.carlock;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
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

public class CarLockToos implements View.OnClickListener, OnTabSelectListener {
    private Button car_hsj_l_close,car_hsj_l_open,car_hsj_r_close,car_hsj_r_open;
    private View mLeftViewRight,mRightViewRight,mTopViewRight,mBottomViewRight,mBottomViewLeft;
    private CommonTabLayout commonTabLayout1;
    private View mLeftViewLeft,mRightViewLeft,mTopViewLeft;
    private TextView car_hsj_tv0,car_hsj_tv;
    private Switch yg;
    private Button wfl_o,wfl_f,wfr_o,wfr_f,wbl_o,wbr_o,wbr_f,wbl_f;
    private Fragment mFragment;
    private Context context;
    private LeftViewModel mViewModel;
    private static final String TAG = "yyq123";
    private static final boolean DEBUG = true;
    private View view1;

    public CarLockToos(View view1) {
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
        mViewModel.setMTitles();
        mViewModel.getNameEvent().observe(mFragment, new Observer<ArrayList<CustomTabEntity>>() {
            @Override
            public void onChanged(@Nullable ArrayList<CustomTabEntity> customTabEntities) {
                if (customTabEntities == null) return;
                commonTabLayout1.setTabData(customTabEntities);
            }
        });
        commonTabLayout1.setOnTabSelectListener(this);
        initUiCar2DoorSelect(view1);
        //View mCenterView = view1.findViewById(R.id.center_view);
        //左后视镜
        View left02 = view1.findViewById(R.id.left_01);
        mLeftViewLeft = left02.findViewById(R.id.car_hsj_left_view);
        mRightViewLeft = left02.findViewById(R.id.car_hsj_right_view);
        mTopViewLeft = left02.findViewById(R.id.car_hsj_top_view);
        mBottomViewLeft = left02.findViewById(R.id.car_hsj_bottom_view);

        //右后视镜
        View left01 = view1.findViewById(R.id.right_01);
        mLeftViewRight = left01.findViewById(R.id.car_hsj_left_view);
        mRightViewRight = left01.findViewById(R.id.car_hsj_right_view);
        mTopViewRight = left01.findViewById(R.id.car_hsj_top_view);
        mBottomViewRight = left01.findViewById(R.id.car_hsj_bottom_view);

        yg = view1.findViewById(R.id.left_fragment_yg_switch);
        yg.setTag(view1.findViewById(R.id.car_yg_tv));
        setHsjViewOnClickRight();
        setHsjViewOnClickLeft();
        car_hsj_tv0 = view1.findViewById(R.id.car_hsj_tv0);
        car_hsj_tv = view1.findViewById(R.id.car_hsj_tv);
        car_hsj_l_close = view1.findViewById(R.id.car_hsj_l_close);//左后视镜折叠
        car_hsj_l_open = view1.findViewById(R.id.car_hsj_l_open);//左后视镜打开
        car_hsj_r_close = view1.findViewById(R.id.car_hsj_r_close);//右后视镜折叠
        car_hsj_r_open = view1.findViewById(R.id.car_hsj_r_open);//右后视镜打开

        car_hsj_l_close.setOnClickListener(this);
        car_hsj_l_open.setOnClickListener(this);
        car_hsj_r_open.setOnClickListener(this);
        car_hsj_r_close.setOnClickListener(this);

        //从sdkk获取雨刮默认状态
        yg.setChecked(false);

        yg.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!yg.isPressed()) return;
                if (buttonView.getId() == R.id.left_fragment_yg_switch) {
                    if (isChecked) {
                        //((TextView) buttonView.getTag()).setTextColor(getResources().getColor(R.color.left_fragment_94989F));
                        //commonTabLayout1.setIndicatorColor(getResources().getColor(R.color.left_fragment_485e7f));
                        //ctl_noclick.setVisibility(View.VISIBLE);
                        //sdkk打开雨刮
                        //sdkk雨刮调节
                        //todo----------------
                    } else {
                        //((TextView) buttonView.getTag()).setTextColor(getResources().getColor(R.color.left_fragment_ffffff));
                        //commonTabLayout1.setIndicatorColor(getResources().getColor(R.color.left_fragment_E5512F));
                        //ctl_noclick.setVisibility(View.GONE);
                        //sdkk关闭雨刮
                        //todo----------------
                    }
                }
            }
        });
        /*new PathInfo.Builder(new CirclePathGenerator(), mCenterView)
                .create()
                .apply();*/
        initUI2HsjRingView();

        wfl_o = (view1.findViewById(R.id.car_window_front_l1));
        wfl_f = (view1.findViewById(R.id.car_window_front_l2));
        wfr_o = (view1.findViewById(R.id.car_window_front_r1));
        wfr_f = (view1.findViewById(R.id.car_window_front_r2));

        wbl_o = (view1.findViewById(R.id.car_window_back_l1));
        wbl_f = (view1.findViewById(R.id.car_window_back_l2));
        wbr_o = (view1.findViewById(R.id.car_window_back_r1));
        wbr_f = (view1.findViewById(R.id.car_window_back_r2));

        setCarWindowSwitch(wfl_o, wfl_f, wfr_o, wfr_f, wbl_o, wbl_f, wbr_o, wbr_f);
        setCarWindowVentilateSwitch(
                (Button) (view1.findViewById(R.id.car_tf_front_l)),
                (Button) (view1.findViewById(R.id.car_tf_front_r)),
                (Button) (view1.findViewById(R.id.car_tf_back_l)),
                (Button) (view1.findViewById(R.id.car_tf_back_r)));
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.car_hsj_l_close) {
            //sdkk关闭左后视镜
            setUi2LeftHsjTools(false, true, R.color.left_fragment_94989F);
            //todo

        } else if (id == R.id.car_hsj_l_open) {
            //sdkk打开左后视镜
            setUi2LeftHsjTools(true, false, R.color.left_fragment_ffffff);
            //todo

        } else if (id == R.id.car_hsj_r_open) {
            //sdkk打开右后视镜
            setUi2RightHsjTools(false, true, R.color.left_fragment_ffffff);
            //todo

        } else if (id == R.id.car_hsj_r_close) {
            //sdkk关闭右后视镜
            setUi2RightHsjTools(true, false, R.color.left_fragment_94989F);
            //todo

        }
    }

    private void setHsjViewEnable(boolean isclick) {
        mLeftViewRight.setEnabled(isclick);
        mRightViewRight.setEnabled(isclick);
        mTopViewRight.setEnabled(isclick);
        mBottomViewRight.setEnabled(isclick);
    }

    private void setHsjViewEnable1(boolean isclick) {
        mLeftViewLeft.setEnabled(isclick);
        mRightViewLeft.setEnabled(isclick);
        mTopViewLeft.setEnabled(isclick);
        mBottomViewLeft.setEnabled(isclick);
    }

    //sdkk 窗全开
    private void setCarWindowSwitch(final Button left_front1, final Button left_front2,
                                    final Button right_front1, final Button right_front2,
                                    final Button left_back1, final Button left_back2,
                                    final Button right_back1, final Button right_back2) {
        left_front1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                left_front2.setEnabled(true);
                left_front1.setEnabled(false);

            }
        });
        left_front2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                left_front2.setEnabled(false);
                left_front1.setEnabled(true);

            }
        });

        right_front1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                right_front1.setEnabled(false);
                right_front2.setEnabled(true);
            }
        });
        right_front2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                right_front1.setEnabled(true);
                right_front2.setEnabled(false);

            }
        });

        left_back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                left_back1.setEnabled(false);
                left_back2.setEnabled(true);

            }
        });
        left_back2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                left_back1.setEnabled(true);
                left_back2.setEnabled(false);
            }
        });

        right_back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                right_back1.setEnabled(false);
                right_back2.setEnabled(true);

            }
        });
        right_back2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                right_back1.setEnabled(true);
                right_back2.setEnabled(false);

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

    private void setUi2LeftHsjTools(boolean b, boolean b2, int p) {
        car_hsj_l_close.setEnabled(b);
        car_hsj_l_open.setEnabled(b2);
        car_hsj_tv0.setTextColor(context.getResources().getColor(p));
        setHsjViewEnable1(b);
    }

    private void setUi2RightHsjTools(boolean b, boolean b2, int p) {
        car_hsj_r_open.setEnabled(b);
        car_hsj_r_close.setEnabled(b2);
        car_hsj_tv.setTextColor(context.getResources().getColor(p));
        setHsjViewEnable(b2);
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

    public void onMirrorStatus(int i, int i1) {
        //后视镜状态 LOCK_OFF = 0; 折叠 LOCK_ON = 1; 展开
        switch (i) {
            case FlyNewEnergyManager.LOCK_OFF:
                setUi2LeftHsjTools(false, true, R.color.left_fragment_94989F);
                break;
            case FlyNewEnergyManager.LOCK_ON:
                setUi2LeftHsjTools(true, false, R.color.left_fragment_ffffff);
                break;
            default:
                break;
        }

        switch (i1) {
            case FlyNewEnergyManager.LOCK_OFF:
                setUi2RightHsjTools(true, false, R.color.left_fragment_94989F);
                break;
            case FlyNewEnergyManager.LOCK_ON:
                setUi2RightHsjTools(false, true, R.color.left_fragment_ffffff);
                break;
            default:
                break;
        }
    }

    public void onWiperStatus(int i) {
        if (DEBUG) Log.d(TAG, "onWiperStatus: " + i);
        //前雨刮间歇档 0x00://关闭 0x01://单次 0x02://低速 0x03://高速 0x04://间歇控制
        if (commonTabLayout1 == null) return;
        switch (i) {
            case 0x00:
                ((TextView) yg.getTag()).setTextColor(context.getResources().getColor(R.color.left_fragment_94989F));
                commonTabLayout1.setIndicatorColor(context.getResources().getColor(R.color.left_fragment_485e7f));
                yg.setChecked(true);
                break;
            case 0x01:
                commonTabLayout1.setCurrentTab(0);
                break;
            case 0x02:
                commonTabLayout1.setCurrentTab(1);
                break;
            case 0x03:
                commonTabLayout1.setCurrentTab(2);
                break;
            case 0x04:
                commonTabLayout1.setCurrentTab(3);
                break;
            default:
                break;

        }

        if (i != 0x00) {
            ((TextView) yg.getTag()).setTextColor(context.getResources().getColor(R.color.left_fragment_ffffff));
            commonTabLayout1.setIndicatorColor(context.getResources().getColor(R.color.left_fragment_E5512F));
            yg.setChecked(false);
        }

    }

    public void onWindowStatus(int i, int i1, int i2, int i3) {
        //onWindowStatus(int frontLeft, int frontRight, int backLeft, int backRight)
        // 0x00:表示关闭 0x01:表示全打开 0x02:表示通风
        if (DEBUG) Log.d(TAG, "onWindowStatus i : " + i + " i1 : " + i1 + "i2 : " + i2 + " i3 : " + i3);
        switch (i) {
            case 0x00:
                wfl_f.setEnabled(false);
                wfl_o.setEnabled(true);
                break;
            case 0x01:
                wfl_f.setEnabled(true);
                wfl_o.setEnabled(false);
                break;
            case 0x02:
                break;
            default:
                break;
        }
        switch (i1) {
            case 0x00:
                wfr_f.setEnabled(false);
                wfr_o.setEnabled(true);
                break;
            case 0x01:
                wfr_f.setEnabled(true);
                wfr_o.setEnabled(false);
                break;
            case 0x02:
                break;
            default:
                break;
        }
        switch (i2) {
            case 0x00:
                wbl_f.setEnabled(false);
                wbl_o.setEnabled(true);
                break;
            case 0x01:
                break;
            case 0x02:
                wbl_f.setEnabled(true);
                wbl_o.setEnabled(false);
                break;
            default:
                break;
        }
        switch (i3) {
            case 0x00:
                wbr_f.setEnabled(false);
                wbr_o.setEnabled(true);
                break;
            case 0x01:
                wbr_f.setEnabled(true);
                wbr_o.setEnabled(false);
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
            case 0://单次
                break;
            case 1://低速
                break;
            case 2://高速
                break;
            case 3://间歇
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

    private void setHsjViewOnClickLeft() {
        mLeftViewLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "2", Toast.LENGTH_SHORT).show();
            }
        });
        mRightViewLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mTopViewLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mBottomViewLeft.setOnClickListener(new View.OnClickListener() {
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

        new PathInfo.Builder(new OvalRingPathGenerator(0.05f, 45, 90), mBottomViewLeft)
                .create()
                .apply();
        new PathInfo.Builder(new OvalRingPathGenerator(0.05f, 135, 90), mLeftViewLeft)
                .create()
                .apply();
        new PathInfo.Builder(new OvalRingPathGenerator(0.05f, 225, 90), mTopViewLeft)
                .create()
                .apply();
        new PathInfo.Builder(new OvalRingPathGenerator(0.05f, 315, 90), mRightViewLeft)
                .create()
                .apply();
    }
}
