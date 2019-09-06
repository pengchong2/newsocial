package cn.flyaudio.flyforum;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.List;

import cn.flyaudio.flycodelibrary.base.ui.BaseActivity;
import cn.flyaudio.flyforum.adapter.RecommendAdapter;
import cn.flyaudio.flyforum.bean.BrandBean;
import cn.flyaudio.flyforum.bean.IndexBean;
import cn.flyaudio.flyforum.util.GetIndexListUtils;

public class RecommendActivity extends BaseActivity implements GetIndexListUtils.GetWeiboCallback {
    private static final String TAG = "RecommendActivity";

    private RecyclerView listView;
    private RecommendAdapter adapter;
    private SwipeRefreshLayout refreshLayout;
    private int pageSize = 20;
    private int pageIndex = 1;
    private TextView tvNonet;
    private TextView tvRecommend;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend);

        tvRecommend = (TextView) findViewById(R.id.tv_recommend);

        tvNonet = (TextView) findViewById(R.id.tv_nonet);
        tvNonet.setVisibility(View.GONE);
        tvNonet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GetIndexListUtils.getInstant().registInterface(RecommendActivity.this);
                GetIndexListUtils.getInstant().requestData(pageIndex, pageSize);
            }
        });
        listView = findViewById(R.id.rv_recommend);
        refreshLayout = findViewById(R.id.recommendRefresh);
        listView.setLayoutManager(new LinearLayoutManager(RecommendActivity.this));
        adapter = new RecommendAdapter(RecommendActivity.this);
        listView.setAdapter(adapter);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
//                showLoading();
                GetIndexListUtils.getInstant().registInterface(RecommendActivity.this);
                GetIndexListUtils.getInstant().requestData(pageIndex, pageSize);
            }
        });
        if (isConn()) {
            showLoading();
            GetIndexListUtils.getInstant().registInterface(RecommendActivity.this);
            GetIndexListUtils.getInstant().requestData(pageIndex, pageSize);
            GetIndexListUtils.getInstant().getBranchList();
        } else {
            onError("网络未连接，请先检查网络");
        }
    }

    @Override
    protected View getLayoutView(LayoutInflater layoutInflater, Bundle savedInstanceState) {
        return null;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

//    private void isNeedUpdateToken() {
//        long scrapTime = SPUtils.getInstance().getLong("scrapTime", 0);
//        long l = SystemClock.currentThreadTimeMillis();
//        if (scrapTime==0){
//            SPUtils.getInstance().put("scrapTime", l);
//        }else {
//            if (l-24*60*60*1000L>24*60*60*1000L){//超过一天重新获取token
//                //todo updateToken
//            }
//        }
//
//    }

    @Override
    public void getdataSuccess(IndexBean indexBean) {
        if (refreshLayout.isRefreshing()) {
            refreshLayout.setRefreshing(false);
        }
        hideLoading();
        tvNonet.setVisibility(View.GONE);
        if (!GetIndexListUtils.isLoadData) {
            adapter.setData(indexBean);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void getBranchSuccess(List<BrandBean.ZBean> zBeanList) {
        zBeanList.toString();
        int size = zBeanList.size();
    }

    @Override
    public void onError(String msg) {
        hideLoading();
        tvRecommend.setVisibility(View.VISIBLE);
        tvNonet.setVisibility(View.VISIBLE);
    }


}

