package cn.flyaudio.flycodelibrary.server.upload;


import cn.flyaudio.flycodelibrary.server.ProgressListener;

/**
 * ================================================
 * 作    者：彭冲
 * 描    述：全局的上传监听
 * ================================================
 */
public abstract class UploadListener<T> implements ProgressListener<T> {

    public final Object tag;

    public UploadListener(Object tag) {
        this.tag = tag;
    }
}
