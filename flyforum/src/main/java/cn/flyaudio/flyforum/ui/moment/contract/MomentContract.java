package cn.flyaudio.flyforum.ui.moment.contract;

import com.lzy.okgo.model.Response;

import java.util.List;

import cn.flyaudio.flycodelibrary.base.model.BaseMvpModel;
import cn.flyaudio.flycodelibrary.base.presenter.BaseMvpPresenter;
import cn.flyaudio.flycodelibrary.base.ui.BaseMvpView;
import cn.flyaudio.flyforum.bean.InfoListBean;
import cn.flyaudio.flyforum.bean.MomentBean;
import cn.flyaudio.flyforum.ui.moment.model.MomentModel;
import io.reactivex.Observable;

/**
 * Created by 叶兴运 on
 * 2019/2/16.14:47
 */
public interface MomentContract   {
    interface MModel extends BaseMvpModel{
        Observable<Response<MomentBean>> loadMomentList(int page, int pageSize,boolean isLoadmore);
    }

    interface MomentView extends BaseMvpView {
        void loadSuccess(List<MomentBean.ListBean> momentList);
        void loadMoreSuccess(List<MomentBean.ListBean> momentList);
    }

     abstract  class MoPresenter extends BaseMvpPresenter<MomentView,MomentModel> {
       public abstract void loadMomentList(int page,int pageSize,boolean isLoadmore);
    }
}
