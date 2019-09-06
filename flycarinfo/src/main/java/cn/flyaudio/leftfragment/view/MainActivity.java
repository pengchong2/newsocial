package cn.flyaudio.leftfragment.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import cn.flyaudio.leftfragment.LeftFragment;
import cn.flyaudio.leftfragment.R;

//单独模块测试Activity
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, LeftFragment.newInstance())
                    .commitNow();
        }
    }
}
