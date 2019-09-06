package cn.flyaudio.flyforum.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;

import java.util.List;

import cn.flyaudio.flyforum.util.GetIndexListUtils;

/**
 * Created by necorchen on 18-10-10.
 */

public class PictureView extends LinearLayout {
    private static final String TAG = "PictureView";
    private List<String> imageUrls;

    public PictureView(Context context) {
        super(context);
    }

    public PictureView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PictureView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public PictureView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    public void setPictureList(List<String> list){

        this.imageUrls = list;

        if(list.size()<=0){
            setVisibility(View.GONE);
            return;
        }
        setVisibility(View.VISIBLE);
        if(list.size()>1){
            for(int i=0;i<list.size();i++){
                if(i>3){
                    break;
                }



                ImageView imageView = new ImageView(getContext());
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                Glide.with(getContext()).load(GetIndexListUtils.urlHead+list.get(i)).into(imageView);
                LayoutParams lp = new LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT,1);
                lp.leftMargin =10;
                addView(imageView,lp);


            }
;
        }else {
            ImageView imageView = new ImageView(getContext());
            Glide.with(getContext()).load(GetIndexListUtils.urlHead+list.get(0)).into(imageView);
            LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
            lp.leftMargin =10;
            addView(imageView,lp);

        }

        for (int i = 0; i < this.getChildCount(); i++) {
            if (i > 3) return;
            click(i);

        }

    }

    private void click(final int position){
        this.getChildAt(position).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onPictureViewListener != null){
                    onPictureViewListener.onPictureView(imageUrls,position);
                }
            }
        });
    }

    public void setOnPictureViewListener(OnPictureViewListener onPictureViewListener){
        this.onPictureViewListener = onPictureViewListener;
    }

    private OnPictureViewListener onPictureViewListener;

    public interface OnPictureViewListener{
        void onPictureView(List<String> pictureUrl , int click);
    }

}
