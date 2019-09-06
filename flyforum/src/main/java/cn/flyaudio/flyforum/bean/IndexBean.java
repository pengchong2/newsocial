package cn.flyaudio.flyforum.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 叶兴运 on
 * 2019/1/22.9:59
 */
public class IndexBean extends BaseBean<IndexBean> implements Serializable{

    private static final long serialVersionUID = -5698700104847215059L;
    private List<VehicleActivityVOListBean> vehicleActivityVOList;
    private List<VehicleInfoVOListBean> vehicleInfoVOList;
    private List<UserInfoVOListBean> userInfoVOList;

    public List<VehicleActivityVOListBean> getVehicleActivityVOList() {
        return vehicleActivityVOList;
    }

    public void setVehicleActivityVOList(List<VehicleActivityVOListBean> vehicleActivityVOList) {
        this.vehicleActivityVOList = vehicleActivityVOList;
    }

    public List<VehicleInfoVOListBean> getVehicleInfoVOList() {
        return vehicleInfoVOList;
    }

    public void setVehicleInfoVOList(List<VehicleInfoVOListBean> vehicleInfoVOList) {
        this.vehicleInfoVOList = vehicleInfoVOList;
    }

    public List<UserInfoVOListBean> getUserInfoVOList() {
        return userInfoVOList;
    }

    public void setUserInfoVOList(List<UserInfoVOListBean> userInfoVOList) {
        this.userInfoVOList = userInfoVOList;
    }

    public static class VehicleActivityVOListBean implements Serializable {
        private static final long serialVersionUID = 4134183754119410854L;
        /**
         * uuid : 4289505771374150b7206a3ea651ed16
         * title : 超高性能！！！
         * content : 车模！！！！
         * cover : car1.jpg
         * qrcode : 1cfb32de2e824ab2a44a8c8305613a9d
         * price : 0
         * allPeopleNumber : 1000
         * applyPeopleNumber : 0
         * lookNumber : 26
         * activitySite : 深圳
         * applyStartTime : 2019-01-02
         * applyEndTime : 2019-01-08
         * activityStartTime : 2019-01-08
         * activityEndTime : 2019-01-20
         * isApplyRestrict : null
         * isBrowseRestrict : null
         * isRelease : null
         * oneTag : 08929c84193e11e98ee77cd30aeb138e
         * twoTag : 超高性能
         * threeTag : 车模
         * activityStatus : 2
         * applyStatus : 2
         * vehicleActivityBrandList : null
         */

        private String uuid;
        private String title;
        private String content;
        private String cover;
        private String qrcode;
        private int price;
        private int allPeopleNumber;
        private int applyPeopleNumber;
        private int lookNumber;
        private String activitySite;
        private String applyStartTime;
        private String applyEndTime;
        private String activityStartTime;
        private String activityEndTime;
        private Object isApplyRestrict;
        private Object isBrowseRestrict;
        private Object isRelease;
        private String oneTag;
        private String twoTag;
        private String threeTag;
        private int activityStatus;
        private int applyStatus;
        private Object vehicleActivityBrandList;

        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getQrcode() {
            return qrcode;
        }

        public void setQrcode(String qrcode) {
            this.qrcode = qrcode;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public int getAllPeopleNumber() {
            return allPeopleNumber;
        }

        public void setAllPeopleNumber(int allPeopleNumber) {
            this.allPeopleNumber = allPeopleNumber;
        }

        public int getApplyPeopleNumber() {
            return applyPeopleNumber;
        }

        public void setApplyPeopleNumber(int applyPeopleNumber) {
            this.applyPeopleNumber = applyPeopleNumber;
        }

        public int getLookNumber() {
            return lookNumber;
        }

        public void setLookNumber(int lookNumber) {
            this.lookNumber = lookNumber;
        }

        public String getActivitySite() {
            return activitySite;
        }

        public void setActivitySite(String activitySite) {
            this.activitySite = activitySite;
        }

        public String getApplyStartTime() {
            return applyStartTime;
        }

        public void setApplyStartTime(String applyStartTime) {
            this.applyStartTime = applyStartTime;
        }

        public String getApplyEndTime() {
            return applyEndTime;
        }

        public void setApplyEndTime(String applyEndTime) {
            this.applyEndTime = applyEndTime;
        }

        public String getActivityStartTime() {
            return activityStartTime;
        }

        public void setActivityStartTime(String activityStartTime) {
            this.activityStartTime = activityStartTime;
        }

        public String getActivityEndTime() {
            return activityEndTime;
        }

        public void setActivityEndTime(String activityEndTime) {
            this.activityEndTime = activityEndTime;
        }

        public Object getIsApplyRestrict() {
            return isApplyRestrict;
        }

        public void setIsApplyRestrict(Object isApplyRestrict) {
            this.isApplyRestrict = isApplyRestrict;
        }

        public Object getIsBrowseRestrict() {
            return isBrowseRestrict;
        }

        public void setIsBrowseRestrict(Object isBrowseRestrict) {
            this.isBrowseRestrict = isBrowseRestrict;
        }

        public Object getIsRelease() {
            return isRelease;
        }

        public void setIsRelease(Object isRelease) {
            this.isRelease = isRelease;
        }

        public String getOneTag() {
            return oneTag;
        }

        public void setOneTag(String oneTag) {
            this.oneTag = oneTag;
        }

        public String getTwoTag() {
            return twoTag;
        }

        public void setTwoTag(String twoTag) {
            this.twoTag = twoTag;
        }

        public String getThreeTag() {
            return threeTag;
        }

        public void setThreeTag(String threeTag) {
            this.threeTag = threeTag;
        }

        public int getActivityStatus() {
            return activityStatus;
        }

        public void setActivityStatus(int activityStatus) {
            this.activityStatus = activityStatus;
        }

        public int getApplyStatus() {
            return applyStatus;
        }

        public void setApplyStatus(int applyStatus) {
            this.applyStatus = applyStatus;
        }

        public Object getVehicleActivityBrandList() {
            return vehicleActivityBrandList;
        }

        public void setVehicleActivityBrandList(Object vehicleActivityBrandList) {
            this.vehicleActivityBrandList = vehicleActivityBrandList;
        }
    }

    public static class VehicleInfoVOListBean {
        /**
         * uuid : 04656cc4c4ba48c9becab5532f210123
         * title : 资讯测试1
         * content : 本人认为，2019年CBA常规赛MVP是郭艾伦的概率为51%，王哲林的概率为49%。
         * cover : car1.jpg
         * lookNumber : 15
         * commentNumber : 6
         * createTime : 2019-01-17 16:13:34.0
         * releaseUserUuid : 6e010a05334f49358ebceb8eabd889fc
         */

        private String uuid;
        private String title;
        private String content;
        private String cover;
        private String lookNumber;
        private String commentNumber;
        private String createTime;
        private String releaseUserUuid;

        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getLookNumber() {
            return lookNumber;
        }

        public void setLookNumber(String lookNumber) {
            this.lookNumber = lookNumber;
        }

        public String getCommentNumber() {
            return commentNumber;
        }

        public void setCommentNumber(String commentNumber) {
            this.commentNumber = commentNumber;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getReleaseUserUuid() {
            return releaseUserUuid;
        }

        public void setReleaseUserUuid(String releaseUserUuid) {
            this.releaseUserUuid = releaseUserUuid;
        }
    }

    public static class UserInfoVOListBean {
        /**
         * uuid : 69f745531df511e98ee77cd30aeb138e
         * content : 在许多年前，粤军大战或许还是看点多多，两队以前在cba都是可以在球场上呼风唤雨的球队，毕竟都曾经拿过八个冠军。但是自八一队现主教练王治郅退役之后，再加上规则限制没有外援的加入，球队的成绩每一年都处于垫底的位置。今年八一队把主教练阿的江换成王治郅，再加上国青三巨头之一的郭昊文加入，球队今年的成绩比上一年还是有进步，除了4杀北控外，还赢下青岛和浙江。但目前还是以6胜27负的战绩排在联盟的最后一位。
         * likeNumber : 0
         * pictures : null
         * video : null
         * list : null
         * commentNumber : 0
         * createTime : 2019-01-22 11:25:49.0
         * userUuid : 1cfb32de2e824ab2a44a8c8305613a9d
         * name : 测试147168
         * avatar : null
         * type : 0
         */

        private String uuid;
        private String content;
        private String likeNumber;
        private String pictures;
        private Object video;
        private List<String> list;
        private String commentNumber;
        private String createTime;
        private String userUuid;
        private String name;
        private String avatar;
        private int type;

        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getLikeNumber() {
            return likeNumber;
        }

        public void setLikeNumber(String likeNumber) {
            this.likeNumber = likeNumber;
        }

        public Object getPictures() {
            return pictures;
        }

        public void setPictures(String pictures) {
            this.pictures = pictures;
        }

        public Object getVideo() {
            return video;
        }

        public void setVideo(Object video) {
            this.video = video;
        }

        public List<String> getList() {
            return list;
        }

        public void setList(List<String> list) {
            this.list = list;
        }

        public String getCommentNumber() {
            return commentNumber;
        }

        public void setCommentNumber(String commentNumber) {
            this.commentNumber = commentNumber;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getUserUuid() {
            return userUuid;
        }

        public void setUserUuid(String userUuid) {
            this.userUuid = userUuid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }
}
