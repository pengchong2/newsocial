package cn.flyaudio.flycodelibrary.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;


import androidx.annotation.NonNull;
import androidx.collection.SimpleArrayMap;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

/**
 * @className SPUtils
 * @createDate 2018/11/6 18:17
 * @author newtrekWang
 * @email 408030208@qq.com
 * @desc SharePref工具
 *
 */
@SuppressLint("ApplySharedPref")
public final class SPUtils {
    /**
     * 因为一个SharePre文件对应一个SPUtils工具实例，所以用一个容器保存所有SPUtils
     */
    private static final SimpleArrayMap<String, SPUtils> SP_UTILS_MAP = new SimpleArrayMap<>();
    /**
     * 默认的SP名
     */
    private static final String DEFAULT_SP_NAME = "flyaudio";
    /**
     * SharedPreferences 实例
     */
    private SharedPreferences sp;

    /**
     * 获取文件名为默认名的SP工具单例，mode默认为MODE_PRIVATE
     * @return SP工具单例
     */
    public static SPUtils getInstance() {
        return getInstance(DEFAULT_SP_NAME, Context.MODE_PRIVATE);
    }

    /**
     * 获取文件名为默认名的SP工具单例
     * @param mode 模式
     * @return SP工具单例
     */
    public static SPUtils getInstance(final int mode) {
        return getInstance(DEFAULT_SP_NAME, mode);
    }

    /**
     * 获取指定文件名的SP工具单例
     * @param spName sp文件名
     * @return SP工具单例
     */
    public static SPUtils getInstance(@NonNull String spName) {
        return getInstance(spName, Context.MODE_PRIVATE);
    }

    /**
     * 获取指定文件名，指定模式的SP工具单例
     * @param spName sp文件名
     * @param mode 模式
     * @return SP工具单例
     */
    public static SPUtils getInstance(@NonNull String spName, final int mode) {
        if (isSpace(spName)) {
            spName = DEFAULT_SP_NAME;
        }
        SPUtils spUtils = SP_UTILS_MAP.get(spName);
        if (spUtils == null) {
            spUtils = new SPUtils(spName, mode);
            SP_UTILS_MAP.put(spName, spUtils);
        }
        return spUtils;
    }

    /**
     * 构造私有
     * @param spName sp文件名
     */
    private SPUtils(final String spName) {
        sp = AppUtils.getApp().getSharedPreferences(spName, Context.MODE_PRIVATE);
    }

    /**
     * 构造私有
     * @param spName sp文件名
     * @param mode 模式
     */
    private SPUtils(final String spName, final int mode) {
        sp = AppUtils.getApp().getSharedPreferences(spName, mode);
    }

    /**
     * 放字符串值,(没有commit)
     *
     * @param key   键名
     * @param value 键值
     */
    public void put(@NonNull final String key, final String value) {
        sp.edit().putString(key, value).commit();
    }

    /**
     * 获取键值
     *
     * @param key 键名
     * @return 键值，如果未获取则返回空字串
     */
    public String getString(@NonNull final String key) {
        return getString(key, "");
    }

    /**
     * 获取string键值
     *
     * @param key          键名
     * @param defaultValue 默认值
     * @return 键值，如果未获取则返回defaultValue
     */
    public String getString(@NonNull final String key, final String defaultValue) {
        return sp.getString(key, defaultValue);
    }

    /**
     * 放int值
     *
     * @param key   键名
     * @param value 键值
     */
    public void put(@NonNull final String key, final int value) {
        sp.edit().putInt(key, value).commit();
    }


    /**
     * 获取int键值
     *
     * @param key 键名
     * @return 键值，获取失败则返回-1
     */
    public int getInt(@NonNull final String key) {
        return getInt(key, -1);
    }

    /**
     * 获取int键值
     *
     * @param key          键名
     * @param defaultValue 默认值
     * @return int键值，如果未获取则返回defaultValue
     */
    public int getInt(@NonNull final String key, final int defaultValue) {
        return sp.getInt(key, defaultValue);
    }

    /**
     * 放long键值
     *
     * @param key   键名
     * @param value 键值
     */
    public void put(@NonNull final String key, final long value) {
        sp.edit().putLong(key, value).commit();
    }


    /**
     * 获取long键值
     *
     * @param key 键名
     * @return 键值，获取失败则返回-1
     */
    public long getLong(@NonNull final String key) {
        return getLong(key, -1L);
    }

    /**
     * 获取long键值
     *
     * @param key          键名
     * @param defaultValue 默认值
     * @return 键值，获取失败则返回defaultValue
     */
    public long getLong(@NonNull final String key, final long defaultValue) {
        return sp.getLong(key, defaultValue);
    }

    /**
     * 放float键值
     *
     * @param key   键名
     * @param value 键值
     */
    public void put(@NonNull final String key, final float value) {
        sp.edit().putFloat(key, value).commit();
    }


    /**
     * 返回float键值
     *
     * @param key 键名
     * @return 键值，获取失败则返回-1
     */
    public float getFloat(@NonNull final String key) {
        return getFloat(key, -1f);
    }

    /**
     * 返回float键值
     *
     * @param key          键名
     * @param defaultValue 默认值
     * @return 键值，获取失败则返回defaultValue
     */
    public float getFloat(@NonNull final String key, final float defaultValue) {
        return sp.getFloat(key, defaultValue);
    }

    /**
     * 放boolean键值
     *
     * @param key   键名
     * @param value 键值
     */
    public void put(@NonNull final String key, final boolean value) {
        sp.edit().putBoolean(key, value).commit();
    }


    /**
     * 获取float键值
     *
     * @param key 键名
     * @return 键值，获取失败则返回false
     */
    public boolean getBoolean(@NonNull final String key) {
        return getBoolean(key, false);
    }

    /**
     * 获取float键值
     *
     * @param key          键名
     * @param defaultValue 默认值
     * @return 键值，获取失败则返回defaultValue
     */
    public boolean getBoolean(@NonNull final String key, final boolean defaultValue) {
        return sp.getBoolean(key, defaultValue);
    }

    /**
     * 放Set<String>值
     *
     * @param key   键名
     * @param value 键值
     */
    public void put(@NonNull final String key, final Set<String> value) {
        sp.edit().putStringSet(key, value).commit();
    }


    /**
     * 返回Set<String>值
     *
     * @param key 键名
     * @return string集合值，获取失败则返回一个空set
     */
    public Set<String> getStringSet(@NonNull final String key) {
        return getStringSet(key, Collections.<String>emptySet());
    }

    /**
     * 返回Set<String>值
     *
     * @param key          键名
     * @param defaultValue 默认值
     * @return string集合值，获取失败则返回defaultValue
     */
    public Set<String> getStringSet(@NonNull final String key,
                                    final Set<String> defaultValue) {
        return sp.getStringSet(key, defaultValue);
    }

    /**
     * 获取所有的键值对，以map形式
     *
     * @return 所有的键值对
     */
    public Map<String, ?> getAll() {
        return sp.getAll();
    }

    /**
     * 是否含有指定的键值对
     *
     * @param key 键名
     * @return {@code true}: 是 <br>{@code false}: 否
     */
    public boolean contains(@NonNull final String key) {
        return sp.contains(key);
    }

    /**
     * 移除指定键值对
     *
     * @param key 键名
     */
    public void remove(@NonNull final String key) {
        sp.edit().remove(key).commit();
    }

    /**
     * 清空所有的键值对
     */
    public void clear() {
        sp.edit().clear().commit();
    }

    /**
     * 是否为空格
     * @param s
     * @return
     */
    private static boolean isSpace(final String s) {
        if (s == null) {return true;}
        for (int i = 0, len = s.length(); i < len; ++i) {
            if (!Character.isWhitespace(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
