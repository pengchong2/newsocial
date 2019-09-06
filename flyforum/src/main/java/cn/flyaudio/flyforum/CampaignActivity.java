package cn.flyaudio.flyforum;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import cn.flyaudio.flycodelibrary.base.ui.BaseMvpActivity;
import cn.flyaudio.flyforum.adapter.ActivityAdapter;
import cn.flyaudio.flyforum.bean.ActivityListBean;
import cn.flyaudio.flyforum.ui.activities.AcsPresenter.AcsPresenter;
import cn.flyaudio.flyforum.ui.activities.CampaignDetailsActivity;
import cn.flyaudio.flyforum.ui.activities.contract.AcsContract;
import cn.flyaudio.flyforum.ui.activities.model.AcsModel;

public class CampaignActivity extends BaseMvpActivity<AcsPresenter, AcsModel> implements AcsContract.AcsView {
    private RecyclerView rvAcs;
    private SwipeRefreshLayout campaignRefesh;
    private static final String TAG = "InformationActivity";
    private ActivityAdapter adapter;
    private Toast toast;
    private int pageIndex = 1;
    private int pageSize = 20;

    private List<ActivityListBean.ListBean> listBeans;
    private TextView tvNonet;

    private void startdetails(int position) {
        ActivityListBean.ListBean listBean = listBeans.get(position);
        Intent intent = new Intent(CampaignActivity.this, CampaignDetailsActivity.class);
        intent.putExtra("uuid", listBean.getUuid());
        startActivity(intent);
    }

    @Override
    public void showLoading() {
        if (!campaignRefesh.isRefreshing()) {
            super.showLoading();
        }
    }

    @Override
    public void hideLoading() {
        super.hideLoading();
    }


    public void ShowToast(String str) {
        if (toast == null) {
            toast = Toast.makeText(this, str, Toast.LENGTH_SHORT);
        } else {
            toast.setText(str);
            toast.show();
        }

    }

    @Override
    protected void initMvpView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_campaign);
        listBeans = new ArrayList<>();
        rvAcs = findViewById(R.id.rv_acs);
        campaignRefesh = findViewById(R.id.campaignRefesh);

        tvNonet = (TextView) findViewById(R.id.tv_nonet);
        tvNonet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.lodeAcsList(1, pageSize, false);
            }
        });
        adapter = new ActivityAdapter(R.layout.rv_item_activity, null);
        rvAcs.setLayoutManager(new LinearLayoutManager(this));
        rvAcs.setAdapter(adapter);
        campaignRefesh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.lodeAcsList(1, 20, false);
            }
        });
        adapter.setOnLoadMoreListener(new MyOnLoadMoreListerner(), rvAcs);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (position >= listBeans.size()) {
                    position = position - listBeans.size();
                }
                startdetails(position);
            }
        });
        if (isConn()) {
            mPresenter.lodeAcsList(1, 20, false);
        } else {
            onError("网络未连接，请先检查网络");
        }
    }


    @Override
    public void returnDataSuccess(List<ActivityListBean.ListBean> listBeans) {
        rvAcs.setVisibility(View.VISIBLE);
        this.listBeans = listBeans;
        adapter.setNewData(listBeans);
        adapter.notifyDataSetChanged();
        adapter.loadMoreComplete();
        if (campaignRefesh.isRefreshing()) {
            campaignRefesh.setRefreshing(false);
        }
    }

    @Override
    public void loadMoreDataSuccess(List<ActivityListBean.ListBean> activityBean) {
        rvAcs.setVisibility(View.VISIBLE);
        this.listBeans.addAll(activityBean);
        adapter.setNewData(listBeans);
        adapter.notifyDataSetChanged();
        adapter.loadMoreComplete();
        if (campaignRefesh.isRefreshing()) {
            campaignRefesh.setRefreshing(false);
        }
    }

    @Override
    public void onError(String error) {
        //加载结束
        rvAcs.setVisibility(View.GONE);
        adapter.loadMoreFail();
        tvNonet.setVisibility(View.VISIBLE);
    }

    @Override
    public void onEmpty() {
        adapter.loadMoreEnd();
        tvNonet.setText("还没有数据哦");
        tvNonet.setVisibility(View.VISIBLE);
    }

    private class MyOnLoadMoreListerner implements BaseQuickAdapter.RequestLoadMoreListener {
        @Override
        public void onLoadMoreRequested() {
            Log.d(TAG, "加载更多");
            rvAcs.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (listBeans != null && listBeans.size() >= pageSize) {
                        mPresenter.lodeAcsList(++pageIndex, pageSize, true);
                    } else if (listBeans != null && listBeans.size() >= 10) {
                        if (rvAcs.getScrollState() == RecyclerView.SCROLL_STATE_IDLE || (rvAcs.isComputingLayout() == false)) {
                            adapter.loadMoreEnd();
                        }
                    } else {
                        if (rvAcs.getScrollState() == RecyclerView.SCROLL_STATE_IDLE || (rvAcs.isComputingLayout() == false)) {
                            adapter.loadMoreEnd(true);
                        }
                    }
                }
            }, 100);


        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
    }
}
