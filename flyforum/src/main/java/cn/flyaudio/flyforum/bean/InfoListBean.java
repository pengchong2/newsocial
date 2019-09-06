package cn.flyaudio.flyforum.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 叶兴运 on
 * 2019/1/17.20:57
 */
public class InfoListBean  implements Serializable {

    private static final long serialVersionUID = -7317490452552497512L;
    /**
     * total : 10
     * list : [{"cover":"car1.jpg","appCommentVOList":[],"commentNumber":"0","createTime":"2019-01-17 16:13:34.0","lookNumber":"0","releaseUserUuid":"6e010a05334f49358ebceb8eabd889fc","title":"资讯测试1","uuid":"04656cc4c4ba48c9becab5532f210123","content":"04656cc4c4ba48c9becab5532f210cdd"},{"cover":"car.jpg","appCommentVOList":[],"commentNumber":"0","createTime":"2019-01-17 16:17:38.0","lookNumber":"1","releaseUserUuid":"6e010a05334f49358ebceb8eabd889fc","title":"资讯测试222","uuid":"04656cc4c4ba48c9becab5532f210321","content":"资讯测试1、2222"},{"cover":"car2.jpg","appCommentVOList":[],"commentNumber":"0","createTime":"","lookNumber":"0","releaseUserUuid":"6e010a05334f49358ebceb8eabd889fc","title":"232323资讯111","uuid":"44c39dd61a3311e98ee77cd30aeb138e","content":"资讯内容"},{"cover":"car.jpg","appCommentVOList":[],"commentNumber":"0","createTime":"","lookNumber":"0","releaseUserUuid":"6e010a05334f49358ebceb8eabd889fc","title":"资讯111222","uuid":"44c892cb1a3311e98ee77cd30aeb138e","content":"资讯内容"},{"cover":"car.jpg","appCommentVOList":[],"commentNumber":"0","createTime":"","lookNumber":"0","releaseUserUuid":"6e010a05334f49358ebceb8eabd889fc","title":"33434资讯111444","uuid":"44d8bd8b1a3311e98ee77cd30aeb138e","content":"资讯内容"},{"cover":"car2.jpg","appCommentVOList":[],"commentNumber":"0","createTime":"","lookNumber":"0","releaseUserUuid":"6e010a05334f49358ebceb8eabd889fc","title":"资讯333111","uuid":"44ed28f91a3311e98ee77cd30aeb138e","content":"资讯内容"},{"cover":"juju.jpg","appCommentVOList":[],"commentNumber":"0","createTime":"","lookNumber":"0","releaseUserUuid":"6e010a05334f49358ebceb8eabd889fc","title":"资讯555111","uuid":"5522ed301a3311e98ee77cd30aeb138e","content":"资讯内容"},{"cover":"juju.jpg","appCommentVOList":[],"commentNumber":"0","createTime":"","lookNumber":"0","releaseUserUuid":"6e010a05334f49358ebceb8eabd889fc","title":"资讯666111","uuid":"55601b341a3311e98ee77cd30aeb138e","content":"资讯内容"},{"cover":"car2.jpg","appCommentVOList":[],"commentNumber":"0","createTime":"","lookNumber":"0","releaseUserUuid":"6e010a05334f49358ebceb8eabd889fc","title":"777资讯111","uuid":"55945e051a3311e98ee77cd30aeb138e","content":"资讯内容"},{"cover":"car2.jpg","appCommentVOList":[],"commentNumber":"0","createTime":"","lookNumber":"0","releaseUserUuid":"6e010a05334f49358ebceb8eabd889fc","title":"888资讯111","uuid":"559bc6de1a3311e98ee77cd30aeb138e","content":"资讯内容"}]
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

        private String cover;
        private String commentNumber;
        private String createTime;
        private String lookNumber;
        private String releaseUserUuid;
        private String title;
        private String uuid;
        private String content;
        private List<?> appCommentVOList;

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
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

        public String getLookNumber() {
            return lookNumber;
        }

        public void setLookNumber(String lookNumber) {
            this.lookNumber = lookNumber;
        }

        public String getReleaseUserUuid() {
            return releaseUserUuid;
        }

        public void setReleaseUserUuid(String releaseUserUuid) {
            this.releaseUserUuid = releaseUserUuid;
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

        public List<?> getAppCommentVOList() {
            return appCommentVOList;
        }

        public void setAppCommentVOList(List<?> appCommentVOList) {
            this.appCommentVOList = appCommentVOList;
        }
    }
}
