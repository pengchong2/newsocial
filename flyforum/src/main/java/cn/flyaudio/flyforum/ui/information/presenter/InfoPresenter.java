package cn.flyaudio.flyforum.ui.information.presenter;

import com.lzy.okgo.model.Response;

import java.util.List;

import cn.flyaudio.flyforum.baserx.RxManager;
import cn.flyaudio.flyforum.bean.InfoListBean;
import cn.flyaudio.flyforum.ui.information.contract.InfoContract;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 叶兴运 on
 * 2019/2/16.15:19
 */
public class InfoPresenter extends InfoContract.InfoPresenter {
    @Override
    public void loadInfoList(int page, int pageSize,boolean isLoadMore) {
        getView().showLoading();
        mModel.loadInfoList(page, pageSize, isLoadMore)
                .observeOn(AndroidSchedulers.mainThread())//回调在主线程
                .subscribeOn(Schedulers.io())//执行在io
                .subscribe(new Observer<Response<InfoListBean>>() {

                    private int total = 0;

                    @Override
            public void onSubscribe(Disposable d) {
                mRxManage.add(d);//添加到管理中避免内存泄漏
            }

            @Override
            public void onNext(Response<InfoListBean> infoListBeanResponse) {
                if (infoListBeanResponse.code() == 200) {
                    List<InfoListBean.ListBean> list = infoListBeanResponse.body().getList();
                    total = infoListBeanResponse.body().getTotal();
                    if (list.size()==0&& total ==0){
                        getView().onEmpty();
                    }else {
                        if (isLoadMore){
                          getView().loadMoreSuccess(list);
                        }{
                            getView().loadSuccess(list);
                        }
                    }
                } else {
                    getView().onError("");
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
