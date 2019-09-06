package cn.flyaudio.flyforum.ui.information.contract;

import com.lzy.okgo.model.Response;

import java.util.List;

import cn.flyaudio.flycodelibrary.base.model.BaseMvpModel;
import cn.flyaudio.flycodelibrary.base.presenter.BaseMvpPresenter;
import cn.flyaudio.flycodelibrary.base.ui.BaseMvpView;
import cn.flyaudio.flyforum.bean.InfoListBean;
import io.reactivex.Observable;

/**
 * Created by 叶兴运 on
 * 2019/2/16.14:47
 */
public interface InfoContract   {
    interface InfoModel extends BaseMvpModel{
        Observable<Response<InfoListBean>> loadInfoList(int page, int pageSize,boolean isLoadMore);
    }

    interface InfoView extends BaseMvpView {
        void loadSuccess(List<InfoListBean.ListBean> infoListBean);
        void loadMoreSuccess(List<InfoListBean.ListBean> infoListBean);
    }

     abstract  class InfoPresenter extends BaseMvpPresenter<InfoView,InfoModel> {
       public abstract void loadInfoList(int page,int pageSize,boolean isLoadMore);
    }
}
