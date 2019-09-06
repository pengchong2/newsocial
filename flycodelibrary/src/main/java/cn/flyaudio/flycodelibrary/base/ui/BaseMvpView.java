package cn.flyaudio.flycodelibrary.base.ui;

/**
 * @author 叶兴运
 * 因为现在没有统一的UI标准，所以暂时就这三个基础接口
 */
public interface BaseMvpView extends BaseView{
    /**
     * 显示loading
     */
    void showLoading();

    /**
     * 隐藏loading
     */
    void hideLoading();

    /**
     * 显示异常信息
     * @param error 异常信息
     */
    void onError(String error); /**
     * 显示空数据信息
     */
    void onEmpty();
}
