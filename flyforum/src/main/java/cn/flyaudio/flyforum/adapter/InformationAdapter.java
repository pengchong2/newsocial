package cn.flyaudio.flyforum.adapter;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cn.flyaudio.flyforum.Urls;
import cn.flyaudio.flyforum.bean.InfoListBean;
import cn.flyaudio.flyforum.R;

/**
 * Created by 56293 on 2018/9/20.
 */

public class InformationAdapter extends BaseQuickAdapter<InfoListBean.ListBean, BaseViewHolder> {
    private static final String TAG = "InformationAdapter";

    public InformationAdapter(@LayoutRes int resource, @NonNull List<InfoListBean.ListBean> listBeans) {
        super(resource, listBeans);
    }

    @Override
    protected void convert(BaseViewHolder helper, InfoListBean.ListBean item) {
        final String imgurl = item.getCover();
        helper.setText(R.id.information_content, item.getTitle() + "");
        helper.setText(R.id.information_time, item.getCreateTime());
        helper.setText(R.id.information_read, item.getLookNumber());
        ImageView picture = helper.getView(R.id.information_picture);
        if (!TextUtils.isEmpty(imgurl)) {
            String url = Urls.baseImg + imgurl;
            picture.setVisibility(View.VISIBLE);
            picture.setImageResource(R.mipmap.flyforum_placeholder);
            Glide.with(mContext)
                    .load(url)
                    .apply(new RequestOptions().placeholder(R.mipmap.flyforum_placeholder))
                    .into(picture);
        } else {
            picture.setVisibility(View.GONE);
        }
    }

}
