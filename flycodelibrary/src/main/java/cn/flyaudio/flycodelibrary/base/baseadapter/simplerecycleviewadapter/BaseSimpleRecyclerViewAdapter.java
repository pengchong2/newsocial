package cn.flyaudio.flycodelibrary.base.baseadapter.simplerecycleviewadapter;


import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * @param <T> item type
 * @param <VH> ViewHolder
 * @desc RecyclerViewAdapter 一个简单的RecyclerViewAdapter基类
 */
public abstract class BaseSimpleRecyclerViewAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {
    /**
     * 数据模型
     */
    protected List<T> dataList = new ArrayList<>();

    /**
     * item点击监听器
     */
    protected OnItemClickListener<T> onItemClickListener;

    /**
     * 更新整个列表
     *
     * @param data
     */
    public void setData(List<T> data) {
        dataList.clear();
        dataList.addAll(data);
        notifyDataSetChanged();
    }

    /**
     * 添加数据
     * @param data
     */
    public void addData(List<T> data){
        int lastEndPosition = dataList.size()-1;
        dataList.addAll(data);
        notifyItemRangeChanged(lastEndPosition,data.size());
    }

    /**
     * 清除所有数据
     */
    public void clearData(){
        dataList.clear();
        notifyDataSetChanged();
    }

    /**
     * onBindViewHolder默认实现item点击事件处理，如果不要默认实现，可以在子类去掉super.onBindViewHolder()
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
       holder.itemView.setOnClickListener(new View.OnClickListener() {
                                              @Override
                                              public void onClick(View v) {
                                                  if (onItemClickListener != null) {
                                                      onItemClickListener.onItemClick(dataList.get(position), position);
                                                  }
                                              }
                                          }
       );
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    /**
     * item 点击监听器
     *
     * @param <T>
     */
    public interface OnItemClickListener<T> {
        void onItemClick(T item, int position);
    }

    /**
     * 获取数据列表
     * @return
     */
    public List<T> getDataList() {
        return dataList;
    }

    /**
     * 获取item点击监听器
     * @return
     */
    public OnItemClickListener<T> getOnItemClickListener() {
        return onItemClickListener;
    }

    /**
     * 设置Item点击监听器
     * @param onItemClickListener
     */
    public void setOnItemClickListener(OnItemClickListener<T> onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


}
