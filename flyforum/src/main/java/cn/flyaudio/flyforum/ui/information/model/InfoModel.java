package cn.flyaudio.flyforum.ui.information.model;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okrx2.adapter.ObservableResponse;

import cn.flyaudio.flyforum.Urls;
import cn.flyaudio.flyforum.bean.InfoListBean;
import cn.flyaudio.flyforum.ui.information.contract.InfoContract;
import cn.flyaudio.flyforum.util.JsonConvert;
import io.reactivex.Observable;

/**
 * Created by 叶兴运 on
 * 2019/2/16.15:10
 */
public class InfoModel implements InfoContract.InfoModel {
    @Override
    public Observable<Response<InfoListBean>> loadInfoList(int page, int pageSize,boolean isLoadMore) {
        Observable<Response<InfoListBean>> call = OkGo.<InfoListBean>get(Urls.getInfoList)
                .params("pageIndex", page)
                .params("pageSize", pageSize)
                .converter(new JsonConvert<InfoListBean>(){})
                .adapt(new ObservableResponse<InfoListBean>());
        return call;

    }
}
