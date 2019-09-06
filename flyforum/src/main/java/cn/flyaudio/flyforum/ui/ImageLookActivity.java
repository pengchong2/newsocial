package cn.flyaudio.flyforum.ui;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import cn.flyaudio.flyforum.R;

public class ImageLookActivity extends Activity {
    ViewPager viewpager1;
    TextView number;
    int pos;
    ImageView imv_back;
    private Context context;
    View v;
    private List<String> images;

    public ImageLookActivity(Context context){
        this.context = context;
    }



    private void init(){
        v = LayoutInflater.from(context).inflate(R.layout.view_image,null);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        viewpager1 = v.findViewById(R.id.viewpager1);
        number = v.findViewById(R.id.number);
//        SouQuanFragment2.isPush =false ;
//        imv_back = v.findViewById(R.id.imv_back);
        imv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        initOther();
    }


    public void initOther() {
        pos = getIntent().getIntExtra("pos",-1);
        number.setText(pos+1+"/"+images.size());
        viewpager1.setAdapter(adapter);
        viewpager1.setCurrentItem(pos);
        viewpager1.addOnPageChangeListener(new ViewPager.OnPageChangeListener(){
            @Override
            public void onPageScrollStateChanged(int arg0) {


            }
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {


            }
            @Override
            public void onPageSelected(int position) {
                pos=position;
                number.setText(pos+1+"/"+images.size());
            }
        });
    }

    PagerAdapter adapter=new PagerAdapter(){
        @Override
        public int getCount() {
            return images.size();
        }
        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0==arg1;
        }
        @Override
        public void destroyItem(ViewGroup container, int position, Object o){
        }
        @Override
        public Object instantiateItem(ViewGroup container,int position){
            ImageView im=new ImageView(context);
            Glide.with(context).load(images.get(position)).into(im);
            container.addView(im);
            return im;
        }
    };

}
