package cn.flyaudio.leftfragment.carlight.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import java.util.List;

import cn.flyaudio.leftfragment.carlight.bean.CarLightObject;
import cn.flyaudio.leftfragment.R;
import cn.flyaudio.sdk.manager.FlyNewEnergyManager;

public class CarLightListAdapter extends BaseAdapter {
    private List<CarLightObject> list;
    private LayoutInflater inflater;
    private Context context;
    private static final String TAG = "yyq123";

    public CarLightListAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void setData(List<CarLightObject> lists) {
        list = lists;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int type = getItemViewType(position);
        if (type == 0) {
            ViewHolder viewHolder;
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.left_fragment_list_item, null);
                viewHolder = new ViewHolder();
                viewHolder.tv_addTokenName = convertView.findViewById(R.id.left_fragment_list_item_tv);
                viewHolder.switch_addToken = convertView.findViewById(R.id.left_fragment_list_item_sw);
                convertView.setTag(viewHolder);

            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
               /* if(position == 0){
                    viewHolder.switch_addToken.setVisibility(View.GONE);
                }else {
                    viewHolder.switch_addToken.setVisibility(View.VISIBLE);
                }*/
            viewHolder.tv_addTokenName.setText(list.get(position).getName());
            viewHolder.switch_addToken.setTag(position);
            //viewHolder.switch_addToken.setOnCheckedChangeListener(null);
            viewHolder.switch_addToken.setChecked(list.get(position).getaSwitch().isChecked());
            viewHolder.switch_addToken.setOnCheckedChangeListener(onCheckedChangeListener);
        }
        return convertView;
    }

    private CompoundButton.OnCheckedChangeListener onCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            int tag = (int) buttonView.getTag();
            list.get(tag).getaSwitch().setChecked(isChecked);
            /*if (isChecked) {
                Log.d(TAG, "onCheckedChanged: true");
            } else {
                Log.d(TAG, "onCheckedChanged: false");
            }*/
            Log.d(TAG, "onCheckedChanged: position："+tag);
            if (!buttonView.isPressed()) {
                return;
            }
            setSWonClick(isChecked, tag);
        }
    };

    private void setSWonClick(boolean isChecked, int position) {
        switch (position) {
            case 0://前雾灯开关
                Log.d(TAG, "setSWonClick: 是否打开前雾灯");
                FlyNewEnergyManager.getInstance().enableFrontFogLamp(isChecked);
                break;
            case 1://后雾灯开关
                FlyNewEnergyManager.getInstance().enableTailFogLamp(isChecked);
                break;
            case 2://日间行车灯开关
                FlyNewEnergyManager.getInstance().enableDrl(isChecked);
                break;
            case 3://双闪灯开关
                FlyNewEnergyManager.getInstance().enableDoubleFlash(isChecked);
                break;
            case 4://倒车灯
                FlyNewEnergyManager.getInstance().enableReversingLamp(isChecked);
                break;
            case 5://刹车灯
                FlyNewEnergyManager.getInstance().enableStoplight(isChecked);
                break;
            default:
                break;

        }

    }


    public static class ViewHolder {
        TextView tv_addTokenName;//token名称
        Switch switch_addToken;//token开关
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

}
