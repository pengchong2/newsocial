package cn.flyaudio.flycodelibrary.server;
import com.lzy.okgo.model.Progress;



/**
 * @author  彭冲
 * @
 * @param <T>
 */
public interface ProgressListener<T> {

    /**
     * 成功添加任务的回调
     * @param progress
     */
    void onStart(Progress progress);


    /**
     * 下载进行时回调
     * @param progress
     */
    void onProgress(Progress progress);


    /**
     * 下载出错时回调
     * @param progress
     */
    void onError(Progress progress);


    /**
     * 载完成时回调
     * @param t
     * @param progress
     */
    void onFinish(T t, Progress progress);


    /**
     * 被移除时回调
     * @param progress
     */
    void onRemove(Progress progress);
}
