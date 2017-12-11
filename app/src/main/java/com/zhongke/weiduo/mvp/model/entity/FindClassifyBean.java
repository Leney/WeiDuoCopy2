package com.zhongke.weiduo.mvp.model.entity;

import java.util.List;

/**
 * Created by hyx on 2017/11/21.
 * 发现的活动类别
 */

public class FindClassifyBean {
    /**
     * pageTotal : 2
     * pageIndex : 1
     * records : [{"createUserId":90,"createTime":"2017-11-10 14:25:05","kindName":"直播类","id":150,"orgId":1},{"createUserId":90,"createTime":"2017-11-10 14:25:21","kindName":"宣传类","id":151,"orgId":1},{"createUserId":90,"createTime":"2017-11-10 14:25:37","kindName":"户外活动类","id":152,"orgId":1},{"createUserId":90,"createTime":"2017-11-10 14:26:10","kindName":"文科类","id":153,"orgId":1},{"createUserId":90,"createTime":"2017-11-10 14:29:47","kindName":"室内活动类","id":154,"orgId":1},{"createUserId":90,"createTime":"2017-11-10 14:30:26","kindName":"成语比赛类","id":155,"orgId":1},{"createUserId":90,"createTime":"2017-11-10 14:30:39","kindName":"文字游戏类","id":156,"orgId":1},{"createUserId":90,"createTime":"2017-11-10 14:30:48","kindName":"问答类","id":157,"orgId":1},{"createUserId":90,"createTime":"2017-11-10 14:32:50","kindName":"展览类","id":158,"orgId":1},{"createUserId":90,"createTime":"2017-11-09 11:36:26","kindName":"唱歌比赛","id":159,"orgId":1},{"createUserId":1,"createTime":"2017-11-06 20:31:45","kindName":"成语接龙","id":160,"orgId":1},{"createUserId":90,"createTime":"2017-11-09 11:36:35","kindName":"拔河比赛","id":161,"orgId":1},{"createUserId":90,"createTime":"2017-11-10 14:33:03","kindName":"参观类","id":162,"orgId":1},{"createUserId":90,"createTime":"2017-11-10 14:32:20","kindName":"文化类","id":163,"orgId":1},{"createUserId":90,"createTime":"2017-11-10 14:32:31","kindName":"历史类","id":164,"orgId":1},{"createUserId":90,"createTime":"2017-11-10 14:32:38","kindName":"语文类","id":165,"orgId":1}]
     * recordTotal : 29
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
         * createUserId : 90
         * createTime : 2017-11-10 14:25:05
         * kindName : 直播类
         * id : 150
         * orgId : 1
         */

        private int createUserId;
        private String createTime;
        private String kindName;
        private int id;
        private int orgId;

        public int getCreateUserId() {
            return createUserId;
        }

        public void setCreateUserId(int createUserId) {
            this.createUserId = createUserId;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getKindName() {
            return kindName;
        }

        public void setKindName(String kindName) {
            this.kindName = kindName;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getOrgId() {
            return orgId;
        }

        public void setOrgId(int orgId) {
            this.orgId = orgId;
        }

        @Override
        public String toString() {
            return "RecordsBean{" +
                    "createUserId=" + createUserId +
                    ", createTime='" + createTime + '\'' +
                    ", kindName='" + kindName + '\'' +
                    ", id=" + id +
                    ", orgId=" + orgId +
                    '}';
        }
    }
}
