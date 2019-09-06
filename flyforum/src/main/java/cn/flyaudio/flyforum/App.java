package cn.flyaudio.flyforum;
import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpHeaders;

import cn.flyaudio.flycodelibrary.utils.AppUtils;


/**
 * Created by 叶兴运 on
 * 2019/1/17.20:40
 */
public class App extends MultiDexApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);
        AppUtils.init(this);
        OkGo.getInstance().init(this);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.put("Accept","*/*");
        //httpHeaders.put("X-Access-Token","d2cb9f0025aee3cfd7d8ea");
        //当前token值是根据智慧飞歌登陆账号后生成，当前使用的是测试token,实际中需要注册登陆账号后生成
        //需要安装指定版本智慧飞歌，然后用手机号码注册账号，然后用账号和密码生成token
        httpHeaders.put("X-Access-Token","9e473707241785f0b38a86");
        OkGo.getInstance().addCommonHeaders(httpHeaders);
    }
}
