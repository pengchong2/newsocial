package cn.flyaudio.flyforum.ui.moment.model;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okrx2.adapter.ObservableResponse;

import cn.flyaudio.flyforum.Urls;
import cn.flyaudio.flyforum.bean.InfoListBean;
import cn.flyaudio.flyforum.bean.MomentBean;

import cn.flyaudio.flyforum.ui.moment.contract.MomentContract;
import cn.flyaudio.flyforum.util.JsonConvert;
import io.reactivex.Observable;

/**
 * Created by 叶兴运 on
 * 2019/2/16.15:10
 */
public class MomentModel implements MomentContract.MModel {
    @Override
    public Observable<Response<MomentBean>> loadMomentList(int page, int pageSize,boolean isLoadmore) {
        Observable<Response<MomentBean>> call = OkGo.<MomentBean>get(Urls.getMomentList)
                .params("pageIndex", page)
                .params("pageSize", pageSize)
                .converter(new JsonConvert<MomentBean>(){})
                .adapt(new ObservableResponse<MomentBean>());
        return call;

    }
}
