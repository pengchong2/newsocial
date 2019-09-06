package cn.flyaudio.flyforum;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.List;

import cn.flyaudio.flycodelibrary.base.ui.BaseMvpFragment;
import cn.flyaudio.flycodelibrary.utils.L;
import cn.flyaudio.flycodelibrary.utils.ToastUtils;
import cn.flyaudio.flyforum.adapter.MomentAdapter;
import cn.flyaudio.flyforum.bean.MomentBean;
import cn.flyaudio.flyforum.ui.moment.MomentDetailActivity;
import cn.flyaudio.flyforum.ui.moment.contract.MomentContract;
import cn.flyaudio.flyforum.ui.moment.model.MomentModel;
import cn.flyaudio.flyforum.ui.moment.presenter.MomentPresenter;

public class MomentFragment extends BaseMvpFragment<MomentPresenter, MomentModel> implements MomentContract.MomentView  {
    private static final String TAG = "MomentFragment";

    private RecyclerView rvMoment;
    private SwipeRefreshLayout momentRefesh;
    private MomentAdapter adapter;
    private TextView tvNonet;

    private List<MomentBean.ListBean> listBeans;
    private int pageIndex = 1;
    private int pageSize = 20;

    private void putZan(String uuid,boolean praiseFlag) {
        OkGo.<String>put(Urls.putMomentZan)
                .params("uuid", uuid)
                .params("type", 1)//代表 ：此刻
                .params("praiseFlag", praiseFlag)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if (response.code() == 200) {
                            L.d(TAG, response.body() + "");
                            L.d("点赞//取消成功");
                            ToastUtils.showShort(response.body().substring(response.body().length()-6,response.body().length()-2)+"");
//                            mPresenter.loadMomentList();
                            adapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        L.d("点赞失败");
                        L.d(response.body() + "");
                        L.d(response.message() + "");
                        L.d(response.code() + "");
                        ToastUtils.showShort(getResources().getString(R.string.errortips));
                    }
                });
    }

    @Override
    protected void initMvpView(View view, @Nullable Bundle savedInstanceState) {
        rvMoment = view.findViewById(R.id.rv_momentlist);

        tvNonet = (TextView) view.findViewById(R.id.tv_nonet);
        tvNonet.setVisibility(View.GONE);
        tvNonet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.loadMomentList(1,pageSize,false);
            }
        });

        rvMoment.setLayoutManager(new MomentFragment.MyContentLinearLayoutManager(getContext()));
        momentRefesh = view.findViewById(R.id.momentRefresh);
        listBeans = new ArrayList<>();
        adapter = new MomentAdapter(R.layout.rv_moment_item,
                listBeans);
        rvMoment.setAdapter(adapter);
        momentRefesh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.loadMomentList(1, pageSize,false);
            }
        });
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Log.d(TAG, "onItemChildClick: ");
                MomentBean.ListBean listBean = listBeans.get(position);
                int isPraise = listBean.getIsPraise();
                TextView praise = view.findViewById(R.id.moment_zan);
                if (isPraise==1){
                    int likenum = Integer.parseInt(listBean.getLikeNumber()) - 1;
                    listBean.setIsPraise(0);
                    listBean.setLikeNumber(likenum+"");
                    praise.setText(likenum +"");
                    praise.setTextColor(getResources().getColor(R.color.colorWhite));
                    Drawable drawable = getResources().getDrawable(R.mipmap.moment_zan);
                    drawable.setBounds(0, 0, px2dip(33), px2dip(33));
                    praise.setCompoundDrawables(drawable, null, null, null);
                }else {
                    int likenum = Integer.parseInt(listBean.getLikeNumber()) + 1;
                    praise.setText(likenum +"");
                    listBean.setIsPraise(1);
                    listBean.setLikeNumber(likenum+"");
                    praise.setTextColor(getResources().getColor(R.color.acs_tagcolor));
                    Drawable drawable = getResources().getDrawable(R.mipmap.praise_press);
                    drawable.setBounds(0, 0, px2dip(33), px2dip(33));
                    praise.setCompoundDrawables(drawable, null, null, null);
                }

                putZan(listBean.getUuid(), isPraise ==0?true:false);

            }
        });

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                MomentBean.ListBean listBean = listBeans.get(position);
                Intent intent = new Intent(getActivity(), MomentDetailActivity.class);
                intent.putExtra("uuid", listBean.getUuid());
                startActivity(intent);
            }

        });
        adapter.setOnLoadMoreListener(new MyLoadMoreListener(), rvMoment);
        if (isConn()) {
            mPresenter.loadMomentList(1, pageSize,false);
        } else {
            onError("网络未连接，请先检查网络");
        }
    }


    /**
     * 重新连接网络上后刷新数据
     */
    public void refreshData(){
        mPresenter.loadMomentList(1, pageSize,false);
    }

    @Override
    public View getLayoutView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view =  inflater.inflate(R.layout.activity_moment,container,false);
     //  initMvpView(view,savedInstanceState);
        return view;
    }

    @Override
    public void initData() {

    }

    @Override
    public void loadSuccess(List<MomentBean.ListBean> momentList) {
        tvNonet.setVisibility(View.GONE);
        Log.d(TAG, "loadSuccess: " + momentList.size());
        if (listBeans != null && listBeans.size() > 0) {
            listBeans.clear();
        }
        this.listBeans = momentList;
        adapter.setNewData(momentList);
        adapter.notifyDataSetChanged();
        adapter.loadMoreEnd();
        adapter.loadMoreComplete();
        if (momentRefesh.isRefreshing()) {
            momentRefesh.setRefreshing(false);
        }
    }

    @Override
    public void loadMoreSuccess(List<MomentBean.ListBean> momentList) {
        tvNonet.setVisibility(View.GONE);
        Log.d(TAG, "loadSuccess: " + momentList.size());
//        if (listBeans != null && listBeans.size() > 0) {
//            listBeans.clear();
//        }
        this.listBeans .addAll(momentList);
        adapter.addData(momentList);
        adapter.notifyDataSetChanged();
        adapter.loadMoreComplete();
        if (momentRefesh.isRefreshing()) {
            momentRefesh.setRefreshing(false);
        }
    }

    @Override
    public void onEmpty() {
        hideLoading();
        adapter.loadMoreEnd();
        adapter.loadMoreComplete();
    }



    private class MyLoadMoreListener implements BaseQuickAdapter.RequestLoadMoreListener {

        private int size = 0;

        @Override
        public void onLoadMoreRequested() {
            rvMoment.postDelayed(new Runnable() {
                @Override
                public void run() {
                    //大于上一次的size
                    if (listBeans != null && listBeans.size() >= pageSize && listBeans.size() > size) {
                        size = listBeans.size();
                        adapter.notifyDataSetChanged();
                        mPresenter.loadMomentList(++pageIndex, pageSize,true);
                    } else if (listBeans != null && listBeans.size() >= 10) {
                        adapter.loadMoreEnd();
                    } else {
                        adapter.loadMoreEnd(true);
                    }
                }
            },100);
            Log.d(TAG, "加载更多");

        }
    }

    public class MyContentLinearLayoutManager extends LinearLayoutManager {

        public MyContentLinearLayoutManager(Context context) {
            super(context);
        }

        public MyContentLinearLayoutManager(Context context, int orientation, boolean reverseLayout) {
            super(context, orientation, reverseLayout);
        }

        public MyContentLinearLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
            super(context, attrs, defStyleAttr, defStyleRes);
        }

        @Override
        public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
            try {
                super.onLayoutChildren(recycler, state);
            } catch (IndexOutOfBoundsException e) {
                e.printStackTrace();
            } finally {
            }
        }
    }


    /**
     * 根据手机的分辨率PX(像素)转成DP
     * @param pxValue
     * @return
     */
    public  int px2dip( float pxValue) {
        float scale = getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    @Override
    public void onError(String msg) {
        hideLoading();
        tvNonet.setVisibility(View.VISIBLE);
        adapter.loadMoreEnd();
        adapter.loadMoreFail();
    }


    @Override
    public void showLoading() {
        if (!momentRefesh.isRefreshing()){
            super.showLoading();
        }
    }

    @Override
    public void hideLoading() {
        super.hideLoading();
    }



}
