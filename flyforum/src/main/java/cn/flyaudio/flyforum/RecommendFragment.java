package cn.flyaudio.flyforum;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import java.util.List;
import cn.flyaudio.flycodelibrary.base.ui.BaseFragment;
import cn.flyaudio.flyforum.adapter.RecommendAdapter;
import cn.flyaudio.flyforum.bean.BrandBean;
import cn.flyaudio.flyforum.bean.IndexBean;
import cn.flyaudio.flyforum.util.GetIndexListUtils;

public class RecommendFragment extends BaseFragment implements GetIndexListUtils.GetWeiboCallback {

    private static final String TAG = "RecommendFragment";

    private RecyclerView listView;
    private RecommendAdapter adapter;
    private SwipeRefreshLayout refreshLayout;
    private int pageSize = 20;
    private int pageIndex = 1;
    private TextView tvNonet;
    private TextView tvRecommend;
    public ProgressDialog dialog;

    private RadioGroup mSocialContractFragmentRg;
    public RecommendFragment(RadioGroup socialContractFragmentRg){
        this.mSocialContractFragmentRg = socialContractFragmentRg;
    }

    @Override
    public View getLayoutView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_recommend,container,false);
    }

    @Override
    public void initView(View view, @Nullable Bundle savedInstanceState) {
        tvRecommend = (TextView) view.findViewById(R.id.tv_recommend);
        tvNonet = (TextView) view.findViewById(R.id.tv_nonet);
        tvNonet.setVisibility(View.GONE);
        tvNonet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GetIndexListUtils.getInstant().registInterface(RecommendFragment.this);
                GetIndexListUtils.getInstant().requestData(pageIndex, pageSize);
            }
        });
        listView = view.findViewById(R.id.rv_recommend);
        refreshLayout = view.findViewById(R.id.recommendRefresh);
        listView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new RecommendAdapter(getContext(),mSocialContractFragmentRg);
        listView.setAdapter(adapter);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                GetIndexListUtils.getInstant().registInterface(RecommendFragment.this);
                GetIndexListUtils.getInstant().requestData(pageIndex, pageSize);
            }
        });
        if (isConn()) {
            showLoading();
            GetIndexListUtils.getInstant().registInterface(RecommendFragment.this);
            GetIndexListUtils.getInstant().requestData(pageIndex, pageSize);
            GetIndexListUtils.getInstant().getBranchList();
        } else {
            onError("网络未连接，请先检查网络");
        }
    }

    /**
     * 重新连接网络上后刷新数据
     */
    public void refreshData(){
        GetIndexListUtils.getInstant().registInterface(RecommendFragment.this);
        GetIndexListUtils.getInstant().requestData(pageIndex, pageSize);
    }

    @Override
    public void initData() {

    }

    @Override
    public void getdataSuccess(IndexBean body) {
        Log.d(TAG,"getdataSuccess");
        if (refreshLayout.isRefreshing()) {
            refreshLayout.setRefreshing(false);
        }
        hideLoading();
        tvNonet.setVisibility(View.GONE);
        if (!GetIndexListUtils.isLoadData) {
            Log.d(TAG,"notifyDataSetChanged");
            adapter.setData(body);
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
      //  tvRecommend.setVisibility(View.VISIBLE);
        tvNonet.setVisibility(View.VISIBLE);
    }

    public void showLoading() {

//        dialog = new ProgressDialog(getContext(),R.style.theme_dialog);
//        dialog.setMessage(getString(cn.flyaudio.flycodelibrary.R.string.default_loading));
//        dialog.setCancelable(true);
//        dialog.setCanceledOnTouchOutside(false);
//        dialog.show();

    }
    public void hideLoading() {

//        if (dialog != null && dialog.isShowing()) {
//            dialog.hide();
//            dialog.dismiss();
//        }

    }


    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG,"onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG,"onResume");
    }
}
