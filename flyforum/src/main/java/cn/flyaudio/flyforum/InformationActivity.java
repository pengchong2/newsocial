package cn.flyaudio.flyforum;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

import cn.flyaudio.flycodelibrary.base.ui.BaseMvpActivity;
import cn.flyaudio.flyforum.adapter.InformationAdapter;
import cn.flyaudio.flyforum.bean.InfoListBean;
import cn.flyaudio.flyforum.ui.information.ImformationDetailsActivity;
import cn.flyaudio.flyforum.ui.information.contract.InfoContract;
import cn.flyaudio.flyforum.ui.information.model.InfoModel;
import cn.flyaudio.flyforum.ui.information.presenter.InfoPresenter;

public class InformationActivity extends BaseMvpActivity<InfoPresenter, InfoModel> implements InfoContract.InfoView {
    private static final String TAG = "InformationActivity";
    private RecyclerView rvInfo;
    private SwipeRefreshLayout informationRefesh;
    private InformationAdapter adapter;
    private TextView tvError;
    private List<InfoListBean.ListBean> listBeans;
    private int pageIndex = 1;
    private int pageSize = 20;

    @Override
    public void onError(String msg) {
        hideLoading();
//        if (!TextUtils.isEmpty(msg)){
//            tvError.setText(msg);
//        }
        adapter.loadMoreEnd(true);
        adapter.loadMoreComplete();
        tvError.setVisibility(View.VISIBLE);
        rvInfo.setVisibility(View.GONE);
    }

    @Override
    protected void initMvpView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_information);
        tvError = (TextView) findViewById(R.id.tv_error);
        rvInfo = findViewById(R.id.flyforum_information);
        informationRefesh = findViewById(R.id.informationRefesh);
        adapter = new InformationAdapter(
                R.layout.rv_item_information,
                null);

        rvInfo.setLayoutManager(new LinearLayoutManager(this));
        rvInfo.setAdapter(adapter);
        informationRefesh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                mPresenter.loadInfoList(1, pageSize,false);
            }
        });
        adapter.setOnItemClickListener(new AdapterItemClickListerner());
        adapter.setOnLoadMoreListener(new MyOnLoadMoreListerner(), rvInfo);
        adapter.disableLoadMoreIfNotFullPage();
        tvError.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.loadInfoList(pageIndex, pageSize,false);
            }
        });
        if (isConn()) {
            mPresenter.loadInfoList(1, pageSize,false);
        } else {
            onError("网络未连接，请先检查网络");
        }
    }

    @Override
    public void loadSuccess(List<InfoListBean.ListBean> listBeans) {
        if ( this.listBeans != null &&  this.listBeans.size() > 0) {
            this.listBeans.clear();
        }
        this.listBeans = listBeans;
        rvInfo.setVisibility(View.VISIBLE);
        adapter.setNewData( this.listBeans);
        if (informationRefesh.isRefreshing()) {
            informationRefesh.setRefreshing(false);
        }
    }

    @Override
    public void loadMoreSuccess(List<InfoListBean.ListBean> listBeans) {
        rvInfo.setVisibility(View.VISIBLE);
        this.listBeans .addAll(listBeans);
        adapter.setNewData(this.listBeans);
        if (informationRefesh.isRefreshing()) {
            informationRefesh.setRefreshing(false);
        }
    }

    @Override
    public void showLoading() {
        if (!informationRefesh.isRefreshing()){
            super.showLoading();
        }
    }

    @Override
    public void hideLoading() {
        super.hideLoading();
    }

    private class MyOnLoadMoreListerner implements BaseQuickAdapter.RequestLoadMoreListener {
        private int size = 0;
        @Override
        public void onLoadMoreRequested() {
            Log.d(TAG, "加载更多");
            rvInfo.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Log.d(TAG, "加载更多run");
                    if (listBeans != null && listBeans.size() >= pageSize && listBeans.size() > size) {
                       //大于等于上一次的size
                        size = listBeans.size();
                        mPresenter.loadInfoList(++pageIndex, pageSize,false);
                        adapter.loadMoreComplete();
                    } else if (listBeans != null && listBeans.size() >= 10) {
                        if (rvInfo.getScrollState() == RecyclerView.SCROLL_STATE_IDLE) {
                            adapter.loadMoreEnd();
                        }
                    } else {
                        if (rvInfo.getScrollState() == RecyclerView.SCROLL_STATE_IDLE) {
                            adapter.loadMoreEnd(true);
                        }
                    }

                }

            }, 100);
        }
    }


    private class AdapterItemClickListerner implements BaseQuickAdapter.OnItemClickListener {
        @Override
        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
            //未知情况数据越界，暂时的解决方案
            if (position >= listBeans.size()) {
                position = position - listBeans.size();
            }
            InfoListBean.ListBean listBean = listBeans.get(position);
            Intent intent = new Intent(InformationActivity.this, ImformationDetailsActivity.class);
            intent.putExtra("uuid", listBean.getUuid());
            startActivity(intent);
        }
    }

    @Override
    public void onEmpty() {
        hideLoading();
        tvError.setText("暂时没有数据，请稍候再试");
        tvError.setVisibility(View.VISIBLE);
    }
}
