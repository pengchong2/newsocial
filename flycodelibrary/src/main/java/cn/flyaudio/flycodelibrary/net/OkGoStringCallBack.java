package cn.flyaudio.flycodelibrary.net;

import com.lzy.okgo.model.Response;

import okhttp3.ResponseBody;

/**
 * @author newtrekWang
 * @fileName OkGoStringCallBack
 * @createDate 2018/12/5 11:51
 * @email 408030208@qq.com
 * @desc 自动将Response.body 转为String
 */
public abstract class OkGoStringCallBack extends OkGoCallBack<String> {
    /**
     * 编码方式,默认utf-8
     */
    private String decodeType = "UTF-8";

    public OkGoStringCallBack() {

    }
    public OkGoStringCallBack(String decodeType){
        this.decodeType = decodeType;
    }
    /**
     * 不向用户暴露此接口，改为暴露onSuccess(String result)
     * @param response
     */
    @Override
    final public void onSuccess(Response<String> response) {
        onSuccess(response.body());
    }

    /**
     * 不向用户暴露此接口，以实现转换
     * @param response
     * @return
     * @throws Throwable
     */
    @Override
    final public String convertResponse(okhttp3.Response response) throws Throwable {
        ResponseBody responseBody = response.body();
        if (responseBody == null) {
            return null;
        }
        String result ;
        if (decodeType!=null){
            result = new String(responseBody.bytes(),decodeType);
        }else {
            result = new String(responseBody.bytes());
        }
        return result;
    }

    /**
     * 请求并解析出的结果,由用户去实现
     * @param result
     */
    abstract public void onSuccess(String result);
}
