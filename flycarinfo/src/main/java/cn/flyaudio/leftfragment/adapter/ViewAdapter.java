package cn.flyaudio.leftfragment.adapter;

import java.util.List;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

/**
 * ViewPager的适配器
 */
public class ViewAdapter extends PagerAdapter {
    private static final String TAG = "yyq123";
    private List<View> viewList;//数据源

    public ViewAdapter(List<View> viewList) {

        this.viewList = viewList;
    }

    //数据源的数目
    @Override
    public int getCount() {

        return viewList.size();
    }

    //view是否由对象产生，官方写arg0==arg1即可
    @Override
    public boolean isViewFromObject(@NonNull View arg0, @NonNull Object arg1) {

        return arg0 == arg1;
    }

    //销毁一个页卡(即ViewPager的一个item)
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        Log.d(TAG, "destroyItem: position " + position);
        container.removeView(viewList.get(position));
    }

    //对应页卡添加上数据
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        Log.d(TAG, "instantiateItem: position " + position);
        container.addView(viewList.get(position));//千万别忘记添加到container
        return viewList.get(position);
    }
}