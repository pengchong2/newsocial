package cn.flyaudio.flycodelibrary.net;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.lzy.okgo.model.Response;

import java.lang.reflect.Type;

import okhttp3.ResponseBody;

/**
 * @author newtrekWang
 * @fileName OkGoJsonCallBack
 * @createDate 2018/12/5 11:27
 * @email 408030208@qq.com
 * @desc 自动将Response.body 解析为 Bean
 */
public abstract class OkGoJsonCallBack<T> extends OkGoCallBack<T> {
    private Type type;
    private Class<T> clazz;

    public OkGoJsonCallBack(Type type){
        this.type = type;
    }
    public OkGoJsonCallBack(Class<T> clazz){
        this.clazz = clazz;
    }
    /**
     * 不向用户暴露此接口，改为暴露onSuccess(T bean)
     * @param response
     */
    @Override
    final public void onSuccess(Response<T> response) {
        onSuccess(response.body());
    }

    /**
     * 不向用户暴露此接口，已实现解析
     * @param response
     * @return 如果response.body()为null,则返回null,反则解析里面的内容
     * @throws Throwable
     */
    @Override
    final public T convertResponse(okhttp3.Response response) throws Throwable {
        ResponseBody responseBody = response.body();
        if (responseBody == null) {
            return null;
        }
        T data = null;
        Gson gson = new Gson();
        JsonReader jsonReader = new JsonReader(responseBody.charStream());
        if (type != null) {
            data = gson.fromJson(jsonReader,type);
        }else if (clazz != null) {
            data = gson.fromJson(jsonReader,clazz);
        }
        return data;
    }

    /**
     * 请求并解析出的结果,由用户去实现
     * @param jsonBean
     */
    public abstract void onSuccess(T jsonBean);
}
