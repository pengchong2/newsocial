package cn.flyaudio.flycodelibrary.bus;

/**
 * @author newtrekWang
 * @fileName EventBean
 * @createDate 2018/11/13 14:11
 * @email 408030208@qq.com
 * @desc 事件总线通用数据类
 */
public class EventBean {

    public EventBean(){}

    public EventBean(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 事件码
     */
    public int code;
    /**
     * 事件消息
     */
    public String msg;
    /**
     * 数据
     */
    public Object data;

}
