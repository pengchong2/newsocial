package cn.flyaudio.flycodelibrary.base.presenter;

import android.os.Handler;
import android.os.Looper;


import androidx.annotation.Nullable;

import java.lang.ref.WeakReference;

import cn.flyaudio.flycodelibrary.base.baserx.RxManager;
import cn.flyaudio.flycodelibrary.base.model.BaseMvpModel;
import cn.flyaudio.flycodelibrary.base.ui.BaseMvpView;
import cn.flyaudio.flycodelibrary.utils.NetworkUtils;

/**
 * @author 叶兴运
 */
public abstract class BaseMvpPresenter<V extends BaseMvpView, M extends BaseMvpModel> {
    /**
     * 弱引用，避免内存泄漏
     */
    private WeakReference<V> mReference;
    /**
     * 数据处理对象
     */
    protected M mModel;
    /**
     * UI线程的Handler
     */
    protected Handler mUiHandler = new Handler(Looper.getMainLooper());


    public RxManager mRxManage = new RxManager();
    /**
     * 从子类拿到view实例
     *
     * @return
     */
    @Nullable
    public final V getView() {
        return mReference == null ? null : mReference.get();
    }

    /**
     * 返回当前view接口引用是否不为空
     * @return 当前view接口引用是否不为空
     */
    protected boolean viewIsNotNull(){
        return mReference == null ? false : mReference.get() != null;
    }

    /**
     * 绑定view
     */

   public final void attach(V view, M m) {
        this.mReference = new WeakReference<V>(view);
        this.mModel = m;
        this.onStart();
    }

    /**
     * 解绑view
     */

   public final void detach() {
        mUiHandler.removeCallbacksAndMessages(null);
        mUiHandler = null;
        mReference.clear();
        mReference = null;
        mModel = null;
    }
    /**
     * 检查网络是否可用
     * @return
     */
    public void onStart(){
    }
    public void onDestroy() {
        mRxManage.clear();
    }
    protected   boolean canUseNetWork(){
       return NetworkUtils.isConnected();
    }
}
