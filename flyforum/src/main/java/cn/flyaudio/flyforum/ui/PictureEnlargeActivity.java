package cn.flyaudio.flyforum.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import cn.flyaudio.flyforum.R;
import cn.flyaudio.flyforum.util.GetIndexListUtils;

public class PictureEnlargeActivity extends Activity implements View.OnClickListener,View.OnTouchListener {
    private static final String TAG = "PictureEnlargeActivity";

    private static final int scrollWidth = 320;
    private ImageView largePictrue;
    private TextView pictureText;
    private RelativeLayout imageLayout;

    private List<String> imageUrl;
    private int itemPosition;
    private int imagePosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_enlarge);

        initData();
        initView();
        setData();
    }

    private void initData(){
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("picData");
        imageUrl = bundle.getStringArrayList("images");
        itemPosition = bundle.getInt("position");
        imagePosition = bundle.getInt("imagesPosition");
    }

    private void initView(){
        largePictrue = findViewById(R.id.imageLarge);
        pictureText = findViewById(R.id.imageText);

        imageLayout = findViewById(R.id.imageLayout);
        imageLayout.setOnClickListener(this);
        imageLayout.setOnTouchListener(this);
    }

    private void setData(){
        if (imageUrl != null && imageUrl.size() > imagePosition){

            Log.e(TAG, "setData: "+ GetIndexListUtils.urlHead +imageUrl.get(imagePosition) );
            Glide.with(this).load(GetIndexListUtils.urlHead + imageUrl.get(imagePosition)).into(largePictrue);
            pictureText.setText((imagePosition+1)+"/"+imageUrl.size());
        }

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.imageLayout){
            Log.e(TAG, "onClick: imageLayout " );
            finish();
        }
    }

    private float lastRawX ;
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.e(TAG, "onTouch: ACTION_DOWN " +imageLayout.getWidth());

                lastRawX = motionEvent.getRawX();
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e(TAG, "onTouch: ACTION_MOVE " );

                float nowDiff = motionEvent.getRawX() - lastRawX;
                if (nowDiff >= scrollWidth){
                    //滑动到下一页

                }
                break;
            case MotionEvent.ACTION_UP:
                Log.e(TAG, "onTouch: ACTION_UP " );
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.e(TAG, "onTouch: ACTION_CANCEL " );
                break;
                default:
        }
        return true;
    }
}
