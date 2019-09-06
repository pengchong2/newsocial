package cn.flyaudio.flycodelibrary.base.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import cn.flyaudio.flycodelibrary.R;
import cn.flyaudio.flycodelibrary.base.presenter.BaseMvpPresenter;
import cn.flyaudio.flycodelibrary.base.model.BaseMvpModel;
import cn.flyaudio.flycodelibrary.utils.TUtil;


/**
 * MVP模式中的UI层的Activity抽象类，提供了mvp模式下Activity的基础通用功能
 *
 * @author 叶兴运
 * @time 2017/3/16 13:38
 */
public abstract class BaseMvpActivity<P extends BaseMvpPresenter, M extends BaseMvpModel> extends BaseActivity implements BaseMvpView {
    /**
     * 业务处理对象
     */
    public P mPresenter;
    /**
     * 数据处理对象
     */
    public M mModel;

    /**
     * 默认系统加载进度框
     */
    private ProgressDialog dialog;

    @Override
    protected void initView(Bundle savedInstanceState) {
        // 初始化Presenter和Model,绑定view
        if ((this instanceof BaseMvpView)) {
            mPresenter = TUtil.getT(this, 0);
            mModel = TUtil.getT(this, 1);
            if (mPresenter != null) {
                mPresenter.attach(this, mModel);
            }
        }
        initMvpView(savedInstanceState);
    }
    @Override
    protected View getLayoutView(LayoutInflater layoutInflater, Bundle savedInstanceState) {
        return null;
    }

    /**
     * 默认显示loading实现
     */
    @Override
    public void showLoading() {
        if (dialog==null){
            dialog = new ProgressDialog(this,R.style.Theme_Dialog);
            dialog.setMessage(getString(R.string.default_loading));
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();
        }else {
            dialog.show();
        }
    }

    /**
     * 默认关闭loading实现
     */
    @Override
    public void hideLoading() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    /**
     * 默认显示错误信息的UI实现
     * @param error 异常信息
     */
    @Override
    public void onError(String error) {
        showToast(error);
    }

    /**
     * 默认显示空信息的UI实现
     *
     */
    @Override
    public void onEmpty( ) {
    }




    /**
     * 初始化mvp模式下的View
     * @param savedInstanceState
     */
    protected abstract void initMvpView(Bundle savedInstanceState);

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detach();
            mPresenter.onDestroy();
        }
    }

}
