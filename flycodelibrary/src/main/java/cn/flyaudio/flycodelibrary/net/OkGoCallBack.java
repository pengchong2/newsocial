package cn.flyaudio.flycodelibrary.net;


import com.lzy.okgo.callback.Callback;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;

/**
 * @author newtrekWang
 * @fileName OkGoCallBack
 * @createDate 2018/12/5 11:14
 * @email 408030208@qq.com
 * @desc  自定义通用CallBack，定制想要暴露给用户的回调接口
 */
public abstract class OkGoCallBack<T> implements Callback<T> {
    /**
     * 不给用户暴露此接口
     * @param response
     */
    @Override
    final public void onCacheSuccess(Response<T> response) {
    }
    /**
     * 不给用户暴露此接口
     * @param progress
     */
    @Override
   final   public void uploadProgress(Progress progress) {

    }

    /**
     * 不给用户暴露此接口
     * @param progress
     */
    @Override
    final public void downloadProgress(Progress progress) {

    }

    /**
     * 子类可实现也可不实现
     */
    @Override
    public void onFinish() {
    }

    /**
     * 子类可实现也可不实现
     * @param request
     */
    @Override
    public void onStart(Request<T, ? extends Request> request) {
    }

    /**
     * 子类可实现也可不实现
     * @param response
     */
    @Override
    public void onError(Response<T> response) {
    }
}
