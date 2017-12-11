package com.zhongke.weiduo.mvp.model.entity;

import java.util.List;

/**
 * 搜索家庭或群组列表Bean对象
 * Created by llj on 2017/11/16.
 */

public class SearchFamilyAndGroupResultBean {

    /**
     * pageTotal : 1
     * pageIndex : 1
     * recordTotal : 1
     * resultList : [{"gIconUrl":"http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/jnWfYAMxE7TrWpxeQHFr3XaNN1508729362137.jpg","gName":"gfdfsd","gCoverUrl":"http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/Sef36t5EfDm6rCcNEzN3Ckpe61508729364445.png","id":58}]
     */

    private int pageTotal;
    private int pageIndex;
    private int recordTotal;
    private List<ResultListBean> resultList;

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

    public List<ResultListBean> getResultList() {
        return resultList;
    }

    public void setResultList(List<ResultListBean> resultList) {
        this.resultList = resultList;
    }

    public static class ResultListBean {
        /**
         * gIconUrl : http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/jnWfYAMxE7TrWpxeQHFr3XaNN1508729362137.jpg
         * gName : gfdfsd
         * gCoverUrl : http://zhongke.oss-cn-shenzhen.aliyuncs.com/res_pic/Sef36t5EfDm6rCcNEzN3Ckpe61508729364445.png
         * id : 58
         */

        private String gIconUrl;
        private String gName;
        private String gCoverUrl;
        private int id;

        public String getGIconUrl() {
            return gIconUrl;
        }

        public void setGIconUrl(String gIconUrl) {
            this.gIconUrl = gIconUrl;
        }

        public String getGName() {
            return gName;
        }

        public void setGName(String gName) {
            this.gName = gName;
        }

        public String getGCoverUrl() {
            return gCoverUrl;
        }

        public void setGCoverUrl(String gCoverUrl) {
            this.gCoverUrl = gCoverUrl;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
