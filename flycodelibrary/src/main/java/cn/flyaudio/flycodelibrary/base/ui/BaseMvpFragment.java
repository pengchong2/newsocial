package cn.flyaudio.flycodelibrary.base.ui;


import android.app.ProgressDialog;
import android.os.Bundle;

import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import cn.flyaudio.flycodelibrary.R;
import cn.flyaudio.flycodelibrary.base.presenter.BaseMvpPresenter;
import cn.flyaudio.flycodelibrary.base.model.BaseMvpModel;
import cn.flyaudio.flycodelibrary.utils.TUtil;
/**
 * @className BaseMvpFragment
 * @createDate 2018/11/13 9:33
 * @author yxy
 * @desc  MVP模式中的UI层的Fragment抽象类，提供了mvp模式下Fragment的基础通用功能
 *
 */
public abstract class BaseMvpFragment<T extends BaseMvpPresenter, E extends BaseMvpModel> extends BaseFragment implements BaseMvpView {
    /**
     * 业务处理对象
     */
    public T mPresenter;
    /**
     * 数据处理对象
     */
    public E mModel;
    /**
     * 默认系统加载进度框
     */
    private ProgressDialog dialog;


    @Override
    public void initView(View view, @Nullable Bundle savedInstanceState) {
        Log.d("fragment","initView this = "+this);
        if (this instanceof BaseMvpView) {
            //通过反射实例化
            mPresenter = TUtil.getT(this, 0);
            mModel = TUtil.getT(this, 1);
            Log.d("present","mPresenter = "+mPresenter.getClass().getSimpleName()+" mModel = "+mModel.getClass().getSimpleName());
            mPresenter.attach(this, mModel);
        }
        Log.d("fragment","mPresenter = "+mPresenter);
        initMvpView(view, savedInstanceState);
    }

    /**
     * 初始化mvp模式下的view
     */
    protected abstract void initMvpView(View view, @Nullable Bundle savedInstanceState);

    /**
     * 默认显示loading实现
     */
    @Override
    public void showLoading() {

//        dialog = new ProgressDialog(getContext(),R.style.Theme_Dialog);
//        dialog.setMessage(getString(R.string.default_loading));
//        dialog.setCancelable(false);
//        dialog.setCanceledOnTouchOutside(false);
//        dialog.show();

    }

    /**
     * 默认关闭loading实现
     */
    @Override
    public void hideLoading() {

//        if (dialog != null && dialog.isShowing()) {
//            dialog.dismiss();
//        }

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
     * 销毁前清理
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detach();
        }
    }




}
