package cn.flyaudio.flyaudiolauncher.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import cn.flyaudio.flyaudiolauncher.R;

/**
 * @author weifule
 * @date 19-7-23
 * Email: fulewei@foxmail.com
 * Description:
 */
public class CarInfoFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_carinfo, container, false);
        return view;
    }
}
