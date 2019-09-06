package cn.flyaudio.flyaudiolauncher.presentation;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.ImageView;

import cn.flyaudio.flyaudiolauncher.R;

/**
 * @author weifule
 * @date 19-7-8
 * Email: fulewei@foxmail.com
 * Description:
 */
public class ClusterView extends FrameLayout {

    private static final String TAG = "ClusterView";
    private ImageView imageView;

    public ClusterView(Context context) {
        this(context, null);
    }

    public ClusterView(Context context, AttributeSet attrs) {
        super(context);
        inflate(getContext(), R.layout.cluster_view, this);
        imageView = findViewById(R.id.image_map);
    }

    public void enqueueCard(Bitmap bitmap) {
        Log.d(TAG, " enqueueCard getHeight : " + bitmap.getHeight() + " getWidth : " + bitmap.getWidth());
        imageView.setImageBitmap(bitmap);
    }

}