package cn.flyaudio.flyforum.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 叶兴运 on
 * 2019/1/17.16:13
 */
public class BaseBean<T> {

    /**
     * status : 200
     * date : Thu, 17 Jan 2019 07:45:42 GMT
     * server : nginx/1.13.9
     * strict-transport-security : max-age=31536000
     * content-type : application/json;charset=UTF-8
     */

    protected String status;
    protected String date;
    protected String server;
    @SerializedName("strict-transport-security")
    protected String stricttransportsecurity;
    @SerializedName("content-type")
    protected String contenttype;
    /**
     * error : {"type":"Internal Server Error","message":"网络繁忙，请稍后再试"}
     */

    private ErrorBean error;


    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

    public T body;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getStricttransportsecurity() {
        return stricttransportsecurity;
    }

    public void setStricttransportsecurity(String stricttransportsecurity) {
        this.stricttransportsecurity = stricttransportsecurity;
    }

    public String getContenttype() {
        return contenttype;
    }

    public void setContenttype(String contenttype) {
        this.contenttype = contenttype;
    }

    public ErrorBean getError() {
        return error;
    }

    public void setError(ErrorBean error) {
        this.error = error;
    }

    public static class ErrorBean {
        /**
         * type : Internal Server Error
         * message : 网络繁忙，请稍后再试
         */

        private String type;
        private String message;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
