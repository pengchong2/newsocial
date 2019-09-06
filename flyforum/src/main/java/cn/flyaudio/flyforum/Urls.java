package cn.flyaudio.flyforum;

/**
 * Created by 叶兴运 on
 * 2019/1/17.16:05
 */
public class Urls {
   // public static final String base = "https://api-fg-dev.dankal.cn/v1";
   public static final String base = "https://car.test.isuanyun.com/v1";
  //  public static final String baseImg = "http://fg-cdn.dankal.cn/";
  public static final String baseImg = "https://flyaudio-union-develop.oss-cn-shenzhen.aliyuncs.com/";
    /**
     * 获取活动列表
     * ?pageIndex=1&pageSize=10
     */
    public static final String getActivityList = base + "/app/vehicle/activity/get/vehicle/activity_list";
    /**
     * 获取咨讯列表 get
     */
    public static final String getInfoList = base + "/app/vehicle/info/get/vehicle/info_list";

    /**
     * 更新活动查看数 put
     */
    public static final String updateLookNumber = base + "/app/vehicle/activity/update_look_number";
    /**
     * 预约活动
     */
    public static final String isApplyActivity = base + "/app/vehicle/activity/is_apply_activity";
    /**
     * 提交咨讯详情
     */
    public static final String getInfoDetails = base + "/app/vehicle/info/get/vehicle/info_details";
    /**
     * get 车辆资讯的评论列表
     */
    public static final String getCommentList = base + "/app/vehicle/info/get/comment_list";
    /**
     * get 此刻的评论列表
     */
    public static final String getMomentCommentList = base + "/app/user/info/get/comment_list";
    /**
     * 提交评论 put
     */
    public static final String putInfoComment = base + "/app/vehicle/info/comment";
    /**
     * 提交内部评论，put
     */
    public static final String putInsideComment = base + "/app/vehicle/info/inside_comment";
    /**
     * 此刻列表
     */
    public static final String getMomentList = base + "/app/user/info/get/user/info_list";
    /**
     * 此刻评论
     */
    public static final String putMomentComment = base + "/app/user/info/comment";
    /**
     * 此刻内部评论
     */
    public static final String putMomentInsideComment = base + "/app/user/info/comment";
    /**
     * 此刻点赞/此刻评论点赞
     */
    public static final String putMomentZan = base + "/app/user/info/praise_user_info";
 /**
     * 此刻详情
     */
    public static final String getMomentdetail= base + "/app/user/info/get/user/info_details/";
  /**
     * 此刻详情
     */
    public static final String getAcsdetail= base + "/app/vehicle/activity/get/vehicle/activity_details/";
    /**
     * 首页列表
     */
    public static final String getIndexList = base + "/app/home/get/info_list";

    /**
     * 品牌列表
     */
    public static final String getBrandList = base + "/app/user/info/get/brand_list";
}
