package cn.flyaudio.flyforum.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import cn.flyaudio.flyforum.CampaignActivity;
import cn.flyaudio.flyforum.InformationActivity;
import cn.flyaudio.flyforum.MomentActivity;
import cn.flyaudio.flyforum.Urls;
import cn.flyaudio.flyforum.bean.IndexBean;
import cn.flyaudio.flyforum.ui.activities.CampaignDetailsActivity;
import cn.flyaudio.flyforum.ui.information.ImformationDetailsActivity;
import cn.flyaudio.flyforum.ui.moment.MomentDetailActivity;
import cn.flyaudio.flyforum.R;

/**
 * Created by 56293 on 2018/9/20.
 */

public class RecommendAdapter extends RecyclerView.Adapter {
    private final Context mContext;
    private List<IndexBean.UserInfoVOListBean> userMomentVOList;//此刻
    private List<IndexBean.VehicleInfoVOListBean> vehicleInfoVOList;//咨讯
    private List<IndexBean.VehicleActivityVOListBean> vehicleActivityInfoVOList;//活动
    private int userMomentVOListsize;//此刻长度
    private int vehicleInfoVOListsize;//咨讯长度
    private int vehicleActivityInfoVOListsize;//活动长度
    private IndexBean indexBean;
    private RadioGroup mSocialContractRgp;
    private String TAG = "RecommendAdapter";
    public RecommendAdapter(Context context, RadioGroup socialContractRgp) {
        this.mSocialContractRgp = socialContractRgp;
        this.mContext = context;
        userMomentVOList = new ArrayList<>();
        vehicleInfoVOList = new ArrayList<>();
        vehicleActivityInfoVOList = new ArrayList<>();
//        indexBean = bean;
//        userMomentVOList = indexBean.getuserMomentVOList();
//        vehicleInfoVOList = indexBean.getVehicleInfoVOList();
//        vehicleActivityInfoVOList = indexBean.getVehicleActivityVOList();
//        userMomentVOListsize = userMomentVOList == null ? 0 : userMomentVOList.size();
//        vehicleInfoVOListsize = vehicleInfoVOList == null ? 0 : vehicleInfoVOList.size();
//        vehicleActivityInfoVOListsize = vehicleActivityInfoVOList == null ? 0 : vehicleActivityInfoVOList.size();

    }


    public RecommendAdapter(Context context) {

        this.mContext = context;
        userMomentVOList = new ArrayList<>();
        vehicleInfoVOList = new ArrayList<>();
        vehicleActivityInfoVOList = new ArrayList<>();

    }


    public void setData(IndexBean bean) {
        indexBean = bean;

        userMomentVOList.clear();
        vehicleInfoVOList.clear();
        vehicleActivityInfoVOList.clear();
        userMomentVOList = indexBean.getUserInfoVOList();
        vehicleInfoVOList = indexBean.getVehicleInfoVOList();
        vehicleActivityInfoVOList = indexBean.getVehicleActivityVOList();
        userMomentVOListsize = userMomentVOList == null ? 0 : userMomentVOList.size();
        vehicleInfoVOListsize = vehicleInfoVOList == null ? 0 : vehicleInfoVOList.size();
        vehicleActivityInfoVOListsize = vehicleActivityInfoVOList == null ? 0 : vehicleActivityInfoVOList.size();

        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 1) {
            return new ActivityViewHolder(View.inflate(mContext, R.layout.rv_item_activity, null));
        } else if (viewType == 2) {
            return new InfoViewHolder(View.inflate(mContext, R.layout.rv_item_information, null));
        } else if (viewType == 3) {
            return new MomentViewHolder(LayoutInflater.from(mContext).inflate(R.layout.rv_moment_item, parent, false));
        } else {
            return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MomentViewHolder) {
            int mPosition = position - vehicleInfoVOListsize - vehicleActivityInfoVOListsize;
            if (mPosition == 0) {
                ((MomentViewHolder) holder).include.setVisibility(View.VISIBLE);
                ((MomentViewHolder) holder).tvRecommend.setText(mContext.getResources().getString(R.string.moment_recommend));
                ((MomentViewHolder) holder).tvRecommendTips.setText(mContext.getResources().getString(R.string.moment_recommend_tips));
                ((MomentViewHolder) holder).tvMore.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       // mContext.startActivity(new Intent(mContext, MomentActivity.class));
                        mSocialContractRgp.check(R.id.momentrb);
                    }
                });
            } else {
                ((MomentViewHolder) holder).include.setVisibility(View.GONE);
            }
            final IndexBean.UserInfoVOListBean userInfoBean = indexBean.getUserInfoVOList().get(position - vehicleActivityInfoVOListsize - vehicleInfoVOListsize);
            ((MomentViewHolder) holder).itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mContext.startActivity(new Intent(mContext, MomentDetailActivity.class)
                            .putExtra("uuid", userInfoBean.getUuid()));
                }
            });
            ((MomentViewHolder) holder).momentContent.setText(userInfoBean.getContent());
            ((MomentViewHolder) holder).momentZan.setText(userInfoBean.getLikeNumber());
            ((MomentViewHolder) holder).momentTime.setText(userInfoBean.getCreateTime());
            ((MomentViewHolder) holder).momentNick.setText(userInfoBean.getName());
            Glide.with(mContext).load(Urls.baseImg + userInfoBean.getAvatar())
                    .apply(new RequestOptions().placeholder(R.mipmap.flyforum_icon2))
                    .into(((MomentViewHolder) holder).momentAvatar);
            Glide.with(mContext).load(Urls.baseImg +
                    (userInfoBean.getList() == null ? "" : userInfoBean.getList().get(0)))
                    .apply(new RequestOptions().placeholder(R.mipmap.flyforum_placeholder))
                    .into(((MomentViewHolder) holder).momentCover);
            ((MomentViewHolder) holder).momentMsg.setText(userInfoBean.getCommentNumber());
        } else if (holder instanceof InfoViewHolder) {
            int mPosition = position - vehicleActivityInfoVOList.size();
            if (mPosition == 0) {
                ((InfoViewHolder) holder).include.setVisibility(View.VISIBLE);
                ((InfoViewHolder) holder).tvRecommend.setText(mContext.getResources().getString(R.string.imformation_recommend));
                ((InfoViewHolder) holder).tvRecommendTips.setText(mContext.getResources().getString(R.string.imformation_recommend_tips));
                ((InfoViewHolder) holder).tvMore.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                      //  mContext.startActivity(new Intent(mContext, InformationActivity.class));
                        mSocialContractRgp.check(R.id.informationrb);
                    }
                });
            } else {
                ((InfoViewHolder) holder).include.setVisibility(View.GONE);
            }
            final IndexBean.VehicleInfoVOListBean vehicleInfoVOListBean = vehicleInfoVOList.get(mPosition);
            ((InfoViewHolder) holder).itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mContext.startActivity(new Intent(mContext, ImformationDetailsActivity.class)
                            .putExtra("uuid", vehicleInfoVOListBean.getUuid()));
                }
            });
            ((InfoViewHolder) holder).informationContent.setText(vehicleInfoVOListBean.getTitle());
            ((InfoViewHolder) holder).informationComment.setText(vehicleInfoVOListBean.getCommentNumber());
            ((InfoViewHolder) holder).informationRead.setText(vehicleInfoVOListBean.getLookNumber());
            ((InfoViewHolder) holder).informationTime.setText(vehicleInfoVOListBean.getCreateTime());
            Glide.with(mContext).load(Urls.baseImg + vehicleInfoVOListBean.getCover())
                    .apply(new RequestOptions().placeholder(R.mipmap.flyforum_placeholder))
                    .into(((InfoViewHolder) holder).informationPicture);

        } else if (holder instanceof ActivityViewHolder) {
            if (position == 0) {
                ((ActivityViewHolder) holder).include.setVisibility(View.VISIBLE);
                ((ActivityViewHolder) holder).tvRecommend.setText(mContext.getResources().getString(R.string.activity_recommend));
                ((ActivityViewHolder) holder).tvRecommendTips.setText(mContext.getResources().getString(R.string.activity_recommend_tips));
                ((ActivityViewHolder) holder).tvMore.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       // mContext.startActivity(new Intent(mContext, CampaignActivity.class));
                        mSocialContractRgp.check(R.id.campaignrb);
                    }
                });
            } else {
                ((ActivityViewHolder) holder).include.setVisibility(View.GONE);
            }
            final IndexBean.VehicleActivityVOListBean activityVOListBean = indexBean.getVehicleActivityVOList().get(position);
            ((ActivityViewHolder) holder).itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mContext.startActivity(new Intent(mContext, CampaignDetailsActivity.class)
                            .putExtra("uuid", activityVOListBean.getUuid()));
                }
            });
           // ((ActivityViewHolder) holder).activityContent.setText(activityVOListBean.getContent());
            ((ActivityViewHolder) holder).activityContent.setText(activityVOListBean.getTitle());
            ((ActivityViewHolder) holder).activityLocation.setText(activityVOListBean.getActivitySite() + " | ");
            ((ActivityViewHolder) holder).activityStarttime.setText(activityVOListBean.getActivityStartTime());
            ((ActivityViewHolder) holder).activityTag1.setText(activityVOListBean.getOneTag().length() > 8 ? activityVOListBean.getOneTag().substring(0, 7) : activityVOListBean.getOneTag());
            ((ActivityViewHolder) holder).activityTag2.setText(activityVOListBean.getTwoTag() + "");
            ((ActivityViewHolder) holder).activityTag3.setText(activityVOListBean.getThreeTag() + "");
            if (TextUtils.isEmpty(activityVOListBean.getOneTag())) {
                ((ActivityViewHolder) holder).activityTag1.setVisibility(View.GONE);
            }
            if (TextUtils.isEmpty(activityVOListBean.getTwoTag())) {
                ((ActivityViewHolder) holder).activityTag2.setVisibility(View.GONE);
            }
            if (TextUtils.isEmpty(activityVOListBean.getThreeTag())) {
                ((ActivityViewHolder) holder).activityTag3.setVisibility(View.GONE);
            }
            int acStatus = activityVOListBean.getActivityStatus();

            ((ActivityViewHolder) holder).activityIsStart.setText(acStatus == 0 ? "活动未开始" : (acStatus == 1 ? "活动进行中" : "活动已结束"));
            Glide.with(mContext).load(Urls.baseImg + activityVOListBean.getCover())
                    .apply(new RequestOptions().placeholder(R.mipmap.flyforum_placeholder))
                    .into(((ActivityViewHolder) holder).activity_cover);
        }

    }

    @Override
    public int getItemViewType(int position) {
        if (vehicleActivityInfoVOList != null && vehicleActivityInfoVOListsize > 0) {
            if (position < vehicleActivityInfoVOListsize) {
                return 1;
            }
            if (vehicleInfoVOList != null && vehicleInfoVOListsize > 0) {//此刻
                if (position < vehicleInfoVOListsize + vehicleActivityInfoVOListsize) {
                    return 2;
                }
                if (userMomentVOList != null && userMomentVOListsize > 0) {//咨讯
                    if (position < vehicleInfoVOListsize + userMomentVOListsize + vehicleActivityInfoVOListsize) {
                        return 3;
                    }
                }
            }
            if (userMomentVOList != null && userMomentVOListsize > 0) {
                if (position < vehicleInfoVOListsize + vehicleActivityInfoVOListsize) {
                    return 3;
                }
            }
        } else if (vehicleInfoVOList != null && vehicleInfoVOListsize > 0) {
//            if (vehicleInfoVOList != null && vehicleInfoVOListsize > 0) {
            if (position < vehicleInfoVOListsize) {
                return 2;
            }
            if (userMomentVOList != null && userMomentVOListsize > 0) {
                if (position < vehicleInfoVOListsize + userMomentVOListsize) {
                    return 3;
                }
            }
        } else if (userMomentVOList != null && userMomentVOListsize > 0) {
            if (position < userMomentVOListsize) {
                return 3;
            }

        }
        return 0;

    }

    @Override
    public int getItemCount() {
        int size = (userMomentVOList == null ? 0 : userMomentVOList.size()) +
                (vehicleInfoVOList == null ? 0 : vehicleInfoVOList.size()) +
                (vehicleActivityInfoVOList == null ? 0 : vehicleActivityInfoVOList.size());
        return size;
    }


    private class MomentViewHolder extends RecyclerView.ViewHolder {
        ImageView momentAvatar;
        TextView momentNick;
        ImageView momentCover;
        TextView momentContent;
        TextView momentTime;
        TextView momentZan;
        TextView momentMsg;
        View include;
        TextView tvRecommend;
        TextView tvRecommendTips;
        TextView tvMore;

        public MomentViewHolder(View convertView) {
            super(convertView);


            tvRecommend = (TextView) convertView.findViewById(R.id.tv_recommend);
            tvRecommendTips = (TextView) convertView.findViewById(R.id.tv_recommend_tips);
            tvMore = (TextView) convertView.findViewById(R.id.tv_more);
            include = (View) convertView.findViewById(R.id.include);

            momentAvatar = (ImageView) convertView.findViewById(R.id.moment_avatar);
            momentNick = (TextView) convertView.findViewById(R.id.moment_nick);
            momentCover = (ImageView) convertView.findViewById(R.id.moment_cover);
            momentContent = (TextView) convertView.findViewById(R.id.moment_content);
            momentTime = (TextView) convertView.findViewById(R.id.moment_time);
            momentZan = (TextView) convertView.findViewById(R.id.moment_zan);
            momentMsg = (TextView) convertView.findViewById(R.id.moment_msg);


        }
    }

    private class InfoViewHolder extends RecyclerView.ViewHolder {
        TextView informationContent;
        ImageView informationPicture;
        TextView informationTime;
        TextView informationRead;
        TextView informationComment;
        View include;
        TextView tvRecommend;
        TextView tvRecommendTips;
        TextView tvMore;

        public InfoViewHolder(View convertView) {
            super(convertView);
            include = (View) convertView.findViewById(R.id.include);
            tvRecommend = (TextView) convertView.findViewById(R.id.tv_recommend);
            tvRecommendTips = (TextView) convertView.findViewById(R.id.tv_recommend_tips);
            tvMore = (TextView) convertView.findViewById(R.id.tv_more);
            informationContent = (TextView) convertView.findViewById(R.id.information_content);
            informationPicture = (ImageView) convertView.findViewById(R.id.information_picture);
            informationTime = (TextView) convertView.findViewById(R.id.information_time);
            informationComment = (TextView) convertView.findViewById(R.id.information_msg);
            informationRead = (TextView) convertView.findViewById(R.id.information_read);

        }
    }

    private class ActivityViewHolder extends RecyclerView.ViewHolder {
        TextView activityContent;
        RelativeLayout activityPicture;
        ImageView activity_cover;
        TextView activityIsStart;
        ImageView activityLocalIcon;
        TextView activityLocation;
        TextView activityStarttime;
        TextView activityTag3;
        TextView activityTag1;
        TextView activityTag2;
        View include;
        TextView tvRecommend;
        TextView tvRecommendTips;
        TextView tvMore;

        public ActivityViewHolder(View convertView) {
            super(convertView);
            include = (View) convertView.findViewById(R.id.include);
            tvRecommend = (TextView) convertView.findViewById(R.id.tv_recommend);
            tvRecommendTips = (TextView) convertView.findViewById(R.id.tv_recommend_tips);
            tvMore = (TextView) convertView.findViewById(R.id.tv_more);
            activityTag3 = (TextView) convertView.findViewById(R.id.activity_tag3);
            activityTag1 = (TextView) convertView.findViewById(R.id.activity_tag1);
            activityTag2 = (TextView) convertView.findViewById(R.id.activity_tag2);
            activityContent = (TextView) convertView.findViewById(R.id.activity_content);
            activity_cover = (ImageView) convertView.findViewById(R.id.activity_cover);
            activityIsStart = (TextView) convertView.findViewById(R.id.activity_isStart);
            activityLocation = (TextView) convertView.findViewById(R.id.activity_location);
            activityStarttime = (TextView) convertView.findViewById(R.id.activity_starttime);
        }
    }
}
