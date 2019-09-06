package cn.flyaudio.flyforum.ui.activities.contract;


import com.lzy.okgo.model.Response;

import java.util.List;

import cn.flyaudio.flycodelibrary.base.model.BaseMvpModel;
import cn.flyaudio.flycodelibrary.base.presenter.BaseMvpPresenter;
import cn.flyaudio.flycodelibrary.base.ui.BaseMvpView;
import cn.flyaudio.flyforum.bean.ActivityListBean;
import io.reactivex.Observable;

/**
 * Created by 叶兴运 on
 * 2019/1/24.10:46
 */
public interface AcsContract   {
    interface Model extends BaseMvpModel {
       Observable<Response<ActivityListBean>> lodeAcsList(int index ,int page,boolean isLoadMore);
    }
    interface AcsView extends BaseMvpView {
        void returnDataSuccess(List<ActivityListBean.ListBean> activityBean);
        void loadMoreDataSuccess(List<ActivityListBean.ListBean> activityBean);

    }
    abstract static class Presenter extends BaseMvpPresenter<AcsView, Model> {
        public abstract void lodeAcsList(int index,int page,boolean isLoadMore);
//        public abstract void onItemSwap(ArrayList<NewsChannelTable> newsChannelTableList, int fromPosition, final int toPosition);
//
//        public abstract void onItemAddOrRemove(ArrayList<NewsChannelTable> mineChannelTableList, ArrayList<NewsChannelTable> moreChannelTableList);
    }
}
