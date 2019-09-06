package cn.flyaudio.flyforum.ui.moment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import cn.flyaudio.flyforum.R;
import cn.flyaudio.flyforum.Urls;
import cn.flyaudio.flyforum.adapter.CommentListAdapter;
import cn.flyaudio.flyforum.bean.CommentBean;
import cn.flyaudio.flyforum.bean.MomentBean;
import cn.flyaudio.flyforum.bean.MomentDetail;
import cn.flyaudio.flyforum.imagewatcher.GlideSimpleLoader;
import cn.flyaudio.flyforum.imagewatcher.ImageWatcherHelper;
import cn.flyaudio.flyforum.util.JsonCallback;
import cn.flyaudio.flyforum.util.PxDp;
import cn.jzvd.Jzvd;
import cn.jzvd.JzvdStd;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class MomentDetailActivity extends FragmentActivity {
    private static final String TAG = "MomentDetailActivity";

    private String uuid;
    private ImageView ivBack;
    private TextView tvTitle;
    private TextView tvMomentName;
    private TextView tvCreateTime;
    private TextView tvContent;
    private TextView tvComment;
    private RecyclerView rvCommonlist;
    private TextView tvNocomment;
    private int pageIndex = 1;
    private int pageSize = 20;
    private CommentBean commentBeanBean;
    private CommentListAdapter commentListAdapter;
    private ImageView ivAvator;
    private RecyclerView rvImgs;
    private JzvdStd videoView;
    private RelativeLayout rlPlay;
    private ProgressDialog dialog;
    private ImageWatcherHelper iwHelper;
    private LinearLayout loadingView;
    private List<CommentBean.ListBeanX> list;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorBlack));
        }
        setContentView(R.layout.activity_moment_details);
        initView();
        initData();
//        putComment("123456789", "d3769dab1d4811e98ee77cd30aeb138e");
//        putZan("123456789", "d3769dab1d4811e98ee77cd30aeb138e");
//        putInsideComment("123456789", "d3769dab1d4811e98ee77cd30aeb138e", "123456789", "d3769dab1d4811e98ee77cd30aeb138e");
    }


    private void putZan(String uuid,boolean ispraise) {
        OkGo.<String>put(Urls.putMomentZan)
                .params("uuid", uuid)
                .params("type", 2)//代表 ：此刻
                .params("praiseFlag", ispraise)//代表 ：此刻
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if (response.code() == 200) {
                            L.d(response.body() + "");
                            ToastUtils.showShort(response.body().substring(response.body().length()-6,response.body().length()-2)+"");
                            commentListAdapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        L.d(response.body() + "");
                        L.d(response.message() + "");
                        L.d(response.code() + "");
                        ToastUtils.showShort(getResources().getString(R.string.errortips));
                    }
                });
    }

    @Override
    protected void onNewIntent(Intent newintent) {
        super.onNewIntent(newintent);
        if (!iwHelper.handleBackPressed()) {
            if (JzvdStd.backPress()) {

            }
        }
        setIntent(newintent);
        uuid = newintent.getStringExtra("uuid");
        if (!TextUtils.isEmpty(uuid)) {
            showLoading();
            getDetails(uuid);
            getCommentList(uuid, pageIndex, pageSize);
        }
    }

    private void initView() {
        loadingView = (LinearLayout) findViewById(R.id.loadingView);
        ivBack = (ImageView) findViewById(R.id.iv_back);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        tvContent = (TextView) findViewById(R.id.tv_content);
        tvComment = (TextView) findViewById(R.id.tv_comment);
        tvComment.getPaint().setFakeBoldText(true);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvMomentName = (TextView) findViewById(R.id.tv_moment_name);
        tvCreateTime = (TextView) findViewById(R.id.tv_create_time);
        tvContent = (TextView) findViewById(R.id.tv_content);
        tvComment = (TextView) findViewById(R.id.tv_comment);
        rvCommonlist = (RecyclerView) findViewById(R.id.rv_commonlist);
        rvCommonlist.setLayoutManager(new LinearLayoutManager(this));
        rvCommonlist.setNestedScrollingEnabled(false);
        commentListAdapter = new CommentListAdapter(R.layout.commentlist_item, null);
        commentListAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            TextView praise = view.findViewById(R.id.iv_zanum);
            CommentBean.ListBeanX beanX = list.get(position);
            int isPraise = beanX.getIsPraise();
            if (isPraise==1){
                int likenum = Integer.parseInt(beanX.getLikeNumber()) - 1;
                beanX.setIsPraise(0);
                beanX.setLikeNumber(likenum+"");
                praise.setText(likenum +"");
                praise.setTextColor(getResources().getColor(R.color.colorWhite));
                Drawable drawable = getResources().getDrawable(R.mipmap.moment_zan);
                drawable.setBounds(0, 0, PxDp.px2dip(this,33),PxDp.px2dip(this,33));
                praise.setCompoundDrawables(drawable, null, null, null);
            }else {
                int likenum = Integer.parseInt(beanX.getLikeNumber()) + 1;
                praise.setText(likenum +"");
                beanX.setIsPraise(1);
                beanX.setLikeNumber(likenum+"");
                praise.setTextColor(getResources().getColor(R.color.acs_tagcolor));
                Drawable drawable = getResources().getDrawable(R.mipmap.praise_press);
                drawable.setBounds(0, 0, PxDp.px2dip(this,33),PxDp.px2dip(this,33));
                praise.setCompoundDrawables(drawable, null, null, null);
            }

            putZan(beanX.getUuid(), isPraise ==0?true:false);
        });
        rvCommonlist.setAdapter(commentListAdapter);
        tvNocomment = (TextView) findViewById(R.id.tv_nocomment);

        ivAvator = (ImageView) findViewById(R.id.iv_avator);
        rvImgs = (RecyclerView) findViewById(R.id.rv_imgs);

        rvImgs.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        videoView = findViewById(R.id.video_view);

        rlPlay = (RelativeLayout) findViewById(R.id.rl_play);
        //图片查看初始化
        iwHelper = ImageWatcherHelper.with(MomentDetailActivity.this, new GlideSimpleLoader());

//        preimg = (ImageView) findViewById(R.id.preimg);
    }

    @Override
    public void onBackPressed() {
        if (!iwHelper.handleBackPressed()) {
            if (JzvdStd.backPress()) {
                return;
            }
            super.onBackPressed();
        }

    }

    private void setbanner(MomentDetail detailBean) {
        if (detailBean.getList() != null && detailBean.getList().size() > 0) {
            final List<String> images = new ArrayList<>();
            for (String imgs : detailBean.getList()) {
                imgs = Urls.baseImg + imgs;
                images.add(imgs);
            }

            if (images != null && images.size() > 0) {
//                final List<Uri> dataList = new ArrayList<>();
//                for (String url : images) {
//                    Uri uri = Uri.parse(url);
//                    dataList.add(uri);
//                }

                rvImgs.setAdapter(new BaseQuickAdapter<String, BaseViewHolder>(
                        R.layout.images_item, images) {
                    @Override
                    protected void convert(BaseViewHolder helper, String item) {

                        final SparseArray<ImageView> mapping = new SparseArray<>(); // 这个请自行理解，
                        final ImageView imgs = helper.getView(R.id.iv_imgs);
                        mapping.put(0, imgs);
                        imgs.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                final List<Uri> dataList = new ArrayList<>();
                                dataList.add(Uri.parse(item));
                                iwHelper.show(imgs, mapping, dataList);
                            }
                        });
                        Glide.with(mContext).load(item).into((ImageView) imgs);
                    }
                });
                rvImgs.setVisibility(View.VISIBLE);
            } else {
                rvImgs.setVisibility(View.GONE);
            }

        } else {
            rvImgs.setVisibility(View.GONE);
        }

    }

    private void initData() {
        list = new ArrayList<>();
        Intent intent = getIntent();
        uuid = intent.getStringExtra("uuid");
        if (!TextUtils.isEmpty(uuid)) {
            showLoading();
            getDetails(uuid);
            getCommentList(uuid, pageIndex, pageSize);
        }
    }

    public void showLoading() {
        if (isFinishing()) {
            return;
        }
//        if (dialog==null){
//            dialog = new ProgressDialog(this);
//            dialog.setMessage(getString(cn.flyaudio.flycodelibrary.R.string.default_loading));
//            dialog.setCancelable(false);
//            dialog.setCanceledOnTouchOutside(false);
//            dialog.show();
//        }else {
//            dialog.show();
//        }
        if (loadingView!=null){
            loadingView.setVisibility(View.VISIBLE);
        }

    }

    public void hideLoading() {
//        if (dialog != null && dialog.isShowing()) {
//            dialog.hide();
//            dialog.dismiss();
//        }
        if (loadingView!=null){
            loadingView.setVisibility(View.GONE);
        }

    }

    @Override
    protected void onDestroy() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
        super.onDestroy();
    }

    private void getCommentList(String uuid, int pageIndex, int pageSize) {
        OkGo.<CommentBean>get(Urls.getMomentCommentList)
                .params("pageIndex", pageIndex)
                .params("pageSize", pageSize)
                .params("infoUuid", uuid)
                .execute(new JsonCallback<CommentBean>() {
                    @Override
                    public void onError(Response<CommentBean> response) {
                        hideLoading();
                        L.d("error:" + response.message());
                    }

                    @Override
                    public void onSuccess(Response<CommentBean> response) {
                        hideLoading();
                        if (response.code() == 200) {
                            commentBeanBean = response.body();
                            list = response.body().getList();
                            if (list == null || list.size() == 0) {
                                tvNocomment.setVisibility(View.VISIBLE);
                            } else {
                                tvNocomment.setVisibility(View.GONE);
                            }
                            commentListAdapter.setNewData(list);
                            commentListAdapter.notifyDataSetChanged();
                        }
                    }
                });
    }

    private void getDetails(String uuid) {
        OkGo.<MomentDetail>get(Urls.getMomentdetail + uuid)
                .execute(new JsonCallback<MomentDetail>() {
                    @Override
                    public void onError(Response<MomentDetail> response) {
                        L.d("error:" + response.message());
                        hideLoading();
                    }

                    @Override
                    public void onSuccess(Response<MomentDetail> response) {
                        hideLoading();
                        if (response.code() == 200) {
                            MomentDetail detailBean = response.body();
                            tvContent.setText(detailBean.getContent());
                            String avatar = detailBean.getAvatar();
                            Glide.with(MomentDetailActivity.this).load(Urls.baseImg + avatar)
                                    .apply(RequestOptions.bitmapTransform(new CircleCrop()).placeholder(R.mipmap.userplaceholder))
                                    .into(ivAvator);
                            tvMomentName.setText(detailBean.getName());
                            tvTitle.setText(getResources().getString(R.string.momentdetail));
                            tvCreateTime.setText(detailBean.getCreateTime());
                            if (detailBean.getType() == 2) {
                                setVideo(detailBean);
                            } else {
                                setbanner(detailBean);
                            }

                        }
                    }
                });
    }

    private void setVideo(MomentDetail detailBean) {
        if (detailBean.getList() != null && detailBean.getList().size() > 0) {
            final String url = Urls.baseImg + detailBean.getList().get(0);
            rvImgs.setVisibility(View.GONE);

            rlPlay.setVisibility(View.VISIBLE);
            videoView.setUp(url
                    , "" + detailBean.getContent(), Jzvd.SCREEN_WINDOW_NORMAL);
            Glide.with(MomentDetailActivity.this)
                    .load(url)
                    .into(videoView.thumbImageView);

        }

    }


    @Override
    protected void onPause() {
        super.onPause();
        Jzvd.releaseAllVideos();
    }


    public void putComment(String content, String uuid) {
        MediaType mediaType = MediaType.parse("application/json");
        String body = "{  \"content\": \"" + content + "\",  \"uuid\": \"" + uuid + "\"}";
        RequestBody requestBody = RequestBody.create(mediaType, body);
        OkGo.<String>put(Urls.putMomentComment)
                .upRequestBody(requestBody)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if (response.code() == 200) {
                            L.d(response.body() + "");
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        L.d(response.body() + "");
                        L.d(response.message() + "");
                        L.d(response.code() + "");
                        ToastUtils.showShort("评论失败，请检查网络连接");
                    }
                });
    }

    public void putInsideComment(String commentUuid, String content, String receiveUuid, String uuid) {
        MediaType mediaType = MediaType.parse("application/json");
        String body = "{\n" +
                "  \"commentUuid\": \"" + commentUuid + "\",\n" +
                "  \"content\": \"" + content + "\",\n" +
                "  \"receiveUuid\": \"" + receiveUuid + "\",\n" +
                "  \"uuid\": \"" + uuid + "\"\n" +
                "}";
        RequestBody requestBody = RequestBody.create(mediaType, body);
        OkGo.<String>put(Urls.putInsideComment)
                .upRequestBody(requestBody)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if (response.code() == 200) {
                            L.d(response.body() + "");
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        L.d(response.body() + "");
                        L.d(response.message() + "");
                        L.d(response.code() + "");
                        ToastUtils.showShort("回复失败，请检查网络连接");
                    }
                });
    }


}
