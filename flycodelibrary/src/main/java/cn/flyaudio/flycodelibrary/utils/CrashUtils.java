package cn.flyaudio.flycodelibrary.utils;

import android.annotation.SuppressLint;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresPermission;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.Thread.UncaughtExceptionHandler;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


import cn.flyaudio.flycodelibrary.constant.MemoryConstants;

import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

/**
 * @className CrashUtils
 * @createDate 2018/11/5 15:15
 * @author newtrekWang
 * @email 408030208@qq.com
 * @desc 程序崩溃处理工具
 *
 */
public final class CrashUtils {
    private static final String TAG = "CrashUtils";
    private static final String CRASH = "crash";
    private static final String CACHE = "cache";
    private static final String BACKUP = "backup";
    /**
     * 日志文件总大小限制 默认为20M
     */
    private static  double sizeLimit = 20d;
    /**
     * 默认的崩溃日志存放目录
     */
    private static String defaultDir;
    /**
     * 自定义崩溃日志存放目录
     */
    private static String dir;
    /**
     * 自定义对应的缓存路径
     */
    private static String cacheDir;
    /**
     * 默认缓存路径
     */
    private static String defaultCacheDir;
    /**
     * 自定义对应的备份路径
     */
    private static String backupDir;
    /**
     * 默认备份路径
     */
    private static String defaultBackupDir;
    /**
     * 应用包名
     */
    private  static String  packageName;
    /**
     * APP版本名字
     */
    private static String versionName;
    /**
     * APP版本号
     */
    private static int    versionCode;
    /**
     * 文件分隔符
     */
    private static final String FILE_SEP = System.getProperty("file.separator");
    /**
     * 时间格式
     */
    @SuppressLint("SimpleDateFormat")
    private static final Format FORMAT   = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
    /**
     * 默认的崩溃异常处理者
     */
    private static UncaughtExceptionHandler DEFAULT_UNCAUGHT_EXCEPTION_HANDLER;
    /**
     * 自定义的崩溃异常处理者
     */
    private static  UncaughtExceptionHandler UNCAUGHT_EXCEPTION_HANDLER;
    /**
     * 异常监听器
     */
    private static OnCrashListener sOnCrashListener;

    static {
        try {
            PackageInfo pi = AppUtils.getApp()
                    .getPackageManager()
                    .getPackageInfo(AppUtils.getApp().getPackageName(), 0);
            if (pi != null) {
                versionName = pi.versionName;
                versionCode = pi.versionCode;
                packageName = pi.packageName;
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        DEFAULT_UNCAUGHT_EXCEPTION_HANDLER = Thread.getDefaultUncaughtExceptionHandler();

        UNCAUGHT_EXCEPTION_HANDLER = new UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(final Thread t, final Throwable e) {
                if (e == null) {
                    if (DEFAULT_UNCAUGHT_EXCEPTION_HANDLER != null) {
                        DEFAULT_UNCAUGHT_EXCEPTION_HANDLER.uncaughtException(t, null);
                    } else {
                        android.os.Process.killProcess(android.os.Process.myPid());
                        System.exit(1);
                    }
                    return;
                }
                final String time = FORMAT.format(new Date(System.currentTimeMillis()));
                final StringBuilder sb = new StringBuilder();
                final String head = "************* Log Head ****************" +
                        "\nApplication Package: " + packageName +
                        "\nTime Of Crash      : " + time +
                        "\nDevice Manufacturer: " + Build.MANUFACTURER +
                        "\nDevice Model       : " + Build.MODEL +
                        "\nAndroid Version    : " + Build.VERSION.RELEASE +
                        "\nAndroid SDK        : " + Build.VERSION.SDK_INT +
                        "\nApp VersionName    : " + versionName +
                        "\nApp VersionCode    : " + versionCode +
                        "\n************* Log Head ****************\n\n";
                sb.append(head);
                StringWriter sw = new StringWriter();
                PrintWriter pw = new PrintWriter(sw);
                e.printStackTrace(pw);
                Throwable cause = e.getCause();
                while (cause != null) {
                    cause.printStackTrace(pw);
                    cause = cause.getCause();
                }
                pw.flush();
                sb.append(sw.toString());
                final String crashInfo = sb.toString();
                // 判断要不要清理
                autoClean();
                // 写入日志
                final String fullPath = (cacheDir == null ? defaultCacheDir : cacheDir) +packageName+"_"+time + ".txt";
                if (createOrExistsFile(fullPath)) {
                    input2File(crashInfo, fullPath);
                } else {
                    Log.e("CrashUtils", "create " + fullPath + " failed!");
                }

                if (sOnCrashListener != null) {
                    sOnCrashListener.onCrash(crashInfo, e);
                }

                if (DEFAULT_UNCAUGHT_EXCEPTION_HANDLER != null) {
                    DEFAULT_UNCAUGHT_EXCEPTION_HANDLER.uncaughtException(t, e);
                }
            }
        };
    }

    /**
     * 自动判断日志文件大小，超过20M则将cache目录下的日志copy覆盖到backup目录，并删除cache中的文件
     */
    private static void autoClean(){
        String cachePath = cacheDir == null?defaultCacheDir:cacheDir;
        String backupPath = backupDir == null ? defaultBackupDir:backupDir;
        double cacheSize = StorageSizeUtils.getFileOrFilesSize(cachePath,MemoryConstants.MB);
        if (cacheSize > sizeLimit){
            FileUtils.deleteAllInDir(backupPath);
            FileUtils.copyDir(cachePath,backupPath);
            FileUtils.deleteAllInDir(cachePath);
        }
    }


    /**
     * 防止实例化
     */
    private CrashUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 初始化
     * <p>APP必须持有SD文件写权限
     * {@code <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />}</p>
     *
     */
    @SuppressLint("MissingPermission")
    @RequiresPermission(WRITE_EXTERNAL_STORAGE)
    public static void init() {
        init("");
    }

    /**
     * 初始化
     * <p>APP必须持有
     * {@code <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />}</p>
     *
     * @param crashDir 崩溃信息保存路径
     */
    @SuppressLint("MissingPermission")
    @RequiresPermission(WRITE_EXTERNAL_STORAGE)
    public static void init(@NonNull final File crashDir) {
        init(crashDir.getAbsolutePath(), null);
    }

    /**
     * 初始化
     * <p>APP必须持有
     * {@code <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />}</p>
     * @param crashDirPath 崩溃信息保存路径
     */
    @SuppressLint("MissingPermission")
    @RequiresPermission(WRITE_EXTERNAL_STORAGE)
    public static void init(final String crashDirPath) {
        init(crashDirPath, null);
    }

    /**
     * 初始化
     * <p>APP必须持有
     * {@code <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />}</p>
     *
     * @param onCrashListener 崩溃回调
     */
    @SuppressLint("MissingPermission")
    @RequiresPermission(WRITE_EXTERNAL_STORAGE)
    public static void init(final OnCrashListener onCrashListener) {
        init("", onCrashListener);
    }


    /**
     * 初始化
     * <p>APP必须持有
     * {@code <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />}</p>
     * @param crashDir 崩溃信息保存路径
     * @param onCrashListener 崩溃回调
     */
    @SuppressLint("MissingPermission")
    @RequiresPermission(WRITE_EXTERNAL_STORAGE)
    public static void init(@NonNull final File crashDir, final OnCrashListener onCrashListener) {
        init(crashDir.getAbsolutePath(), onCrashListener);
    }

    /**
     *
     * @param crashDirPath 崩溃信息保存路径
     * @param onCrashListener 崩溃回调
     */
    @RequiresPermission(WRITE_EXTERNAL_STORAGE)
    public static void init(final String crashDirPath, final OnCrashListener onCrashListener) {
        // 自定义保存路径
        if (isSpace(crashDirPath)) {
            dir = null;
            cacheDir = null;
            backupDir = null;
        } else {
            dir = crashDirPath.endsWith(FILE_SEP) ? crashDirPath+CRASH+FILE_SEP+packageName+FILE_SEP : crashDirPath + FILE_SEP+CRASH+FILE_SEP+packageName+FILE_SEP;
            cacheDir = dir + CACHE + FILE_SEP;
            backupDir = dir + BACKUP + FILE_SEP;
            FileUtils.createOrExistsDir(cacheDir);
            FileUtils.createOrExistsDir(backupDir);
        }
        // 默认保存路径
        String removeAbleSDPath =  SDCardUtils.getDefaultRemoveableSDCardPath();
        if (removeAbleSDPath == null){
            String defaultSDPath = SDCardUtils.getDefaultSDCardPath();
            String defaultAppCachePath = AppUtils.getApp().getCacheDir().getAbsolutePath();
            if (defaultSDPath != null){
                defaultDir = defaultSDPath.endsWith(FILE_SEP) ? defaultSDPath+CRASH+FILE_SEP+packageName+FILE_SEP : defaultSDPath+FILE_SEP+CRASH+FILE_SEP+packageName+FILE_SEP;
            }else{
                defaultDir = defaultAppCachePath.endsWith(FILE_SEP) ? defaultAppCachePath+CRASH+FILE_SEP+packageName+FILE_SEP : defaultAppCachePath+FILE_SEP+CRASH+FILE_SEP+packageName+FILE_SEP;
            }
        }else{
            defaultDir = removeAbleSDPath.endsWith(FILE_SEP) ? removeAbleSDPath+CRASH+FILE_SEP+packageName+FILE_SEP : removeAbleSDPath+FILE_SEP+CRASH+FILE_SEP+packageName+FILE_SEP;
        }

        defaultBackupDir = defaultDir+BACKUP+FILE_SEP;
        defaultCacheDir = defaultDir+CACHE+FILE_SEP;
        boolean create1 = FileUtils.createOrExistsDir(defaultCacheDir);
        boolean create2 =  FileUtils.createOrExistsDir(defaultBackupDir);
        Log.e(TAG,"customDir:"+dir+"\n  defaultDir: "+defaultDir +"\n defaultCache: "+defaultCacheDir+create1+"\n defaultBackup: "+defaultBackupDir+create2);
        sOnCrashListener = onCrashListener;
        Thread.setDefaultUncaughtExceptionHandler(UNCAUGHT_EXCEPTION_HANDLER);
    }

    /**
     * 获取当前崩溃日志缓存文件夹最大容量 单位为MB
     * @return 当前崩溃日志缓存文件夹最大容量 单位为MB
     */
    public static double getCacheSizeLimit() {
        return sizeLimit;
    }

    /**
     * 设置崩溃日志缓存文件夹最大容量 单位为MB
     * @param sizeLimit 容量大小 单位为MB
     */
    public static void setCacheSizeLimit(final double sizeLimit) {
        CrashUtils.sizeLimit = sizeLimit;
    }

    ///////////////////////////////////////////////////////////////////////////
    // 接口
    ///////////////////////////////////////////////////////////////////////////

    /**
     * 崩溃监听器
     */
    public interface OnCrashListener {
        /**
         * 崩溃回调
         * @param crashInfo 崩溃信息
         * @param e 异常
         */
        void onCrash(String crashInfo, Throwable e);
    }

    ///////////////////////////////////////////////////////////////////////////
    // other utils methods
    ///////////////////////////////////////////////////////////////////////////

    /**
     * 把崩溃信息写入到文件（使用线程池）
     * @param input
     * @param filePath
     */
    private static void input2File(final String input, final String filePath) {
        Future<Boolean> submit = Executors.newSingleThreadExecutor().submit(new Callable<Boolean>() {
            @Override
            public Boolean call() {
                BufferedWriter bw = null;
                try {
                    bw = new BufferedWriter(new FileWriter(filePath, true));
                    bw.write(input);
                    return true;
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                } finally {
                    try {
                        if (bw != null) {
                            bw.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        try {
            if (submit.get()) {return;}
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.e("CrashUtils", "write crash info to " + filePath + " failed!");
    }

    /**
     * 不存在则创建文件
     * @param filePath
     * @return
     */
    private static boolean createOrExistsFile(final String filePath) {
        File file = new File(filePath);
        if (file.exists()) {return file.isFile();}
        if (!createOrExistsDir(file.getParentFile())) {
            return false;
        }
        try {
            return file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 不存在则创建文件目录
     * @param file
     * @return
     */
    private static boolean createOrExistsDir(final File file) {
        return file != null && (file.exists() ? file.isDirectory() : file.mkdirs());
    }

    /**
     * 是否是空白
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
