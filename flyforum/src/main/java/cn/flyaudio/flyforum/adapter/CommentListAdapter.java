package cn.flyaudio.flyforum.adapter;

import android.graphics.drawable.Drawable;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cn.flyaudio.flyforum.R;
import cn.flyaudio.flyforum.Urls;
import cn.flyaudio.flyforum.bean.CommentBean;
import cn.flyaudio.flyforum.util.PxDp;

public class CommentListAdapter extends BaseQuickAdapter<CommentBean.ListBeanX, BaseViewHolder> {
    public CommentListAdapter(int commentlist_item, List<CommentBean.ListBeanX> commentVOList) {
        super(commentlist_item, commentVOList);
    }

    @Override
    protected void convert(BaseViewHolder helper, CommentBean.ListBeanX item) {
        helper.setText(R.id.tv_comment, item.getContent());
        helper.setText(R.id.tv_name, item.getName());
        helper.setText(R.id.tv_create_time, item.getCreateTime());
//        helper.setText(R.id.iv_zanum, TextUtils.isEmpty(item.getLikeNumber())?"0":item.getLikeNumber());
        helper.setText(R.id.iv_zanum, item.getLikeNumber());
        TextView praise = helper.getView(R.id.iv_zanum);
        helper.addOnClickListener(R.id.iv_zanum);
        int isPraise = item.getIsPraise();
        if (isPraise==1){
            praise.setTextColor(mContext.getResources().getColor(R.color.acs_tagcolor));
            Drawable drawable = mContext.getResources().getDrawable(R.mipmap.praise_press);
            drawable.setBounds(0, 0, PxDp.px2dip(mContext,33), PxDp.px2dip(mContext,(33)));
            praise.setCompoundDrawables(drawable, null, null, null);
        }else {
            praise.setTextColor(mContext.getResources().getColor(R.color.colorWhite));
            Drawable drawable = mContext.getResources().getDrawable(R.mipmap.moment_zan);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            praise.setCompoundDrawables(drawable, null, null, null);
        }
        helper.setText(R.id.iv_commentnum, item.getList()==null?"0":item.getList().size()+"");
        ImageView avator = helper.getView(R.id.iv_avator);
        Glide.with(mContext).load(Urls.baseImg + item.getAvatar())
                .apply(RequestOptions.bitmapTransform(new CircleCrop()).placeholder(R.mipmap.userplaceholder))
                .into(avator);
        RecyclerView rvIncommonlist = (RecyclerView) helper.getView(R.id.rv_incommonlist);
        rvIncommonlist.setVisibility(View.GONE);
        List<CommentBean.ListBeanX.ListBean> list = item.getList();
        if (list != null && list.size() > 0) {
            rvIncommonlist.setVisibility(View.VISIBLE);
            rvIncommonlist.setLayoutManager(new LinearLayoutManager(mContext));
            rvIncommonlist.setNestedScrollingEnabled(false);
            rvIncommonlist.setAdapter(new BaseQuickAdapter<CommentBean.ListBeanX.ListBean, BaseViewHolder>(R.layout.inscommentlist_item, list) {
                @Override
                protected void convert(BaseViewHolder helper, CommentBean.ListBeanX.ListBean item) {
                    helper.setText(R.id.tv_sendname, item.getSendName())
                            .setText(R.id.tv_recname, item.getReceiveName())
                            .setText(R.id.tv_content, ": " + item.getContent());
                }
            });
        }

    }
}