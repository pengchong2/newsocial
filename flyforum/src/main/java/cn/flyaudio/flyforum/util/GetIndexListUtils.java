package cn.flyaudio.flyforum.util;

import android.util.Log;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.squareup.okhttp.OkHttpClient;

import java.util.ArrayList;
import java.util.List;

import cn.flyaudio.flyforum.Urls;
import cn.flyaudio.flyforum.bean.BrandBean;
import cn.flyaudio.flyforum.bean.IndexBean;

public class GetIndexListUtils {
    private static final String TAG = "GetWeiboListUtils";
    public static boolean isLoadData;
    public static String urlHead = "http://120.24.250.191:9898/jeesns-web";
    private static GetIndexListUtils weiboListUtilsInstant;
    private GetWeiboCallback getWeiboCallback;


    public static interface GetWeiboCallback {
        void getdataSuccess(IndexBean body);

        void getBranchSuccess(List<BrandBean.ZBean> zBeanList);

        void onError(String msg);
    }

    public static GetIndexListUtils getInstant() {
        if (weiboListUtilsInstant == null) {
            weiboListUtilsInstant = new GetIndexListUtils();
        }
        return weiboListUtilsInstant;
    }


    public void requestData(int pageIndex, int pageSize) {
        isLoadData = true;
        OkGo.<IndexBean>get(Urls.getIndexList)
                .params("pageIndex", pageIndex)
                .params("pageSize", pageSize)
                .execute(new JsonCallback<IndexBean>() {
                    @Override
                    public void onError(com.lzy.okgo.model.Response<IndexBean> response) {
//                        getWeiboCallback.onError(response.body().getError().getMessage()+"");
                        getWeiboCallback.onError(response.message() + "");
                        isLoadData = false;
                    }

                    @Override
                    public void onSuccess(com.lzy.okgo.model.Response<IndexBean> response) {
                        isLoadData = false;
                        if (response.code() == 200) {
                            response.body();
                            getWeiboCallback.getdataSuccess(response.body());
                        } else {
                            getWeiboCallback.onError(response.body().getError().getMessage() + "");
                        }
                    }
                });


    }

    public void getBranchList() {
        OkGo.<BrandBean>get(Urls.getBrandList).execute(new JsonCallback<BrandBean>() {
            @Override
            public void onError(Response<BrandBean> response) {
                Log.d("yxy", response.code() + "");
            }

            @Override
            public void onSuccess(Response<BrandBean> response) {
                if (response.code() == 200) {
                    BrandBean body = response.body();
                    List<BrandBean.ZBean> list = new ArrayList<>();
                    list.addAll(body.getA());
                    list.addAll(body.getB());
                    list.addAll(body.getC());
                    list.addAll(body.getD());
                    list.addAll(body.getE());
                    list.addAll(body.getF());
                    list.addAll(body.getG());
                    list.addAll(body.getH());
                    list.addAll(body.getI());
                    list.addAll(body.getJ());
                    list.addAll(body.getK());
                    list.addAll(body.getL());
                    list.addAll(body.getM());
                    list.addAll(body.getN());
                    list.addAll(body.getO());
                    list.addAll(body.getP());
                    list.addAll(body.getQ());
                    list.addAll(body.getR());
                    list.addAll(body.getS());
                    list.addAll(body.getT());
                    list.addAll(body.getU());
                    list.addAll(body.getV());
                    list.addAll(body.getW());
                    list.addAll(body.getX());
                    list.addAll(body.getY());
                    list.addAll(body.getZ());
                    getWeiboCallback.getBranchSuccess(list);


                }
            }
        });
    }


    public void registInterface(GetWeiboCallback callback) {
        this.getWeiboCallback = callback;
    }


}
