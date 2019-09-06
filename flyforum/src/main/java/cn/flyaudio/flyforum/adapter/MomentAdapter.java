package cn.flyaudio.flyforum.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.List;

import cn.flyaudio.flycodelibrary.utils.L;
import cn.flyaudio.flycodelibrary.utils.ToastUtils;
import cn.flyaudio.flyforum.Urls;
import cn.flyaudio.flyforum.bean.MomentBean;
import cn.flyaudio.flyforum.R;
import cn.flyaudio.flyforum.util.PxDp;

public class MomentAdapter extends BaseQuickAdapter<MomentBean.ListBean, BaseViewHolder> {


    public MomentAdapter(@LayoutRes int resource, @NonNull List<MomentBean.ListBean> bean) {
        super(resource, bean);
    }

    @Override
    protected void convert(BaseViewHolder helper, final MomentBean.ListBean item) {
//        List<String> list = item.getList();
        helper.setText(R.id.moment_content, item.getContent());
        helper.setText(R.id.moment_nick, item.getName());
        helper.setText(R.id.moment_zan, item.getLikeNumber());
        TextView praise = helper.getView(R.id.moment_zan);
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
        helper.addOnClickListener(R.id.moment_zan);

        //此处为评论数
        helper.setText(R.id.moment_msg, item.getCommentNumber());

        helper.setText(R.id.moment_time, item.getCreateTime());
        ImageView avatar = helper.getView(R.id.moment_avatar);
        ImageView icon = helper.getView(R.id.moment_cover);
        if (item.getList()!=null&&item.getList().size()>0){
            Glide.with(mContext).load(Urls.baseImg + item.getList().get(0) + "")
                    .apply(new RequestOptions().placeholder(R.mipmap.flyforum_activity_pic1)).into(icon);
        }

        Glide.with(mContext).load(Urls.baseImg + item.getAvatar())
                .apply(RequestOptions.bitmapTransform(new CircleCrop()).placeholder(R.mipmap.flyforum_icon2))
                .into(avatar);

    }




}
