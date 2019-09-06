package cn.flyaudio.flyforum.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.github.chrisbanes.photoview.OnPhotoTapListener;
import com.github.chrisbanes.photoview.PhotoView;

import java.util.List;

import cn.flyaudio.flyforum.R;
import cn.flyaudio.flyforum.util.GetIndexListUtils;

public class PictureBrowsingActivity extends Activity implements ViewPager.OnPageChangeListener {
    private static final String TAG = "PictureBrowsingActivity";
    private static final int scrollWidth = 320;
    private ViewPager viewpager;
    private TextView pictureText;
    private RelativeLayout image;

    private List<String> imageUrl;
    private int imagePosition;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_browsing);
        initData();
        initView();
        initAdapter();
        initListener();
    }

    private void initData(){
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("picData");
        imageUrl = bundle.getStringArrayList("images");
        imagePosition = bundle.getInt("imagesPosition");
    }

    private void initView(){
        viewpager = findViewById(R.id.imageLarge);
        pictureText = findViewById(R.id.imageText);
        image = findViewById(R.id.imageLayout);

        pictureText.setText((imagePosition+1)+"/"+imageUrl.size());
//        image.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                finish();
//            }
//        });
    }

    private void initAdapter(){
        SamplePagerAdapter adapter = new SamplePagerAdapter();
        viewpager.setAdapter(adapter);
        viewpager.setCurrentItem(imagePosition);
    }

    private void initListener(){
        viewpager.addOnPageChangeListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        pictureText.setText((position+1)+"/"+imageUrl.size());
    }

    @Override
    public void onPageSelected(int position) {
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    class SamplePagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return imageUrl.size();
        }

        //预加载
        @Override
        public View instantiateItem(ViewGroup container, int position) {
            //加载网络图片,PhotoView框架
            PhotoView photoView = new PhotoView(container.getContext());
            String path = GetIndexListUtils.urlHead + imageUrl.get(position);
            Glide.with(PictureBrowsingActivity.this)
                    .load(path)
                    .apply(new RequestOptions().centerCrop()).into(photoView);

            photoView.setOnPhotoTapListener(new OnPhotoTapListener() {
                @Override
                public void onPhotoTap(ImageView view, float x, float y) {
                    finish();
                }
            });

            container.addView(photoView, ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);

            return photoView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }

}


