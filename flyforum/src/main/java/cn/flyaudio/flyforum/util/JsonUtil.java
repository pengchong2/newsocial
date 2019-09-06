package cn.flyaudio.flyforum.util;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class JsonUtil {

    public static <T> T parseJson(String json, Class<T> bean) {
        Gson gson = new Gson();
        return gson.fromJson(json, bean);
    }

    public static <T> List<T> parseJsonArray(String json, Class<T> bean) {
        Type type = new TypeToken<ArrayList<JsonObject>>() {
        }.getType();
        ArrayList<JsonObject> jsonObjects = new Gson().fromJson(json, type);

        ArrayList<T> arrayList = new ArrayList<>();
        for (JsonObject jsonObject : jsonObjects) {
            arrayList.add(new Gson().fromJson(jsonObject, bean));
        }
        return arrayList;
    }

    public static <T> T parseJson(InputStream json, Class<T> bean) {
        Gson gson = new Gson();
        return gson.fromJson(new InputStreamReader(json), bean);
    }

    public static <T> List<T> parseJsonArray(InputStream json, Class<T> bean) {
        Type type = new TypeToken<ArrayList<JsonObject>>() {
        }.getType();
        ArrayList<JsonObject> jsonObjects = new Gson().fromJson(new InputStreamReader(json), type);

        ArrayList<T> arrayList = new ArrayList<>();
        for (JsonObject jsonObject : jsonObjects) {
            arrayList.add(new Gson().fromJson(jsonObject, bean));
        }
        return arrayList;
    }

    public static String toJson(Object bean) {
        Gson gson = new Gson();
        return gson.toJson(bean);
    }
}
