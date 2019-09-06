package cn.flyaudio.flycodelibrary.utils;

import java.lang.reflect.ParameterizedType;

/**
 * @className TUtil
 * @createDate 2018/11/13 10:12
 * @author yxy
 * @desc 反射实例化类工具
 *
 */
public class TUtil {

    /**
     * 通过Class获取一个实例
     * @param o
     * @param i
     * @param <T>
     * @return
     */
    public static <T> T getT(Object o, int i) {
        try {
            return ((Class<T>) ((ParameterizedType) (o.getClass()
                    .getGenericSuperclass())).getActualTypeArguments()[i])
                    .newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Class<?> forName(String className) {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}

