package cn.flyaudio.flyforum.ui.information;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LevelListDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import org.xml.sax.XMLReader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import cn.flyaudio.flycodelibrary.base.ui.BaseActivity;
import cn.flyaudio.flycodelibrary.utils.L;
import cn.flyaudio.flycodelibrary.utils.ToastUtils;
import cn.flyaudio.flyforum.InformationActivity;
import cn.flyaudio.flyforum.R;
import cn.flyaudio.flyforum.Urls;
import cn.flyaudio.flyforum.adapter.CommentListAdapter;
import cn.flyaudio.flyforum.bean.CommentBean;
import cn.flyaudio.flyforum.bean.ImformaDetailBean;
import cn.flyaudio.flyforum.util.JsonCallback;
import cn.flyaudio.flyforum.util.PxDp;


public class ImformationDetailsActivity extends BaseActivity {

    private String uuid;
    private static final String TAG = "ImformationDetails";
    private RecyclerView rvCommonlist;
    private CommentListAdapter commentListAdapter;
    private ImformaDetailBean detailBean = null;
    private CommentBean commentBeanBean = null;
    private List<ImformaDetailBean.AppCommentVOListBean> commentVOList;
    private int pageSize = 20;
    private int pageIndex = 1;
    private TextView tvTitle;
    private TextView tvInfodetalTitle;
    private TextView tvCreateTime;
    private TextView tvMsgnum;
    private TextView tvViewnum;
    private TextView tvNocomment;
    private TextView tvInfocontent;
    private TextView mNonetTv;
    private List<CommentBean.ListBeanX> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imformation_details);
        initView();
        initData();

    }

    @Override
    protected View getLayoutView(LayoutInflater layoutInflater, Bundle savedInstanceState) {
        return null;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    private void initView() {
        tvNocomment = (TextView) findViewById(R.id.tv_nocomment);
        findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        rvCommonlist = (RecyclerView) findViewById(R.id.rv_commonlist);
        rvCommonlist.setNestedScrollingEnabled(false);
        rvCommonlist.setLayoutManager(new LinearLayoutManager(this));
        commentListAdapter = new CommentListAdapter(R.layout.commentlist_item, null);
        commentListAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Log.d(TAG,"view = "+view.getId());
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
                    drawable.setBounds(0, 0, PxDp.px2dip(ImformationDetailsActivity.this,33),PxDp.px2dip(ImformationDetailsActivity.this,33));
                    praise.setCompoundDrawables(drawable, null, null, null);
                }else {
                    int likenum = Integer.parseInt(beanX.getLikeNumber()) + 1;
                    praise.setText(likenum +"");
                    beanX.setIsPraise(1);
                    beanX.setLikeNumber(likenum+"");
                    praise.setTextColor(getResources().getColor(R.color.acs_tagcolor));
                    Drawable drawable = getResources().getDrawable(R.mipmap.praise_press);
                    drawable.setBounds(0, 0, PxDp.px2dip(ImformationDetailsActivity.this,33),PxDp.px2dip(ImformationDetailsActivity.this,33));
                    praise.setCompoundDrawables(drawable, null, null, null);
                }

                putZan(beanX.getUuid(), isPraise ==0?true:false);


            }
        });



        rvCommonlist.setAdapter(commentListAdapter);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvTitle.setText(getResources().getString(R.string.infodetail));
        tvInfodetalTitle = (TextView) findViewById(R.id.tv_infodetal_title);
        tvInfodetalTitle.getPaint().setFakeBoldText(true);
        tvCreateTime = (TextView) findViewById(R.id.tv_create_time);
        tvMsgnum = (TextView) findViewById(R.id.tv_msgnum);
        tvViewnum = (TextView) findViewById(R.id.tv_viewnum);
        tvInfocontent = (TextView) findViewById(R.id.tv_infocontent);
        tvInfocontent.getPaint().setFakeBoldText(true);
        mNonetTv = (TextView)findViewById(R.id.tv_nonet);
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

    public void onNonetTvClick(){
        Log.d(TAG,"onNonetTvClick");
        if (!TextUtils.isEmpty(uuid)) {
            showLoading();
            getDetails(uuid);
            getCommentList(uuid, pageIndex, pageSize);
        }
    }

    private void initData() {
        commentVOList = new ArrayList<>();
        Intent intent = getIntent();
        uuid  = intent.getStringExtra("uuid");
        if (!TextUtils.isEmpty(uuid)) {
            showLoading();
            getDetails(uuid);
            getCommentList(uuid, pageIndex, pageSize);
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Intent newintent = getIntent();
        uuid  = newintent.getStringExtra("uuid");
        if (!TextUtils.isEmpty(uuid)) {
            showLoading();
            getDetails(uuid);
            getCommentList(uuid, pageIndex, pageSize);
        }
    }

    private void getCommentList(String uuid, int pageIndex, int pageSize) {
        OkGo.<CommentBean>get(Urls.getCommentList)
                .params("pageIndex", pageIndex)
                .params("pageSize", pageSize)
                .params("uuid", uuid)
                .execute(new JsonCallback<CommentBean>() {
                    @Override
                    public void onError(Response<CommentBean> response) {
                        L.d("error:" + response.message());
                        hideLoading();
                    }

                    @Override
                    public void onSuccess(Response<CommentBean> response) {
                        hideLoading();
                        if (response.code() == 200) {
                            commentBeanBean = response.body();
                            list = response.body().getList();
                            if (list == null || list.size() == 0) {
                                tvNocomment.setVisibility(View.VISIBLE);
                                return;
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
        OkGo.<ImformaDetailBean>get(Urls.getInfoDetails + "/" + uuid)
                .execute(new JsonCallback<ImformaDetailBean>() {
                    @Override
                    public void onError(Response<ImformaDetailBean> response) {
                        L.d("error:" + response.message());
                        hideLoading();
                        mNonetTv.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onSuccess(Response<ImformaDetailBean> response) {
                        hideLoading();
                        mNonetTv.setVisibility(View.GONE);
                        if (response.code() == 200) {
                            detailBean = response.body();
                            if (!TextUtils.isEmpty(detailBean.getCommentNumber())) {
                                tvMsgnum.setText(detailBean.getCommentNumber() + "");
                            }
                            tvInfodetalTitle.setText("" + detailBean.getTitle());
                            tvCreateTime.setText("" + detailBean.getCreateTime() == null ? "" : detailBean.getCreateTime());
                            if (!TextUtils.isEmpty(detailBean.getLookNumber())) {
                                tvViewnum.setText(detailBean.getLookNumber() + "");
                            }
                            if (!TextUtils.isEmpty(detailBean.getContent())) {
                                tvInfocontent.setMovementMethod(LinkMovementMethod.getInstance());

                                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                                    tvInfocontent.setText(Html.fromHtml(detailBean.getContent(), Html.FROM_HTML_MODE_COMPACT, imgGetter, new MyTagHandler(ImformationDetailsActivity.this)));
                                } else {
                                    tvInfocontent.setText(Html.fromHtml(detailBean.getContent(), imgGetter, new MyTagHandler(ImformationDetailsActivity.this)));
                                }
                            }


                        }
                    }
                });
    }

    final Html.ImageGetter imgGetter = new Html.ImageGetter() {

        @Override
        public Drawable getDrawable(final String source) {
            LevelListDrawable d = new LevelListDrawable();
            Drawable empty = getResources().getDrawable(R.mipmap.flyforum_activity_pic1);
            d.addLevel(0, 0, empty);
            d.setBounds(0, 0, empty.getIntrinsicWidth(), empty.getIntrinsicHeight());

            new LoadImage().execute(source, d);
            return d;
        }
    };

    public class MyTagHandler implements Html.TagHandler {

        private Context mContext;

        public MyTagHandler(Context context) {
            mContext = context.getApplicationContext();
        }

        @Override
        public void handleTag(boolean opening, String tag, Editable output, XMLReader xmlReader) {
            // 处理标签<img>
            if (tag.toLowerCase(Locale.getDefault()).equals("img")) {
                // 获取长度
                int len = output.length();
                // 获取图片地址
                ImageSpan[] images = output.getSpans(len - 1, len, ImageSpan.class);
                String imgURL = images[0].getSource();
                // 使图片可点击并监听点击事件
                output.setSpan(new ClickableImage(mContext, imgURL), len - 1, len, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }

        private class ClickableImage extends ClickableSpan {
            private String url;
            private Context context;

            public ClickableImage(Context context, String url) {
                this.context = context;
                this.url = url;
            }

            @Override
            public void onClick(View widget) {
                RequestOptions options = new RequestOptions();
                options.placeholder(R.mipmap.flyforum_placeholder);

                Glide.with(ImformationDetailsActivity.this).asBitmap().load(url).apply(options).into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
//                        photoView.setVisibility(View.VISIBLE);
//                        StatusBarUtil.setColor(InformationDetailActivity.this, getResources().getColor(R.color.black_333333), 0);
//                        //                     mAttacher = new PhotoViewAttacher(photoView);
//                        photoView.setImageBitmap(resource);
                        //                     mAttacher.update();
                    }
                });

            }
        }
    }

    class LoadImage extends AsyncTask<Object, Void, Bitmap> {
        private LevelListDrawable mDrawable;

        @Override
        protected Bitmap doInBackground(Object... params) {
            String source = (String) params[0];
            mDrawable = (LevelListDrawable) params[1];
            try {
                InputStream is = new URL(source).openStream();
                return BitmapFactory.decodeStream(is);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if (bitmap != null) {
                BitmapDrawable d = new BitmapDrawable(bitmap);
                mDrawable.addLevel(1, 1, d);
                mDrawable.setBounds(0, 0, bitmap.getWidth(), bitmap.getHeight());
                mDrawable.setLevel(1);
                // i don't know yet a better way to refresh TextView
                // mTv.invalidate() doesn't work as expected
                CharSequence t = tvInfocontent.getText();
                tvInfocontent.setText(t);
                tvInfocontent.invalidate();
            }
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
    }
//    private void setData() {
//        if (titleText != null) {
//            title.setText(titleText);
//        }
//
//        if (imageUrl != null && !imageUrl.isEmpty()) {
//            Glide.with(this).load(GetActiListUtils.urlHead + imageUrl)
//                    .apply(new RequestOptions().placeholder(R.mipmap.flyforum_placeholder))
//                    .into(image);
//        }
//
//        if (addressText != null) {
//            address.setText(addressText);
//        }
//
//        if (timeText != null) {
//            time.setText(timeText);
//        }
//        if (applyPeopleNumber != null) {
//            signUp.setText("" + applyPeopleNumber + "人已报名");
//        }
//    }


}
