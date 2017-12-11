package com.zhongke.weiduo.mvp.model.entity;

import java.util.List;

/**
 * Created by dgg1 on 2017/11/8.
 * 新的专家实体
 */

public class NewExpertBean {

    /**
     * pageTotal : 1
     * pageIndex : 1
     * records : [{"coverUrl":"http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/RnstXT8XZ4rM5ftktdmyaB6QN1509085032882.jpg","teachTag":"美术","nickName":"狐妖小红娘","id":87,"userId":90,"info":"国内有名的漫画师，中国话，树苗高手等。曾连续7年夺得世界漫画，中国话，树苗的冠军。"},"......"]
     * recordTotal : 8
     */

    private int pageTotal;
    private int pageIndex;
    private int recordTotal;
    private List<RecordsBean> records;

    public int getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(int pageTotal) {
        this.pageTotal = pageTotal;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getRecordTotal() {
        return recordTotal;
    }

    public void setRecordTotal(int recordTotal) {
        this.recordTotal = recordTotal;
    }

    public List<RecordsBean> getRecords() {
        return records;
    }

    public void setRecords(List<RecordsBean> records) {
        this.records = records;
    }

    public static class RecordsBean {
        /**
         * coverUrl : http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/RnstXT8XZ4rM5ftktdmyaB6QN1509085032882.jpg
         * teachTag : 美术
         * nickName : 狐妖小红娘
         * id : 87
         * userId : 90
         * info : 国内有名的漫画师，中国话，树苗高手等。曾连续7年夺得世界漫画，中国话，树苗的冠军。
         */

        private String coverUrl;
        private String teachTag;
        private String nickName;
        private int id;
        private int userId;
        private String info;

        public String getCoverUrl() {
            return coverUrl;
        }

        public void setCoverUrl(String coverUrl) {
            this.coverUrl = coverUrl;
        }

        public String getTeachTag() {
            return teachTag;
        }

        public void setTeachTag(String teachTag) {
            this.teachTag = teachTag;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        @Override
        public String toString() {
            return "RecordsBean{" +
                    "coverUrl='" + coverUrl + '\'' +
                    ", teachTag='" + teachTag + '\'' +
                    ", nickName='" + nickName + '\'' +
                    ", id=" + id +
                    ", userId=" + userId +
                    ", info='" + info + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "NewExpertBean{" +
                "pageTotal=" + pageTotal +
                ", pageIndex=" + pageIndex +
                ", recordTotal=" + recordTotal +
                ", records=" + records +
                '}';
    }
}
