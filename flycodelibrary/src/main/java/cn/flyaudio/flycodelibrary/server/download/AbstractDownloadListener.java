package cn.flyaudio.flycodelibrary.server.download;
import java.io.File;
import cn.flyaudio.flycodelibrary.server.ProgressListener;

/**
 * ================================================
 * 作    者：彭冲
 * 描    述：全局的下载监听
 * ================================================
 */
public abstract class AbstractDownloadListener implements ProgressListener<File> {

    public final Object tag;

    public AbstractDownloadListener(Object tag) {
        this.tag = tag;
    }
}
