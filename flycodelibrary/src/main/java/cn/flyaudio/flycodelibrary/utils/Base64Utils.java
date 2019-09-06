package cn.flyaudio.flycodelibrary.utils;

import android.util.Base64;

public final class Base64Utils {

    public static String encode(String json){
        String strBase64 = Base64.encodeToString(json.getBytes(), Base64.DEFAULT);
        return strBase64;
    }

    public static String decode(String string){
        String json = new String(Base64.decode(string.getBytes(), Base64.DEFAULT));
        return json;
    }
}
