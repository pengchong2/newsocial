package cn.flyaudio.flyforum.adapter;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cn.flyaudio.flyforum.R;
import cn.flyaudio.flyforum.Urls;
import cn.flyaudio.flyforum.bean.ActivityListBean;

/**
 * Created by 56293 on 2018/9/20.
 */

public class ActivityAdapter extends BaseQuickAdapter<ActivityListBean.ListBean, BaseViewHolder> {
    private static final String TAG = "ActivityAdapter";

    public ActivityAdapter(@LayoutRes int layoutid, @NonNull List<ActivityListBean.ListBean> listBeans) {
        super(R.layout.rv_item_activity, listBeans);
    }

    @Override
    protected void convert(BaseViewHolder helper, ActivityListBean.ListBean item) {
        int acStatus = item.getActivityStatus();
        helper.setText(R.id.activity_content, item.getTitle());
        helper.setText(R.id.activity_location, item.getActivitySite() + " | ");
        if (TextUtils.isEmpty(item.getTwoTag().trim())) {
            helper.getView(R.id.activity_tag2).setVisibility(View.GONE);
        }
        if (TextUtils.isEmpty(item.getOneTag().trim())) {
            helper.getView(R.id.activity_tag1).setVisibility(View.GONE);
        }
        if (TextUtils.isEmpty(item.getThreeTag().trim())) {
            helper.getView(R.id.activity_tag3).setVisibility(View.GONE);
        }
        helper.setText(R.id.activity_tag1, item.getOneTag().length() > 8 ? item.getOneTag().substring(0, 7) : item.getOneTag());
        helper.setText(R.id.activity_tag2, item.getTwoTag() + "");
        helper.setText(R.id.activity_tag3, item.getThreeTag() + "");
        helper.setText(R.id.activity_starttime, item.getActivityStartTime() + "");
        helper.setText(R.id.activity_isStart, acStatus == 0 ?
                mContext.getResources().getString(R.string.acstatus_nostart)
                : (acStatus == 1 ? mContext.getResources().getString(R.string.acstatus_end)
                : mContext.getResources().getString(R.string.acstatus_doing)));
        TextView acstatus = helper.getView(R.id.activity_isStart);
        switch (acStatus) {
            case 0:
                acstatus.setBackground(mContext.getResources().getDrawable(R.drawable.shadowlaylist_red));
                break;
            case 1:
                acstatus.setBackground(mContext.getResources().getDrawable(R.drawable.shadowlaylist_gray));

                break;
            default:
                acstatus.setBackground(mContext.getResources().getDrawable(R.drawable.shadowlaylists));
                break;

        }
        ImageView icon = helper.getView(R.id.activity_cover);
        icon.setImageResource(R.mipmap.flyforum_placeholder);
        String cover = Urls.baseImg + item.getCover();
        Glide.with(mContext).load(cover + "")
                .apply(new RequestOptions().placeholder(R.mipmap.flyforum_placeholder))
                .into(icon);
    }

}
