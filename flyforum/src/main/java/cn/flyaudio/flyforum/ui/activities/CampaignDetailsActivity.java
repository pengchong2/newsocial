package cn.flyaudio.flyforum.ui.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import cn.flyaudio.flycodelibrary.base.ui.BaseActivity;
import cn.flyaudio.flycodelibrary.utils.L;
import cn.flyaudio.flycodelibrary.utils.SPUtils;
import cn.flyaudio.flyforum.R;
import cn.flyaudio.flyforum.Urls;
import cn.flyaudio.flyforum.bean.AcsDetailBean;
import cn.flyaudio.flyforum.bean.ActivityListBean;
import cn.flyaudio.flyforum.bean.ImformaDetailBean;
import cn.flyaudio.flyforum.ui.information.ImformationDetailsActivity;
import cn.flyaudio.flyforum.util.JsonCallback;
import cn.flyaudio.flyforum.util.ZXingUtils;

public class CampaignDetailsActivity extends BaseActivity {
    private String uuid;
    private static final String TAG = "CampaignDetailsActivity";
    private TextView tvTitle;
    private ImageView ivCover;
    private TextView tvAcsTitle;
    private TextView activityTag1;
    private TextView activityTag2;
    private TextView activityTag3;
    private TextView tvAcsContent;
    private TextView tvAcsApplynum;
    private TextView tvAcsViewnum;
    private TextView tvPlace;
    private TextView tvStime;
    private TextView tvAcstatus;
    private Button btnApply;
    private String qrcode;
    // 活动报名还未开始
    private String ACTIVITYNOSTART = "0.0";
    // 立即报名
    private String ACTIVITYDOING = "1.0";
    // 活动报名已结束
    private String ACTIVITYEND = "2.0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campaign_details);
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
        findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvTitle.setText(getResources().getString(R.string.acsdetail));
        ivCover = (ImageView) findViewById(R.id.iv_cover);
        tvAcsTitle = (TextView) findViewById(R.id.tv_acs_title);
        activityTag1 = (TextView) findViewById(R.id.activity_tag1);
        activityTag2 = (TextView) findViewById(R.id.activity_tag2);
        activityTag3 = (TextView) findViewById(R.id.activity_tag3);
        tvAcsContent = (TextView) findViewById(R.id.tv_acs_content);
        tvAcsApplynum = (TextView) findViewById(R.id.tv_acs_applynum);
        tvAcsViewnum = (TextView) findViewById(R.id.tv_acs_viewnum);
        tvPlace = (TextView) findViewById(R.id.tv_place);
        tvStime = (TextView) findViewById(R.id.tv_stime);
        tvAcstatus = (TextView) findViewById(R.id.tv_acstatus);
        btnApply = (Button) findViewById(R.id.btn_apply);
        btnApply.setOnClickListener(new ApplyClickListener());
    }

    private void initData() {
        Intent intent = getIntent();
        uuid = intent.getStringExtra("uuid");
        getdetail(uuid);
        addViewNum();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Intent newintent = getIntent();
        uuid = newintent.getStringExtra("uuid");
        getdetail(uuid);
        addViewNum();
    }

    private void getdetail(String uuid) {
        showLoading();
        OkGo.<AcsDetailBean>get(Urls.getAcsdetail  + uuid)
                .execute(new JsonCallback<AcsDetailBean>() {
                    @Override
                    public void onError(Response<AcsDetailBean> response) {
                        L.d("error:" + response.message());
                        hideLoading();
                    }

                    @Override
                    public void onSuccess(Response<AcsDetailBean> response) {
                        hideLoading();
                        if (response.code() == 200) {
                            AcsDetailBean dataBean = response.body();
                            CampaignDetailsActivity.this.uuid = dataBean.getUuid();
                            qrcode = dataBean.getQrcode();
                            tvAcsTitle.setText(dataBean.getTitle());
                            if (!TextUtils.isEmpty(dataBean.getOneTag())) {
                                activityTag1.setVisibility(View.VISIBLE);
                            }
                            if (!TextUtils.isEmpty(dataBean.getTwoTag())) {
                                activityTag2.setVisibility(View.VISIBLE);
                            }
                            if (!TextUtils.isEmpty(dataBean.getThreeTag())) {
                                activityTag3.setVisibility(View.VISIBLE);
                            }
                            int acStatus = dataBean.getActivityStatus();
                            tvAcstatus.setText(acStatus == 0 ?
                                    getResources().getString(R.string.acstatus_nostart)
                                    : (acStatus == 1 ? getResources().getString(R.string.acstatus_doing)
                                    : getResources().getString(R.string.acstatus_end)));
                            String  applystatus = dataBean.getApplyStatus().toString();
                            Log.d(TAG,"applystatus = "+applystatus);
                            if(ACTIVITYDOING.equals(applystatus)){
                                btnApply.setText(R.string.activity_doing);
                                btnApply.setClickable(true);
                                btnApply.setBackgroundColor(getResources().getColor( R.color.btncolorable));
                            }else if(ACTIVITYEND.equals(applystatus)){
                                btnApply.setText(R.string.activity_end);
                                btnApply.setClickable(false);
                                btnApply.setBackgroundColor(getResources().getColor( R.color.btncolordisable));
                            }else if(ACTIVITYNOSTART.equals(applystatus)){
                                btnApply.setText(R.string.activity_nostart);
                                btnApply.setClickable(false);
                                btnApply.setBackgroundColor(getResources().getColor( R.color.btncolordisable));
                            }
                            activityTag1.setText(dataBean.getOneTag() + "");
                            activityTag2.setText(dataBean.getTwoTag() + "");
                            activityTag3.setText(dataBean.getThreeTag() + "");
                            tvAcsContent.setText(dataBean.getContent());
                            tvPlace.setText(dataBean.getActivitySite() + " | ");
                            tvStime.setText(dataBean.getActivityStartTime());
                            tvAcsViewnum.setText(dataBean.getLookNumber() + "");
                            tvAcsApplynum.setText(getResources().getString(R.string.applynum)+dataBean.getApplyPeopleNumber()+"/"+ dataBean.getAllPeopleNumber() + "");
                            Glide.with(CampaignDetailsActivity.this).load(Urls.baseImg + dataBean.getCover())
                                    .apply(new RequestOptions()).into(ivCover);


                        }
                    }
                });
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    private void addViewNum() {
        String mUuid = SPUtils.getInstance().getString("uuid", "");
        //每次点进来都会增加查看次数。有bug。
        if (!mUuid.equals(uuid)) {
            OkGo.<String>put(Urls.updateLookNumber + "/" + uuid)
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Response<String> response) {
                            Log.d(TAG, "添加查看次数失败");
                        }

                        @Override
                        public void onSuccess(Response<String> response) {
                            if (response.code() == 200) {
                                if (response.body().equals("增加成功")) {
                                    Log.d(TAG, "添加查看次数成功");
                                    SPUtils.getInstance().put("uuid", uuid);
                                }
                            }
                        }
                    });
        }
    }


    private class ApplyClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Bitmap qrImage = ZXingUtils.createQRImage(qrcode, 350, 350);
            showCustomizeDialog(qrImage);
        }
    }

    private void showCustomizeDialog(Bitmap qrImage) {
        final View dialogView = LayoutInflater.from(CampaignDetailsActivity.this)
                .inflate(R.layout.dialog_customzxing, null);
        ImageView zxing = dialogView.findViewById(R.id.iv_zxing);
        zxing.setImageBitmap(qrImage);
        Dialog dialog = new Dialog(CampaignDetailsActivity.this,
                R.style.theme_dialog);
        dialog.setContentView(dialogView);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        dialog.show();
        //解决dialog黑边问题
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(params);

    }

}
