package cn.flyaudio.flyforum.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 叶兴运 on
 * 2019/1/21.14:22
 */
public class MomentBean  extends BaseBean<MomentBean> implements Serializable {


    private static final long serialVersionUID = -8257267913770677148L;
    /**
     * total : 20
     * list : [{"commentNumber":"4","avatar":"fg_1548385933492.png","video":"","list":["fg_1085446066071535618_1548412788783.jpg","web-dir-prefix/web_1548751807000.jpg"],"type":1,"uuid":"8a0e424a208d11e98ee77cd30aeb138e","content":"哈","pictures":"[\"fg_1085446066071535618_1548412788783.jpg\",\"web-dir-prefix/web_1548751807000.jpg\"]","createTime":"2019-01-25 18:39:49","name":"测试150185","userUuid":"21666e6947af49de9c2f6d78cb7c0c1f","isPraise":1,"likeNumber":"2"},{"commentNumber":"2","avatar":"fg_1548385933492.png","video":"","list":["fg__1548411624172.jpg","fg__1548411624184.jpg"],"type":1,"uuid":"41c96f1d208b11e98ee77cd30aeb138e","content":"测试一下","pictures":"[\"fg__1548411624172.jpg\",\"fg__1548411624184.jpg\"]","createTime":"2019-01-25 18:23:28","name":"测试150185","userUuid":"21666e6947af49de9c2f6d78cb7c0c1f","isPraise":1,"likeNumber":"1"},{"commentNumber":"0","avatar":"fg_1548385933492.png","video":"","list":["fg__1548411624172.jpg","fg__1548411624184.jpg"],"type":1,"uuid":"299231c2208b11e98ee77cd30aeb138e","content":"测试一下","pictures":"[\"fg__1548411624172.jpg\",\"fg__1548411624184.jpg\"]","createTime":"2019-01-25 18:22:48","name":"测试150185","userUuid":"21666e6947af49de9c2f6d78cb7c0c1f","isPraise":1,"likeNumber":"1"},{"commentNumber":"1","avatar":"fg_1548385933492.png","video":"[\"web-dir-prefix/web_1548750778000.mp4\"]","list":["web-dir-prefix/web_1548750778000.mp4"],"type":2,"uuid":"31c40c87207211e98ee77cd30aeb138e","content":"视频","pictures":"","createTime":"2019-01-25 15:24:04","name":"测试150185","userUuid":"21666e6947af49de9c2f6d78cb7c0c1f","isPraise":1,"likeNumber":"1"},{"commentNumber":"0","avatar":"fg_1548385933492.png","video":"","list":["fg_1085446066071535618_1548400769366.jpg","fg_1085446066071535618_1548400769388.jpg"],"type":1,"uuid":"a4f9f791207111e98ee77cd30aeb138e","content":"yyyty","pictures":"[\"fg_1085446066071535618_1548400769366.jpg\",\"fg_1085446066071535618_1548400769388.jpg\"]","createTime":"2019-01-25 15:20:08","name":"测试150185","userUuid":"21666e6947af49de9c2f6d78cb7c0c1f","isPraise":0,"likeNumber":"0"},{"commentNumber":"1","avatar":"fg_1548385933492.png","video":"","list":["fg_1085446066071535618_1548320627172.jpg","fg_1085446066071535618_1548320627203.jpg"],"type":1,"uuid":"f88af9491fb611e98ee77cd30aeb138e","content":"测试一下吧","pictures":"[\"fg_1085446066071535618_1548320627172.jpg\",\"fg_1085446066071535618_1548320627203.jpg\"]","createTime":"2019-01-24 17:03:52","name":"测试150185","userUuid":"21666e6947af49de9c2f6d78cb7c0c1f","isPraise":1,"likeNumber":"1"},{"commentNumber":"13","avatar":"2323","video":"","list":[],"type":0,"uuid":"6a293b031df511e98ee77cd30aeb138e","content":"在许多年前，粤军大战或许还是看点多多，两队以前在cba都是可以在球场上呼风唤雨的球队，毕竟都曾经拿过八个冠军。但是自八一队现主教练王治郅退役之后，再加上规则限制没有外援的加入，球队的成绩每一年都处于垫底的位置。今年八一队把主教练阿的江换成王治郅，再加上国青三巨头之一的郭昊文加入，球队今年的成绩比上一年还是有进步，除了4杀北控外，还赢下青岛和浙江。但目前还是以6胜27负的战绩排在联盟的最后一位。","pictures":"","createTime":"2019-01-22 11:25:49","name":"大鱼海棠","userUuid":"1cfb32de2e824ab2a44a8c8305613a9d","isPraise":0,"likeNumber":"0"},{"commentNumber":"5","avatar":"2323","video":"","list":[],"type":0,"uuid":"6883961a1df511e98ee77cd30aeb138e","content":"在许多年前，粤军大战或许还是看点多多，两队以前在cba都是可以在球场上呼风唤雨的球队，毕竟都曾经拿过八个冠军。但是自八一队现主教练王治郅退役之后，再加上规则限制没有外援的加入，球队的成绩每一年都处于垫底的位置。今年八一队把主教练阿的江换成王治郅，再加上国青三巨头之一的郭昊文加入，球队今年的成绩比上一年还是有进步，http://fg-cdn.dankal.cn/fg_1085446066071535618_1548400216709.jpghttp://fg-cdn.dankal.cn/fg_1085446066071535618_1548400216709.jpg除了4杀北控外，还赢下青岛和浙江。但目前还是以6胜27负的战绩排在联盟的最后一位。","pictures":"","createTime":"2019-01-22 11:25:46","name":"大鱼海棠","userUuid":"1cfb32de2e824ab2a44a8c8305613a9d","isPraise":0,"likeNumber":"0"},{"commentNumber":"0","avatar":"2323","video":"","list":[],"type":0,"uuid":"400415391df511e98ee77cd30aeb138e","content":"孙楠是歌坛前辈，红了很多年按道理来说他应该是很有积蓄的，为什么要住在月租700的普通小区?孙楠住在哪里呢?据网曝，孙楠妻子潘蔚接受采访时自曝为了孩子教育，从北京搬到徐州住在普通的居民小区里。孙楠房租700元果然是真的!","pictures":"","createTime":"2019-01-22 11:24:38","name":"大鱼海棠","userUuid":"1cfb32de2e824ab2a44a8c8305613a9d","isPraise":0,"likeNumber":"0"},{"commentNumber":"0","avatar":"2323","video":"","list":[],"type":0,"uuid":"3ec6bff41df511e98ee77cd30aeb138e","content":"孙楠是歌坛前辈，红了很多年按道理来说他应该是很有积蓄的，为什么要住在月租700的普通小区?孙楠住在哪里呢?据网曝，孙楠妻子潘蔚接受采访时自曝为了孩子教育，从北京搬到徐州住在普通的居民小区里。孙楠房租700元果然是真的!","pictures":"","createTime":"2019-01-22 11:24:36","name":"大鱼海棠","userUuid":"1cfb32de2e824ab2a44a8c8305613a9d","isPraise":0,"likeNumber":"0"},{"commentNumber":"2","avatar":"2323","video":"","list":[],"type":0,"uuid":"69f745531df511e98ee77cd30aeb138e","content":"在许多年前，粤军大战或许还是看点多多，两队以前在cba都是可以在球场上呼风唤雨的球队，毕竟都曾经拿过八个冠军。但是自八一队现主教练王治郅退役之后，再加上规则限制没有外援的加入，球队的成绩每一年都处于垫底的位置。今年八一队把主教练阿的江换成王治郅，再加上国青三巨头之一的郭昊文加入，球队今年的成绩比上一年还是有进步，除了4杀北控外，还赢下青岛和浙江。但目前还是以6胜27负的战绩排在联盟的最后一位。","pictures":"","createTime":"2019-01-22 11:25:49","name":"大鱼海棠","userUuid":"1cfb32de2e824ab2a44a8c8305613a9d","isPraise":0,"likeNumber":"0"},{"commentNumber":"0","avatar":"2323","video":"","list":[],"type":0,"uuid":"6935a3fd1df511e98ee77cd30aeb138e","content":"在许多年前，粤军大战或许还是看点多多，两队以前在cba都是可以在球场上呼风唤雨的球队，毕竟都曾经拿过八个冠军。但是自八一队现主教练王治郅退役之后，再加上规则限制没有外援的加入，球队的成绩每一年都处于垫底的位置。今年八一队把主教练阿的江换成王治郅，再加上国青三巨头之一的郭昊文加入，球队今年的成绩比上一年还是有进步，除了4杀北控外，还赢下青岛和浙江。但目前还是以6胜27负的战绩排在联盟的最后一位。","pictures":"","createTime":"2019-01-22 11:25:48","name":"大鱼海棠","userUuid":"1cfb32de2e824ab2a44a8c8305613a9d","isPraise":0,"likeNumber":"0"},{"commentNumber":"0","avatar":"2323","video":"","list":[],"type":0,"uuid":"69bd94f51df511e98ee77cd30aeb138e","content":"在许多年前，粤军大战或许还是看点多多，两队以前在cba都是可以在球场上呼风唤雨的球队，毕竟都曾经拿过八个冠军。但是自八一队现主教练王治郅退役之后，再加上规则限制没有外援的加入，球队的成绩每一年都处于垫底的位置。今年八一队把主教练阿的江换成王治郅，再加上国青三巨头之一的郭昊文加入，球队今年的成绩比上一年还是有进步，http://fg-cdn.dankal.cn/fg_1085446066071535618_1548400216709.jpghttp://fg-cdn.dankal.cn/fg_1085446066071535618_1548400216709.jpg除了4杀北控外，还赢下青岛和浙江。但目前还是以6胜27负的战绩排在联盟的最后一位。","pictures":"","createTime":"2019-01-22 11:25:48","name":"大鱼海棠","userUuid":"1cfb32de2e824ab2a44a8c8305613a9d","isPraise":0,"likeNumber":"0"},{"commentNumber":"0","avatar":"2323","video":"","list":[],"type":0,"uuid":"319be8e21df511e98ee77cd30aeb138e","content":"孙楠是歌坛前辈，红了很多年按道理来说他应该是很有积蓄的，为什么要住在月租700的普通小区?孙楠住在哪里呢?据网曝，孙楠妻子潘蔚接受采访时自曝为了孩子教育，从北京搬到徐州住在普通的居民小区里。孙楠房租700元果然是真的!","pictures":"","createTime":"2019-01-22 11:24:14","name":"大鱼海棠","userUuid":"1cfb32de2e824ab2a44a8c8305613a9d","isPraise":0,"likeNumber":"0"},{"commentNumber":"0","avatar":"2323","video":"","list":[],"type":0,"uuid":"3070eb961df511e98ee77cd30aeb138e","content":"孙楠是歌坛前辈，红了很多年按道理来说他应该是很有积蓄的，为什么要住在月租700的普通小区?孙楠住在哪里呢?据网曝，孙楠妻子潘蔚接受采访时自曝为了孩子教育，从北京搬到徐州住在普通的居民小区里。孙楠房租700元果然是真的!","pictures":"","createTime":"2019-01-22 11:24:12","name":"大鱼海棠","userUuid":"1cfb32de2e824ab2a44a8c8305613a9d","isPraise":0,"likeNumber":"0"},{"commentNumber":"39","avatar":"2323","video":"","list":["car1.jpg","car2.jpg"],"type":1,"uuid":"d3769dab1d4811e98ee77cd30aeb138e","content":"http://fg-cdn.dankal.cn/fg_1085446066071535618_1548400216709.jpghttp://fg-cdn.dankal.cn/fg_1085446066071535618_1548400216709.jpg港与香港持牌签约顾问碰面；<\/p><p>（四）签约顾问陪同前往投资机构验证中心，现场完成面签并缴费（需时约1小时）；<\/p><p>（五）签约顾问陪同前往银行开立香港银行账户（如需开户）；<\/p><p>（六）签约顾问提交相关合约文件；<\/p><p>（七）经过审核，合约生效，投资人收到正式合同文本；<\/p><p>（八）投资人设置网上账户，在线查看投资表现。<\/p><p><br><\/p><p><strong>13、供款和提款方便吗？<\/strong><\/p><p><br><\/p><p>供款：可以选择在香港银行开户，然后绑定银行账户自动扣款；也可以使用Visa&amp;Master信用卡进行供款。<\/p><p><br><\/p><p>提款：<\/p><p>（一）向投资顾问下指令提款，填写取款申请表格，递交到第三方理财机构或者保险公司；<\/p><p>（二）金融机构会按照指令卖出指定的基金；<\/p><p>（三）把钱汇到投资人本人银行账户或者给出支票（抬头是客户本人），整个过程需要5~14个工作日。<\/p>","pictures":"[\"car1.jpg\",\"car2.jpg\"]","createTime":"2019-01-21 14:50:23","name":"大鱼海棠","userUuid":"1cfb32de2e824ab2a44a8c8305613a9d","isPraise":1,"likeNumber":"3"},{"commentNumber":"0","avatar":"2323","video":"","list":["car1.jpg","car2.jpg"],"type":1,"uuid":"b612c1261d4811e98ee77cd30aeb138e","content":"港与香港持牌签约顾问碰面；<\/p><p>（四）签约顾问陪同前往投资机构验证中心，现场完成面签并缴费（需时约1小时）；<\/p><p>（五）签约顾问陪同前往银行开立香港银行账户（如需开户）；<\/p><p>（六）签约顾问提交相关合约文件；<\/p><p>（七）经过审核，合约生效，投资人收到正式合同文本；<\/p><p>（八）投资人设置网上账户，在线查看投资表现。<\/p><p><br><\/p><p><strong>13、供款和提款方便吗？<\/strong><\/p><p><br><\/p><p>供款：可以选择在香港银行开户，然后绑定银行账户自动扣款；也可以使用Visa&amp;Master信用卡进行供款。<\/p><p><br><\/p><p>提款：<\/p><p>（一）向投资顾问下指令提款，填写取款申请表格，递交到第三方理财机构或者保险公司；<\/p><p>（二）金融机构会按照指令卖出指定的基金；http://fg-cdn.dankal.cn/fg_1085446066071535618_1548400216709.jpghttp://fg-cdn.dankal.cn/fg_1085446066071535618_1548400216709.jpg<\/p><p>http://fg-cdn.dankal.cn/fg_1085446066071535618_1548400216709.jpghttp://fg-cdn.dankal.cn/fg_1085446066071535618_1548400216709.jpg（三）把钱汇到投资人本人银行账户或者给出支票（抬头是客户本人），整个过程需要5~14个工作日。<\/p>","pictures":"[\"car1.jpg\",\"car2.jpg\"]","createTime":"2019-01-21 14:49:34","name":"大鱼海棠","userUuid":"1cfb32de2e824ab2a44a8c8305613a9d","isPraise":0,"likeNumber":"1"},{"commentNumber":"0","avatar":"2323","video":"","list":[],"type":0,"uuid":"215f03e71d4411e98ee77cd30aeb138e","content":"港与香港持牌签约顾问碰面；<\/p><p>（四）签约顾问陪同前往投资机构验证中心，现场完成面签并缴费（需时约1小时）；<\/p><p>（五）签约顾问陪同前往银行开立香港银行账户（如需开户）；<\/p><p>（六）签约顾问提交相关合约文件；<\/p><p>（七）经过审核，合约生效，投资人收到正式合同文本；<\/p><p>（八）投资人设置网上账户，在线查看投资表现。<\/p><p><br><\/p><p><strong>13、供款和提款方便吗？<\/strong><\/p><p><br><\/p><p>供款：可以选择在香港银行开户，然后绑定银行账户自动扣款；也可以使用Visa&amp;Master信用卡进行供款。<\/p><p><br><\/p><p>提款：<\/p><p>（一）向投资顾问下指令提款，填写取款申请表格，递交到第三方理财机构或者保险公司；<\/p><p>（二）金融机构会按照指令卖出指定的基金；http://fg-cdn.dankal.cn/fg_1085446066071535618_1548400216709.jpghttp://fg-cdn.dankal.cn/fg_1085446066071535618_1548400216709.jpg<\/p><p>（三）把钱汇到投资人本人银行账户或者给出支票（抬头是客户本人），整个过程需要5~14个工作日。<\/p>","pictures":"","createTime":"2019-01-21 14:16:46","name":"大鱼海棠","userUuid":"1cfb32de2e824ab2a44a8c8305613a9d","isPraise":0,"likeNumber":"0"},{"commentNumber":"0","avatar":"2323","video":"[\"fg_1085446066071535618_1548400769366.jpg\"]","list":["fg_1085446066071535618_1548400769366.jpg"],"type":2,"uuid":"08de97481d4411e98ee77cd30aeb138e","content":"内容","pictures":"[\"fg_1085446066071535618_1548400769366.jpg\",\"fg_1085446066071535618_1548400769388.jpg\"]","createTime":"2019-01-21 14:16:05","name":"大鱼海棠","userUuid":"1cfb32de2e824ab2a44a8c8305613a9d","isPraise":0,"likeNumber":"0"},{"commentNumber":"0","avatar":"2323","video":"","list":[],"type":0,"uuid":"bbd7d4a61d4311e98ee77cd30aeb138e","content":"测试此刻！！！！！！！！测试此刻！！！！！！！！测试此刻！！！！！！！！","pictures":"","createTime":"2019-01-21 14:13:56","name":"大鱼海棠","userUuid":"1cfb32de2e824ab2a44a8c8305613a9d","isPraise":0,"likeNumber":"0"}]
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

    public static class ListBean implements Serializable {
        private static final long serialVersionUID = 1574238470442017291L;
        /**
         * commentNumber : 4
         * avatar : fg_1548385933492.png
         * video :
         * list : ["fg_1085446066071535618_1548412788783.jpg","web-dir-prefix/web_1548751807000.jpg"]
         * type : 1
         * uuid : 8a0e424a208d11e98ee77cd30aeb138e
         * content : 哈
         * pictures : ["fg_1085446066071535618_1548412788783.jpg","web-dir-prefix/web_1548751807000.jpg"]
         * createTime : 2019-01-25 18:39:49
         * name : 测试150185
         * userUuid : 21666e6947af49de9c2f6d78cb7c0c1f
         * isPraise : 1
         * likeNumber : 2
         */

        private String commentNumber;
        private String avatar;
        private String video;
        private int type;
        private String uuid;
        private String content;
        private String pictures;
        private String createTime;
        private String name;
        private String userUuid;
        private int isPraise;
        private String likeNumber;
        private List<String> list;

        public String getCommentNumber() {
            return commentNumber;
        }

        public void setCommentNumber(String commentNumber) {
            this.commentNumber = commentNumber;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getVideo() {
            return video;
        }

        public void setVideo(String video) {
            this.video = video;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
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

        public String getPictures() {
            return pictures;
        }

        public void setPictures(String pictures) {
            this.pictures = pictures;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUserUuid() {
            return userUuid;
        }

        public void setUserUuid(String userUuid) {
            this.userUuid = userUuid;
        }

        public int getIsPraise() {
            return isPraise;
        }

        public void setIsPraise(int isPraise) {
            this.isPraise = isPraise;
        }

        public String getLikeNumber() {
            return likeNumber;
        }

        public void setLikeNumber(String likeNumber) {
            this.likeNumber = likeNumber;
        }

        public List<String> getList() {
            return list;
        }

        public void setList(List<String> list) {
            this.list = list;
        }
    }
}
