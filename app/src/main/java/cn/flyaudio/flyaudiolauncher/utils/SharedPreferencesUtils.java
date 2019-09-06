package cn.flyaudio.flyaudiolauncher.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;

/**
 * @author weifule
 * @date 19-8-7
 * Email: fulewei@foxmail.com
 * Description:
 */
public class SharedPreferencesUtils {

    public static final String MEDIA_APP_WIDGET_ID = "mediaAppWidgetId";
    public static final String BT_APP_WIDGET_ID = "btAppWidgetId";

    private static SharedPreferences get(Context context) {
        return context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
    }

    public static boolean getBoolean(Context context, String key, boolean defaultValue) {
        return get(context).getBoolean(key, defaultValue);
    }

    public static void putBoolean(Context context, String key, boolean value) {
        get(context).edit().putBoolean(key, value).apply();
    }

    public static int getInt(Context context, String key, int defaultValue) {
        return get(context).getInt(key, defaultValue);
    }

    public static void putInt(Context context, String key, int value) {
        get(context).edit().putInt(key, value).apply();
    }

    public static long getLong(Context context, String key, long defaultValue) {
        return get(context).getLong(key, defaultValue);
    }

    public static void putLong(Context context, String key, long value) {
        get(context).edit().putLong(key, value).apply();
    }

    public static String getString(Context context, String key, String defaultValue) {
        return get(context).getString(key, defaultValue);
    }

    public static void putString(Context context, String key, String value) {
        get(context).edit().putString(key, value).apply();
    }

    public static Map<String, ?> getAll(Context context) {
        return get(context).getAll();
    }

    public static void remove(Context context, String key) {
        get(context).edit().remove(key).apply();
    }

    public static void registerListener(Context context,
                                        SharedPreferences.OnSharedPreferenceChangeListener listener) {
        get(context).registerOnSharedPreferenceChangeListener(listener);
    }

    public static void unregisterListener(Context context,
                                          SharedPreferences.OnSharedPreferenceChangeListener listener) {
        get(context).unregisterOnSharedPreferenceChangeListener(listener);
    }

}
