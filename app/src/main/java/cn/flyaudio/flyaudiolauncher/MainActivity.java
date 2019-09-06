package cn.flyaudio.flyaudiolauncher;

import android.appwidget.AppWidgetHostView;
import android.appwidget.AppWidgetProviderInfo;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.util.Pair;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.LatLng;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import cn.flyaudio.flyaudiolauncher.amap.AMapNaviActivity;
import cn.flyaudio.flyaudiolauncher.amap.AMapPresenterImpl;
import cn.flyaudio.flyaudiolauncher.amap.IAMapPresenter;
import cn.flyaudio.flyaudiolauncher.amap.IAmapView;
import cn.flyaudio.flyaudiolauncher.fragments.CarInfoFragment;
import cn.flyaudio.flyaudiolauncher.fragments.FlyUnionFragment;
import cn.flyaudio.flyaudiolauncher.presentation.FlyPresentation;
import cn.flyaudio.flyaudiolauncher.utils.AppManager;
import cn.flyaudio.flyaudiolauncher.utils.FileUtils;
import cn.flyaudio.flyaudiolauncher.utils.SharedPreferencesUtils;
import cn.flyaudio.flyaudiolauncher.view.DrawerLayout;
import cn.flyaudio.flyaudiolauncher.widget.WidgetHostViewLoader;

/**
 * @author weifule
 * @date 19-7-23
 * Email: fulewei@foxmail.com
 * Description:
 */
public class MainActivity extends CheckPermissionsActivity implements IAmapView {

    private static final String TAG = "FlyaudioLauncher";

    private FragmentManager fragmentManager;
    private CarInfoFragment carInfoFragment;
    private FlyUnionFragment flyUnionFragment;

    private DrawerLayout drawerLayout;
    private AMap aMap;
    private MapView mapView;
    private RelativeLayout appwidget_audio;
    private RelativeLayout appwidget_bluetooth;
    private WidgetHostViewLoader audioLoader;
    private WidgetHostViewLoader btLoader;
    private WidgetHostViewLoader weatherLoader;

    private IAMapPresenter aMapPresenter;
    private FlyPresentation presentation;

    private Button bt_simulat_navi, bt_start_navi, bt_stop_navi;

    private String[][] appWidgetConfig = {
            {"com.flyaudio.mediawidget","com.flyaudio.mediawidget.view.MediaWidget"},
            {"cn.flyaudio.btwidget","cn.flyaudio.btwidget.BtAppWidget"},
            {"cn.flyaudio.Weather","cn.flyaudio.weather.WeatherWidget"}
    };

    private String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        path = "/storage/emulated/0/FlyLog/" + "/logcat.txt";
        FileUtils.writer(path,"onCreate:");
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
        initView(savedInstanceState);
        loadAudioWidget();
        loadBtWidget();
        loadWeatherWidget();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
        FileUtils.writer(path,"onStart:");
        if (audioLoader != null){
            audioLoader.onStart();
        }
        if (btLoader != null){
            btLoader.onStart();
        }
        if (weatherLoader != null){
            weatherLoader.onStart();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
        FileUtils.writer(path,"onResume:");
        if (mapView != null){
            mapView.onResume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mapView != null){
            mapView.onPause();
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        if (mapView != null){
            mapView.onSaveInstanceState(outState);
        }
    }

    private void initView(Bundle savedInstanceState){
        drawerLayout = findViewById(R.id.drawer_layout);
        drawerLayout.setCustomLeftEdgeSize(drawerLayout, 0.1f);
        drawerLayout.setCustomRightEdgeSize(drawerLayout, 0.1f);
        initMapView(savedInstanceState);
        presentation = new FlyPresentation(this, aMap);
    }

    private void initMapView(Bundle savedInstanceState) {
        aMapPresenter = new AMapPresenterImpl(this);
        mapView = findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);
        aMap = mapView.getMap();
        aMapPresenter.onCreatMap(aMap,AMap.MAP_TYPE_NIGHT);
        //aMapPresenter.setTrafficEnabled(true);

        //模拟导航
        bt_simulat_navi = findViewById(R.id.bt_simulat_navi);
        bt_simulat_navi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (presentation != null) {
                    presentation.startNavi();
                }
            }
        });

        //发起导航
        bt_start_navi = findViewById(R.id.bt_start_navi);
        bt_start_navi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LatLng startLatLng, endLatLng;
                Bundle localBundle = aMapPresenter.getLocalBundle();
                Bundle poiBundle = aMapPresenter.getPoiBundle();
                startLatLng = localBundle.getParcelable("localLatLng");
                endLatLng = poiBundle.getParcelable("poiLatLng");
                if (startLatLng != null && endLatLng != null) {
                    Log.d(TAG, "bt_start_navi startLatLng : " + startLatLng + " endLatLng : " + endLatLng);
                    aMap.clear();
                    presentation.startNavi(startLatLng, endLatLng);
                }
            }
        });

        //结束导航
        bt_stop_navi = findViewById(R.id.bt_stop_navi);
        bt_stop_navi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppManager.getAppManager().finishActivity(AMapNaviActivity.class);
            }
        });


    }

    private FrameLayout.LayoutParams setWidgetLayoutParams(AppWidgetHostView audioWidgetHostView) {
        FrameLayout.LayoutParams ll = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        ll.setMargins(0, 0, 0, 0);
        audioWidgetHostView.setPadding(0, 0, 0, 0);
        audioWidgetHostView.getChildAt(0).setPadding(0, 0, 0, 0);
        return ll;
    }

    private void loadAudioWidget(){
        appwidget_audio = findViewById(R.id.appwidget_audio);
        audioLoader = new WidgetHostViewLoader(this, 1981);
        int mediaId = SharedPreferencesUtils.getInt(this, SharedPreferencesUtils.MEDIA_APP_WIDGET_ID, -1);
        FileUtils.writer(path,"loadAudioWidget mediaId111 : " + mediaId);
        if (mediaId != -1){
            audioLoader.getAppWidgetHost().deleteAppWidgetId(mediaId);
        }
        Pair<Integer,AppWidgetProviderInfo> info= audioLoader.buildAppWidgetInfo(this, appWidgetConfig[0][0],
                appWidgetConfig[0][1]);
        if (info != null) {
            SharedPreferencesUtils.putInt(this, SharedPreferencesUtils.MEDIA_APP_WIDGET_ID, info.first);
            FileUtils.writer(path,"loadAudioWidget mediaId222 : " + info.first);
            AppWidgetHostView audioWidgetHostView = audioLoader.preloadWidget(info.first);
            FrameLayout.LayoutParams ll = setWidgetLayoutParams(audioWidgetHostView);
            appwidget_audio.addView(audioWidgetHostView,ll);
        }else{
            SharedPreferencesUtils.remove(this, SharedPreferencesUtils.MEDIA_APP_WIDGET_ID);
        }
    }

    private void loadBtWidget(){
        appwidget_bluetooth = findViewById(R.id.appwidget_bluetooth);
        btLoader = new WidgetHostViewLoader(this, 1982);
        int btId = SharedPreferencesUtils.getInt(this, SharedPreferencesUtils.BT_APP_WIDGET_ID, -1);
        if (btId != -1){
            btLoader.getAppWidgetHost().deleteAppWidgetId(btId);
        }
        Pair<Integer,AppWidgetProviderInfo> info= btLoader.buildAppWidgetInfo(this, appWidgetConfig[1][0],
                appWidgetConfig[1][1]);
        if (info != null) {
            SharedPreferencesUtils.putInt(this, SharedPreferencesUtils.BT_APP_WIDGET_ID, info.first);
            AppWidgetHostView btWidgetHostView = btLoader.preloadWidget(info.first);
            FrameLayout.LayoutParams ll = setWidgetLayoutParams(btWidgetHostView);
            appwidget_bluetooth.addView(btWidgetHostView,ll);
        }else {
            SharedPreferencesUtils.remove(this, SharedPreferencesUtils.BT_APP_WIDGET_ID);
        }
    }

    private void loadWeatherWidget(){
        LinearLayout linearLayout = findViewById(R.id.ll_time_weather);
        weatherLoader = new WidgetHostViewLoader(this, 1983);
        Pair<Integer,AppWidgetProviderInfo> info= weatherLoader.buildAppWidgetInfo(this,appWidgetConfig[2][0],
                appWidgetConfig[2][1]);
        AppWidgetHostView audioWidgetHostView = weatherLoader.preloadWidget(info.first);
        FrameLayout.LayoutParams ll = setWidgetLayoutParams(audioWidgetHostView);
        linearLayout.addView(audioWidgetHostView,ll);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (aMapPresenter != null){
            aMapPresenter.destroyAMap();
        }
        if (audioLoader != null){
            audioLoader.releaseWidget();
        }
        if (btLoader != null){
            btLoader.releaseWidget();
        }
        if (weatherLoader != null){
            weatherLoader.releaseWidget();
        }
        if (presentation != null){
            presentation.releasePresentation();
        }
        if (mapView != null){
            mapView.onDestroy();
        }
        EventBus.getDefault().unregister(this);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            moveTaskToBack(false);
            drawerLayout.closeDrawers();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onReceiveMsg(EventMessage message) {
        Log.e(TAG, "onReceiveMsg: " + message.toString());
        if ("onActivityCreated".equals(message.getType()) && "AMapNaviActivity".equals(message.getMessage())) {
            if (presentation != null) {
                presentation.disPresentation();
            }
        }else if ("onActivityDestroyed".equals(message.getType()) && "AMapNaviActivity".equals(message.getMessage())) {
            if (presentation != null) {
                presentation.showPresentation();
            }
        }
    }

    @Override
    public void updatePersent() {
        presentation.updatePersent();
    }
}
