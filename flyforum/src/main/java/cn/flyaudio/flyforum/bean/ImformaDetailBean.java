package cn.flyaudio.flyforum.bean;

import java.util.List;

/**
 * Created by 叶兴运 on
 * 2019/1/19.11:41
 */
public class ImformaDetailBean extends BaseBean<ImformaDetailBean> {

    /**
     * uuid : 04656cc4c4ba48c9becab5532f210123
     * title : 资讯测试1
     * content : 04656cc4c4ba48c9becab5532f210cdd
     * cover : null
     * lookNumber : 10
     * commentNumber : 6
     * createTime : 2019-01-17 16:13:34.0
     * releaseUserUuid : 6e010a05334f49358ebceb8eabd889fc
     * appCommentVOList : [{"avatar":null,"content":"测试评论2232323233","createTime":"2019-01-18 14:17:44.0","userUuid":"21666e6947af49de9c2f6d78cb7c0c1f","uuid":"c4e8e5881ae811e98ee77cd30aeb138e","name":null,"list":[{"content":"内部评论！！","createTime":"2019-01-18 14:20:05.0","receiveName":"测试150","sendName":"测试147","receiveUuid":"879545bdff264a07b8b870de6edf7ca9","sendUuid":"1cfb32de2e824ab2a44a8c8305613a9d"},{"content":"内部评论333333！！","createTime":"2019-01-18 14:20:17.0","receiveName":"测试150","sendName":"测试147","receiveUuid":"879545bdff264a07b8b870de6edf7ca9","sendUuid":"1cfb32de2e824ab2a44a8c8305613a9d"}]},{"avatar":null,"content":"测试评论2233","createTime":"2019-01-18 14:17:32.0","userUuid":"21666e6947af49de9c2f6d78cb7c0c1f","uuid":"bd2f31371ae811e98ee77cd30aeb138e","name":null,"list":[]},{"avatar":null,"content":"测试评论2233","createTime":"2019-01-18 14:16:26.0","userUuid":"1cfb32de2e824ab2a44a8c8305613a9d","uuid":"9668ecb31ae811e98ee77cd30aeb138e","name":"测试147","list":[]},{"avatar":null,"content":"测试资讯评论","createTime":"2019-01-18 12:06:20.0","userUuid":"1cfb32de2e824ab2a44a8c8305613a9d","uuid":"699562cc1ad611e98ee77cd30aeb138e","name":"测试147","list":[]}]
     */

    private String uuid;
    private String title;
    private String content;
    private String cover;
    private String lookNumber;
    private String commentNumber;
    private String createTime;
    private String releaseUserUuid;
    private List<AppCommentVOListBean> appCommentVOList;

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

    public Object getCover() {
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

    public List<AppCommentVOListBean> getAppCommentVOList() {
        return appCommentVOList;
    }

    public void setAppCommentVOList(List<AppCommentVOListBean> appCommentVOList) {
        this.appCommentVOList = appCommentVOList;
    }

    public static class AppCommentVOListBean {
        /**
         * avatar : null
         * content : 测试评论2232323233
         * createTime : 2019-01-18 14:17:44.0
         * userUuid : 21666e6947af49de9c2f6d78cb7c0c1f
         * uuid : c4e8e5881ae811e98ee77cd30aeb138e
         * name : null
         * list : [{"content":"内部评论！！","createTime":"2019-01-18 14:20:05.0","receiveName":"测试150","sendName":"测试147","receiveUuid":"879545bdff264a07b8b870de6edf7ca9","sendUuid":"1cfb32de2e824ab2a44a8c8305613a9d"},{"content":"内部评论333333！！","createTime":"2019-01-18 14:20:17.0","receiveName":"测试150","sendName":"测试147","receiveUuid":"879545bdff264a07b8b870de6edf7ca9","sendUuid":"1cfb32de2e824ab2a44a8c8305613a9d"}]
         */

        private String avatar;
        private String content;
        private String createTime;
        private String userUuid;
        private String uuid;
        private String name;
        private List<ListBean> list;

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
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

        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }

        public Object getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * content : 内部评论！！
             * createTime : 2019-01-18 14:20:05.0
             * receiveName : 测试150
             * sendName : 测试147
             * receiveUuid : 879545bdff264a07b8b870de6edf7ca9
             * sendUuid : 1cfb32de2e824ab2a44a8c8305613a9d
             */

            private String content;
            private String createTime;
            private String receiveName;
            private String sendName;
            private String receiveUuid;
            private String sendUuid;

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getReceiveName() {
                return receiveName;
            }

            public void setReceiveName(String receiveName) {
                this.receiveName = receiveName;
            }

            public String getSendName() {
                return sendName;
            }

            public void setSendName(String sendName) {
                this.sendName = sendName;
            }

            public String getReceiveUuid() {
                return receiveUuid;
            }

            public void setReceiveUuid(String receiveUuid) {
                this.receiveUuid = receiveUuid;
            }

            public String getSendUuid() {
                return sendUuid;
            }

            public void setSendUuid(String sendUuid) {
                this.sendUuid = sendUuid;
            }
        }
    }
}
