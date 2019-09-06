package cn.flyaudio.flyforum.util;

import android.util.Log;

import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.model.Response;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;

public abstract class JsonCallback<T> extends AbsCallback<T> {

    @Override
    public abstract void onError(Response<T> response);


    @SuppressWarnings("unchecked")
    @Override
    public T convertResponse(okhttp3.Response response) throws Throwable {
        ResponseBody body = response.body();
        if (body == null) {
            Log.e("JsonCallback",response.message());
            return null;
        }
        String result = body.string();

        Type genType = getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        return (T) JsonUtil.parseJson(result, (Class) params[0]);
    }
}
