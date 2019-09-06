package cn.flyaudio.flycodelibrary.utils;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author newtrekWang
 * @className SkinResourceUtils
 * @createDate 2018/10/27 17:08
 * @email 408030208@qq.com
 * @desc 用于加载本地apk或者其他apk中的资源（raw，layout，drawable，mipmap,attr,values/strings,colors）
 * <p></p>
 * 使用本工具，请首先调用initSkinResourceUtil()方法进行初始化
 */
public final class SkinResourceUtils {
    /**
     * 构造器私有
     */
    private SkinResourceUtils(){
        throw new UnsupportedOperationException("u don't touch me!");
    }
    /**
     * log的标签
     */
    public static final String TAG = "SkinResourceUtils";
    /**
     * 资源类型：layout
     */
    public static final String RESOURCE_TYPE_LAYOUT = "layout";
    /**
     * 资源类型：raw
     */
    public static final String RESOURCE_TYPE_RAW = "raw";
    /**
     * 资源类型：drawable
     */
    public static final String RESOURCE_TYPE_DRAWABLE = "drawable";
    /**
     * 资源类型：mipmap
     */
    public static final String RESOURCE_TYPE_MIPMAP = "mipmap";
    /**
     * 资源类型：string
     */
    public static final String RESOURCE_TYPE_STRING = "string";
    /**
     * 资源类型：dimen
     */
    public static final String RESOURCE_TYPE_DIMEN = "dimen";
    /**
     * 资源类型：color
     */
    public static final String RESOURCE_TYPE_COLOR = "color";
    /**
     * 资源类型：menu
     */
    public static final String RESOURCE_TYPE_MENU = "menu";
    /**
     * 资源类型：xml
     */
    public static final String RESOURCE_TYPE_XML = "xml";
    /**
     * 资源类型：anim
     */
    public static final String RESOURCE_TYPE_ANIM = "anim";
    /**
     * 资源类型：animator
     */
    public static final String RESOURCE_TYPE_ANIMATOR = "animator";
    /**
     * 资源类型：attr
     */
    public static final String RESOURCE_TYPE_ATTR = "attr";
    /**
     * 资源类型：id
     */
    public static final String RESOURCE_TYPE_ID = "id";
    /**
     * 资源类型：bool
     */
    public static final String RESOURCE_TYPE_BOOL = "bool";
    /**
     * 资源类型：array
     */
    public static final String RESOURCE_TYPE_ARRAY = "array";
    /**
     * 资源类型：integer
     */
    public static final String RESOURCE_TYPE_INTEGER = "integer";
    /**
     * 资源类型：style
     */
    public static final String RESOURCE_TYPE_STYLE = "style";
    /**
     * 皮肤包资源所在的应用上下文
     */
    private static Context mSkinContext = null;
    /**
     * 本地应用上下文
     */
    private static Context mLocalContext = null;

    /**
     * 获取皮肤包资源所在的上下文
     *
     * @return 皮肤包资源所在的上下文
     */
    public static Context getSkinContext() {
        return mSkinContext;
    }

    /**
     * 获取皮肤包资源里的Resources对象
     *
     * @return Resources对象
     */
    public static Resources getSkinResurces() {
        return mSkinContext.getResources();
    }

    /**
     * 初始化皮肤包工具类设置
     *
     * @param context 本应用的上下文，注意传入的Context不要是Activity级别，否则会造成内存泄漏，Activity无法回收
     * @param pkgName 皮肤包所在的应用包名
     */
    public static void initSkinResourceUtil(@NonNull Context context, @NonNull String pkgName) {
        try {
            mLocalContext = context;
            mSkinContext = context.createPackageContext(pkgName, Context.CONTEXT_INCLUDE_CODE | Context.CONTEXT_IGNORE_SECURITY);
        } catch (NameNotFoundException e) {
            Log.e(TAG, "initSkinResourceUtil: >>>>>" + e.getMessage());
            mSkinContext = context;
        }
    }

    /**
     * 获取指定应用下的指定资源的id
     *
     * @param name    资源名
     * @param type    资源类型
     * @param context 应用上下文
     * @return 资源id 未找到资源id则返回0
     */
    private static int getIdentifier(@NonNull String name, @NonNull String type, @NonNull Context context) {
        try {
            return context.getResources().getIdentifier(name, type, context.getPackageName());
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * 获取指定资源的id，首先从皮肤包apk应用找，找不到则在本地apk找
     *
     * @param name 资源名
     * @param type 资源类型
     * @return 资源id 如果未找到，则返回0
     */
    private static int getIdentifier(@NonNull String name, @NonNull String type) {
        int id = getIdentifier(name, type, mSkinContext);
        if (id == 0 && mSkinContext != mLocalContext) {
            Log.d(TAG, "getIdentifier from mLocalContext");
            id = getIdentifier(name, type, mLocalContext);
        }
        return id;
    }

    /**
     * 获取指定资源的id
     *
     * @param name 资源名
     * @param type 资源类型，使用常量
     * @return 资源id, 未找到会返回0
     */
    public static int getSkinResourceId(@NonNull String name, @NonNull String type) {
        return getIdentifier(name, type);
    }

    /**
     * 获取皮肤包应用的主题Theme
     * @return 皮肤包应用的主题Theme
     */
    public static Resources.Theme getSkinApplicationTheme(){
        return mSkinContext.getTheme();
    }

    /**
     * 获取指定context下的指定资源的id
     *
     * @param name    资源名
     * @param type    资源类型，使用常量
     * @param context 资源所在的上下文context
     * @return 资源id, 未找到会返回0
     */
    public static int getSkinResourceId(@NonNull String name, @NonNull String type, @NonNull Context context) {
        return getIdentifier(name, type, context);
    }

    /**
     * 获取指定drawable的id
     *
     * @param name drawable资源名
     * @return drawable资源id, 未找到会返回0
     */
    public static int getSkinDrawableIdByName(@NonNull String name) {
        return getIdentifier(name, RESOURCE_TYPE_DRAWABLE);
    }

    /**
     * 获取指定mipmap的id
     *
     * @param name drawable资源名
     * @return mipmap资源id, 未找到会返回0
     */
    public static int getSkinMipmapIdByName(@NonNull String name) {
        return getIdentifier(name, RESOURCE_TYPE_MIPMAP);
    }

    /**
     * 获取指定string的id
     *
     * @param name string资源名
     * @return string资源id, 未找到会返回0
     */
    public static int getSkinStringIdByName(@NonNull String name) {
        return getIdentifier(name, RESOURCE_TYPE_STRING);
    }

    /**
     * 获取指定color的id
     *
     * @param name color资源名
     * @return color资源id, 未找到会返回0
     */
    public static int getSkinColorIdByName(@NonNull String name) {
        return getIdentifier(name, RESOURCE_TYPE_COLOR);
    }

    /**
     * 获取指定dimen的id
     *
     * @param name dimen资源名
     * @return dimen资源id, 未找到会返回0
     */
    public static int getSkinDimenIdByName(@NonNull String name) {
        return getIdentifier(name, RESOURCE_TYPE_DIMEN);
    }

    /**
     * 获取指定bool的id
     *
     * @param name bool资源名
     * @return bool资源id, 未找到会返回0
     */
    public static int getSkinBoolIdByName(@NonNull String name) {
        return getIdentifier(name, RESOURCE_TYPE_BOOL);
    }

    /**
     * 获取指定integer的id
     *
     * @param name integer资源名
     * @return integer资源id, 未找到会返回0
     */
    public static int getSkinIntegerIdByName(@NonNull String name) {
        return getIdentifier(name, RESOURCE_TYPE_INTEGER);
    }

    /**
     * 获取指定style的id
     *
     * @param name style资源名
     * @return style资源id, 未找到会返回0
     */
    public static int getSkinStyleIdByName(@NonNull String name) {
        return getIdentifier(name, RESOURCE_TYPE_STYLE);
    }

    /**
     * 获取指定stringArray的id
     *
     * @param name stringArray资源名
     * @return stringArray资源id, 未找到会返回0
     */
    public static int getSkinStringArrayIdByName(@NonNull String name) {
        return getIdentifier(name, RESOURCE_TYPE_ARRAY);
    }

    /**
     * 获取指定integerArray的id
     *
     * @param name integerArray资源名
     * @return integerArray资源id, 未找到会返回0
     */
    public static int getSkinIntegerArrayIdByName(@NonNull String name) {
        return getIdentifier(name, RESOURCE_TYPE_ARRAY);
    }
    /**
     * 获取指定animator的id
     *
     * @param name animator资源名
     * @return animator资源id, 未找到会返回0
     */
    public static int getSkinAnimatorIdByName(@NonNull String name) {
        return getIdentifier(name, RESOURCE_TYPE_ANIMATOR);
    }

    /**
     * 获取指定menu的id
     *
     * @param name menu资源名
     * @return menu资源id, 未找到会返回0
     */
    public static int getSkinMenuIdByName(@NonNull String name) {
        return getIdentifier(name, RESOURCE_TYPE_MENU);
    }

    /**
     * 获取指定anim的id
     *
     * @param name anim资源名
     * @return anim资源id, 未找到会返回0
     */
    public static int getSkinAnimIdByName(@NonNull String name) {
        return getIdentifier(name, RESOURCE_TYPE_ANIM);
    }

    /**
     * 获取指定layout的id
     *
     * @param name layout资源名
     * @return layout资源id, 未找到会返回0
     */
    public static int getSkinLayoutIdByName(@NonNull String name) {
        return getIdentifier(name, RESOURCE_TYPE_LAYOUT);
    }

    /**
     * 获取指定xml的id
     *
     * @param name xml资源名
     * @return xml资源id, 未找到会返回0
     */
    public static int getSkinXmlIdByName(@NonNull String name) {
        return getIdentifier(name, RESOURCE_TYPE_XML);
    }
    /**
     * 获取二进制资源的inputStream
     *
     * @param name raw资源文件名
     * @return 资源的inputStream对象，
     */
    public static InputStream getSkinRawInputStream(@NonNull String name) {
        Context context = mSkinContext;
        int rawId = getIdentifier(name, RESOURCE_TYPE_RAW, context);
        if (rawId == 0 && mSkinContext != mLocalContext) {
            context = mLocalContext;
            rawId = getIdentifier(name, RESOURCE_TYPE_RAW, context);
        }
        return rawId == 0 ? null : context.getResources().openRawResource(rawId);
    }

    /**
     * 获取Assets资源的inputStream
     *
     * @param fileName 资源文件名，默认目录是assets下的目录，比如可以这样写格式：android.jpg或images/mvp.png
     * @return Assets资源的inputStream
     * @throws IOException 可能会抛出IO异常
     */
    public static InputStream getSkinAssetsInputStream(@NonNull String fileName) throws IOException {
        Context context = mSkinContext;
        return context.getAssets().open(fileName);
    }

    /**
     * 获取Assets资源列表
     *
     * @param path Assets相对路径,如果第一个字符为/，则为根目录；第一个字符不为/，默认目录为assets下的目录
     * @return 资源文件列表 string数组形式
     * @throws IOException 可能会抛出IO异常
     */
    public static String[] getSkinAssetsFileNames(@NonNull String path) throws IOException {
        Context context = mSkinContext;
        return context.getAssets().list(path);
    }

    /**
     * 获取LayoutView
     *
     * @param name layout资源名
     * @return layoutView 如果layout资源找不到，则返回null；如果inflate到不能识别的View,会抛找不到View类的异常
     */
    public static View getSkinLayoutViewByName(@NonNull String name) {
        Context context = mSkinContext;
        int id = getIdentifier(name, RESOURCE_TYPE_LAYOUT, context);
        Log.d(TAG, "getSkinLayoutViewByName name: " + name);
        if (id == 0 && mSkinContext != mLocalContext) {
            context = mLocalContext;
            Log.d(TAG, "getSkinLayoutViewByName from mLocalContext");
            id = getIdentifier(name, RESOURCE_TYPE_LAYOUT, context);
        } else {
            Log.d(TAG, "getSkinLayoutViewByName from mSkinContext");
        }
        if (id == 0) {
            return null;
        }
        LayoutInflater mInflater = LayoutInflater.from(context);
        return mInflater.inflate(id, null);
    }

    /**
     * 获取字符串资源，先从皮肤包apk找，找不到然后本地找
     *
     * @param name 字符串资源键名
     * @return string值, 找不到该资源id会返回null，找到id但获取不到资源则抛NotFound异常
     */
    public static String getSkinStringByName(@NonNull String name) {
        Context context = mSkinContext;
        int id = getIdentifier(name, RESOURCE_TYPE_STRING, context);
        if (id == 0 && mSkinContext != mLocalContext) {
            context = mLocalContext;
            id = getIdentifier(name, RESOURCE_TYPE_STRING, context);
        }
        if (id == 0) {
            return null;
        }
        return context.getResources().getString(id);
    }

    /**
     * 获取字符串数组资源，先从皮肤包apk找，找不到然后本地找
     *
     * @param name 字符串数组资源名
     * @return 字符串数组，未找到会返回null或NotFound异常
     */
    public static String[] getSkinStringArrayByName(@NonNull String name) {
        Context context = mSkinContext;
        int id = getIdentifier(name, RESOURCE_TYPE_ARRAY, context);
        if (id == 0 && mSkinContext != mLocalContext) {
            context = mLocalContext;
            id = getIdentifier(name, RESOURCE_TYPE_ARRAY, context);
        }
        if (id == 0) {
            return null;
        }
        return context.getResources().getStringArray(id);
    }

    /**
     * 获取int资源，先从皮肤包apk找，找不到然后本地找
     * @param name int资源名
     * @return int值，未找到会返回0或抛出NotFound异常
     */
    public static int getSkinIntegerByName(@NonNull String name){
        Context context = mSkinContext;
        int id = getIdentifier(name, RESOURCE_TYPE_INTEGER, context);
        if (id == 0 && mSkinContext != mLocalContext) {
            context = mLocalContext;
            id = getIdentifier(name, RESOURCE_TYPE_INTEGER, context);
        }
        return id==0?0:context.getResources().getInteger(id);
    }
    /**
     * 获取int数组资源，先从皮肤包apk找，找不到然后本地找
     * @param name int数组资源名
     * @return int数组，未找到会返回0或抛出NotFound异常
     */
    public static int[] getSkinIntegerArrayByName(@NonNull String name){
        Context context = mSkinContext;
        int id = getIdentifier(name, RESOURCE_TYPE_ARRAY, context);
        if (id == 0 && mSkinContext != mLocalContext) {
            context = mLocalContext;
            id = getIdentifier(name, RESOURCE_TYPE_ARRAY, context);
        }
        return id==0?null:context.getResources().getIntArray(id);
    }

    /**
     * 获取尺寸资源，先从皮肤包apk找，找不到然后本地找
     *
     * @param name 尺寸资源键名
     * @return 尺寸值，找不到该资源id会返回0，找到id但获取不到资源则抛NotFound异常
     */
    public static int getSkinDimenByName(@NonNull String name) {
        Context context = mSkinContext;
        int id = getIdentifier(name, RESOURCE_TYPE_DIMEN, context);
        if (id == 0 && mSkinContext != mLocalContext) {
            context = mLocalContext;
            id = getIdentifier(name, RESOURCE_TYPE_DIMEN, context);
        }
        if (id == 0) {
            return 0;
        }
        return context.getResources().getDimensionPixelOffset(id);
    }

    /**
     * 获取颜色资源，先从皮肤包apk找，找不到然后本地找
     *
     * @param name 颜色资源键名
     * @return 颜色值，找不到该资源id会返回-1，找到id但获取不到资源则抛NotFound异常
     */
    public static int getSkinColorByName(@NonNull String name) {
        Context context = mSkinContext;
        int id = getIdentifier(name, RESOURCE_TYPE_COLOR, context);
        if (id == 0 && mSkinContext != mLocalContext) {
            context = mLocalContext;
            id = getIdentifier(name, RESOURCE_TYPE_COLOR, context);
        }
        if (id == 0) {
            return -1;
        }
        return context.getResources().getColor(id);
    }

    /**
     * 获取Drawable资源，先从皮肤包apk找，找不到然后本地找
     *
     * @param name drawable资源名
     * @return drawable对象，找不到该资源id会返回null，找到id但获取不到资源则抛NotFound异常
     */
    public static Drawable getSkinDrawableByName(@NonNull String name) {
        Context context = mSkinContext;
        int id = getIdentifier(name, RESOURCE_TYPE_DRAWABLE, context);
        if (id == 0 && mSkinContext != mLocalContext) {
            context = mLocalContext;
            id = getIdentifier(name, RESOURCE_TYPE_DRAWABLE, context);
        }
        if (id == 0) {
            return null;
        }
        return context.getResources().getDrawable(id);
    }

    /**
     * 获取mipmap下的drawable资源，先从皮肤包apk找，找不到然后本地找
     *
     * @param name mipmap资源名
     * @return drawable对象，找不到该资源id会返回null，找到id但获取不到资源则抛NotFound异常
     */
    public static Drawable getSkinMipmapByName(@NonNull String name) {
        Context context = mSkinContext;
        int id = getIdentifier(name, RESOURCE_TYPE_MIPMAP, context);
        if (id == 0 && mSkinContext != mLocalContext) {
            context = mLocalContext;
            id = getIdentifier(name, RESOURCE_TYPE_MIPMAP, context);
        }
        if (id == 0) {
            return null;
        }
        return context.getResources().getDrawable(id);
    }

    /**
     * 获取控件id值，先从皮肤包apk找，找不到然后本地找
     *
     * @param name 控件View名
     * @return view的id值
     */
    public static int getSkinViewIdByName(@NonNull String name) {
        Context context = mSkinContext;
        int id = getIdentifier(name, RESOURCE_TYPE_ID, context);
        if (id == 0 && mSkinContext != mLocalContext) {
            context = mLocalContext;
            id = getIdentifier(name, RESOURCE_TYPE_ID, context);
        }
        return id;
    }


    /**
     * 获取drawable目录下的ColorStateList资源，先从皮肤包apk找，找不到然后本地找
     *
     * @param name drawable资源名
     * @return colorStateList对象，找不到该资源id会返回null，找到id但获取不到资源则抛NotFound异常
     */
    public static ColorStateList getSkinColorStateListByName(@NonNull String name) {
        Context context = mSkinContext;
        int id = getIdentifier(name, RESOURCE_TYPE_DRAWABLE, context);
        if (id == 0 && mSkinContext != mLocalContext) {
            context = mLocalContext;
            id = getIdentifier(name, RESOURCE_TYPE_DRAWABLE, context);
        }
        if (id == 0) {
            return null;
        }
        return context.getResources().getColorStateList(id);
    }

    /**
     * 获取属性id
     *
     * @param attrNames 多个属性名
     * @return 属性名对应的id值数组
     */
    public static int[] getSkinAttrsIdByName(@NonNull String[] attrNames) {
        int[] result = new int[attrNames.length];
        for (int i = 0; i < attrNames.length; i++) {
            result[i] = getIdentifier(attrNames[i], RESOURCE_TYPE_ATTR);
        }
        return result;
    }

    /**
     * 获取指定xml资源的解析器
     * @param name xml资源名
     * @return xml资源的解析器
     */
    public static XmlResourceParser getSkinXmlResourceParserByName(@NonNull String name){
        Context context = mSkinContext;
        int id = getIdentifier(name, RESOURCE_TYPE_XML, context);
        if (id == 0 && mSkinContext != mLocalContext) {
            context = mLocalContext;
            id = getIdentifier(name, RESOURCE_TYPE_XML, context);
        }
        return id == 0 ? null:context.getResources().getXml(id);
    }
    /**
     * 获取指定animator资源的解析器
     * @param name animator资源名
     * @return animator资源的解析器
     */
    public static XmlResourceParser getSkinAnimationParserByName(@NonNull String name){
        Context context = mSkinContext;
        int id = getIdentifier(name, RESOURCE_TYPE_ANIMATOR, context);
        if (id == 0 && mSkinContext != mLocalContext) {
            context = mLocalContext;
            id = getIdentifier(name, RESOURCE_TYPE_ANIMATOR, context);
        }
        return id == 0 ? null:context.getResources().getAnimation(id);
    }

    /**
     * 资源id和对应的上下文Context包装类
     */
    private static class Result {
        /**
         * 资源id
         */
        private final int id;
        /**
         * 资源所在的上下文
         */
        private final Context context;

        /**
         * 构造方法
         *
         * @param id      资源id
         * @param context 资源所在的上下文
         */
        public Result(int id, Context context) {
            this.id = id;
            this.context = context;
        }

        public int getId() {
            return id;
        }

        public Context getContext() {
            return context;
        }

        @Override
        public String toString() {
            return "Result{" +
                    "id=" + id +
                    ", context=" + context +
                    '}';
        }
    }

    /**
     * 资源id和对应的上下文Context
     *
     * @param name 资源名
     * @param type 资源类型
     * @return 资源id和对应的上下文Context包装对象
     */
    public static Result getIdentifierAndContext(@NonNull String name, @NonNull String type) {
        Context context = mSkinContext;
        int id = getIdentifier(name, type, context);
        //皮肤包没找到，找本地
        if (id == 0 && mSkinContext != mLocalContext) {
            context = mLocalContext;
            id = getIdentifier(name, type, context);
        }
        //返回结果中包含id和context
        return new Result(id, context);
    }

}
