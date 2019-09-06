package cn.flyaudio.flyforum.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 叶兴运 on
 * 2019/1/17.16:11
 */
public class ActivityListBean  implements Serializable{


    private static final long serialVersionUID = -6263741430173849508L;

    /**
     * total : 13
     * list : [{"applyEndTime":"2019-01-28","applyPeopleNumber":99,"restrictCarryNumber":4,"applyRequired":"","qrcode":"","activityStartTime":"2019-01-28","isApplyRestrict":0,"lookNumber":132,"threeTag":"","title":"测试活动标题","uuid":"1wety3qq788whgfvv45e","vehicleActivityBrandList":[],"content":"测试活动内容","applyStartTime":"2019-01-08","isRelease":"","cover":"car2.jpg","restrictType":0,"price":0,"activityStatus":2,"twoTag":"超高性能1","activitySite":"深圳","allPeopleNumber":100,"activityEndTime":"2019-01-30","oneTag":"车模","isBrowseRestrict":0,"applyStatus":2},{"applyEndTime":"2019-02-19","applyPeopleNumber":0,"restrictCarryNumber":101,"applyRequired":"[\"phone\",\"age\"]","qrcode":"qrCode_3254a52198fe49439fca43d2d894d73c.jpg","activityStartTime":"2019-01-30","isApplyRestrict":0,"lookNumber":10,"threeTag":"","title":"","uuid":"3254a52198fe49439fca43d2d894d73c","vehicleActivityBrandList":[],"content":"<p>xxx<\/p>","applyStartTime":"2019-01-10","isRelease":"","cover":"web-dir-prefix/web_1548475220000.jpg","restrictType":0,"price":0,"activityStatus":1,"twoTag":"xxx","activitySite":"103","allPeopleNumber":102,"activityEndTime":"2019-02-19","oneTag":"超高性能","isBrowseRestrict":0,"applyStatus":1},{"applyEndTime":"2019-01-28","applyPeopleNumber":2,"restrictCarryNumber":4,"applyRequired":"","qrcode":"1cfb32de2e824ab2a44a8c8305613a9d","activityStartTime":"2019-01-28","isApplyRestrict":0,"lookNumber":72,"threeTag":"车模","title":"超高性能！！！","uuid":"4289505771374150b7206a3ea651ed16","vehicleActivityBrandList":[],"content":"车模！！！！","applyStartTime":"2019-01-02","isRelease":"","cover":"car1.jpg","restrictType":0,"price":0,"activityStatus":2,"twoTag":"超高性能1","activitySite":"深圳","allPeopleNumber":1000,"activityEndTime":"2019-01-30","oneTag":"08929c84193e11e98ee77cd30aeb138e","isBrowseRestrict":0,"applyStatus":2},{"applyEndTime":"2019-02-24","applyPeopleNumber":0,"restrictCarryNumber":12,"applyRequired":"[\"sex\",\"phone\",\"name\"]","qrcode":"qrCode_c3cc65caf34349f98cddb3cd640bae66.jpg","activityStartTime":"2019-01-10","isApplyRestrict":0,"lookNumber":3,"threeTag":"","title":"","uuid":"c3cc65caf34349f98cddb3cd640bae66","vehicleActivityBrandList":[],"content":"<p>xxx<\/p >","applyStartTime":"2019-01-16","isRelease":"","cover":"web-dir-prefix/web_1548475220000.jpg","restrictType":0,"price":0,"activityStatus":1,"twoTag":"xxx","activitySite":"111","allPeopleNumber":13411,"activityEndTime":"2019-02-17","oneTag":"超高性能","isBrowseRestrict":0,"applyStatus":1},{"applyEndTime":"2019-02-24","applyPeopleNumber":0,"restrictCarryNumber":12,"applyRequired":"[\"sex\",\"phone\",\"name\"]","qrcode":"code_cf6eb5e6290a42d2b963cd30b18bd9de.jpg","activityStartTime":"2019-01-10","isApplyRestrict":0,"lookNumber":7,"threeTag":"","title":"","uuid":"cf6eb5e6290a42d2b963cd30b18bd9de","vehicleActivityBrandList":[],"content":"<p>xxx<\/p >","applyStartTime":"2019-01-16","isRelease":"","cover":"web-dir-prefix/web_1548475220000.jpg","restrictType":0,"price":0,"activityStatus":1,"twoTag":"xxx","activitySite":"111","allPeopleNumber":13411,"activityEndTime":"2019-02-17","oneTag":"超高性能","isBrowseRestrict":0,"applyStatus":1},{"applyEndTime":"2019-02-14","applyPeopleNumber":0,"restrictCarryNumber":1,"applyRequired":"[\"name\",\"phone\",\"age\"]","qrcode":"qrCode_d2297af3786b4dd9a430c34ed6bc2628.jpg","activityStartTime":"2019-02-22","isApplyRestrict":0,"lookNumber":6,"threeTag":"A级","title":"xxxxxx","uuid":"d2297af3786b4dd9a430c34ed6bc2628","vehicleActivityBrandList":[],"content":"<p>xxxxxxxxxxxxxxx<\/p>","applyStartTime":"2019-02-12","isRelease":"","cover":"web-dir-prefix/web_1549959412000.jpg","restrictType":0,"price":0,"activityStatus":0,"twoTag":"车模","activitySite":"ccc","allPeopleNumber":2,"activityEndTime":"2019-02-23","oneTag":"超高性能1","isBrowseRestrict":0,"applyStatus":1},{"applyEndTime":"2019-02-24","applyPeopleNumber":0,"restrictCarryNumber":12,"applyRequired":"[\"sex\",\"phone\",\"name\"]","qrcode":"qrCode_c3cc65caf34349f98cddb3cd640bae66.jpg","activityStartTime":"2019-01-10","isApplyRestrict":0,"lookNumber":3,"threeTag":"","title":"","uuid":"c3cc65caf34349f98cddb3cd640bae66","vehicleActivityBrandList":[],"content":"<p>xxx<\/p >","applyStartTime":"2019-01-16","isRelease":"","cover":"web-dir-prefix/web_1548475220000.jpg","restrictType":0,"price":0,"activityStatus":1,"twoTag":"xxx","activitySite":"111","allPeopleNumber":13411,"activityEndTime":"2019-02-17","oneTag":"超高性能","isBrowseRestrict":0,"applyStatus":1},{"applyEndTime":"2019-02-24","applyPeopleNumber":0,"restrictCarryNumber":12,"applyRequired":"[\"sex\",\"phone\",\"name\"]","qrcode":"qrCode_c3cc65caf34349f98cddb3cd640bae66.jpg","activityStartTime":"2019-01-10","isApplyRestrict":0,"lookNumber":3,"threeTag":"","title":"","uuid":"c3cc65caf34349f98cddb3cd640bae66","vehicleActivityBrandList":[],"content":"<p>xxx<\/p >","applyStartTime":"2019-01-16","isRelease":"","cover":"web-dir-prefix/web_1548475220000.jpg","restrictType":0,"price":0,"activityStatus":1,"twoTag":"xxx","activitySite":"111","allPeopleNumber":13411,"activityEndTime":"2019-02-17","oneTag":"超高性能","isBrowseRestrict":0,"applyStatus":1},{"applyEndTime":"2019-02-19","applyPeopleNumber":0,"restrictCarryNumber":101,"applyRequired":"[\"phone\",\"age\"]","qrcode":"qrCode_3254a52198fe49439fca43d2d894d73c.jpg","activityStartTime":"2019-01-30","isApplyRestrict":0,"lookNumber":10,"threeTag":"","title":"","uuid":"3254a52198fe49439fca43d2d894d73c","vehicleActivityBrandList":[],"content":"<p>xxx<\/p>","applyStartTime":"2019-01-10","isRelease":"","cover":"web-dir-prefix/web_1548475220000.jpg","restrictType":0,"price":0,"activityStatus":1,"twoTag":"xxx","activitySite":"103","allPeopleNumber":102,"activityEndTime":"2019-02-19","oneTag":"超高性能","isBrowseRestrict":0,"applyStatus":1},{"applyEndTime":"2019-02-14","applyPeopleNumber":0,"restrictCarryNumber":1,"applyRequired":"[\"name\",\"phone\",\"age\"]","qrcode":"qrCode_d2297af3786b4dd9a430c34ed6bc2628.jpg","activityStartTime":"2019-02-22","isApplyRestrict":0,"lookNumber":6,"threeTag":"A级","title":"xxxxxx","uuid":"d2297af3786b4dd9a430c34ed6bc2628","vehicleActivityBrandList":[],"content":"<p>xxxxxxxxxxxxxxx<\/p>","applyStartTime":"2019-02-12","isRelease":"","cover":"web-dir-prefix/web_1549959412000.jpg","restrictType":0,"price":0,"activityStatus":0,"twoTag":"车模","activitySite":"ccc","allPeopleNumber":2,"activityEndTime":"2019-02-23","oneTag":"超高性能1","isBrowseRestrict":0,"applyStatus":1},{"applyEndTime":"2019-02-14","applyPeopleNumber":0,"restrictCarryNumber":1,"applyRequired":"[\"name\",\"phone\",\"age\"]","qrcode":"qrCode_d2297af3786b4dd9a430c34ed6bc2628.jpg","activityStartTime":"2019-02-22","isApplyRestrict":0,"lookNumber":6,"threeTag":"A级","title":"xxxxxx","uuid":"d2297af3786b4dd9a430c34ed6bc2628","vehicleActivityBrandList":[],"content":"<p>xxxxxxxxxxxxxxx<\/p>","applyStartTime":"2019-02-12","isRelease":"","cover":"web-dir-prefix/web_1549959412000.jpg","restrictType":0,"price":0,"activityStatus":0,"twoTag":"车模","activitySite":"ccc","allPeopleNumber":2,"activityEndTime":"2019-02-23","oneTag":"超高性能1","isBrowseRestrict":0,"applyStatus":1},{"applyEndTime":"2019-02-24","applyPeopleNumber":0,"restrictCarryNumber":12,"applyRequired":"[\"sex\",\"phone\",\"name\"]","qrcode":"code_cf6eb5e6290a42d2b963cd30b18bd9de.jpg","activityStartTime":"2019-01-10","isApplyRestrict":0,"lookNumber":7,"threeTag":"","title":"","uuid":"cf6eb5e6290a42d2b963cd30b18bd9de","vehicleActivityBrandList":[],"content":"<p>xxx<\/p >","applyStartTime":"2019-01-16","isRelease":"","cover":"web-dir-prefix/web_1548475220000.jpg","restrictType":0,"price":0,"activityStatus":1,"twoTag":"xxx","activitySite":"111","allPeopleNumber":13411,"activityEndTime":"2019-02-17","oneTag":"超高性能","isBrowseRestrict":0,"applyStatus":1},{"applyEndTime":"2019-02-24","applyPeopleNumber":0,"restrictCarryNumber":12,"applyRequired":"[\"sex\",\"phone\",\"name\"]","qrcode":"code_cf6eb5e6290a42d2b963cd30b18bd9de.jpg","activityStartTime":"2019-01-10","isApplyRestrict":0,"lookNumber":7,"threeTag":"","title":"","uuid":"cf6eb5e6290a42d2b963cd30b18bd9de","vehicleActivityBrandList":[],"content":"<p>xxx<\/p >","applyStartTime":"2019-01-16","isRelease":"","cover":"web-dir-prefix/web_1548475220000.jpg","restrictType":0,"price":0,"activityStatus":1,"twoTag":"xxx","activitySite":"111","allPeopleNumber":13411,"activityEndTime":"2019-02-17","oneTag":"超高性能","isBrowseRestrict":0,"applyStatus":1}]
     */

    private int total;
    private List<ListBean> list;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * applyEndTime : 2019-01-28
         * applyPeopleNumber : 99
         * restrictCarryNumber : 4
         * applyRequired :
         * qrcode :
         * activityStartTime : 2019-01-28
         * isApplyRestrict : 0
         * lookNumber : 132
         * threeTag :
         * title : 测试活动标题
         * uuid : 1wety3qq788whgfvv45e
         * vehicleActivityBrandList : []
         * content : 测试活动内容
         * applyStartTime : 2019-01-08
         * isRelease :
         * cover : car2.jpg
         * restrictType : 0
         * price : 0
         * activityStatus : 2
         * twoTag : 超高性能1
         * activitySite : 深圳
         * allPeopleNumber : 100
         * activityEndTime : 2019-01-30
         * oneTag : 车模
         * isBrowseRestrict : 0
         * applyStatus : 2
         */

        private String applyEndTime;
        private int applyPeopleNumber;
        private int restrictCarryNumber;
        private String applyRequired;
        private String qrcode;
        private String activityStartTime;
        private int isApplyRestrict;
        private int lookNumber;
        private String threeTag;
        private String title;
        private String uuid;
        private String content;
        private String applyStartTime;
        private String isRelease;
        private String cover;
        private int restrictType;
        private int price;
        private int activityStatus;
        private String twoTag;
        private String activitySite;
        private int allPeopleNumber;
        private String activityEndTime;
        private String oneTag;
        private int isBrowseRestrict;
        private int applyStatus;
        private List<?> vehicleActivityBrandList;

        public String getApplyEndTime() {
            return applyEndTime;
        }

        public void setApplyEndTime(String applyEndTime) {
            this.applyEndTime = applyEndTime;
        }

        public int getApplyPeopleNumber() {
            return applyPeopleNumber;
        }

        public void setApplyPeopleNumber(int applyPeopleNumber) {
            this.applyPeopleNumber = applyPeopleNumber;
        }

        public int getRestrictCarryNumber() {
            return restrictCarryNumber;
        }

        public void setRestrictCarryNumber(int restrictCarryNumber) {
            this.restrictCarryNumber = restrictCarryNumber;
        }

        public String getApplyRequired() {
            return applyRequired;
        }

        public void setApplyRequired(String applyRequired) {
            this.applyRequired = applyRequired;
        }

        public String getQrcode() {
            return qrcode;
        }

        public void setQrcode(String qrcode) {
            this.qrcode = qrcode;
        }

        public String getActivityStartTime() {
            return activityStartTime;
        }

        public void setActivityStartTime(String activityStartTime) {
            this.activityStartTime = activityStartTime;
        }

        public int getIsApplyRestrict() {
            return isApplyRestrict;
        }

        public void setIsApplyRestrict(int isApplyRestrict) {
            this.isApplyRestrict = isApplyRestrict;
        }

        public int getLookNumber() {
            return lookNumber;
        }

        public void setLookNumber(int lookNumber) {
            this.lookNumber = lookNumber;
        }

        public String getThreeTag() {
            return threeTag;
        }

        public void setThreeTag(String threeTag) {
            this.threeTag = threeTag;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

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

        public String getApplyStartTime() {
            return applyStartTime;
        }

        public void setApplyStartTime(String applyStartTime) {
            this.applyStartTime = applyStartTime;
        }

        public String getIsRelease() {
            return isRelease;
        }

        public void setIsRelease(String isRelease) {
            this.isRelease = isRelease;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public int getRestrictType() {
            return restrictType;
        }

        public void setRestrictType(int restrictType) {
            this.restrictType = restrictType;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public int getActivityStatus() {
            return activityStatus;
        }

        public void setActivityStatus(int activityStatus) {
            this.activityStatus = activityStatus;
        }

        public String getTwoTag() {
            return twoTag;
        }

        public void setTwoTag(String twoTag) {
            this.twoTag = twoTag;
        }

        public String getActivitySite() {
            return activitySite;
        }

        public void setActivitySite(String activitySite) {
            this.activitySite = activitySite;
        }

        public int getAllPeopleNumber() {
            return allPeopleNumber;
        }

        public void setAllPeopleNumber(int allPeopleNumber) {
            this.allPeopleNumber = allPeopleNumber;
        }

        public String getActivityEndTime() {
            return activityEndTime;
        }

        public void setActivityEndTime(String activityEndTime) {
            this.activityEndTime = activityEndTime;
        }

        public String getOneTag() {
            return oneTag;
        }

        public void setOneTag(String oneTag) {
            this.oneTag = oneTag;
        }

        public int getIsBrowseRestrict() {
            return isBrowseRestrict;
        }

        public void setIsBrowseRestrict(int isBrowseRestrict) {
            this.isBrowseRestrict = isBrowseRestrict;
        }

        public int getApplyStatus() {
            return applyStatus;
        }

        public void setApplyStatus(int applyStatus) {
            this.applyStatus = applyStatus;
        }

        public List<?> getVehicleActivityBrandList() {
            return vehicleActivityBrandList;
        }

        public void setVehicleActivityBrandList(List<?> vehicleActivityBrandList) {
            this.vehicleActivityBrandList = vehicleActivityBrandList;
        }
    }
}
