package cn.flyaudio.flyforum.ui.moment.presenter;

import com.lzy.okgo.model.Response;

import java.util.List;

import cn.flyaudio.flyforum.bean.InfoListBean;
import cn.flyaudio.flyforum.bean.MomentBean;
import cn.flyaudio.flyforum.ui.moment.contract.MomentContract;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 叶兴运 on
 * 2019/2/16.15:19
 */
public class MomentPresenter extends MomentContract.MoPresenter {
    @Override
    public void loadMomentList(int page, int pageSize,boolean isLoadmore) {
        getView().showLoading();
        mModel.loadMomentList(page, pageSize,isLoadmore)
                .observeOn(AndroidSchedulers.mainThread())//回调在主线程
                .subscribeOn(Schedulers.io())//执行在io
                .subscribe(new Observer<Response<MomentBean>>() {

                    private int total = 0;

                    @Override
                    public void onSubscribe(Disposable d) {
                        mRxManage.add(d);//添加到管理中避免内存泄漏
                    }

                    @Override
                    public void onNext(Response<MomentBean> momentResponse) {
                        if (momentResponse.code() == 200) {
                            List<MomentBean.ListBean> list = momentResponse.body().getList();
                            total = momentResponse.body().getTotal();
                            if (list.size() == 0 && total == 0) {
                                getView().onEmpty();
                            } else {
                                if (isLoadmore){
                                    getView().loadMoreSuccess(list);
                                }else {

                                    getView().loadSuccess(list);
                                }
                            }
                        } else {
                            getView().onError(momentResponse.message() + "");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().onError(e.getMessage() + "");
                    }

                    @Override
                    public void onComplete() {
                        getView().hideLoading();
                    }
                });
    }
}
