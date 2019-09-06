package cn.flyaudio.flyaudiolauncher.fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import cn.flyaudio.flyaudiolauncher.R;
import cn.flyaudio.flyforum.CampaignFragment;
import cn.flyaudio.flyforum.InformationFragment;
import cn.flyaudio.flyforum.MomentFragment;
import cn.flyaudio.flyforum.RecommendFragment;

/**
 * @author weifule
 * @date 19-7-23
 * Email: fulewei@foxmail.com
 * Description:
 */
public class FlyUnionFragment extends Fragment implements RadioGroup.OnCheckedChangeListener ,View.OnTouchListener{

    private String TAG = "FlyUnionFragment";
    Fragment[] fragmentArray ;
    private RadioGroup fragmentRg;
    private LinearLayout mRecommendLayout,mCampaignLayout,mInformationLayout,mMomentLayout;
    private MyNetworkBroadcastReceiver receiver = new MyNetworkBroadcastReceiver();
    public FlyUnionFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        mRecommendLayout = view.findViewById(R.id.fragmentRecommendlayout);
        mCampaignLayout = view.findViewById(R.id.fragmentCampaignlayout);
        mInformationLayout = view.findViewById(R.id.fragmentInformationlayout);
        mMomentLayout = view.findViewById(R.id.fragmentMomentlayout);
        getContext().registerReceiver(receiver,new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        view.setOnTouchListener(this);
        return view;
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG,"onViewCreated union");
        Log.d("version","flyaudiounion--201908072006");
        fragmentRg = view.findViewById(cn.flyaudio.flyforum.R.id.fragemntrg);
        fragmentArray = new Fragment[]{ new RecommendFragment(fragmentRg),new CampaignFragment(),new InformationFragment(),new MomentFragment()} ;
        fragmentRg.setOnCheckedChangeListener(this);
        getFragmentManager().beginTransaction().add(R.id.fragmentRecommendlayout,fragmentArray[0]).commit();
        getFragmentManager().beginTransaction().add(R.id.fragmentCampaignlayout,fragmentArray[1]).commit();
        getFragmentManager().beginTransaction().add(R.id.fragmentInformationlayout,fragmentArray[2]).commit();
        getFragmentManager().beginTransaction().add(R.id.fragmentMomentlayout,fragmentArray[3]).commit();
    }




    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
        Log.d(TAG,"checkId = "+checkedId);

        switch (checkedId){

            case R.id.recommendrb:
                mRecommendLayout.setVisibility(View.VISIBLE);
                mCampaignLayout.setVisibility(View.INVISIBLE);
                mInformationLayout.setVisibility(View.INVISIBLE);
                mMomentLayout.setVisibility(View.INVISIBLE);
               // getFragmentManager().beginTransaction().replace(R.id.fragmentRecommendlayout,fragmentArray[0]).commit();
                break;
            case R.id.campaignrb:
                mCampaignLayout.setVisibility(View.VISIBLE);
                mRecommendLayout.setVisibility(View.INVISIBLE);
                mInformationLayout.setVisibility(View.INVISIBLE);
                mMomentLayout.setVisibility(View.INVISIBLE);
               // getFragmentManager().beginTransaction().replace(R.id.fragmentCampaignlayout,fragmentArray[1]).commit();
                break;
            case R.id.informationrb:
                mInformationLayout.setVisibility(View.VISIBLE);
                mRecommendLayout.setVisibility(View.INVISIBLE);
                mCampaignLayout.setVisibility(View.INVISIBLE);
                mMomentLayout.setVisibility(View.INVISIBLE);
              //  getFragmentManager().beginTransaction().replace(R.id.fragmentInformationlayout,fragmentArray[2]).commit();
                break;
            case R.id.momentrb:
                mMomentLayout.setVisibility(View.VISIBLE);
                mRecommendLayout.setVisibility(View.INVISIBLE);
                mCampaignLayout.setVisibility(View.INVISIBLE);
                mInformationLayout.setVisibility(View.INVISIBLE);
              //  getFragmentManager().beginTransaction().replace(R.id.fragmentMomentlayout,fragmentArray[3]).commit();
                break;

            default:break;
        }
    }


    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG,"onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG,"onResume");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG,"onDestroyView");
        getContext().unregisterReceiver(receiver);
    }




    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG,"onDetach");
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return true;
    }


    class MyNetworkBroadcastReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent != null){
                Log.d(TAG,"action = "+intent.getAction());
                if(isConn()){
                    Log.d(TAG,"network connected");
                    ((RecommendFragment)fragmentArray[0]).refreshData();
                    ((CampaignFragment)fragmentArray[1]).refreshData();
                    ((InformationFragment)fragmentArray[2]).refreshData();
                    ((MomentFragment)fragmentArray[3]).refreshData();
                }
            }

        }
    }


    public boolean isConn(){
        boolean bisConnFlag=false;
        //1.获取网络连接的管理对象
        ConnectivityManager conManager = (ConnectivityManager)getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        //2.通过管理者对象拿到网络的信息
        NetworkInfo network = conManager.getActiveNetworkInfo();

        if(network!=null){
            //3.网络状态是否可用的返回值
            bisConnFlag=network.isAvailable();
        }

        return bisConnFlag;
    }


}
