package cn.flyaudio.flycodelibrary.base.baseadapter.baselistview;

import android.content.Context;


import java.util.List;

import cn.flyaudio.flycodelibrary.base.baseadapter.baselistview.base.ItemViewDelegate;

public abstract class BaseListViewAdapter<T> extends MultiItemTypeAdapter<T>
{

    public BaseListViewAdapter(Context context, final int layoutId, List<T> datas)
    {
        super(context, datas);

        addItemViewDelegate(new ItemViewDelegate<T>()
        {
            @Override
            public int getItemViewLayoutId()
            {
                return layoutId;
            }

            @Override
            public boolean isForViewType(T item, int position)
            {
                return true;
            }

            @Override
            public void convert(ViewHolder holder, T t, int position)
            {
                BaseListViewAdapter.this.convert(holder, t, position);
            }
        });
    }

    protected abstract void convert(ViewHolder viewHolder, T item, int position);

}
