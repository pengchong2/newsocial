package cn.flyaudio.flycodelibrary.net;

import android.app.Application;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.cookie.CookieJarImpl;
import com.lzy.okgo.cookie.store.MemoryCookieStore;
import com.lzy.okgo.https.HttpsUtils;
import com.lzy.okgo.interceptor.HttpLoggingInterceptor;
import com.lzy.okgo.request.GetRequest;
import com.lzy.okgo.request.PostRequest;

import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import cn.flyaudio.flycodelibrary.utils.JSONUtils;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;

/**
 * @author newtrekWang
 * @fileName OkGoUtils
 * @createDate 2018/11/9 17:47
 * @email 408030208@qq.com
 * @desc 基于OkGo的网络请求工具
 */
public final class OkGoUtils {

    private static final String TAG = "OkGoUtils";
    /**
     * 上下文
     */
    private Application application;
    /**
     * okHttpClientBuilder单例
     */
    private OkHttpClient okHttpClient;
    /**
     * 提出来方便动态设置log级别
     */
    private HttpLoggingInterceptor loggingInterceptor;
    /**
     * 构造私有
     */
    private OkGoUtils() {
    }

    /**
     * 单例容器
     */
    static class OkGoUtilsHolder {
        public static final OkGoUtils INSTANCE = new OkGoUtils();
    }

    /**
     * 静态内部类获取单例
     *
     * @return
     */
    public static OkGoUtils getInstance() {
        return OkGoUtilsHolder.INSTANCE;
    }

    /**
     * 初始化操作,使用默认配置
     *
     * @param application
     */
    public void init(Application application) {
        if (application == null){
            throw new NullPointerException("application is null");
        }
        this.application = application;
        this.okHttpClient = getDefaultOkHttpClientBuilder().build();
        // 全部都是默认配置
        OkGo.getInstance().init(application)
                .setOkHttpClient(okHttpClient)
                // 定为不支持缓存
                .setCacheMode(CacheMode.NO_CACHE)
                // 默认失败重连3次
                .setRetryCount(3);
    }

    /**
     * 初始化操作，使用用户自定义的OkHttpClient.Builder
     *
     * @param application
     */
    public void init(Application application ,OkHttpClient.Builder builder ) {
        if (application == null){
            throw new NullPointerException("application is null");
        }
        this.application = application;
        this.okHttpClient = builder.build();
        OkGo.getInstance().init(application)
                .setOkHttpClient(okHttpClient)
                // 定为不支持缓存
                .setCacheMode(CacheMode.NO_CACHE)
                // 默认失败重连3次
                .setRetryCount(3);
    }

    /**
     * 获取默认配置的OkHttpClient.Builder
     * @return
     */
    public OkHttpClient.Builder getDefaultOkHttpClientBuilder(){
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(getLogInterceptor());
        //全局的读取超时时间
        builder.readTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);
        //全局的写入超时时间
        builder.writeTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);
        //全局的连接超时时间
        builder.connectTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);
        //使用内存保持cookie，app退出后，cookie消失
        builder.cookieJar(new CookieJarImpl(new MemoryCookieStore()));
        //信任所有证书,不安全有风险
        HttpsUtils.SSLParams sslParams1 = HttpsUtils.getSslSocketFactory();
        builder.sslSocketFactory(sslParams1.sSLSocketFactory, sslParams1.trustManager);
        return builder;
    }

    /**
     * 初始化cookie说明
     * //使用sp保持cookie，如果cookie不过期，则一直有效
     * builder.cookieJar(new CookieJarImpl(new SPCookieStore(application)));
     * //使用数据库保持cookie，如果cookie不过期，则一直有效
     * builder.cookieJar(new CookieJarImpl(new DBCookieStore(application)));
     * //使用内存保持cookie，app退出后，cookie消失
     * builder.cookieJar(new CookieJarImpl(new MemoryCookieStore()));
     */

    /**
     * 初始化https说明
     *  //方法一：信任所有证书,不安全有风险
     *  HttpsUtils.SSLParams sslParams1 = HttpsUtils.getSslSocketFactory();
     *  //方法二：自定义信任规则，校验服务端证书
     *  HttpsUtils.SSLParams sslParams2 = HttpsUtils.getSslSocketFactory(new SafeTrustManager());
     *  //方法三：使用预埋证书，校验服务端证书（自签名证书）
     *  HttpsUtils.SSLParams sslParams3 = HttpsUtils.getSslSocketFactory(getAssets().open("srca.cer"));
     *  //方法四：使用bks证书和密码管理客户端证书（双向认证），使用预埋证书，校验服务端证书（自签名证书）
     *  HttpsUtils.SSLParams sslParams4 = HttpsUtils.getSslSocketFactory(getAssets().open("xxx.bks"), "123456", getAssets().open("yyy.cer"));
     *  builder.sslSocketFactory(sslParams1.sSLSocketFactory, sslParams1.trustManager);
     * //配置https的域名匹配规则，详细看demo的初始化介绍，不需要就不要加入，使用不当会导致https握手失败
     *  builder.hostnameVerifier(new SafeHostnameVerifier());
     */


    /**
     * log拦截器
     *
     * @return
     */
    private Interceptor getLogInterceptor() {
        loggingInterceptor = new HttpLoggingInterceptor("OkGo");
        //log打印级别，决定了log显示的详细程度
        loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY);
        //log颜色级别，决定了log在控制台显示的颜色
        loggingInterceptor.setColorLevel(Level.INFO);
        return loggingInterceptor;
    }

    /**
     * 设置是否打印请求信息log,只支持默认的loggingInterceptor
     * @param showLog
     */
    public void setShowLog(boolean showLog){
        if (loggingInterceptor == null){
            return;
        }
        if (showLog){
            loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY);
        }else{
            loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.NONE);
        }
    }

    /**
     * 获取全局唯一OkHttpClient
     *
     * @return
     */
    public OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }

/*****************************************GET  start***********************************************************************/
    /**
     * GET方式进行网络申请(无参数请求网络)
     *
     * @param tag       区分请求的tag标签
     * @param url       请求的url
     * @param okGoCallBack  通用回调
     */
    public <T> void getData(Object tag,
                            String url,
                            OkGoCallBack<T> okGoCallBack) {
       getData(tag,url,null,null,okGoCallBack);
    }

    /**
     * GET方式进行网络申请(无参数请求网络)
     *
     * @param tag       区分请求的tag标签
     * @param url       请求的url
     * @param okGoStringCallBack 解析为string回调
     */
    public void getData(Object tag,
                            String url,
                            OkGoStringCallBack okGoStringCallBack) {
        getData(tag,url,null,null,okGoStringCallBack);
    }

    /**
     * GET方式进行网络申请(无参数请求网络)
     *
     * @param tag       区分请求的tag标签
     * @param url       请求的url
     * @param okGoJsonCallBack 解析json为Bean回调
     */
    public <T> void getData(Object tag,
                        String url,
                        OkGoJsonCallBack<T> okGoJsonCallBack) {
        getData(tag,url,null,null,okGoJsonCallBack);
    }


    /**
     * GET方式进行网络申请(有参数请求网络)
     *
     * @param tag       区分请求的tag标签
     * @param url       请求的url
     * @param params    传入参数
     * @param okGoCallBack  通用回调
     */
    public <T> void getData(Object tag,
                            String url,
                            Map<String, Object> params,
                            OkGoCallBack<T> okGoCallBack) {
       getData(tag,url,params,null,okGoCallBack);
    }

    /**
     * GET方式进行网络申请(有参数请求网络)
     *
     * @param tag       区分请求的tag标签
     * @param url       请求的url
     * @param params    传入参数
     * @param okGoStringCallBack  解析为string回调
     */
    public  void getData(Object tag,
                            String url,
                            Map<String, Object> params,
                            OkGoStringCallBack okGoStringCallBack) {
        getData(tag,url,params,null,okGoStringCallBack);
    }

    /**
     * GET方式进行网络申请(有参数请求网络)
     *
     * @param tag       区分请求的tag标签
     * @param url       请求的url
     * @param params    传入参数
     * @param okGoJsonCallBack  解析json为bean回调
     */
    public <T> void getData(Object tag,
                         String url,
                         Map<String, Object> params,
                         OkGoJsonCallBack<T> okGoJsonCallBack) {
        getData(tag,url,params,null,okGoJsonCallBack);
    }

    /**
     *  GET方式进行网络申请(请求头有参数请求网络)
     * @param tag       区分请求的tag标签
     * @param url       请求的url
     * @param params    请求参数，无可置为null
     * @param headers   请求头，不能传入中文字体，否则报错，无可置为null
     * @param okGoCallBack  通用回调
     * @param <T>
     */
    public <T> void getData(Object tag,
                            String url,
                            Map<String, Object> params,
                            Map<String, String> headers,
                            OkGoCallBack<T> okGoCallBack) {
        GetRequest<T> request = OkGo.<T>get(url)
                .tag(tag);
        setGetHeaders(request , headers);
        setGetParams(request , params);
        request.execute(okGoCallBack);
    }


    /**
     *  GET方式进行网络申请(请求头有参数请求网络)
     * @param tag       区分请求的tag标签
     * @param url       请求的url
     * @param params    请求参数，无可置为null
     * @param headers   请求头，不能传入中文字体，否则报错，无可置为null
     * @param okGoStringCallBack  解析为string回调
     */
    public  void getData(Object tag,
                            String url,
                            Map<String, Object> params,
                            Map<String, String> headers,
                            OkGoStringCallBack okGoStringCallBack) {
        GetRequest<String> request = OkGo.<String>get(url)
                .tag(tag);
        setGetHeaders(request , headers);
        setGetParams(request , params);
        request.execute(okGoStringCallBack);
    }

    /**
     *  GET方式进行网络申请(请求头有参数请求网络)
     * @param tag       区分请求的tag标签
     * @param url       请求的url
     * @param params    请求参数，无可置为null
     * @param headers   请求头，不能传入中文字体，否则报错，无可置为null
     * @param okGoJsonCallBack  解析json为bean回调
     */
    public <T> void getData(Object tag,
                         String url,
                         Map<String, Object> params,
                         Map<String, String> headers,
                         OkGoJsonCallBack<T> okGoJsonCallBack) {
        GetRequest<T> request = OkGo.<T>get(url)
                .tag(tag);
        setGetHeaders(request , headers);
        setGetParams(request , params);
        request.execute(okGoJsonCallBack);
    }

/*****************************************GET  end***********************************************************************/


/*****************************************Post  start***********************************************************************/

    /**
     * post表单
     *
     * @param tag       区分请求的tag标签
     * @param url       网络申请url
     * @param params    map类型，存放要存放的表单参数名称与值，只能存放int，String，Boolean等基本类型
     * @param okGoCallBack  通用回调
     */
    public <T> void postData(Object tag,
                         String url,
                         Map<String, Object> params,
                         OkGoCallBack<T> okGoCallBack) {
        postData(tag,url,params,null,okGoCallBack);
    }

    /**
     * post表单
     *
     * @param tag       区分请求的tag标签
     * @param url       网络申请url
     * @param params    map类型，存放要存放的表单参数名称与值，只能存放int，String，Boolean等基本类型
     * @param okGoStringCallBack  解析为String回调
     */
    public void postData(Object tag,
                             String url,
                             Map<String, Object> params,
                             OkGoStringCallBack okGoStringCallBack) {
        postData(tag,url,params,null,okGoStringCallBack);
    }

    /**
     * post表单
     *
     * @param tag       区分请求的tag标签
     * @param url       网络申请url
     * @param params    map类型，存放要存放的表单参数名称与值，只能存放int，String，Boolean等基本类型
     * @param okGoJsonCallBack 解析json为bean回调
     */
    public <T> void postData(Object tag,
                         String url,
                         Map<String, Object> params,
                         OkGoJsonCallBack<T> okGoJsonCallBack) {
        postData(tag,url,params,null,okGoJsonCallBack);
    }


    /**
     * post表单
     *
     * @param tag       区分请求的tag标签
     * @param url       网络申请url
     * @param params    map类型，存放要存放的表单参数名称与值，只能存放int，String，Boolean等基本类型,如果没有可传null
     * @param headers  请求头，如果没有，可传null
     * @param okGoCallBack  通用回调
     */
    public <T> void postData(Object tag,
                             String url,
                             Map<String, Object> params,
                             Map<String, String> headers,
                             OkGoCallBack<T> okGoCallBack) {
        PostRequest<T> request = OkGo.<T>post(url)
                .tag(tag);
        setPostHeaders(request,headers);
        setPostParams(request, params);
        request.execute(okGoCallBack);
    }

    /**
     * post表单
     *
     * @param tag       区分请求的tag标签
     * @param url       网络申请url
     * @param params    map类型，存放要存放的表单参数名称与值，只能存放int，String，Boolean等基本类型,如果没有可传null
     * @param headers  请求头，如果没有，可传null
     * @param okGoStringCallBack  解析为String回调
     */
    public  void postData(Object tag,
                             String url,
                             Map<String, Object> params,
                             Map<String, String> headers,
                             OkGoStringCallBack okGoStringCallBack) {
        PostRequest<String> request = OkGo.<String>post(url)
                .tag(tag);
        setPostHeaders(request,headers);
        setPostParams(request, params);
        request.execute(okGoStringCallBack);
    }

    /**
     * post表单
     *
     * @param tag       区分请求的tag标签
     * @param url       网络申请url
     * @param params    map类型，存放要存放的表单参数名称与值，只能存放int，String，Boolean等基本类型,如果没有可传null
     * @param headers  请求头，如果没有，可传null
     * @param okGoJsonCallBack  解析json为bean回调
     */
    public <T> void postData(Object tag,
                          String url,
                          Map<String, Object> params,
                          Map<String, String> headers,
                          OkGoJsonCallBack<T> okGoJsonCallBack) {
        PostRequest<T> request = OkGo.<T>post(url)
                .tag(tag);
        setPostHeaders(request,headers);
        setPostParams(request, params);
        request.execute(okGoJsonCallBack);
    }


    /**
     *  post Json
     *
     * @param tag      区分请求的tag标签
     * @param url      网络申请网址
     * @param json     json
     * @param okGoCallBack 通用回调
     */
    public <T> void postData(Object tag,
                             String url,
                             String  json,
                             OkGoCallBack<T> okGoCallBack) {
        postData(tag,url,json,null,okGoCallBack);
    }

    /**
     *  post Json
     *
     * @param tag      区分请求的tag标签
     * @param url      网络申请网址
     * @param <T>      bean类，会自动转换成json
     * @param okGoCallBack 通用回调
     */
    public <T> void postData(Object tag,
                             String url,
                             Object bean,
                             OkGoCallBack<T> okGoCallBack) {
        postData(tag,url,bean,null,okGoCallBack);
    }

    /**
     * post json
     * @param tag  区分请求的tag
     * @param url  请求url
     * @param json json字符串
     * @param headers 请求头
     * @param okGoCallBack 请求通用回调
     * @param <T>
     */
    public <T> void postData(Object tag,
                             String url,
                             String json,
                             Map<String,String> headers,
                             OkGoCallBack<T> okGoCallBack){
        PostRequest<T> request = OkGo.<T>post(url)
                .tag(tag);
        setPostHeaders(request,headers);
        request.upJson(json);
        request.execute(okGoCallBack);
    }

    /**
     * post json
     * @param tag 区分请求的tag
     * @param url 请求url
     * @param postBean jsonBean
     * @param headers 请求头
     * @param okGoCallBack 请求通用回调
     * @param <T>
     */
    public <T> void postData(Object tag,
                             String url,
                             Object postBean,
                             Map<String,String> headers,
                             OkGoCallBack<T> okGoCallBack){
        String json = JSONUtils.getGson().toJson(postBean);
        postData(tag,url,json,headers,okGoCallBack);
    }



    /**
     * post json
     * @param tag 区分请求的tag
     * @param url 请求url
     * @param json json
     * @param okGoStringCallBack  解析为string回调
     */
    public void postData(Object tag,
                         String url,
                         String json,
                         OkGoStringCallBack okGoStringCallBack){
       postData(tag,url,json,null,okGoStringCallBack);
    }


    /**
     * post json
     * @param tag 区分请求的tag
     * @param url 请求url
     * @param bean json
     * @param okGoStringCallBack  解析为string回调
     */
    public  void postData(Object tag,
                          String url,
                          Object bean,
                          OkGoStringCallBack okGoStringCallBack){
        postData(tag,url,bean,null,okGoStringCallBack);
    }

    /**
     * post json
     * @param tag 区分请求的tag
     * @param url 请求url
     * @param json json
     * @param headers 请求头
     * @param okGoStringCallBack  解析为string回调
     */
    public void postData(Object tag,
                         String url,
                         String json,
                         Map<String,String > headers,
                         OkGoStringCallBack okGoStringCallBack){
        PostRequest<String> request = OkGo.<String>post(url)
                .tag(tag);
        setPostHeaders(request,headers);
        request.upJson(json);
        request.execute(okGoStringCallBack);
    }

    /**
     * post json
     * @param tag 区分请求的tag
     * @param url 请求url
     * @param bean json
     * @param headers 请求头
     * @param okGoStringCallBack  解析为string回调
     */
    public  void postData(Object tag,
                         String url,
                         Object bean,
                         Map<String,String > headers,
                         OkGoStringCallBack okGoStringCallBack){
        String json = JSONUtils.getGson().toJson(bean);
        postData(tag,url,json,headers,okGoStringCallBack);
    }

    /**
     * post json
     * @param tag 区分请求的tag
     * @param url  请求url
     * @param json json
     * @param okGoJsonCallBack 解析json为Bean回调
     * @param <T>
     */
    public <T> void postData(Object tag,
                             String url,
                             String json,
                             OkGoJsonCallBack<T> okGoJsonCallBack){
        postData(tag,url,json,null,okGoJsonCallBack);
    }

    /**
     * post json
     * @param tag 区分请求的tag
     * @param url  请求url
     * @param bean jsonBean
     * @param okGoJsonCallBack 解析json为Bean回调
     * @param <T>
     */
    public <T> void postData(Object tag,
                             String url,
                             Object bean,
                             OkGoJsonCallBack<T> okGoJsonCallBack){
       postData(tag,url,bean,null,okGoJsonCallBack);
    }

    /**
     * post json
     * @param tag 区分请求的tag
     * @param url  请求url
     * @param json json
     * @param headers 请求头
     * @param okGoJsonCallBack 解析json为Bean回调
     * @param <T>
     */
    public <T> void postData(Object tag,
                         String url,
                         String json,
                         Map<String,String> headers,
                         OkGoJsonCallBack<T> okGoJsonCallBack){
        PostRequest<T> request = OkGo.<T>post(url)
                .tag(tag);
        setPostHeaders(request,headers);
        request.upJson(json);
        request.execute(okGoJsonCallBack);
    }

    /**
     * post json
     * @param tag 区分请求的tag
     * @param url  请求url
     * @param bean jsonBean
     * @param headers 请求头
     * @param okGoJsonCallBack 解析json为Bean回调
     * @param <T>
     */
    public <T> void postData(Object tag,
                             String url,
                             Object bean,
                             Map<String,String> headers,
                             OkGoJsonCallBack<T> okGoJsonCallBack){
        String json = JSONUtils.getGson().toJson(bean);
        postData(tag,url,json,headers,okGoJsonCallBack);
    }

    /************************************************POST end************************************************************/

    /*************************************************辅助类 start****************************************************************/

    /**
     * 设置get请求的参数数据
     *
     * @param request   PostRequest请求
     * @param params    map<String,Object>
     * @return
     */
    private GetRequest setGetParams(GetRequest request, Map<String, Object> params) {
        if (params == null || request == null){return request;}
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            if (entry.getValue().getClass() == Integer.class) {
                request.params(entry.getKey(), (int) entry.getValue());
            } else if (entry.getValue().getClass() == String.class) {
                request.params(entry.getKey(), (String) entry.getValue());
            } else if (entry.getValue().getClass() == Boolean.class) {
                request.params(entry.getKey(), (Boolean) entry.getValue());
            }else if (entry.getValue().getClass() == Character.class){
                request.params(entry.getKey(),(char)entry.getValue());
            }else if (entry.getValue().getClass() == Long.class){
                request.params(entry.getKey(),(long)entry.getValue());
            }else if (entry.getValue().getClass() == Short.class){
                request.params(entry.getKey(),(short)entry.getValue());
            }else if (entry.getValue().getClass() == Float.class){
                request.params(entry.getKey(),(float)entry.getValue());
            }else if (entry.getValue().getClass() == Double.class){
                request.params(entry.getKey(),(double)entry.getValue());
            }
        }

        return request;
    }

    /**
     * 设置get请求的请求头
     * @param request
     * @param headers
     * @return
     */
    private GetRequest setGetHeaders(GetRequest request, Map<String, String> headers) {
        if (headers == null || request == null){return request;}
        for (Map.Entry<String, String> entry : headers.entrySet()) {

            request.headers(entry.getKey(), entry.getValue());
        }

        return request;
    }


    /**
     * 上传数据，辅助类，专门为将map数据传参
     *
     * @param request PostRequest请求
     * @param params  map<String,Object>
     * @return 传入数据的请求
     */
    private <T> PostRequest<T> setPostParams(PostRequest<T> request, Map<String, Object> params) {
        if (params == null || request == null){return request;}
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            if (entry.getValue().getClass() == Integer.class) {
                request.params(entry.getKey(), (int) entry.getValue());
            } else if (entry.getValue().getClass() == String.class) {
                request.params(entry.getKey(), (String) entry.getValue());
            } else if (entry.getValue().getClass() == Boolean.class) {
                request.params(entry.getKey(), (Boolean) entry.getValue());
            }else if (entry.getValue().getClass() == Character.class){
                request.params(entry.getKey(),(char)entry.getValue());
            }else if (entry.getValue().getClass() == Long.class){
                request.params(entry.getKey(),(long)entry.getValue());
            }else if (entry.getValue().getClass() == Short.class){
                request.params(entry.getKey(),(short)entry.getValue());
            }else if (entry.getValue().getClass() == Float.class){
                request.params(entry.getKey(),(float)entry.getValue());
            }else if (entry.getValue().getClass() == Double.class){
                request.params(entry.getKey(),(double)entry.getValue());
            }
        }
        return request;
    }

    /**
     * 设置post请求的请求头
     * @param request
     * @param params
     * @param <T>
     * @return
     */
    private <T> PostRequest<T> setPostHeaders(PostRequest<T> request, Map<String, String> params) {
        if (params == null || request == null){return request;}
        for (Map.Entry<String, String> entry : params.entrySet()) {
            request.headers(entry.getKey(),entry.getValue());
        }
        return request;
    }


/*****************************************辅助类 end***********************************************************************/


    /**
     * 通过tag取消指定的请求
     *
     * @param tag
     */
    public void cancelByTag(Object tag) {
        OkGo.getInstance().cancelTag(tag);
    }

    /**
     * 取消所有请求
     */
    public void cancelAll() {
        OkGo.getInstance().cancelAll();
    }

}
