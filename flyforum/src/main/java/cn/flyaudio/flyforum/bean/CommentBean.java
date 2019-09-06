package cn.flyaudio.flyforum.bean;

import java.util.List;

/**
 * Created by 叶兴运 on
 * 2019/1/21.18:21
 */
public class CommentBean extends BaseBean<CommentBean> {


    private int total;
    private List<ListBeanX> list;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<ListBeanX> getList() {
        return list;
    }

    public void setList(List<ListBeanX> list) {
        this.list = list;
    }

    public static class ListBeanX {
        /**
         * createTime : 2019-01-18 14:17:44.0
         * name :
         * userUuid : 21666e6947af49de9c2f6d78cb7c0c1f
         * avatar :
         * list : [{"receiveName":"测试150","sendName":"测试147","createTime":"2019-01-18 14:20:05.0","sendUuid":"1cfb32de2e824ab2a44a8c8305613a9d","content":"内部评论！！","receiveUuid":"879545bdff264a07b8b870de6edf7ca9"},{"receiveName":"测试150","sendName":"测试147","createTime":"2019-01-18 14:20:17.0","sendUuid":"1cfb32de2e824ab2a44a8c8305613a9d","content":"内部评论333333！！","receiveUuid":"879545bdff264a07b8b870de6edf7ca9"}]
         * uuid : c4e8e5881ae811e98ee77cd30aeb138e
         * content : 测试评论2232323233
         */

        private String createTime;
        private String likeNumber;
        private int isPraise;
        private String name;
        private String userUuid;
        private String avatar;
        private String uuid;

        public String getLikeNumber() {
            return likeNumber;
        }

        public void setLikeNumber(String likeNumber) {
            this.likeNumber = likeNumber;
        }

        public int getIsPraise() {
            return isPraise;
        }

        public void setIsPraise(int isPraise) {
            this.isPraise = isPraise;
        }

        private String content;
        private List<ListBean> list;

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

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
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

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * receiveName : 测试150
             * sendName : 测试147
             * createTime : 2019-01-18 14:20:05.0
             * sendUuid : 1cfb32de2e824ab2a44a8c8305613a9d
             * content : 内部评论！！
             * receiveUuid : 879545bdff264a07b8b870de6edf7ca9
             */

            private String receiveName;
            private String sendName;
            private String createTime;
            private String sendUuid;
            private String content;
            private String receiveUuid;

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

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getSendUuid() {
                return sendUuid;
            }

            public void setSendUuid(String sendUuid) {
                this.sendUuid = sendUuid;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getReceiveUuid() {
                return receiveUuid;
            }

            public void setReceiveUuid(String receiveUuid) {
                this.receiveUuid = receiveUuid;
            }
        }
    }
}
