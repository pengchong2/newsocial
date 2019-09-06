package cn.flyaudio.flycodelibrary.base.ui;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.CallSuper;
import androidx.annotation.ColorInt;

import cn.flyaudio.flycodelibrary.R;
import cn.flyaudio.flycodelibrary.utils.AppManager;
import cn.flyaudio.flycodelibrary.utils.ToastUtils;

/**
 * 提供了Activity的基础通用功能
 *
 * @author 叶兴运
 * @time 2017/3/16 13:38
 */
public abstract class BaseActivity extends Activity implements BaseView {

    public ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 把activity放到activity栈中管理
        AppManager.getAppManager().addActivity(this);
        LayoutInflater inflater = LayoutInflater.from(this);
        View rootView  = getLayoutView(inflater,savedInstanceState);
        if (rootView != null) {
            // 初始化控件
            setContentView(rootView);
        }
        initView(savedInstanceState);
    }


    /**
     * 设置布局View
     * @param layoutInflater
     * @param savedInstanceState
     * @return
     */
    protected abstract View getLayoutView(LayoutInflater layoutInflater,Bundle savedInstanceState);


    /**
     * 在这里做初始化View的操作
     *
     * @param savedInstanceState
     */
    protected abstract void initView(Bundle savedInstanceState);

    /**
     * 清理Activity栈
     */
    @CallSuper
    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getAppManager().finishActivity(this);
    }

    /**
     * 让状态栏全透明，并把布局延伸到状态栏，放弃5.0以下用户
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void setLayoutToTop() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

    /**
     * 设置状态栏颜色，放弃5.0以下用户
     *
     * @param statusColor 需要设置的颜色
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void setStatusColor(@ColorInt int statusColor) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (statusColor != -1) {
                getWindow().setStatusBarColor(statusColor);
            }
            return;
        }

    }

    /**
     * 通过Class跳转界面
     **/
    public void startActivity(Class<?> cls) {
        startActivity(cls, null);
    }

    /**
     * 通过Class跳转界面
     **/
    public void startActivityForResult(Class<?> cls, int requestCode) {
        startActivityForResult(cls, null, requestCode);
    }

    /**
     * 含有Bundle通过Class跳转界面
     **/
    public void startActivityForResult(Class<?> cls, Bundle bundle,
                                       int requestCode) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }


    public void showLoading() {
        dialog = new ProgressDialog(this,R.style.Theme_Dialog);
        dialog.setMessage(getString(cn.flyaudio.flycodelibrary.R.string.default_loading));
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }
    public void hideLoading() {
        if (dialog != null && dialog.isShowing()) {
            dialog.hide();
            dialog.dismiss();
        }
    }

    /**
     * 含有Bundle通过Class跳转界面
     **/
    public void startActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }
    public boolean isConn(){
        boolean bisConnFlag=false;
        //1.获取网络连接的管理对象
        ConnectivityManager conManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        //2.通过管理者对象拿到网络的信息
        NetworkInfo network = conManager.getActiveNetworkInfo();

        if(network!=null){
            //3.网络状态是否可用的返回值
            bisConnFlag=network.isAvailable();
        }

        return bisConnFlag;
    }

    /**
     * 默认显示Toast实现
     * @param msg msg
     */
    @Override
    public void showToast(String msg) {
//        Toast.makeText(this, ""+msg, Toast.LENGTH_SHORT).show();
        ToastUtils.showShort(msg);
    }
}
