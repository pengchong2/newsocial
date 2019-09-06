package cn.flyaudio.flyforum.ui.activities.AcsPresenter;

import com.lzy.okgo.model.Response;

import java.util.List;

import cn.flyaudio.flycodelibrary.base.presenter.BaseMvpPresenter;
import cn.flyaudio.flyforum.bean.ActivityListBean;
import cn.flyaudio.flyforum.bean.BaseBean;
import cn.flyaudio.flyforum.bean.InfoListBean;
import cn.flyaudio.flyforum.ui.activities.contract.AcsContract;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 叶兴运 on
 * 2019/1/24.10:28
 */
public class AcsPresenter extends AcsContract.Presenter {

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void lodeAcsList(int index, int page,boolean isLoadMore) {
        getView().showLoading();
        mModel.lodeAcsList(index, page,isLoadMore).subscribe(new Observer<Response<ActivityListBean>>() {
            private int total = 0;

            @Override
            public void onSubscribe(Disposable d) {
                mRxManage.add(d);
            }

            @Override
            public void onNext(Response<ActivityListBean> baseBeanResponse) {
                if (baseBeanResponse.code() == 200) {
                    List<ActivityListBean.ListBean> list = baseBeanResponse.body().getList();
                    total = baseBeanResponse.body().getTotal();
                    if (list != null  && total > 0) {
                        if (isLoadMore){
                            getView().loadMoreDataSuccess(list);
                        }else {

                            getView().returnDataSuccess(list);
                        }
                    } else {
                        getView().onEmpty();
                    }
                } else {
                    getView().onError("");
                }

            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                getView().onError("");
            }

            @Override
            public void onComplete() {
                getView().hideLoading();
            }
        });
    }
}
