package cn.flyaudio.flyforum.ui.activities.model;


import com.lzy.okgo.OkGo;
import com.lzy.okgo.convert.StringConvert;
import com.lzy.okgo.model.Response;
import com.lzy.okrx2.adapter.ObservableBody;
import com.lzy.okrx2.adapter.ObservableResponse;


import cn.flyaudio.flyforum.Urls;
import cn.flyaudio.flyforum.bean.ActivityListBean;
import cn.flyaudio.flyforum.bean.BaseBean;
import cn.flyaudio.flyforum.ui.activities.contract.AcsContract;
import cn.flyaudio.flyforum.util.JsonConvert;
import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 叶兴运 on
 * 2019/1/24.11:08
 */
public class AcsModel implements AcsContract.Model {
    @Override
    public Observable<Response<ActivityListBean>> lodeAcsList(int index,int page,boolean isLoadMore) {
        Observable<Response<ActivityListBean>> call = OkGo.<ActivityListBean>get(Urls.getActivityList)
                .params("pageIndex", index)
                .params("pageSize", page)
                .converter(new JsonConvert<ActivityListBean>() {
                })
                .adapt(new ObservableResponse<ActivityListBean>());
        return call;

    }


}
